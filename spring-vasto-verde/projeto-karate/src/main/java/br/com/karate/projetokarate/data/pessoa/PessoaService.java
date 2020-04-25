package br.com.karate.projetokarate.data.pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.karate.projetokarate.messaging.ErrorCategory;
import br.com.karate.projetokarate.messaging.MessageService;
import br.com.karate.projetokarate.messaging.ServiceException;
import br.com.karate.projetokarate.model.pessoa.PesquisarPessoasInput;
import br.com.karate.projetokarate.model.pessoa.PessoaDto;
import br.com.karate.projetokarate.model.pessoa.PessoaSaveInput;

@Service
public class PessoaService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaService.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaValidator validar;
	

	public Pessoa findById(int idPessoa) {
		LOGGER.info("Encontrando pessoa por ID...");
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(idPessoa);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}
		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Pessoa não encontrada com o ID especificado.");
	}
	
	public List<Pessoa> findAll() {
		List<Pessoa> output = this.pessoaRepository.findAllByAtivoTrue();
		return output;
	}


	public Page<Pessoa> findAll(PesquisarPessoasInput filtro) {
		LOGGER.info("Encontrar todas as pessoas...");
		Pageable paginacao = PageRequest.of(filtro.getPaginacao().getPagina(), filtro.getPaginacao().getNumeroRegistrosPagina());
		Page<Pessoa> pagePessoa = this.pessoaRepository.findAllByAtivoTrue(paginacao);
		return pagePessoa;
	}

	public Pessoa findByEmail(String email) {
		LOGGER.info("Encontrando pessoa por e-mail...");
		Optional<Pessoa> pessoa = this.pessoaRepository.findByEmail(email);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}
		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Pessoa não encontrada com o e-mail especificado.");
	}

	public Pessoa findByLogin(String login) {
		LOGGER.info("Encontrando pessoa por login...");
		Optional<Pessoa> pessoa = this.pessoaRepository.findByLogin(login);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}
		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Pessoa não encontrada com o login especificado.");
	}

	public Pessoa findByCpf(String cpf) {
		LOGGER.info("Retornando pessoa por CPF...");
		Optional<Pessoa> pessoa = this.pessoaRepository.findByCpf(cpf);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}
		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Pessoa não encontrada com o CPF especificado.");
	}

	public ResponseEntity<String> excluir(String cpf) {
		LOGGER.info("Excluindo pessoa...");
		Optional<Pessoa> pessoa = this.pessoaRepository.findByCpf(cpf);

		if (pessoa.isPresent()) {
			validar.verificaAtletaDependente(pessoa.get());
			pessoa.get().setAtivo(false);
			this.pessoaRepository.save(pessoa.get());
			LOGGER.info("Pessoa excluída com sucesso!");
			return MessageService.send(HttpStatus.OK);
		}

		return MessageService.send(HttpStatus.BAD_REQUEST, "Não foi possível excluir a pessoa.",
				"Exclusão de Pessoas.");
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		LOGGER.info("Buscando usuário por username...");
		Pessoa pessoa = this.findByLogin(login);

		return new org.springframework.security.core.userdetails.User(pessoa.getLogin(), pessoa.getSenha(),
				new ArrayList<>());
	}

	public ResponseEntity<?> cadastrar(PessoaSaveInput payload) {
		LOGGER.info("Salvando pessoa...");
		validar.validarPessoa(payload);
		validar.validarDuplicada(payload);

		try {
			Pessoa pessoa = PessoaConverter.toRec(payload);
			pessoaRepository.save(pessoa);
			LOGGER.info("Pessoa cadastrada com sucesso...");
		} catch (Exception e) {
			LOGGER.error("Erro ao cadastrar a pessoa " + payload.getCpf(), e.getCause());
			throw new ServiceException(ErrorCategory.BAD_REQUEST, e.getMessage(), e.getCause(), e.getMessage());
		}
		return MessageService.send(HttpStatus.CREATED);
	}

	public ResponseEntity<?> alterar(Pessoa payload) {
		LOGGER.info("Alterando pessoa...");
		Pessoa pessoa = this.findByCpf(payload.getCpf());
		pessoa = PessoaConverter.toPut(pessoa, payload);

		try {
			pessoaRepository.save(pessoa);

			return MessageService.send(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Erro ao alterar pessoa no banco de dados...");
			throw new ServiceException(ErrorCategory.BAD_REQUEST, e.getMessage(), e.getCause(), e.getMessage());
		}
	}

}
