package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

public class PanelCadastraQuestoes extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;

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
				txtCadastraPergunta.setText("");
			}
		});
		txtCadastraPergunta.setText("Digite aqui a pergunta");
		
		JButton btnNewButton = new JButton("New button");
		
		JComboBox comboBoxPerguntas = new JComboBox();
		
		JButton btnNewButton_1 = new JButton("New button");
		
		JButton btnNewButton_2 = new JButton("New button");
		
		JButton btnNewButton_3 = new JButton("New button");
		
		JButton btnNewButton_4 = new JButton("New button");
		
		JFormattedTextField txtCadastraResposta = new JFormattedTextField();
		txtCadastraResposta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCadastraResposta.setText("");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (!txtCadastraResposta.getText().isEmpty()) {
					
				}else{
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
		
		JButton btnNewButton_5 = new JButton("New button");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBoxPerguntas, 0, 497, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
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
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(table, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtCadastraPergunta, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
							.addGap(9))))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtCadastraResposta, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
					.addGap(17)
					.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(txtCadastraPergunta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(comboBoxPerguntas, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCadastraResposta, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_5))
					.addGap(98)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
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
