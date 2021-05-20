package com.projeto.model.bo;

import java.util.List;

import com.projeto.model.dao.CategoriaDAO;
import com.projeto.model.dao.PerguntaDAO;
import com.projeto.model.entity.PerguntaVO;

public class PerguntaBO {
	PerguntaDAO perguntaDAO = new PerguntaDAO();
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	public List<PerguntaVO> buscaPorCategoriaEscolhida(String categoriaEscolhida) {
		
		int idCategoria =  categoriaDAO.buscaIdCategoria(categoriaEscolhida);
		
		return perguntaDAO.buscaPorCategoriaEscolhida(idCategoria);
	}

}
