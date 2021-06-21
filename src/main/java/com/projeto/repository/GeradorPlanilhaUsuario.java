package com.projeto.repository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.projeto.controller.UsuarioController;
import com.projeto.model.entity.UsuarioVO;
import com.projeto.seletor.PesquisarUsuarioSeletor;

public class GeradorPlanilhaUsuario {

	public void gerarPlanilhaUsuarios(PesquisarUsuarioSeletor seletor, String caminhoEscolhido) {
		
		List<UsuarioVO> usuario = new ArrayList<>();
		UsuarioController usuarioController = new UsuarioController();
		
		seletor.setLimite(0);
		seletor.setPagina(-1);
		
		usuario = usuarioController.pesquisarUsuarioController(seletor);
		
		String[] nomeColunas = {"Nome", "Tipo de Usuario", "Turno", "Sexo", "Possui Deficiência", "RG", "CPF", "Status"}; 
		HSSFWorkbook planilha = new HSSFWorkbook();
		HSSFSheet aba = planilha.createSheet("Relatório de Usuarios");
		
		Row headerRow = aba.createRow(0);
		
		// Cria o cabeçalho
		for (int i =0; i < nomeColunas.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(nomeColunas[i]);			
		}
		
		// Cria linhas
		int rowNum = 0;
		for (UsuarioVO usu : usuario) {
			Row novaLinha = aba.createRow(rowNum++);
			novaLinha.createCell(0).setCellValue(usu.getNome());
			novaLinha.createCell(1).setCellValue(usu.getTipo().toString());
			novaLinha.createCell(2).setCellValue(usu.getTurno().toString());
			
			if (usu.getSexo() == Constants.MASCULINO) {
				novaLinha.createCell(3).setCellValue("Masculino");
			} else {
				novaLinha.createCell(3).setCellValue("Feminino");
			}
			
			if (usu.isPossuiDeficiencia()) {
				novaLinha.createCell(4).setCellValue("Sim");
			} else {
				novaLinha.createCell(4).setCellValue("Não");
			}
			
			novaLinha.createCell(5).setCellValue(usu.getRg());
			novaLinha.createCell(6).setCellValue(usu.getCpf());
			novaLinha.createCell(7).setCellValue(usu.isAtivo());
		}
		
		// Ajusta o tamanho das colunas de acordo com seu conteúdo
		for (int i = 0; i < nomeColunas.length; i++) {
			aba.autoSizeColumn(i);
		}
		
		// Escreve o arquivo em disco no caminho informado
		FileOutputStream fileOut = null;
		
		try {
			fileOut = new FileOutputStream(caminhoEscolhido + ".xls");
			planilha.write(fileOut);
			
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
					planilha.close();
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
		
	}

}
