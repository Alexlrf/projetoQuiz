package com.projeto.model.entity;

public class CategoriaVO {
	
	private int idCategoria;
	private String descricaoCategoria;
	
	public CategoriaVO(int idCategoria, String descricaoCategoria) {
		super();
		this.idCategoria = idCategoria;
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

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}	

}
