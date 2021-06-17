package com.projeto.model.bo;

import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.projeto.exceptions.CpfExistenteException;
import com.projeto.exceptions.RgExistenteException;
import com.projeto.exceptions.SenhaIncorretaException;
import com.projeto.exceptions.UsuarioNaoExistenteException;
import com.projeto.model.dao.UsuarioDAO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.seletor.PesquisarDeUsuarioSeletor;

public class UsuarioBO {

	public UsuarioVO verificarLoginBO(String cpf, String senha) throws UsuarioNaoExistenteException, SenhaIncorretaException {
		UsuarioDAO verificarCpfSenha = new UsuarioDAO();
		if (!verificarCpfSenha.verificarCpfDAO(cpf, null)) {
			throw new UsuarioNaoExistenteException("Usuario não cadastrado");
		} else if (!verificarCpfSenha.verificarSenhaDAO(cpf, senha)){
			throw new SenhaIncorretaException("Senha incorreta");
		}else {
			return verificarCpfSenha.verificarCpfSenhaDAO(cpf, senha);
		}
	}

	public List<UsuarioVO> pesquisarUsuarioSeletorBO(PesquisarDeUsuarioSeletor pesquisarUsuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.pesquisarPorSeletor(pesquisarUsuario);
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

	public int consultarTotalPaginas(PesquisarDeUsuarioSeletor relatorioUsuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTotalPaginas(relatorioUsuario);
	}
	
	public UsuarioVO cadastrar(UsuarioVO usuario) throws RgExistenteException, CpfExistenteException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		if (usuarioDAO.verificarRgDAO(usuario.getRg(), usuario.getIdUsuario())) {
			throw new RgExistenteException("Rg já existente no banco, favor reconsiderar!");
		} else if (usuarioDAO.verificarCpfDAO(usuario.getCpf(), usuario.getIdUsuario())) {
			throw new CpfExistenteException("Cpf já cadastrado no banco, favor reconsiderar!");
		} else {
			usuario = usuarioDAO.cadastrar(usuario);
		}
		
		return usuario;
	}

	public boolean alterar(UsuarioVO usuario) throws RgExistenteException, CpfExistenteException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		boolean atualizou = false;
		
		if (usuarioDAO.verificarRgDAO(usuario.getRg(), usuario.getIdUsuario())) {
			throw new RgExistenteException("Rg já existente no banco, favor reconsiderar!");
		} else if (usuarioDAO.verificarCpfDAO(usuario.getCpf(), usuario.getIdUsuario())) {
			throw new CpfExistenteException("Cpf já cadastrado no banco, favor reconsiderar!");
		} else {
			atualizou = usuarioDAO.alterar(usuario);
		}
		return atualizou;
	}

}
