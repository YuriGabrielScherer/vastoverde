package br.com.karate.projetokarate.model.campeonato;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CampeonatoSaveInput {

	@NotNull
	@Length(max = 30)
	private String titulo;

	@NotNull
	@Length(max = 200)
	private String descricao;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFim;

	@NotNull
	@Length(max = 50)
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

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
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
