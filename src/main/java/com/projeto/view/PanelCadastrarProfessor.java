package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.SystemColor;
import java.text.ParseException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.placeholder.PlaceholderPasswordField;
import com.projeto.placeholder.PlaceholderTextField;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCadastrarProfessor extends JPanel {
	private PlaceholderTextField txtNome;
	private JFormattedTextField txtRg;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtCelular;
	private PlaceholderTextField txtNacionalidade;
	private PlaceholderPasswordField pswSenha;
	private PlaceholderPasswordField pswConfirmarSenha;
	private JRadioButton btnMasculino;
	private JRadioButton btnFeminino;
	private JRadioButton btnDeficienteSim;
	private JRadioButton btnDeficienteNao;
	private JComboBox cbxTurno;
	private JComboBox cbxDisciplina;
	private JCheckBox cbMostrarSenha;
	private JCheckBox cbConfirmarSenha;
	private JButton btnNewButton;
	private DatePickerSettings dateSettings;
	private DatePicker dataNascimento;

	/**
	 * Create the panel.
	 */
	public PanelCadastrarProfessor() {
		setBackground(new Color(70, 130, 150));
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblCadastrarProfessor = new JLabel("Cadastrar Professor");
		lblCadastrarProfessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarProfessor.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblRg = new JLabel("RG:");
		
		JLabel lblCpf = new JLabel("CPF:");
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		
		JLabel lblSexo = new JLabel("Sexo:");
		
		JLabel lblPossuiDeficiencia = new JLabel("Possui alguma deficiência");
		
		JLabel lblCelular = new JLabel("Celular:");
		
		JLabel lblNacionalidade = new JLabel("Nacionalidade");
		
		JLabel lblTurno = new JLabel("Turno:");
		
		JLabel lblSenha = new JLabel("Senha:");
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha:");
		
		txtNome = new PlaceholderTextField();
		txtNome.setPlaceholder("Digite o nome completo, Ex: José da Silva Sauro.");
		txtNome.setColumns(10);
		
		MaskFormatter mascaraRg;
		try {
			mascaraRg = new MaskFormatter("#.###.###");
			txtRg = new JFormattedTextField(mascaraRg);
		} catch (ParseException e) {
			System.out.println("Erro ao formatar mascara de Rg: " + e.getMessage());
		}
		txtRg.setColumns(10);
		
		MaskFormatter mascaraCpf;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCpf = new JFormattedTextField(mascaraCpf);
		} catch (ParseException e) {
			System.out.println("Erro ao formatar mascara de Cpf: " + e.getMessage());
		}
		txtCpf.setColumns(10);
		
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dataNascimento = new DatePicker(dateSettings);
		
		btnMasculino = new JRadioButton("Masculino");
		
		btnFeminino = new JRadioButton("Feminino");
		
		btnDeficienteSim = new JRadioButton("Sim");
		
		btnDeficienteNao = new JRadioButton("Não");
		
		MaskFormatter mascaraCelular;
		try {
			mascaraCelular = new MaskFormatter("(##) #####-####");
			txtCelular = new JFormattedTextField(mascaraCelular);
		} catch (ParseException e) {
			System.out.println("Erro ao formatar mascara de celular: "  + e.getMessage());
		}
		txtCelular.setColumns(10);
		
		txtNacionalidade = new PlaceholderTextField();
		txtNacionalidade.setPlaceholder("Digite sua nacionalidade, Ex: 'Brasil'.");
		txtNacionalidade.setColumns(10);
		
		cbxTurno = new JComboBox();
		
		pswSenha = new PlaceholderPasswordField();
		pswSenha.setPlaceholder("Digite sua senha, e não se esqueça de anotar.");
		
		pswConfirmarSenha = new PlaceholderPasswordField();
		pswConfirmarSenha.setPlaceholder("A confirmação da senha deve ser exatamente igual a senha digitada anteriormente.");
		
		JLabel lblDisciplina = new JLabel("Disciplina:");
		
		cbxDisciplina = new JComboBox();
		
		cbMostrarSenha = new JCheckBox("Mostrar senha");
		cbMostrarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMostrarSenha.isSelected()) {
					pswSenha.setEchoChar((char)0);
				} else {
					pswSenha.setEchoChar('•');
				}
			}
		});
		
		JLabel lblDicaSenha = new JLabel("A senha deve conter de 8 à 30 caracteres.");
		
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
		
		btnNewButton = new JButton("C A D A S T R A R");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnNewButton_1 = new JButton("L I M P A R");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(299)
					.addComponent(lblCadastrarProfessor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(247))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDataDeNascimento)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(dataNascimento, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
							.addGap(32)
							.addComponent(lblSexo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnMasculino)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnFeminino)
							.addGap(58))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNacionalidade)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNacionalidade, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED, 18, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblTurno)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblRg)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtRg, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblCpf)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPossuiDeficiencia)
									.addGap(18)
									.addComponent(btnDeficienteSim)
									.addGap(6)
									.addComponent(btnDeficienteNao)
									.addGap(21)
									.addComponent(lblCelular)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCelular, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblDisciplina)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cbxDisciplina, 0, 157, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSenha)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(cbMostrarSenha, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
											.addGap(85)
											.addComponent(lblDicaSenha, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(267))
										.addComponent(pswSenha, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblConfirmarSenha)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(cbConfirmarSenha)
										.addComponent(pswConfirmarSenha, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))))
							.addGap(20))))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addComponent(lblCadastrarProfessor)
					.addGap(50)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRg)
						.addComponent(lblCpf)
						.addComponent(txtRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(lblSexo)
						.addComponent(dataNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMasculino)
						.addComponent(btnFeminino))
					.addGap(50)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPossuiDeficiencia)
						.addComponent(lblCelular)
						.addComponent(btnDeficienteNao)
						.addComponent(btnDeficienteSim)
						.addComponent(txtCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDisciplina)
						.addComponent(cbxDisciplina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNacionalidade)
						.addComponent(txtNacionalidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTurno))
					.addGap(56)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(pswSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbMostrarSenha)
						.addComponent(lblDicaSenha))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmarSenha)
						.addComponent(pswConfirmarSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbConfirmarSenha)
					.addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		ProfessorVO professor = new ProfessorVO();
		professor.setTipo(TipoUsuarioEnum.PROFESSOR);
	} 
	

}
