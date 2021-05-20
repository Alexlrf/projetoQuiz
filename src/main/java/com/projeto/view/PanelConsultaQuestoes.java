package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PanelConsultaQuestoes extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTable tableConsulta;
	private PlaceholderTextField textoDeBusca;
	private String[] nomeColunas = {"PERGUNTA", "CATEGORIA"};
	private JTable tableAlternativas;
	private CategoriaController categoriaController = new CategoriaController();
	private PerguntaController perguntaController = new PerguntaController();
	private PlaceholderTextField textField;

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
					
					for (PerguntaVO perguntaVO : perguntas) {
						System.out.println(perguntaVO.getTextoPergunta());
						
					}
					
				}
			}
		});
		comboCategorias.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboCategorias.setModel(new DefaultComboBoxModel(new String[] {"CATEGORIAS"}));
		List<CategoriaVO> categorias = new ArrayList<>();
		//categorias = categoriaController.consultaTodasCategorias();
		
//		for (CategoriaVO categoriaVO : categorias) {
//			comboCategorias.addItem(categoriaVO.getDescricaoCategoria());			
//		}
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(112, 128, 144));
		
		JFormattedTextField txtformatadoTextoBusca = new JFormattedTextField();
		txtformatadoTextoBusca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtformatadoTextoBusca.setToolTipText("Digite o texto para busca");
		txtformatadoTextoBusca.setText("Digite o texto para busca");
		
		tableAlternativas = new JTable();
		
		JLabel lblAlternativas = new JLabel("ALTERNATIVAS");
		lblAlternativas.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblPerguntas = new JLabel("PERGUNTAS");
		lblPerguntas.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textField = new PlaceholderTextField();
		textField.setPlaceholder("Digite o seu login");
		textField.setForeground(Color.black);
		//textField.setP(Color.black);
		textField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tableAlternativas, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(tableConsulta, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtformatadoTextoBusca, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
									.addGap(100)
									.addComponent(comboCategorias, 0, 81, Short.MAX_VALUE))
								.addComponent(lblAlternativas, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
							.addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addGap(26))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPerguntas, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboCategorias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtformatadoTextoBusca, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPerguntas)
					.addGap(8)
					.addComponent(tableConsulta, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAlternativas)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tableAlternativas, GroupLayout.DEFAULT_SIZE, 4, Short.MAX_VALUE)
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
}
