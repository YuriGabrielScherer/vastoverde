package br.com.karate.projetokarate.atletas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AtletaRepository extends JpaRepository<AtletasDto, Long> {

}
