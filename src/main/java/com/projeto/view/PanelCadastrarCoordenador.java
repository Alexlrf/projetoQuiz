package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.projeto.placeholder.PlaceholderPasswordField;
import com.projeto.placeholder.PlaceholderTextField;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCadastrarCoordenador extends JPanel {
	private PlaceholderTextField txtNome;
	private JFormattedTextField txtRg;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtCelular;
	private PlaceholderTextField txtNacionalidade;
	private PlaceholderPasswordField pswSenha;
	private PlaceholderPasswordField pswConfirmarSenha;
	private DatePickerSettings dateSettings;
	private DatePicker dataNascimento;
	private JButton btnCadastrar;
	private JCheckBox cbConfirmarSenha;
	private JComboBox cbxTurno;
	private JRadioButton rdbtnNao;
	private JRadioButton rdbtnSim;
	private JRadioButton rdbtnFeminino;
	private JRadioButton rdbtnMasculino;

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
		
		txtNome = new PlaceholderTextField();
		txtNome.setPlaceholder("Digite o nome completo, Ex: José da Silva Sauro.");
		txtNome.setColumns(10);
		
		JLabel lblRg = new JLabel("RG:");
		
		MaskFormatter mascaraRg;
		try {
			mascaraRg = new MaskFormatter("#.###.###");
			txtRg = new JFormattedTextField(mascaraRg);
		} catch (ParseException e) {
			System.out.println("Erro ao formatar mascara de Rg: " + e.getMessage());
		}
		txtRg.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		
		MaskFormatter mascaraCpf;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCpf = new JFormattedTextField(mascaraCpf);
		} catch (ParseException e) {
			System.out.println("Erro ao formatar mascara de Cpf: " + e.getMessage());
		}
		txtCpf.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dataNascimento = new DatePicker(dateSettings);
		
		JLabel lblSexo = new JLabel("Sexo:");
		
		rdbtnMasculino = new JRadioButton("Masculino");
		
		rdbtnFeminino = new JRadioButton("Feminino");
		
		JLabel lblPosuuiDeficiencia = new JLabel("Possui alguma deficiência?");
		
		rdbtnSim = new JRadioButton("Sim");
		
		rdbtnNao = new JRadioButton("Não");
		
		JLabel lblCelular = new JLabel("Celular:");
		
		MaskFormatter mascaraCelular;
		try {
			mascaraCelular = new MaskFormatter("(##) #####-####");
			txtCelular = new JFormattedTextField(mascaraCelular);
		} catch (ParseException e) {
			System.out.println("Erro ao formatar mascara de celular: "  + e.getMessage());
		}
		txtCelular.setColumns(10);
		
		JLabel lblNacionalidade = new JLabel("Nacionalidade:");
		
		txtNacionalidade = new PlaceholderTextField();
		txtNacionalidade.setPlaceholder("Digite sua nacionalidade, Ex: 'Brasil'.");
		txtNacionalidade.setColumns(10);
		
		JLabel lblTurno = new JLabel("Turno:");
		
		cbxTurno = new JComboBox();
		
		JLabel lblSenha = new JLabel("Senha:");
		
		pswSenha = new PlaceholderPasswordField();
		pswSenha.setPlaceholder("Digite sua senha, e não se esqueça de anotar.");
		pswSenha.setColumns(10);
		
		JCheckBox cbMostrarSenha = new JCheckBox("Mostrar Senha");
		cbMostrarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMostrarSenha.isSelected()) {
					pswSenha.setEchoChar((char)0);
				} else {
					pswSenha.setEchoChar('•');
				}
			}
		});
		
		JLabel lblSenha_1 = new JLabel("Confirmar Senha:");
		
		pswConfirmarSenha = new PlaceholderPasswordField();
		pswConfirmarSenha.setPlaceholder("A confirmação da senha deve ser exatamente igual a senha digitada anteriormente.");
		pswConfirmarSenha.setColumns(10);
		
		cbConfirmarSenha = new JCheckBox("Mostrar Senha");
		cbConfirmarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbConfirmarSenha.isSelected()) {
					pswConfirmarSenha.setEchoChar((char)0);
				} else {
					pswConfirmarSenha.setEchoChar('•');
				}
			}
		});
		
		JLabel lblDicaSenha = new JLabel("A senha deve conter de 8 à 30 caracteres.");
		
		btnCadastrar = new JButton("C A D A S T R A R");
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
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
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
									.addComponent(dataNascimento, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
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
									.addComponent(txtCelular, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNacionalidade)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtNacionalidade, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblTurno)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cbxTurno, 0, 358, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSenha)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblSenha_1)
												.addComponent(cbMostrarSenha))
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addGap(34)
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(cbConfirmarSenha)
														.addComponent(pswConfirmarSenha, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)))
												.addGroup(gl_panel.createSequentialGroup()
													.addGap(84)
													.addComponent(lblDicaSenha, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))))
										.addComponent(pswSenha, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE))))))
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
						.addComponent(dataNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexo)
						.addComponent(rdbtnMasculino)
						.addComponent(rdbtnFeminino))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPosuuiDeficiencia)
						.addComponent(rdbtnNao)
						.addComponent(rdbtnSim)
						.addComponent(lblCelular)
						.addComponent(txtCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNacionalidade)
						.addComponent(txtNacionalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTurno)
						.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pswSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha))
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
