package com.projeto.model.entity;

public class CategoriaVO {
	
	private Long idCategoria;
	private String descricaoCategoria;
	
	public CategoriaVO(Long idCategoria, String descricaoCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.descricaoCategoria = descricaoCategoria;
	}

	public CategoriaVO() {
		super();
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}	

}
