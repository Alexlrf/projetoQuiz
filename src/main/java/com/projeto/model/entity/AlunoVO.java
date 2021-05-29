package com.projeto.model.entity;

import java.time.LocalDate;

import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;

public class AlunoVO extends UsuarioVO{

	public AlunoVO(Integer idUsuario, String nome, String rg, String cpf, LocalDate dataNascimento, char sexo,
			boolean possuiDeficiencia, String celular, String nacionalidade, TurnoEnum turno, String senha,
			TipoUsuarioEnum tipo) {
		super(idUsuario, nome, rg, cpf, dataNascimento, sexo, possuiDeficiencia, celular, nacionalidade, turno, senha, tipo);
	}

	public AlunoVO() {
		super();
	}
}
