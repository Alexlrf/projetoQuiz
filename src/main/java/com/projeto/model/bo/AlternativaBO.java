package com.projeto.model.bo;

import java.util.List;

import com.projeto.model.dao.AlternativaDAO;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.PerguntaVO;

public class AlternativaBO {
	AlternativaDAO alternativaDAO = new AlternativaDAO();

	public void cadastraAlternativas(PerguntaVO pergunta, List<String> listaAlternativas) {
		System.out.println(" CATEGORIA: "+pergunta.getCategoria()+"\n\n PERGUNTA: "+pergunta.getTextoPergunta()+"\n\n");
		int cont = 1;
		for (String opcao : listaAlternativas) {
			System.out.println(" >> ALTERNATIVA "+cont+":  "+opcao+"\n");
			cont ++;
		}
		
	}

	public List<AlternativaVO> buscaAlternativas(PerguntaVO pergunta) {
		// TODO Auto-generated method stub
		return alternativaDAO.buscaAlternativas(pergunta);
	}

}
