package br.com.karate.projetokarate.data.campeonato;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.karate.projetokarate.data.atleta.Atleta;
import br.com.karate.projetokarate.generic.Auditable;

@Entity
@Table(name = "campeonatos")
public class Campeonato extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idCampeonato", unique = true)
	private int id;

	@NotNull
	@Column(unique = true, length = 30)
	private String titulo;

	@NotNull
	@Column(length = 200)
	private String descricao;

	@NotNull
	@Column(name = "dataInicio")
	private LocalDateTime dataInicio;

	@Column(name = "dataFim")
	private LocalDateTime dataFim;

	@NotNull
	@Column(name = "endereco", length = 50)
	private String endereco;

	@NotNull
	@ManyToMany(targetEntity = Atleta.class)
	private List<Atleta> atletas;

	// TODO colocar arbitros

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Atleta> getAtletas() {
		return atletas;
	}

	public void setAtletas(List<Atleta> atletas) {
		this.atletas = atletas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Campeonato other = (Campeonato) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
