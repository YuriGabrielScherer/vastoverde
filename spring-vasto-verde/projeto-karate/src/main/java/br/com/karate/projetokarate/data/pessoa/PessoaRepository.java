package br.com.karate.projetokarate.data.pessoa;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.karate.projetokarate.generic.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

	Optional<Pessoa> findByEmail(String email);

	Optional<Pessoa> findByCpf(String cpf);

	Optional<Pessoa> findByLogin(String login);

	boolean existsByCpf(String cpf);

	boolean existsByEmail(String email);
	
	Page<Pessoa> findAllByAtivoTrue(Pageable pageable);
	
}
