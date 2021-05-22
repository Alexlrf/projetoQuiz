package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Banco;
import com.projeto.repository.BaseDao;

public class PerguntaDAO implements BaseDao<PerguntaVO> {
	

	@Override
	public List<PerguntaVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerguntaVO completeResultset(ResultSet rs) throws SQLException {
		PerguntaVO perguntaVO = new PerguntaVO();
		
		perguntaVO.setTextoPergunta(rs.getString("texto_pergunta"));
		perguntaVO.setCategoria(rs.getString("id_categoria"));		
		perguntaVO.setIdPergunta(rs.getInt("id_pergunta"));
		return perguntaVO;		
	}

//	public List<PerguntaVO> buscaPorCategoriaEscolhida(int idCategoria){
//		List<PerguntaVO> listaPerguntas = new ArrayList<PerguntaVO>();
//		String sql = "SELECT * FROM pergunta WHERE id_categoria = ?";
//		
//		try (Connection conn = Banco.getConnection();
//				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){			
//			
//			stmt.setInt(1, idCategoria);
//			ResultSet rs = stmt.executeQuery();
//			while(rs.next()) {				
//				PerguntaVO perguntaVO = new PerguntaVO();
//				perguntaVO = completeResultset(rs);
//				listaPerguntas.add(perguntaVO);
//				
//			}			
//			
//		} catch (SQLException e) {
//			System.out.println("Erro ao buscar pergunta por categoria!\n"+e.getMessage());
//		}
//		
//		return listaPerguntas;
//		
//	}
	
	
	public List<PerguntaVO> buscaPorCategoriaEscolhida(int idCategoria){
		List<PerguntaVO> listaPerguntas = new ArrayList<PerguntaVO>();
		PerguntaVO pergunta; 
		String sql= "SELECT	"
						+ "pergunta.id_usuario, pergunta.id_categoria, pergunta.texto_pergunta"
						+ ", pergunta.id_pergunta, categoria.descricao_categoria"
				+ " FROM "
						+ "pergunta"
				+ " INNER JOIN	"
						+ "categoria on categoria.id_categoria = pergunta.id_categoria"
				+ "	WHERE "
						+ "pergunta.id_categoria = ?";
				
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)){
			
			stmt.setInt(1, idCategoria);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				pergunta = new PerguntaVO();
				pergunta.setCategoria(rs.getString("categoria.descricao_categoria"));
				pergunta.setIdUsuario(rs.getInt("pergunta.id_usuario"));
				pergunta.setTextoPergunta(rs.getString("pergunta.texto_pergunta"));
				pergunta.setIdPergunta(rs.getInt("pergunta.id_pergunta"));
				listaPerguntas.add(pergunta);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pergunta por categoria!\n"+e.getMessage());
		}
		
		return listaPerguntas;
		
	}
	
	
	
	

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

	
	public List<PerguntaVO> buscaPorTextoDigitado(String textoDigitado) {
		List<PerguntaVO> listaPerguntas = new ArrayList<>();
		PerguntaVO pergunta; 
		String sql= "SELECT	"
							+ "pergunta.id_usuario, pergunta.id_categoria, pergunta.texto_pergunta"
							+ ", pergunta.id_pergunta, categoria.descricao_categoria"
					+ " FROM "
							+ "pergunta"
					+ " INNER JOIN	"
							+ "categoria on categoria.id_categoria = pergunta.id_categoria"
					+ "	WHERE "
							+ "texto_pergunta"
					+ " LIKE "
							+ "'%"+textoDigitado+"%'";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)){
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				pergunta = new PerguntaVO();
				pergunta.setCategoria(rs.getString("categoria.descricao_categoria"));
				pergunta.setIdUsuario(rs.getInt("pergunta.id_usuario"));
				pergunta.setTextoPergunta(rs.getString("pergunta.texto_pergunta"));
				pergunta.setIdPergunta(rs.getInt("pergunta.id_pergunta"));
				listaPerguntas.add(pergunta);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao consultar por texto digitado!\n"+e.getMessage());
		}		
		return listaPerguntas;
	}

}
