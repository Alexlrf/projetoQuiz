package com.projeto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.projeto.controller.QuizController;
import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.QuizVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Constants;
import com.projeto.seletor.QuizSeletor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelConsultaQuiz extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private String[] nomeColunasAlunos = { " N O M E ", " A C E R T O " };
	private QuizController quizController = new QuizController();
	private List<QuizVO> listaQuiz;
	private List<QuizSeletor> resultadosQuiz;
	private JComboBox comboBoxQuiz;
	private JTable tableAlunos;

	/**
	 * Create the panel.
	 */
	public PanelConsultaQuiz(UsuarioVO usuarioLogado) {
		setBorder(new LineBorder(new Color(250, 128, 114), 5));
		setBackground(new Color(112, 128, 144));
		
		comboBoxQuiz = new JComboBox();
		comboBoxQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisaQuizRealizado();
			}

			private void pesquisaQuizRealizado() {
				limpaTabelaAlunos();
				String quizEscolhido = comboBoxQuiz.getSelectedItem().toString();
				
				if (quizEscolhido == null || comboBoxQuiz.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha um Quiz para consultar!", Constants.ALERTA, JOptionPane.ERROR_MESSAGE);
				} else {
					int quizDaBusca = Integer.parseInt(quizEscolhido);

					try {
						resultadosQuiz = quizController.buscaResultadosQuiz(quizDaBusca);
						preencheTabelaResultados(resultadosQuiz);
						
					} catch (Exception mensagem) {
						JOptionPane.showMessageDialog(null, mensagem.getMessage(), Constants.ALERTA,
								JOptionPane.ERROR_MESSAGE, null);
					}
				}				
			}		
		});
		comboBoxQuiz.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxQuiz.setModel(new DefaultComboBoxModel(new String[] {"QUIZ"}));
		
		/**
		 * Preenche, logo ao iniciar a tela, o comboBox com os IDs de quiz gerados pelo usuário logado
		 */
		try {
			listaQuiz = quizController.consultaTodosQuizes(usuarioLogado);
			for (QuizVO quizVO : listaQuiz) {
				comboBoxQuiz.addItem(quizVO.getIdQuiz());
				revalidate();				
			}
		} catch (ErroNaConsultaException e1) {
			JOptionPane.showMessageDialog(null, "Não foi possível consultar as categorias!");
		}
		
		JLabel lblNewLabel = new JLabel("Consulta Quiz");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		tableAlunos = new JTable();
		tableAlunos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(394, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(78)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboBoxQuiz, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addComponent(tableAlunos, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
							.addGap(117))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(comboBoxQuiz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(tableAlunos, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
					.addGap(73))
		);
		setLayout(groupLayout);

	}

	protected void preencheTabelaResultados(List<QuizSeletor> resultadosQuiz2) {
		limpaTabelaAlunos();
		
		DefaultTableModel modeloTabela = (DefaultTableModel) tableAlunos.getModel();
		tableAlunos.getColumnModel().getColumn(0).setPreferredWidth(650);

		for (QuizSeletor resultado : resultadosQuiz2) {
			Object[] novaLinha = new Object[2];

			novaLinha[0] = resultado.getNomeAluno().toUpperCase();
			novaLinha[1] = resultado.getAcertos();
			modeloTabela.addRow(novaLinha);
		}
	}

	protected void limpaTabelaAlunos() {				
		tableAlunos.setModel(new DefaultTableModel(new Object[][] { nomeColunasAlunos }, nomeColunasAlunos));
		tableAlunos.getColumnModel().getColumn(0).setPreferredWidth(650);
				
	}
}
