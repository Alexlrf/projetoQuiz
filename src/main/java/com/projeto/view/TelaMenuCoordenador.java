package com.projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class TelaMenuCoordenador extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuCoordenador frame = new TelaMenuCoordenador();
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
	public TelaMenuCoordenador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 270, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("GERAL");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmGeral = new JMenuItem("Geral");
		mnNewMenu.add(mntmGeral);
		
		JMenu mnCadastro = new JMenu("ALUNO");
		menuBar.add(mnCadastro);
		
		JMenu mnProfessores = new JMenu("PROFESSOR");
		menuBar.add(mnProfessores);
		
		JMenu mnCoordenador = new JMenu("COORDENADOR");
		menuBar.add(mnCoordenador);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 32, 601, 384);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[][][][][][grow][][][][][][grow][][][][][grow][grow][][][]", "[][][][grow][][][][][][][][][]"));
		
		JLabel lblRelatrioDeAlunos = new JLabel("Relatório de coordenadores");
		lblRelatrioDeAlunos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblRelatrioDeAlunos, "cell 11 0");
		
		JLabel lblNome = new JLabel("Nome");
		panel.add(lblNome, "cell 3 1,growx,aligny bottom");
		
		textField = new JTextField();
		panel.add(textField, "cell 5 1,growx");
		textField.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		panel.add(lblCpf, "cell 15 1,alignx trailing");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "cell 16 1,growx");
		textField_1.setColumns(10);
		
		table = new JTable();
		panel.add(table, "cell 0 2 21 9,grow");
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton, "cell 11 11,growx,aligny top");
		
		JButton btnAlterar = new JButton("Alterar");
		panel.add(btnAlterar, "cell 2 12");
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnExcluir, "cell 19 12");
	}
}
