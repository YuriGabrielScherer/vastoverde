package br.com.karate.projetokarate.data.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.karate.projetokarate.data.generic.CrudRepository;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

	Optional<Pessoa> findByEmail(String email);

	Optional<Pessoa> findByCpf(String cpf);

	Optional<Pessoa> findByLogin(String login);

	boolean existsByCpf(String cpf);

	boolean existsByEmail(String email);
	
	boolean existsByCodigo(int codigo);
	
	Page<Pessoa> findAllByAtivoTrue(Pageable pageable);
	
	List<Pessoa> findAllByAtivoTrueAndFaixaIdIsNull();
	
}
