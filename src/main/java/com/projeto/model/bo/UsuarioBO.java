package com.projeto.model.bo;

import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.projeto.exceptions.SenhaIncorretaException;
import com.projeto.exceptions.UsuarioNaoExistenteException;
import com.projeto.model.dao.UsuarioDAO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.seletor.RelatorioDeUsuarioSeletor;

public class UsuarioBO {

	public UsuarioVO verificarLoginBO(String cpf, String senha) throws UsuarioNaoExistenteException, SenhaIncorretaException {
		UsuarioDAO verificarCpfSenha = new UsuarioDAO();
		if (!verificarCpfSenha.verificarCpfDAO(cpf)) {
			throw new UsuarioNaoExistenteException("Usuario não cadastrado");
		} else if (!verificarCpfSenha.verificarSenhaDAO(cpf, senha)){
			throw new SenhaIncorretaException("Senha incorreta");
		}else {
			return verificarCpfSenha.verificarCpfSenhaDAO(cpf, senha);
		}
	}

	public List<UsuarioVO> relatorioUsuarioSeletorBO(RelatorioDeUsuarioSeletor relatorioUsuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.pesquisarPorSeletor(relatorioUsuario);
	}

	public String excluirUsuarioBO(Integer idUsuarioSelecionado) {
		String retorno = "Erro ao verificar validação de exclusão";
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		if (usuarioDAO.desativarUsuarioDAO(idUsuarioSelecionado)) {
			retorno = "Usuario excluido com sucesso.";
		} else {
			retorno = "Não foi possivel excluir o usuario.";
		}
		return retorno;
	}

	public int consultarTotalPaginas(RelatorioDeUsuarioSeletor relatorioUsuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTotalPaginas(relatorioUsuario);
	}
	
	public UsuarioVO cadastrar(UsuarioVO usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.cadastrar(usuario);
	}

	public boolean alterar(UsuarioVO prof) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.alterar(prof);
	}

}
