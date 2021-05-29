package com.projeto.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.projeto.model.entity.AlunoVO;
import com.projeto.repository.BaseDao;

public class AlunoDAO implements BaseDao<AlunoVO>{

	@Override
	public AlunoVO insert(AlunoVO obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(AlunoVO obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AlunoVO findById(Integer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlunoVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlunoVO completeResultset(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
