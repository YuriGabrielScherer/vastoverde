package br.com.karate.projetokarate.associacao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.karate.projetokarate.utils.messaging.ErrorCategory;
import br.com.karate.projetokarate.utils.messaging.MessageException;

@Service
public class AssociacaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssociacaoService.class);

	@Autowired
	private AssociacaoRepository associacaoRepository;
	
	@Autowired
	private AssociacaoValidator validar;

	public Associacao findById(int id) {
		LOGGER.info("Retornando associação por ID...");
		Optional<Associacao> a = this.associacaoRepository.findById(id);

		if (a.isPresent() && a.get().isAtivo())
			return a.get();

		throw new MessageException(ErrorCategory.BAD_REQUEST, "Associação não encontrada com o ID especificado.",
				"Busca por associação.");
	}
	
	public List<Associacao> findAll(){
		return this.associacaoRepository.findAll();
	}

	public ResponseEntity<?> save(Associacao a) {
		LOGGER.info("Salvando associação...");
		// Validations

		try {
			this.associacaoRepository.save(a);
			LOGGER.info("Associação salva...");
		} catch (Exception e) {
			LOGGER.error("Erro ao salvar associação...", e.getCause());
			System.out.println(e.getMessage());
			throw new MessageException(ErrorCategory.BAD_REQUEST, "Erro ao salvar a associação.", "Salvar Associação.");
		}

		return new ResponseEntity<String>("Associação cadastrada com sucesso!", HttpStatus.CREATED);
	}

	public ResponseEntity<?> delete(int id) {
		LOGGER.info("Excluindo associação...");
		Associacao a = this.findById(id);
		
		validar.verificarAtletaVinculado(a);
		
		a.setAtivo(false);

		try {
			this.associacaoRepository.save(a);
			LOGGER.info("Associação excluída com sucesso...");
		} catch (Exception e) {
			LOGGER.error("Erro ao excluir associação...", e.getCause());
		}

		return new ResponseEntity<String>("Associação excluída com sucesso.", HttpStatus.OK);
	}

}
