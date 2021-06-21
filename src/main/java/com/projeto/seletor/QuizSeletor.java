package com.projeto.seletor;

public class QuizSeletor {
	
	private String nomeAluno;
	private int acertos;
	
	public QuizSeletor(String nomeAluno, int acertos) {
		this.nomeAluno = nomeAluno;
		this.acertos = acertos;
	}

	public QuizSeletor() {
		super();
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}	

}
