package br.com.karate.projetokarate.model.campeonato;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

public class CampeonatoSaveInput {
	
	@NotNull
	private String titulo;
	@NotNull
	private String descricao;
	@NotNull
	private Date dataInicio;
	private Date dataFim;
	@NotNull
	private String endereco;
	@NotNull
	private List<Integer> idAtletas;
	
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
	public List<Integer> getIdAtletas() {
		return idAtletas;
	}
	public void setIdAtletas(List<Integer> IdAtletas) {
		this.idAtletas = IdAtletas;
	}
	

}
