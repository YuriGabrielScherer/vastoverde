package br.com.karate.projetokarate.atletas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.karate.projetokarate.data.pessoa.Pessoa;

@Entity
public class Atletas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@OneToOne
	@NotNull
	private Pessoa pessoaAtleta;
	
	@NotNull
	private Graduacao graduacao;
	
	//Futuramente implementar federação,confederação e associação 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoaAtleta() {
		return pessoaAtleta;
	}

	public void setPessoaAtleta(Pessoa pessoaAtleta) {
		this.pessoaAtleta = pessoaAtleta;
	}

	public Graduacao getGraduacao() {
		return graduacao;
	}

	public void setGraduacao(Graduacao graduacao) {
		this.graduacao = graduacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Atletas other = (Atletas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
