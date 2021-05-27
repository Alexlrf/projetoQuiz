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

public class PerguntaDAO implements BaseDao<PerguntaVO> {
	PerguntaVO perguntaVO = new PerguntaVO();
	CategoriaVO categoriaVO = new CategoriaVO();
	CategoriaDAO categoriaDAO = new CategoriaDAO();
	
	@Override
	public PerguntaVO insert(PerguntaVO pergunta) throws SQLException {
		String sql = "INSERT INTO pergunta (id_usuario, id_categoria, texto_pergunta) values (?, ?, ?);";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, sql)){
			
			stmt.setInt(1, 5);
			stmt.setInt(2, pergunta.getCategoria().getIdCategoria());
			stmt.setString(3, pergunta.getTextoPergunta());
			stmt.executeUpdate();
			ResultSet id = stmt.getGeneratedKeys();
			if (id.next()) {				
				perguntaVO.setIdPergunta(id.getInt(1));
			}			
			
		} catch(SQLException e) {
			System.out.println("Erro no cadastro de pergunta!");
		}
		
		return perguntaVO;
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
		
		perguntaVO.setTextoPergunta(rs.getString("texto_pergunta"));				
		perguntaVO.setIdPergunta(rs.getInt("id_pergunta"));
		return perguntaVO;		
	}

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
		String sqlCategoria = "";
				
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)){
			
			stmt.setInt(1, idCategoria);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				pergunta = new PerguntaVO();
				pergunta.setCategoria(categoriaDAO.findById(idCategoria));
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

	public List<PerguntaVO> buscaComSeletor(PerguntaSeletor perguntaSeletor) {
		
		List<PerguntaVO> listaPerguntasBuscadas = new ArrayList<>();
		PerguntaVO p;
		String sql = "SELECT * FROM Pergunta p";
		
		if (perguntaSeletor.temFiltro()) {
			sql = criarFiltros(perguntaSeletor, sql);
		}
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)){
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				p = new PerguntaVO();
				p.setTextoPergunta(rs.getString("texto_pergunta"));
				p.setCategoria(categoriaDAO.findById(perguntaSeletor.getIdCategoria()));
				p.setIdPergunta(rs.getInt("id_pergunta"));
				
				listaPerguntasBuscadas.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar vacinas com filtros.\nCausa: " + e.getMessage());
			
		}		
		return listaPerguntasBuscadas;
	}
	

	private String buscaNomeCategoriaPorId(ResultSet rs) {
		String categoria = "";
		
		String sql = "SELECT descricao_categoria FROM categoria WHERE id_categoria = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)){
			
			stmt.setInt(1, rs.getInt("id_categoria"));
			ResultSet res = stmt.executeQuery();

			if (res.next()) {
				categoria = res.getString("descricao_categoria");				
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pergunta por categoria!\n"+e.getMessage());
		}	
		
		return categoria;
	}

	private String criarFiltros(PerguntaSeletor perguntaSeletor, String sql) {
		sql += " WHERE ";
		boolean primeiro = true;
		
		if (perguntaSeletor.getTexto().trim().length() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.texto_pergunta LIKE '%" + perguntaSeletor.getTexto() + "%'";
			primeiro = false;
		}
		
		if (perguntaSeletor.getIdCategoria() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.id_categoria = " + perguntaSeletor.getIdCategoria() ;
			primeiro = false;
		}		
		return sql;
	}

	public CategoriaVO buscaIdcategoria(String categoria) {
		
		String sql = "SELECT id_categoria FROM categoria WHERE descricao_categoria = ?";
		
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql)){
			
			stmt.setString(1, categoria);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				categoriaVO.setIdCategoria(rs.getInt("id_categoria"));				
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pergunta por categoria!\n"+e.getMessage());
		}		
		return categoriaVO;
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
				pergunta.setCategoria(buscaIdcategoria(textoDigitado));
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
