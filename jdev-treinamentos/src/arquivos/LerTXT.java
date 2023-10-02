package arquivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LerTXT {
	
	public static void main(String[] args) throws FileNotFoundException {
		FileInputStream stream = new FileInputStream(new File("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\primeiroArquivo.txt"));
		
		Scanner leitor = new Scanner(stream, "UTF-8");
		
		while(leitor.hasNext()) {
			String linha = leitor.nextLine();
			
			if(linha != null && !linha.isBlank()) {
				System.out.println(linha);
			}
		}
		
		leitor.close();
	}

}
