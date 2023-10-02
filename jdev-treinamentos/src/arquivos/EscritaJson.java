package arquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EscritaJson {

	public static void main(String[] args) throws IOException {

		Pessoa pessoa1 = new Pessoa("pessoa1", "pessoa1gmail.com", 20);
		Pessoa pessoa2 = new Pessoa("pessoa2", "pessoa2gmail.com", 50);
		Pessoa pessoa3 = new Pessoa("pessoa3", "pessoa3gmail.com", 20);
		Pessoa pessoa4 = new Pessoa("Tirineu Francisco", "tirineugmail.com", 38);

		List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2, pessoa3, pessoa4);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String json = gson.toJson(pessoas);
		
		System.out.println(json);
		
		FileWriter escritor = new FileWriter("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\pessoas.json");
		
		escritor.write(json);
		escritor.flush();
		escritor.close();
		
		
		
	}
}
