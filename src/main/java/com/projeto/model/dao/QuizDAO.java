package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.QuizVO;
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

	public int validaCodigoQuiz(int codigoQuiz) throws ErroNaConsultaException {
		int codigoQuizValido = 0; 
		
		String sql = "SELECT distinct(id_quiz) FROM prova_quiz WHERE id_quiz = ?";
		
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {
			
			stmt.setInt(1, codigoQuiz);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				codigoQuizValido = (rs.getInt("id_quiz"));	
			}
			
		}  catch (SQLException e) {
			codigoQuizValido = 0;		
			throw new ErroNaConsultaException("Erro ao conectar com o Quiz!");
		}			
		return codigoQuizValido;
	}

	public List<PerguntaVO> consultaPerguntasQuiz(int idRetornado) throws ErroNaConsultaException{
		List<PerguntaVO> perguntasRetornadas = new ArrayList<>();
		PerguntaVO pergunta;
		
		String sql = "SELECT"
				+ 		" prova_quiz.id_pergunta, pergunta.texto_pergunta "
				+ "FROM"
				+ 		" prova_quiz \r\n"
				+ "INNER JOIN"
				+ 		" pergunta on prova_quiz.id_pergunta = pergunta.id_pergunta "
				+ "WHERE"
				+ 		" prova_quiz.id_quiz = ?;";		
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {
			
			stmt.setInt(1, idRetornado);			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				pergunta = new PerguntaVO();
				pergunta.setIdPergunta(rs.getInt("id_pergunta"));
				pergunta.setTextoPergunta(rs.getString("texto_pergunta"));
				pergunta.setListaAlternativas(preencheAlternativas(pergunta.getIdPergunta()));
				perguntasRetornadas.add(pergunta);
			}
			
		}  catch (SQLException e) {
			throw new ErroNaConsultaException("Erro ao conectar com as perguntas do Quiz!");
		}			
		return perguntasRetornadas;
	}

	private List<AlternativaVO> preencheAlternativas(int idPergunta) {
		List<AlternativaVO> alternativas = new ArrayList<>();
		String sql = "SELECT * FROM alternativa WHERE id_pergunta = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			
			stmt.setInt(1, idPergunta);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				AlternativaVO alternativaVO = new AlternativaVO();
				alternativaVO.setIdAlternativa(rs.getInt("id_alternativa"));
				alternativaVO.setIdPergunta(rs.getInt("id_pergunta"));
				alternativaVO.setTexto(rs.getString("texto_alternativa"));
				alternativaVO.setAlternativaCorreta(rs.getString("alternativa_correta"));
				alternativas.add(alternativaVO);
			}			
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar alternativas por id_pergunta!");
			
		}				
		return alternativas;
	}

	public void cadastraResultado(QuizVO quizVO) throws ErroNoCadastroException {
		String sql = "INSERT INTO resultado_prova (id_quiz, id_usuario, num_acertos) VALUES (?, ?, ?);";
			
			try (Connection conn = Banco.getConnection();
					PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {

				stmt.setInt(1, quizVO.getIdQuiz());			
				stmt.setInt(2, quizVO.getAluno().getIdUsuario());
				stmt.setInt(3, quizVO.getAcertos());
				stmt.executeUpdate();			
				
			} catch (SQLException e) {
				System.out.println("Erro ao registrar resultado!");
				throw new ErroNoCadastroException("Erro ao registrar resultado!\nLEMBRETE\nCada aluno pode responder somente uma vez cada Quiz!\n");
							
			}		
		}

}
