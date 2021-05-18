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
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLoginSenha.class.getResource("/imagens/iconeQuebraCabeca.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel JPanelBackground = new JPanel();
		JPanelBackground.setBorder(new LineBorder(new Color(250, 128, 114), 5));
		JPanelBackground.setBackground(new Color(112, 128, 144));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setMaximumSize(new Dimension(300, 300));
		
		
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		cbxMostrarSenha.setBackground(new Color(0, 102, 153));
		cbxMostrarSenha.setFont(new Font("Tahoma", Font.PLAIN, 9));
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
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
								.addComponent(txtLogin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
								.addComponent(cbxMostrarSenha))))
					.addGap(24))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(70)
					.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxMostrarSenha)
					.addGap(20)
					.addComponent(btnNewButton)
					.addGap(44))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(JPanelBackground, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(JPanelBackground, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		FlowLayout fl_JPanelBackground = new FlowLayout(FlowLayout.CENTER, 20, 190);
		JPanelBackground.setLayout(fl_JPanelBackground);
		JPanelBackground.add(panel);
		contentPane.setLayout(gl_contentPane);
		
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
