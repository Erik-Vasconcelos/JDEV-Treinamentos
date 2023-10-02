package arquivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class EditandoPlanilha {
	
	public static void main(String[] args) throws IOException {
		File arquivo = new File("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\plainilha.xls");
		
		FileInputStream entrada = new FileInputStream(arquivo);
		
		HSSFWorkbook gerenciador = new HSSFWorkbook(entrada);
		HSSFSheet planilha = gerenciador.getSheetAt(0);
		
		Iterator<Row> linhas = planilha.iterator();
		
		while(linhas.hasNext()) {
			Row linha = linhas.next();
			
			int numeroColunas = linha.getPhysicalNumberOfCells();
			
			Cell coluna =  linha.createCell(numeroColunas);
			coluna.setCellValue("7.259,78");
		}
		
		entrada.close();
		
		FileOutputStream saida = new FileOutputStream(arquivo);
		gerenciador.write(saida);
		saida.flush();
		saida.close();
		
		System.out.println("Planilha atualizada!");
	}

}
