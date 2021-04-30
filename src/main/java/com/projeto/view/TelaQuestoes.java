package com.projeto.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.repository.Constants;

public class TelaQuestoes extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaQuestoes frame = new TelaQuestoes();
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
	public TelaQuestoes() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));

		final JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = "Quiz - Capitais de estados brasileiros";
				String resposta = JOptionPane.showInputDialog(null, "Qual a capital do Paran�?", titulo,
						JOptionPane.QUESTION_MESSAGE).trim();
				if (!resposta.equalsIgnoreCase("Curitiba")) {
					String segundaResposta = JOptionPane.showInputDialog(null,
							Constants.OUTRA_CHANCE, titulo, JOptionPane.ERROR_MESSAGE).trim();
					if (segundaResposta.equalsIgnoreCase("Curitiba")) {
						JOptionPane.showMessageDialog(null, Constants.CERTA, titulo,
								JOptionPane.INFORMATION_MESSAGE);
						btn1.setBackground(Color.green);
						btn1.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, Constants.ERRADA, titulo,
								JOptionPane.ERROR_MESSAGE);
						btn1.setBackground(Color.red);
						btn1.setEnabled(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, Constants.CERTA, titulo,
							JOptionPane.INFORMATION_MESSAGE);
					btn1.setBackground(Color.green);
					btn1.setEnabled(false);
				}

			}
		});
		contentPane.add(btn1);

		JButton btn2 = new JButton("2");
		contentPane.add(btn2);

		JButton btn3 = new JButton("3");
		contentPane.add(btn3);

		JButton btn4 = new JButton("4");
		contentPane.add(btn4);

		JButton btn5 = new JButton("5");
		contentPane.add(btn5);

		JButton btn6 = new JButton("6");
		contentPane.add(btn6);
	}

}
