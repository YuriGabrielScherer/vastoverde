package br.com.karate.projetokarate.data.pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.karate.projetokarate.data.associacao.Associacao;
import br.com.karate.projetokarate.data.associacao.AssociacaoService;
import br.com.karate.projetokarate.model.atleta.AtletaDto;
import br.com.karate.projetokarate.model.pessoa.PessoaCampOutput;
import br.com.karate.projetokarate.model.pessoa.PessoaDto;
import br.com.karate.projetokarate.model.pessoa.PessoaSaveInput;

@Component
public class PessoaConverter {

	private PasswordEncoder senhaCrip = new BCryptPasswordEncoder();
	
	@Autowired
	private AssociacaoService associacaoService;	

	public PessoaDto toDto(Pessoa payload) {
		PessoaDto pessoa = new PessoaDto();

		pessoa.setNome(payload.getNome());
		pessoa.setCpf(payload.getCpf());
		pessoa.setDataNascimento(payload.getData_nascimento());		
		pessoa.setTelefone(payload.getTelefone());
		pessoa.setDataCadastro(payload.getData_cadastro());
		
		return pessoa;
	}

	public List<PessoaDto> toDto(Page<Pessoa> page) {
		List<PessoaDto> pessoas = new ArrayList<>();
		page.getContent().stream().filter(p -> p.isAtivo()).forEach(p -> {
			pessoas.add(toDto(p));
		});
		return pessoas;
	}

	public Pessoa toRec(PessoaSaveInput payload, Pessoa pessoa) {
		pessoa.setNome(payload.getNome());
		pessoa.setCpf(payload.getCpf());
		pessoa.setLogin(payload.getLogin());
		pessoa.setSenha(senhaCrip.encode(payload.getSenha()));
		pessoa.setData_cadastro(LocalDate.now());
		
		pessoa.setData_nascimento(payload.getDataNascimento());
		
		if (payload.getTelefone() != null) {
			pessoa.setTelefone(payload.getTelefone());
		}

		if (payload.getTipoUsuario() == null) {
			pessoa.setTipoPessoa(EnumTipoPessoa.NORMAL);
		} else {
			pessoa.setTipoPessoa(payload.getTipoUsuario());
		}

		return pessoa;
	}


	public PessoaCampOutput toCamp(Pessoa payload) {
		PessoaCampOutput pessoa = new PessoaCampOutput();

		pessoa.setNome(payload.getNome());
		pessoa.setCpf(payload.getCpf());
		pessoa.setDataNascimento(payload.getData_nascimento());
		pessoa.setSexo(payload.getSexo());
		pessoa.setTelefone(payload.getTelefone());

		return pessoa;
	}
}
