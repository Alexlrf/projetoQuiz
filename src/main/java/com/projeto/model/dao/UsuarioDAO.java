package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;
import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.CoordenadorVO;
import com.projeto.model.entity.DisciplinaVO;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Banco;
import com.projeto.seletor.PesquisarUsuarioSeletor;

public class UsuarioDAO{
	
	/**
	 * Verifica se o rg existe
	 * @param rg
	 * @return
	 */
	public boolean verificarRgDAO(String rg, Integer idUsuario) {
		boolean validar  = false;
		String sql = "SELECT RG FROM USUARIO WHERE RG = '" + rg + "'";
		
		if (idUsuario != null && idUsuario > 0) {
			sql += " AND ID_USUARIO <> " + idUsuario;
		}
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet valido = stmt.executeQuery();
			
			// valida se a consulta tenha algum retorno
			if (valido.next()) {
				validar = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar a existĂȘncia de rg no banco." + e.getMessage());
		}
		return validar;
	}
	
	/**
	 * Verifica se o cpf existe
	 * @param cpf
	 * @return
	 */
	public boolean verificarCpfDAO(String cpf, Integer idUsuario) {
		boolean validar  = false;
		String sql = "SELECT CPF FROM USUARIO WHERE CPF = '" + cpf + "'";
		
		if (idUsuario != null && idUsuario > 0) {
			sql += " AND ID_USUARIO <> " + idUsuario;
		}

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet valido = stmt.executeQuery();
			
			// valida se a consulta tenha algum retorno
			if (valido.next()) {
				validar = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar a existĂȘncia de cpf no banco." + e.getMessage());
		}
		return validar;
	}
	
	/**
	 * Verifica se a senha existe
	 * @param senha
	 * @return
	 */
	public boolean verificarSenhaDAO(String cpf, String senha) {
		boolean validar  = false;
		
		String sql = "SELECT SENHA FROM USUARIO WHERE cpf = '" + cpf + "' AND senha = '" + senha + "'";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet valido = stmt.executeQuery();
			
			// valida se a consulta tenha algum retorno
			if (valido.next()) {
				validar = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar a existĂȘncia de senha no banco." + e.getMessage());
		}
		return validar;
	}
	
