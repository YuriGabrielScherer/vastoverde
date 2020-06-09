package br.com.karate.projetokarate.data.associacao;

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
import br.com.karate.projetokarate.model.associacao.AssociacaoSaveInput;

@Component
public class AssociacaoValidator {

	@Autowired
	private AssociacaoService associacaoService;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	private Set<ConstraintViolation<AssociacaoSaveInput>> validar(AssociacaoSaveInput a) {
		Set<ConstraintViolation<AssociacaoSaveInput>> violation = validator.validate(a);
		return violation;
	}

	protected void validarAssociacao(AssociacaoSaveInput a) {
		Set<ConstraintViolation<AssociacaoSaveInput>> validations = validar(a);

		if (!validations.isEmpty()) {
			List<String> erros = new ArrayList<>();
			validations.stream().forEach(erro -> {
				erros.add("Campo " + erro.getPropertyPath() + " " + erro.getMessage() + "\n");
			});
			throw new ServiceException(ErrorCategory.BAD_REQUEST, erros.toString(), "Cadastro de Associações.");
		}
	}

	protected void validarDuplicada(AssociacaoSaveInput associacao) {
		boolean exists = this.associacaoService.existsByNome(associacao.getNome());
		if (exists)
			throw new ServiceException(ErrorCategory.BAD_REQUEST, "Associação dupliacada no banco de dados.",
					"Cadastro de Associações.");
		
		exists = this.associacaoService.existsByCodigo(associacao.getCodigo());
		if (exists)
			throw new ServiceException(ErrorCategory.BAD_REQUEST, "Código da associação dupliacada no banco de dados.",
					"Cadastro de Associações.");
	}

	protected void verificarAtletaVinculado(Associacao associacao) {
		// TODO Verificar atletaVinculado
	}
}
