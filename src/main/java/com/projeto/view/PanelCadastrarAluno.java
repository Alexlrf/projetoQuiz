package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.projeto.controller.UsuarioController;
import com.projeto.enums.TurnoEnum;
import com.projeto.model.dao.AlunoDAO;
import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.placeholder.PlaceholderPasswordField;
import com.projeto.placeholder.PlaceholderTextField;
import com.projeto.repository.Utils;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JFormattedTextField;

public class PanelCadastrarAluno extends JPanel {
	private PlaceholderTextField txtNome;
	private JFormattedTextField txtCpf;
	private final ButtonGroup buttonGroupSexo = new ButtonGroup();
	private final ButtonGroup buttonGroupDeficiencia = new ButtonGroup();
	private JFormattedTextField txtRg;
	private JFormattedTextField txtCelular;
	private PlaceholderPasswordField pswSenha;
	private PlaceholderPasswordField pswConfirmarSenha;
	private DatePickerSettings dateSettings;
	private DatePicker dataNascimento;
	private JRadioButton rdbMasculino;
	private JRadioButton rdbFeminino;
	private JRadioButton rdbDeficienteSim;
	private JRadioButton rdbDeficienteNao;
	private JCheckBox cbMostrarSenha;
	private JCheckBox cbConfirmarSenha;
	private JButton btnLimpar;
	private JButton btnCadastrar;
	private UsuarioVO aluno = new AlunoVO();
	private UsuarioController UsuarioController = new UsuarioController();
	private JComboBox cbxTurno;

	public PanelCadastrarAluno(UsuarioVO aluno) {
		this.aluno = aluno;
	}
	
