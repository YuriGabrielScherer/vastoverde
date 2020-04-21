package br.com.karate.projetokarate.data.atleta;

import java.util.List;

import br.com.karate.projetokarate.data.associacao.Associacao;
import br.com.karate.projetokarate.generic.CrudRepository;

public interface AtletaRepository extends CrudRepository<Atleta, Integer> {

	List<Atleta> findAllByAssociacao(Associacao associacao);
	
}
