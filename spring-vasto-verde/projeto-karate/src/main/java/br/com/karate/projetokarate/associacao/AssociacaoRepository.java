package br.com.karate.projetokarate.associacao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociacaoRepository extends JpaRepository<Associacao, Integer> {

	boolean existsByNome(String nome);
}
