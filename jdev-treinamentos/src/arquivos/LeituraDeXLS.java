package arquivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class LeituraDeXLS {

	public static void main(String[] args) throws IOException {
		FileInputStream entrada = new FileInputStream(new File("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\plainilha.xls"));
		
		HSSFWorkbook gerenciador = new HSSFWorkbook(entrada);
		HSSFSheet planilha = gerenciador.getSheetAt(0);
		
		Iterator<Row> linhas = planilha.iterator();
		List<Pessoa> pessoas = new ArrayList<>();
		
		while(linhas.hasNext()) {
			Row linha = linhas.next();
			
			Pessoa pessoa = new Pessoa();
			Iterator<Cell> colunas = linha.iterator();
			
			while(colunas.hasNext()) {
				Cell coluna = colunas.next();
				 
				switch(coluna.getColumnIndex()) {
				case 0:
					pessoa.setNome(coluna.getStringCellValue());
					break;
				case 1:
					pessoa.setEmail(coluna.getStringCellValue());
					break;
				case 2:
					pessoa.setIdade(Double.valueOf(coluna.getNumericCellValue()).intValue());
					break;
				}
				
			}
			pessoas.add(pessoa);
			
		}
		
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa);
		}
		
	}

}
