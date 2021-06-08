package com.projeto.controller;

import java.sql.SQLException;
import java.util.List;

import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.bo.AlternativaBO;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Utils;

public class AlternativaController {
	 
	AlternativaBO alternativaBO = new AlternativaBO();
	
	public boolean cadastraQuestao(PerguntaVO pergunta) throws ErroNoCadastroException, SQLException {		
		
		return alternativaBO.cadastraQuestao(pergunta);
	}	

	public boolean validaAlternativas(List<AlternativaVO> listaAlternativas) {			
		return Utils.alternativaValida(listaAlternativas.get(0).getTexto(), listaAlternativas.get(1).getTexto()
				, listaAlternativas.get(2).getTexto(), listaAlternativas.get(3).getTexto(), listaAlternativas.get(4).getTexto());
	}	


	public List<AlternativaVO> buscaAlternativas(PerguntaVO pergunta) {	
 		return alternativaBO.buscaAlternativas(pergunta);
	}

	public boolean alteraAlternativa(AlternativaVO alternativaVO) throws ErroNoCadastroException{
		
		
		
		
		System.out.println("IdPergunta : "+alternativaVO.getIdPergunta()+ "\nIdAlternativa : "+alternativaVO.getIdAlternativa()
							+"\nTexto : "+alternativaVO.getTexto()+"\nStatus : "+alternativaVO.getAlternativaCorreta());
		return false;
		
	}	

}
