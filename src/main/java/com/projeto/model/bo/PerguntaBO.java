package com.projeto.model.bo;

import java.util.List;

import com.projeto.model.dao.PerguntaDAO;
import com.projeto.model.entity.PerguntaVO;

public class PerguntaBO {
	PerguntaDAO perguntaDAO = new PerguntaDAO();

	public List<PerguntaVO> buscaPorCategoriaEscolhida(String categoriaEscolhida) {
		return perguntaDAO.buscaPorCategoriaEscolhida(categoriaEscolhida);
	}

}
