package com.projeto.model.bo;

import java.util.List;

import com.projeto.model.entity.PerguntaVO;

public class AlternativaBO {

	public void cadastraAlternativas(PerguntaVO pergunta, List<String> listaAlternativas) {
		System.out.println(" CATEGORIA: "+pergunta.getCategoria()+"\n\n PERGUNTA: "+pergunta.getTextoPergunta()+"\n\n");
		int cont = 1;
		for (String opcao : listaAlternativas) {
			System.out.println(" >> ALTERNATIVA "+cont+":  "+opcao+"\n");
			cont ++;
		}
		
	}

}
