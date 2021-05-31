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
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections4.map.HashedMap;

import com.mysql.cj.util.Util;
import com.projeto.controller.AlternativaController;
import com.projeto.controller.CategoriaController;
import com.projeto.controller.PerguntaController;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.placeholder.PlaceholderTextField;
import com.projeto.repository.Constants;
import com.projeto.repository.GeradorPlanilha;
import com.projeto.repository.Utils;
import com.projeto.seletor.PerguntaSeletor;
import javax.swing.ImageIcon;

public class PanelConsultaQuestoes extends JPanel {
	private static final long serialVersionUID = 1L;

	private String[] nomeColunasAlternativas = { "A L T E R N A T I V A ", "S T A T U S" };
	private String[] nomeColunasPerguntas = { "P E R G U N T A", "C A T E G O R I A" };
	private AlternativaController alternativaController = new AlternativaController();
	private CategoriaController categoriaController = new CategoriaController();
	private PerguntaController perguntaController = new PerguntaController();
	private List<PerguntaVO> perguntas = new ArrayList<>();
	private PlaceholderTextField textFieldBusca;
	private JComboBox comboCategorias;
	private JTable tableAlternativas;
	private JTable tableConsulta;

	Map<Integer, String> mapCategorias = new HashedMap<>();

	/**
	 * Create the panel.
	 */
	public PanelConsultaQuestoes() {
		setBackground(new Color(112, 128, 144));
		setBorder(new LineBorder(new Color(250, 128, 114), 5));

		JLabel lblNomeUsuario = new JLabel("Nome Usuário");
		lblNomeUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));

		tableConsulta = new JTable();
		tableConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int perguntaSelecionada = tableConsulta.getSelectedRow() - 1;

				if (perguntaSelecionada >= 0) {
					PerguntaVO pergunta = perguntas.get(perguntaSelecionada);
					preencherAlternativas(pergunta);
				}
			}
		});

		comboCategorias = new JComboBox();
		comboCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarPerguntas();
			}
		});
		comboCategorias.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboCategorias.setModel(new DefaultComboBoxModel(new String[] { "CATEGORIAS" }));

		/**
		 * Preenche o combo box com as categorias ao iniciar a tela
		 * 
		 */
		List<CategoriaVO> categorias = new ArrayList<>();
		categorias = categoriaController.consultaTodasCategorias();
		for (CategoriaVO categoriaVO : categorias) {
			comboCategorias.addItem(categoriaVO.getDescricaoCategoria().toUpperCase());
			mapCategorias.put(categoriaVO.getIdCategoria(), categoriaVO.getDescricaoCategoria());			
		}

		JPanel panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(112, 128, 144));

		tableAlternativas = new JTable();
		tableAlternativas.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));

		limpaTabelaAlternativas();
		limpaTabelaPerguntas();

		JLabel lblAlternativas = new JLabel("ALTERNATIVAS");
		lblAlternativas.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblPerguntas = new JLabel("PERGUNTAS");
		lblPerguntas.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblTitulo = new JLabel("Consultar Questões");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));

		textFieldBusca = new PlaceholderTextField();
		textFieldBusca.setPlaceholder("Digite o texto para busca");
		textFieldBusca.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tableAlternativas, GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(tableConsulta, GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textFieldBusca, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
									.addGap(50)
									.addComponent(comboCategorias, 0, 341, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
							.addGap(54)
							.addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addGap(26))
						.addComponent(lblPerguntas, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAlternativas, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(360, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboCategorias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldBusca, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGap(33)
					.addComponent(lblPerguntas)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tableConsulta, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(lblAlternativas)
					.addGap(18)
					.addComponent(tableAlternativas, GroupLayout.PREFERRED_SIZE, 10, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(panelBotoes, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelBotoes.setLayout(new GridLayout(1, 0, 10, 10));
		
		JButton btnGerarXls = new JButton("   Salvar Excel");	
		btnGerarXls.setBackground(UIManager.getColor("Button.light"));
		btnGerarXls.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGerarXls.setBackground(new Color(153, 255, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGerarXls.setBackground(UIManager.getColor("Button.light"));
			}
		});
		btnGerarXls.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGerarXls.setToolTipText("Salva como planilha Excel ");
		panelBotoes.add(btnGerarXls);
		btnGerarXls.setIcon(new ImageIcon(PanelConsultaQuestoes.class.getResource("/imagens/excel.png")));
		btnGerarXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Salvar relatório como...");
				
				int resultado = jfc.showSaveDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido =jfc.getSelectedFile().getAbsolutePath();
					GeradorPlanilha geradorPlanilha = new GeradorPlanilha();
					try {
						geradorPlanilha.gerarPlanilhaPerguntas(perguntas, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, "Planilha gerada com sucesso!", Constants.SUCESSO,
								JOptionPane.INFORMATION_MESSAGE, null);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Erro ao gerar planilha!", "ATENÇÃO",
								JOptionPane.ERROR_MESSAGE, null);
					}
				}				
			}
		});
		
		JButton btnExcluir = new JButton("EXCLUIR");

		formataBotao(btnExcluir);

		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelBotoes.add(btnExcluir);

		JButton btnAlterar = new JButton("ALTERAR");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] opcoes = { "Escolha uma opção", "CATEGORIA", "PERGUNTA", "ALTERNATIVA" };
				String opcaoEscolhida = (String) JOptionPane.showInputDialog(null, null, "ALTERAR",
						JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
								
				if (opcaoEscolhida != null && !opcaoEscolhida.equalsIgnoreCase("Escolha uma opção")) {

					switch (opcaoEscolhida) {

					case "CATEGORIA":
						preparaAlteracaoCategoria();
						break;
						
					case "PERGUNTA":

						break;
					case "ALTERNATIVA":

						break;

					default:
						break;
					}
				}
			}

			private void preparaAlteracaoCategoria() {
				if (comboCategorias.getSelectedIndex() != 0) {
					String categoriaEscolhida = comboCategorias.getSelectedItem().toString();
					String categoriaAlterada = Utils.formataEspacoUnico(JOptionPane.showInputDialog(null, categoriaEscolhida,
							"Digite a alteração desejada!", JOptionPane.QUESTION_MESSAGE).toUpperCase());
					
					boolean alteracaoConfirmada = categoriaController.alteraCategoria(categoriaEscolhida,
							categoriaAlterada);
					if (alteracaoConfirmada) {
						JOptionPane.showMessageDialog(null, "Alteração efetuada!", Constants.SUCESSO,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Alteração NÃO realizada!", Constants.ALERTA,
								JOptionPane.ERROR_MESSAGE, null);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Escolha uma categoria!", Constants.ALERTA,
							JOptionPane.ERROR_MESSAGE, null);
				}

			}
		});
		formataBotao(btnAlterar);
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelBotoes.add(btnAlterar);

		JButton btnConsultar = new JButton("CONSULTAR");
		formataBotao(btnConsultar);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarPerguntas();

			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelBotoes.add(btnConsultar);

		JButton btnSalvar = new JButton("SALVAR");
		formataBotao(btnSalvar);
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelBotoes.add(btnSalvar);
		setLayout(groupLayout);

	}

	protected void consultarPerguntas() {
		limpaTabelaPerguntas();
		limpaTabelaAlternativas();
		PerguntaSeletor perguntaSeletor = new PerguntaSeletor();

		if (Utils.stringValida(textFieldBusca.getText().toString().trim())) {
			perguntaSeletor.setTexto(Utils.formataEspacoUnico(textFieldBusca.getText().toString()));
		} else {
			perguntaSeletor.setTexto("");
		}

		if (comboCategorias.getSelectedIndex() > 0) {
			perguntaSeletor.setCategoria(comboCategorias.getSelectedItem().toString());
			int indexEscolhido = getChavePorValor(mapCategorias, perguntaSeletor.getCategoria());
			perguntaSeletor.setIdCategoria(indexEscolhido);

		} else {
			perguntaSeletor.setCategoria("");
		}

		try {
			perguntas = perguntaController.buscaComSeletor(perguntaSeletor);

			if (perguntas.size() == 0 || perguntas == null) {
								
			} else {
				preencherTabelaPerguntas(perguntas);
			}
			
		} catch (Exception mensagem) {
			JOptionPane.showMessageDialog(null, mensagem.getMessage(), Constants.ALERTA,
					JOptionPane.ERROR_MESSAGE, null);
		}		
	}

	protected void preencherAlternativas(PerguntaVO pergunta) {
		List<AlternativaVO> alternativas = alternativaController.buscaAlternativas(pergunta);
		preencherTabelaAlternativas(alternativas);
	}

	private void preencherTabelaAlternativas(List<AlternativaVO> alternativas) {
		limpaTabelaAlternativas();
		DefaultTableModel modeloTabela = (DefaultTableModel) tableAlternativas.getModel();
		tableAlternativas.getColumnModel().getColumn(0).setPreferredWidth(650);

		for (AlternativaVO alternativaVO : alternativas) {
			Object[] novaLinha = new Object[2];
			
			novaLinha[0] = alternativaVO.getTexto().toUpperCase();
			novaLinha[1] = alternativaVO.getAlternativaCorreta().toUpperCase();
			modeloTabela.addRow(novaLinha);
		}		
	}

	private void limpaTabelaAlternativas() {
		tableAlternativas
				.setModel(new DefaultTableModel(new Object[][] { nomeColunasAlternativas }, nomeColunasAlternativas));
		tableAlternativas.getColumnModel().getColumn(0).setPreferredWidth(650);
	}

	private void preencherTabelaPerguntas(List<PerguntaVO> perguntas) {
		limpaTabelaPerguntas();
		DefaultTableModel modeloTabela = (DefaultTableModel) tableConsulta.getModel();
		tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(650);

		for (PerguntaVO perguntaVO : perguntas) {
			Object[] novaLinha = new Object[2];
			
			novaLinha[0] = perguntaVO.getTextoPergunta().toUpperCase();
			novaLinha[1] = perguntaVO.getCategoria().getDescricaoCategoria().toUpperCase();
			modeloTabela.addRow(novaLinha);			
		}		
	}

	private void limpaTabelaPerguntas() {
		tableConsulta.setModel(new DefaultTableModel(new Object[][] { nomeColunasPerguntas }, nomeColunasPerguntas));
		tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(650);
		Font f1 = new Font(Font.SERIF, Font.PLAIN, 14);

	}

	public static <T, E> T getChavePorValor(Map<T, E> map, E value) {

		for (Entry<T, E> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
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
