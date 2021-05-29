package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.repository.Banco;
import com.projeto.repository.BaseDao;
import com.projeto.seletor.PerguntaSeletor;

public class CategoriaDAO implements BaseDao<CategoriaVO>{

	@Override
	public CategoriaVO insert(CategoriaVO categoriaVO) throws SQLException {
		CategoriaVO categoria = new CategoriaVO();
		String sql = "INSERT INTO categoria (descricao_categoria) VALUES (?);";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql)){
			stmt.setString(1, categoriaVO.getDescricaoCategoria());
			stmt.executeUpdate();
			
			ResultSet id = stmt.getGeneratedKeys();
			if (id.next()) {				
				categoria.setIdCategoria(id.getInt(1));				
			}			
			
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar categoria!");
			throw new SQLException("Erro ao cadastrar categoria!");
		}		
		return categoria;
	}

	@Override
	public boolean update(CategoriaVO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CategoriaVO findById(Integer id) {
		CategoriaVO categoria = new CategoriaVO();
		String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();			
			if (rs.next()) {
				categoria = this.completeResultset(rs);				
			}
		} catch (SQLException e) {
			System.out.println("Erro na consulta de categoria por id!");
		}
		return categoria;
	}
	

	@Override
	public List<CategoriaVO> findAll() {
		CategoriaVO categoria = new CategoriaVO();
		List<CategoriaVO> listaCategorias = new ArrayList<>();
		String sql = "SELECT * FROM categoria";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)) {
			
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				categoria = this.completeResultset(rs);
				listaCategorias.add(categoria);
			}
		} catch (SQLException e) {
			System.out.println("Erro na consulta!");
		}
		return listaCategorias;
	}

	@Override
	public CategoriaVO completeResultset(ResultSet rs) throws SQLException {
		CategoriaVO categoriaVO = new CategoriaVO();
		categoriaVO.setIdCategoria(rs.getInt("id_categoria"));
		categoriaVO.setDescricaoCategoria(rs.getString("descricao_categoria"));
		
		return categoriaVO;
	}

	public boolean consultaCategoriaExistente(CategoriaVO categoriaVO) {
		CategoriaVO categoria = new CategoriaVO();
		boolean retorno = false;
		String sql = "SELECT * FROM categoria WHERE descricao_categoria = ?";
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			
			stmt.setString(1, categoriaVO.getDescricaoCategoria());
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				categoria = completeResultset(rs);
				retorno = true;
			}			
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar categoria por descrição!");
			retorno = true;
		}		
		return retorno;
	}

	public int buscaIdCategoria(String categoriaEscolhida) {
		int idRetornado = 0;
		String sql = "SELECT id_categoria FROM categoria WHERE descricao_categoria = ?"; 
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			
			stmt.setString(1, categoriaEscolhida);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {				
				idRetornado = rs.getInt(1);				
			}
			
		}catch (SQLException e) {
			System.out.println("Erro ao consultar categoria por descrição!");			
		}		
		return idRetornado;
	}

	public CategoriaVO buscaCategoriaPorDescricao(String descricaoCategoria) {
		CategoriaVO categoriaVO = new CategoriaVO();
		String sql = "SELECT * FROM categoria WHERE descricao_categoria = ?;";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			
			stmt.setString(1, descricaoCategoria);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				categoriaVO.setIdCategoria(rs.getInt("id_categoria"));
				categoriaVO.setDescricaoCategoria(rs.getString("descricao_categoria"));
				
			}
			
		}catch (SQLException e) {
			System.out.println("Erro ao consultar categoria por descrição!");
			
		}	
		
		return categoriaVO;
	}

	public CategoriaVO buscaCategoria(PerguntaVO p) {
		CategoriaVO categoriaVO = new CategoriaVO();
		String sql = "SELECT categoria.descricao_categoria, categoria.id_categoria"
				+ " FROM"
				+ 		" pergunta"
				+ " inner join"
				+ 		" categoria on categoria.id_categoria = pergunta.id_categoria"
				+ " WHERE"
				+		 " pergunta.id_pergunta = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			
			stmt.setInt(1, p.getIdPergunta());
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				categoriaVO.setIdCategoria(rs.getInt("id_categoria"));
				categoriaVO.setDescricaoCategoria(rs.getString("descricao_categoria"));				
			}
			
		}catch (SQLException e) {
			System.out.println("Erro ao consultar categoria por descrição!");
			
		}			
		return categoriaVO;
	}
	
}
