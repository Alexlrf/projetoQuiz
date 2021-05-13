package com.projeto.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.projeto.enums.UsuarioEnum;
import com.projeto.model.entity.UsuarioVO;

public class UsuarioDAO implements BaseDao<UsuarioVO>{
	
	@Override
	public UsuarioVO insert(UsuarioVO obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(UsuarioVO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public UsuarioVO verificarLoginDAO(String login, String senha) {
		UsuarioVO usuario = new UsuarioVO();
		
		String sql = "SELECT fun_valida_usuario('" + login + "','" + senha + "') as validacao" ;

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet valido = stmt.executeQuery();
			
			// valida se a consulta tenha algum retorno
			if (valido.next()) {
				usuario.setIdUsuario(valido.getInt("validacao"));
				
				// valida se o idUsuario foi retornado
				if (usuario.getIdUsuario() > 0) {
					usuario = this.findById(usuario.getIdUsuario());
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar a existência de login no banco." + e.getMessage());
		}
		return usuario;
	}

	@Override
	public UsuarioVO findById(Integer idUsuario) {
		UsuarioVO usuario = new UsuarioVO();
		String sql = "SELECT * FROM USUARIO WHERE IDUSUARIO = " + idUsuario;
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet resultadoConsulta = stmt.executeQuery();
			
			if (resultadoConsulta.next()) {
				usuario = this.completeResultset(resultadoConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar usuario por idUsuario." + e.getMessage());
		}
		
		return usuario;
	}

	@Override
	public UsuarioVO completeResultset(ResultSet rs) throws SQLException {
		UsuarioVO usuario = new UsuarioVO();
		usuario.setIdUsuario(rs.getInt("IDUSUARIO"));
		usuario.setSenha(rs.getString("SENHA"));
		usuario.setTipo(UsuarioEnum.getEstagioPesquisa(rs.getString("TIPO")));		
		return usuario;
	}

	@Override
	public List<UsuarioVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
