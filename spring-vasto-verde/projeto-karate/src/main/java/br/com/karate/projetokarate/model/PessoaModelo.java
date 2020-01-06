package br.com.karate.projetokarate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoas")
public class PessoaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "idPessoa")
    private int id;

    @Column(nullable = false, name = "nome")
    private String nomePessoa;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "senha")
    private String senhaPessoa;

    @Column(nullable = false, name = "telefone")
    private String telefonePessoa;

    @Column(nullable = false, name = "dataNascimento")
    private String dataNascimentoPessoa;

    @Column(nullable = false, name = "cpf")
    private String cpfPessoa;

    @Column(nullable = false, name = "tipoUsuario")
    private int tipoUsuarioPessoa;

    @Column(nullable = false, name = "sexo")
    private char sexoPessoa;

    @Column(nullable = false, name = "idAssociacao")

    // Getters and Setters

    public int getidPessoa() {
        return id;
    }

    public void setidPessoa(int idPessoa) {
        this.id = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getEmailPessoa() {
        return email;
    }

    public void setEmailPessoa(String emailPessoa) {
        this.email = emailPessoa;
    }

    public String getSenhaPessoa() {
        return senhaPessoa;
    }

    public void setSenhaPessoa(String senhaPessoa) {
        this.senhaPessoa = senhaPessoa;
    }

    public String getTelefonePessoa() {
        return telefonePessoa;
    }

    public void setTelefonePessoa(String telefonePessoa) {
        this.telefonePessoa = telefonePessoa;
    }

    public String getDataNascimentoPessoa() {
        return dataNascimentoPessoa;
    }

    public void setDataNascimentoPessoa(String dataNascimentoPessoa) {
        this.dataNascimentoPessoa = dataNascimentoPessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public int getTipoUsuarioPessoa() {
        return tipoUsuarioPessoa;
    }

    public void setTipoUsuarioPessoa(int tipoUsuarioPessoa) {
        this.tipoUsuarioPessoa = tipoUsuarioPessoa;
    }

    public char getSexoPessoa() {
        return sexoPessoa;
    }

    public void setSexoPessoa(String sexoPessoa) {
        System.out.println(sexoPessoa.toCharArray()[0]);
        this.sexoPessoa = sexoPessoa.toCharArray()[0];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSexoPessoa(char sexoPessoa) {
        this.sexoPessoa = sexoPessoa;
    }

}