package br.com.karate.projetokarate.model.pessoa;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.karate.projetokarate.data.pessoa.TipoPessoa;

public class PessoaSaveInput {

	@NotNull
	private String nome;

	@NotNull
	@Column(length = 50)
	private String email;

	@NotNull
	private String login;

	@NotNull
	@Column(length = 30)
	private String senha;

	@Column(length = 14)
	private String telefone;

	@NotNull
	@Column(length = 10)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	@NotNull
	@Column(length = 11)
	private String cpf;

	@NotNull
	private char sexo;

	@NotNull
	private TipoPessoa tipoUsuario;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public TipoPessoa getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoPessoa tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
