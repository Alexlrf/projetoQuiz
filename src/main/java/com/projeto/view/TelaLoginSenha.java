package com.projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.placeholder.PlaceholderPasswordField;
import com.projeto.placeholder.PlaceholderTextField;

import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TelaLoginSenha extends JFrame {

	private JPanel contentPane;
	private PlaceholderTextField txtLogin;
	private PlaceholderPasswordField passwordField;

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
		txtLogin.setPlaceholder("login do usuário");
		txtLogin.setToolTipText("Digite o seu Login");
		txtLogin.setColumns(10);
		
		passwordField = new PlaceholderPasswordField();
		passwordField.setPlaceholder("Senha do usuário");
		passwordField.setToolTipText("Digite sua senha");
		
		JButton btnNewButton = new JButton("Entrar");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(241)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
						.addComponent(passwordField, Alignment.TRAILING)
						.addComponent(txtLogin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
					.addGap(268))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(248)
					.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addComponent(btnNewButton)
					.addContainerGap(272, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
