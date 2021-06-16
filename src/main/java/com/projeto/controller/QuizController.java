package com.projeto.controller;

import java.util.List;

import com.projeto.bo.QuizBO;
import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.QuizVO;

public class QuizController {
	QuizBO quizBO = new QuizBO();
	
	public int cadastraQuiz(List<PerguntaVO> quiz, Integer idUsuario) throws ErroNoCadastroException {
		
		return quizBO.cadastraQuiz(quiz, idUsuario);
	}

	public QuizVO validaCodigoQuiz(String codigoQuiz) throws ErroNaConsultaException {			
		return quizBO.validaCodigoQuiz(codigoQuiz);
	}

}
