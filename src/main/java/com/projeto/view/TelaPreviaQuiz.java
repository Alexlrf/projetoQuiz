package com.projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.projeto.model.entity.PerguntaVO;

import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

public class TelaPreviaQuiz extends JFrame {

	private JPanel contentPane;
	private static List<PerguntaVO> perguntas = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					TelaPreviaQuiz frame = new TelaPreviaQuiz(perguntas);
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
	public TelaPreviaQuiz(List<PerguntaVO> perguntas) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPreviaQuiz.class.getResource("/imagens/iconeQuebraCabeca.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 714, 377);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		contentPane.setBorder(new LineBorder(new Color(250, 128, 114), 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 30, 20));
		
		geraFormularioPerguntas(perguntas);		
	}

	private void geraFormularioPerguntas(List<PerguntaVO> perguntas2) {		

		for (PerguntaVO perguntaVO : perguntas2) {
			JLabel lblNewLabel = new JLabel(" >>   "+perguntaVO.getTextoPergunta());
			contentPane.add(lblNewLabel);			
			
		}	

		
	}

}
