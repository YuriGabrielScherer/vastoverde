package br.com.karate.projetokarate.data.pessoa;

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

import br.com.karate.projetokarate.data.atleta.Atleta;
import br.com.karate.projetokarate.data.atleta.AtletaService;
import br.com.karate.projetokarate.messaging.ErrorCategory;
import br.com.karate.projetokarate.messaging.ServiceException;
import br.com.karate.projetokarate.model.pessoa.PessoaSaveInput;

@Component
public class PessoaValidator {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private AtletaService atletaService;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Validator validator = factory.getValidator();

	private Set<ConstraintViolation<PessoaSaveInput>> validar(PessoaSaveInput payload) {
		Set<ConstraintViolation<PessoaSaveInput>> constraintViolations = validator.validate(payload);
		return constraintViolations;
	}

	protected void validarPessoa(PessoaSaveInput payload) {
		Set<ConstraintViolation<PessoaSaveInput>> validations = validar(payload);

		if (!validations.isEmpty()) {
			List<String> erros = new ArrayList<String>();
			validations.stream().forEach(erro -> {
				erros.add("Campo " + erro.getPropertyPath() + " " + erro.getMessage() + "\n");
			});

			throw new ServiceException(ErrorCategory.BAD_REQUEST, erros.toString(), "Cadastro de Pessoa");
		}

		validarCpf(payload.getCpf());
	}

	protected void validarDuplicada(PessoaSaveInput payload) {
		boolean existePessoa = this.pessoaRepository.existsByCpf(payload.getCpf());
		if (existePessoa)
			throw new ServiceException(ErrorCategory.BAD_REQUEST, "CPF duplicado no banco de dados.",
					"Cadastro de Pessoa");

		existePessoa = this.pessoaRepository.existsByEmail(payload.getEmail());
		if (existePessoa)
			throw new ServiceException(ErrorCategory.BAD_REQUEST, "E-mail duplicado no banco de dados.",
					"Cadastro de Pessoa");

	}

	/*
	 * Este método verifica se existe algum atleta vinculado à pessoa.
	 * 
	 */

	protected void verificaAtletaDependente(Pessoa pessoa) {
		Atleta atleta = this.atletaService.findByIdWithoutException(pessoa.getid());

		if (atleta != null)
			throw new ServiceException(ErrorCategory.BAD_REQUEST,
					"Essa pessoa possui um atleta vinculado a ela. Exclua o atleta e tente novamente.",
					"Exclusão de Pessoas");
	}

	private void validarCpf(String cpf) {
		// TODO implementar metodo validar CPF - em outro lugar.
	}

}
