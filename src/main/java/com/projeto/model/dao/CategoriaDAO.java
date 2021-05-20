package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.model.entity.CategoriaVO;
import com.projeto.repository.Banco;
import com.projeto.repository.BaseDao;

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
	public CategoriaVO findById(Integer obj) {
		// TODO Auto-generated method stub
		return null;
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

	
}
