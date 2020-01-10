package br.com.karate.projetokarate.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.karate.projetokarate.model.VwPessoaAtletaModelo;

public interface VwPessoaAtletaRepository extends Repository<VwPessoaAtletaModelo, Integer> {

    // Metodo apenas para retornar o que ha no banco;
    List<VwPessoaAtletaModelo> findAll();

}