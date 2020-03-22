package br.com.karate.projetokarate.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.karate.projetokarate.model.AtletaModelo;

public interface AtletaRepository extends Repository<AtletaModelo, Integer> {

    // Metodos CRUD
    void save(AtletaModelo atleta);

    List<AtletaModelo> findAll();

    AtletaModelo findById(int idAtleta);

    void delete(AtletaModelo atleta);

}