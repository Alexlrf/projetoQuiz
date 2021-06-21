package com.projeto.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {	

	/**
	 * Cadastra um novo item no banco;
	 * 
	 * @param obj - objeto com os dados a serem persistidos no banco em sua tabela
	 *            relacionada;
	 * 
	 * @return novo item com a PK cadastrada no banco
	 * 
	 * @throws SQLException
	 */
	public T insert(T obj) throws SQLException;

	/**
	 * Atualiza um objeto na tabela do banco;
	 * 
	 * @param obj - objeto com os dados a serem alterados e persistidos no banco;
	 * 
	 * @return se o objeto foi alterado com sucesso;
	 */
	public boolean update(T obj);

	/**
	 * Exclui um objeto na tabela do banco;
	 * 
	 * @param obj - id do objeto a ser exclu�do do banco;
	 * 
	 * @return se o objeto foi exclu�do com sucesso;
	 */
	public boolean delete(Integer obj);

	/**
	 * Busca um objeto na tabela do banco;
	 * 
	 * @param obj - id do objeto a ser buscado no banco;
	 * 
	 * @return objeto consultado;
	 */
	public T findById(Integer obj);

	/**
	 * Consulta todos os objetos na tabela do banco;
	 * 
	 * @param
	 * 
	 * @return lista de objetos da consulta
	 */
	public List<T> findAll();

	public T completeResultset(ResultSet rs) throws SQLException;

}
