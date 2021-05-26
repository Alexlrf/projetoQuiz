package com.projeto.controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;

import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.bo.AlternativaBO;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;

public class AlternativaController {
	 
	AlternativaBO alternativaBO = new AlternativaBO();
	
	public boolean cadastraAlternativas(PerguntaVO pergunta, List<String> listaAlternativas) throws ErroNoCadastroException, SQLException {		
		boolean retorno = true;
		
		
//		if (validaAlternativas(listaAlternativas)) {
//			if (validaPergunta(pergunta.getTextoPergunta())) {
//				retorno =alternativaBO.cadastraAlternativas(pergunta, listaAlternativas);
//			}else {

		return retorno;
	}	

	public boolean validaAlternativas(List<String> listaAlternativas) {			
		return Utils.alternativaValida(listaAlternativas.get(0), listaAlternativas.get(1)
				, listaAlternativas.get(2), listaAlternativas.get(3), listaAlternativas.get(4));
	}	


	public List<AlternativaVO> buscaAlternativas(PerguntaVO pergunta) {
 		return alternativaBO.buscaAlternativas(pergunta);
	}	

}
