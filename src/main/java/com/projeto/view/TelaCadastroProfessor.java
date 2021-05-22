package com.projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class TelaCadastroProfessor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnNewButton_1;
	private JLabel lblTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroProfessor frame = new TelaCadastroProfessor();
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
	public TelaCadastroProfessor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(40, 69, 46, 14);
		contentPane.add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(79, 66, 538, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(40, 112, 46, 14);
		contentPane.add(lblCpf);
		
		textField_1 = new JTextField();
		textField_1.setBounds(79, 109, 538, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento");
		lblDataDeNascimento.setBounds(40, 154, 119, 14);
		contentPane.add(lblDataDeNascimento);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 151, 474, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(40, 236, 46, 14);
		contentPane.add(lblLogin);
		
		textField_3 = new JTextField();
		textField_3.setBounds(79, 233, 538, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(40, 271, 46, 14);
		contentPane.add(lblSenha);
		
		textField_4 = new JTextField();
		textField_4.setBounds(79, 268, 538, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBounds(398, 385, 219, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.setBounds(87, 385, 219, 23);
		contentPane.add(btnNewButton_1);
		
		lblTipo = new JLabel("tipo");
		lblTipo.setBounds(40, 195, 46, 14);
		contentPane.add(lblTipo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(79, 192, 538, 20);
		contentPane.add(comboBox);
	}
}
