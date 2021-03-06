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
	private JMenuItem menuItemCadastrarProfessor;
	private JMenuItem menuItemCadastrarCoordenador;
	private JMenuItem menuItemCadastrarAluno;
	private JMenu menuQuestoes;
	private JMenuItem menuItemCadastraQuestao;
	private JMenuItem menuItemConsultaQuestao;
	private JMenu menuResolverQuiz;
	private JMenuItem menuItemResponderQuiz;
	private JMenu mnSair;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmQuiz;

	private JMenuItem menuItemConsultaQuiz;

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

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
		this.TelaPrincipal();
	}


	public TelaPrincipal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		this.TelaPrincipal();
		setExtendedState(Frame.MAXIMIZED_BOTH);
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

		contentPane = new Home();
		setContentPane(contentPane);
		revalidate();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);		

		menuResolverQuiz = new JMenu("RESOLVER QUEST??ES");
		menuBar.add(menuResolverQuiz);

		menuItemResponderQuiz = new JMenuItem("Responder Quiz");
		menuItemResponderQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane = new PanelQuiz((AlunoVO) usuario);				
				setContentPane(contentPane);
				revalidate();
			}
		});
		menuResolverQuiz.add(menuItemResponderQuiz);

		menuQuestoes = new JMenu("QUEST??ES");
		menuBar.add(menuQuestoes);

		menuItemCadastraQuestao = new JMenuItem("Cadastrar Quest??o");
		menuItemCadastraQuestao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				contentPane = new PanelCadastraQuestoes((ProfessorVO) usuario);				
				setContentPane(contentPane);
				revalidate();
			}
		});
		menuQuestoes.add(menuItemCadastraQuestao);

		menuItemConsultaQuestao = new JMenuItem("Consultar Quest??es");
		menuBar.add(menuQuestoes);
		
		menuItemConsultaQuestao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				contentPane = new PanelConsultaQuestoes(usuario);
				setContentPane(contentPane);
				revalidate();
			}
		});

		menuQuestoes.add(menuItemConsultaQuestao);		
		
		menuItemConsultaQuiz = new JMenu("CONSULTA QUIZ");		
		menuBar.add(menuItemConsultaQuiz);
		
		mntmQuiz = new JMenuItem("Consultar");
		mntmQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane = new PanelConsultaQuiz(usuario);
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				revalidate();
			}
		});
		
		menuItemConsultaQuiz.add(mntmQuiz);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		menuRelatorioDeUsuarios = new JMenu("RELAT??RIO");
		menuBar.add(menuRelatorioDeUsuarios);

		menuItemRelatorioDeUsuarios = new JMenuItem("Relat??rio de Usu??rios");
		menuItemRelatorioDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelRelatorioDeUsuario painel = new PanelRelatorioDeUsuario(); 
				contentPane = painel;

				painel.getBtnAlterar().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						UsuarioVO usuarioSelecionado = painel.obterUsuarioSelecionado();
						int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Voc?? deseja alterar o(a) " + usuarioSelecionado.getNome() + "?", 
								"Alterar Usu??rio", JOptionPane.YES_NO_OPTION);
						
						if (showConfirmDialog == JOptionPane.OK_OPTION) {
							if(usuarioSelecionado instanceof AlunoVO) {
								AlunoVO aluno = (AlunoVO) usuarioSelecionado;
								contentPane = new PanelCadastrarAluno(aluno);
								
							} else if (usuarioSelecionado instanceof ProfessorVO) {
								ProfessorVO professor = (ProfessorVO) usuarioSelecionado;
								contentPane = new PanelCadastrarProfessor(professor);
								
							} else if (usuarioSelecionado instanceof CoordenadorVO) {
								CoordenadorVO coordenador = (CoordenadorVO) usuarioSelecionado;
								contentPane = new PanelCadastrarCoordenador(coordenador);
							}
							setContentPane(contentPane);
							revalidate();
						}
					}
				});
				setContentPane(contentPane);
				revalidate();
			}
		});
		menuRelatorioDeUsuarios.add(menuItemRelatorioDeUsuarios);

		menuCadastroDeUsuarios = new JMenu("CADASTRO DE USU??RIOS");
		menuBar.add(menuCadastroDeUsuarios);

		menuItemCadastrarProfessor = new JMenuItem("CadastrarProfessor");
		menuItemCadastrarProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane = new PanelCadastrarProfessor();
				setContentPane(contentPane);
				revalidate();
			}
		});
		menuCadastroDeUsuarios.add(menuItemCadastrarProfessor);

		menuItemCadastrarCoordenador = new JMenuItem("Cadastrar Coordenador");
		menuItemCadastrarCoordenador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane = new PanelCadastrarCoordenador();
				setContentPane(contentPane);
				revalidate();
			}
		});
		menuCadastroDeUsuarios.add(menuItemCadastrarCoordenador);

		menuItemCadastrarAluno = new JMenuItem("Cadastrar Aluno");
		menuItemCadastrarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane = new PanelCadastrarAluno();
				setContentPane(contentPane);
				revalidate();
			}
		});
		menuCadastroDeUsuarios.add(menuItemCadastrarAluno);

		mnSair = new JMenu("S A I R");		
		menuBar.add(mnSair);

		mntmNewMenuItem = new JMenuItem("S A I R ");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLoginSenha loginSenha = new TelaLoginSenha();
				loginSenha.setVisible(true);
				dispose();
			}
		});
		mnSair.add(mntmNewMenuItem);
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
			this.abrirTelaAluno(aluno);

		} else if (usuario instanceof ProfessorVO) {
			ProfessorVO professor = (ProfessorVO) usuario;
			this.abrirTelaProfessor(professor);

		} else if (usuario instanceof CoordenadorVO) {
			CoordenadorVO coordenador = (CoordenadorVO) usuario;
			this.abrirTelaCoordenador(coordenador);
		}
	}

	public void abrirTelaAluno(AlunoVO aluno) {
		// visibilidades somente do aluno
		menuResolverQuiz.setVisible(true);

		// visibilidades negadas para o aluno
		menuQuestoes.setVisible(false);
		menuRelatorioDeUsuarios.setVisible(false);
		menuCadastroDeUsuarios.setVisible(false);
		menuItemConsultaQuiz.setVisible(false);
	}

	public void abrirTelaProfessor(ProfessorVO professor) {
		// visibilidades somente do professor
		menuQuestoes.setVisible(true);
		menuItemConsultaQuiz.setVisible(true);		

		// visibilidades negadas para o professor
		menuResolverQuiz.setVisible(false);
		menuRelatorioDeUsuarios.setVisible(false);
		menuCadastroDeUsuarios.setVisible(false);
	}

	public void abrirTelaCoordenador(CoordenadorVO coordenador) {
		// visibilidades somente do coordenador
		menuRelatorioDeUsuarios.setVisible(true);
		menuCadastroDeUsuarios.setVisible(true);

		// visibilidades negadas para o coordenador
		menuQuestoes.setVisible(false);
		menuResolverQuiz.setVisible(false);
		menuItemConsultaQuiz.setVisible(false);
	}
}
