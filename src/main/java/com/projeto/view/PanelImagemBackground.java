package com.projeto.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class PanelImagemBackground extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private Image planoDeFundo;
	
	public PanelImagemBackground() {
		planoDeFundo = Toolkit.getDefaultToolkit().getImage(PanelImagemBackground.class.getResource("/imagens/background.png"));
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(planoDeFundo, 0, 0, null);
	}
}
