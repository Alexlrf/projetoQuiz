package com.projeto.model.entity;

import java.util.List;

public class PerguntaVO  {	
	
	private int idPergunta;
	private int idUsuario;
	private String textoPergunta;
	private CategoriaVO categoria;
	private List<AlternativaVO> listaAlternativas;
	
	public PerguntaVO(int idPergunta, int idUsuario, String textoPergunta, CategoriaVO categoria, List<AlternativaVO> listaAlternativas) {
		super();
		this.idPergunta = idPergunta;
		this.idUsuario = idUsuario;
		this.textoPergunta = textoPergunta;
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
		
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTextoPergunta() {
		return textoPergunta;
	}

	public void setTextoPergunta(String textoPergunta) {
		this.textoPergunta = textoPergunta;
	}

	public CategoriaVO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaVO categoria) {
		this.categoria = categoria;
	}

	public List<AlternativaVO> getListaAlternativas() {
		return listaAlternativas;
	}

	public void setListaAlternativas(List<AlternativaVO> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}	    
}
