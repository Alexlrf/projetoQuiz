package com.projeto.controller;

import java.util.List;

import com.projeto.bo.QuizBO;
import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.PerguntaVO;

public class QuizController {
	QuizBO quizBO = new QuizBO();
	
	public int cadastraQuiz(List<PerguntaVO> quiz, Integer idUsuario) throws ErroNoCadastroException {
		
		return quizBO.cadastraQuiz(quiz, idUsuario);
	}

	public int validaCodigoQuiz(String codigoQuiz) throws ErroNaConsultaException {
		
		return 0;
	}

}
