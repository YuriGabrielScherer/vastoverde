package br.com.karate.projetokarate.utils.pageable;

import java.util.List;



public class Pageable {

	private Long pagina;
	private Long registrosPagina;
	private List<RecListaOrdenacao> ordenacao;
	
	public Pageable(long pagina, long registrosPagina, List<RecListaOrdenacao> ordenacao) {
		this.pagina = pagina;
		this.registrosPagina = registrosPagina;
		this.ordenacao = ordenacao;
	}
	
	public static Pageable of(Long pagina, Long quantidadeRegistro, List<RecListaOrdenacao> ordenacao) {
		return new Pageable(pagina, quantidadeRegistro, ordenacao);
	}

	public int getPagina() {
		return pagina.intValue();
	}

	public int getRegistrosPagina() {
		return registrosPagina.intValue();
	}

	public List<RecListaOrdenacao> getOrdenacao() {
		return ordenacao;
	}
	
	
	
}
