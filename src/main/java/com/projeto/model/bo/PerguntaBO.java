package com.projeto.model.bo;

import java.util.ArrayList;
import java.util.List;

import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.dao.CategoriaDAO;
import com.projeto.model.dao.DisciplinaDAO;
import com.projeto.model.dao.PerguntaDAO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Utils;
import com.projeto.seletor.PerguntaSeletor;

public class PerguntaBO {
	PerguntaDAO perguntaDAO = new PerguntaDAO();
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	CategoriaVO categoriaVO = new CategoriaVO();
	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	
	public List<PerguntaVO> buscaPorCategoriaEscolhida(String categoriaEscolhida) {		
		int idCategoria =  categoriaDAO.buscaIdCategoria(categoriaEscolhida);
		
		return perguntaDAO.buscaPorCategoriaEscolhida(idCategoria);
	}
	
	
	/**	 * 
	 * Método que busca o id da categoria e insere  na pergunta para consultar categoria e texto da pergunta
	 * 
	 * @param perguntaSeletor
	 * @return lista de perguntas
	 * @throws ErroNaConsultaException
	 */
	
	public List<PerguntaVO> buscaComSeletor(PerguntaSeletor perguntaSeletor) throws ErroNaConsultaException{	
		List<PerguntaVO> listaPerguntas = new ArrayList<>();
		
		if (!perguntaSeletor.isPerguntasUsuario() ) {
			perguntaSeletor.setIdDisciplina(disciplinaDAO.buscaIdDisciplina(perguntaSeletor.getIdUsuario()));
			perguntaSeletor.setIdUsuario(0);
		} else {
			perguntaSeletor.setIdDisciplina(0);
			
		}
		
		if (Utils.stringValida(perguntaSeletor.getCategoria())) {
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


	public void alteraPergunta(PerguntaVO perguntaAlterada) throws ErroNoCadastroException{
		perguntaDAO.alteraPergunta(perguntaAlterada);			
		 		
	}


	public int buscaIdUsuario(int idPergunta) {
		return perguntaDAO.buscaIdUsuario(idPergunta);
	}
}
