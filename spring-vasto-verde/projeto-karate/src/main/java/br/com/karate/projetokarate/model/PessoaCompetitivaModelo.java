package br.com.karate.projetokarate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoasCompetitivas")
public class PessoaCompetitivaModelo{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private int id;

    @Column(nullable = true, name = "federacao")
    private int federacao;

    @Column(nullable = true, name = "confederacao")
    private int confederacao;

    @Column(nullable = false, name = "dataInicio")
    private String dataInicio;

    @Column(nullable = false, name = "idPessoa")
    private int idPessoa;

    @Column(nullable = false, name = "idGrau")
    private int idGrau;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFederacao() {
        return federacao;
    }

    public void setFederacao(int federacao) {
        this.federacao = federacao;
    }

    public int getConfederacao() {
        return confederacao;
    }

    public void setConfederacao(int confederacao) {
        this.confederacao = confederacao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdGrau() {
        return idGrau;
    }

    public void setIdGrau(int idGrau) {
        this.idGrau = idGrau;
    }

}