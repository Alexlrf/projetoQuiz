package com.projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.awt.event.InputEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TelaMenuProfessor extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
		setTitle("Q  U  I  Z");
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
		mnNewMenu.add(menuConsultaQuestao);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate calendario = LocalDate.now();
		Instant relogioHora = Instant.now();
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText(dataFormatter.format(calendario).toString());
		lblNewLabel.setBackground(new Color(169, 169, 169));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel.setText(relogioHora.toString());
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(207, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(149, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
