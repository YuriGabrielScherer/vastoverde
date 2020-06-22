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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.karate.projetokarate.messaging.ErrorCategory;
import br.com.karate.projetokarate.messaging.MessageService;
import br.com.karate.projetokarate.messaging.ServiceException;
import br.com.karate.projetokarate.model.pessoa.PesquisarPessoasInput;
import br.com.karate.projetokarate.model.pessoa.PessoaSaveInput;

@Service
public class PessoaService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaService.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaConverter pessoaConverter;

	@Autowired
	private PessoaValidator validar;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Pessoa pessoa = this.findByLogin(login);

		return new User(pessoa.getLogin(), pessoa.getSenha(), new ArrayList<>());
	}

	@Transactional(readOnly = true)
	public boolean existsByCpf(String cpf) {
		return this.pessoaRepository.existsByCpf(cpf);
	}

	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
		return this.pessoaRepository.existsByEmail(email);
	}

	@Transactional(readOnly = true)
	public boolean existsByCodigo(int codigo) {
		return this.pessoaRepository.existsByCodigo(codigo);
	}

	@Transactional(readOnly = true)
	public Pessoa findById(int idPessoa) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(idPessoa);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}
		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Pessoa não encontrada com o ID especificado.");
	}

	@Transactional(readOnly = true)
	public List<Pessoa> findAll() {
		List<Pessoa> output = this.pessoaRepository.findAllByAtivoTrueAndFaixaIdIsNull();
		return output;
	}

	@Transactional(readOnly = true)
	public Page<Pessoa> findAll(PesquisarPessoasInput filtro) {
		Pageable paginacao = PageRequest.of(filtro.getPaginacao().getPagina(),
				filtro.getPaginacao().getNumeroRegistrosPagina());
		Page<Pessoa> pagePessoa = this.pessoaRepository.findAllByAtivoTrue(paginacao);
		return pagePessoa;
	}

	@Transactional(readOnly = true)
	public Optional<Pessoa> findByEmailWithoutThrow(String email) {
		return this.pessoaRepository.findByEmail(email);
	}

	@Transactional(readOnly = true)
	public boolean existsEmailWithoutThrow(String email) {
		return this.findByEmailWithoutThrow(email).isPresent();
	}

	@Transactional(readOnly = true)
	public Pessoa findByLogin(String login) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findByLogin(login);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}
		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Pessoa não encontrada com o login especificado.");
	}

	@Transactional(readOnly = true)
	public Pessoa findByCpf(String cpf) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findByCpf(cpf);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}

		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Pessoa não encontrada com o CPF especificado.");
	}

	@Transactional(readOnly = true)
	public Pessoa findByCpfWithoutThrow(String cpf) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findByCpf(cpf);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}

		return null;
	}

	public ResponseEntity<String> excluir(String cpf) {
		Pessoa pessoa = this.findByCpf(cpf);
		pessoa.setAtivo(false);
		// TODO Validar se pode excluir
		try {
			this.pessoaRepository.save(pessoa);
			return MessageService.send(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Erro ao excluir pessoa...");
			LOGGER.trace(e.getStackTrace().toString());
			return MessageService.send(HttpStatus.BAD_REQUEST, "Não foi possível excluir a pessoa.",
					"Exclusão de Pessoas.");
		}

	}

	public ResponseEntity<?> cadastrar(PessoaSaveInput payload) {
		validar.validarCadastroPessoa(payload);
		Pessoa pessoa = findByCpfWithoutThrow(payload.getCpf());

		if (pessoa == null) {
			pessoa = new Pessoa();
			validar.validarDuplicada(payload);
		}

		pessoa = pessoaConverter.toRec(payload, pessoa);
		try {
			pessoaRepository.save(pessoa);
			LOGGER.info("Pessoa cadastrada com sucesso...");
			return MessageService.send(HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error("Erro ao cadastrar a pessoa " + payload.getCpf(), e.getCause());
			throw new ServiceException(ErrorCategory.BAD_REQUEST, e.getMessage(), e.getCause(), e.getMessage());
		}
	}
}
