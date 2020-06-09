package br.com.karate.projetokarate.model.associacao;

import br.com.karate.projetokarate.utils.pageable.RecPaginacao;

public class PesquisarAssociacaoInput {

	private String nome;
	private String cidade;
	private RecPaginacao paginacao;

	public PesquisarAssociacaoInput() {
	}

	public PesquisarAssociacaoInput(String nome, RecPaginacao paginacao) {
		super();
		this.nome = nome;
		this.paginacao = paginacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public RecPaginacao getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(RecPaginacao paginacao) {
		this.paginacao = paginacao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	

}
