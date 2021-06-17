package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.projeto.controller.UsuarioController;
import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;
import com.projeto.exceptions.CpfExistenteException;
import com.projeto.exceptions.RgExistenteException;
import com.projeto.model.entity.CoordenadorVO;
import com.projeto.model.entity.DisciplinaVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.placeholder.PlaceholderPasswordField;
import com.projeto.placeholder.PlaceholderTextField;
import com.projeto.repository.Utils;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class PanelCadastrarCoordenador extends JPanel {
	private PlaceholderTextField txtNome;
	private JFormattedTextField txtRg;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtCelular;
	private PlaceholderPasswordField pswSenha;
	private PlaceholderPasswordField pswConfirmarSenha;
	private DatePickerSettings dateSettings;
	private DatePicker dataNascimento;
	private JButton btnCadastrar;
	private JCheckBox cbConfirmarSenha;
	private JComboBox cbxTurno;
	private JRadioButton rdbDeficienteNao;
	private JRadioButton rdbDeficienteSim;
	private JRadioButton rdbFeminino;
	private JRadioButton rdbMasculino;
	private UsuarioVO coordenador = new CoordenadorVO();
	private UsuarioController UsuarioController = new UsuarioController();
	private JCheckBox cbMostrarSenha;
	private final ButtonGroup buttonGroupDeficiente = new ButtonGroup();
	private final ButtonGroup buttonGroupSexo = new ButtonGroup();

	public PanelCadastrarCoordenador (UsuarioVO coordenador) {
		this.coordenador = coordenador;
		construitTela();
	}
	
	public PanelCadastrarCoordenador() {
		construitTela();
	}
	
	/**
	 * Create the panel.
	 */
	public void construitTela() {
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
		
		rdbMasculino = new JRadioButton("Masculino");
		buttonGroupSexo.add(rdbMasculino);
		
		rdbFeminino = new JRadioButton("Feminino");
		buttonGroupSexo.add(rdbFeminino);
		
		JLabel lblPosuuiDeficiencia = new JLabel("Possui alguma deficiência?");
		
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
		pswSenha.setColumns(10);
		
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
		
		JLabel lblDicaSenha = new JLabel("A senha deve conter de 8 a 30 caracteres.");
		
		btnCadastrar = new JButton("C A D A S T R A R");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validarCampos = validarCampos();
				boolean senhaValida = validarSenha();
				
				if (validarCampos && senhaValida) {
					cadastrarCoordenador();
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
					cadastrarCoordenador();
				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnLimpar = new JButton("L I M P A R");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTela();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(225)
							.addComponent(lblCadastrarCoordenador, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
							.addGap(228))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblDataDeNascimento)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(dataNascimento, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblSexo)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbMasculino)
									.addGap(18)
									.addComponent(rdbFeminino)
									.addGap(62))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPosuuiDeficiencia)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbDeficienteSim)
									.addGap(12)
									.addComponent(rdbDeficienteNao)
									.addGap(18)
									.addComponent(lblCelular)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCelular, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblTurno)
										.addComponent(lblNome))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(cbxTurno, 0, 200, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblRg)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtRg, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblCpf)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtCpf, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
										.addComponent(txtNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)))
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
														.addComponent(pswConfirmarSenha, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)))
												.addGroup(gl_panel.createSequentialGroup()
													.addGap(84)
													.addComponent(lblDicaSenha, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))))
										.addComponent(pswSenha, GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE))))))
					.addGap(22))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 410, Short.MAX_VALUE)
					.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(txtCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf)
						.addComponent(txtRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRg)
						.addComponent(lblTurno)
						.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeNascimento)
						.addComponent(dataNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSexo)
						.addComponent(rdbMasculino)
						.addComponent(rdbFeminino))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPosuuiDeficiencia)
						.addComponent(rdbDeficienteNao)
						.addComponent(rdbDeficienteSim)
						.addComponent(lblCelular)
						.addComponent(txtCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
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
					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		if (coordenador.getIdUsuario() != null) {
			btnAtualizar.setVisible(true);
			btnCadastrar.setVisible(false);
			preencherCoordenadorNaTela((CoordenadorVO)coordenador);
		} else {
			btnAtualizar.setVisible(false);
			btnCadastrar.setVisible(true);
		}

	}

	private void preencherCoordenadorNaTela(CoordenadorVO coordenador) {
		txtNome.setText(coordenador.getNome());
		cbxTurno.setSelectedItem(coordenador.getTurno().toString());
		txtRg.setText(coordenador.getRg());
		txtCpf.setText(coordenador.getCpf());
		dataNascimento.setDate(coordenador.getDataNascimento());
		
		rdbFeminino.setSelected(true);
		if (coordenador.getSexo() == 'M') {
			rdbMasculino.setSelected(true);
		}
		
		rdbDeficienteNao.setSelected(true);
		if (coordenador.isPossuiDeficiencia()) {
			rdbDeficienteSim.setSelected(true);
		}
		
		txtCelular.setText(coordenador.getCelular());
		pswSenha.setText(coordenador.getSenha());
		pswConfirmarSenha.setText(coordenador.getSenha());
		
	}

	protected void limparTela() {
		txtNome.setText("");
		cbxTurno.setSelectedIndex(0);
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

	protected void cadastrarCoordenador() {
		coordenador.setNome(txtNome.getText());
		
		if (cbxTurno.getSelectedItem().equals("MATUTINO")) {
			coordenador.setTurno(TurnoEnum.MATUTINO);
		} else if (cbxTurno.getSelectedItem().equals("VESPERTINO")) {
			coordenador.setTurno(TurnoEnum.VESPERTINO);
		} else if (cbxTurno.getSelectedItem().equals("NOTURNO")) {
			coordenador.setTurno(TurnoEnum.NOTURNO);
		}
		
		coordenador.setRg(txtRg.getText().replace(".", ""));
		coordenador.setCpf(txtCpf.getText().replace(".", "").replace("-", ""));
		coordenador.setDataNascimento(dataNascimento.getDate());
		
		coordenador.setSexo('F');
		if (rdbMasculino.isSelected()) {
			coordenador.setSexo('M');
		}
		
		coordenador.setPossuiDeficiencia(false);
		if (rdbDeficienteSim.isSelected()) {
			coordenador.setPossuiDeficiencia(true);
		}
		
		coordenador.setCelular(txtCelular.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
		coordenador.setSenha(pswSenha.getText());
		coordenador.setTipo(TipoUsuarioEnum.COORDENADOR);
		
		String mensagem = "";
		String titulo = "";
		if (coordenador.getIdUsuario() != null) {
			titulo = "Atualizar";
			boolean atualizou = false;
			mensagem = "Erro ao atualizar coordenador.";
			
			try {
				atualizou = UsuarioController.alterar(coordenador);
			} catch (RgExistenteException | CpfExistenteException e) {
				mensagem = e.getMessage();
				titulo = "Aviso";
			}
			
			if (atualizou) {
				mensagem = "Coordenador atualizado com sucesso!";
			}
		} else {
			
			try {
				titulo = "Cadastrar";
				coordenador = UsuarioController.cadastrar(coordenador);
				if (coordenador.getIdUsuario() != null) {
					mensagem = "Coordenador cadastrado com sucesso!";
					limparTela();
				} else {
					mensagem = "Erro ao cadastrar coordenador.";
				}
			} catch (RgExistenteException | CpfExistenteException e) {
				mensagem = e.getMessage();
				titulo = "Aviso";
			}
		}
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
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
		
		mensagem.append("são obrigatórios.\n Favor preenchê-los!");
		
		if (!validar) {
			JOptionPane.showMessageDialog(null, mensagem, "A T E N Ç Ã O", JOptionPane.WARNING_MESSAGE);
		}
		
		return validar;
	}

}
