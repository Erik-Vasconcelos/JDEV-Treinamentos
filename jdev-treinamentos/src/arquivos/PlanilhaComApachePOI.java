package arquivos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class PlanilhaComApachePOI {
	
	public static void main(String[] args) throws IOException {
		
		Pessoa pessoa1 = new Pessoa("pessoa1", "pessoa1gmail.com", 20);
		Pessoa pessoa2 = new Pessoa("pessoa2", "pessoa2gmail.com", 50);
		Pessoa pessoa3 = new Pessoa("pessoa3", "pessoa3gmail.com", 20);
		Pessoa pessoa4 = new Pessoa("Tirineu Francisco", "tirineugmail.com", 38);

		List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2, pessoa3, pessoa4);
		
		File file = new File("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\plainilha.xls");
		
		if(!file.exists()) {
			file.createNewFile();
		}
		
		HSSFWorkbook gerenciador = new HSSFWorkbook();
		HSSFSheet planilha = gerenciador.createSheet("Minha primeira planilha com Apache POI");//cria a planilha
		
		int numeroLinha = 0;
		
		for (Pessoa p : pessoas) {
			Row linha = planilha.createRow(numeroLinha ++);
			
			int numeroColuna = 0;
			
			Cell colunaNome = linha.createCell(numeroColuna ++);
			colunaNome.setCellValue(p.getNome());
			
			Cell colunaEmail = linha.createCell(numeroColuna ++);
			colunaEmail.setCellValue(p.getEmail());
			
			Cell colunaIdade = linha.createCell(numeroColuna ++);
			colunaIdade.setCellValue(p.getIdade());
			
		}
		
		FileOutputStream saida = new FileOutputStream(file);
		gerenciador.write(saida); //escreve no outputStream
		saida.flush(); //realiza a gravação no arquivo 
		saida.close();
		
		gerenciador.close();
	}

}
