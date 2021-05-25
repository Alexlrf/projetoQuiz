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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

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
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblRg = new JLabel("RG:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:");
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		
		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		
		JLabel lblPosuuiAlgumaDeficincia = new JLabel("Possui alguma deficiência?");
		
		JRadioButton rdbtnSim = new JRadioButton("Sim");
		
		JRadioButton rdbtnNo = new JRadioButton("Não");
		
		JLabel lblCelular = new JLabel("Celular:");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblNacionalidade = new JLabel("Nacionalidade:");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JLabel lblTurno = new JLabel("Turno:");
		
		JComboBox comboBox = new JComboBox();
		
		JLabel lblSenha = new JLabel("Senha:");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JCheckBox chckbxMostrarSenha = new JCheckBox("Mostrar Senha");
		
		JLabel lblSenha_1 = new JLabel("Confirmar Senha:");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JCheckBox chckbxMostrarSenha_1 = new JCheckBox("Mostrar Senha");
		
		JLabel lblNewLabel = new JLabel("A senha deve conter de 8 à 30 caracteres.");
		
		JButton btnNewButton = new JButton("C A D A S T R A R");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnNewButton_1 = new JButton("L I M P A R");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(225)
							.addComponent(lblCadastrarCoordenador, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
							.addGap(228))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblRg)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblCpf)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(lblDataDeNascimento)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblSexo)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnMasculino)
									.addGap(18)
									.addComponent(rdbtnFeminino)
									.addGap(62))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPosuuiAlgumaDeficincia)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnSim)
									.addGap(12)
									.addComponent(rdbtnNo)
									.addGap(18)
									.addComponent(lblCelular)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNacionalidade)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblTurno)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBox, 0, 360, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblSenha_1)
										.addComponent(lblSenha))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(chckbxMostrarSenha)
											.addGap(118)
											.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(209))
										.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(13)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(chckbxMostrarSenha_1)
												.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE))))))))
					.addGap(22))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRg)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf))
					.addGap(47)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexo)
						.addComponent(rdbtnMasculino)
						.addComponent(rdbtnFeminino))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPosuuiAlgumaDeficincia)
						.addComponent(rdbtnNo)
						.addComponent(rdbtnSim)
						.addComponent(lblCelular)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNacionalidade)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTurno)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxMostrarSenha)
						.addComponent(lblNewLabel))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha_1)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxMostrarSenha_1)
					.addPreferredGap(ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}

}
