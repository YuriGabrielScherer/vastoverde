package br.com.karate.projetokarate.data.associacao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.karate.projetokarate.data.generic.Auditable;
import br.com.karate.projetokarate.data.pessoa.Pessoa;

@Entity
@Table(name = "associacoes")
public class Associacao extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(length = 70)
	private String nome;

	@NotNull
	@Column(unique = true)
	private int codigo;

	@NotNull
	@Column(length = 35)
	private String cidade;

	@Column(length = 50)
	private String endereco;

	@OneToMany(targetEntity = Pessoa.class, cascade = CascadeType.ALL)
	private List<Pessoa> pessoas;

	@Column(length = 14)
	private String telefone;

	@OneToOne()
	private Pessoa responsavel;

	@NotNull
	private boolean ativo = true;

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
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

	public void setAtletas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Associacao other = (Associacao) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		return true;
	}

}
