package com.projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.QuizVO;
import com.projeto.model.entity.UsuarioVO;
import java.awt.GridLayout;

public class TelaQuiz extends JFrame {

	private JPanel contentPane;
	private static UsuarioVO usuario;
	private static QuizVO quiz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaQuiz frame = new TelaQuiz(usuario, quiz);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaQuiz(UsuarioVO usuario, QuizVO quiz) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		geraQuiz(usuario, quiz);
		
	}
	
		private void geraQuiz(UsuarioVO alunoLogado, QuizVO quizVO2) {
			
			int contadorQuestoes = 1;
			
			for (PerguntaVO pergunta : quizVO2.getPerguntas()) {
				
				JButton btnNewButton = new JButton(contadorQuestoes + "");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int opcaoEscolhida = JOptionPane.showConfirmDialog(null, "Deseja responder a pergunta?",
								"INCLUSÃO DE PERGUNTA ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
								null);

						if (opcaoEscolhida == JOptionPane.YES_OPTION) {
							TelaAlternativas telaAlternativas = new TelaAlternativas(pergunta.getListaAlternativas());
							telaAlternativas.setVisible(true);
						} 
						
						
					}
				});
				getContentPane().add(btnNewButton);
				contadorQuestoes++;
			}		
		
		}	


}
