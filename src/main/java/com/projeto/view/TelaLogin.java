package com.projeto.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TelaLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private final JPanel panelCentralDados = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/imagens/iconeQuebraCabeca.png")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JOptionPane.showMessageDialog(null, "Digite "
				+ "\nUSUARIO = admin (Para acessar tela de cadastro de perguntas)"
				+ "\nUSUARIO = aluno (Para acessar a tela de questões) ");
		
		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(0, 0, 1368, 705);
		contentPane.add(panelBackground);
		panelBackground.setLayout(new BorderLayout(0, 0));
		
		PanelImagemBackground panelTeste = new PanelImagemBackground();
		panelBackground.add(panelTeste);
		panelCentralDados.setBorder(new LineBorder(new Color(75, 0, 130), 20, true));
		panelCentralDados.setBackground(new Color(25, 25, 112));
		GroupLayout gl_panelTeste = new GroupLayout(panelTeste);
		gl_panelTeste.setHorizontalGroup(
			gl_panelTeste.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTeste.createSequentialGroup()
					.addGap(498)
					.addComponent(panelCentralDados, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(430, Short.MAX_VALUE))
		);
		gl_panelTeste.setVerticalGroup(
			gl_panelTeste.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTeste.createSequentialGroup()
					.addGap(226)
					.addComponent(panelCentralDados, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(195, Short.MAX_VALUE))
		);
		
		JFormattedTextField frmtdtxtfldUsuario = new JFormattedTextField();
		frmtdtxtfldUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (frmtdtxtfldUsuario.getText().equalsIgnoreCase("USUÁRIO")) {
					frmtdtxtfldUsuario.setText("");
				}
			}
		});
		frmtdtxtfldUsuario.setForeground(new Color(128, 128, 128));
		frmtdtxtfldUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		frmtdtxtfldUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frmtdtxtfldUsuario.getText().equalsIgnoreCase("USUÁRIO")) {
					frmtdtxtfldUsuario.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (frmtdtxtfldUsuario.getText().isEmpty()) {
					frmtdtxtfldUsuario.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (frmtdtxtfldUsuario.getText().trim().isEmpty()) {
					frmtdtxtfldUsuario.setText("USUÁRIO");
				}
			}		
		});
		
		frmtdtxtfldUsuario.setText("USUÁRIO");
		
		JButton btnLogar = new JButton("ENTRAR");
		btnLogar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogar.setToolTipText("Logar");
		btnLogar.setForeground(Color.DARK_GRAY);
		btnLogar.setBackground(Color.LIGHT_GRAY);
		btnLogar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogar.setBackground(Color.DARK_GRAY);
				btnLogar.setForeground(Color.LIGHT_GRAY);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogar.setForeground(Color.DARK_GRAY);
				btnLogar.setBackground(Color.LIGHT_GRAY);

			}
		});
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (frmtdtxtfldUsuario.getText().equalsIgnoreCase("admin")) {
					TelaMenuProfessor telaMenuProfessor = new TelaMenuProfessor();						
					telaMenuProfessor.setVisible(true);					
					dispose();					
					
				}else if(frmtdtxtfldUsuario.getText().equalsIgnoreCase("aluno")) {
					TelaQuestoes questoes = new TelaQuestoes();
					questoes.setVisible(true);
					dispose();
				}
			}
		});
		GroupLayout gl_panelCentralDados = new GroupLayout(panelCentralDados);
		gl_panelCentralDados.setHorizontalGroup(
			gl_panelCentralDados.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralDados.createSequentialGroup()
					.addGroup(gl_panelCentralDados.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentralDados.createSequentialGroup()
							.addGap(130)
							.addComponent(btnLogar, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCentralDados.createSequentialGroup()
							.addGap(32)
							.addComponent(frmtdtxtfldUsuario, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_panelCentralDados.setVerticalGroup(
			gl_panelCentralDados.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralDados.createSequentialGroup()
					.addGap(68)
					.addComponent(frmtdtxtfldUsuario, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(95)
					.addComponent(btnLogar)
					.addGap(28))
		);
		panelCentralDados.setLayout(gl_panelCentralDados);
		panelTeste.setLayout(gl_panelTeste);
	}
}
