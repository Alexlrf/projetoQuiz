package com.projeto.controller;

import java.util.ArrayList;
import java.util.List;

import com.projeto.exceptions.LoginNaoInformadoException;
import com.projeto.exceptions.SenhaIncorretaException;
import com.projeto.exceptions.SenhaNaoInformadaException;
import com.projeto.exceptions.UsuarioNaoExistenteException;
import com.projeto.model.bo.UsuarioBO;
import com.projeto.model.dao.UsuarioDAO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.seletor.RelatorioDeUsuarioSeletor;
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

	public List<UsuarioVO> relatorioUsuarioController(RelatorioDeUsuarioSeletor relatorioUsuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.relatorioUsuarioSeletorBO(relatorioUsuario);
	}

	public String excluirUsuarioController(Integer idUsuarioSelecionado) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.excluirUsuarioBO(idUsuarioSelecionado);
	}

	public ArrayList<String> consultarTipoUsuarioController() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTipoUsuarioDAO();
	}

	public int consultarTotalPaginas(RelatorioDeUsuarioSeletor relatorioUsuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarTotalPaginas(relatorioUsuario);
	}
	
	public UsuarioVO cadastrar(UsuarioVO usuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.cadastrar(usuario);
	}

	public boolean alterar(UsuarioVO prof) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.alterar(prof);
	}

}
