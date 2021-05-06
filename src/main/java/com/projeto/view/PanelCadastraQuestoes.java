package com.projeto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.projeto.repository.Utils;
import javax.swing.table.DefaultTableModel;

public class PanelCadastraQuestoes extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBox comboBoxPerguntas = new JComboBox();
	private String[] nomeColunas = {"R E S P O S T A", "STATUS"};

	/**
	 * Create the panel.
	 */
	public PanelCadastraQuestoes() {
		setBorder(new LineBorder(new Color(250, 128, 114), 6));
		setBackground(new Color(255, 228, 196));

		JLabel lblTitulo = new JLabel("Questões");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"R E S P O S T A", "STATUS"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(1187);
		table.getColumnModel().getColumn(0).setMinWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(43);

		JFormattedTextField txtCadastraPergunta = new JFormattedTextField();
		txtCadastraPergunta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtCadastraPergunta.getText().equalsIgnoreCase("Digite aqui a pergunta")) {
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
				if (txtCadastraPergunta.getText().isEmpty()) {
					txtCadastraPergunta.setText("Digite aqui a pergunta");
				}
			}
		});

		txtCadastraPergunta.setText("Digite aqui a pergunta");

		JButton btnAdicionaPergunta = new JButton("");
		btnAdicionaPergunta.setToolTipText("Adicionar pergunta");
		btnAdicionaPergunta.setIcon(new ImageIcon(PanelCadastraQuestoes.class.getResource("/imagens/btnAdiciona.png")));
		btnAdicionaPergunta.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdicionaPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Utils.stringValida(txtCadastraPergunta.getText())
						&& !txtCadastraPergunta.getText().equalsIgnoreCase("Digite aqui a pergunta")) {

					if (JOptionPane.showConfirmDialog(null, "Cadastrar outra pergunta?",
							"Cadastro de Perguntas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						
						comboBoxPerguntas.addItem(txtCadastraPergunta.getText().toString().trim());
						txtCadastraPergunta.setText("Digite aqui a pergunta");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Digite a pergunta", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});

		comboBoxPerguntas = new JComboBox();
		comboBoxPerguntas.setModel(new DefaultComboBoxModel(new String[] { "P E R G U N T A S" }));

		JFormattedTextField txtCadastraResposta = new JFormattedTextField();
		txtCadastraResposta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtCadastraResposta.getText().equalsIgnoreCase("Digite aqui a resposta")) {
					txtCadastraResposta.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (txtCadastraResposta.getText().isEmpty()) {
					txtCadastraResposta.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (txtCadastraResposta.getText().isEmpty()) {
					txtCadastraResposta.setText("Digite aqui a resposta");
				}
			}
		});
		txtCadastraResposta.setText("Digite aqui a resposta");
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(255, 228, 196));
		
		JButton btnAdicionaResposta = new JButton("");
		btnAdicionaResposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//limparTabela();
			}
		});
		btnAdicionaResposta.setToolTipText("Adicionar resposta");
		btnAdicionaResposta.setIcon(new ImageIcon(PanelCadastraQuestoes.class.getResource("/imagens/btnAdiciona.png")));
		btnAdicionaResposta.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBoxPerguntas, 0, 497, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(365, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtCadastraPergunta, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAdicionaPergunta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(20))))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtCadastraResposta, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAdicionaResposta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionaPergunta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCadastraPergunta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(comboBoxPerguntas, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCadastraResposta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdicionaResposta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panelBotoes, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(9))
		);
		panelBotoes.setLayout(new GridLayout(1, 0, 10, 5));
								
										JButton btnNewButton_4 = new JButton("New button");
										panelBotoes.add(btnNewButton_4);
						
								JButton btnNewButton_3 = new JButton("New button");
								panelBotoes.add(btnNewButton_3);
				
						JButton btnNewButton_2 = new JButton("New button");
						panelBotoes.add(btnNewButton_2);
		
		//		comboBoxPerguntas.addItem(lista.toArray().toString());
		
				JButton btnNewButton_1 = new JButton("New button");
				panelBotoes.add(btnNewButton_1);
		setLayout(groupLayout);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				//this.limparTabela();
				int indiceSelecionado = table.getSelectedRow();
				
//				if (indiceSelecionado > 0) {
//					btnEditar.setEnabled(true);
//					btnExcluir.setEnabled(true);
//				} else {
//					btnEditar.setEnabled(false);
//					btnExcluir.setEnabled(false);
//				}
			}

		});
		//table.setBounds(19, 60, 837, 160);
		this.add(table);		

	}	

}