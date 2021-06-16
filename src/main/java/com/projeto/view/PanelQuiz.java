package com.projeto.view;

import javax.swing.JPanel;

import com.projeto.controller.QuizController;
import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.QuizVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelQuiz extends JPanel {
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
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnNewButton);

	}

	private boolean iniciaQuiz(UsuarioVO alunoLogado) {
		JOptionPane.showMessageDialog(null, "Bem Vindo  "+alunoLogado.getNome().toUpperCase()+"!\nVamos começar?");
		
		codigoQuiz = JOptionPane.showInputDialog(null, "Digite o código do quiz!");
		
		while(!Utils.stringValida(codigoQuiz)) {
			int opcao = JOptionPane.showConfirmDialog(null, "Quer prosseguir?");
			if (opcao == JOptionPane.YES_OPTION) {
				codigoQuiz = JOptionPane.showInputDialog(null, "Digite o código do quiz!");				
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
				JOptionPane.showMessageDialog(null, " Vamos Lá !!!", Constants.SUCESSO,
						JOptionPane.INFORMATION_MESSAGE);
				
				System.out.println("ID Quiz -> "+quizVO.getIdQuiz()+"  | Aluno = "+alunoLogado.getNome());
				System.out.println("=====================================================");
				  for (PerguntaVO pergunta : quizVO.getPerguntas()) {
					  System.out.println("=====================================================");
					  System.out.println("=====================================================");
					  System.out.println(pergunta.getTextoPergunta()+"\n");
					  for (AlternativaVO alternativa: pergunta.getListaAlternativas()) {
						  System.out.println(alternativa.getTexto()+" - "+alternativa.getAlternativaCorreta()+"\n");
					}
				}
										
				
			}
			
			
		} catch (ErroNaConsultaException mensagem) {
			JOptionPane.showMessageDialog(null, mensagem.getMessage(), Constants.ALERTA,
					JOptionPane.ERROR_MESSAGE, null);
		}	

		
	}

}
