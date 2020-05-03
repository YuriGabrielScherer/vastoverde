package br.com.karate.projetokarate.model.associacao;

import java.util.List;

import br.com.karate.projetokarate.utils.pageable.RecPaginacaoRetorno;

public class PesquisarAssociacaoOutput {

	private List<AssociacaoDto> associacoes;
	private RecPaginacaoRetorno paginacao;

	public PesquisarAssociacaoOutput() {
	}

	public PesquisarAssociacaoOutput(List<AssociacaoDto> associacoes, RecPaginacaoRetorno paginacao) {
		this.associacoes = associacoes;
		this.paginacao = paginacao;
	}

	public List<AssociacaoDto> getAssociacoes() {
		return associacoes;
	}

	public void setAssociacoes(List<AssociacaoDto> associacoes) {
		this.associacoes = associacoes;
	}

	public RecPaginacaoRetorno getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(RecPaginacaoRetorno paginacao) {
		this.paginacao = paginacao;
	}

}
