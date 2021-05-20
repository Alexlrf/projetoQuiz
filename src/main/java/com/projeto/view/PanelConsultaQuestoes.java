package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.projeto.controller.CategoriaController;
import com.projeto.controller.PerguntaController;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.placeholder.PlaceholderTextField;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PanelConsultaQuestoes extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTable tableConsulta;
	private PlaceholderTextField textoDeBusca;
	private String[] nomeColunas = {"P E R G U N T A", "C A T E G O R I A"};
	private JTable tableAlternativas;
	private CategoriaController categoriaController = new CategoriaController();
	private PerguntaController perguntaController = new PerguntaController();

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
		
		JComboBox comboCategorias = new JComboBox();
		comboCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<PerguntaVO> perguntas = new ArrayList<>();
				if (comboCategorias.getSelectedIndex() > 0) {
					String categoriaEscolhida = comboCategorias.getSelectedItem().toString();
					perguntas = perguntaController.buscaPorCategoriaEscolhida(categoriaEscolhida);
					
					if (perguntas.size() > 0) {
						
						preencherTabela(perguntas, categoriaEscolhida);
						
					} else {

					}
					
										
				}
			}

//			private void preencherTabela(List<PerguntaVO> perguntas) {
//				// TODO Auto-generated method stub
//				
//			}
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
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		tableAlternativas.getColumnModel().getColumn(0).setPreferredWidth(493);
		
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
									.addGap(100)
									.addComponent(comboCategorias, 0, 98, Short.MAX_VALUE)))
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
		
		JButton btnNewButton_3 = new JButton("New button");
		panelBotoes.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("New button");
		panelBotoes.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("New button");
		panelBotoes.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("New button");
		panelBotoes.add(btnNewButton);
		setLayout(groupLayout);		

	}
	
	private void preencherTabela(List<PerguntaVO> perguntas, String categoriaEscolhida) {		 
		limpaTabela();
		DefaultTableModel modeloTabela = (DefaultTableModel) tableConsulta.getModel();
		tableConsulta.getColumnModel().getColumn(0).setPreferredWidth(650);
		
		for (PerguntaVO perguntaVO : perguntas) {
			Object[] novaLinha = new Object[2];
			
			novaLinha[0] = perguntaVO.getTextoPergunta();
			novaLinha[1] = categoriaEscolhida;
			modeloTabela.addRow(novaLinha);
			
		}		
	}
	
	private void limpaTabela() {		
		tableConsulta.setModel(new DefaultTableModel(new Object[][] { nomeColunas }, nomeColunas));
		Font  f1  = new Font(Font.SERIF, Font.PLAIN,  14);
		//tableConsulta.setEditingRow(int ).setFont(f1);
		//UIManager.put("TableHeader.font",new Font("Arial", Font.BOLD, 18) );
	}
}
