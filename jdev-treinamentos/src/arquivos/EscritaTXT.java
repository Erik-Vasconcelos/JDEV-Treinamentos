package arquivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EscritaTXT {

	public static void main(String[] args) throws IOException {
		
		Pessoa pessoa1 = new Pessoa("pessoa1", "pessoa1gmail.com", 20);
		Pessoa pessoa2 = new Pessoa("pessoa2", "pessoa2gmail.com", 50);
		Pessoa pessoa3 = new Pessoa("pessoa3", "pessoa3gmail.com", 20);
		
		List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2, pessoa3);
		
		File arquivo = new File("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\primeiroArquivo.txt");
		
		if(!arquivo.exists()) {
			arquivo.createNewFile();
		}
		
		FileWriter escritor = new FileWriter(arquivo);
		
		for (Pessoa p : pessoas) {
			escritor.write(p.getNome() + ";" + p.getEmail() + ";" + p.getIdade() + "\n");
		}
		
		escritor.flush();
		escritor.close();
		
	}
}
