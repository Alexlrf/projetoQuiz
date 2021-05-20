package com.projeto.model.entity;

public class AlternativaVO {
	
	private int idAlternativa;
	private int idPergunta;
	private String texto;
	private Boolean alternativaCorreta;
	
	public AlternativaVO(int idAlternativa, int idPergunta, String texto, Boolean alternativaCorreta) {
		super();
		this.idAlternativa = idAlternativa;
		this.idPergunta = idPergunta;
		this.texto = texto;
		this.alternativaCorreta = alternativaCorreta;
	}

	public AlternativaVO() {
		super();
	}

	public int getIdAlternativa() {
		return idAlternativa;
	}

	public void setIdAlternativa(int idAlternativa) {
		this.idAlternativa = idAlternativa;
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

	public Boolean getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(Boolean alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}
	
	
}
