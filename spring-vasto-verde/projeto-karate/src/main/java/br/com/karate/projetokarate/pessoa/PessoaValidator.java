package br.com.karate.projetokarate.pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.karate.projetokarate.atleta.Atleta;
import br.com.karate.projetokarate.atleta.AtletaRepository;
import br.com.karate.projetokarate.utils.messaging.ErrorCategory;
import br.com.karate.projetokarate.utils.messaging.MessageException;

@Component
public class PessoaValidator {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private AtletaRepository atletaRepository;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Validator validator = factory.getValidator();

	private Set<ConstraintViolation<Pessoa>> validar(Pessoa payload) {
		Set<ConstraintViolation<Pessoa>> constraintViolations = validator.validate(payload);
		return constraintViolations;
	}

	protected void validarPessoa(Pessoa payload) {
		Set<ConstraintViolation<Pessoa>> validations = validar(payload);

		if (!validations.isEmpty()) {
			List<String> erros = new ArrayList<String>();
			validations.stream().forEach(erro -> {
				erros.add("Campo " + erro.getPropertyPath() + " " + erro.getMessage() + "\n");
			});

			throw new MessageException(ErrorCategory.BAD_REQUEST, erros.toString(), "Cadastro de Pessoa");
		}

		validarCpf(payload.getCpf());
	}

	protected void validarDuplicada(Pessoa payload) {
		boolean existePessoa = this.pessoaRepository.existsByCpf(payload.getCpf());
		if (existePessoa)
			throw new MessageException(ErrorCategory.BAD_REQUEST, "CPF duplicado no banco de dados.",
					"Cadastro de Pessoa");

		existePessoa = this.pessoaRepository.existsByEmail(payload.getEmail());
		if (existePessoa)
			throw new MessageException(ErrorCategory.BAD_REQUEST, "E-mail duplicado no banco de dados.",
					"Cadastro de Pessoa");

	}

	/*
	 * Este método verifica se existe algum atleta vinculado à pessoa.
	 * 
	 */

	protected void verificaAtletaDependente(Pessoa pessoa) {
		Optional<Atleta> atleta = this.atletaRepository.findById(pessoa.getid());

		if (atleta.isPresent() && atleta.get().isAtivo())
			throw new MessageException(ErrorCategory.BAD_REQUEST,
					"Essa pessoa possui um atleta vinculado a ela. Exclua o atleta e tente novamente.",
					"Exclusão de Pessoas");
	}

	private void validarCpf(String cpf) {
		// TODO implementar metodo validar CPF - em outro lugar.		
	}

}
