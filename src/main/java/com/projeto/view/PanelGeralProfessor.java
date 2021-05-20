package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class PanelGeralProfessor extends JPanel {	
	private static final long serialVersionUID = 1L;
	
	public JLabel lblNomeUsuarioLogado;
	private String nomeUsuario;	
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	/**
	 * Create the panel.
	 */
	public PanelGeralProfessor() {
		setBackground(Color.BLACK);
		setBorder(new LineBorder(new Color(0, 0, 0), 5));
		
		lblNomeUsuarioLogado = new JLabel("");
		lblNomeUsuarioLogado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNomeUsuarioLogado.setForeground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(311, Short.MAX_VALUE)
					.addComponent(lblNomeUsuarioLogado, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNomeUsuarioLogado, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(260, Short.MAX_VALUE))
		);
		setLayout(groupLayout);		

	}
}
