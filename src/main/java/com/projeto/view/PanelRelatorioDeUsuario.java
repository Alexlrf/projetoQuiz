package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;

import com.projeto.controller.UsuarioController;
import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;
import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.CoordenadorVO;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.seletor.PesquisarUsuarioSeletor;

import com.projeto.repository.Constants;
import com.projeto.repository.GeradorPlanilhaUsuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelRelatorioDeUsuario extends JPanel {
	private static final int TAMANHO_PAGINA = 10;
	
	private JTextField txtNome;
	private JTable tblListaDeUsuarios;
	private JComboBox cbxTipoUsuario;
	private JButton btnBuscar;
	private JButton btnLimpar;
	private JButton btnPaginaAnterior;
	private JLabel lblPaginaAtual;
	private JButton btnProximaPagina;
	private JPanel panel;
	private List<UsuarioVO> usuarios = new ArrayList<>();
	private String[] nomesColunas = {"Nome", "Tipo de Usuario", "Turno", "Sexo", "Possui Deficiência", "RG", "CPF", "Status"};
	private DefaultTableModel model;
	private int paginaAtual = 1;
	private JButton btnGerarXls;
	private JComboBox cbxTurno;
	private UsuarioController usuarioController = new UsuarioController();
	private JButton btnExcluir;
	private JButton btnAlterar;
	private int paginasTotal;
	private JLabel lblTotalPaginas;

	private JComboBox cbxAtivado;

	private PesquisarUsuarioSeletor relatorioUsuario;

	/**
	 * Create the panel.
	 */
	public PanelRelatorioDeUsuario() {
		
		panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 780, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblRelatorioDeUsuarios = new JLabel("Relatório de Usuários");
		lblRelatorioDeUsuarios.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblRelatorioDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuário:");
		
		ArrayList<String> tipoUsuario = usuarioController.consultarTipoUsuarioController();
		cbxTipoUsuario = new JComboBox();
		DefaultComboBoxModel preencherTipoUsuario = new DefaultComboBoxModel(tipoUsuario.toArray());
		cbxTipoUsuario.setModel(preencherTipoUsuario);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarUsuariosSeletores();
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTabelaUsuario();
				limparTudo();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tblListaDeUsuarios = new JTable();
		tblListaDeUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblListaDeUsuarios.getSelectedRow() > 0) {
					btnExcluir.setEnabled(true);
					btnAlterar.setEnabled(true);
				} else {
					btnExcluir.setEnabled(false);
					btnAlterar.setEnabled(false);
				}
			}
		});
		
		tblListaDeUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		btnPaginaAnterior = new JButton("← ");
		btnPaginaAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paginaAtual > 1) {
					paginaAtual--;
					btnExcluir.setEnabled(false);
					btnAlterar.setEnabled(false);
				}
				buscarUsuariosSeletores();
			}
		});
		btnPaginaAnterior.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPaginaAnterior.setEnabled(false);
		
		lblPaginaAtual = new JLabel("");
		lblPaginaAtual.setText(paginaAtual + "");
		
		btnProximaPagina = new JButton("→");
		btnProximaPagina.setAlignmentX(0.5f);
		btnProximaPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					paginaAtual++;
					buscarUsuariosSeletores();
					btnExcluir.setEnabled(false);
					btnAlterar.setEnabled(false);
				}
			}
		);
		btnProximaPagina.setEnabled(false);
		
		verificarBotoesPaginas();
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int indiceSelecionadoNaTablela = tblListaDeUsuarios.getSelectedRow();
				if (indiceSelecionadoNaTablela > 0) {
					UsuarioVO usuarioSelecionado = usuarios.get(indiceSelecionadoNaTablela - 1);
					
					String perguntaExclusao = "Deseja excluir o usuario: " + usuarioSelecionado.getNome() + "?";
					
					int opcaoSelecionada = JOptionPane.showConfirmDialog(null, perguntaExclusao, "Está certo disso?", JOptionPane.YES_NO_OPTION);
					
					if (opcaoSelecionada == JOptionPane.YES_OPTION) {
						String mensagem = usuarioController.excluirUsuarioController(usuarioSelecionado.getIdUsuario());
						JOptionPane.showMessageDialog(null, mensagem);
						usuarios.remove(indiceSelecionadoNaTablela - 1);
						limparTabelaUsuario();
						limparTudo();
					}
				} else {
					btnExcluir.setEnabled(false);
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExcluir.setEnabled(false);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAlterar.setEnabled(false);
		
		btnGerarXls = new JButton("Salvar Excel");
		btnGerarXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Salvar relatório como...");

				int resultado = jfc.showSaveDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
					GeradorPlanilhaUsuario geradorPlanilha = new GeradorPlanilhaUsuario();
					try {
						geradorPlanilha.gerarPlanilhaUsuarios(relatorioUsuario, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, "Planilha gerada com sucesso!", Constants.SUCESSO,
								JOptionPane.INFORMATION_MESSAGE, null);

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao gerar planilha!", "ATENÇÃO",
								JOptionPane.ERROR_MESSAGE, null);
						System.out.println("Erro ao gerar planilha de usuarios: " + ex.getMessage());
					}
				}
			}
		});
		btnGerarXls.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGerarXls.setIcon(new ImageIcon(PanelConsultaQuestoes.class.getResource("/imagens/excel.png")));
		btnGerarXls.setEnabled(false);
		
		cbxTurno = new JComboBox<String>();
		cbxTurno.setModel(new DefaultComboBoxModel
				(new String[] {"SELECIONE O TURNO", TurnoEnum.MATUTINO.toString(), TurnoEnum.VESPERTINO.toString(), TurnoEnum.NOTURNO.toString()}));
		
		JLabel lblTurno = new JLabel("Turno");
		
		JLabel lblDe = new JLabel("de");
		
		lblTotalPaginas = new JLabel("");
		
		JLabel lblAtivo = new JLabel("ativado");
		
		cbxAtivado = new JComboBox();
		cbxAtivado.setModel(new DefaultComboBoxModel
				(new String[] {Constants.TODOS.toString(), Constants.ATIVADO.toString(), Constants.DESATIVADO.toString()}));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(272)
					.addComponent(lblRelatorioDeUsuarios, GroupLayout.PREFERRED_SIZE, 273, Short.MAX_VALUE)
					.addGap(253))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(250)
					.addComponent(btnPaginaAnterior, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPaginaAtual, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(lblDe)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTotalPaginas, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnProximaPagina, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addGap(249))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnGerarXls, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(tblListaDeUsuarios, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(lblAtivo)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(btnLimpar, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
											.addGap(36)))
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(btnExcluir, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
											.addGap(86))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(19)
											.addComponent(cbxAtivado, 0, 114, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblTurno)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnAlterar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(cbxTurno, 0, 135, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTipoDeUsuario)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(cbxTipoUsuario, 0, 87, Short.MAX_VALUE)
										.addComponent(btnBuscar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))
							.addGap(67))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addComponent(lblRelatorioDeUsuarios)
					.addGap(125)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxTipoUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoDeUsuario)
						.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTurno)
						.addComponent(cbxAtivado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAtivo))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLimpar)
						.addComponent(btnBuscar)
						.addComponent(btnExcluir)
						.addComponent(btnAlterar))
					.addGap(37)
					.addComponent(tblListaDeUsuarios, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPaginaAnterior)
						.addComponent(lblPaginaAtual)
						.addComponent(lblDe)
						.addComponent(lblTotalPaginas)
						.addComponent(btnProximaPagina))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnGerarXls)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
	}

	private void verificarBotoesPaginas() {
		btnPaginaAnterior.setEnabled(paginaAtual > 1);
		btnProximaPagina.setEnabled(paginaAtual < paginasTotal);
	}

	protected void buscarUsuariosSeletores() {
		this.limparTabelaUsuario();
		
		lblPaginaAtual.setText(paginaAtual + "");
		
		relatorioUsuario = new PesquisarUsuarioSeletor();
		
		relatorioUsuario.setPagina(paginaAtual);
		relatorioUsuario.setLimite(TAMANHO_PAGINA);
		
		UsuarioController usuarioController = new UsuarioController();
		if (cbxTipoUsuario.getSelectedIndex() > 0) {
			if (cbxTipoUsuario.getSelectedItem().equals("ALUNO")) {
				relatorioUsuario.setTipo(TipoUsuarioEnum.ALUNO);
			} else if (cbxTipoUsuario.getSelectedItem().equals("PROFESSOR")){
				relatorioUsuario.setTipo(TipoUsuarioEnum.PROFESSOR);
			} else if (cbxTipoUsuario.getSelectedItem().equals("COORDENADOR")) {
				relatorioUsuario.setTipo(TipoUsuarioEnum.COORDENADOR);
			} else {			
				relatorioUsuario.setTipo(null);
			}
		}
		
		if (cbxTurno.getSelectedIndex() > 0) {
			if (cbxTurno.getSelectedItem().equals("MATUTINO")) {
				relatorioUsuario.setTurno(TurnoEnum.MATUTINO);
			} else if (cbxTurno.getSelectedItem().equals("VESPERTINO")) {
				relatorioUsuario.setTurno(TurnoEnum.VESPERTINO);
			} else if (cbxTurno.getSelectedItem().equals("NOTURNO")) {
				relatorioUsuario.setTurno(TurnoEnum.NOTURNO);
			} else {
				relatorioUsuario.setTurno(null);
			}
		}
		
		relatorioUsuario.setAtivo(null);
		if (cbxAtivado.getSelectedIndex() > 0) {
			if (cbxAtivado.getSelectedItem().equals(Constants.ATIVADO.toString())) {
				relatorioUsuario.setAtivo(true);
			} else {
				relatorioUsuario.setAtivo(false);
			}
		}
		
		relatorioUsuario.setNome(txtNome.getText());
		
		usuarios = usuarioController.pesquisarUsuarioController(relatorioUsuario);
		
		paginasTotal = usuarioController.consultarTotalPaginas(relatorioUsuario);
		lblTotalPaginas.setText(paginasTotal + "");
		
		verificarBotoesPaginas();
		this.atualizarTabelaUsuario(usuarios);
	}

	private void atualizarTabelaUsuario(List<UsuarioVO> usuario) {
		model = (DefaultTableModel) this.tblListaDeUsuarios.getModel();
		
		for(UsuarioVO usu: usuarios) {
			
			Object[] novaLinhaTabela = new Object[8];
			
			novaLinhaTabela[0] = usu.getNome();
			novaLinhaTabela[1] = usu.getTipo();
			novaLinhaTabela[2] = usu.getTurno();
			
			if (usu.getSexo() == Constants.MASCULINO) {
				novaLinhaTabela[3] = "Masculino";
			} else {
				novaLinhaTabela[3] = "Feminino";
			}
			
			if (usu.isPossuiDeficiencia()) {
				novaLinhaTabela[4] = "Sim";				
			} else {
				novaLinhaTabela[4] = "Não";
			}
			
			novaLinhaTabela[5] = usu.getRg();
			novaLinhaTabela[6] = usu.getCpf();
			
			if ( usu.isAtivo()) {
				novaLinhaTabela[7] = "ATIVADO";
			} else {
				novaLinhaTabela[7] = "DESATIVADO";
			}
			
			model.addRow(novaLinhaTabela);
		}
		
		habilitarBtnExcel();
	}


	protected void limparTudo() {
		txtNome.setText("");
		cbxTipoUsuario.setSelectedIndex(0);
		cbxTurno.setSelectedIndex(0);
		cbxAtivado.setSelectedIndex(0);
		btnExcluir.setEnabled(false);
		btnGerarXls.setEnabled(false);
		btnAlterar.setEnabled(false);
		paginaAtual = 1;
		paginasTotal = 1;
		lblPaginaAtual.setText(paginaAtual + "");
		lblTotalPaginas.setText(paginasTotal + "");
	}
	
	private void limparTabelaUsuario() {
		tblListaDeUsuarios.setModel(new DefaultTableModel(new Object[][] {nomesColunas, }, nomesColunas));
		btnPaginaAnterior.setEnabled(false);
		btnProximaPagina.setEnabled(false);
	}
	
	private void habilitarBtnExcel() {
		if (tblListaDeUsuarios.getRowCount() > 1) {
			btnGerarXls.setEnabled(true);
		} else {
			btnGerarXls.setEnabled(false);
		}
	}

	public JTable getTblListaDeUsuarios() {
		return tblListaDeUsuarios;
	}

	public void setTblListaDeUsuarios(JTable tblListaDeUsuarios) {
		this.tblListaDeUsuarios = tblListaDeUsuarios;
	}

	public JButton getBtnAlterar() {
		return btnAlterar;
	}

	public void setBtnAlterar(JButton btnAlterar) {
		this.btnAlterar = btnAlterar;
	}

	public UsuarioVO obterUsuarioSelecionado() {
		return usuarios.get(tblListaDeUsuarios.getSelectedRow() - 1);
	}
}
