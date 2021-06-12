package com.projeto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.projeto.controller.AlternativaController;
import com.projeto.controller.CategoriaController;
import com.projeto.controller.PerguntaController;
import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;

public class PanelCadastraQuestoes extends JPanel {
	private static final long serialVersionUID = 1L;

	private List<AlternativaVO> listaAlternativas = new ArrayList<>();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JFormattedTextField txtAdicionaCategoria;
	private	JFormattedTextField txtCadastraPergunta;
	private	JFormattedTextField txtCadastraResposta1;
	private	JFormattedTextField txtCadastraResposta2;
	private	JFormattedTextField txtCadastraResposta3;
	private	JFormattedTextField txtCadastraResposta4;
	private	JFormattedTextField txtCadastraResposta5;
	private PerguntaVO perguntaVO = null;
	private JComboBox comboBoxPerguntas; 
//	public JLabel lblNomeUsuario;
	private JLabel lblNomeUsuarioLogado;

	AlternativaController alternativaController = new AlternativaController();
	CategoriaController categoriaController = new CategoriaController();
	PerguntaController perguntaController = new PerguntaController();
	CategoriaVO categoriaVO = new CategoriaVO();

	/**
	 * Create the panel.
	 * @param usuario 
	 */
	public PanelCadastraQuestoes(ProfessorVO usuarioLogado) {
		setBorder(new LineBorder(new Color(250, 128, 114), 6));
		setBackground(new Color(112, 128, 144));
		
		lblNomeUsuarioLogado = new JLabel(usuarioLogado.getNome());
		lblNomeUsuarioLogado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeUsuarioLogado.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblTitulo = new JLabel("Cadastrar Questões");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		comboBoxPerguntas = new JComboBox();
		comboBoxPerguntas.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxPerguntas.setModel(new DefaultComboBoxModel(new String[] { "C A T E G O R I A S" }));
		
		/* Preenche o combo de categorias ao iniciar a tela */
		
		List<CategoriaVO> listaCategorias = new ArrayList<>();
		try {
			listaCategorias = categoriaController.consultaTodasCategorias(usuarioLogado);
			for (CategoriaVO categoriaVO : listaCategorias) {
				comboBoxPerguntas.addItem(categoriaVO.getDescricaoCategoria().toUpperCase());
				revalidate();
			}
			
		} catch (ErroNaConsultaException e1) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar categorias!");
		}
				

