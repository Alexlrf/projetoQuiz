package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class PanelCadastrarCoordenador extends JPanel {
	private JTextField txtNome;
	private JTextField txtRg;
	private JTextField txtCpf;
	private JTextField txtDataNascimento;
	private JTextField textField_4;
	private JTextField txtNacionalidade;
	private JTextField pswSenha;
	private JTextField pswConfirmarSenha;

	/**
	 * Create the panel.
	 */
	public PanelCadastrarCoordenador() {
		setBackground(new Color(32, 178, 170));
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblCadastrarCoordenador = new JLabel("Cadastrar Coordenador");
		lblCadastrarCoordenador.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCadastrarCoordenador.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblRg = new JLabel("RG:");
		
		txtRg = new JTextField();
		txtRg.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		
		txtDataNascimento = new JTextField();
		txtDataNascimento.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:");
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		
		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		
		JLabel lblPosuuiDeficiencia = new JLabel("Possui alguma deficiência?");
		
		JRadioButton rdbtnSim = new JRadioButton("Sim");
		
		JRadioButton rdbtnNao = new JRadioButton("Não");
		
		JLabel lblCelular = new JLabel("Celular:");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblNacionalidade = new JLabel("Nacionalidade:");
		
		txtNacionalidade = new JTextField();
		txtNacionalidade.setColumns(10);
		
		JLabel lblTurno = new JLabel("Turno:");
		
		JComboBox cbxTurno = new JComboBox();
		
		JLabel lblSenha = new JLabel("Senha:");
		
		pswSenha = new JTextField();
		pswSenha.setColumns(10);
		
		JCheckBox cbMostrarSenha = new JCheckBox("Mostrar Senha");
		
		JLabel lblSenha_1 = new JLabel("Confirmar Senha:");
		
		pswConfirmarSenha = new JTextField();
		pswConfirmarSenha.setColumns(10);
		
		JCheckBox cbConfirmarSenha = new JCheckBox("Mostrar Senha");
		
		JLabel lblDicaSenha = new JLabel("A senha deve conter de 8 à 30 caracteres.");
		
		JButton btnCadastrar = new JButton("C A D A S T R A R");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnLimpar = new JButton("L I M P A R");
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(225)
							.addComponent(lblCadastrarCoordenador, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
							.addGap(228))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblRg)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtRg, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblCpf)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblDataDeNascimento)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtDataNascimento, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblSexo)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnMasculino)
									.addGap(18)
									.addComponent(rdbtnFeminino)
									.addGap(62))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPosuuiDeficiencia)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnSim)
									.addGap(12)
									.addComponent(rdbtnNao)
									.addGap(18)
									.addComponent(lblCelular)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNacionalidade)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtNacionalidade, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblTurno)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cbxTurno, 0, 358, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblSenha_1)
										.addComponent(lblSenha))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(cbMostrarSenha)
											.addGap(118)
											.addComponent(lblDicaSenha, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(209))
										.addComponent(pswSenha, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(13)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(cbConfirmarSenha)
												.addComponent(pswConfirmarSenha, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE))))))))
					.addGap(22))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
					.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addComponent(lblCadastrarCoordenador)
					.addGap(62)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRg)
						.addComponent(txtRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf))
					.addGap(47)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(txtDataNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexo)
						.addComponent(rdbtnMasculino)
						.addComponent(rdbtnFeminino))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPosuuiDeficiencia)
						.addComponent(rdbtnNao)
						.addComponent(rdbtnSim)
						.addComponent(lblCelular)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNacionalidade)
						.addComponent(txtNacionalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTurno)
						.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(pswSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbMostrarSenha)
						.addComponent(lblDicaSenha))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha_1)
						.addComponent(pswConfirmarSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbConfirmarSenha)
					.addPreferredGap(ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}

}
