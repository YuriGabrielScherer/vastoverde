package br.com.karate.projetokarate.model;

public class AtletaPessoaCompetitivaModelo {

    private int idAtleta, idGrau, idPessoaCompetitiva, idPessoa, federacao, confederacao;
    private String nomeResponsavel, telefoneResponsavel, dataInicio, cpfResponsavel;

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

    public int getConfederacao() {
        return confederacao;
    }

    public void setConfederacao(int confederacao) {
        this.confederacao = confederacao;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

}