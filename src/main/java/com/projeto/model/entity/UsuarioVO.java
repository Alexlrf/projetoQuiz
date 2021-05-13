package com.projeto.model.entity;

import com.projeto.enums.UsuarioEnum;

public class UsuarioVO {
	
	private Integer idUsuario;
	private String login;
	private String senha;
	private UsuarioEnum tipo;

	public UsuarioVO(Integer idUsuario, String login, String senha, UsuarioEnum tipo) {
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

	public UsuarioEnum getTipo() {
		return tipo;
	}

	public void setTipo(UsuarioEnum tipo) {
		this.tipo = tipo;
	}

}
