package com.projeto.model.entity;

import java.time.LocalDate;

import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;

public class ProfessorVO extends UsuarioVO{
	
	private int idDisciplina;

	public ProfessorVO() {
		super();
	}

	public ProfessorVO(Integer idUsuario, String nome, String rg, String cpf, LocalDate dataNascimento, char sexo,
			boolean possuiDeficiencia, String celular, TurnoEnum turno, String senha,
			TipoUsuarioEnum tipo, boolean avito, int idDisciplina) {
		super(idUsuario, nome, rg, cpf, dataNascimento, sexo, possuiDeficiencia, celular, turno, senha,
				tipo, avito);
		this.idDisciplina = idDisciplina;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	
}
