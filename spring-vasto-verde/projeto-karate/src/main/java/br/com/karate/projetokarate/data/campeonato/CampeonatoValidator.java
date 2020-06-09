package br.com.karate.projetokarate.data.campeonato;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.karate.projetokarate.messaging.ErrorCategory;
import br.com.karate.projetokarate.messaging.ServiceException;
import br.com.karate.projetokarate.model.campeonato.CampeonatoSaveInput;

@Component
public class CampeonatoValidator {

	@Autowired
	private CampeonatoService campeonatoService;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	private Set<ConstraintViolation<CampeonatoSaveInput>> validar(CampeonatoSaveInput campeonato) {
		Set<ConstraintViolation<CampeonatoSaveInput>> violations = validator.validate(campeonato);
		return violations;
	}

	protected void validarCampeonato(CampeonatoSaveInput campeonato) {
		Set<ConstraintViolation<CampeonatoSaveInput>> validations = this.validar(campeonato);

		if (!validations.isEmpty()) {
			List<String> erros = new ArrayList<>();
			validations.stream()
					.forEach(erro -> erros.add("Campo " + erro.getPropertyPath() + " " + erro.getMessage() + " \n"));

			throw new ServiceException(ErrorCategory.BAD_REQUEST, erros.toString(), "Cadastro de Campeonatos");
		}
	}
	
	protected void validarDuplicado(Campeonato campeonato) {
		// TODO validar cadastro de campeonatos duplicados por Nome ou, Data e Cidade.
	}

}
