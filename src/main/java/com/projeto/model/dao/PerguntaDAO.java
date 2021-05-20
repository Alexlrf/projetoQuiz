package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Banco;
import com.projeto.repository.BaseDao;

public class PerguntaDAO implements BaseDao<PerguntaVO>{

	@Override
	public PerguntaVO insert(PerguntaVO obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PerguntaVO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PerguntaVO findById(Integer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerguntaVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerguntaVO completeResultset(ResultSet rs) throws SQLException {
		PerguntaVO perguntaVO = new PerguntaVO();
		
		perguntaVO.setTexto(rs.getString("texto_pergunta"));
		perguntaVO.setCategoria(rs.getString("categoria"));
		perguntaVO.setIdPergunta(rs.getInt("id_pergunta"));
		return perguntaVO;		
	}

	public List<PerguntaVO> buscaPorCategoriaEscolhida(String categoriaEscolhida){
		List<PerguntaVO> listaPerguntas = new ArrayList<PerguntaVO>();
		String sql = "SELECT * FROM pergunta WHERE categoria = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			
			stmt.setString(1, categoriaEscolhida);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {				
				PerguntaVO perguntaVO = new PerguntaVO();
				perguntaVO = completeResultset(rs);
				listaPerguntas.add(perguntaVO);
				
			}			
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pergunta por categoria!");
		}
		
		return listaPerguntas;
	}

}