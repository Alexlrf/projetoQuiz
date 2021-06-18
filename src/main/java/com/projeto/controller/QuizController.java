package com.projeto.controller;

import java.util.List;

import com.projeto.bo.QuizBO;
import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.QuizVO;
import com.projeto.repository.Utils;

public class QuizController {
	QuizBO quizBO = new QuizBO();
	
	public int cadastraQuiz(List<PerguntaVO> quiz, Integer idUsuario) throws ErroNoCadastroException {
		
		String mensagem = "";
		
		if (quiz == null) {
			mensagem += "Erro ao cadastrar Quiz!";
			
		} else if (idUsuario == 0) {
			mensagem += "Erro ao identificar aluno!";
			
		} else if (quiz.size() == 0) {
			mensagem += "Selecione perguntas para o Quiz!";
			
		} else {
			quizBO.cadastraQuiz(quiz, idUsuario);			
		}
		
		if (Utils.stringValida(mensagem)) {
			throw new ErroNoCadastroException(mensagem);			
		}		
		
		return 0;
	}

	public QuizVO validaCodigoQuiz(String codigoQuiz) throws ErroNaConsultaException {			
		return quizBO.validaCodigoQuiz(codigoQuiz);
	}

	public void cadastraResultado(QuizVO quizVO) throws ErroNoCadastroException{
		quizBO.cadastraResultado(quizVO);
	}

}
