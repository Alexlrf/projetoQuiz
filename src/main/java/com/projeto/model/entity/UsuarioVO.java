package com.projeto.model.entity;

import java.time.LocalDate;

import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;

public abstract class UsuarioVO {
	
	private Integer idUsuario;
	private String nome;
	private String rg;
	private String cpf;
	private LocalDate dataNascimento;
	private char sexo;
	private boolean possuiDeficiencia;
	private String celular;
	private String nacionalidade;
	private TurnoEnum turno;
	private String senha;
	private TipoUsuarioEnum tipo;
	
	public UsuarioVO(Integer idUsuario, String nome, String rg, String cpf, LocalDate dataNascimento, char sexo,
			boolean possuiDeficiencia, String celular, String nacionalidade, TurnoEnum turno, String senha,
			TipoUsuarioEnum tipo) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.possuiDeficiencia = possuiDeficiencia;
		this.celular = celular;
		this.nacionalidade = nacionalidade;
		this.turno = turno;
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
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public char getSexo() {
		return sexo;
	}
	
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public boolean isPossuiDeficiencia() {
		return possuiDeficiencia;
	}
	
	public void setPossuiDeficiencia(boolean possuiDeficiencia) {
		this.possuiDeficiencia = possuiDeficiencia;
	}
	
	public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	public TurnoEnum getTurno() {
		return turno;
	}
	
	public void setTurno(TurnoEnum turno) {
		this.turno = turno;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public TipoUsuarioEnum getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoUsuarioEnum tipo) {
		this.tipo = tipo;
	}

}
