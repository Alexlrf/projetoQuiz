package com.projeto.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.projeto.controller.QuizController;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.QuizVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Constants;
import java.awt.Frame;
import java.awt.Toolkit;

public class TelaQuiz extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static int contadorPerguntasRespondidas;
	private int contadorBotoesdeQuestao;
	private static UsuarioVO usuarioVO;
	private static int acertos;	
	private JPanel contentPane;
	private static QuizVO quiz;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaQuiz.class.getResource("/imagens/iconeQuebraCabeca.png")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
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
			acertos = 0;
			JButton btnQuestao = new JButton(contadorBotoesdeQuestao + "");
			formataBotao(btnQuestao);
			btnQuestao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						selecionaPergunta(pergunta);
				}

				private void selecionaPergunta(PerguntaVO pergunta) {
					int opcaoEscolhida = JOptionPane.showConfirmDialog(null, "Deseja responder a pergunta?",
							"CONFIRMAÇÃO DE TENTATIVA ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
					
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
			JOptionPane.showMessageDialog(null, "Quiz Finalizado!\n Número de acertos: "+acertos, null, JOptionPane.INFORMATION_MESSAGE);			
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
			contaPerguntaRespondida();
			
		} else {
			contaPerguntaRespondida();
		}
	}

	private static void contaPerguntaRespondida() {
		contadorPerguntasRespondidas++;
		if (contadorPerguntasRespondidas >= quiz.getPerguntas().size()) {
			contadorPerguntasRespondidas = 0;
			fechaTela();		
		}		
	}
	
	public JButton formataBotao(JButton botao) {

		botao.setBackground(UIManager.getColor("Button.light"));
		botao.setForeground(UIManager.getColor("Button.foreground"));
		botao.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!botao.isEnabled()) {
					botao.setBackground(Color.gray);
				} else {
					
					botao.setBackground(Color.darkGray);
					botao.setForeground(UIManager.getColor("Button.light"));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				if (!botao.isEnabled()) {
					botao.setBackground(Color.gray);
				} else {
					botao.setBackground(UIManager.getColor("Button.light"));
					botao.setForeground(UIManager.getColor("Button.foreground"));					
				
				}
				
			}
		});
		return botao;
	}
}
