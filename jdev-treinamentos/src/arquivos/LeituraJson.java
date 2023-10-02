package arquivos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class LeituraJson {
	
	public static void main(String[] args) throws FileNotFoundException {

		FileReader reader = new FileReader("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\pessoas.json");
		
		JsonArray elementos = (JsonArray) JsonParser.parseReader(reader);
		
		List<Pessoa> pessoas = new ArrayList<>();
		
		for(JsonElement e : elementos) {
			Pessoa pessoa = new Gson().fromJson(e, Pessoa.class);
			
			pessoas.add(pessoa);
		}
		
		System.out.println(pessoas);
		
	}

}
