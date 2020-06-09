package br.com.karate.projetokarate.model.associacao;

import br.com.karate.projetokarate.data.pessoa.Pessoa;

public class AssociacaoDto {

	private String nome;
	private String endereco;
	private int codigo;
	private String cidade;
	private Pessoa responsavel;
	private String telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Pessoa getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
	
}