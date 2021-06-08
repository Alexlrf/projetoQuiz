package com.projeto.model.bo;

import java.sql.SQLException;
import java.util.List;

import com.projeto.model.dao.AlternativaDAO;
import com.projeto.model.dao.DisciplinaDAO;
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
	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

	public boolean cadastraQuestao(PerguntaVO pergunta) throws SQLException {
		boolean retorno = true;
		String mensagem = "";
		
		pergunta.setCategoria(perguntaDAO.buscaIdcategoria(pergunta.getCategoria().getDescricaoCategoria()));
		pergunta.setIdDisciplina(disciplinaDAO.buscaIdDisciplina(pergunta.getIdUsuario()));
		perguntaVO = perguntaDAO.insert(pergunta);
		pergunta.setIdPergunta(perguntaVO.getIdPergunta());
		
		if (pergunta.getCategoria().getIdCategoria() < 0 || pergunta.getCategoria() == null) {
			mensagem = "Erro ao cadastrar pergunta!";
			retorno = false;
		} else {
			if (!alternativaDAO.cadastraAlternativas(pergunta)) {
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
		return alternativaDAO.buscaAlternativas(pergunta);
	}

}
