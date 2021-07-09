package com.projeto.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.projeto.model.entity.PerguntaVO;

public class TelaPreviaQuiz extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private static Set<PerguntaVO> perguntasQuiz = new HashSet<>();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					TelaPreviaQuiz frame = new TelaPreviaQuiz(perguntasQuiz);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPreviaQuiz(Set<PerguntaVO> perguntasQuiz) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPreviaQuiz.class.getResource("/imagens/iconeQuebraCabeca.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 714, 377);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		contentPane.setBorder(new LineBorder(new Color(250, 128, 114), 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 30, 20));
		
		geraFormularioPerguntas(perguntasQuiz);		
	}

	private void geraFormularioPerguntas(Set<PerguntaVO> perguntas2) {		

		for (PerguntaVO perguntaVO : perguntas2) {
			JLabel lblNewLabel = new JLabel(" >>   "+perguntaVO.getTextoPergunta());
			contentPane.add(lblNewLabel);			
			
		}	

		
	}

}
