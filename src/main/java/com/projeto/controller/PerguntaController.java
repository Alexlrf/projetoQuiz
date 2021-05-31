package com.projeto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.model.bo.PerguntaBO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;
import com.projeto.seletor.PerguntaSeletor;

public class PerguntaController {
	PerguntaBO perguntaBO = new PerguntaBO();
	List<PerguntaVO> listaPerguntas = new ArrayList<>();

	public List<PerguntaVO> buscaPorCategoriaEscolhida(String categoriaEscolhida) {
		return perguntaBO.buscaPorCategoriaEscolhida(categoriaEscolhida);
	}	

	public List<PerguntaVO> buscaComSeletor(PerguntaSeletor perguntaSeletor) throws ErroNaConsultaException{
		String mensagem = "";
		
		listaPerguntas = perguntaBO.buscaComSeletor(perguntaSeletor);
		if (listaPerguntas == null || listaPerguntas.size() == 0) {
			mensagem = "Não foram encontradas perguntas para esta pesquisa";
		} 
		
		if (Utils.stringValida(mensagem)) {
			throw new ErroNaConsultaException(mensagem);		
		}		
		return listaPerguntas;		
	}	

	public List<PerguntaVO> buscaPorTextoDigitado(String textoDigitado) throws ErroNaConsultaException {		
		String mensagem = "";
		
		if (Utils.stringValida(textoDigitado)) {
			listaPerguntas = perguntaBO.buscaPorTextoDigitado(textoDigitado);
		} else {
			mensagem = "Erro na busca!\n Confira o texto digitado \n";
		}
		
		if (listaPerguntas == null || listaPerguntas.size() == 0) {
			mensagem = "Não foram encontradas perguntas com este termo de busca!";
			throw new ErroNaConsultaException(mensagem);
		}		
		return listaPerguntas;		
	}

	public boolean validaPergunta(PerguntaVO perguntaVO) {
		boolean perguntaValida = true;
		if (perguntaVO.getTextoPergunta().equalsIgnoreCase(Constants.PERGUNTA) || !Utils.stringValida(perguntaVO.getTextoPergunta())) {
			perguntaValida = false;
		}
		return perguntaValida;
	}
}