	/**
	 * verifica a existencia de um usuario atravĂ©s do parametros passados, identifica qual o seu tipo e preenche seus atributos.
	 * @param cpf
	 * @param senha
	 * @return tipo de usuario e seus atributos.
	 */
	public UsuarioVO verificarCpfSenhaDAO(String cpf, String senha) {
		UsuarioVO usuario = null;
		String sql = "SELECT * FROM usuario WHERE cpf = '" + cpf + "' AND senha = '" + senha + "'";
	
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet rs = stmt.executeQuery();
			
			// valida se a consulta tenha algum retorno
			if (rs.next()) {
				String tipo = rs.getString("TIPO");
				
				// Verifica se o usuario Ă© um aluno
				if(tipo.equals("ALUNO")) {
					AlunoVO usuarioAluno = new AlunoVO();
					usuarioAluno = (AlunoVO) preencherAtributos(usuarioAluno, rs);
					return usuarioAluno;
				
					// Verifica se o usuario Ă© um professor
				} else if (tipo.equals("PROFESSOR")) {
					ProfessorVO usuarioProfessor = new ProfessorVO();
					usuarioProfessor = (ProfessorVO) this.preencherAtributos(usuarioProfessor, rs);
					usuarioProfessor.setIdDisciplina(rs.getInt("ID_DISCIPLINA"));
					return usuarioProfessor;
					
					// Verifica se o usuario Ă© um coordenador
				} else if (tipo.equals("COORDENADOR")) {
					CoordenadorVO usuarioCoordenador = new CoordenadorVO();
					usuarioCoordenador = (CoordenadorVO) this.preencherAtributos(usuarioCoordenador, rs);
					return usuarioCoordenador;
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar usuario no banco." + e.getMessage());
		}
		return usuario;
	}
	
	/**
	 * Preenche os atributos em comum entre os usuarios
	 * @param usuario
	 * @param rs
	 * @return usuario com os atributos comuns preenchidos
	 * @throws SQLException
	 */
	private UsuarioVO preencherAtributos(UsuarioVO usuario, ResultSet rs) throws SQLException {
		usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
		usuario.setNome(rs.getString("NOME"));
		usuario.setRg(rs.getString("RG"));
		usuario.setCpf(rs.getString("CPF"));
		usuario.setDataNascimento(rs.getDate("DT_NASCIMENTO").toLocalDate());
		usuario.setSexo(rs.getString("SEXO").charAt(0));
		usuario.setPossuiDeficiencia(rs.getBoolean("POSSUI_DEFICIENCIA"));
		usuario.setCelular(rs.getString("CELULAR"));
		usuario.setTurno(TurnoEnum.getTurnoEnum(rs.getString("TURNO")));
		usuario.setTipo(TipoUsuarioEnum.getTipoUsuarioEnum(rs.getString("TIPO")));
		usuario.setAtivo(rs.getBoolean("ATIVO"));
		usuario.setSenha(rs.getString("SENHA"));
		return usuario;
	}

	public List<UsuarioVO> pesquisarPorSeletor(PesquisarUsuarioSeletor relatorioUsuario) {
		final List<UsuarioVO> retornoRelatorioUsuario = new ArrayList<>();
		
		String sql = "SELECT * FROM USUARIO u";
		
		if (relatorioUsuario.temFiltro()) {
			sql = criarFiltros(relatorioUsuario, sql);
		}
		
		if (relatorioUsuario.temPaginacao()) {
			sql += " LIMIT " + relatorioUsuario.getLimite() + " OFFSET " + relatorioUsuario.getOffset();
		}
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet rs = stmt.executeQuery();
			
			// valida se a consulta tenha algum retorno
			while (rs.next()) {
				String tipo = rs.getString("TIPO");
				
				// Verifica se o usuario Ă© um aluno
				if(tipo.equals("ALUNO")) {
					AlunoVO usuarioAluno = new AlunoVO();
					usuarioAluno = (AlunoVO) preencherAtributos(usuarioAluno, rs);
					retornoRelatorioUsuario.add(usuarioAluno);
				
					// Verifica se o usuario Ă© um professor
				} else if (tipo.equals("PROFESSOR")) {
					ProfessorVO usuarioProfessor = new ProfessorVO();
					usuarioProfessor = (ProfessorVO) this.preencherAtributos(usuarioProfessor, rs);
					usuarioProfessor.setIdDisciplina(rs.getInt("ID_DISCIPLINA"));
					retornoRelatorioUsuario.add(usuarioProfessor);
					
					// Verifica se o usuario Ă© um coordenador
				} else if (tipo.equals("COORDENADOR")) {
					CoordenadorVO usuarioCoordenador = new CoordenadorVO();
					usuarioCoordenador = (CoordenadorVO) this.preencherAtributos(usuarioCoordenador, rs);
					retornoRelatorioUsuario.add(usuarioCoordenador);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar usuario no banco." + e.getMessage());
		}
		return retornoRelatorioUsuario;
	}

	private String criarFiltros(PesquisarUsuarioSeletor seletor, String sql) {
		sql += " WHERE ";
		boolean primeiro = true;
		
		if ((seletor.getNome() != null) && (seletor.getNome().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "u.NOME LIKE '%" + seletor.getNome() + "%'";
			primeiro = false;
		}
		
		if ((seletor.getTipo() != null) && (seletor.getTipo().toString().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "u.TIPO = '" + seletor.getTipo() + "'";
			primeiro = false;
		}
		
		if ((seletor.getTurno() != null) && (seletor.getTurno().toString().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "u.TURNO = '" + seletor.getTurno() + "'";
			primeiro = false;
		}
		
		if (seletor.getAtivo() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "u.ATIVO = " + seletor.getAtivo();
		}
		
		return sql;
	}

	public boolean desativarUsuarioDAO(Integer idUsuarioSelecionado) {
		boolean desativarUsuario = false;
		
		String sql = " UPDATE USUARIO SET ATIVO = ? WHERE ID_USUARIO = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			stmt.setBoolean(1, false);
			stmt.setInt(2, idUsuarioSelecionado);
			
			int quantidadeDeLinhasAfetadas = stmt.executeUpdate();
			
			desativarUsuario = quantidadeDeLinhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao desativar usuario: \n " + e.getMessage());
		}

		return desativarUsuario;
	}

	public ArrayList<String> consultarTipoUsuarioDAO() {
		ArrayList<String> tipoUsuario = new ArrayList<>();
		tipoUsuario.add("SELECIONE O TIPO");
		
		String sql = "SELECT DISTINCT TIPO FROM USUARIO";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String tipo = rs.getString("TIPO");
				tipoUsuario.add(tipo);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao consultar Tipo de Usuario: " + e.getMessage());
		}
		
		return tipoUsuario;
	}

	public int consultarTotalPaginas(PesquisarUsuarioSeletor relatorioUsuario) {
		int totalUsuarios = 0;
		
		String sql = "SELECT count(*) FROM USUARIO u";
		
		if (relatorioUsuario.temFiltro()) {
			sql = criarFiltros(relatorioUsuario, sql);
		}
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				totalUsuarios = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao consultar total de paginas: " + e.getMessage());
		} 
		
		int totalPaginas = totalUsuarios / relatorioUsuario.getLimite();
		int resto = totalUsuarios % relatorioUsuario.getLimite();
		
		if (resto > 0) {
			totalPaginas++;
		}
		
		return totalPaginas;
	}
	
	public UsuarioVO cadastrar(UsuarioVO usuario) {
		String sql = "INSERT INTO USUARIO (NOME, RG, CPF, DT_NASCIMENTO, SEXO, POSSUI_DEFICIENCIA, CELULAR, "
				+ " TURNO, SENHA, TIPO, ATIVO, ID_DISCIPLINA) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);){
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getRg());
			stmt.setString(3, usuario.getCpf());
			stmt.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento()));
			stmt.setString(5, usuario.getSexo() + "");
			stmt.setBoolean(6, usuario.isPossuiDeficiencia());
			stmt.setString(7, usuario.getCelular());
			stmt.setString(8, usuario.getTurno().toString());
			stmt.setString(9, usuario.getSenha());
			stmt.setString(10, usuario.getTipo().toString());
			stmt.setBoolean(11, usuario.isAtivo());
			
