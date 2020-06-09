package br.com.karate.projetokarate.model.pessoa;

import java.time.LocalDate;

import br.com.karate.projetokarate.data.pessoa.EnumTipoPessoa;
import br.com.karate.projetokarate.model.atleta.AtletaDto;

public class PessoaDto {

	private String nome;

	private String email;
	
	private int codigo;

	private String telefone;

	private LocalDate dataNascimento;
	
	private String cidade;

	private String cpf;

	private EnumTipoPessoa tipoUsuario;

	private AtletaDto atleta;

	private char sexo;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public EnumTipoPessoa getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(EnumTipoPessoa tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public void setAtleta(AtletaDto atleta) {
		this.atleta = atleta;
	}

	public AtletaDto getAtleta() {
		return this.atleta;
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
	
	
}
