package com.projeto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.projeto.controller.UsuarioController;
import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;
import com.projeto.exceptions.CpfExistenteException;
import com.projeto.exceptions.RgExistenteException;
import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.placeholder.PlaceholderPasswordField;
import com.projeto.placeholder.PlaceholderTextField;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;

public class PanelCadastrarAluno extends JPanel {
	private PlaceholderTextField txtNome;
	private JFormattedTextField txtCpf;
	private final ButtonGroup buttonGroupSexo = new ButtonGroup();
	private final ButtonGroup buttonGroupDeficiente = new ButtonGroup();
	private JTextField txtRg;
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
	private JLabel lblStatus;
	private JComboBox cbxAtivado;
	private int contaCaracteres = 0;

	public PanelCadastrarAluno(AlunoVO aluno) {
		this.aluno = aluno;
		construirTela();
	}
	
	public PanelCadastrarAluno() {
		construirTela();
	}
	
	/**
	 * Create the panel.
	 */
	public void construirTela() {
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
	
		txtRg = new JTextField();
		txtRg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {				
				contaCaracteres++;
				if (contaCaracteres > 20) {
					JOptionPane.showMessageDialog(null, "Campo RG pode conter no MÁXIMO 20 caracteres\n"
							+ "NÃO são aceitas mais de 3 letras em sequência",
							"A T E N Ç Ã O", JOptionPane.ERROR_MESSAGE);
					txtRg.setText("");
					contaCaracteres = 0;
				}
			}
		});
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
		buttonGroupDeficiente.add(rdbDeficienteSim);
		
		rdbDeficienteNao = new JRadioButton("Não");
		buttonGroupDeficiente.add(rdbDeficienteNao);
		
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
		
		JLabel lblDicaSenha = new JLabel("A senha deve conter de 8 a 30 caracteres.");
		
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
		
		lblStatus = new JLabel("Status");
		
		cbxAtivado = new JComboBox();
		cbxAtivado.setModel(new DefaultComboBoxModel
				(new String[] {Constants.ATIVADO.toString(), Constants.DESATIVADO.toString()}));
		
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
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
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
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblStatus)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cbxAtivado, 0, 89, Short.MAX_VALUE))))
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
											.addComponent(lblDicaSenha, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
										.addComponent(pswSenha, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblConfirmarSenha)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(cbConfirmarSenha, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
										.addComponent(pswConfirmarSenha, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)))
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
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addComponent(btnCadastrar)
					.addGap(62)
					.addComponent(btnAtualizar)
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
						.addComponent(lblNome)
						.addComponent(lblStatus)
						.addComponent(cbxAtivado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
					.addPreferredGap(ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		if (aluno.getIdUsuario() != null) {
			btnAtualizar.setVisible(true);
			btnCadastrar.setVisible(false);
			preencherAlunoNaTela((AlunoVO)aluno);
		} else {
			btnAtualizar.setVisible(false);
			btnCadastrar.setVisible(true);
			cbxAtivado.setEnabled(false);
		}
	}

	private void preencherAlunoNaTela(AlunoVO aluno) {
		txtNome.setText(aluno.getNome());
		
		cbxAtivado.setSelectedIndex(1);
		if (aluno.isAtivo()) {
			cbxAtivado.setSelectedIndex(0);
		}
		
		cbxTurno.setSelectedItem(aluno.getTurno().toString());
		txtRg.setText(aluno.getRg());
		txtCpf.setText(aluno.getCpf());
		dataNascimento.setDate(aluno.getDataNascimento());
		
		rdbFeminino.setSelected(true);
		if (aluno.getSexo() == 'M') {
			rdbMasculino.setSelected(true);
		}
		
		rdbDeficienteNao.setSelected(true);
		if (aluno.isPossuiDeficiencia()) {
			rdbDeficienteSim.setSelected(true);
		}
		
		txtCelular.setText(aluno.getCelular());
		pswSenha.setText(aluno.getSenha());
		pswConfirmarSenha.setText(aluno.getSenha());
	}

	protected void cadastrarAluno() {
		aluno.setNome(txtNome.getText());
		
		aluno.setAtivo(true);
		if (cbxAtivado.getSelectedIndex() > 0) {
			aluno.setAtivo(false);
		}
		
		if (cbxTurno.getSelectedItem().equals("MATUTINO")) {
			aluno.setTurno(TurnoEnum.MATUTINO);
		} else if (cbxTurno.getSelectedItem().equals("VESPERTINO")) {
			aluno.setTurno(TurnoEnum.VESPERTINO);
		} else if (cbxTurno.getSelectedItem().equals("NOTURNO")) {
			aluno.setTurno(TurnoEnum.NOTURNO);
		}
		
		aluno.setRg(txtRg.getText().replace(".", ""));
		aluno.setCpf(txtCpf.getText().replace(".", "").replace("-", ""));
		aluno.setDataNascimento(dataNascimento.getDate());
		
		aluno.setSexo('F');
		if (rdbMasculino.isSelected()) {
			aluno.setSexo('M');
		}
		
		aluno.setPossuiDeficiencia(false);
		if (rdbDeficienteSim.isSelected()) {
			aluno.setPossuiDeficiencia(true);
		}
		
		aluno.setCelular(txtCelular.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
		aluno.setSenha(pswSenha.getText());
		aluno.setTipo(TipoUsuarioEnum.ALUNO);
		
		String mensagem = "";
		String titulo = "";
		int icone = JOptionPane.ERROR_MESSAGE;
		if (aluno.getIdUsuario() != null) {
			titulo = "Atualizar";
			boolean atualizou = false;
			mensagem = "Erro ao atualizar aluno!";
			
			try {
				atualizou = UsuarioController.alterar(aluno);
			} catch (RgExistenteException | CpfExistenteException e) {
				mensagem = e.getMessage() + "!";
				titulo = "Aviso";
			}
			
			if (atualizou) {
				mensagem = "Aluno atualizado com sucesso!";
				icone = JOptionPane.INFORMATION_MESSAGE;
			}
		} else {
			
			try {
				titulo = "Cadastrar";
				aluno = UsuarioController.cadastrar(aluno);
				if (aluno.getIdUsuario() != null) {
					mensagem = "Aluno cadastrado com sucesso!";
					icone = JOptionPane.INFORMATION_MESSAGE;
					limparTela();
				} else {
					mensagem = "Erro ao cadastrar aluno!";
				}
			} catch (RgExistenteException | CpfExistenteException e) {
				mensagem = e.getMessage() + "!";
				titulo = "Aviso";
			}
		}
		JOptionPane.showMessageDialog(null, mensagem, titulo, icone);
		
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
					+ "Favor verificar!", "Senha Inconsistente", JOptionPane.ERROR_MESSAGE);
		} else if (pswSenha.getText().length() < 8) {
			validarSenha = false;
			JOptionPane.showMessageDialog(null, "A senha deve conter mais de 8 caracteres.\n Favor verificar!", 
					"Senha Inconsistente", JOptionPane.ERROR_MESSAGE);
		} else if (pswSenha.getText().length() > 30) {
			validarSenha = false;
			JOptionPane.showMessageDialog(null, "A senha deve conter menos de 30 caracteres.\n Favor verificar!", 
					"Senha Inconsistente", JOptionPane.ERROR_MESSAGE);
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
		} else if (!Utils.validaFormatoRG(txtRg.getText())){			
			JOptionPane.showMessageDialog(null, "Número de RG inválido!",
					"A T E N Ç Ã O", JOptionPane.ERROR_MESSAGE);
			txtRg.setText("");
			txtRg.requestFocusInWindow();
			contaCaracteres = 0;
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
			JOptionPane.showMessageDialog(null, "Data de nascimento é maior que a data atual. \n Favor Reconsiderar!", "A T E N Ç Ã O", JOptionPane.ERROR_MESSAGE);
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
			txtCelular.requestFocusInWindow();
			validar = false;
		} else if (!Utils.validaNumeroCelular(txtCelular.getText())){			
			JOptionPane.showMessageDialog(null, "Número de celular inválido!",
					"A T E N Ç Ã O", JOptionPane.ERROR_MESSAGE);
			txtCelular.setText("");
			txtCelular.requestFocusInWindow();
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
			JOptionPane.showMessageDialog(null, mensagem, "A T E N Ç Ã O", JOptionPane.ERROR_MESSAGE);
		}
		
		return validar;
	}
}
