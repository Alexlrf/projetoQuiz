package com.projeto.exceptions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PerguntaNaoExistenteException extends Exception {

	public PerguntaNaoExistenteException(String msg) {
		super(msg);
	}


	public static void closeStatement(PreparedStatement st) {
		// TODO Auto-generated method stub
		
	}


	public static void closeResultSet(ResultSet rs) {
		// TODO Auto-generated method stub
		
	}

}