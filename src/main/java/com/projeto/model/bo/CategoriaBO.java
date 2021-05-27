package com.projeto.model.bo;

import java.sql.SQLException;
import java.util.List;

import com.projeto.model.dao.CategoriaDAO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Utils;

public class CategoriaBO {
	
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	
	public boolean cadastraCategoria(CategoriaVO categoriaVO) throws SQLException {
		boolean retorno = true;
		CategoriaVO categoria = new CategoriaVO();
		String mensagem = "";
		if (categoriaDAO.consultaCategoriaExistente(categoriaVO)) {
			mensagem = "Categoria já cadastrada!\n";
			retorno = false;
		}else {
			categoria = categoriaDAO.insert(categoriaVO);
			if (categoria == null || categoria.getIdCategoria()==0) {
				mensagem = "Erro ao cadastrar categoria!\n";
				retorno = false;
			}
		}
		
		if (Utils.stringValida(mensagem)) {
			throw new SQLException(mensagem);
		}
		
		return retorno;
	}

	public List<CategoriaVO> consultaTodasCategorias() {
		return categoriaDAO.findAll();
	}

	public CategoriaVO buscaCategoriaPorDescricao(String descricaoCategoria) { 
		
 		return categoriaDAO.buscaCategoriaPorDescricao(descricaoCategoria);
	}

}
