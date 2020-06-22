package br.com.karate.projetokarate.model.campeonato;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class CampeonatoDto {

	private String titulo;
	private String descricao;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private String endereco;
	//private List<AtletaCampOutput> atletas;
	
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
	public LocalDateTime getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDateTime getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/*
	  public List<AtletaCampOutput> getAtletas() {
	 
		return atletas;
	}
	public void setAtletas(List<AtletaCampOutput> atletas) {
		this.atletas = atletas;
	}
	*/
}
