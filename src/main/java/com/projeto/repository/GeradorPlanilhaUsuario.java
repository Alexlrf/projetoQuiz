package com.projeto.repository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.MaskFormatter;

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
		
		String[] nomeColunas = {"N O M E", "T I P O   D E   U S U A R I O", "T U R N O", "S E X O", 
				"P O S S U I   D E F I C I Ê N C I A", "R G", "C P F", "S T A T U S"}; 
		HSSFWorkbook planilha = new HSSFWorkbook();
		HSSFSheet aba = planilha.createSheet("Relatório de Usuarios");
		
		Row headerRow = aba.createRow(0);
		
		// Cria o cabeçalho
		for (int i =0; i < nomeColunas.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(nomeColunas[i]);			
		}
		
		// Cria linhas
		int rowNum = 1;
		for (UsuarioVO usu : usuario) {
			Row novaLinha = aba.createRow(rowNum++);
			novaLinha.createCell(0).setCellValue(usu.getNome());
			novaLinha.createCell(1).setCellValue(usu.getTipo().toString());
			novaLinha.createCell(2).setCellValue(usu.getTurno().toString());
			
			novaLinha.createCell(3).setCellValue("Feminino");
			if (usu.getSexo() == Constants.MASCULINO) {
				novaLinha.createCell(3).setCellValue("Masculino");
			}
			
			novaLinha.createCell(4).setCellValue("Não");
			if (usu.isPossuiDeficiencia()) {
				novaLinha.createCell(4).setCellValue("Sim");
			}
			
			novaLinha.createCell(5).setCellValue(usu.getRg());
			
			String cpfMascarado = this.mascararCpf(String.valueOf(usu.getCpf()));
			novaLinha.createCell(6).setCellValue(cpfMascarado);
			
			novaLinha.createCell(7).setCellValue("DESATIVADO");
			if (usu.isAtivo()) {
				novaLinha.createCell(7).setCellValue("ATIVADO");
			}
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
	
	private String mascararCpf(String cpf) {
		try {
			MaskFormatter mask = new MaskFormatter("AAA.AAA.AAA-AA");
			mask.setPlaceholderCharacter(' ');
			mask.setValueContainsLiteralCharacters(false);
			cpf = mask.valueToString(cpf);
		} catch (ParseException e) {
			System.out.println("Erro ao formatar rg: " + e.getMessage());
		}
		return cpf;
	}

}
