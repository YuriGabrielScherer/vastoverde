package br.com.karate.projetokarate.pessoa;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.karate.projetokarate.atleta.Atleta;
import br.com.karate.projetokarate.enums.TipoPessoa;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column( name = "idPessoa", unique = true)
    private int id;

    @NotNull
    @Column(length = 100)
    private String nome;

    @Column( length = 50, unique = true)
    private String email;

    @NotNull
    @Column( length = 100)
    private String senha;

    @Column( length = 14)
    private String telefone;

    @NotNull
    @Column()
    private Calendar  dataNascimento;

    @CPF
    @NotNull
    @Column( length = 11, unique = true)
    private String cpf;

    @NotNull
    @Column( name = "tipoUsuario")
    private TipoPessoa tipoUsuario;

    @NotNull
    @Column( name = "sexo")
    private char sexo;
    
    @NotNull
    @Column()
    private boolean ativo;
    
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Atleta getAtleta() {
		return atleta;
	}

	public void setAtleta(Atleta atleta) {
		this.atleta = atleta;
	}

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Atleta atleta;


    public int getid() {
        return id;
    }

    public void setid(int idPessoa) {
        this.id = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomePessoa) {
        this.nome = nomePessoa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailPessoa) {
        this.email = emailPessoa;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Calendar  getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar  dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

	public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpfPessoa) {
        this.cpf = cpfPessoa;
    }

    public TipoPessoa getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoPessoa tipoUsuarioPessoa) {
        this.tipoUsuario = tipoUsuarioPessoa;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexoPessoa) {
        this.sexo = sexoPessoa;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atleta == null) ? 0 : atleta.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (atleta == null) {
			if (other.atleta != null)
				return false;
		} else if (!atleta.equals(other.atleta))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id != other.id)
			return false;
		return true;
	}


}