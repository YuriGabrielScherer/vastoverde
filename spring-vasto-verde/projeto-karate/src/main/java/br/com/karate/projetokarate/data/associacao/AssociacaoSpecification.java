package br.com.karate.projetokarate.data.associacao;

import org.springframework.data.jpa.domain.Specification;

public final class AssociacaoSpecification {
	
	public static Specification<Associacao> findAssociacaoByName(String name) {
		return (root, query, builder) -> builder.equal(root.get("nome"), name); 
	}

}
