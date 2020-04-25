package br.com.karate.projetokarate.data.pessoa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import br.com.karate.projetokarate.model.pessoa.PessoaCampOutput;
import br.com.karate.projetokarate.model.pessoa.PessoaDto;
import br.com.karate.projetokarate.model.pessoa.PessoaSaveInput;
import br.com.karate.projetokarate.utils.CriptografarSenha;

public class PessoaConverter {

	@Autowired
	private static CriptografarSenha senhaCrip = new CriptografarSenha();

	public static PessoaDto toDto(Pessoa payload) {
		PessoaDto pessoa = new PessoaDto();

		pessoa.setNome(payload.getNome());
		pessoa.setCpf(payload.getCpf());
		pessoa.setDataNascimento(payload.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		pessoa.setEmail(payload.getEmail());
		pessoa.setSexo(payload.getSexo());
		pessoa.setTelefone(payload.getTelefone());
		pessoa.setTipoUsuario(payload.getTipoUsuario());
		return pessoa;
	}

	public static List<PessoaDto> toDto(Page<Pessoa> page) {
		List<PessoaDto> pessoas = new ArrayList<>();
		page.getContent().stream().forEach(p -> {
			if (p.isAtivo()) {
				pessoas.add(toDto(p));
			}
		});
		return pessoas;
	}

	@SuppressWarnings("deprecation")
	public static Pessoa toRec(PessoaSaveInput payload) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(payload.getNome());
		pessoa.setCpf(payload.getCpf());
		pessoa.setDataNascimento(LocalDateTime.of(payload.getDataNascimento().getYear(),
				payload.getDataNascimento().getMonth(), payload.getDataNascimento().getDay(), 0, 0));
		pessoa.setEmail(payload.getEmail());
		pessoa.setLogin(payload.getLogin());
		pessoa.setSenha(senhaCrip.criptografar(payload.getSenha()));
		pessoa.setSexo(payload.getSexo());
		pessoa.setTelefone(payload.getTelefone());
		pessoa.setAtivo(true);

		if (payload.getTipoUsuario() == null) {
			pessoa.setTipoUsuario(TipoPessoa.Normal);
		} else {
			pessoa.setTipoUsuario(payload.getTipoUsuario());
		}

		return pessoa;
	}

	public static Pessoa toPut(Pessoa pessoa, Pessoa payload) {
		// TODO finalizar PUT pessoa
		if (payload.getNome() != null)
			pessoa.setNome(payload.getNome());
		if (payload.getCpf() != null)
			pessoa.setCpf(payload.getCpf());
		if (payload.getDataNascimento() != null)
			pessoa.setDataNascimento(payload.getDataNascimento());
		if (payload.getEmail() != null)
			pessoa.setEmail(payload.getEmail());
		if (payload.getSenha() != null)
			pessoa.setSenha(senhaCrip.criptografar(payload.getSenha()));
		if (payload.getTelefone() != null)
			pessoa.setTelefone(payload.getTelefone());

		pessoa.setTipoUsuario(payload.getTipoUsuario());
		pessoa.setSexo(payload.getSexo());
		return pessoa;
	}

	public static PessoaCampOutput toCamp(Pessoa payload) {
		PessoaCampOutput pessoa = new PessoaCampOutput();

		pessoa.setNome(payload.getNome());
		pessoa.setCpf(payload.getCpf());
		pessoa.setDataNascimento(payload.getDataNascimento());
		pessoa.setEmail(payload.getEmail());
		pessoa.setSexo(payload.getSexo());
		pessoa.setTelefone(payload.getTelefone());

		return pessoa;
	}
}
