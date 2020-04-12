package br.com.karate.projetokarate.atleta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AtletaRepository extends JpaRepository<Atleta, Integer> {
	
	List<Atleta> findAllByIdAssociacao();

}