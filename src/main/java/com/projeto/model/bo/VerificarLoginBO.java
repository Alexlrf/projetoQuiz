package com.projeto.model.bo;

import com.projeto.model.dao.UsuarioDAO;
import com.projeto.model.entity.UsuarioVO;

public class VerificarLoginBO {

	public UsuarioVO verificarLoginBO(String login, String senha) {
		UsuarioDAO verificarLogin = new UsuarioDAO();
		return verificarLogin.verificarLoginDAO(login, senha);
	}

}
