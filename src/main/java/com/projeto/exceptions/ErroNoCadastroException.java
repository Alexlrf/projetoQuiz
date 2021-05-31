package com.projeto.exceptions;

public class ErroNoCadastroException extends Exception{
	private static final long serialVersionUID = 1L;

	public  ErroNoCadastroException(String mensagem) {
		super(mensagem);
	}

}
