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

import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.QuizVO;
import com.projeto.model.entity.UsuarioVO;

public class TelaQuiz extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private static UsuarioVO usuario;
	private static QuizVO quiz;
	private int contadorBotoesdeQuestao;
	private static int acertos;	
	private int contadorPerguntasRespondidas;

	public  int getAcertos() {
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
		TelaQuiz.quiz = quiz;
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
					System.out.println("Acertou fora: "+acertos+" - "+contadorPerguntasRespondidas+" - "+pergunta.getIdPergunta());
					contadorPerguntasRespondidas++;	
					if (contadorPerguntasRespondidas <= quizVO.getPerguntas().size()) {
						selecionaPergunta(pergunta);
						
						System.out.println("Acertou dentro: "+acertos+" - "+contadorPerguntasRespondidas+" - "+pergunta.getIdPergunta());
							
					}else {
						fechaTela();							
					}
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
	
	protected void fechaTela() {
		JOptionPane.showMessageDialog(null, "Quiz Finalizado!\n Você acertou: "+getAcertos()+" Questões");
		this.dispose();
		
	}

	public static void acertosQuiz(boolean retornoQuestao) {
		if (retornoQuestao) {
			acertos++;
			System.out.println(acertos+" dentro do metodo");
		} 		
	}
}
