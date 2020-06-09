package br.com.karate.projetokarate.model.pessoa;

import java.time.LocalDate;

import br.com.karate.projetokarate.utils.pageable.RecPaginacao;

public class PesquisarPessoasInput {

	private String cpf;
	private int codigo;
	private String nome;
	private String email;
	private String cidade;
	private String telefone;
	private LocalDate dataNascimento; // Filtro por ano.

	private RecPaginacao paginacao;

	public PesquisarPessoasInput() {
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public RecPaginacao getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(RecPaginacao paginacao) {
		this.paginacao = paginacao;
	}

}
