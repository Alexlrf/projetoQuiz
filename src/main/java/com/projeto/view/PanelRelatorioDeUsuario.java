package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;

import com.projeto.controller.UsuarioController;
import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.CoordenadorVO;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.seletor.RelatorioDeUsuarioSeletor;

import com.projeto.repository.Constants;

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

	/** TIPO DE USUARIO, NOME COM LIKE, PESQUISAR TODOS. LEMBRAR DE ACRESCENTAR PAGINAÇÃO e relatório excel.
	 * Create the panel.
	 */
	public PanelRelatorioDeUsuario() {
		
		panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 780, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblRelatorioDeUsuarios = new JLabel("Relatório de Usuários");
		lblRelatorioDeUsuarios.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblRelatorioDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuário:");
		
		cbxTipoUsuario = new JComboBox();
		cbxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"TODOS", "ALUNO", "PROFESSOR", "COORDENADOR"}));
		
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
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioController usuarioController = new UsuarioController();
				int indiceSelecionadoNaTablela = tblListaDeUsuarios.getSelectedRow();
				UsuarioVO usuarioSelecionado = usuario.get(indiceSelecionadoNaTablela - 1);
				
				String perguntaExclusao = "Deseja excluir o usuario: " + usuarioSelecionado.getNome() + "?";
				
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, perguntaExclusao, "Está certo disso?", JOptionPane.YES_NO_OPTION);
				
				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					String mensagem = usuarioController.excluirUsuarioController(usuarioSelecionado.getIdUsuario());
					JOptionPane.showMessageDialog(null, mensagem);
					limparTabelaUsuario();
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO fazer alteração do usuario e chamar o painel de cadastro passando o usuario como parametro
				JOptionPane.showMessageDialog(null, "Botão em Construção...");
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(272)
					.addComponent(lblRelatorioDeUsuarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(253))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(tblListaDeUsuarios, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNome)
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnLimpar, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnExcluir, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblTipoDeUsuario)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cbxTipoUsuario, 0, 248, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(btnAlterar, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))))
					.addGap(67))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(264)
					.addComponent(btnPaginaAnterior, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblPaginaAtual)
					.addGap(18)
					.addComponent(btnProximaPagina, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addGap(297))
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
						.addComponent(lblTipoDeUsuario)
						.addComponent(cbxTipoUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(btnProximaPagina))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}

	protected void buscarUsuariosSeletores() {
		lblPaginaAtual.setText(paginaAtual + "");
		
		RelatorioDeUsuarioSeletor relatorioUsuario = new RelatorioDeUsuarioSeletor();
		
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
		
		relatorioUsuario.setNome(txtNome.getText());
		
		usuario = usuarioController.relatorioUsuarioController(relatorioUsuario);
		
		this.atualizarTabelaUsuario(usuario);
	}

	private void atualizarTabelaUsuario(List<UsuarioVO> usuario2) {
		
		this.limparTabelaUsuario();
		
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
		
//		for (UsuarioVO usuarioVO : usuario) {
//			if(usuarioVO instanceof AlunoVO) {
//				AlunoVO aluno = (AlunoVO) usuarioVO;
//				preencherTabelaUsuarios(aluno);
//				
//			} else if (usuarioVO instanceof ProfessorVO) {
//				ProfessorVO professor = (ProfessorVO) usuarioVO;
//				preencherTabelaUsuarios(professor);
//			} else if (usuarioVO instanceof CoordenadorVO) {
//				CoordenadorVO coordenador = (CoordenadorVO) usuarioVO;
//				preencherTabelaUsuarios(coordenador);
//			}
//		}
	}

	private void limparTabelaUsuario() {
		tblListaDeUsuarios.setModel(new DefaultTableModel(new Object[][] {nomesColunas, }, nomesColunas));
	}
}
