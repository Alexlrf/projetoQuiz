package com.projeto.model.bo;

import java.sql.SQLException;
import java.util.List;

import com.projeto.model.dao.AlternativaDAO;
import com.projeto.model.dao.PerguntaDAO;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.PerguntaVO;

public class AlternativaBO {
	AlternativaDAO alternativaDAO = new AlternativaDAO();
	PerguntaDAO perguntaDAO = new PerguntaDAO();
	PerguntaVO perguntaVO = new PerguntaVO();

	public boolean cadastraAlternativas(PerguntaVO pergunta, List<String> listaAlternativas) throws SQLException {
		boolean retorno = true;
		perguntaVO = perguntaDAO.insert(pergunta);
		
		if (perguntaVO.getIdPergunta() < 1) {
			retorno = false;
			
		} else {
			
		}
		
		
		
		
		System.out.println(" CATEGORIA: "+pergunta.getCategoria()+"\n\n PERGUNTA: "+pergunta.getTextoPergunta()+"\n\n");
		int cont = 1;
		for (String opcao : listaAlternativas) {
			System.out.println(" >> ALTERNATIVA "+cont+":  "+opcao+"\n");
			cont ++;
		}
		return retorno;
		
	}

	public List<AlternativaVO> buscaAlternativas(PerguntaVO pergunta) {
		// TODO Auto-generated method stub
		return alternativaDAO.buscaAlternativas(pergunta);
	}

}
