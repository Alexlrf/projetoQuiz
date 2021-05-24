package com.projeto.controller;

import com.projeto.exceptions.LoginNaoInformadoException;
import com.projeto.exceptions.SenhaNaoInformadaException;
import com.projeto.exceptions.UsuarioNaoExistenteException;
import com.projeto.model.bo.VerificarLoginBO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.view.TelaPrincipal;

public class VerificarLoginController {

	public UsuarioVO verificarLoginController(String login, String senha) throws UsuarioNaoExistenteException, LoginNaoInformadoException, SenhaNaoInformadaException {
		VerificarLoginBO verificarLogin = new VerificarLoginBO();
		TelaPrincipal telaProfessor = new TelaPrincipal();

		if (login == null || login.trim().isEmpty()) {
			throw new LoginNaoInformadoException("Login não informado");
		} else if (senha.trim().isEmpty()) {
			throw new SenhaNaoInformadaException("Senha não informada");
		}
		
		UsuarioVO usuario = new UsuarioVO();
		usuario = verificarLogin.verificarLoginBO(login, senha);

		if (usuario == null || usuario.getIdUsuario() <= 0) {
			throw new UsuarioNaoExistenteException("Usuario não cadastrado");
		}
		
		
		telaProfessor.setUsuario(usuario);
		telaProfessor.setVisible(true);
		return usuario;
	}

}
