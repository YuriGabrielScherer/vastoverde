package br.com.karate.projetokarate.data.campeonato;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.karate.projetokarate.data.generic.Auditable;
import br.com.karate.projetokarate.data.pessoa.Pessoa;

@Entity
@Table(name = "campeonatos")
public class Campeonato extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "id", unique = true)
	private int id;

	@NotNull
	@Column(unique = true, length = 30)
	private String titulo;

	@NotNull
	@Column(length = 200)
	private String descricao;

	@NotNull
	@Column(name = "data_inicio")
	private LocalDateTime dataInicio;

	@Column(name = "data_fim")
	private LocalDateTime dataFim;

	@NotNull
	@Column(name = "endereco", length = 50)
	private String endereco;

	@ManyToMany
	@JoinTable(name = "campeonatos_pessoas", // Nome da Table gerada para compor o ManyToMany
			joinColumns = @JoinColumn(name = "id_campeonato"), // Nome do campo da Tabela que estou que vai compor a
																// chave estrangeira para a tabela gerada
			inverseJoinColumns = @JoinColumn(name = "id_pessoa")) // A PK do "Inverso" do relacionamento
	private List<Pessoa> pessoas;

	@NotNull
	@ManyToMany(targetEntity = Pessoa.class)
	@JoinTable(name = "arbitros_pessoas",
				joinColumns = @JoinColumn(name = "id_campeonato"),
				inverseJoinColumns = @JoinColumn(name = "id_pessoa"))
	private List<Pessoa> arbitros;

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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
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
