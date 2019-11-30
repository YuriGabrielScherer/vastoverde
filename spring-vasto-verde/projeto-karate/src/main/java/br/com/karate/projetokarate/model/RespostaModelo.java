package br.com.karate.projetokarate.model;

public class RespostaModelo {

    // Atributo
    private String mensagem;
    private String titulo;

    // Construtores
    public RespostaModelo() {
    }

    public RespostaModelo(String mensagem) {
        this.mensagem = mensagem;
    }

    public RespostaModelo(String mensagem, String titulo) {
        this.mensagem = mensagem;
        this.titulo = titulo;
    }

    // Set e Get
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
