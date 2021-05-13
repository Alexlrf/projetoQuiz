package com.projeto.model.bo;

import com.projeto.repository.VerificarLoginDAO;

public class VerificarLoginBO {

	public boolean verificarLoginBO(String login, String senha) {
		VerificarLoginDAO verificarLogin = new VerificarLoginDAO();
		return verificarLogin.verificarLoginDAO(login, senha);
	}

}
