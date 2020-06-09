package br.com.karate.projetokarate.security.permission;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.karate.projetokarate.data.pessoa.Pessoa;


@Entity
@Table(name = "roles")
public class Permissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int id;
	
	@Column()
	private String role;
	
	@ManyToMany(mappedBy = "permissao", fetch = FetchType.LAZY)
	private List<Pessoa> pessoa;
	
	public Permissao() {}
	
	public Permissao(String nome) {
		this.role = nome;
	}
}

