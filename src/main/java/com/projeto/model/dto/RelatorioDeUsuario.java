package com.projeto.model.dto;

import java.time.LocalDate;

import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;

public class RelatorioDeUsuario {

	private Integer idUsuario;
	private String nome;
	private String rg;
	private String cpf;
	private char sexo;
	private boolean possuiDeficiencia;
	private TurnoEnum turno;
	private TipoUsuarioEnum tipo;
	
	public RelatorioDeUsuario(Integer idUsuario, String nome, String rg, String cpf, char sexo,
			boolean possuiDeficiencia, TurnoEnum turno, TipoUsuarioEnum tipo) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.sexo = sexo;
		this.possuiDeficiencia = possuiDeficiencia;
		this.turno = turno;
		this.tipo = tipo;
	}

	public RelatorioDeUsuario() {
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

	public TurnoEnum getTurno() {
		return turno;
	}

	public void setTurno(TurnoEnum turno) {
		this.turno = turno;
	}

	public TipoUsuarioEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuarioEnum tipo) {
		this.tipo = tipo;
	}
	
}
