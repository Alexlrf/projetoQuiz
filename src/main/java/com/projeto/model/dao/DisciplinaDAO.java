package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.projeto.repository.Banco;

public class DisciplinaDAO {

	
	public int buscaIdDisciplina(Integer idUsuario) {
		int idDisciplinaPesquisada = 0;
		String sql = "SELECT "
				+ 		"disciplina.id_disciplina"
				+" FROM "
				+		"usuario"
				+" INNER JOIN " 
				+ 		"disciplina ON usuario.id_disciplina = disciplina.id_disciplina"				
				+" WHERE "
				+		"Id_usuario = ?;";		
	
	try (Connection conn = Banco.getConnection();
			PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {
		
		stmt.setInt(1, idUsuario);
		ResultSet rs = stmt.executeQuery();			
		if (rs.next()) {
			idDisciplinaPesquisada = rs.getInt("id_disciplina");			
		}
	} catch (SQLException e) {
		System.out.println("Erro na consulta troca para disciplina!");
	}
	return idDisciplinaPesquisada;
	}
}
