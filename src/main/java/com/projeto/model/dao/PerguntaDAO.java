package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.projeto.exceptions.PerguntaNaoExistenteException;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.PerguntaVO;

import com.projeto.repository.BaseDao;

public class PerguntaDAO implements BaseDao<PerguntaVO> {

	private Connection conn;

	public PerguntaDAO(Connection conn) {
		this.conn = conn;
	}

	/*
	 * @Override public PerguntaVO insert(AlternativaVO obj) { PreparedStatement st
	 * = null; try { st = conn.prepareStatement( "INSET INTO " + "()"+ "VALLUES"
	 * 
	 * ); } }
	 */
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
	public PerguntaVO findById(Integer idPergunta) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT questao.idquiz, questao.questao, questao.pergunta, resposta.resposta "
					+ "FROM questao INNER JOIN resposta "
					+ "ON questao.idquestao = resposta.idquetao"
					+ "WHERE questao.idquestao = ?");
			st.setInt(1, idPergunta);
			rs = st.executeQuery();
		    if (rs.next()) {
		    	AlternativaVO questao = instantiateAlternativaVO(rs);
		    	PerguntaVO pergunta = instantiatePerguntaVO(rs, questao);
		    	return pergunta;
		    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			PerguntaNaoExistenteException.closeStatement(st);
			PerguntaNaoExistenteException.closeResultSet(rs);
		};
		return null;

	}

	private PerguntaVO instantiatePerguntaVO(ResultSet rs, AlternativaVO questao) throws SQLException {
		    PerguntaVO pergunta = new PerguntaVO();
	    	pergunta.setCategoria(rs.getString("categoria"));
	    	return pergunta;
	}

	private AlternativaVO instantiateAlternativaVO(ResultSet rs) throws SQLException {
			AlternativaVO questao = new AlternativaVO();
	    	questao.setIdAlternativa(rs.getLong("idAlternativa"));
	    	questao.setIdPergunta(rs.getLong("idPergunta"));
	    	questao.setTexto(rs.getString("texto"));
	    	questao.setAlternativaCorreta(rs.getBoolean("alternativaCorreta"));
	    	return questao;
	}

	@Override
	public List<PerguntaVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerguntaVO completeResultset(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerguntaVO insert(PerguntaVO obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
