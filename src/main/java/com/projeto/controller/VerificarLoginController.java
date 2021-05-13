package com.projeto.controller;

import com.projeto.model.bo.VerificarLoginBO;

public class VerificarLoginController {

	public boolean verificarLoginController(String login, String senha) {
		VerificarLoginBO verificarLogin = new VerificarLoginBO();
		return verificarLogin.verificarLoginBO(login, senha);
	}

}
