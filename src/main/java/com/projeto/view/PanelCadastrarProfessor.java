package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.SystemColor;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;
import com.projeto.exceptions.CpfExistenteException;
import com.projeto.exceptions.RgExistenteException;
import com.projeto.model.entity.DisciplinaVO;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.placeholder.PlaceholderPasswordField;
import com.projeto.placeholder.PlaceholderTextField;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;
import com.projeto.controller.UsuarioController;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class PanelCadastrarProfessor extends JPanel {
	private PlaceholderTextField txtNome;
	private JFormattedTextField txtRg;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtCelular;
	private PlaceholderPasswordField pswSenha;
	private PlaceholderPasswordField pswConfirmarSenha;
	private JRadioButton rdbMasculino;
	private JRadioButton rdbFeminino;
	private JRadioButton rdbDeficienteSim;
	private JRadioButton rdbDeficienteNao;
	private JComboBox cbxTurno;
	private JComboBox cbxDisciplina;
	private JCheckBox cbMostrarSenha;
	private JCheckBox cbConfirmarSenha;
	private DatePickerSettings dateSettings;
	private DatePicker dataNascimento;
	private UsuarioVO professor = new ProfessorVO();
	private UsuarioController UsuarioController = new UsuarioController();
	private List<DisciplinaVO> disciplinas = new ArrayList<>();
	private final ButtonGroup buttonGroupDeficiente = new ButtonGroup();
	private final ButtonGroup buttonGroupSexo = new ButtonGroup();
	private JComboBox cbxAtivado;

	public PanelCadastrarProfessor(ProfessorVO professor) {
		this.professor = professor;
		construirTela();
	}
	
	/**
	 * Create the panel.
	 */
	public PanelCadastrarProfessor() {
		construirTela();
	}

	private void construirTela() {
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
		
		rdbMasculino = new JRadioButton("Masculino");
		buttonGroupSexo.add(rdbMasculino);
		
		rdbFeminino = new JRadioButton("Feminino");
		buttonGroupSexo.add(rdbFeminino);
		
		rdbDeficienteSim = new JRadioButton("Sim");
		buttonGroupDeficiente.add(rdbDeficienteSim);
		
		rdbDeficienteNao = new JRadioButton("Não");
		buttonGroupDeficiente.add(rdbDeficienteNao);
		
		MaskFormatter mascaraCelular;
		try {
			mascaraCelular = new MaskFormatter("(##) #####-####");
			txtCelular = new JFormattedTextField(mascaraCelular);
		} catch (ParseException e) {
			System.out.println("Erro ao formatar mascara de celular: "  + e.getMessage());
		}
		txtCelular.setColumns(10);
		
		ArrayList<String> turno = new ArrayList<>();
		turno.add(0, "Selecione o Turno");
		turno.add(1, TurnoEnum.MATUTINO.toString());
		turno.add(2, TurnoEnum.VESPERTINO.toString());
		turno.add(3, TurnoEnum.NOTURNO.toString());
		cbxTurno = new JComboBox();
		DefaultComboBoxModel preencherTurno = new DefaultComboBoxModel(turno.toArray());
		cbxTurno.setModel(preencherTurno);
		
		pswSenha = new PlaceholderPasswordField();
		pswSenha.setPlaceholder("Digite sua senha, e não se esqueça de anotar.");
		
		pswConfirmarSenha = new PlaceholderPasswordField();
		pswConfirmarSenha.setPlaceholder("A confirmação da senha deve ser exatamente igual a senha digitada anteriormente.");
		
		JLabel lblDisciplina = new JLabel("Disciplina:");
		
		disciplinas = UsuarioController.buscarDisciplina();
		ArrayList<String> disc = new ArrayList<>();
		disc.add("SELECIONE A DISCIPLINA");
		for (DisciplinaVO disciplinaVO : disciplinas) {
			disc.add(disciplinaVO.getNomeDisciplina());
		}
		cbxDisciplina = new JComboBox();
		DefaultComboBoxModel preencherDisciplina = new DefaultComboBoxModel(disc.toArray());
		cbxDisciplina.setModel(preencherDisciplina);
		
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
		
		JLabel lblDicaSenha = new JLabel("A senha deve conter de 8 a 30 caracteres.");
		
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
		
		JButton btnCadastrar = new JButton("C A D A S T R A R");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean validarCampos = validarCampos();
				boolean senhaValida = validarSenha();
				
				if (validarCampos && senhaValida) {
					cadastrarProfessor();
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
					cadastrarProfessor();
				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnLimpar = new JButton("L I M P A R");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparTela();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		cbxAtivado = new JComboBox();
		cbxAtivado.setModel(new DefaultComboBoxModel
				(new String[] {Constants.ATIVADO.toString(), Constants.DESATIVADO.toString()}));
		
		JLabel lblAtivado = new JLabel("Ativado");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(299)
					.addComponent(lblCadastrarProfessor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(247))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
					.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSenha)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(cbMostrarSenha, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
											.addGap(85)
											.addComponent(lblDicaSenha, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
											.addGap(267))
										.addComponent(pswSenha, GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblConfirmarSenha)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(cbConfirmarSenha)
										.addComponent(pswConfirmarSenha, GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE))))
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDataDeNascimento)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(dataNascimento, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
							.addGap(32)
							.addComponent(lblSexo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbMasculino)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbFeminino)
							.addGap(58))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblTurno)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cbxTurno, 0, 186, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblRg)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtRg, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblCpf)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
									.addGap(11)
									.addComponent(lblAtivado)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cbxAtivado, 0, 138, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPossuiDeficiencia)
									.addGap(18)
									.addComponent(rdbDeficienteSim)
									.addGap(6)
									.addComponent(rdbDeficienteNao)
									.addGap(21)
									.addComponent(lblCelular)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCelular, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblDisciplina)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cbxDisciplina, 0, 157, Short.MAX_VALUE)))
							.addGap(20))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addComponent(lblCadastrarProfessor)
					.addGap(50)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxAtivado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAtivado))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf)
						.addComponent(txtRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRg)
						.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTurno))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(lblSexo)
						.addComponent(dataNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbMasculino)
						.addComponent(rdbFeminino))
					.addGap(50)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPossuiDeficiencia)
						.addComponent(lblCelular)
						.addComponent(rdbDeficienteNao)
						.addComponent(rdbDeficienteSim)
						.addComponent(txtCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDisciplina)
						.addComponent(cbxDisciplina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
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
					.addGap(84)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnCadastrar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(btnAtualizar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addComponent(btnLimpar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		if (professor.getIdUsuario() != null) {
			btnAtualizar.setVisible(true);
			btnCadastrar.setVisible(false);
			preencherProfessorNaTela((ProfessorVO)professor);
		} else {
			btnAtualizar.setVisible(false);
			btnCadastrar.setVisible(true);
			cbxAtivado.setEnabled(false);
		}
	}

	private void preencherProfessorNaTela(ProfessorVO professor) {
		txtNome.setText(professor.getNome());
		
		cbxAtivado.setSelectedIndex(1);
		if (professor.isAtivo()) {
			cbxAtivado.setSelectedIndex(0);
		}
		
		cbxTurno.setSelectedItem(professor.getTurno().toString());
		txtRg.setText(professor.getRg());
		txtCpf.setText(professor.getCpf());
		dataNascimento.setDate(professor.getDataNascimento());
		
		rdbFeminino.setSelected(true);
		if (professor.getSexo() == 'M') {
			rdbMasculino.setSelected(true);
		}
		
		rdbDeficienteNao.setSelected(true);
		if (professor.isPossuiDeficiencia()) {
			rdbDeficienteSim.setSelected(true);
		}
		
		txtCelular.setText(professor.getCelular());
		
		for (DisciplinaVO disciplinaVO : disciplinas) {
			if(professor.getIdDisciplina() == disciplinaVO.getIdDisciplina()) {
				cbxDisciplina.setSelectedItem(disciplinaVO.getNomeDisciplina());
			}
		}
		pswSenha.setText(professor.getSenha());
		pswConfirmarSenha.setText(professor.getSenha());
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

	protected void cadastrarProfessor() {
		
		professor.setNome(txtNome.getText());
		
		professor.setAtivo(true);
		if (cbxAtivado.getSelectedIndex() > 0) {
			professor.setAtivo(false);
		}
		
		if (cbxTurno.getSelectedItem().equals("MATUTINO")) {
			professor.setTurno(TurnoEnum.MATUTINO);
		} else if (cbxTurno.getSelectedItem().equals("VESPERTINO")) {
			professor.setTurno(TurnoEnum.VESPERTINO);
		} else if (cbxTurno.getSelectedItem().equals("NOTURNO")) {
			professor.setTurno(TurnoEnum.NOTURNO);
		}
		
		professor.setRg(txtRg.getText().replace(".", ""));
		professor.setCpf(txtCpf.getText().replace(".", "").replace("-", ""));
		professor.setDataNascimento(dataNascimento.getDate());
		
		professor.setSexo('F');
		if (rdbMasculino.isSelected()) {
			professor.setSexo('M');
		}
		
		professor.setPossuiDeficiencia(false);
		if (rdbDeficienteSim.isSelected()) {
			professor.setPossuiDeficiencia(true);
		}
		
		professor.setCelular(txtCelular.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
		
		String disciplinaSelecionada = cbxDisciplina.getSelectedItem().toString();
		for (DisciplinaVO disciplinaVO : disciplinas) {
			if (disciplinaVO.getNomeDisciplina().equalsIgnoreCase(disciplinaSelecionada)) {
				((ProfessorVO)professor).setIdDisciplina(disciplinaVO.getIdDisciplina());
			}
		}
		
		professor.setSenha(pswSenha.getText());
		professor.setTipo(TipoUsuarioEnum.PROFESSOR);
		
		String mensagem = "";
		String titulo = "";
		if (professor.getIdUsuario() != null) {
			titulo = "Atualizar";
			boolean atualizou = false;
			mensagem = "Erro ao atualizar professor.";
			
			try {
				atualizou = UsuarioController.alterar(professor);
			} catch (RgExistenteException | CpfExistenteException e) {
				mensagem = e.getMessage();
				titulo = "Aviso";
			}
			
			if (atualizou) {
				mensagem = "Professor atualizado com sucesso!";
			}
		} else {
			
			try {
				titulo = "Cadastrar";
				professor = UsuarioController.cadastrar(professor);
				if (professor.getIdUsuario() != null) {
					mensagem = "Professor cadastrado com sucesso!";
					limparTela();
				} else {
					mensagem = "Erro ao cadastrar professor.";
				}
			} catch (RgExistenteException | CpfExistenteException e) {
				mensagem = e.getMessage();
				titulo = "Aviso";
			}
		}
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	protected void limparTela() {
		txtNome.setText("");
		cbxTurno.setSelectedIndex(0);
		cbxAtivado.setSelectedIndex(0);
		txtRg.setText("");
		txtCpf.setText("");
		dataNascimento.clear();
		buttonGroupSexo.clearSelection();
		buttonGroupDeficiente.clearSelection();
		txtCelular.setText("");
		cbxDisciplina.setSelectedIndex(0);
		pswSenha.setText("");
		pswConfirmarSenha.setText("");
		cbMostrarSenha.setSelected(false);
		cbConfirmarSenha.setSelected(false);
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
		
		if (cbxDisciplina.getSelectedIndex() == 0) {
			mensagem.append("Disciplina, ");
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
		
		mensagem.append("são obrigatórios.\n Favor preenchê-los!");
		
		if (!validar) {
			JOptionPane.showMessageDialog(null, mensagem, "A T E N Ç Ã O", JOptionPane.WARNING_MESSAGE);
		}
		
		return validar;
	}
}
