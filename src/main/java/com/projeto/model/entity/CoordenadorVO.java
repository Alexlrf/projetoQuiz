package com.projeto.model.entity;

import java.time.LocalDate;

import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;

public class CoordenadorVO extends UsuarioVO{
	
	public CoordenadorVO(Integer idUsuario, String nome, String rg, String cpf, LocalDate dataNascimento, char sexo,
			boolean possuiDeficiencia, String celular, String nacionalidade, TurnoEnum turno, String senha,
			TipoUsuarioEnum tipo, boolean avito) {
		super(idUsuario, nome, rg, cpf, dataNascimento, sexo, possuiDeficiencia, celular, nacionalidade, turno, senha, tipo,
				avito);
	}

	public CoordenadorVO() {
		super();
	}
	
}
