package br.com.karate.projetokarate.generic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CrudRepository<T, ID> extends JpaRepository<T, ID> {

	<S extends T> S save(S entity);

	Optional<T> findById(ID primaryKey);

	List<T> findAll();

	long count();

	void delete(T entity);

	boolean existsById(ID primaryKey);

	List<T> findAll(Sort sort);

	Page<T> findAll(Pageable pageable);
}
