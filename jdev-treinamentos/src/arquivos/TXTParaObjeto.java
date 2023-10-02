package arquivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TXTParaObjeto {

	public static void main(String[] args) throws FileNotFoundException {
		FileInputStream stream = new FileInputStream(
				new File("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\primeiroArquivo.txt"));

		Scanner leitor = new Scanner(stream, "UTF-8");

		List<Pessoa> pessoas = new ArrayList<>();

		while (leitor.hasNext()) {
			String linha = leitor.nextLine();

			if (linha != null && !linha.isBlank()) {
				String[] dados = linha.split("\\;");

				Pessoa pessoa = new Pessoa(dados[0], dados[1], Integer.parseInt(dados[2]));
				pessoas.add(pessoa);
			}
		}

		leitor.close();

		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa);
		}

	}

}
