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
import org.springframework.transaction.annotation.Transactional;

import br.com.karate.projetokarate.messaging.ErrorCategory;
import br.com.karate.projetokarate.messaging.ServiceException;
import br.com.karate.projetokarate.model.associacao.AssociacaoSaveInput;
import br.com.karate.projetokarate.model.associacao.PesquisarAssociacaoInput;

@Service
public class AssociacaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssociacaoService.class);

	@Autowired
	private AssociacaoRepository associacaoRepository;

	@Autowired
	private AssociacaoValidator validar;

	@Autowired
	private AssociacaoConverter associacaoConverter;

	@Transactional(readOnly = true)
	public Associacao findById(int id) {
		Optional<Associacao> a = this.associacaoRepository.findById(id);
		if (a.isPresent() && a.get().isAtivo())
			return a.get();
		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Associação não encontrada com o ID especificado.",
				"Busca por associação.");
	}

	@Transactional(readOnly = true)
	public Associacao findByCodigoWithoutThrow(int codigo) {
		Associacao a = associacaoRepository.findByCodigo(codigo);
		if (a != null && a.isAtivo())
			return a;

		return null;
	}

	@Transactional(readOnly = true)
	public Associacao findByCodigo(int codigo) {
		Associacao a = associacaoRepository.findByCodigo(codigo);
		if (a != null && a.isAtivo())
			return a;

		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Associação não encontrada com o código especificado.");
	}

	@Transactional(readOnly = true)
	public boolean existsByNome(String nome) {
		return this.associacaoRepository.existsByNome(nome);
	}

	@Transactional(readOnly = true)
	public boolean existsByCodigo(int codigo) {
		return this.associacaoRepository.existsByCodigo(codigo);
	}

	@Transactional(readOnly = true)
	public List<Associacao> findAll() {
		return this.associacaoRepository.findAll().stream().filter(a -> a.isAtivo()).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Page<Associacao> findAll(PesquisarAssociacaoInput filtro) {

		Pageable paginacao = PageRequest.of(filtro.getPaginacao().getPagina(),
				filtro.getPaginacao().getNumeroRegistrosPagina());

		if (filtro.getNome() == null && filtro.getCidade() == null) {
			return associacaoRepository.findAllByAtivoTrue(paginacao);
		}

		if (filtro.getNome() != null && filtro.getCidade() != null) {
			return associacaoRepository.findAllByAtivoTrueAndNomeIgnoreCaseContainingOrCidadeIgnoreCaseContaining(
					paginacao, filtro.getNome(), filtro.getCidade());
		}

		if (filtro.getNome() != null) {
			return associacaoRepository.findAllByAtivoTrueAndNomeIgnoreCaseContaining(paginacao, filtro.getNome());
		}

		return associacaoRepository.findAllByAtivoTrueAndCidadeIgnoreCaseContaining(paginacao, filtro.getCidade());

	}

	public ResponseEntity<?> save(AssociacaoSaveInput asso) {
		validar.validarAssociacao(asso);

		Associacao associacao = associacaoRepository.findByCodigo(asso.getCodigo());

		if (associacao == null) {
			associacao = new Associacao();
			validar.validarDuplicada(asso);
		}

		associacao = associacaoConverter.toRec(asso, associacao);
		try {
			this.associacaoRepository.save(associacao);
			LOGGER.info("Associação cadastrada no banco de dados...");
		} catch (Exception e) {
			LOGGER.error("Erro ao salvar associação...", e.getCause());
			throw new ServiceException(ErrorCategory.BAD_REQUEST, "Erro ao salvar a associação.", "Salvar Associação.");
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
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
