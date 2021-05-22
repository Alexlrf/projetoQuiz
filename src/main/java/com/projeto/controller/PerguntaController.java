package com.projeto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.model.bo.PerguntaBO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Utils;

public class PerguntaController {
	PerguntaBO perguntaBO = new PerguntaBO();
	List<PerguntaVO> listaPerguntas = new ArrayList<>();

	public List<PerguntaVO> buscaPorCategoriaEscolhida(String categoriaEscolhida) {
		return perguntaBO.buscaPorCategoriaEscolhida(categoriaEscolhida);
	}

	public List<PerguntaVO> buscaPorTextoDigitado(String textoDigitado) throws SQLException {		
		String mensagem = "";
		
		if (Utils.stringValida(textoDigitado)) {
			listaPerguntas = perguntaBO.buscaPorTextoDigitado(textoDigitado);
		} else {
			mensagem = "Erro na busca!\n Confira o texto digitado \n";
		}
		
		if (listaPerguntas == null || listaPerguntas.size() == 0) {
			mensagem = "Não foram encontradas perguntas com este termo de busca!";
			throw new SQLException(mensagem);
		}		
		return listaPerguntas;		
	}

}
