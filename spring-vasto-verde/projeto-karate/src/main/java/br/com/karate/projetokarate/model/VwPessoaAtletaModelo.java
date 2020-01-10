package br.com.karate.projetokarate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_pessoa_pessoacomp_atleta")
public class VwPessoaAtletaModelo {

    // Identificadores
    @Id
    @Column(name = "idPessoa", nullable = false)
    private int idPessoa;

    @Column(nullable = false, name = "idPessoaCompetitiva")
    private int idPessoaCompetitiva;

    @Column(nullable = false, name = "idAtleta")
    private int idAtleta;

    @Column(nullable = false, name = "idGrau")
    private int idGrau;

    // Pessoa
    @Column(nullable = false, name = "nomePessoa")
    private String nomePessoa;

    @Column(nullable = false, name = "cpfPessoa")
    private String cpfPessoa;

    @Column(name = "dataNascimento")
    private String dataNascimento;

    // Pessoa Competitiva
    @Column(nullable = true, name = "federacao")
    private int federacao;

    @Column(nullable = true, name = "confederacao")
    private int confederacao;

    @Column(nullable = false, name = "dataInicio")
    private String dataInicio;

    // Atleta
    @Column(nullable = false, name = "nomeResponsavel")
    private String nomeResponsavel;

    @Column(nullable = false, name = "telefoneResponsavel")
    private String telefoneResponsavel;

    @Column(nullable = false, name = "cpfResponsavel")
    private String cpfResponsavel;

    // Metodos GET

    public int getIdPessoa() {
        return idPessoa;
    }

    public int getIdPessoaCompetitiva() {
        return idPessoaCompetitiva;
    }

    public int getIdAtleta() {
        return idAtleta;
    }

    public int getIdGrau() {
        return idGrau;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public int getFederacao() {
        return federacao;
    }

    public int getConfederacao() {
        return confederacao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

}