package com.projeto.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerificarLoginDAO {

	public boolean verificarLoginDAO(String login, String senha) {
		boolean loginRetorno = false;
		ResultSet idUsuario;
		
		String sql = "SELECT fun_valida_usuario('" + login + "','" + senha + "')";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			idUsuario = stmt.executeQuery();
			
			if (idUsuario.next()) {
				loginRetorno = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar a existência de login no banco." + e.getMessage());
		}
		
		return loginRetorno;
	}

}
