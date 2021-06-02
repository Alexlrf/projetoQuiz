package com.projeto.model.entity;

public class CategoriaVO {
	
	private int idCategoria;
	private int idDisciplina;
	private UsuarioVO usuario; 
	private String descricaoCategoria;
	
	public CategoriaVO(int idCategoria, int idDisciplina, UsuarioVO usuario, String descricaoCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.idDisciplina = idDisciplina;
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
	
	int getIdDisciplina() {
		return idDisciplina;
	}

	void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
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
