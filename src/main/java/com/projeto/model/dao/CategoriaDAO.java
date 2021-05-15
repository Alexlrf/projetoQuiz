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
	public CategoriaVO insert(CategoriaVO obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
		} catch (Exception e) {
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
		CategoriaVO categoriaVO2 = new CategoriaVO();
		boolean retorno = true;
		String sql = "SELECT * FROM categoria WHERE descricao_categoria LIKE '%"+categoriaVO.getDescricaoCategoria()+"%'";
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);) {
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retorno;
	}

	
}
