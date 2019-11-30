package br.com.karate.projetokarate.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.karate.projetokarate.model.PessoaModelo;

public interface PessoaRepository extends Repository<PessoaModelo, Integer> {

    // Metodos Crud
    void save(PessoaModelo pessoa);

    List<PessoaModelo> findAll();

    PessoaModelo findById(int idPessoa);

    PessoaModelo findByEmail(String email);

    void delete(PessoaModelo pessoa);

}