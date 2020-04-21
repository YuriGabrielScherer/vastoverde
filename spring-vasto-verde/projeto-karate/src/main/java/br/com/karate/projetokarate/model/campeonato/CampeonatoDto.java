package br.com.karate.projetokarate.model.campeonato;

import java.util.Date;
import java.util.List;

import br.com.karate.projetokarate.model.atleta.AtletaCampOutput;
import br.com.karate.projetokarate.model.atleta.AtletaDto;

public class CampeonatoDto {

	private String titulo;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;
	private String endereco;
	private List<AtletaCampOutput> atletas;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public List<AtletaCampOutput> getAtletas() {
		return atletas;
	}
	public void setAtletas(List<AtletaCampOutput> atletas) {
		this.atletas = atletas;
	}

}
