package br.com.karate.projetokarate.atleta;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.karate.projetokarate.associacao.Associacao;
import br.com.karate.projetokarate.campeonato.Campeonato;
import br.com.karate.projetokarate.pessoa.Pessoa;

@Entity
@Table(name = "atletas")
public class Atleta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idAtleta", unique = true)
	private int id;

	@NotNull
	@Column(name = "nomeResponsavel", length = 100)
	private String nomeResponsavel;

	@NotNull
	@Column(name = "telefoneResponsavel", length = 14)
	private String telefoneResponsavel;

	@NotNull
	@Column( length = 30)
	private String endereco;

	@CPF
	@NotNull
	@Column(name = "cpfResponsavel", length = 11, unique = true)
	private String cpfResponsavel;

	@NotNull
	@Column(name = "ativo")
	private boolean ativo;

	@NotNull
	@MapsId
	@OneToOne(optional = false)
	@JoinColumn(name = "idPessoa", unique = true, updatable = false)
	private Pessoa pessoa;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Campeonato> campeonatos;

	@NotNull
	@JoinColumn(name = "associacao_id")
	@ManyToOne()
	private Associacao associacao;

	// Getters and Setters
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getTelefoneResponsavel() {
		return telefoneResponsavel;
	}

	public void setTelefoneResponsavel(String telefoneResponsavel) {
		this.telefoneResponsavel = telefoneResponsavel;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Associacao getAssociacao() {
		return associacao;
	}

	public void setAssociacao(Associacao associacao) {
		this.associacao = associacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpfResponsavel == null) ? 0 : cpfResponsavel.hashCode());
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
		Atleta other = (Atleta) obj;
		if (cpfResponsavel == null) {
			if (other.cpfResponsavel != null)
				return false;
		} else if (!cpfResponsavel.equals(other.cpfResponsavel))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}