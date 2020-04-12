package br.com.karate.projetokarate.atleta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.karate.projetokarate.pessoa.Pessoa;
import br.com.karate.projetokarate.pessoa.PessoaService;
import br.com.karate.projetokarate.utils.messaging.ErrorCategory;
import br.com.karate.projetokarate.utils.messaging.MessageException;

public class AtletaValidator {

	@Autowired
	private AtletaRepository atletaRepository;
	
	@Autowired
	private PessoaService pessoaService;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Validator validator = factory.getValidator();

	private Set<ConstraintViolation<Atleta>> validar(Atleta payload) {
		Set<ConstraintViolation<Atleta>> violations = validator.validate(payload);
		return violations;
	}

	protected void validarAtleta(Atleta payload) {
		Set<ConstraintViolation<Atleta>> validations = validar(payload);

		if (!validations.isEmpty()) {
			List<String> erros = new ArrayList<>();
			validations.stream().forEach(erro -> {
				erros.add("Campo " + erro.getPropertyPath() + " " + erro.getMessage() + "\n");
			});
			throw new MessageException(ErrorCategory.BAD_REQUEST, erros.toString(), "Cadastro de Atleta");
		}
	}

	protected void validarDuplicado(AtletaSaveInput payload) {
		Pessoa pessoa = this.pessoaService.findByCpf(payload.getCpfPessoa());
		
		boolean exists = this.atletaRepository.existsById(pessoa.getid());
		if (exists)
			throw new MessageException(ErrorCategory.BAD_REQUEST, "Atleta duplicado no banco de dados.", "Cadastro de Atleta");

	}

}
