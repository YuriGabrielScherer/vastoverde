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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.karate.projetokarate.utils.MessageException;
import br.com.karate.projetokarate.utils.MessageService;

@Service
public class PessoaService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaService.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public Optional<Pessoa> findById(int idPessoa) {
		return this.pessoaRepository.findById(idPessoa);
	}

	public List<Pessoa> findAll() {
		return this.pessoaRepository.findAll();
	}

	public Pessoa getByEmail(String email) {
		return this.pessoaRepository.findByEmail(email);
	}

	public ResponseEntity<Pessoa> getByCpf(String cpf) {
		Pessoa pessoa = this.pessoaRepository.findByCpf(cpf);
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
	}

	public ResponseEntity<String> excluir(int idPessoa) {
		LOGGER.info("Buscando pessoa para exclusão.");
		Optional<Pessoa> pessoa = this.pessoaRepository.findById(idPessoa);

		if (pessoa != null) {
			this.pessoaRepository.delete(pessoa.get());
			return MessageService.send(HttpStatus.OK, "Usuário excluído com sucesso!", "Excluído!");
		}

		return MessageService.send(HttpStatus.BAD_REQUEST, "Não foi possível excluir a pessoa.",
				"Pessoa não encontrada.");
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Pessoa pessoa = pessoaRepository.findByEmail(email);

		if (pessoa == null)
			throw new UsernameNotFoundException("User not found with email: " + email);

		return new org.springframework.security.core.userdetails.User(pessoa.getEmail(), pessoa.getSenha(),
				new ArrayList<>());

	}

	public ResponseEntity<?> save(Pessoa payload) {
		Pessoa pessoa = new Pessoa();

		pessoa.setNome(payload.getNome());
		pessoa.setCpf(payload.getCpf());
		pessoa.setDataNascimento(payload.getDataNascimento());
		pessoa.setEmail(payload.getEmail());
		pessoa.setSenha(bcryptEncoder.encode(payload.getSenha()));
		System.out.println("Senha -> " + pessoa.getSenha());
		pessoa.setSexo(payload.getSexo());
		pessoa.setTelefone(payload.getTelefone());
		pessoa.setTipoUsuario(payload.getTipoUsuario());

		try {
			pessoaRepository.save(pessoa);
			return MessageService.send(HttpStatus.CREATED, "A pessoa foi salva com sucesso no banco de dados.",
					"Pessoa salva!");
		} catch (Exception e) {
			throw new MessageException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível salvar a pessoa.");
		}

	}

}
