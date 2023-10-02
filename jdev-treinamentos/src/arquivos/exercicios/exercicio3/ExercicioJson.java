package arquivos.exercicios.exercicio3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import arquivos.exercicios.exercicio2.Aluno;

public class ExercicioJson {

	public static void main(String[] args) throws IOException {
		escreverArquivo();
		lerArquivo();
	}

	private static void escreverArquivo() throws IOException {
		Aluno aluno1 = new Aluno(20201134010006L, "Riam Claudino", "QUIM3M");
		Aluno aluno2 = new Aluno(20201137010004L, "Maria Clara", "INFO3V");
		Aluno aluno3 = new Aluno(20201131110001L, "Fabio Gustavo Júnior", "ADM2M");

		List<Aluno> alunos = new ArrayList<>(Arrays.asList(aluno1, aluno2, aluno3));

		File arquivo = new File("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\alunos.json");

		FileWriter escritor = new FileWriter(arquivo);
		escritor.write(new Gson().toJson(alunos));
		escritor.flush();
		escritor.close();

	}

	private static void lerArquivo() throws FileNotFoundException {
		List<Aluno> alunos = new ArrayList<>(Arrays.asList());

		FileReader leitor = new FileReader("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\alunos.json");
		JsonArray elementosJson = (JsonArray) JsonParser.parseReader(leitor);

		for (JsonElement e : elementosJson) {
			Aluno aluno = new Gson().fromJson(e, Aluno.class);

			alunos.add(aluno);
		}

		for (Aluno a : alunos) {
			System.out.println(a);
		}
	}

}
