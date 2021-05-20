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

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.projeto.controller.AlternativaController;
import com.projeto.controller.CategoriaController;
import com.projeto.controller.PerguntaController;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.placeholder.PlaceholderTextField;

public class PanelConsultaQuestoes extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private String[] nomeColunasAlternativas = {"A L T E R N A T I V A ", "S T A T U S"};
	private String[] nomeColunasPerguntas = {"P E R G U N T A", "C A T E G O R I A"};
	private AlternativaController alternativaController = new AlternativaController();
	private CategoriaController categoriaController = new CategoriaController();
	private PerguntaController perguntaController = new PerguntaController();
	private List<PerguntaVO> perguntas = new ArrayList<>();
	private PlaceholderTextField textoDeBusca;
	private JTable tableAlternativas;
	private JTable tableConsulta;

	/**
	 * Create the panel.
	 */
	public PanelConsultaQuestoes() {
		setBackground(new Color(112, 128, 144));
		setBorder(new LineBorder(new Color(250, 128, 114), 5));
		
		textoDeBusca = new PlaceholderTextField();
		textoDeBusca.setText("Digite o texto para busca");
		
		JLabel lblNomeUsuario = new JLabel("Nome Usuário");
		lblNomeUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tableConsulta = new JTable();
		tableConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int perguntaSelecionada = tableConsulta.getSelectedRow()-1;
				 
				PerguntaVO pergunta = perguntas.get(perguntaSelecionada);
				
				preencherAlternativas(pergunta);
				
			}
		});
		
		JComboBox comboCategorias = new JComboBox();
		comboCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (comboCategorias.getSelectedIndex() > 0) {
					String categoriaEscolhida = comboCategorias.getSelectedItem().toString();
					perguntas = perguntaController.buscaPorCategoriaEscolhida(categoriaEscolhida);
					
					if (perguntas.size() > 0) {						
						preencherTabelaPerguntas(perguntas, categoriaEscolhida);						
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível realizar consulta!", "ATENÇÃO",
								JOptionPane.ERROR_MESSAGE, null);
					}										
				}
			}

		});
		comboCategorias.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboCategorias.setModel(new DefaultComboBoxModel(new String[] {"CATEGORIAS"}));
		
		
		/**
		 * Preenche o combo box com as categorias ao iniciar a tela
		 * 
		 */
		List<CategoriaVO> categorias = new ArrayList<>();
		categorias = categoriaController.consultaTodasCategorias();
		for (CategoriaVO categoriaVO : categorias) {
			comboCategorias.addItem(categoriaVO.getDescricaoCategoria());			
		}
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(112, 128, 144));
		
		JFormattedTextField txtformatadoTextoBusca = new JFormattedTextField();
		txtformatadoTextoBusca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtformatadoTextoBusca.setToolTipText("Digite o texto para busca");
		txtformatadoTextoBusca.setText("Digite o texto para busca");
		
		tableAlternativas = new JTable();
		tableAlternativas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		JLabel lblAlternativas = new JLabel("ALTERNATIVAS");
		lblAlternativas.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblPerguntas = new JLabel("PERGUNTAS");
		lblPerguntas.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel = new JLabel("Consultar Questões");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tableAlternativas, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(tableConsulta, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtformatadoTextoBusca, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
									.addGap(54)
									.addComponent(comboCategorias, 0, 144, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
							.addGap(54)
							.addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addGap(26))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPerguntas, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(363, Short.MAX_VALUE))
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
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboCategorias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtformatadoTextoBusca, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPerguntas)
					.addGap(8)
					.addComponent(tableConsulta, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(lblAlternativas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableAlternativas, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(panelBotoes, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelBotoes.setLayout(new GridLayout(1, 0, 10, 10));
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelBotoes.add(btnExcluir);
		
		JButton btnAlterar = new JButton("ALTERAR");
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelBotoes.add(btnAlterar);
		
		JButton btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelBotoes.add(btnConsultar);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelBotoes.add(btnSalvar);
		setLayout(groupLayout);		

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
			
			novaLinha[0] = alternativaVO.getTexto();
			novaLinha[1] = alternativaVO.getAlternativaCorreta();
			modeloTabela.addRow(novaLinha);
		}
		
	}

	private void limpaTabelaAlternativas() {
		tableAlternativas.setModel(new DefaultTableModel(new Object[][] { nomeColunasAlternativas }, nomeColunasAlternativas));
		
	}

	private void preencherTabelaPerguntas(List<PerguntaVO> perguntas, String categoriaEscolhida) {		 
		limpaTabelaPerguntas();
		DefaultTableModel modeloTabela = (DefaultTableModel) tableConsulta.getModel();
		tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(650);
		
		for (PerguntaVO perguntaVO : perguntas) {
			Object[] novaLinha = new Object[2];
			
			novaLinha[0] = perguntaVO.getTextoPergunta();
			novaLinha[1] = categoriaEscolhida;
			modeloTabela.addRow(novaLinha);
			
		}		
	}
	
	private void limpaTabelaPerguntas() {		
		tableConsulta.setModel(new DefaultTableModel(new Object[][] { nomeColunasPerguntas }, nomeColunasPerguntas));
		Font  f1  = new Font(Font.SERIF, Font.PLAIN,  14);
		//tableConsulta.setEditingRow(int ).setFont(f1);
		//UIManager.put("TableHeader.font",new Font("Arial", Font.BOLD, 18) );
	}
}