		txtAdicionaCategoria = new JFormattedTextField();
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
		txtAdicionaCategoria.setForeground(Color.DARK_GRAY);
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
		formataBotao(btnAdicionaCategoria);
		btnAdicionaCategoria.setToolTipText("Adicionar categoria");
		btnAdicionaCategoria
				.setIcon(new ImageIcon(PanelCadastraQuestoes.class.getResource("/imagens/btnAdiciona.png")));
		btnAdicionaCategoria.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdicionaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoriaVO.setDescricaoCategoria(Utils.formataEspacoUnico(txtAdicionaCategoria.getText()).toString().toUpperCase());
				categoriaVO.setIdDisciplina(usuarioLogado.getIdDisciplina());
				categoriaVO.setIdUsuario(usuarioLogado.getIdUsuario());
				categoriaVO.setAtivada(true);
					try {
						categoriaController.cadastraCategoria(categoriaVO);
						JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!", Constants.SUCESSO,
								JOptionPane.PLAIN_MESSAGE, null);
						comboBoxPerguntas.addItem(Utils.formataEspacoUnico(txtAdicionaCategoria.getText().toString().toUpperCase()));
						txtAdicionaCategoria.setText("Adicionar categoria");
						
					} catch (Exception mensagem) {
						JOptionPane.showMessageDialog(null, mensagem.getMessage(), "ATENÇÃO",
								JOptionPane.ERROR_MESSAGE, null);
					}
			}
		});

		txtCadastraResposta1 = new JFormattedTextField();
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
		txtCadastraResposta1.setForeground(Color.DARK_GRAY);
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
		panelBotoes.setBackground(new Color(112, 128, 144));

		txtCadastraResposta2 = new JFormattedTextField();
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
		txtCadastraResposta2.setForeground(Color.DARK_GRAY);
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

		txtCadastraResposta3 = new JFormattedTextField();
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
		txtCadastraResposta3.setForeground(Color.DARK_GRAY);
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

		txtCadastraResposta4 = new JFormattedTextField();
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
		txtCadastraResposta4.setForeground(Color.DARK_GRAY);
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

		txtCadastraResposta5 = new JFormattedTextField();
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
		txtCadastraResposta5.setForeground(Color.DARK_GRAY);
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

		txtCadastraPergunta = new JFormattedTextField();
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
		txtCadastraPergunta.setForeground(Color.DARK_GRAY);
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
		rdbtnOpcaoCorreta1.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnOpcaoCorreta1.setForeground(Color.BLACK);
		buttonGroup.add(rdbtnOpcaoCorreta1);
		rdbtnOpcaoCorreta1.setBackground(new Color(112, 128, 144));

		JRadioButton rdbtnOpcaoCorreta2 = new JRadioButton("CORRETA");
		rdbtnOpcaoCorreta2.setForeground(Color.BLACK);
		rdbtnOpcaoCorreta2.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonGroup.add(rdbtnOpcaoCorreta2);
		rdbtnOpcaoCorreta2.setBackground(new Color(112, 128, 144));

		JRadioButton rdbtnOpcaoCorreta3 = new JRadioButton("CORRETA");
		rdbtnOpcaoCorreta3.setForeground(Color.BLACK);
		rdbtnOpcaoCorreta3.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonGroup.add(rdbtnOpcaoCorreta3);
		rdbtnOpcaoCorreta3.setBackground(new Color(112, 128, 144));

		JRadioButton rdbtnOpcaoCorreta4 = new JRadioButton("CORRETA");
		rdbtnOpcaoCorreta4.setForeground(Color.BLACK);
		rdbtnOpcaoCorreta4.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonGroup.add(rdbtnOpcaoCorreta4);
		rdbtnOpcaoCorreta4.setBackground(new Color(112, 128, 144));

		JRadioButton rdbtnOpcaoCorreta5 = new JRadioButton("CORRETA");
		rdbtnOpcaoCorreta5.setForeground(Color.BLACK);
		rdbtnOpcaoCorreta5.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonGroup.add(rdbtnOpcaoCorreta5);
		rdbtnOpcaoCorreta5.setBackground(new Color(112, 128, 144));
				
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
										.addGap(18).addComponent(lblNomeUsuarioLogado, GroupLayout.PREFERRED_SIZE, 212,
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
										.addComponent(lblNomeUsuarioLogado, GroupLayout.PREFERRED_SIZE, 29,
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
		panelBotoes.setLayout(new GridLayout(1, 0, 20, 20));

		JButton btnNewButton_2 = new JButton("Limpar");
		formataBotao(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaPreenchimento();
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelBotoes.add(btnNewButton_2);

		JButton btnSalvar = new JButton("Salvar");
		formataBotao(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				capturaDadosDaTela();				
				if(!validaComboBoxCategoria(comboBoxPerguntas)) {
					JOptionPane.showMessageDialog(null, "Selecione uma categoria!",
							Constants.ALERTA, JOptionPane.ERROR_MESSAGE, null);	
					
				}  else if (!perguntaController.validaPergunta(perguntaVO)) {
					JOptionPane.showMessageDialog(null, "Verifique o preenchimento do campo PERGUNTA!",
							Constants.ALERTA
							, JOptionPane.ERROR_MESSAGE, null);					
					
				}else if (!alternativaController.validaAlternativas(listaAlternativas)) {
					JOptionPane.showMessageDialog(null, "Verifique o preenchimento das alternativas!",
							Constants.ALERTA
							, JOptionPane.ERROR_MESSAGE, null);						
				}else if (!validaAlternativaCorreta(buttonGroup)) {
						JOptionPane.showMessageDialog(null, "Marque a alternativa correta!",
								Constants.ALERTA
								, JOptionPane.ERROR_MESSAGE, null);						
				} else {
					try {						
						alternativaController.cadastraQuestao(perguntaVO);						
						JOptionPane.showMessageDialog(null, "Cadastro realizado!", 
								Constants.SUCESSO, JOptionPane.INFORMATION_MESSAGE, null);
						limpaPreenchimento();						
						
					} catch (ErroNoCadastroException mensagem) {						
						JOptionPane.showMessageDialog(null, mensagem, 
								Constants.ALERTA, JOptionPane.ERROR_MESSAGE, null);						
					} catch (SQLException mensagem) {
						JOptionPane.showMessageDialog(null, mensagem, 
								Constants.ALERTA, JOptionPane.ERROR_MESSAGE, null);	
					}
				}
			}

			private void capturaDadosDaTela() {
				listaAlternativas = new ArrayList<>();
				perguntaVO = new PerguntaVO();
				CategoriaVO categoriaVO = new CategoriaVO();
				categoriaVO.setDescricaoCategoria(comboBoxPerguntas.getSelectedItem().toString());
				categoriaVO = categoriaController.buscaCategoriaPorDescricao(comboBoxPerguntas.getSelectedItem().toString());
				perguntaVO.setCategoria(categoriaVO);
				perguntaVO.setIdUsuario(usuarioLogado.getIdUsuario());
				perguntaVO.setTextoPergunta(Utils.formataEspacoUnico(txtCadastraPergunta.getText().toString()));				
				
				AlternativaVO alternativa1 = new AlternativaVO();				
				alternativa1.setTexto(Utils.formataEspacoUnico(txtCadastraResposta1.getText().toString()));
				if (rdbtnOpcaoCorreta1.isSelected()) {
					alternativa1.setAlternativaCorreta(Constants.ALTERNATIVA_CORRETA);
				} else {
					alternativa1.setAlternativaCorreta(Constants.ALTERNATIVA_ERRADA);
				}
				
				AlternativaVO alternativa2 = new AlternativaVO();
				alternativa2.setTexto(Utils.formataEspacoUnico(txtCadastraResposta2.getText().toString()));
				if (rdbtnOpcaoCorreta2.isSelected()) {
					alternativa2.setAlternativaCorreta(Constants.ALTERNATIVA_CORRETA);
				} else {
					alternativa2.setAlternativaCorreta(Constants.ALTERNATIVA_ERRADA);
				}
				
				AlternativaVO alternativa3 = new AlternativaVO();
				alternativa3.setTexto(Utils.formataEspacoUnico(txtCadastraResposta3.getText().toString()));
				if (rdbtnOpcaoCorreta3.isSelected()) {
					alternativa3.setAlternativaCorreta(Constants.ALTERNATIVA_CORRETA);
				} else {
					alternativa3.setAlternativaCorreta(Constants.ALTERNATIVA_ERRADA);
				}
				
				AlternativaVO alternativa4 = new AlternativaVO();
				alternativa4.setTexto(Utils.formataEspacoUnico(txtCadastraResposta4.getText().toString()));
				if (rdbtnOpcaoCorreta4.isSelected()) {
					alternativa4.setAlternativaCorreta(Constants.ALTERNATIVA_CORRETA);
				} else {
					alternativa4.setAlternativaCorreta(Constants.ALTERNATIVA_ERRADA);
				}
				
				AlternativaVO alternativa5 = new AlternativaVO();
				alternativa5.setTexto(Utils.formataEspacoUnico(txtCadastraResposta5.getText().toString()));
				if (rdbtnOpcaoCorreta5.isSelected()) {
					alternativa5.setAlternativaCorreta(Constants.ALTERNATIVA_CORRETA);
				} else {
					alternativa5.setAlternativaCorreta(Constants.ALTERNATIVA_ERRADA);
				}
				
				listaAlternativas.add(alternativa1);
				listaAlternativas.add(alternativa2);
				listaAlternativas.add(alternativa3);
				listaAlternativas.add(alternativa4);
				listaAlternativas.add(alternativa5);
				
				perguntaVO.setListaAlternativas(listaAlternativas);
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
	
	protected void limpaPreenchimento() {
		txtAdicionaCategoria.setText(Constants.ADICINA_CATEGORIA);
		txtCadastraResposta5.setText(Constants.ALTERNATIVA_5);
		txtCadastraResposta4.setText(Constants.ALTERNATIVA_4);
		txtCadastraResposta3.setText(Constants.ALTERNATIVA_3);
		txtCadastraResposta2.setText(Constants.ALTERNATIVA_2);
		txtCadastraResposta1.setText(Constants.ALTERNATIVA_1);
		txtCadastraPergunta.setText(Constants.PERGUNTA);
		comboBoxPerguntas.setSelectedIndex(0);
		buttonGroup.clearSelection();		
	}
	
public JButton formataBotao(JButton botao) {
		
		botao.setBackground(UIManager.getColor("Button.light"));
		botao.setForeground(UIManager.getColor("Button.foreground"));
		botao.addMouseListener(new MouseAdapter() {			
			
			@Override
			public void mouseEntered(MouseEvent e) {
				botao.setBackground(Color.darkGray);
				botao.setForeground(UIManager.getColor("Button.light"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				botao.setBackground(UIManager.getColor("Button.light"));
				botao.setForeground(UIManager.getColor("Button.foreground"));
			}
		});
		return botao;
	}
}


