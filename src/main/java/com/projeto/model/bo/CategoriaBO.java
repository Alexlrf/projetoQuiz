package com.projeto.model.bo;

import java.sql.SQLException;
import java.util.List;

import com.projeto.exceptions.ErroNaConsultaException;
import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.dao.CategoriaDAO;
import com.projeto.model.dao.DisciplinaDAO;
import com.projeto.model.entity.CategoriaVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Utils;

public class CategoriaBO {
	
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	CategoriaVO categoria = new CategoriaVO();
	
	public boolean cadastraCategoria(CategoriaVO categoriaVO) throws SQLException {
		boolean retorno = true;
		CategoriaVO categoria = new CategoriaVO();
		String mensagem = "";
		if (categoriaDAO.consultaCategoriaExistente(categoriaVO)) {
			mensagem = "Categoria já cadastrada!\n";
			retorno = false;
		}else {
			categoriaVO.setIdDisciplina(disciplinaDAO.buscaIdDisciplina(categoriaVO.getIdUsuario()));
			categoria = categoriaDAO.insert(categoriaVO);
			if (categoria == null || categoria.getIdCategoria()==0) {
				mensagem = "Erro ao cadastrar categoria!\n";
				retorno = false;
			}
		}
		
		if (Utils.stringValida(mensagem)) {
			throw new SQLException(mensagem);
		}
		
		return retorno;
	}

	public List<CategoriaVO> consultaTodasCategorias(UsuarioVO usuarioLogado) throws ErroNaConsultaException{
		return categoriaDAO.buscaCategoriasUsuario(usuarioLogado);
	}

	public String alteraCategoria(String categoriaEscolhida, String categoriaAlterada, Integer idUsuario) {
		String mensagem = "";
		
		categoria = categoriaDAO.buscaCategoriaPorDescricao(categoriaEscolhida);
		if (categoria.getIdCategoria() == 0) {
			mensagem = "Categoria não encontrada!";			
			
		} else if (categoria.getIdUsuario() != idUsuario){
			mensagem = "Não é possível alterar CATEGORIA de outro usuário!";
			
		} else {
			boolean alteradaCategoria = categoriaDAO.alteraCategoria(categoriaEscolhida, categoriaAlterada);
			
			if (alteradaCategoria) {
				mensagem = "Categoria alterada!!";
			} else {
				mensagem = "Não foi possível alterar categoria!";
			}			
		}		
		return mensagem;
	}
	
	public CategoriaVO buscaCategoriaPorDescricao(String descricaoCategoria) { 
		
 		return categoriaDAO.buscaCategoriaPorDescricao(descricaoCategoria);
	}

}
