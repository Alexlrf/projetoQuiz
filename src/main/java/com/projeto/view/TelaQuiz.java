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

import com.projeto.controller.QuizController;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.QuizVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Constants;

import javax.swing.JLabel;

public class TelaQuiz extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private static UsuarioVO usuarioVO;
	private static QuizVO quiz;
	private int contadorBotoesdeQuestao;
	private static int acertos;	
	private static int contadorPerguntasRespondidas;

	public static  int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaQuiz frame = new TelaQuiz(usuarioVO, quiz);
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
		TelaQuiz.quiz = quiz;
		usuarioVO = usuario;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		geraQuiz(usuario, quiz);

	}

	protected void geraQuiz(UsuarioVO alunoLogado, QuizVO quizVO) {
		
		contadorBotoesdeQuestao = 1;
		
		for (PerguntaVO pergunta : quizVO.getPerguntas()) {

			JButton btnQuestao = new JButton(contadorBotoesdeQuestao + "");
			btnQuestao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						selecionaPergunta(pergunta);						

				}

				private void selecionaPergunta(PerguntaVO pergunta) {
					int opcaoEscolhida = JOptionPane.showConfirmDialog(null, "Deseja responder a pergunta?",
							"INCLUSÃO DE PERGUNTA ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
					
					if (opcaoEscolhida == JOptionPane.YES_OPTION) {						
							TelaAlternativas telaAlternativas = new TelaAlternativas(pergunta.getListaAlternativas(), pergunta);
							telaAlternativas.setVisible(true);
							btnQuestao.setBackground(Color.gray);
							btnQuestao.setEnabled(false);						
					}
				}
			});
			getContentPane().add(btnQuestao);
			contadorBotoesdeQuestao++;
		}
	}
	
	protected static void fechaTela() {
			JOptionPane.showMessageDialog(null, "Quiz Finalizado!\n Você acertou: "+getAcertos()+" Questões");
			cadastraResultado();
	}

	private static void cadastraResultado() {
		
		QuizController quizController = new QuizController();
		QuizVO quizVO = new QuizVO();
		quizVO.setAluno(usuarioVO);
		quizVO.setIdQuiz(quiz.getIdQuiz());
		quizVO.setAcertos(acertos);
		
		try {
			quizController.cadastraResultado(quizVO);
		} catch (ErroNoCadastroException mensagem) {
			JOptionPane.showMessageDialog(null, mensagem.getMessage(), Constants.ALERTA, JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public static void acertosQuiz(boolean retornoQuestao, int contador) {
		if (retornoQuestao) {
			acertos++;
			contadorPerguntasRespondidas++;
			if (contadorPerguntasRespondidas >= quiz.getPerguntas().size()) {
				fechaTela();		
			}
		} else {
			contadorPerguntasRespondidas++;	
		}
	}
}
