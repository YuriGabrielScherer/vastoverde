package br.com.karate.projetokarate.atleta;

import java.util.List;

import br.com.karate.projetokarate.campeonato.Campeonato;

public class AtletaSaveInput {

	private String nomeResponsavel;

	private String telefoneResponsavel;

	private String cpfResponsavel;
	
	private String endereco;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	private List<Campeonato> campeonatos;

	private int idAssociacao;

	private String cpfPessoa;

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

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
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