	/**
	 * Create the panel.
	 */
	public PanelCadastrarAluno() {
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
		
		txtNome = new PlaceholderTextField();
		txtNome.setPlaceholder("Digite o nome completo, Ex: José da Silva Sauro.");
		txtNome.setColumns(10);
		
		JLabel lblCadastrarAluno = new JLabel("Cadastrar Aluno");
		lblCadastrarAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarAluno.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblRg = new JLabel("RG:");
		
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
		
		JLabel lblCpf = new JLabel("CPF:");
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		dataNascimento = new DatePicker(dateSettings);
		
		JLabel lblSexo = new JLabel("Sexo");
		
		rdbMasculino = new JRadioButton("Masculino");
		buttonGroupSexo.add(rdbMasculino);
		
		rdbFeminino = new JRadioButton("Feminino");
		buttonGroupSexo.add(rdbFeminino);
		
		JLabel lblPossuiDeficiencia = new JLabel("Possui alguma deficiência?");
		
		rdbDeficienteSim = new JRadioButton("Sim");
		buttonGroupDeficiencia.add(rdbDeficienteSim);
		
		rdbDeficienteNao = new JRadioButton("Não");
		buttonGroupDeficiencia.add(rdbDeficienteNao);
		
		JLabel lblCelular = new JLabel("Celular:");
		
		MaskFormatter mascaraCelular;
		try {
			mascaraCelular = new MaskFormatter("(##) #####-####");
			txtCelular = new JFormattedTextField(mascaraCelular);
		} catch (ParseException e) {
			System.out.println("Erro ao formatar mascara de celular: "  + e.getMessage());
		}
		txtCelular.setColumns(10);
		
		JLabel lblTurno = new JLabel("Turno:");
		
		ArrayList<String> turno = new ArrayList<>();
		turno.add(0, "Selecione o Turno");
		turno.add(1, TurnoEnum.MATUTINO.toString());
		turno.add(2, TurnoEnum.VESPERTINO.toString());
		turno.add(3, TurnoEnum.NOTURNO.toString());
		cbxTurno = new JComboBox();
		DefaultComboBoxModel preencherTurno = new DefaultComboBoxModel(turno.toArray());
		cbxTurno.setModel(preencherTurno);
		
		JLabel lblSenha = new JLabel("Senha:");
		
		pswSenha = new PlaceholderPasswordField();
		pswSenha.setPlaceholder("Digite sua senha, e não se esqueça de anotar.");
		
		cbMostrarSenha = new JCheckBox("Mostrar Senha");
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
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha:");
		
		pswConfirmarSenha = new PlaceholderPasswordField();
		pswConfirmarSenha.setPlaceholder("A confirmação da senha deve ser exatamente igual a senha digitada anteriormente.");
		
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
		
		btnLimpar = new JButton("L I M P A R");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparTela();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnCadastrar = new JButton("C A D A S T R A R");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean validarCampos = validarCampos();
				boolean senhaValida = validarSenha();
				
				if (validarCampos && senhaValida) {
					cadastrarAluno();
				}
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnAtualizar = new JButton("A T U A L I Z A R");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean validarCampos = validarCampos();
				boolean senhaValida = validarSenha();
				
				if (validarCampos && senhaValida) {
					cadastrarAluno();
				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(250)
							.addComponent(lblCadastrarAluno, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(138))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNome)
										.addComponent(lblTurno))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(cbxTurno, 0, 163, Short.MAX_VALUE)
											.addGap(22)
											.addComponent(lblRg)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtRg, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblCpf)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
										.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDataDeNascimento, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dataNascimento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblSexo)
									.addGap(16)
									.addComponent(rdbMasculino)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbFeminino)
									.addGap(50))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSenha)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(cbMostrarSenha, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
											.addGap(95)
											.addComponent(lblDicaSenha, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
										.addComponent(pswSenha, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblConfirmarSenha)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(cbConfirmarSenha, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
										.addComponent(pswConfirmarSenha, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPossuiDeficiencia)
									.addGap(18)
									.addComponent(rdbDeficienteSim)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbDeficienteNao)
									.addGap(18)
									.addComponent(lblCelular)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCelular, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)))))
					.addGap(19))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
					.addComponent(btnCadastrar)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
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
						.addComponent(lblRg)
						.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTurno))
					.addGap(47)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(dataNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexo)
						.addComponent(rdbFeminino)
						.addComponent(rdbMasculino))
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbDeficienteNao)
						.addComponent(rdbDeficienteSim)
						.addComponent(lblCelular)
						.addComponent(lblPossuiDeficiencia))
					.addGap(37)
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
					.addComponent(cbConfirmarSenha)
					.addPreferredGap(ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		if (aluno.getIdUsuario() != null) {
			btnAtualizar.setVisible(true);
			btnCadastrar.setVisible(false);
		} else {
			btnAtualizar.setVisible(false);
			btnCadastrar.setVisible(true);
		}
	}

	protected void cadastrarAluno() {
		// TODO Auto-generated method stub
		
	}
	
	protected void limparTela() {
		txtNome.setText("");
		cbxTurno.setSelectedIndex(0);
		txtRg.setText("");
		txtCpf.setText("");
		dataNascimento.clear();
		rdbMasculino.setSelected(false);
		rdbFeminino.setSelected(false);
		rdbDeficienteNao.setSelected(false);
		rdbDeficienteSim.setSelected(false);
		txtCelular.setText("");
		pswSenha.setText("");
		pswConfirmarSenha.setText("");
		cbMostrarSenha.setSelected(false);
		cbConfirmarSenha.setSelected(false);
	}

	protected boolean validarSenha() {
boolean validarSenha = true;
		
		if (!pswSenha.getText().equals(pswConfirmarSenha.getText())) {
			validarSenha = false;
			JOptionPane.showMessageDialog(null, "Para confirmar a senha, é necessário que as duas senhas sejam as mesmas.\n "
					+ "Favor verificar!", "Senha Inconsistente", JOptionPane.INFORMATION_MESSAGE);
		} else if (pswSenha.getText().length() < 8) {
			validarSenha = false;
			JOptionPane.showMessageDialog(null, "A senha deve conter mais de 8 caracteres.\n Favor verificar!", 
					"Senha Inconsistente", JOptionPane.INFORMATION_MESSAGE);
		} else if (pswSenha.getText().length() > 30) {
			validarSenha = false;
			JOptionPane.showMessageDialog(null, "A senha deve conter menos de 30 caracteres.\n Favor verificar!", 
					"Senha Inconsistente", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return validarSenha;
	}

	protected boolean validarCampos() {
		StringBuilder mensagem = new StringBuilder();
		boolean validar = true;
		mensagem.append("Os campos ");
		
		if (!Utils.stringValida(txtNome.getText())) {
			mensagem.append("nome, ");
			validar = false;
		}

		if (cbxTurno.getSelectedIndex() == 0) {
			mensagem.append("Turno, ");
			validar = false;
		}
		
		if (!Utils.stringValida(txtRg.getText().replace(".", ""))) {
			mensagem.append("Rg, ");
			validar = false;
		}
		
		if (!Utils.stringValida(txtCpf.getText().replace(".", "").replace("-", ""))) {
			mensagem.append("Cpf, ");
			validar = false;
		}

		LocalDate dtNascimento = dataNascimento.getDate();
		if (dtNascimento == null) {
			mensagem.append("Data de Nascimento, ");
			validar = false;
		}
		
		LocalDate dataAtual = LocalDate.now();
		if (dtNascimento!= null && dtNascimento.isAfter(dataAtual)) {
			JOptionPane.showMessageDialog(null, "Data de nascimento é maior que a data atual. \n Favor Reconsiderar!", "A T E N Ç Ã O", JOptionPane.WARNING_MESSAGE);
			validar = false;
		}

		if (!rdbMasculino.isSelected() && !rdbFeminino.isSelected()) {
			mensagem.append("Sexo, ");
			validar = false;
		}

		if (!rdbDeficienteNao.isSelected() && !rdbDeficienteSim.isSelected()) {
			mensagem.append("Possui Deficiência, ");
			validar = false;
		}

		if (!Utils.stringValida(txtCelular.getText().replace("(", "").replace(")", "").replace("-", ""))) {
			mensagem.append("Celular, ");
			validar = false;
		}

		if (!Utils.stringValida(pswSenha.getText())) {
			mensagem.append("Senha, ");
			validar = false;
		}
		
		if (!Utils.stringValida(pswConfirmarSenha.getText())) {
			mensagem.append("Confirmar a Senha, ");
			validar = false;
		}
		
		mensagem.append("são obrigatórios.\n Favor preenchelos!");
		
		if (!validar) {
			JOptionPane.showMessageDialog(null, mensagem, "A T E N Ç Ã O", JOptionPane.WARNING_MESSAGE);
		}
		
		return validar;
	}
}
