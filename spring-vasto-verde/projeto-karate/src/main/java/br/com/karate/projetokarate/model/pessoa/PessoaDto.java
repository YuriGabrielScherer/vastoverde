package br.com.karate.projetokarate.model.pessoa;

import java.time.LocalDateTime;
import java.util.Calendar;

import br.com.karate.projetokarate.data.atleta.Atleta;
import br.com.karate.projetokarate.data.pessoa.TipoPessoa;

public class PessoaDto {

	private String nome;

	private String email;

	private String telefone;

	private String dataNascimento;

	private String cpf;

	private TipoPessoa tipoUsuario;

	private char sexo;

	private Atleta atleta;
	
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoPessoa getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoPessoa tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Atleta getAtleta() {
		return atleta;
	}

	public void setAtleta(Atleta atleta) {
		this.atleta = atleta;
	}

}
