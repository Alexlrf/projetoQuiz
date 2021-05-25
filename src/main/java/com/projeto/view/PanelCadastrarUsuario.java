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
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelCadastrarUsuario() {
		
		JLabel lblCadastrarAluno = new JLabel("Cadastrar Aluno");
		lblCadastrarAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarAluno.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		
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
		
		JLabel lblRg = new JLabel("RG:");
		
		txtRg = new JTextField();
		txtRg.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		
		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		
		JLabel lblNacionalidade = new JLabel("Nacionalidade:");
		
		txtNacionalidade = new JTextField();
		txtNacionalidade.setColumns(10);
		
		JLabel lblTurno = new JLabel("Turno:");
		
		JComboBox cbxTurno = new JComboBox();
		
		JLabel lblSenha = new JLabel("Senha:");
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha:");
		
		JLabel lblDicaSenha = new JLabel("A senha deve conter de 8 à 30 caracteres.");
		
		JCheckBox cbMostrarSenha = new JCheckBox("Mostrar Senha");
		
		JCheckBox cbMostrarConfirmacaoSenha = new JCheckBox("Mostrar Senha");
		
		pswSenha = new JPasswordField();
		
		pswConfirmarSenha = new JPasswordField();
		
		JButton BtnCadastrar = new JButton("C A D A S T R A R");
		BtnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BtnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnLimpar = new JButton("L I M P A R");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(287)
					.addComponent(lblCadastrarAluno, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(257))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(0)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(28)
									.addComponent(lblNacionalidade)
									.addGap(19)
									.addComponent(txtNacionalidade, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTurno)
									.addGap(18)
									.addComponent(cbxTurno, 0, 298, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(29)
									.addComponent(lblSenha)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(cbMostrarSenha, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblDicaSenha, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
											.addGap(274))
										.addComponent(pswSenha, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(31)
									.addComponent(lblPossuiDeficiencia)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnDeficienteSim)
									.addGap(12)
									.addComponent(btnDeficienteNao)
									.addGap(27)
									.addComponent(lblCelular)
									.addGap(18)
									.addComponent(txtCelular, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGap(33)
									.addComponent(lblConfirmarSenha)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(cbMostrarConfirmacaoSenha, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
										.addComponent(pswConfirmarSenha, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
									.addComponent(BtnCadastrar, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNome)
										.addComponent(lblRg))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtRg, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblCpf)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblDataDeNascimento, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
											.addGap(322))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addGap(129)
											.addComponent(textField, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
											.addGap(18)))
									.addGap(18)
									.addComponent(lblSexo)
									.addGap(18)
									.addComponent(btnMasculino, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnFeminino, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))))
					.addGap(28))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastrarAluno)
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRg)
						.addComponent(lblCpf)
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexo)
						.addComponent(btnMasculino)
						.addComponent(btnFeminino))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDeficienteNao)
						.addComponent(lblCelular)
						.addComponent(txtCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPossuiDeficiencia)
						.addComponent(btnDeficienteSim))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNacionalidade)
						.addComponent(lblTurno)
						.addComponent(txtNacionalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pswSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha))
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDicaSenha)
						.addComponent(cbMostrarSenha))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pswConfirmarSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConfirmarSenha))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbMostrarConfirmacaoSenha)
					.addPreferredGap(ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnLimpar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(BtnCadastrar, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
