package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.projeto.enums.TipoUsuarioEnum;
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
	
	@SuppressWarnings("unlikely-arg-type")
	public UsuarioVO verificarLoginDAO(String login, String senha) {
	UsuarioVO usuario = null;
	
	String sql = "SELECT * FROM usuario WHERE cpf = '" + login + "' AND senha = MD5('" + senha + "')s";

	try (Connection conn = Banco.getConnection();
			PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
		
		ResultSet rs = stmt.executeQuery();
		
		// valida se a consulta tenha algum retorno
		if (rs.next()) {
			String tipo = rs.getString("tipo");
			
			if(tipo.equals(TipoUsuarioEnum.ALUNO)) {
				usuario = new AlunoVO();
			} else if (tipo.equals(TipoUsuarioEnum.PROFESSOR)) {
				usuario = new ProfessorVO();
			} else if (tipo.equals(TipoUsuarioEnum.COORDENACAO)) {
				usuario = new CoordenadorVO();
			}
		}
	} catch (SQLException e) {
		System.out.println("Erro ao consultar a existência de login no banco." + e.getMessage());
	}
	return usuario;
}

//	public UsuarioVO findById(Integer idUsuario) {
//		UsuarioVO usuario;
//		String sql = "SELECT * FROM USUARIO WHERE IDUSUARIO = " + idUsuario;
//		
//		try (Connection conn = Banco.getConnection();
//				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
//			
//			ResultSet resultadoConsulta = stmt.executeQuery();
//			
//			if (resultadoConsulta.next()) {
//				usuario = this.completeResultset(resultadoConsulta);
//			}
//		} catch (SQLException e) {
//			System.out.println("Erro ao consultar usuario por idUsuario." + e.getMessage());
//		}
//		
//		return usuario;
//	}

//	public UsuarioVO completeResultset(ResultSet rs) throws SQLException {
//		UsuarioVO usuario = new UsuarioVO() {
//		};
//		usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
//		usuario.setSenha(rs.getString("SENHA"));
//		usuario.setTipo(TipoUsuarioEnum.getTipoUsuarioEnum(rs.getString("TIPO")));		
//		return usuario;
//	}

}
