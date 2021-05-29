package com.projeto.model.bo;

import com.projeto.exceptions.SenhaIncorretaException;
import com.projeto.exceptions.UsuarioNaoExistenteException;
import com.projeto.model.dao.UsuarioDAO;
import com.projeto.model.entity.UsuarioVO;

public class UsuarioBO {

	public UsuarioVO verificarLoginBO(String cpf, String senha) throws UsuarioNaoExistenteException, SenhaIncorretaException {
		UsuarioDAO verificarCpfSenha = new UsuarioDAO();
		if (!verificarCpfSenha.verificarCpfDAO(cpf)) {
			throw new UsuarioNaoExistenteException("Usuario não cadastrado");
		} else if (!verificarCpfSenha.verificarSenhaDAO(senha)){
			throw new SenhaIncorretaException("Senha incorreta");
		}else {
			return verificarCpfSenha.verificarCpfSenhaDAO(cpf, senha);
		}
	}

}
