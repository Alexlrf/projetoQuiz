package com.projeto.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.projeto.controller.QuizController;
import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.QuizVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;

public class PanelQuiz extends JPanel {
	private static final long serialVersionUID = 1L;

	private QuizController quizController = new QuizController();
	private QuizVO quizVO = new QuizVO();

	private String codigoQuiz;

	/**
	 * Create the panel.
	 */
	public PanelQuiz(UsuarioVO alunoLogado) {

		boolean comeco = iniciaQuiz(alunoLogado);

		if (comeco) {
			validaCodigoDigitado(codigoQuiz, alunoLogado);
		}

	}

	private boolean iniciaQuiz(UsuarioVO alunoLogado) {
		JOptionPane.showMessageDialog(null,
				" Bem Vindo  " + alunoLogado.getNome().toUpperCase() + "!\n\n Vamos começar?\n",
				null, JOptionPane.QUESTION_MESSAGE);

		codigoQuiz = JOptionPane.showInputDialog(null, " Digite o código do quiz", "CÓDIGO DO QUIZ ",
				JOptionPane.QUESTION_MESSAGE);

		while (!Utils.stringValida(codigoQuiz)) {
			int opcao = JOptionPane.showConfirmDialog(null, " Quer prosseguir?", null,
					JOptionPane.OK_CANCEL_OPTION);
			if (opcao == JOptionPane.YES_OPTION) {
				codigoQuiz = JOptionPane.showInputDialog(null, " Digite o código do quiz", "CÓDIGO DO QUIZ ",
						JOptionPane.QUESTION_MESSAGE);
			} else {
				return false;
			}
		}
		return true;
	}

	private void validaCodigoDigitado(String codigoQuiz, UsuarioVO alunoLogado) {

		try {
			quizVO = quizController.validaCodigoQuiz(codigoQuiz);
			if (quizVO != null) {
				JOptionPane.showMessageDialog(null, "Vamos lá !!!", "BOA SORTE !!!", 
						JOptionPane.PLAIN_MESSAGE);
				TelaQuiz telaQuiz = new TelaQuiz(alunoLogado, quizVO);
				telaQuiz.setVisible(true);
			}

		} catch (ErroNaConsultaException mensagem) {
			JOptionPane.showMessageDialog(null, mensagem.getMessage(), Constants.ALERTA, JOptionPane.ERROR_MESSAGE,
					null);
		}
	}

}
