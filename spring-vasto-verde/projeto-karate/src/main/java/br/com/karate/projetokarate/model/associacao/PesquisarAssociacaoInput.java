package br.com.karate.projetokarate.model.associacao;

import br.com.karate.projetokarate.utils.pageable.RecPaginacao;

public class PesquisarAssociacaoInput {

	private String filtro;
	private RecPaginacao paginacao;

	public PesquisarAssociacaoInput() {
	}

	public PesquisarAssociacaoInput(String filtro, RecPaginacao paginacao) {
		super();
		this.filtro = filtro;
		this.paginacao = paginacao;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public RecPaginacao getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(RecPaginacao paginacao) {
		this.paginacao = paginacao;
	}

}
