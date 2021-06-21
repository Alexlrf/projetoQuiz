package com.projeto.controller;

import java.sql.SQLException;
import java.util.List;

import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.bo.CategoriaBO;
import com.projeto.model.bo.PerguntaBO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Utils;

public class CategoriaController {
	CategoriaBO categoriaBO = new CategoriaBO();
	PerguntaBO perguntaBO = new PerguntaBO();

	public boolean cadastraCategoria(CategoriaVO categoriaVO) throws ErroNoCadastroException, SQLException {
		boolean retorno = true;
		
		if (validaCategoria(categoriaVO)) {
			
			if (!categoriaBO.cadastraCategoria(categoriaVO)) {
				retorno = false;
				throw new ErroNoCadastroException("Não foi possível cadastrar Categoria!\n Verifique se "
						+categoriaVO.getDescricaoCategoria()+" já está cadastrada.\n");
			} 
			
		} else {
			throw new ErroNoCadastroException("Texto inválido!\n Verifique se "
					+categoriaVO.getDescricaoCategoria()+"\n não excede o número de caracteres.\n");
		}		
		return retorno;			
	}	

	private boolean validaCategoria(CategoriaVO categoriaVO) throws ErroNoCadastroException {
		String validacaoCategoria = " ";
		boolean categoriaValida = true;
		if (!Utils.stringValida(categoriaVO.getDescricaoCategoria()) 
				|| categoriaVO.getDescricaoCategoria().equalsIgnoreCase("Adicionar categoria")) {
			categoriaValida = false;
			validacaoCategoria = "Adicione a categoria a ser cadastrada!\n";
		}
		
		if (Utils.stringValida(validacaoCategoria)) {
			throw new ErroNoCadastroException(validacaoCategoria);			
		}		
		return categoriaValida;
	}

	public List<CategoriaVO> consultaTodasCategorias(UsuarioVO usuarioLogado) throws ErroNaConsultaException {
		
		return categoriaBO.consultaTodasCategorias(usuarioLogado);
	}


	public String alteraCategoria(String categoriaEscolhida, String categoriaAlterada, Integer idUsuario) {
		
		return categoriaBO.alteraCategoria(categoriaEscolhida, categoriaAlterada, idUsuario);
	}


	public CategoriaVO buscaCategoriaPorDescricao(String descricaoCategoria) {
				
		return categoriaBO.buscaCategoriaPorDescricao(descricaoCategoria);
	}

	public void excluiCategoria(String categoriaEscolhida, Integer idUsuario) throws ErroNoCadastroException {
		String mensagem = "";
		
		if (!Utils.stringValida(categoriaEscolhida)) {
			mensagem = "Erro ao encontrar CATEGORIA!";
		} else {
			if (!categoriaBO.excluiCategoria(categoriaEscolhida, idUsuario)) {
				mensagem = "Erro ao excluir CATEGORIA!";
			}
		}
		
		if (Utils.stringValida(mensagem)) {
			throw new ErroNoCadastroException(mensagem);
		}
	}
}
