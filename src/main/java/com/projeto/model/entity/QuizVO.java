package com.projeto.model.entity;

import java.util.List;

public class QuizVO {
	
	private List<PerguntaVO> perguntas;
	private UsuarioVO aluno;  
	private int acertos;
	private int idQuiz;
	
	public QuizVO(UsuarioVO aluno, int idQuiz, List<PerguntaVO> perguntas, int acertos) {
		super();
		this.aluno = aluno;
		this.idQuiz = idQuiz;
		this.perguntas = perguntas;
		this.acertos = acertos;
	}

	public QuizVO() {
		super();
	}

	public UsuarioVO getAluno() {
		return aluno;
	}

	public void setAluno(UsuarioVO aluno) {
		this.aluno = aluno;
	}

	public int getIdQuiz() {
		return idQuiz;
	}

	public void setIdQuiz(int idQuiz) {
		this.idQuiz = idQuiz;
	}

	public List<PerguntaVO> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<PerguntaVO> perguntas) {
		this.perguntas = perguntas;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}
		
}
