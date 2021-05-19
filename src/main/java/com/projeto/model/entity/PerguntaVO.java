package com.projeto.model.entity;

import java.io.Serializable;

public class PerguntaVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Long idPergunta;
	private String texto;
	private String categoria;
	
	private AlternativaVO questao;
	
	
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

	public AlternativaVO getQuestao() {
		return questao;
	}

	public void setQuestao(AlternativaVO questao) {
		this.questao = questao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPergunta == null) ? 0 : idPergunta.hashCode());
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
		if (idPergunta == null) {
			if (other.idPergunta != null)
				return false;
		} else if (!idPergunta.equals(other.idPergunta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "idPergunta=" + idPergunta
				+ "\ntexto=" + texto
				+ "\ncategoria=" + categoria;
	}	
    
}
