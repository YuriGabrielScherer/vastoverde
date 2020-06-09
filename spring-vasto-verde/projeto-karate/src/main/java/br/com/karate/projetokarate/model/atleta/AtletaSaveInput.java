package br.com.karate.projetokarate.model.atleta;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AtletaSaveInput {

	@NotNull
	@Length(max = 100)
	private String nomeResponsavel;

	@NotNull
	@Length(max = 11)
	private String cpfResponsavel;
	
	@Length(max = 14)
	private String telefoneResponsavel;

	@Length(max = 30)
	private String endereco;

	@NotNull
	private Grau grau;
	
	@NotNull
	@Length(max = 11)
	private String cpfPessoa;
	
	@Max(value = 99999)
	private int confederacao;
	
	@Max(value = 99999)
	private int federacao;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getTelefoneResponsavel() {
		return telefoneResponsavel;
	}

	public void setTelefoneResponsavel(String telefoneResponsavel) {
		this.telefoneResponsavel = telefoneResponsavel;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getCpfPessoa() {
		return cpfPessoa;
	}

	public void setCpfPessoa(String cpfPessoa) {
		this.cpfPessoa = cpfPessoa;
	}
	
	public Grau getGrau() {
		return grau;
	}

	public void setGrau(Grau grau) {
		this.grau = grau;
	}

	public int getConfederacao() {
		return confederacao;
	}

	public void setConfederacao(int confederacao) {
		this.confederacao = confederacao;
	}

	public int getFederacao() {
		return federacao;
	}

	public void setFederacao(int federacao) {
		this.federacao = federacao;
	}

	
}
