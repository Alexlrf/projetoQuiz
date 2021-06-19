package com.projeto.controller;

import java.sql.SQLException;
import java.util.List;

import com.projeto.exceptions.ErroNoCadastroException;
import com.projeto.model.bo.AlternativaBO;
import com.projeto.model.bo.PerguntaBO;
import com.projeto.model.entity.AlternativaVO;
import com.projeto.model.entity.PerguntaVO;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.repository.Constants;
import com.projeto.repository.Utils;

public class AlternativaController {
	 
	AlternativaBO alternativaBO = new AlternativaBO();
	PerguntaBO perguntaBO = new PerguntaBO();
	
	public boolean cadastraQuestao(PerguntaVO pergunta) throws ErroNoCadastroException, SQLException {		
		
		return alternativaBO.cadastraQuestao(pergunta);
	}	

	public boolean validaAlternativas(List<AlternativaVO> listaAlternativas) {			
		return Utils.alternativaValida(listaAlternativas.get(0).getTexto(), listaAlternativas.get(1).getTexto()
				, listaAlternativas.get(2).getTexto(), listaAlternativas.get(3).getTexto(), listaAlternativas.get(4).getTexto());
	}	


	public List<AlternativaVO> buscaAlternativas(PerguntaVO pergunta) {	
 		return alternativaBO.buscaAlternativas(pergunta);
	}

	public boolean alteraAlternativa(List<AlternativaVO> alternativas, AlternativaVO alternativaVO, UsuarioVO usuarioLogado) throws ErroNoCadastroException{
		String mensagem = "";
		boolean retorno = true;
		
		int idBuscado = perguntaBO.buscaIdUsuario(alternativaVO.getIdPergunta());
		
		if (alternativaVO.getIdPergunta() == 0) {
			mensagem = "Erro ao encontrar pergunta da alternativa!\n";
			retorno = false;
		} else if (!Utils.stringValida(alternativaVO.getTexto())) {
			mensagem = "Erro ao encontrar TEXTO da alternativa!\n";
			retorno = false;
		} else if (!Utils.stringValida(alternativaVO.getAlternativaCorreta())) {
			mensagem = "Erro ao encontrar STATUS da alternativa!\n";
			retorno = false;
		} else if (idBuscado != usuarioLogado.getIdUsuario()) {
			mensagem = "Não é possível alterar ALTERNATIVA de outro usuário!\n";
			retorno = false;
		} else if (validaAlternativas(alternativas)) {
			mensagem = "Verifique a existência de alternativas em branco!\n";
			retorno = false;
		}else if (!validaAlternativaCorreta(alternativas)) {
			mensagem = "Verifique a escolha da alternativa correta!\n";
			retorno = false;
		} else {
			if(!alternativaBO.alteraAlternativa(alternativaVO)) {
				mensagem = "Não foi possível alterar ALTERNATIVA!\n";
				retorno = false;
			}
		}
		
		if (Utils.stringValida(mensagem)) {
			throw new ErroNoCadastroException(mensagem);
		}		
		return retorno;		
	}

	private boolean validaAlternativaCorreta(List<AlternativaVO> alternativas) {
		boolean listaValida =true;
		int contaAlternativaCorreta = 0;
		
		for (AlternativaVO alternativaVO : alternativas) {
			if (alternativaVO.getAlternativaCorreta().equalsIgnoreCase(Constants.ALTERNATIVA_CORRETA)) {
				contaAlternativaCorreta++;
			}			
		}
		
		if (contaAlternativaCorreta != 1) {
			listaValida =false;
		}
		
		return listaValida;
	}	

}
