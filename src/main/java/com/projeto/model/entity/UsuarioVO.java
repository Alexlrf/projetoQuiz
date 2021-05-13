package com.projeto.model.entity;

public class UsuarioVO {
	
	private Integer idUsuario;
	private String login;
	private String senha;
	private String tipo;

	public UsuarioVO(Integer idUsuario, String login, String senha, String tipo) {
		super();
		this.idUsuario = idUsuario;
		this.login = login;
		this.senha = senha;
		this.tipo = tipo;
	}

	public UsuarioVO() {
		super();
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
