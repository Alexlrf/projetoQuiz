package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

public class PanelConsultaQuestoes extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTable tableConsulta;
	private PlaceholderTextField textoDeBusca;

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
		comboCategorias.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboCategorias.setModel(new DefaultComboBoxModel(new String[] {"CATEGORIAS"}));
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(112, 128, 144));
		
		JFormattedTextField frmtdtxtfldDigiteOTexto = new JFormattedTextField();
		frmtdtxtfldDigiteOTexto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmtdtxtfldDigiteOTexto.setToolTipText("Digite o texto para busca");
		frmtdtxtfldDigiteOTexto.setText("Digite o texto para busca");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addGap(25))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tableConsulta, GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(frmtdtxtfldDigiteOTexto, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
							.addGap(100)
							.addComponent(comboCategorias, 0, 200, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNomeUsuario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboCategorias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldDigiteOTexto, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tableConsulta, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
					.addGap(34)
					.addComponent(panelBotoes, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
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
