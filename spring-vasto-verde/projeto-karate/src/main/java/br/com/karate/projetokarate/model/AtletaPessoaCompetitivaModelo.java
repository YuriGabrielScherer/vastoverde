package br.com.karate.projetokarate.model;

public class AtletaPessoaCompetitivaModelo {

    private int idAtleta, idGrau, idPessoaCompetitiva, idPessoa, federacao, condeferacao;
    private String nomeResponsavel, telefoneResponsavel, dataInicio;

    public int getIdAtleta() {
        return idAtleta;
    }

    public void setIdAtleta(int idAtleta) {
        this.idAtleta = idAtleta;
    }

    public int getIdGrau() {
        return idGrau;
    }

    public void setIdGrau(int idGrau) {
        this.idGrau = idGrau;
    }

    public int getIdPessoaCompetitiva() {
        return idPessoaCompetitiva;
    }

    public void setIdPessoaCompetitiva(int idPessoaCompetitiva) {
        this.idPessoaCompetitiva = idPessoaCompetitiva;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getFederacao() {
        return federacao;
    }

    public void setFederacao(int federacao) {
        this.federacao = federacao;
    }

    public int getCondeferacao() {
        return condeferacao;
    }

    public void setCondeferacao(int condeferacao) {
        this.condeferacao = condeferacao;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

}