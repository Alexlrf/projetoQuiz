package com.projeto.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.projeto.model.entity.AlternativaVO;
import com.projeto.repository.Constants;

public class TelaAlternativas extends JFrame {

	private JPanel contentPane;
	private JButton btnAlternativa1;
	private JButton btnAlternativa2;
	private JButton btnAlternativa3;
	private JButton btnAlternativa4;
	private JButton btnAlternativa5;
	private static List<AlternativaVO> alternativas;
	private int acertos;	

	int getAcertos() {
		return acertos;
	}

	void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAlternativas frame = new TelaAlternativas(alternativas);
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
	public TelaAlternativas(List<AlternativaVO> alternativas) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 737, 468);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		contentPane.setBorder(new LineBorder(new Color(250, 128, 114), 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 20, 10));
		
		acertos = 0;
		
		JButton btnAlternativa1 = new JButton(alternativas.get(0).getTexto()+"");
		btnAlternativa1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (alternativas.get(0).getAlternativaCorreta().equals(Constants.ALTERNATIVA_CORRETA)) {
					acertos++;
				}
			}
		});
		formataBotao(btnAlternativa1);
		btnAlternativa1.setBorder(new LineBorder(new Color(0, 0, 128), 3));
		contentPane.add(btnAlternativa1);
		
		JButton btnAlternativa2 = new JButton(alternativas.get(1).getTexto()+"");
		btnAlternativa2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (alternativas.get(1).getAlternativaCorreta().equals(Constants.ALTERNATIVA_CORRETA)) {
					acertos++;
				}
			}
		});
		formataBotao(btnAlternativa2);
		btnAlternativa2.setBorder(new LineBorder(new Color(0, 0, 128), 3));
		contentPane.add(btnAlternativa2);
		
		JButton btnAlternativa3 = new JButton(alternativas.get(2).getTexto()+"");
		btnAlternativa3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (alternativas.get(2).getAlternativaCorreta().equals(Constants.ALTERNATIVA_CORRETA)) {
					acertos++;
				}
			}
		});
		formataBotao(btnAlternativa3);
		btnAlternativa3.setBorder(new LineBorder(new Color(0, 0, 128), 3));
		contentPane.add(btnAlternativa3);
		
		JButton btnAlternativa4 = new JButton(alternativas.get(3).getTexto()+"");
		btnAlternativa4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (alternativas.get(3).getAlternativaCorreta().equals(Constants.ALTERNATIVA_CORRETA)) {
					acertos++;
				}
			}
		});
		formataBotao(btnAlternativa4);
		btnAlternativa4.setBorder(new LineBorder(new Color(0, 0, 128), 3));
		contentPane.add(btnAlternativa4);
		
		JButton btnAlternativa5 = new JButton(alternativas.get(4).getTexto()+"");
		btnAlternativa5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (alternativas.get(4).getAlternativaCorreta().equals(Constants.ALTERNATIVA_CORRETA)) {
					acertos++;
				}
			}
		});
		formataBotao(btnAlternativa5);
		btnAlternativa5.setBorder(new LineBorder(new Color(0, 0, 128), 3));
		contentPane.add(btnAlternativa5);		
	}
	
	public JButton formataBotao(JButton botao) {

		botao.setBackground(UIManager.getColor("Button.light"));
		botao.setForeground(UIManager.getColor("Button.foreground"));
		botao.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				botao.setBackground(Color.darkGray);
				botao.setForeground(UIManager.getColor("Button.light"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				botao.setBackground(UIManager.getColor("Button.light"));
				botao.setForeground(UIManager.getColor("Button.foreground"));
			}
		});
		return botao;
	}	

}
