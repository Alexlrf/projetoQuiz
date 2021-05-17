package com.projeto.model.entity;

public class PerguntaVO {
	
	private Long idPergunta;
	private String texto;
	private String categoria;
	
	public PerguntaVO(Long idPergunta, String texto, String categoria) {
		super();
		this.idPergunta = idPergunta;
		this.texto = texto;
		this.categoria = categoria;
	}

	public PerguntaVO() {
		super();
	}

	public Long getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(Long idPergunta) {
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

}
