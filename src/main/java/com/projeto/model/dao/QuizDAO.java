package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Banco;

public class QuizDAO {

	public int cadastraCodigoQuiz(int idUsuario) {
		int idRetornado = 0;
		String sql = "INSERT INTO quiz (id_usuario) values (?);";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql)){
			
			stmt.setInt(1, idUsuario);
			stmt.executeUpdate();
			
			ResultSet id = stmt.getGeneratedKeys();
			if (id.next()) {				
				idRetornado = (id.getInt(1));
			}			
			
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar usuário para Quiz");
		}		
		return idRetornado;

	}
	
	public boolean cadastraQuiz(int idQuiz, List<PerguntaVO> quiz, Integer idUsuario) {
		boolean retorno = true;
		String sql = "INSERT INTO prova_quiz (id_quiz, id_usuario, id_pergunta) VALUES (?, ?, ?);";
		
		for (PerguntaVO pergunta : quiz) {
			
			try (Connection conn = Banco.getConnection();
					PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {

				stmt.setInt(1, idQuiz);			
				stmt.setInt(2, idUsuario);
				stmt.setInt(3, pergunta.getIdPergunta());
				stmt.executeUpdate();			
				
			} catch (SQLException e) {
				System.out.println("Erro ao cadastrar lista de alternativas!");
				retorno = false;				
			}		
		}
		return retorno;
	}


}
