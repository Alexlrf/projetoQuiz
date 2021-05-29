package com.projeto.controller;

import javax.swing.JOptionPane;

import com.projeto.exceptions.LoginNaoInformadoException;
import com.projeto.exceptions.SenhaIncorretaException;
import com.projeto.exceptions.SenhaNaoInformadaException;
import com.projeto.exceptions.UsuarioNaoExistenteException;
import com.projeto.model.bo.UsuarioBO;
import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.CoordenadorVO;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.view.TelaPrincipal;

public class UsuarioController {

	public UsuarioVO verificarLoginController(String cpf, String senha) throws UsuarioNaoExistenteException, LoginNaoInformadoException, SenhaNaoInformadaException, SenhaIncorretaException {
		UsuarioBO verificarLogin = new UsuarioBO();
		
		// caso algum dos campos estejam nulos, deverá lançar uma Exceção
		if (cpf == null || cpf.trim().isEmpty()) {
			throw new LoginNaoInformadoException("Login não informado");
		} else if (senha.trim().isEmpty()) {
			throw new SenhaNaoInformadaException("Senha não informada");
		}
		
		UsuarioVO usuario = verificarLogin.verificarLoginBO(cpf, senha);
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		telaPrincipal.setUsuario(usuario);
		telaPrincipal.setVisible(true);
		
		return usuario;
	}
}
