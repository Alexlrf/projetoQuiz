package com.projeto.model.entity;

public class CategoriaVO {
	
	private int idCategoria;
	private int idDisciplina;
	private int idUsuario; 
	private String descricaoCategoria;
	
	public CategoriaVO(int idCategoria, int idDisciplina, int idUsuario, String descricaoCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.idDisciplina = idDisciplina;
		this.idUsuario = idUsuario;
		this.descricaoCategoria = descricaoCategoria;
	}

	public CategoriaVO() {
		super();
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

}
