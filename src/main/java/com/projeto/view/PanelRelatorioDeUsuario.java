package com.projeto.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;

public class PanelRelatorioDeUsuario extends JPanel {
	private JTextField txtNome;
	private JTable tblListaDeUsuarios;
	private JComboBox cbxTipoUsuario;
	private JButton btnBuscar;
	private JButton btnLimpar;
	private JButton btnPaginaAnterior;
	private JLabel lblPaginaAtual;
	private JButton btnProximaPagina;
	private JPanel panel;

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
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tblListaDeUsuarios = new JTable();
		tblListaDeUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tipo", "Turno", "Sexo", "Deficiente", "CPF", "RG", "Nome"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		btnPaginaAnterior = new JButton("← ");
		btnPaginaAnterior.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		lblPaginaAtual = new JLabel("1");
		
		btnProximaPagina = new JButton("→");
		btnProximaPagina.setAlignmentX(0.5f);
		btnProximaPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
									.addComponent(btnLimpar, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
									.addGap(330)
									.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblTipoDeUsuario)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cbxTipoUsuario, 0, 248, Short.MAX_VALUE)))))
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
						.addComponent(btnBuscar))
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
		
	}
}
