package com.projeto.controller;

import java.util.ArrayList;
import java.util.List;

import com.projeto.exceptions.CpfExistenteException;
import com.projeto.exceptions.LoginNaoInformadoException;
import com.projeto.exceptions.RgExistenteException;
import com.projeto.exceptions.SenhaIncorretaException;
import com.projeto.exceptions.SenhaNaoInformadaException;
import com.projeto.exceptions.UsuarioNaoExistenteException;
import com.projeto.model.bo.UsuarioBO;
import com.projeto.model.dao.UsuarioDAO;
import com.projeto.model.entity.DisciplinaVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.seletor.PesquisarDeUsuarioSeletor;
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

	public List<UsuarioVO> pesquisarUsuarioController(PesquisarDeUsuarioSeletor pesquisarUsuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.pesquisarUsuarioSeletorBO(pesquisarUsuario);
	}

	public String excluirUsuarioController(Integer idUsuarioSelecionado) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.excluirUsuarioBO(idUsuarioSelecionado);
	}

	public ArrayList<String> consultarTipoUsuarioController() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTipoUsuarioDAO();
	}

	public int consultarTotalPaginas(PesquisarDeUsuarioSeletor relatorioUsuario) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarTotalPaginas(relatorioUsuario);
	}
	
	public UsuarioVO cadastrar(UsuarioVO usuario) throws RgExistenteException, CpfExistenteException {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.cadastrar(usuario);
	}

	public boolean alterar(UsuarioVO prof) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.alterar(prof);
	}

	public List<DisciplinaVO> buscarDisciplina() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.buscarDisciplina();
	}

}
