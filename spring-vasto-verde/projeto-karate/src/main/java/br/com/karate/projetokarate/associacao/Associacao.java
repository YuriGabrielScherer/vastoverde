package br.com.karate.projetokarate.associacao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import br.com.karate.projetokarate.atleta.Atleta;

@Entity
@Table(name="associacoes")
public class Associacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true, name="idAssociacao")
	private int id;
	
	@Column(nullable = false, unique= true)
	private String nome;
	
	@Column(nullable = true)
	private String cidade;
	
	@Column(nullable = true)
	private String endereco;
	
	@OneToMany(targetEntity = Atleta.class, mappedBy="associacao", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Column(nullable = true)
	private List<Atleta> atletas;

}
