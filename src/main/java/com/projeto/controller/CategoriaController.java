package com.projeto.controller;

import java.util.List;

import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.bo.CategoriaBO;
import com.projeto.model.bo.PerguntaBO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Utils;

public class CategoriaController {
	CategoriaBO categoriaBO = new CategoriaBO();
	PerguntaBO perguntaBO = new PerguntaBO();

	public boolean cadastraCategoria(CategoriaVO categoriaVO) throws ErroNoCadastroException {
		boolean retorno = true;
		
		if (validaCategoria(categoriaVO)) {
			if(categoriaBO.cadastraCategoria(categoriaVO)) {
				
			} else {
				throw new ErroNoCadastroException("Não foi possível cadastrar Categoria!\n Verifique se "
						+categoriaVO.getDescricaoCategoria()+" já está cadastrada.\n");
			}			
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

	public List<CategoriaVO> consultaTodasCategorias() {
		
		return categoriaBO.consultaTodasCategorias();
	}


}
