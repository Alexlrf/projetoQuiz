package com.projeto.controller;

import javax.swing.JOptionPane;

import com.projeto.exceptions.LoginNaoInformadoException;
import com.projeto.exceptions.SenhaIncorretaException;
import com.projeto.exceptions.SenhaNaoInformadaException;
import com.projeto.exceptions.UsuarioNaoExistenteException;
import com.projeto.model.bo.UsuarioBO;
import com.projeto.model.entity.AlunoVO;
import com.projeto.model.entity.CoordenadorVO;
import com.projeto.model.entity.ProfessorVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.view.TelaPrincipal;

public class UsuarioController {

	public UsuarioVO verificarLoginController(String cpf, String senha) throws UsuarioNaoExistenteException, LoginNaoInformadoException, SenhaNaoInformadaException, SenhaIncorretaException {
		UsuarioBO verificarLogin = new UsuarioBO();
		TelaPrincipal telaProfessor = new TelaPrincipal();

		if (cpf == null || cpf.trim().isEmpty()) {
			throw new LoginNaoInformadoException("Login não informado");
		} else if (senha.trim().isEmpty()) {
			throw new SenhaNaoInformadaException("Senha não informada");
		}
		
		UsuarioVO usuario = verificarLogin.verificarLoginBO(cpf, senha);
		
		if (usuario == null || usuario.getIdUsuario() <= 0) {
			throw new UsuarioNaoExistenteException("Usuario não cadastrado");
		}
		
		if (usuario.getIdUsuario() > 0 && usuario.getSenha().trim().isEmpty()) {
			throw new SenhaIncorretaException("Senha incorreta");
		}
		
		if(usuario instanceof AlunoVO) {
			AlunoVO aluno = (AlunoVO) usuario;
			JOptionPane.showMessageDialog(null, "Olá aluno" + aluno.getNome() + ", seu menu será chamado em breve... ☺");
		} else if (usuario instanceof ProfessorVO) {
			ProfessorVO professor = (ProfessorVO) usuario;
			JOptionPane.showMessageDialog(null, "Olá professor " + professor.getNome() + " disciplina " + professor.getDisciplina() + ", seu menu será chamado em breve... ☺");
		} else if (usuario instanceof CoordenadorVO) {
			CoordenadorVO coordenador = (CoordenadorVO) usuario;
			JOptionPane.showMessageDialog(null, "Olá coordenador " + coordenador.getNome() + ", seu menu será chamado em breve... ☺");
		}
		
		telaProfessor.setUsuario(usuario);
		telaProfessor.setVisible(true);
		return usuario;
	}

}
