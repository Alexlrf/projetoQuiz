package com.projeto.controller;

import java.util.List;
import java.util.Set;

import com.projeto.bo.QuizBO;
import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.QuizVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Utils;
import com.projeto.seletor.QuizSeletor;

public class QuizController {
	QuizBO quizBO = new QuizBO();

	public int cadastraQuiz(Set<PerguntaVO> quiz, Integer idUsuario) throws ErroNoCadastroException {
		int idQuiz = 0;
		String mensagem = "";

		if (quiz == null) {
			mensagem += "Erro ao cadastrar Quiz!";

		} else if (idUsuario == 0) {
			mensagem += "Erro ao identificar aluno!";

		} else if (quiz.size() == 0) {
			mensagem += "Selecione perguntas para o Quiz!";

		} else {
			idQuiz = quizBO.cadastraQuiz(quiz, idUsuario);
		}

		if (Utils.stringValida(mensagem)) {
			throw new ErroNoCadastroException(mensagem);
		}

		return idQuiz;
	}

	public QuizVO validaCodigoQuiz(String codigoQuiz) throws ErroNaConsultaException {
		return quizBO.validaCodigoQuiz(codigoQuiz);
	}

	public void cadastraResultado(QuizVO quizVO) throws ErroNoCadastroException {
		quizBO.cadastraResultado(quizVO);
	}

	public List<QuizVO> consultaTodosQuizes(UsuarioVO usuarioLogado) throws ErroNaConsultaException {

		return quizBO.consultaTodosQuizes(usuarioLogado);
	}

	public List<QuizSeletor> buscaResultadosQuiz(int idQuizBusca) throws ErroNaConsultaException {

		return quizBO.buscaResultadosQuiz(idQuizBusca);
	}

}
