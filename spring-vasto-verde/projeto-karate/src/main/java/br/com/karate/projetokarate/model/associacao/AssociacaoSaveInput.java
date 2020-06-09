package br.com.karate.projetokarate.model.associacao;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AssociacaoSaveInput {

	@NotNull
	@Length(max = 70)
	private String nome;

	@NotNull
	private int codigo;
	
	@NotNull
	@Length(max = 35)
	private String cidade;
	
	@Length(max = 50)
	private String endereco;
	
	@Length(max = 14)
	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	// TODO Colocar Responsavel aqui - NotNull, cod ou Cpf da Pessoa
	
	
	
	
}
