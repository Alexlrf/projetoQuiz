package com.projeto.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import com.projeto.controller.UsuarioController;
import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.exceptions.CpfNaoInformadoException;
import com.projeto.exceptions.SenhaIncorretaException;
import com.projeto.exceptions.SenhaNaoInformadaException;
import com.projeto.exceptions.UsuarioNaoExistenteException;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.placeholder.PlaceholderPasswordField;
import com.projeto.placeholder.PlaceholderTextField;
import javax.swing.JLabel;
import java.awt.Frame;

public class TelaLoginSenha extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JFormattedTextField txtCpf;
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
		setExtendedState(Frame.MAXIMIZED_BOTH);
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
		
		
		MaskFormatter mascaraCpf;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCpf = new JFormattedTextField(mascaraCpf);
		} catch (ParseException e) {
			System.out.println("Erro ao formatar mascara de Cpf: " + e.getMessage());
		}
		txtCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evento) {
				if (evento.getKeyChar() == KeyEvent.VK_ENTER) {
					verificarLogin();
				}
			}
		});
		txtCpf.setToolTipText("Digite o seu CPF");
		txtCpf.setColumns(10);
		
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
		
		JLabel lblCpf = new JLabel("CPF");
		
		JLabel lblSenha = new JLabel("Senha");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
								.addComponent(cbxMostrarSenha)
								.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
								.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSenha))))
					.addGap(24))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(32)
					.addComponent(lblCpf)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblSenha)
					.addGap(3)
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

	protected void verificarLogin(){
		UsuarioController usuarioController = new UsuarioController();
		
		String cpf = txtCpf.getText().replace(".", "").replace("-", "");
		String senha = new String(passwordField.getPassword());
		
		try {
			UsuarioVO usuario = usuarioController.verificarLoginController(cpf, senha);
			dispose();
			
		} catch (UsuarioNaoExistenteException | CpfNaoInformadoException | SenhaNaoInformadaException | SenhaIncorretaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Login", JOptionPane.ERROR_MESSAGE);
		}
	}
}
