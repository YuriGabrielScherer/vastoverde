package br.com.karate.projetokarate.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Pessoa findByEmail(String email); 
	Pessoa findByCpf(String cpf);
	
}