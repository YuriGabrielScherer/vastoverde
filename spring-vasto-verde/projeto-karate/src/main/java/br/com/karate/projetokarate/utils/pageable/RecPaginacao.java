package br.com.karate.projetokarate.utils.pageable;

import java.util.List;

/*
 * "paginacao": {
 * 		"pagina":1,
 * 		"numeroRegistrosPagina": 10,
 * 		"listaOrdenacao": [
 * 			{
 * 				"campo":"codigo",
 * 				"ordenacao": "Asc"
 * 			}
 * 		]
 * }
 * */

public class RecPaginacao {

	private Long pagina;
	private Long numeroRegistrosPagina;
	private List<RecListaOrdenacao> listaOrdenacao;

	public RecPaginacao() {
	}

	public RecPaginacao(Long pagina, Long numeroRegistrosPagina, List<RecListaOrdenacao> listaOrdenacao) {
		this.pagina = pagina;
		this.numeroRegistrosPagina = numeroRegistrosPagina;
		this.listaOrdenacao = listaOrdenacao;
	}

	public int getPagina() {
		return pagina.intValue();
	}

	public void setPagina(Long pagina) {
		this.pagina = pagina;
	}

	public int getNumeroRegistrosPagina() {
		return numeroRegistrosPagina.intValue();
	}

	public void setNumeroRegistrosPagina(Long numeroRegistrosPagina) {
		this.numeroRegistrosPagina = numeroRegistrosPagina;
	}

	public List<RecListaOrdenacao> getListaOrdenacao() {
		return listaOrdenacao;
	}

	public void setListaOrdenacao(List<RecListaOrdenacao> listaOrdenacao) {
		this.listaOrdenacao = listaOrdenacao;
	}

}
