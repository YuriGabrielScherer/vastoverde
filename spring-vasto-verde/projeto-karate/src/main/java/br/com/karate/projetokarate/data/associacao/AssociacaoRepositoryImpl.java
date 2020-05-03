package br.com.karate.projetokarate.data.associacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssociacaoRepositoryImpl extends AssociacaoRepository {


	Page<Associacao> findAllByAtivoTrue(Pageable pageable);
	
}
