package br.com.karate.projetokarate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "atletas")
public class AtletaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private int id;

    @Column(nullable = false, name = "nomeResponsavel")
    private String nomeResponsavel;

    @Column(nullable = false, name = "telefoneResponsavel")
    private String telefoneResponsavel;

    @Column(nullable = false, name = "idPessoaCompetitiva")
    private int idPessoaCompetitiva;


    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getNumeroResponsavel() {
        return telefoneResponsavel;
    }

    public void setNumeroResponsavel(String numeroResponsavel) {
        this.telefoneResponsavel = numeroResponsavel;
    }

    public int getIdPessoaCompetitiva() {
        return idPessoaCompetitiva;
    }

    public void setIdPessoaCompetitiva(int idPessoaCompetitiva) {
        this.idPessoaCompetitiva = idPessoaCompetitiva;
    }

    
}