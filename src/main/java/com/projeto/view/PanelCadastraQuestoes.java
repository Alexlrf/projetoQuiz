package com.projeto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.projeto.controller.AlternativaController;
import com.projeto.controller.CategoriaController;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PanelCadastraQuestoes extends JPanel {
	private static final long serialVersionUID = 1L;

	private JComboBox comboBoxPerguntas = new JComboBox();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<String> listaAlternativas = new ArrayList<>();
	private PerguntaVO perguntaVO = null;
	public JLabel lblNomeUsuario;

	AlternativaController alternativaController = new AlternativaController();
	CategoriaVO categoriaVO = new CategoriaVO();
	CategoriaController categoriaController = new CategoriaController();

	/**
	 * Create the panel.
	 */
	public PanelCadastraQuestoes() {
		setBorder(new LineBorder(new Color(250, 128, 114), 6));
		setBackground(new Color(222, 184, 135));

		JLabel lblTitulo = new JLabel("Cadastrar Questões");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));

		JFormattedTextField txtAdicionaCategoria = new JFormattedTextField();
		txtAdicionaCategoria.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!Utils.stringValida(txtAdicionaCategoria.getText())) {
					txtAdicionaCategoria.setText("Adicionar categoria");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (txtAdicionaCategoria.getText().equalsIgnoreCase("Adicionar categoria")) {
					txtAdicionaCategoria.setText("");
				}
			}
		});
		txtAdicionaCategoria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (txtAdicionaCategoria.getText().equalsIgnoreCase("Adicionar categoria")) {
					txtAdicionaCategoria.setText("");
				}
			}
		});
		txtAdicionaCategoria.setForeground(new Color(128, 128, 128));
		txtAdicionaCategoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtAdicionaCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtAdicionaCategoria.getText().equalsIgnoreCase("Adicionar categoria")) {
					txtAdicionaCategoria.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (txtAdicionaCategoria.getText().isEmpty()) {
					txtAdicionaCategoria.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (txtAdicionaCategoria.getText().trim().isEmpty()) {
					txtAdicionaCategoria.setText("Adicionar categoria");
				}
			}
		});

		txtAdicionaCategoria.setText("Adicionar categoria");

		JButton btnAdicionaCategoria = new JButton("");
		btnAdicionaCategoria.setToolTipText("Adicionar categoria");
		btnAdicionaCategoria
				.setIcon(new ImageIcon(PanelCadastraQuestoes.class.getResource("/imagens/btnAdiciona.png")));
		btnAdicionaCategoria.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdicionaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					categoriaVO.setDescricaoCategoria(txtAdicionaCategoria.getText().toString().toUpperCase());
					
					try {
						categoriaController.cadastraCategoria(categoriaVO);
						JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!", Constants.SUCESSO,
								JOptionPane.PLAIN_MESSAGE, null);
						comboBoxPerguntas.addItem(Utils.formataEspacoUnico(txtAdicionaCategoria.getText().toString()));
						txtAdicionaCategoria.setText("Adicionar categoria");
						
					} catch (Exception mensagem) {
						JOptionPane.showMessageDialog(null, mensagem.getMessage(), "ATENÇÃO",
								JOptionPane.ERROR_MESSAGE, null);
					}

			}
		});

		comboBoxPerguntas = new JComboBox();
		comboBoxPerguntas.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxPerguntas.setModel(new DefaultComboBoxModel(new String[] { "C A T E G O R I A S" }));

		JFormattedTextField txtCadastraResposta1 = new JFormattedTextField();
		txtCadastraResposta1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!Utils.stringValida(txtCadastraResposta1.getText())) {
					txtCadastraResposta1.setText(Constants.ALTERNATIVA_1);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (txtCadastraResposta1.getText().equalsIgnoreCase(Constants.ALTERNATIVA_1)) {
					txtCadastraResposta1.setText("");
				}
			}
		});
		txtCadastraResposta1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (txtCadastraResposta1.getText().equalsIgnoreCase(Constants.ALTERNATIVA_1)) {
					txtCadastraResposta1.setText("");
				}
			}
		});
		txtCadastraResposta1.setForeground(new Color(128, 128, 128));
		txtCadastraResposta1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCadastraResposta1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtCadastraResposta1.getText().equalsIgnoreCase(Constants.ALTERNATIVA_1)) {
					txtCadastraResposta1.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (txtCadastraResposta1.getText().isEmpty()) {
					txtCadastraResposta1.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (txtCadastraResposta1.getText().trim().isEmpty()) {
					txtCadastraResposta1.setText(Constants.ALTERNATIVA_1);
				}
			}
		});
		txtCadastraResposta1.setText(Constants.ALTERNATIVA_1);

		JPanel panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(222, 184, 135));

		JFormattedTextField txtCadastraResposta2 = new JFormattedTextField();
		txtCadastraResposta2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!Utils.stringValida(txtCadastraResposta2.getText())) {
					txtCadastraResposta2.setText(Constants.ALTERNATIVA_2);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (txtCadastraResposta2.getText().equalsIgnoreCase(Constants.ALTERNATIVA_2)) {
					txtCadastraResposta2.setText("");
				}
			}
		});
		txtCadastraResposta2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (txtCadastraResposta2.getText().equalsIgnoreCase(Constants.ALTERNATIVA_2)) {
					txtCadastraResposta2.setText("");
				}
			}
		});
		txtCadastraResposta2.setForeground(new Color(128, 128, 128));
		txtCadastraResposta2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCadastraResposta2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtCadastraResposta2.getText().equalsIgnoreCase(Constants.ALTERNATIVA_2)) {
					txtCadastraResposta2.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (txtCadastraResposta2.getText().isEmpty()) {
					txtCadastraResposta2.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (txtCadastraResposta2.getText().trim().isEmpty()) {
					txtCadastraResposta2.setText(Constants.ALTERNATIVA_2);
				}
			}
		});
		txtCadastraResposta2.setText(Constants.ALTERNATIVA_2);

		JFormattedTextField txtCadastraResposta3 = new JFormattedTextField();
		txtCadastraResposta3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!Utils.stringValida(txtCadastraResposta3.getText())) {
					txtCadastraResposta3.setText(Constants.ALTERNATIVA_3);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (txtCadastraResposta3.getText().equalsIgnoreCase(Constants.ALTERNATIVA_3)) {
					txtCadastraResposta3.setText("");
				}
			}
		});
		txtCadastraResposta3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (txtCadastraResposta3.getText().equalsIgnoreCase(Constants.ALTERNATIVA_3)) {
					txtCadastraResposta3.setText("");
				}
			}
		});
		txtCadastraResposta3.setForeground(new Color(128, 128, 128));
		txtCadastraResposta3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCadastraResposta3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtCadastraResposta3.getText().equalsIgnoreCase(Constants.ALTERNATIVA_3)) {
					txtCadastraResposta3.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (txtCadastraResposta3.getText().isEmpty()) {
					txtCadastraResposta3.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (txtCadastraResposta3.getText().trim().isEmpty()) {
					txtCadastraResposta3.setText(Constants.ALTERNATIVA_3);
				}
			}
		});
		txtCadastraResposta3.setText(Constants.ALTERNATIVA_3);

		JFormattedTextField txtCadastraResposta4 = new JFormattedTextField();
		txtCadastraResposta4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!Utils.stringValida(txtCadastraResposta4.getText())) {
					txtCadastraResposta4.setText(Constants.ALTERNATIVA_4);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (txtCadastraResposta4.getText().equalsIgnoreCase(Constants.ALTERNATIVA_4)) {
					txtCadastraResposta4.setText("");
				}
			}
		});
		txtCadastraResposta4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (txtCadastraResposta4.getText().equalsIgnoreCase(Constants.ALTERNATIVA_4)) {
					txtCadastraResposta4.setText("");
				}
			}
		});
		txtCadastraResposta4.setForeground(new Color(128, 128, 128));
		txtCadastraResposta4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCadastraResposta4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtCadastraResposta4.getText().equalsIgnoreCase(Constants.ALTERNATIVA_4)) {
					txtCadastraResposta4.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (txtCadastraResposta4.getText().isEmpty()) {
					txtCadastraResposta4.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (txtCadastraResposta4.getText().trim().isEmpty()) {
					txtCadastraResposta4.setText(Constants.ALTERNATIVA_4);
				}
			}
		});
		txtCadastraResposta4.setText(Constants.ALTERNATIVA_4);

		JFormattedTextField txtCadastraResposta5 = new JFormattedTextField();
		txtCadastraResposta5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!Utils.stringValida(txtCadastraResposta5.getText())) {
					txtCadastraResposta5.setText(Constants.ALTERNATIVA_5);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (txtCadastraResposta5.getText().equalsIgnoreCase(Constants.ALTERNATIVA_5)) {
					txtCadastraResposta5.setText("");
				}
			}
		});
		txtCadastraResposta5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (txtCadastraResposta5.getText().equalsIgnoreCase(Constants.ALTERNATIVA_5)) {
					txtCadastraResposta5.setText("");
				}
			}
		});
		txtCadastraResposta5.setForeground(new Color(128, 128, 128));
		txtCadastraResposta5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCadastraResposta5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtCadastraResposta5.getText().equalsIgnoreCase(Constants.ALTERNATIVA_5)) {
					txtCadastraResposta5.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (txtCadastraResposta5.getText().isEmpty()) {
					txtCadastraResposta5.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (txtCadastraResposta5.getText().trim().isEmpty()) {
					txtCadastraResposta5.setText(Constants.ALTERNATIVA_5);
				}
			}
		});
		txtCadastraResposta5.setText(Constants.ALTERNATIVA_5);

		JFormattedTextField txtCadastraPergunta = new JFormattedTextField();
		txtCadastraPergunta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!Utils.stringValida(txtCadastraPergunta.getText())) {
					txtCadastraPergunta.setText(Constants.PERGUNTA);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (txtCadastraPergunta.getText().equalsIgnoreCase(Constants.PERGUNTA)) {
					txtCadastraPergunta.setText("");
				}
			}
		});
		txtCadastraPergunta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (txtCadastraPergunta.getText().equalsIgnoreCase(Constants.PERGUNTA)) {
					txtCadastraPergunta.setText("");
				}
			}
		});
		txtCadastraPergunta.setForeground(new Color(128, 128, 128));
		txtCadastraPergunta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtCadastraPergunta.getText().equalsIgnoreCase(Constants.PERGUNTA)) {
					txtCadastraPergunta.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (txtCadastraPergunta.getText().isEmpty()) {
					txtCadastraPergunta.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (txtCadastraPergunta.getText().trim().isEmpty()) {
					txtCadastraPergunta.setText(Constants.PERGUNTA);
				}
			}
		});
		txtCadastraPergunta.setText("Digite aqui a PERGUNTA");
		txtCadastraPergunta.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JRadioButton rdbtnOpcaoCorreta1 = new JRadioButton("CORRETA");
		buttonGroup.add(rdbtnOpcaoCorreta1);
		rdbtnOpcaoCorreta1.setBackground(new Color(222, 184, 135));

		JRadioButton rdbtnOpcaoCorreta2 = new JRadioButton("CORRETA");
		buttonGroup.add(rdbtnOpcaoCorreta2);
		rdbtnOpcaoCorreta2.setBackground(new Color(222, 184, 135));

		JRadioButton rdbtnOpcaoCorreta3 = new JRadioButton("CORRETA");
		buttonGroup.add(rdbtnOpcaoCorreta3);
		rdbtnOpcaoCorreta3.setBackground(new Color(222, 184, 135));

		JRadioButton rdbtnOpcaoCorreta4 = new JRadioButton("CORRETA");
		buttonGroup.add(rdbtnOpcaoCorreta4);
		rdbtnOpcaoCorreta4.setBackground(new Color(222, 184, 135));

		JRadioButton rdbtnOpcaoCorreta5 = new JRadioButton("CORRETA");
		buttonGroup.add(rdbtnOpcaoCorreta5);
		rdbtnOpcaoCorreta5.setBackground(new Color(222, 184, 135));

		lblNomeUsuario = new JLabel("Nome Usuário");
		lblNomeUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(panelBotoes,
								GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(10).addGroup(groupLayout
								.createParallelGroup(
										Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtAdicionaCategoria, GroupLayout.DEFAULT_SIZE, 224,
												Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnAdicionaCategoria, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(comboBoxPerguntas, 0, 220, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
										.addGap(18).addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 212,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(txtCadastraPergunta, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnOpcaoCorreta1).addComponent(txtCadastraResposta1,
												GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnOpcaoCorreta2).addComponent(txtCadastraResposta2,
												GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnOpcaoCorreta3).addComponent(txtCadastraResposta3,
												GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnOpcaoCorreta4).addComponent(txtCadastraResposta4,
												GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnOpcaoCorreta5).addComponent(txtCadastraResposta5,
												GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(70).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdicionaCategoria, GroupLayout.PREFERRED_SIZE, 29,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
										.addComponent(comboBoxPerguntas, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtAdicionaCategoria, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE)))))
				.addGap(33)
				.addComponent(txtCadastraPergunta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE).addComponent(rdbtnOpcaoCorreta1)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtCadastraResposta1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				.addGap(13).addComponent(rdbtnOpcaoCorreta2).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtCadastraResposta2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				.addGap(10).addComponent(rdbtnOpcaoCorreta3).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtCadastraResposta3, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				.addGap(15).addComponent(rdbtnOpcaoCorreta4).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtCadastraResposta4, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				.addGap(9).addComponent(rdbtnOpcaoCorreta5).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtCadastraResposta5, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				.addGap(45).addComponent(panelBotoes, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addGap(9)));
		panelBotoes.setLayout(new GridLayout(1, 0, 10, 5));

		JButton btnNewButton_4 = new JButton("New button");
		panelBotoes.add(btnNewButton_4);

		JButton btnNewButton_3 = new JButton("New button");
		panelBotoes.add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("New button");
		panelBotoes.add(btnNewButton_2);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				capturaDadosDaTela();							

				if (!validaAlternativaCorreta(buttonGroup)) {
					JOptionPane.showMessageDialog(null, "Verifique o preenchimento\n Marque a alternativa correta", Constants.ALERTA
							, JOptionPane.ERROR_MESSAGE, null);
					
				} else if(!validaComboBoxCategoria(comboBoxPerguntas)) {
					JOptionPane.showMessageDialog(null, "Selecione uma categoria", Constants.ALERTA, JOptionPane.ERROR_MESSAGE, null);
					
				} else {
					try {						
						alternativaController.cadastraAlternativas(perguntaVO, listaAlternativas);						
						JOptionPane.showMessageDialog(null, "Cadastro realizado!", Constants.SUCESSO, JOptionPane.INFORMATION_MESSAGE, null);
						limpaPreenchimento();						
						
					} catch (ErroNoCadastroException mensagem) {						
						JOptionPane.showMessageDialog(null, mensagem.getMessage(), Constants.ALERTA, JOptionPane.ERROR_MESSAGE, null);						
					}
				}
			}

			private void capturaDadosDaTela() {
				listaAlternativas = new ArrayList<>();
				perguntaVO = new PerguntaVO();				
				perguntaVO.setCategoria(comboBoxPerguntas.getSelectedItem().toString());
				perguntaVO.setTexto(Utils.formataEspacoUnico(txtCadastraPergunta.getText().toString()));
				listaAlternativas.add(Utils.formataEspacoUnico(txtCadastraResposta1.getText().toString()));
				listaAlternativas.add(Utils.formataEspacoUnico(txtCadastraResposta2.getText().toString()));
				listaAlternativas.add(Utils.formataEspacoUnico(txtCadastraResposta3.getText().toString()));
				listaAlternativas.add(Utils.formataEspacoUnico(txtCadastraResposta4.getText().toString()));
				listaAlternativas.add(Utils.formataEspacoUnico(txtCadastraResposta5.getText().toString()));
				
			}

			private void limpaPreenchimento() {
				buttonGroup.clearSelection();
				comboBoxPerguntas.setSelectedIndex(0);
				txtCadastraPergunta.setText(Constants.PERGUNTA);
				txtCadastraResposta1.setText(Constants.ALTERNATIVA_1);
				txtCadastraResposta2.setText(Constants.ALTERNATIVA_2);
				txtCadastraResposta3.setText(Constants.ALTERNATIVA_3);
				txtCadastraResposta4.setText(Constants.ALTERNATIVA_4);
				txtCadastraResposta5.setText(Constants.ALTERNATIVA_5);
				
			}

			private boolean validaAlternativaCorreta(ButtonGroup buttonGroup) {
				boolean validado = true;
				if (!rdbtnOpcaoCorreta1.isSelected()&& !rdbtnOpcaoCorreta2.isSelected()&& !rdbtnOpcaoCorreta3.isSelected()
						&& !rdbtnOpcaoCorreta4.isSelected()&& !rdbtnOpcaoCorreta5.isSelected()) {
					validado = false;
				}
				return validado;
			}

			private boolean validaComboBoxCategoria(JComboBox comboBoxPerguntas) {
				boolean categoriaValido = true;
				if (comboBoxPerguntas.getSelectedIndex() < 1) {					
					categoriaValido = false;
				}
				return categoriaValido;				
			}
		});
		
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelBotoes.add(btnSalvar);
		setLayout(groupLayout);

	}
}