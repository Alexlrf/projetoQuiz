package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PanelCadastrarUsuario extends JPanel {
	private JTextField txtNome;
	private JTextField txtCpf;
	private final ButtonGroup buttonGroupSexo = new ButtonGroup();
	private final ButtonGroup buttonGroupDeficiencia = new ButtonGroup();
	private JTextField txtRg;
	private JTextField txtCelular;
	private JTextField txtNacionalidade;
	private JPasswordField pswSenha;
	private JPasswordField pswConfirmarSenha;
	private JTextField txtDataNascimento;

	/**
	 * Create the panel.
	 */
	public PanelCadastrarUsuario() {
		setBackground(new Color(205, 92, 92));
		
		JPanel panel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblCadastrarAluno = new JLabel("Cadastrar Aluno");
		lblCadastrarAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarAluno.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblRg = new JLabel("RG:");
		
		txtRg = new JTextField();
		txtRg.setColumns(10);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		
		txtDataNascimento = new JTextField();
		txtDataNascimento.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		
		JRadioButton btnMasculino = new JRadioButton("Masculino");
		buttonGroupSexo.add(btnMasculino);
		
		JRadioButton btnFeminino = new JRadioButton("Feminino");
		buttonGroupSexo.add(btnFeminino);
		
		JLabel lblPossuiDeficiencia = new JLabel("Possui alguma deficiência?");
		
		JRadioButton btnDeficienteSim = new JRadioButton("Sim");
		buttonGroupDeficiencia.add(btnDeficienteSim);
		
		JRadioButton btnDeficienteNao = new JRadioButton("Não");
		buttonGroupDeficiencia.add(btnDeficienteNao);
		
		JLabel lblCelular = new JLabel("Celular:");
		
		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		
		JLabel lblNacionalidade = new JLabel("Nacionalidade:");
		
		txtNacionalidade = new JTextField();
		txtNacionalidade.setColumns(10);
		
		JLabel lblTurno = new JLabel("Turno:");
		
		JComboBox cbxTurno = new JComboBox();
		
		JLabel lblSenha = new JLabel("Senha:");
		
		pswSenha = new JPasswordField();
		
		JCheckBox cbMostrarSenha = new JCheckBox("Mostrar Senha");
		
		JLabel lblDicaSenha = new JLabel("A senha deve conter de 8 à 30 caracteres.");
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha:");
		
		pswConfirmarSenha = new JPasswordField();
		
		JCheckBox cbMostrarConfirmacaoSenha = new JCheckBox("Mostrar Senha");
		
		JButton btnLimpar = new JButton("L I M P A R");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton BtnCadastrar = new JButton("C A D A S T R A R");
		BtnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BtnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 406, Short.MAX_VALUE)
					.addComponent(BtnCadastrar)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(250)
							.addComponent(lblCadastrarAluno)
							.addPreferredGap(ComponentPlacement.RELATED, 272, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSenha)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(cbMostrarSenha, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
											.addGap(95)
											.addComponent(lblDicaSenha, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
										.addComponent(pswSenha, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblConfirmarSenha)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(cbMostrarConfirmacaoSenha, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
										.addComponent(pswConfirmarSenha, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNome)
										.addComponent(lblRg))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(txtRg, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
											.addGap(21)
											.addComponent(lblCpf)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
										.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDataDeNascimento, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDataNascimento, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblSexo)
									.addGap(16)
									.addComponent(btnMasculino)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnFeminino)
									.addGap(50))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(38)
							.addComponent(lblNacionalidade)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNacionalidade, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTurno)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbxTurno, 0, 311, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(lblPossuiDeficiencia)
							.addGap(18)
							.addComponent(btnDeficienteSim)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDeficienteNao)
							.addGap(18)
							.addComponent(lblCelular)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtCelular, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)))
					.addGap(19))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastrarAluno)
					.addGap(55)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf)
						.addComponent(txtRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRg))
					.addGap(47)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(txtDataNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexo)
						.addComponent(btnFeminino)
						.addComponent(btnMasculino))
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeficienteNao)
						.addComponent(btnDeficienteSim)
						.addComponent(lblCelular)
						.addComponent(lblPossuiDeficiencia))
					.addGap(44)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTurno)
						.addComponent(txtNacionalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNacionalidade))
					.addGap(38)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pswSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDicaSenha)
						.addComponent(cbMostrarSenha))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmarSenha)
						.addComponent(pswConfirmarSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbMostrarConfirmacaoSenha)
					.addGap(218))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(675, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(BtnCadastrar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
