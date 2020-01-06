package br.com.karate.projetokarate.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.karate.projetokarate.model.PessoaCompetitivaModelo;

public interface PessoaCompetitivaRepository extends Repository<PessoaCompetitivaModelo, Integer> {

    // Metodos CRUD
    void save(PessoaCompetitivaModelo PessoaCompetitiva);

    List<PessoaCompetitivaModelo> findAll();

    PessoaCompetitivaModelo findById(int id);

    void delete(PessoaCompetitivaModelo PessoaCompetitiva);

}