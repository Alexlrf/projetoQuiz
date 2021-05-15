package com.projeto.model.bo;

import java.util.List;

import com.projeto.model.dao.CategoriaDAO;
import com.projeto.model.entity.CategoriaVO;

public class CategoriaBO {
	
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	
	public boolean cadastraCategoria(CategoriaVO categoriaVO) {

		if (categoriaDAO.consultaCategoriaExistente(categoriaVO)) {
			
		}
		return false;
	}

	public List<CategoriaVO> consultaTodasCategorias() {
		return categoriaDAO.findAll();
	}

}
