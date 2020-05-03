package br.com.karate.projetokarate.data.associacao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.karate.projetokarate.messaging.ErrorCategory;
import br.com.karate.projetokarate.messaging.ServiceException;
import br.com.karate.projetokarate.model.associacao.PesquisarAssociacaoInput;

@Service
public class AssociacaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssociacaoService.class);

	@Autowired
	private AssociacaoRepositoryImpl associacaoRepository;

	@Autowired
	private AssociacaoValidator validar;

	public Associacao findById(int id) {
		LOGGER.info("Retornando associação por ID...");
		Optional<Associacao> a = this.associacaoRepository.findById(id);
		if (a.isPresent() && a.get().isAtivo())
			return a.get();
		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Associação não encontrada com o ID especificado.",
				"Busca por associação.");
	}

	public boolean existsByNome(String nome) {
		return this.associacaoRepository.existsByNome(nome);
	}

	public List<Associacao> findAll() {
		return this.associacaoRepository.findAll().stream().filter(a -> a.isAtivo()).collect(Collectors.toList());
	}

	public Page<Associacao> findAll(PesquisarAssociacaoInput filtro) {
		Pageable paginacao = PageRequest.of(filtro.getPaginacao().getPagina(),
				filtro.getPaginacao().getNumeroRegistrosPagina());
		Page<Associacao> pageAssociacao = this.associacaoRepository.findAllByAtivoTrue(paginacao);
		
		return pageAssociacao;
	}

	public ResponseEntity<?> save(Associacao a) {
		LOGGER.info("Salvando associação...");
		// TODO implementar metodo validar da associacao
		validar.validarDuplicada(a);

		try {
			this.associacaoRepository.save(a);
			LOGGER.info("Associação salva...");
		} catch (Exception e) {
			LOGGER.error("Erro ao salvar associação...", e.getCause());
			throw new ServiceException(ErrorCategory.BAD_REQUEST, "Erro ao salvar a associação.", "Salvar Associação.");
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
