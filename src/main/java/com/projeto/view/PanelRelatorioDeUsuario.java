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
import com.projeto.seletor.PesquisarDeUsuarioSeletor;

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
	private List<UsuarioVO> usuario = new ArrayList<>();
	private String[] nomesColunas = {"Nome", "Tipo de Usuario", "Turno", "Sexo", "Possui Deficiência", "RG", "CPF"};
	private DefaultTableModel model;
	private int paginaAtual = 1;
	private JButton btnGerarXls;
	private JComboBox cbxTurno;
	private UsuarioController usuarioController = new UsuarioController();
	private JButton btnExcluir;
	private JButton btnAlterar;
	private int paginasTotal;
	private JLabel lblTotalPaginas;

	/** TIPO DE USUARIO, NOME COM LIKE, PESQUISAR TODOS. LEMBRAR DE ACRESCENTAR PAGINAÇÃO e relatório excel.
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
				txtNome.setText("");
				cbxTipoUsuario.setSelectedIndex(0);
				cbxTurno.setSelectedIndex(0);
				btnExcluir.setEnabled(false);
				btnGerarXls.setEnabled(false);
				btnAlterar.setEnabled(false);
				paginaAtual = 1;
				paginasTotal = 1;
				lblPaginaAtual.setText(paginaAtual + "");
				lblTotalPaginas.setText(paginasTotal + "");
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tblListaDeUsuarios = new JTable();
		tblListaDeUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Tipo", "Turno", "Sexo", "Deficiente", "CPF", "RG"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblListaDeUsuarios.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (tblListaDeUsuarios.getSelectedRow() > 0) {
					btnExcluir.setEnabled(true);
					btnAlterar.setEnabled(true);
				} else {
					btnExcluir.setEnabled(false);
					btnAlterar.setEnabled(false);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (tblListaDeUsuarios.getSelectedRow() >= 0) {
					btnExcluir.setEnabled(true);
					btnAlterar.setEnabled(true);
				}
			}
		});
		
		btnPaginaAnterior = new JButton("← ");
		btnPaginaAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paginaAtual > 1) {
					paginaAtual--;
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
				}
			}
		);
		btnProximaPagina.setEnabled(false);
		
		verificarBotoesPaginas();
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UsuarioController usuarioController = new UsuarioController();
				int indiceSelecionadoNaTablela = tblListaDeUsuarios.getSelectedRow();
				if (indiceSelecionadoNaTablela > 0) {
					UsuarioVO usuarioSelecionado = usuario.get(indiceSelecionadoNaTablela - 1);
					
					String perguntaExclusao = "Deseja excluir o usuario: " + usuarioSelecionado.getNome() + "?";
					
					int opcaoSelecionada = JOptionPane.showConfirmDialog(null, perguntaExclusao, "Está certo disso?", JOptionPane.YES_NO_OPTION);
					
					if (opcaoSelecionada == JOptionPane.YES_OPTION) {
						String mensagem = usuarioController.excluirUsuarioController(usuarioSelecionado.getIdUsuario());
						JOptionPane.showMessageDialog(null, mensagem);
						limparTabelaUsuario();
					}
				} else {
					btnExcluir.setEnabled(false);
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExcluir.setEnabled(false);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
		});
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
						geradorPlanilha.gerarPlanilhaUsuarios(usuario, caminhoEscolhido);
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
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(272)
					.addComponent(lblRelatorioDeUsuarios, GroupLayout.PREFERRED_SIZE, 273, Short.MAX_VALUE)
					.addGap(253))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(250)
					.addComponent(btnPaginaAnterior, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPaginaAtual, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(lblDe)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTotalPaginas, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnProximaPagina, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
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
											.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
											.addGap(18))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(btnLimpar, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
											.addGap(36)))
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(btnExcluir, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
											.addGap(86))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblTurno)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cbxTurno, 0, 171, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnAlterar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
										.addComponent(lblTipoDeUsuario))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(cbxTipoUsuario, 0, 144, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(22)
											.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))))
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
						.addComponent(lblTurno)
						.addComponent(cbxTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxTipoUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoDeUsuario))
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

	protected void alterarUsuario() {
		UsuarioVO usuarioSelecionado = usuario.get(tblListaDeUsuarios.getSelectedRow() - 1);
		
		if(usuarioSelecionado instanceof AlunoVO) {
			AlunoVO aluno = (AlunoVO) usuarioSelecionado;
			//chamar a tela do aluno
			
		} else if (usuarioSelecionado instanceof ProfessorVO) {
			ProfessorVO professor = (ProfessorVO) usuarioSelecionado;
			//chamar tela do professor
			
		} else if (usuarioSelecionado instanceof CoordenadorVO) {
			CoordenadorVO coordenador = (CoordenadorVO) usuarioSelecionado;
			//chamar a tela do coordenador
		}
	}

	private void verificarBotoesPaginas() {
		btnPaginaAnterior.setEnabled(paginaAtual > 1);
		btnProximaPagina.setEnabled(paginaAtual < paginasTotal);
	}

	protected void buscarUsuariosSeletores() {
		this.limparTabelaUsuario();
		
		lblPaginaAtual.setText(paginaAtual + "");
		
		PesquisarDeUsuarioSeletor relatorioUsuario = new PesquisarDeUsuarioSeletor();
		
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
		
		relatorioUsuario.setNome(txtNome.getText());
		
		usuario = usuarioController.pesquisarUsuarioController(relatorioUsuario);
		
		paginasTotal = usuarioController.consultarTotalPaginas(relatorioUsuario);
		lblTotalPaginas.setText(paginasTotal + "");
		
		verificarBotoesPaginas();
		this.atualizarTabelaUsuario(usuario);
	}

	private void atualizarTabelaUsuario(List<UsuarioVO> usuario2) {
		model = (DefaultTableModel) this.tblListaDeUsuarios.getModel();
		
		for(UsuarioVO usu: this.usuario) {
			if (usu.isAtivo()) {
				Object[] novaLinhaTabela = new Object[7];
				
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
				
				model.addRow(novaLinhaTabela);
			}
		}
		
		habilitarBtnExcel();
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
}
