package com.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;
import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.CoordenadorVO;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Banco;
import com.projeto.seletor.RelatorioDeUsuarioSeletor;

public class UsuarioDAO{
	
	/**
	 * Verifica se o cpf está correto
	 * @param cpf
	 * @return
	 */
	public boolean verificarCpfDAO(String cpf) {
		boolean validar  = false;
		
		String sql = "SELECT CPF FROM USUARIO WHERE CPF = '" + cpf + "'";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet valido = stmt.executeQuery();
			
			// valida se a consulta tenha algum retorno
			if (valido.next()) {
				validar = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar a existência de login no banco." + e.getMessage());
		}
		return validar;
	}
	
	/**
	 * Verifica se a senha está correta
	 * @param senha
	 * @return
	 */
	public boolean verificarSenhaDAO(String senha) {
		boolean validar  = false;
		
		String sql = "SELECT SENHA FROM USUARIO WHERE SENHA = MD5('" + senha + "')";

		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet valido = stmt.executeQuery();
			
			// valida se a consulta tenha algum retorno
			if (valido.next()) {
				validar = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar a existência de login no banco." + e.getMessage());
		}
		return validar;
	}
	
	/**
	 * verifica a existencia de um usuario através do parametros passados, identifica qual o seu tipo e preenche seus atributos.
	 * @param cpf
	 * @param senha
	 * @return tipo de usuario e seus atributos.
	 */
	public UsuarioVO verificarCpfSenhaDAO(String cpf, String senha) {
		UsuarioVO usuario = null;
		String sql = "SELECT * FROM usuario WHERE cpf = '" + cpf + "' AND senha = MD5('" + senha + "')";
	
		try (Connection conn = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);){
			
			ResultSet rs = stmt.executeQuery();
			
			// valida se a consulta tenha algum retorno
			if (rs.next()) {
				String tipo = rs.getString("TIPO");
				
				// Verifica se o usuario é um aluno
				if(tipo.equals("ALUNO")) {
					AlunoVO usuarioAluno = new AlunoVO();
					usuarioAluno = (AlunoVO) preencherAtributos(usuarioAluno, rs);
					return usuarioAluno;
				
					// Verifica se o usuario é um professor
				} else if (tipo.equals("PROFESSOR")) {
					ProfessorVO usuarioProfessor = new ProfessorVO();
					usuarioProfessor = (ProfessorVO) this.preencherAtributos(usuarioProfessor, rs);
					usuarioProfessor.setDisciplina(rs.getString("DISCIPLINA"));
					return usuarioProfessor;
					
					// Verifica se o usuario é um coordenador
				} else if (tipo.equals("COORDENADOR")) {
					CoordenadorVO usuarioCoordenador = new CoordenadorVO();
					usuarioCoordenador = (CoordenadorVO) this.preencherAtributos(usuarioCoordenador, rs);
					return usuarioCoordenador;
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar a existência de login no banco." + e.getMessage());
		}
		return usuario;
	}
	
	/**
	 * Preenche os atributos em comum entre os usuarios
	 * @param usuario
	 * @param rs
	 * @return usuario com os atributos preenchidos
	 * @throws SQLException
	 */
	private UsuarioVO preencherAtributos(UsuarioVO usuario, ResultSet rs) throws SQLException {
		usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
		usuario.setNome(rs.getString("NOME"));
		usuario.setRg(rs.getString("RG"));
		usuario.setCpf(rs.getString("CPF"));
		usuario.setDataNascimento(rs.getDate("DT_NASCIMENTO").toLocalDate());
		usuario.setSexo(rs.getString("SEXO").charAt(0));
		usuario.setPossuiDeficiencia(rs.getBoolean("POSSUI_DEFICIENCIA"));
		usuario.setCelular(rs.getString("CELULAR"));
		usuario.setNacionalidade(rs.getString("NACIONALIDADE"));
		usuario.setTurno(TurnoEnum.getTurnoEnum(rs.getString("TURNO")));
		usuario.setTipo(TipoUsuarioEnum.getTipoUsuarioEnum(rs.getString("TIPO")));
		return usuario;
	}

	public List<UsuarioVO> relatorioUsuarioSeletorDAO(RelatorioDeUsuarioSeletor relatorioUsuario) {
		
		return null;
	}

}
