package br.com.karate.projetokarate.model.pessoa;

import java.util.List;

import br.com.karate.projetokarate.utils.pageable.RecPaginacaoRetorno;

public class PesquisarPessoasOutput {

	private List<PessoaDto> pessoas;
	private RecPaginacaoRetorno paginacao;

	public PesquisarPessoasOutput() {}

	public PesquisarPessoasOutput(List<PessoaDto> pessoas, RecPaginacaoRetorno paginacao) {
		this.pessoas = pessoas;
		this.paginacao = paginacao;
	}
	
	public List<PessoaDto> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaDto> pessoas) {
		this.pessoas = pessoas;
	}

	public RecPaginacaoRetorno getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(RecPaginacaoRetorno paginacao) {
		this.paginacao = paginacao;
	}

}
