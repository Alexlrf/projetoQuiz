package com.projeto.view;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.CoordenadorVO;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.model.entity.UsuarioVO;

public class TelaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private UsuarioVO usuario;
	private JMenu menuRelatorioDeUsuarios;
	private JMenuItem menuItemRelatorioDeUsuarios;
	private JMenu menuCadastroDeUsuarios;
	private JMenuItem menuItemCadastrarprofessor;
	private JMenuItem menuItemCadastrarCoordenador;
	private JMenuItem menuItemCadastrarAluno;
	private JMenu mnQuestoes;
	private JMenuItem menuCadastraQuestao;
	private JMenuItem menuConsultaQuestao;
	private JMenu menuResolverQuiz;
	private JMenuItem menuItemResponderQuiz;
	
	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
		TelaPrincipal();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public void TelaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/imagens/iconeQuebraCabeca.png")));
		setTitle("      Q  U  I  Z");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
				
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);		

		menuResolverQuiz = new JMenu("RESOLVER QUESTÕES");
		
		menuItemResponderQuiz = new JMenuItem("Responder Quiz");
		menuResolverQuiz.add(menuItemResponderQuiz);
		
		mnQuestoes = new JMenu("QUESTÕES");
		
		menuCadastraQuestao = new JMenuItem("Cadastrar Questão");
		menuCadastraQuestao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				contentPane = new PanelCadastraQuestoes(usuario);				
				setContentPane(contentPane);
				revalidate();
			}
		});
		mnQuestoes.add(menuCadastraQuestao);
		
		menuConsultaQuestao = new JMenuItem("Consultar Questões");
		menuConsultaQuestao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contentPane = new PanelConsultaQuestoes(usuario);
				setContentPane(contentPane);
				revalidate();
			}
		});

		mnQuestoes.add(menuConsultaQuestao);
		
		menuRelatorioDeUsuarios = new JMenu("RELATÓRIO");
		
		menuItemRelatorioDeUsuarios = new JMenuItem("Relatório de Usuários");
		menuRelatorioDeUsuarios.add(menuItemRelatorioDeUsuarios);
		
		menuCadastroDeUsuarios = new JMenu("CADASTRO DE USUÁRIOS");
		
		menuItemCadastrarprofessor = new JMenuItem("CadastrarProfessor");
		menuCadastroDeUsuarios.add(menuItemCadastrarprofessor);
		
		menuItemCadastrarCoordenador = new JMenuItem("Cadastrar Coordenador");
		menuCadastroDeUsuarios.add(menuItemCadastrarCoordenador);
		
		menuItemCadastrarAluno = new JMenuItem("Cadastrar Aluno");
		menuCadastroDeUsuarios.add(menuItemCadastrarAluno);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 229, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
		
		if(usuario instanceof AlunoVO) {
			AlunoVO aluno = (AlunoVO) usuario;
			
			menuBar.add(menuResolverQuiz);
			
		} else if (usuario instanceof ProfessorVO) {
			ProfessorVO professor = (ProfessorVO) usuario;
			
			menuBar.add(mnQuestoes);
			
		} else if (usuario instanceof CoordenadorVO) {
			CoordenadorVO coordenador = (CoordenadorVO) usuario;
			
			menuBar.add(menuRelatorioDeUsuarios);
			menuBar.add(menuCadastroDeUsuarios);
		}
	}

	public void abrirTelaCoordenador(CoordenadorVO coordenador) {
		
		
	}
}
