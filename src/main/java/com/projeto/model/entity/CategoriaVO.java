package com.projeto.model.entity;

public class CategoriaVO {
	
	private int idCategoria;
	private UsuarioVO usuario; 
	private String descricaoCategoria;
	
	public CategoriaVO(int idCategoria, UsuarioVO usuario, String descricaoCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.usuario = usuario;
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
	
	public UsuarioVO getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

}
