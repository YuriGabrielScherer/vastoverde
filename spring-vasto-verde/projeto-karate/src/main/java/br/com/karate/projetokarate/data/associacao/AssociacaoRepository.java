package br.com.karate.projetokarate.data.associacao;

import br.com.karate.projetokarate.generic.CrudRepository;

public interface AssociacaoRepository extends CrudRepository<Associacao, Integer> {

	boolean existsByNome(String nome);
	
}
