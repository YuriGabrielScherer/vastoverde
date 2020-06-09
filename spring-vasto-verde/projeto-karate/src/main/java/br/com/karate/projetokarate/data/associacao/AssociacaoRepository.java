package br.com.karate.projetokarate.data.associacao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.karate.projetokarate.data.generic.CrudRepository;

public interface AssociacaoRepository extends CrudRepository<Associacao, Integer>, JpaSpecificationExecutor<Associacao> {

	boolean existsByNome(String nome);
	
	boolean existsByCodigo(int cod);
	
	
	Associacao findByCodigo(int cod);
	
	Associacao findByCodigoAndNome(int cod, String nome);

	Page<Associacao> findAllByAtivoTrue(Pageable pageable);
	Page<Associacao> findAllByAtivoTrueAndNomeIgnoreCaseContaining(Pageable pageable, String nome);
	Page<Associacao> findAllByAtivoTrueAndCidadeIgnoreCaseContaining(Pageable pageable, String cidade);
	Page<Associacao> findAllByAtivoTrueAndNomeIgnoreCaseContainingOrCidadeIgnoreCaseContaining(Pageable pageable, String nome, String cidade);


}

