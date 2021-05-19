package com.projeto.model.entity;

import java.util.List;

public class PerguntaVO  {	
	
	private int idPergunta;
	private String texto;
	private String categoria;
	private List<AlternativaVO> listaAlternativas;
	
	public PerguntaVO(int idPergunta, String texto, String categoria, List<AlternativaVO> listaAlternativas) {
		super();
		this.idPergunta = idPergunta;
		this.texto = texto;
		this.categoria = categoria;
		this.listaAlternativas = listaAlternativas;
	}

	public PerguntaVO() {
		super();
	}

	public int getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<AlternativaVO> getListaAlternativas() {
		return listaAlternativas;
	}

	public void setListaAlternativas(List<AlternativaVO> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}
	
	    
}
