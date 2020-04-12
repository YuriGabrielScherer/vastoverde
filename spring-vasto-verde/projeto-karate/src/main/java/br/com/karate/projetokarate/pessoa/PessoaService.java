package br.com.karate.projetokarate.pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.karate.projetokarate.utils.messaging.ErrorCategory;
import br.com.karate.projetokarate.utils.messaging.MessageException;
import br.com.karate.projetokarate.utils.messaging.MessageService;

@Service
public class PessoaService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaService.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaValidator validar;
	
	public Pessoa findById(int idPessoa) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(idPessoa);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}
		throw new MessageException(ErrorCategory.BAD_REQUEST, "Pessoa não encontrada com o ID especificado.");
	}

	public List<Pessoa> findAll() {
		return this.pessoaRepository.findAll();
	}

	public Pessoa findByEmail(String email) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findByEmail(email);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}
		throw new MessageException(ErrorCategory.BAD_REQUEST, "Pessoa não encontrada com o e-mail especificado.");
	}

	public Pessoa findByCpf(String cpf) {
		LOGGER.info("Retornando pessoa por CPF...");
		Optional<Pessoa> pessoa = this.pessoaRepository.findByCpf(cpf);
		if (pessoa.isPresent() && pessoa.get().isAtivo()) {
			return pessoa.get();
		}
		throw new MessageException(ErrorCategory.BAD_REQUEST, "Pessoa não encontrada com o CPF especificado.");
	}

	public ResponseEntity<String> excluir(int idPessoa) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(idPessoa);

		if (pessoa.isPresent()) {
			validar.verificaAtletaDependente(pessoa.get());
			pessoa.get().setAtivo(false);
			this.pessoaRepository.save(pessoa.get());
			LOGGER.info("Pessoa excluída com sucesso!");
			return MessageService.send(HttpStatus.OK, "Pessoa excluída com sucesso!", "Exclusão de Pessoas.");
		}

		return MessageService.send(HttpStatus.BAD_REQUEST, "Não foi possível excluir a pessoa.",
				"Exclusão de Pessoas.");
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Pessoa> pessoa = pessoaRepository.findByEmail(email);

		if (pessoa.get() == null)
			throw new UsernameNotFoundException("User not found with email: " + email);

		return new org.springframework.security.core.userdetails.User(pessoa.get().getEmail(), pessoa.get().getSenha(),
				new ArrayList<>());

	}

	public ResponseEntity<?> cadastrar(Pessoa payload) {
		validar.validarPessoa(payload);
		validar.validarDuplicada(payload);
		
		try {
			Pessoa pessoa = PessoaConverter.toRec(payload);
			pessoaRepository.save(pessoa);
			LOGGER.info("Pessoa cadastrada com sucesso...");
		} catch (Exception e) {
			LOGGER.error("Erro ao cadastrar a pessoa " + payload.getCpf(), e.getCause());
			throw new MessageException(ErrorCategory.BAD_REQUEST, e.getMessage(), e.getCause(), e.getMessage());
		}
		return MessageService.send(HttpStatus.CREATED, "A pessoa foi salva com sucesso no banco de dados.",
				"Pessoa salva!");
	}

	public ResponseEntity<?> alterar(Pessoa payload) {
		Pessoa pessoa = this.findByCpf(payload.getCpf());
		pessoa = PessoaConverter.toPut(pessoa, payload);

		pessoaRepository.save(pessoa);
		return MessageService.send(HttpStatus.OK, "A pessoa foi alterada com sucesso no banco de dados.",
				"Pessoa alterada!");

	}

}
