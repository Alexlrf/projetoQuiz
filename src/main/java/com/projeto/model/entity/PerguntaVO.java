package com.projeto.model.entity;

import java.io.Serializable;

public class PerguntaVO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private int idPergunta;
	private String texto;
	private String categoria;
	
	public PerguntaVO(int idPergunta, String texto, String categoria) {
		super();
		this.idPergunta = idPergunta;
		this.texto = texto;
		this.categoria = categoria;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPergunta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerguntaVO other = (PerguntaVO) obj;
		if (idPergunta != other.idPergunta)
			return false;
		return true;
	}
	 
    
}
