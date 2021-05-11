package com.projeto.model.bo;

import java.util.List;

public class AlternativaBO {

	public void cadastraAlternativas(String pergunta, List<String> listaAlternativas) {
		System.out.println("Deu certo - "+pergunta.toString());
		
		for (String opcao : listaAlternativas) {
			System.out.println(opcao);
		}
		
	}

}
