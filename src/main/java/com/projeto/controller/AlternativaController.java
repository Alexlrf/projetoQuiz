package com.projeto.controller;

import java.util.List;

import javax.swing.ButtonGroup;

import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.bo.AlternativaBO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;

public class AlternativaController {
	 
	AlternativaBO alternativaBO = new AlternativaBO();
	
	public boolean cadastraAlternativas(PerguntaVO pergunta, List<String> listaAlternativas) throws ErroNoCadastroException {		
		boolean retorno = true;
		String validacaoAlternativa = "";		
		
		if (validaAlternativas(listaAlternativas)) {
			if (validaPergunta(pergunta.getTextoPergunta())) {
				alternativaBO.cadastraAlternativas(pergunta, listaAlternativas);
			}else {
				validacaoAlternativa += "Verifique o campo PERGUNTA\n";
			}
			
		}else {
			validacaoAlternativa = "Verifique os campos das alternativas";
		}		
		
		if (!validacaoAlternativa.isEmpty()) {
			throw new ErroNoCadastroException(validacaoAlternativa);
		}
		return retorno;
	}	

	private boolean validaAlternativas(List<String> listaAlternativas) {			
		return Utils.alternativaValida(listaAlternativas.get(0), listaAlternativas.get(1)
				, listaAlternativas.get(2), listaAlternativas.get(3), listaAlternativas.get(4));
	}	

	private boolean validaPergunta(String pergunta) {
		boolean perguntaValida = true;
		if (pergunta.equalsIgnoreCase(Constants.PERGUNTA) || !Utils.stringValida(pergunta)) {
			perguntaValida = false;
		}
		return perguntaValida;
	}	

}
