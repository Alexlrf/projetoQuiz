package com.projeto.controller;

import java.util.List;

import com.projeto.model.bo.PerguntaBO;
import com.projeto.model.entity.PerguntaVO;

public class PerguntaController {
	PerguntaBO perguntaBO = new PerguntaBO();

	public List<PerguntaVO> buscaPorCategoriaEscolhida(String categoriaEscolhida) {
		return perguntaBO.buscaPorCategoriaEscolhida(categoriaEscolhida);
	}

}