			Integer idDisciplina = 0;
			if (usuario.getTipo().equals(TipoUsuarioEnum.PROFESSOR)) {
				idDisciplina = ((ProfessorVO)usuario).getIdDisciplina();
			}
			stmt.setInt(12, idDisciplina);
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				usuario.setIdUsuario(rs.getInt(1));
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
		}
		
		return usuario;
	}

	public boolean alterar(UsuarioVO usuario) {
		
		boolean atualizou = false;
		
		String sql = " UPDATE USUARIO SET NOME = ?, RG = ?, CPF = ?, DT_NASCIMENTO = ?,"
				+ " SEXO = ?, POSSUI_DEFICIENCIA = ?, CELULAR = ?,"
				+ " TURNO = ?, SENHA = ?, TIPO = ?, ATIVO = ?, ID_DISCIPLINA = ? WHERE ID_USUARIO = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql);){
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getRg());
			stmt.setString(3, usuario.getCpf());
			stmt.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento()));
			stmt.setString(5, usuario.getSexo() + "");
			stmt.setBoolean(6, usuario.isPossuiDeficiencia());
			stmt.setString(7, usuario.getCelular());
			stmt.setString(8, usuario.getTurno().toString());
			stmt.setString(9, usuario.getSenha());
			stmt.setString(10, usuario.getTipo().toString());
			stmt.setBoolean(11, usuario.isAtivo());
			
			Integer idDisciplina = 0;
			if (usuario.getTipo().equals(TipoUsuarioEnum.PROFESSOR)) {
				idDisciplina = ((ProfessorVO)usuario).getIdDisciplina();
			}
			stmt.setInt(12, idDisciplina);
			stmt.setInt(13, usuario.getIdUsuario());
			
			int linhasAfetadas = stmt.executeUpdate();
			
			atualizou = linhasAfetadas > 0;
			
			} catch (Exception e) {
				System.out.println("Erro ao alterar usuario: " + e.getMessage());
			}
		
		return atualizou;
	}

	public List<DisciplinaVO> buscarDisciplina() {
		List<DisciplinaVO> disciplinas = new ArrayList<>();
		
		String sql = "SELECT DISTINCT ID_DISCIPLINA, NOME_DISCIPLINA FROM DISCIPLINA";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				DisciplinaVO disciplina = new DisciplinaVO();
				disciplina.setIdDisciplina(rs.getInt("ID_DISCIPLINA"));
				disciplina.setNomeDisciplina(rs.getString("NOME_DISCIPLINA"));
				disciplinas.add(disciplina);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao consultar disciplina: " + e.getMessage());
		}
		
		return disciplinas;
	}
}
