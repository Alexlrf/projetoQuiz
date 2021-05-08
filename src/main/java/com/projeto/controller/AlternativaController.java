package com.projeto.controller;

import java.util.List;

import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.bo.AlternativaBO;

public class AlternativaController {
	 
	AlternativaBO alternativaBO = new AlternativaBO();
	
	public boolean cadastraAlternativas(String pergunta, List<String> listaAlternativas) throws ErroNoCadastroException {		
		boolean retorno = true;
		String validacao = "";
		if (validaPergunta(pergunta) && validaAlternativas(listaAlternativas)) {
			alternativaBO.cadastraAlternativas(pergunta, listaAlternativas);
			
		} else {
			validacao = "Erro ao cadastrar! Esta é a exceção";
			retorno = false;
		}
		
		if (!validacao.isEmpty()) {
			throw new ErroNoCadastroException(validacao);
		}
		return retorno;
	}
	

	private boolean validaAlternativas(List<String> listaAlternativas) {
		boolean testador = true;
		
		for (String opcoes : listaAlternativas) {
			
			if (opcoes.contains("ª alternativa")) {
				testador= false;
			}
			
		}
		return testador;
	}
	

	private boolean validaPergunta(String pergunta) {
		// TODO Auto-generated method stub
		return true;
	}



}
