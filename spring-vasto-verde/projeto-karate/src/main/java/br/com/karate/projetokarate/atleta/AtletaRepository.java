package br.com.karate.projetokarate.atleta;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface AtletaRepository extends Repository<Atleta, Integer> {

    // Metodos CRUD
    void save(Atleta atleta);

    List<Atleta> findAll();

    Atleta findById(int idAtleta);

    void delete(Atleta atleta);

}