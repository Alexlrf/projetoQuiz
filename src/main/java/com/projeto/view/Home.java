package com.projeto.view;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Home extends JPanel {

	/**
	 * Create the panel.
	 */
	public Home() {
		setBorder(new LineBorder(new Color(250, 128, 114), 10));
		setBackground(new Color(112, 128, 144));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 600, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 360, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
	}

}
