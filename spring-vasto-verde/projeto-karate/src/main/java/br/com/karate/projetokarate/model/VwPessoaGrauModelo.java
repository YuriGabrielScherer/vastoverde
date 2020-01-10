package br.com.karate.projetokarate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_pessoa_grau")
public class VwPessoaGrauModelo {

    // Identificadores

    @Id
    @Column(name = "idAtleta")
    private int idAtleta;

    @Column(name = "nomePessoa")
    private String nomePessoa;

    @Column(name = "cpfPessoa")
    private String cpfPessoa;

    @Column(name = "dataNascimento")
    private String dataNascimento;

    @Column(name = "idGrau")
    private int idGrau;

    // Get Methods

    public int getIdAtleta() {
        return idAtleta;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public int getIdGrau() {
        return idGrau;
    }

}