package br.com.karate.projetokarate.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.karate.projetokarate.model.VwPessoaGrauModelo;

public interface VwPessoaGrauRepository extends Repository<VwPessoaGrauModelo, Integer> {

    List<VwPessoaGrauModelo> findAll();
}