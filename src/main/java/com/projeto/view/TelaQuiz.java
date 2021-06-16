package com.projeto.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
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

public class TelaQuiz extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private static UsuarioVO usuario;
	private static QuizVO quiz;
	private int contadorQuestoes;
	private static int acertos;	

	public  int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}
	
	public static void acertosQuiz(boolean retornoQuestao) {
		if (retornoQuestao) {
			acertos++;
		} 
	}

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

		contadorQuestoes = 1;
		
		for (PerguntaVO pergunta : quizVO2.getPerguntas()) {

			JButton btnQuestao = new JButton(contadorQuestoes + "");
			btnQuestao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					int opcaoEscolhida = JOptionPane.showConfirmDialog(null, "Deseja responder a pergunta?",
							"INCLUSÃO DE PERGUNTA ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);

					if (opcaoEscolhida == JOptionPane.YES_OPTION) {
						TelaAlternativas telaAlternativas = new TelaAlternativas(pergunta.getListaAlternativas(), pergunta);
						telaAlternativas.setVisible(true);
						btnQuestao.setBackground(Color.gray);
						btnQuestao.setEnabled(false);
					}
					System.out.println("Vc acertou "+acertos);
				}
			});
			getContentPane().add(btnQuestao);
			contadorQuestoes++;
		}
		JOptionPane.showMessageDialog(null, "Quiz Finalizado!\n\n Obrigado!");
		this.dispose();
	}
}
