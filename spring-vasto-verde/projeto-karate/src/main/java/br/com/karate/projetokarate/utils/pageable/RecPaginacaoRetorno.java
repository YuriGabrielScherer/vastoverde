package br.com.karate.projetokarate.utils.pageable;

public class RecPaginacaoRetorno {

	public Long pagina;
	public Long numeroRegistroPagina;
	public Long registrosEncontrados;
	public Long totalPaginas;

	public RecPaginacaoRetorno() {
	}

	public RecPaginacaoRetorno(int pagina, int numeroRegistroPagina, Long registrosEncontrados, int totalPaginas) {
		this.pagina = Long.valueOf(pagina);
		this.numeroRegistroPagina = Long.valueOf(numeroRegistroPagina);
		this.registrosEncontrados = registrosEncontrados;
		this.totalPaginas = Long.valueOf(totalPaginas);
	}

	public RecPaginacaoRetorno(Long pagina, Long numeroRegistroPagina, Long registrosEncontrados, Long totalPaginas) {
		this.pagina = pagina;
		this.numeroRegistroPagina = numeroRegistroPagina;
		this.registrosEncontrados = registrosEncontrados;
		this.totalPaginas = totalPaginas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroRegistroPagina == null) ? 0 : numeroRegistroPagina.hashCode());
		result = prime * result + ((pagina == null) ? 0 : pagina.hashCode());
		result = prime * result + ((registrosEncontrados == null) ? 0 : registrosEncontrados.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecPaginacaoRetorno other = (RecPaginacaoRetorno) obj;
		if (numeroRegistroPagina == null) {
			if (other.numeroRegistroPagina != null)
				return false;
		} else if (!numeroRegistroPagina.equals(other.numeroRegistroPagina))
			return false;
		if (pagina == null) {
			if (other.pagina != null)
				return false;
		} else if (!pagina.equals(other.pagina))
			return false;
		if (registrosEncontrados == null) {
			if (other.registrosEncontrados != null)
				return false;
		} else if (!registrosEncontrados.equals(other.registrosEncontrados))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RecPaginacaoRetorno [pagina=" + pagina + ", numeroRegistroPagina=" + numeroRegistroPagina
				+ ", registrosEncontrados=" + registrosEncontrados + "]";
	}

}
