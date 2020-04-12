package br.com.karate.projetokarate.pessoa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Optional<Pessoa> findByEmail(String email); 
	Optional<Pessoa> findByCpf(String cpf);
	
	boolean existsByCpf(String cpf);
	boolean existsByEmail(String email);
}