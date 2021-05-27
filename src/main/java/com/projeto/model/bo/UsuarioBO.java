package com.projeto.model.bo;

import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.model.dao.UsuarioDAO;
import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.UsuarioVO;

public class UsuarioBO {

	public UsuarioVO verificarLoginBO(String cpf, String senha) {
		UsuarioDAO verificarLogin = new UsuarioDAO();
		return verificarLogin.verificarLoginDAO(cpf, senha);
	}

}
