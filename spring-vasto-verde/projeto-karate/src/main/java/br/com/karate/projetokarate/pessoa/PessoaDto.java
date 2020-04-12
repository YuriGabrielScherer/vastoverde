package br.com.karate.projetokarate.pessoa;

import java.util.Calendar;

import br.com.karate.projetokarate.atleta.Atleta;
import br.com.karate.projetokarate.enums.TipoPessoa;

public class PessoaDto {

	private String nome;

	private String email;

	private String telefone;

	private Calendar dataNascimento;

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

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
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
