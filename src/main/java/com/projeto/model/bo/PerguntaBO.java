package com.projeto.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.model.dao.CategoriaDAO;
import com.projeto.model.dao.PerguntaDAO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Utils;
import com.projeto.seletor.PerguntaSeletor;

public class PerguntaBO {
	PerguntaDAO perguntaDAO = new PerguntaDAO();
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	CategoriaVO categoriaVO = new CategoriaVO();
	
	public List<PerguntaVO> buscaPorCategoriaEscolhida(String categoriaEscolhida) {		
		int idCategoria =  categoriaDAO.buscaIdCategoria(categoriaEscolhida);
		
		return perguntaDAO.buscaPorCategoriaEscolhida(idCategoria);
	}
	
	
	/**	 * 
	 * Método que busca o id da categoria e insere  na pergunta para consultar categoria e texto da pergunta
	 * 
	 * @param perguntaSeletor
	 * @return lista de perguntas
	 * @throws SQLException
	 */
	
	public List<PerguntaVO> buscaComSeletor(PerguntaSeletor perguntaSeletor) throws SQLException{	
		List<PerguntaVO> listaPerguntas = new ArrayList<>();
		
		if (Utils.stringValida(perguntaSeletor.getCategoria())) {
			categoriaVO = perguntaDAO.buscaIdcategoria(perguntaSeletor.getCategoria());
			perguntaSeletor.setIdCategoria(categoriaVO.getIdCategoria());
			listaPerguntas = perguntaDAO.buscaComSeletor(perguntaSeletor);	
			
		} else {
			perguntaSeletor.setIdCategoria(0);
			listaPerguntas = perguntaDAO.buscaComSeletor(perguntaSeletor);
		}		
		return listaPerguntas;
	}
	
	
	public List<PerguntaVO> buscaPorTextoDigitado(String textoDigitado) {
		
		return perguntaDAO.buscaPorTextoDigitado(textoDigitado);
	}

}
