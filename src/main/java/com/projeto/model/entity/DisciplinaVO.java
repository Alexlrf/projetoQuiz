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

	int getIdDisciplina() {
		return idDisciplina;
	}

	void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	String getNomeDisciplina() {
		return nomeDisciplina;
	}

	void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}	
	
}
