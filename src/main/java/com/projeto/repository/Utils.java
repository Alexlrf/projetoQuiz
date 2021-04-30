package com.projeto.repository;

import java.time.LocalDate;

public class Utils {
	
	public static boolean stringValida(String string) {
		boolean retorno = false;
		if (string != null && !string.isEmpty()) {
			retorno = true;
			
		}		
		return retorno;
	}
	
	public static boolean dataValida(LocalDate data) {
		boolean retorno = true;
		LocalDate dataAtual = LocalDate.now();
		if (data.isAfter(dataAtual)) {
			retorno = false;
			
		}		
		return retorno;		
	}

}
