package br.com.karate.projetokarate.model.atleta;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AtletaSaveInput {

	@NotNull
	@Length(max = 100)
	private String nomeResponsavel;

	@NotNull
	@Length(max = 14)
	private String telefoneResponsavel;

	@NotNull
	@Length(max = 11)
	private String cpfResponsavel;

	@NotNull
	@Length(max = 30)
	private String endereco;

	private int idAssociacao;

	@NotNull
	@Length(max = 11)
	private String cpfPessoa;

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

	public int getIdAssociacao() {
		return idAssociacao;
	}

	public void setIdAssociacao(int idAssociacao) {
		this.idAssociacao = idAssociacao;
	}

	public String getCpfPessoa() {
		return cpfPessoa;
	}

	public void setCpfPessoa(String cpfPessoa) {
		this.cpfPessoa = cpfPessoa;
	}

}
