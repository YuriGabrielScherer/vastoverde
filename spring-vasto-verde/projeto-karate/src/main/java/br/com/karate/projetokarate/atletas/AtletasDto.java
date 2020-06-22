package br.com.karate.projetokarate.atletas;

import br.com.karate.projetokarate.data.pessoa.Pessoa;

public class AtletasDto {

	private Pessoa pessoaAtleta;	

	private Graduacao graduacao;
	
	public Pessoa getPessoaAtleta() {
		return pessoaAtleta;
	}

	public void setPessoaAtleta(Pessoa pessoaAtleta) {
		this.pessoaAtleta = pessoaAtleta;
	}

	public Graduacao getGraduacao() {
		return graduacao;
	}

	public void setGraduacao(Graduacao graduacao) {
		this.graduacao = graduacao;
	}
		
}
