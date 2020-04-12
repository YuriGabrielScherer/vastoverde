package br.com.karate.projetokarate.associacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.karate.projetokarate.atleta.Atleta;
import br.com.karate.projetokarate.atleta.AtletaService;
import br.com.karate.projetokarate.utils.messaging.ErrorCategory;
import br.com.karate.projetokarate.utils.messaging.MessageException;

public class AssociacaoValidator {

	@Autowired
	private AssociacaoRepository associacaoRepository;

	@Autowired
	private AtletaService atletaService;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	private Set<ConstraintViolation<Associacao>> validar(Associacao a) {
		Set<ConstraintViolation<Associacao>> violation = validator.validate(a);
		return violation;
	}

	protected void validarAssociacao(Associacao a) {
		Set<ConstraintViolation<Associacao>> validations = validar(a);

		if (!validations.isEmpty()) {
			List<String> erros = new ArrayList<>();
			validations.stream().forEach(erro -> {
				erros.add("Campo " + erro.getPropertyPath() + " " + erro.getMessage() + "\n");
			});

			throw new MessageException(ErrorCategory.BAD_REQUEST, erros.toString(), "Cadastro de Associações.");
		}

	}

	protected void validarDuplicada(Associacao associacao) {
		boolean exists = this.associacaoRepository.existsByNome(associacao.getNome());

		if (exists)
			throw new MessageException(ErrorCategory.BAD_REQUEST, "Associação dupliacada no banco de dados.",
					"Cadastro de Associações.");
	}

	protected void verificarAtletaVinculado(Associacao associacao) {
		List<Atleta> atletas = this.atletaService.findAllByIdAssociacao();

		if (!atletas.isEmpty()) {
			List<String> nomes = new ArrayList<>();
			atletas.stream()
					.forEach(a -> nomes.add("Atleta " + a.getPessoa().getNome() + " está vinculado à Associação.\n"));

			throw new MessageException(ErrorCategory.BAD_REQUEST, nomes.toString(), "Exclusão de Associação.");
		}

	}
}
