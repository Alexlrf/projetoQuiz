package com.projeto.seletor;

import java.time.LocalDate;

import com.projeto.enums.TipoUsuarioEnum;
import com.projeto.enums.TurnoEnum;

public class PesquisarDeUsuarioSeletor {

	private TurnoEnum turno;
	private String nome;
	private TipoUsuarioEnum tipo;
	
	private int limite;
	private int pagina;
	
	public PesquisarDeUsuarioSeletor() {
		//Default: traz os resultados sem limite e sem página
		this.limite = 0;
		this.pagina = -1;
	}
	
	/**
	 * Verifica se os campos de paginacao estao preenchidos
	 *
	 * @return verdadeiro se os campos limite e pagina estao preenchidos
	 */
	public boolean temPaginacao() {
		return ((this.limite > 0) && (this.pagina > -1));
	}
	
	public PesquisarDeUsuarioSeletor(TurnoEnum turno, String nome, TipoUsuarioEnum tipo) {
		super();
		this.turno = turno;
		this.nome = nome;
		this.tipo = tipo;
	}

	/**
	 * Verifica se este seletor tem algum campo preenchido
	 *
	 * @return verdadeiro se existe algum campo de filtro preenchido
	 */
	public boolean temFiltro() {
		boolean retorno = false;
		
		if ((this.nome != null) && (this.nome.trim().length() > 0)) {
			retorno = true;
		}
		
		if ((this.tipo != null) && (this.tipo.toString().trim().length() > 0)) {
			retorno = true;
		}
		
		if ((this.turno != null) && (this.turno.toString().trim().length() > 0)) {
			retorno = true;
		}
		
		return retorno;
	}

	/**
	 * Calcula deslocamento (offset) a partir da pagina e do limite
	 *
	 * @return offset
	 */
	public int getOffset() {
		return (this.limite * (this.pagina - 1));
	}

	public TurnoEnum getTurno() {
		return turno;
	}

	public void setTurno(TurnoEnum turno) {
		this.turno = turno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoUsuarioEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuarioEnum tipo) {
		this.tipo = tipo;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
}
