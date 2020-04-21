package br.com.karate.projetokarate.model.atleta;

import br.com.karate.projetokarate.data.associacao.Associacao;
import br.com.karate.projetokarate.model.pessoa.PessoaCampOutput;

public class AtletaCampOutput {

	private String nomeResponsavel;

	private String telefoneResponsavel;

	private String cpfResponsavel;

	private PessoaCampOutput pessoa;

	private Associacao associacao;

	private boolean ativo;

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

	public PessoaCampOutput getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaCampOutput pessoa) {
		this.pessoa = pessoa;
	}

	public Associacao getAssociacao() {
		return associacao;
	}

	public void setAssociacao(Associacao associacao) {
		this.associacao = associacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
