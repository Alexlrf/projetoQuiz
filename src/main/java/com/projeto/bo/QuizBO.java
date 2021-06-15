package com.projeto.bo;

import java.util.List;

import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.dao.QuizDAO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Utils;

public class QuizBO {
	QuizDAO quizDAO = new QuizDAO();

	public int cadastraQuiz(List<PerguntaVO> quiz, Integer idUsuario) throws ErroNoCadastroException {
		String mensagem = "";
		
		int idQuiz = quizDAO.cadastraCodigoQuiz(idUsuario);
		
		if (idQuiz != 0) {
			if (!quizDAO.cadastraQuiz(idQuiz, quiz, idUsuario)) {
				mensagem = "Erro ao cadastrar perguntas!";
			}
			
		} else {
			mensagem = "Erro ao identificar usuário!";
		}
		
		if (Utils.stringValida(mensagem)) {
			throw new ErroNoCadastroException(mensagem);
		}		
		return idQuiz;
	}
}
