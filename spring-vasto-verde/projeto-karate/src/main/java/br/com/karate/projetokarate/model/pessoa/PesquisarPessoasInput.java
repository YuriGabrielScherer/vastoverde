package br.com.karate.projetokarate.model.pessoa;

import br.com.karate.projetokarate.utils.pageable.RecPaginacao;

public class PesquisarPessoasInput {

	private String filtro;
	private RecPaginacao paginacao;

	public PesquisarPessoasInput() {
	}

	public PesquisarPessoasInput(String filtro, RecPaginacao paginacao) {
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
