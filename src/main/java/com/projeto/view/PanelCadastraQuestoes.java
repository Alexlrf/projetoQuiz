package com.projeto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.projeto.repository.Utils;
import javax.swing.DefaultComboBoxModel;

public class PanelCadastraQuestoes extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBox comboBoxPerguntas  = new JComboBox();;
	private List<String> lista = new ArrayList<>();
	private String perguntaNova;

	/**
	 * Create the panel.
	 */
	public PanelCadastraQuestoes() {
		setBorder(new LineBorder(new Color(250, 128, 114), 6));
		setBackground(new Color(255, 228, 196));
		
		JLabel lblTitulo = new JLabel("Questões");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		table = new JTable();
		
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
		
		JButton btnAdicionaPergunta = new JButton("+");
		btnAdicionaPergunta.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdicionaPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Utils.stringValida(txtCadastraPergunta.getText()) && 
						!txtCadastraPergunta.getText().equalsIgnoreCase("Digite aqui a pergunta") ) {
					
					int opcao = JOptionPane.showConfirmDialog(null, "Cadastrar mais?", "Cadastro", JOptionPane.YES_NO_OPTION );
					
					if (opcao == JOptionPane.YES_OPTION) {
						
						perguntaNova = txtCadastraPergunta.getText().toString().trim();					
						lista.add(perguntaNova);
					}
					perguntaNova=null;
					
					for (String pergunta : lista) {
						
						comboBoxPerguntas.addItem(pergunta);
					}

					
				}else {
					JOptionPane.showMessageDialog(null, "Digite a pergunta", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE, null);
				}				
			}
		});
		
		comboBoxPerguntas = new JComboBox();
		comboBoxPerguntas.setModel(new DefaultComboBoxModel(new String[] {"P E R G U N T A S"}));
		
//		comboBoxPerguntas.addItem(lista.toArray().toString());
		
		JButton btnNewButton_1 = new JButton("New button");
		
		JButton btnNewButton_2 = new JButton("New button");
		
		JButton btnNewButton_3 = new JButton("New button");
		
		JButton btnNewButton_4 = new JButton("New button");
		
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
		
		JButton btnCadastraResposta = new JButton("+");
		btnCadastraResposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Utils.stringValida(txtCadastraResposta.getText()) && 
						!txtCadastraResposta.getText().equalsIgnoreCase("Digite aqui a resposta") ) {					
				}else {
					JOptionPane.showMessageDialog(null, "Digite a resposta", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});
		btnCadastraResposta.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBoxPerguntas, 0, 497, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addGap(56)
							.addComponent(btnNewButton_2)
							.addGap(46)
							.addComponent(btnNewButton_3)
							.addGap(37)
							.addComponent(btnNewButton_4)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(365, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(table, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtCadastraPergunta, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnAdicionaPergunta, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
							.addGap(9))))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtCadastraResposta, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
					.addGap(17)
					.addComponent(btnCadastraResposta, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionaPergunta)
						.addComponent(txtCadastraPergunta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(comboBoxPerguntas, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCadastraResposta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCadastraResposta))
					.addGap(98)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_4)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_2))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
