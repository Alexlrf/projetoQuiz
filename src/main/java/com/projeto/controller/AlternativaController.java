package com.projeto.controller;

import java.util.List;

import javax.swing.ButtonGroup;

import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.bo.AlternativaBO;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;

public class AlternativaController {
	 
	AlternativaBO alternativaBO = new AlternativaBO();
	
	public boolean cadastraAlternativas(String pergunta, List<String> listaAlternativas) throws ErroNoCadastroException {		
		boolean retorno = true;
		String validacao = "";		
		
		if (validaAlternativas(listaAlternativas)) {
			if (validaPergunta(pergunta)) {
				alternativaBO.cadastraAlternativas(pergunta, listaAlternativas);
			}else {
				validacao += "Verifique o campo PERGUNTA\n";
			}
			
		}else {
			validacao = "Verifique os campos das alternativas";
		}		
		
		if (!validacao.isEmpty()) {
			throw new ErroNoCadastroException(validacao);
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
