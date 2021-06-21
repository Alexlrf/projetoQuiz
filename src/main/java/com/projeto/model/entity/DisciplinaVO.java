package com.projeto.model.entity;

public class DisciplinaVO {
	
	private int idDisciplina;
	private String nomeDisciplina;
	
	public DisciplinaVO(int idDisciplina, String nomeDisciplina) {
		super();
		this.idDisciplina = idDisciplina;
		this.nomeDisciplina = nomeDisciplina;		
	}

	public DisciplinaVO() {
		super();
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}	
	
}
