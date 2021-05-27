package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;
import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.CoordenadorVO;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Banco;

public class UsuarioDAO{
	
//	public UsuarioVO verificarLoginDAO(String login, String senha) {
//		UsuarioVO usuario;
//		
//		String sql = "SELECT CPF FROM USUARIO WHERE CPF = " + login;
//
//		try (Connection conn = Banco.getConnection();
//				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
//			
//			ResultSet valido = stmt.executeQuery();
//			
//			// valida se a consulta tenha algum retorno
//			if (valido.next()) {
//				usuario.setIdUsuario(valido.getInt("validacao"));
//				
//				
//				// valida se o usuario existe
//				if (usuario.getIdUsuario() > 0) {
//					usuario = this.validarSenha(login, senha);
//				}
//			}
//		} catch (SQLException e) {
//			System.out.println("Erro ao consultar a existência de login no banco." + e.getMessage());
//		}
//		return usuario;
//	}

//	private UsuarioVO validarSenha(String login, String senha) {
//		UsuarioVO usuario;
//		String sql = "SELECT * FROM usuario WHERE cpf = '" + login + "' AND senha = MD5('" + senha + "')";
//
//		try (Connection conn = Banco.getConnection();
//				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
//			
//			ResultSet valido = stmt.executeQuery();
//			
//			// valida se a consulta tenha algum retorno
//			if (valido.next()) {
//				usuario.setIdUsuario(valido.getInt("validacao"));
//				
//				// valida se o usuario e senha existem
//				if (usuario.getIdUsuario() > 0 && usuario.getSenha().trim().isEmpty()) {
//					usuario = this.findById(usuario.getIdUsuario());
//				}
//			}
//		} catch (SQLException e) {
//			System.out.println("Erro ao consultar a existência de login no banco." + e.getMessage());
//		}
//		return usuario;
//	}
	
	public UsuarioVO verificarLoginDAO(String cpf, String senha) {
	UsuarioVO usuario = null;
	String sql = "SELECT * FROM usuario WHERE cpf = '" + cpf + "' AND senha = MD5('" + senha + "')";

	try (Connection conn = Banco.getConnection();
			PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
		
		ResultSet rs = stmt.executeQuery();
		
		// valida se a consulta tenha algum retorno
		if (rs.next()) {
			String tipo = rs.getString("TIPO");
			
			// Verifica se o usuario é um aluno
			if(tipo.equalsIgnoreCase("ALUNO")) {
				AlunoVO usuarioAluno = (AlunoVO) usuario;
				usuarioAluno = (AlunoVO) this.preencherAtributosGerais(usuarioAluno, rs);
				return usuarioAluno;
			
				// Verifica se o usuario é um professor
			} else if (tipo.equalsIgnoreCase("PROFESSOR")) {
				ProfessorVO usuarioProfessor = (ProfessorVO) usuario;
				usuarioProfessor = (ProfessorVO) this.preencherAtributosGerais(usuarioProfessor, rs);
				usuarioProfessor.setDisciplina(rs.getString("DISCIPLINA"));
				return usuarioProfessor;
				
				// Verifica se o usuario é um coordenador
			} else if (tipo.equalsIgnoreCase("COORDENADOR")) {
				CoordenadorVO usuarioCoordenador = (CoordenadorVO) usuario;
				usuarioCoordenador = (CoordenadorVO) this.preencherAtributosGerais(usuarioCoordenador, rs);
				return usuarioCoordenador;
				
			}
		}
	} catch (SQLException e) {
		System.out.println("Erro ao consultar a existência de login no banco." + e.getMessage());
	}
	return usuario;
}
	
	/**
	 * 
	 * @param usuario
	 * @param rs
	 * @return usuario com os atributos preenchidos;
	 * @throws SQLException
	 */
	private UsuarioVO preencherAtributosGerais(UsuarioVO usuario, ResultSet rs) throws SQLException {
		usuario.setNome(rs.getString("NOME"));
		usuario.setRg(rs.getString("RG"));
		usuario.setCpf(rs.getString("CPF"));
		usuario.setDataNascimento(rs.getDate("DT_NASCIMENTO").toLocalDate());
		usuario.setSexo(rs.getString("SEXO").charAt(0));
		usuario.setPossuiDeficiencia(rs.getBoolean("POSSUI_DEFICIENCIA"));
		usuario.setCelular(rs.getString("CELULAR"));
		usuario.setNacionalidade(rs.getString("NACIONALIDADE"));
		usuario.setTurno(TurnoEnum.getTurnoEnum(rs.getString("TURNO")));
		usuario.setTipo(TipoUsuarioEnum.getTipoUsuarioEnum(rs.getString("TIPO")));
		
		return usuario;
	}

}
