package com.projeto.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.dao.QuizDAO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.QuizVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Utils;
import com.projeto.seletor.QuizSeletor;

public class QuizBO {
	QuizDAO quizDAO = new QuizDAO();

	public int cadastraQuiz(Set<PerguntaVO> quiz, Integer idUsuario) throws ErroNoCadastroException {
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

	public QuizVO validaCodigoQuiz(String codigoQuiz) throws ErroNaConsultaException {
		QuizVO quizVO = new QuizVO();
		String mensagem = "";
		int idQuizSelecionado = Integer.parseInt(codigoQuiz);

		int idRetornado = quizDAO.validaCodigoQuiz(idQuizSelecionado);
		if (idRetornado == 0) {
			mensagem += "Erro ao obter código do Quiz!\n";
		} else {
			List<PerguntaVO> listaPerguntas = new ArrayList<>();
			listaPerguntas = quizDAO.consultaPerguntasQuiz(idRetornado);
			if (listaPerguntas == null || listaPerguntas.size() == 0) {
				mensagem += "Erro ao obter perguntas do Quiz!\n";
			} else {
				quizVO.setIdQuiz(idRetornado);
				quizVO.setPerguntas(listaPerguntas);
			}
		}
		if (Utils.stringValida(mensagem)) {
			throw new ErroNaConsultaException(mensagem);
		}

		return quizVO;
	}

	public void cadastraResultado(QuizVO quizVO) throws ErroNoCadastroException {
		quizDAO.cadastraResultado(quizVO);

	}

	public List<QuizVO> consultaTodosQuizes(UsuarioVO usuarioLogado) throws ErroNaConsultaException {
		return quizDAO.consultaTodosQuizes(usuarioLogado);
	}

	public List<QuizSeletor> buscaResultadosQuiz(int idQuizBusca) throws ErroNaConsultaException {
		return quizDAO.buscaResultadosQuiz(idQuizBusca);
	}
}
