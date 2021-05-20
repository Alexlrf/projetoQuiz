package com.projeto.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.projeto.enums.UsuarioEnum;
import com.projeto.model.entity.UsuarioVO;

public class TelaMenuProfessor extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private UsuarioVO usuario;

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuProfessor frame = new TelaMenuProfessor();
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
	public TelaMenuProfessor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMenuProfessor.class.getResource("/imagens/iconeQuebraCabeca.png")));
		setTitle("      Q  U  I  Z");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
				
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuGeral = new JMenu("GERAL");
		menuBar.add(menuGeral);
		
		JMenuItem geral = new JMenuItem("Geral");
		geral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane = new PanelGeralProfessor();
				setContentPane(contentPane);
				revalidate();				
			}
		});
		menuGeral.add(geral);
		
		JMenu mnNewMenu = new JMenu("QUESTÕES");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuCadastraQuestao = new JMenuItem("Cadastrar Questão");
		menuCadastraQuestao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				contentPane = new PanelCadastraQuestoes();				
				setContentPane(contentPane);
				revalidate();
			}
		});
		mnNewMenu.add(menuCadastraQuestao);
		
		JMenuItem menuConsultaQuestao = new JMenuItem("Consultar Questões");
		menuConsultaQuestao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contentPane = new PanelConsultaQuestoes();
				setContentPane(contentPane);
				revalidate();
			}
		});
		mnNewMenu.add(menuConsultaQuestao);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate calendario = LocalDate.now();				
		String data = formataData.format(calendario).toString();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(112, 128, 144));
//		String hora = formataHora.toString();
//		lblRelogio.setText(hora);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(215, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
					.addGap(0))
		);
		JLabel lblCalendario = new JLabel();
		panel.add(lblCalendario);
		lblCalendario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCalendario.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCalendario.setText(data);
		lblCalendario.setBackground(new Color(169, 169, 169));
		contentPane.setLayout(gl_contentPane);
	}

	public void abrirTelaCoordenador(UsuarioVO usuario) {
		// TODO Auto-generated method stub
		
	}
}
