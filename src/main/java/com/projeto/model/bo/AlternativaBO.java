package com.projeto.model.bo;

import java.sql.SQLException;
import java.util.List;

import com.projeto.model.dao.AlternativaDAO;
import com.projeto.model.dao.PerguntaDAO;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Utils;

public class AlternativaBO {
	AlternativaDAO alternativaDAO = new AlternativaDAO();
	PerguntaDAO perguntaDAO = new PerguntaDAO();
	PerguntaVO perguntaVO = new PerguntaVO();
	CategoriaVO categoriaVO = new CategoriaVO();

	public boolean cadastraQuestao(PerguntaVO pergunta, List<String> listaAlternativas) throws SQLException {
		boolean retorno = true;
		String mensagem = "";
		
		pergunta.setCategoria(perguntaDAO.buscaIdcategoria(pergunta.getCategoria().getDescricaoCategoria()));
		perguntaVO = perguntaDAO.insert(pergunta);
		
		if (perguntaVO.getCategoria().getIdCategoria() < 0 || perguntaVO.getCategoria() == null) {
			mensagem = "Erro ao cadastrar pergunta!";
			retorno = false;
		} else {
			if (!alternativaDAO.cadastraAlternativas(perguntaVO, listaAlternativas)) {
				mensagem = "Erro ao cadastrar alternativas!";
				retorno = false;
			}
		}
		
		if (Utils.stringValida(mensagem)) {
			throw new SQLException(mensagem);
		}		
		return retorno;		
	}

	public List<AlternativaVO> buscaAlternativas(PerguntaVO pergunta) {
		// TODO Auto-generated method stub
		return alternativaDAO.buscaAlternativas(pergunta);
	}

}
