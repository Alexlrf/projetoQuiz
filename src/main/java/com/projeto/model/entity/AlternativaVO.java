package com.projeto.model.entity;

public class AlternativaVO {
	
	private Long idAlternativa;
	private Long idPergunta;
	private String texto;
	private Boolean alternativaCorreta;
	
	public AlternativaVO(Long idAlternativa, Long idPergunta, String texto, Boolean alternativaCorreta) {
		super();
		this.idAlternativa = idAlternativa;
		this.idPergunta = idPergunta;
		this.texto = texto;
		this.alternativaCorreta = alternativaCorreta;
	}

	public AlternativaVO() {
		super();
	}

	public Long getIdAlternativa() {
		return idAlternativa;
	}

	public void setIdAlternativa(Long idAlternativa) {
		this.idAlternativa = idAlternativa;
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

	public Boolean getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(Boolean alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}
	
	
}
