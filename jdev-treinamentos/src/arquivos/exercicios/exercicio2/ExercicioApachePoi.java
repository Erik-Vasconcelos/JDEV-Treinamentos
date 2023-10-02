package arquivos.exercicios.exercicio2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * 
 * @author Erik Vasconcelos
 * @since 21/08/2023
 * 
 *        Crie uma planilha para inserir os dados de alunos como matricula,
 *        nome, e turma e depois leia o arquivo e monte os objetos
 *
 */

public class ExercicioApachePoi {

	public static void main(String[] args) throws IOException {
		criarEscreverArquivo();
		lerArquivo();
	}

	private static void criarEscreverArquivo() throws IOException {
		Aluno aluno1 = new Aluno(20201134010006L, "Riam Claudino", "QUIM3M");
		Aluno aluno2 = new Aluno(20201137010004L, "Maria Clara", "INFO3V");
		Aluno aluno3 = new Aluno(20201131110001L, "Fabio Gustavo Júnior", "ADM2M");

		List<Aluno> alunos = new ArrayList<>(Arrays.asList(aluno1, aluno2, aluno3));

		File arquivo = new File("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\planilhaAlunos.xls");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		HSSFWorkbook gerenciador = new HSSFWorkbook();
		HSSFSheet planilha = gerenciador.createSheet("Planilha dos alunos");

		int numeroLinha = 0;
		for (Aluno a : alunos) {
			Row linha = planilha.createRow(numeroLinha++);

			int numeroColuna = 0;
			Cell colunaMatricula = linha.createCell(numeroColuna++);
			colunaMatricula.setCellValue(a.getMatricula());

			Cell colunaNome = linha.createCell(numeroColuna++);
			colunaNome.setCellValue(a.getNome());

			Cell colunaTurma = linha.createCell(numeroColuna++);
			colunaTurma.setCellValue(a.getTurma());

		}

		FileOutputStream saida = new FileOutputStream(arquivo);
		gerenciador.write(saida);
		saida.flush();
		gerenciador.close();
	}

	private static void lerArquivo() throws IOException {
		File arquivo = new File("D:\\Projetos_Java\\jdev-treinamentos\\arquivos\\planilhaAlunos.xls");

		FileInputStream entrada = new FileInputStream(arquivo);

		HSSFWorkbook gerenciador = new HSSFWorkbook(entrada);
		HSSFSheet planilha = gerenciador.getSheetAt(0);

		List<Aluno> alunos = new ArrayList<>();

		Iterator<Row> linhas = planilha.iterator();
		while (linhas.hasNext()) {
			Row linha = linhas.next();

			Aluno aluno = new Aluno();

			Iterator<Cell> colunas = linha.iterator();
			while (colunas.hasNext()) {
				Cell coluna = colunas.next();

				int numeroColuna = coluna.getColumnIndex();

				switch (numeroColuna) {
				case 0:
					aluno.setMatricula(Double.valueOf(coluna.getNumericCellValue()).longValue());
					break;
				case 1:
					aluno.setNome(coluna.getStringCellValue());
					break;
				case 2:
					aluno.setTurma(coluna.getStringCellValue());
					break;
				}
			}

			alunos.add(aluno);
		}

		for (Aluno aluno : alunos) {
			System.out.println(aluno);
		}
	}

}
