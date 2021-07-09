package com.projeto.repository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Row;

import com.projeto.model.entity.PerguntaVO;

public class GeradorPlanilhaQuestoes {
	
	public void gerarPlanilhaPerguntas(List<PerguntaVO> perguntas, String caminho){
		String[] nomeColunas = {"PERGUNTA", "CATEGORIA"}; 
		HSSFWorkbook planilha = new HSSFWorkbook();
		HSSFSheet aba = planilha.createSheet("planilha 1");
		
		Row headerRow = aba.createRow(0);
		
		// Cria o cabeçalho
		for (int i =0; i < nomeColunas.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(nomeColunas[i]);				
		}
		
		// Cria linhas
		int rowNum = 1;
		for (PerguntaVO pergunta : perguntas) {
			Row novaLinha = aba.createRow(rowNum++);
			novaLinha.createCell(0).setCellValue(pergunta.getTextoPergunta());
			novaLinha.createCell(1).setCellValue(pergunta.getCategoria().getDescricaoCategoria());
			
		}
		
		// Ajusta o tamanho das colunas de acordo com seu conteúdo
		for (int i = 0; i < nomeColunas.length; i++) {
			aba.autoSizeColumn(i);
		}
		
		// Escreve o arquivo em disco no caminho informado
		FileOutputStream fileOut = null;
		
		try {
			fileOut = new FileOutputStream(caminho + ".xls");
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
