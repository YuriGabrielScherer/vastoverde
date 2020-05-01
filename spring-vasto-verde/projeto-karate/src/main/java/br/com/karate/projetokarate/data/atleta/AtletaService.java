package br.com.karate.projetokarate.data.atleta;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.karate.projetokarate.data.associacao.Associacao;
import br.com.karate.projetokarate.data.associacao.AssociacaoService;
import br.com.karate.projetokarate.data.pessoa.Pessoa;
import br.com.karate.projetokarate.data.pessoa.PessoaService;
import br.com.karate.projetokarate.messaging.ErrorCategory;
import br.com.karate.projetokarate.messaging.MessageService;
import br.com.karate.projetokarate.messaging.ServiceException;
import br.com.karate.projetokarate.model.atleta.AtletaSaveInput;

@Service
public class AtletaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AtletaService.class);

	@Autowired
	private AtletaRepository atletaRepository;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private AssociacaoService associacaoService;
	
	@Autowired
	private AtletaValidator validar;

	public List<Atleta> findAll() {
		return this.atletaRepository.findAll().stream().filter(a -> a.isAtivo()).collect(Collectors.toList());
	}

	public Atleta findById(int id) {
		LOGGER.info("Retornando usuário por ID...");
		Optional<Atleta> atleta = this.atletaRepository.findById(id);

		if (atleta.isPresent() && atleta.get().isAtivo())
			return atleta.get();

		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Atleta não encontrado com o ID especificado.",
				"Busca por atleta");
	}
	
	public Atleta findByIdWithoutException(int id) {
		LOGGER.info("Retornando usuário por ID without exception...");
		Optional<Atleta> atleta = this.atletaRepository.findById(id);

		if (atleta.isPresent() && atleta.get().isAtivo())
			return atleta.get();
		
		return null;
	}

	public List<Atleta> findAllByAssociacao(Associacao associacao) {
		LOGGER.info("Retornando todos os atletas por associação...");
		List<Atleta> atletas = this.atletaRepository.findAllByAssociacao(associacao).stream().filter(a -> a.isAtivo())
				.collect(Collectors.toList());
		return atletas;
	}

	public ResponseEntity<?> save(AtletaSaveInput atleta) {
		this.validar.validarAtleta(atleta);
		
		Pessoa pessoaAtleta = buscarPessoa(atleta.getCpfPessoa());
		Associacao associacao = buscarAssociacao(atleta.getIdAssociacao());
		
		try {
			this.atletaRepository.save(AtletaConverter.toRec(atleta, pessoaAtleta, associacao));
			LOGGER.info("Atleta criado com sucesso!");
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ServiceException(ErrorCategory.BAD_REQUEST, "Erro ao cadastrar o atleta", e.getCause(),
					e.getMessage());
		}

	}

	public ResponseEntity<?> delete(int id) {
		Optional<Atleta> atleta = this.atletaRepository.findById(id);
		if (!atleta.isPresent())
			throw new ServiceException(ErrorCategory.BAD_REQUEST, "Atleta não encontrado para realizar a exclusão.",
					"Exclusão de Atleta");
		try {
			atleta.get().setAtivo(false);
			this.atletaRepository.save(atleta.get());
			return MessageService.send(HttpStatus.OK, "Atleta excluído com sucesso!", "Exclusão de Atleta");
		} catch (Exception e) {
			throw new ServiceException(ErrorCategory.BAD_REQUEST, "Algo deu errado ao excluir o Atleta.",
					"Exclusão de Atletas.");
		}
	}

	private Pessoa buscarPessoa(String cpfPessoa) {
		Pessoa pessoa = this.pessoaService.findByCpf(cpfPessoa);
		return pessoa;
	}

	private Associacao buscarAssociacao(int id) {
		Associacao associacao = this.associacaoService.findById(id);
		return associacao;
	}

}
