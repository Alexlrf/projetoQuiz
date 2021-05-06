package com.projeto.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelImagemBackground extends JPanel {

	/**
	 * Create the panel.
	 */
	
	BufferedImage imagemFundo;
	public PanelImagemBackground() {
		
		try {
			//imagemFundo = ImageIO.read(new File("C:\\Users\\alf_a\\Downloads\\iconeQuebraCabeca.png"));
			imagemFundo = ImageIO.read(new File("C:/Users/alf_a/eclipse-workspace/quiz/src/main/java/com/projeto/view/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imagemFundo, 0, 0, null);
	}

}
