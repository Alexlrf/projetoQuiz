package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Banco;
import com.projeto.repository.BaseDao;

public class AlternativaDAO implements BaseDao<AlternativaVO>{

	@Override
	public AlternativaVO insert(AlternativaVO obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(AlternativaVO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AlternativaVO findById(Integer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlternativaVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlternativaVO completeResultset(ResultSet rs) throws SQLException {
		AlternativaVO alternativaVO = new AlternativaVO();
		alternativaVO.setIdAlternativa(rs.getInt("id_alternativa"));
		alternativaVO.setIdPergunta(rs.getInt("id_pergunta"));
		alternativaVO.setTexto(rs.getString("texto_alternativa"));
		alternativaVO.setAlternativaCorreta(rs.getString("alternativa_correta"));
		return alternativaVO;
	}

	public List<AlternativaVO> buscaAlternativas(PerguntaVO pergunta) {
		List<AlternativaVO> alternativas = new ArrayList<>();
		String sql = "SELECT * FROM alternativa WHERE id_pergunta = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			
			stmt.setInt(1, pergunta.getIdPergunta());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				AlternativaVO alternativaVO = new AlternativaVO();
				alternativaVO = completeResultset(rs);
				alternativas.add(alternativaVO);
			}			
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar alternativas por id_pergunta!");
			
		}				
		return alternativas;
	}

	public boolean cadastraAlternativas(PerguntaVO perguntaVO) {
		boolean retorno = true;
		String sql = "INSERT INTO alternativa (id_pergunta, texto_alternativa, alternativa_correta) VALUES (?, ?, ?);";

		for (AlternativaVO alternativa : perguntaVO.getListaAlternativas()) {
			
			try (Connection conn = Banco.getConnection();
					PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {

				stmt.setInt(1, perguntaVO.getIdPergunta());			
				stmt.setString(2, alternativa.getTexto());
				stmt.setString(3, alternativa.getAlternativaCorreta());
				stmt.executeUpdate();			
				
			} catch (SQLException e) {
				System.out.println("Erro ao cadastrar lista de alternativas!");
				retorno = false;				
			}		
		}
		return retorno;		
	}

	public boolean alteraAlternativa(AlternativaVO alternativaVO) {
		boolean retorno = true;
		String sql = "UPDATE alternativa SET texto_alternativa = ?, alternativa_correta = ? WHERE id_alternativa = ?";
			
			try (Connection conn = Banco.getConnection();
					PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {

				stmt.setString(1, alternativaVO.getTexto());			
				stmt.setString(2, alternativaVO.getAlternativaCorreta());
				stmt.setInt(3, alternativaVO.getIdAlternativa());
				stmt.executeUpdate();			
				
			} catch (SQLException e) {
				System.out.println("Erro ao cadastrar lista de alternativas!");
				retorno = false;				
			}		
		
		return retorno;		
	}
}
