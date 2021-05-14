package com.projeto.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.controller.VerificarLoginController;
import com.projeto.enums.UsuarioEnum;
import com.projeto.exceptions.LoginNaoInformadoException;
import com.projeto.exceptions.SenhaNaoInformadaException;
import com.projeto.exceptions.UsuarioNaoExistenteException;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.placeholder.PlaceholderPasswordField;
import com.projeto.placeholder.PlaceholderTextField;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaLoginSenha extends JFrame {

	private JPanel contentPane;
	private PlaceholderTextField txtLogin;
	private PlaceholderPasswordField passwordField;
	private JCheckBox cbxMostrarSenha;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLoginSenha frame = new TelaLoginSenha();
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
	public TelaLoginSenha() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel JPanelBackground = new JPanel();
		JPanelBackground.setBounds(10, 11, 828, 659);
		contentPane.add(JPanelBackground);
		JPanelBackground.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 828, 659);
		JPanelBackground.add(panel);
		
		txtLogin = new PlaceholderTextField();
		txtLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evento) {
				if (evento.getKeyChar() == KeyEvent.VK_ENTER) {
					verificarLogin();
				}
			}
		});
		txtLogin.setPlaceholder("Digite o seu login");
		txtLogin.setToolTipText("Digite o seu Login");
		txtLogin.setColumns(10);
		
		passwordField = new PlaceholderPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evento) {
				if (evento.getKeyChar() == KeyEvent.VK_ENTER) {
					verificarLogin();
				}
			}
		});
		passwordField.setPlaceholder("Digite sua senha");
		passwordField.setToolTipText("Digite sua senha");
		
		btnNewButton = new JButton("Entrar");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evento) {
				if (evento.getKeyChar() == KeyEvent.VK_ENTER) {
					verificarLogin();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verificarLogin();
			}
		});
		
		cbxMostrarSenha = new JCheckBox("Mostrar senha");
		cbxMostrarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbxMostrarSenha.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar('•');
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(241)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cbxMostrarSenha, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(461))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
								.addComponent(passwordField, Alignment.TRAILING, 319, 319, 319)
								.addComponent(txtLogin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
							.addGap(268))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(248)
					.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(cbxMostrarSenha)
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(272, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}

	protected void verificarLogin() {
		UsuarioVO usuario = new UsuarioVO();
		usuario.setLogin(txtLogin.getText());
		usuario.setSenha(new String(passwordField.getPassword()));
		
		VerificarLoginController verificacaoLogin = new VerificarLoginController();
		
		try {
			usuario = verificacaoLogin.verificarLoginController(usuario.getLogin(), usuario.getSenha());
			this.verificarTipoUsuario(usuario);
		} catch (UsuarioNaoExistenteException | LoginNaoInformadoException | SenhaNaoInformadaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Login", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void verificarTipoUsuario(UsuarioVO usuario) {
		
		// chama a tela do professor
		if (usuario.getTipo().equals(UsuarioEnum.PROFESSOR)) {
			TelaMenuProfessor telaProfessor = new TelaMenuProfessor();
			telaProfessor.setVisible(true);
			dispose();
			
			// chama a tela do coordenador
		} else if (usuario.getTipo().equals(UsuarioEnum.COORDENACAO)) {
			// TODO fazer tela de coordenação
			JOptionPane.showMessageDialog(null, "Olá coordenador(a), sua tela ainda esta em construção", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			
			// chama a tela do aluno
		} else if (usuario.getTipo().equals(UsuarioEnum.ALUNO)) {
			// TODO fazer tela do aluno
			JOptionPane.showMessageDialog(null, "Olá aluno, sua tela ainda esta em construção", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
