package br.com.karate.projetokarate.pessoa;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.karate.projetokarate.utils.CriptografarSenha;

public class PessoaConverter {

	@Autowired
	private static CriptografarSenha senhaCrip = new CriptografarSenha();

	public static PessoaDto toDto(Pessoa payload) {
		PessoaDto pessoa = new PessoaDto();

		pessoa.setNome(payload.getNome());
		pessoa.setCpf(payload.getCpf());
		pessoa.setDataNascimento(payload.getDataNascimento());
		pessoa.setEmail(payload.getEmail());
		pessoa.setSexo(payload.getSexo());
		pessoa.setTelefone(payload.getTelefone());
		pessoa.setTipoUsuario(payload.getTipoUsuario());
		return pessoa;
	}

	public static Pessoa toRec(Pessoa payload) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(payload.getNome());
		pessoa.setCpf(payload.getCpf());
		pessoa.setDataNascimento(payload.getDataNascimento());
		pessoa.setEmail(payload.getEmail());
		pessoa.setSenha(senhaCrip.criptografar(payload.getSenha()));
		pessoa.setSexo(payload.getSexo());
		pessoa.setTelefone(payload.getTelefone());
		pessoa.setTipoUsuario(payload.getTipoUsuario());

		return pessoa;
	}

	public static Pessoa toPut(Pessoa pessoa, Pessoa payload) {

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
}
