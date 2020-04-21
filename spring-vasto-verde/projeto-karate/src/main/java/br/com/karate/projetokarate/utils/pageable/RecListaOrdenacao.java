package br.com.karate.projetokarate.utils.pageable;

public class RecListaOrdenacao {

	public String campo;
	public EnumTipoOrdenacao ordenacao; // Asc ou Desc

	public RecListaOrdenacao(String campo, EnumTipoOrdenacao ordenacao) {
		this.campo = campo;
		this.ordenacao = ordenacao;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public EnumTipoOrdenacao getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(EnumTipoOrdenacao ordenacao) {
		this.ordenacao = ordenacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((ordenacao == null) ? 0 : ordenacao.hashCode());
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
		RecListaOrdenacao other = (RecListaOrdenacao) obj;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (ordenacao != other.ordenacao)
			return false;
		return true;
	}

}
