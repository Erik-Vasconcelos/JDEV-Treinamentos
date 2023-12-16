package br.com.jdevtreinamentos.cursojsp.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.jdevtreinamentos.cursojsp.connection.FabricaConexao;

public class DAOImplantacao {

	private Connection connection;

	public DAOImplantacao() {
		connection = FabricaConexao.getConnection();
	}

	public void executarArquivo(File arquivo) {
		if (arquivoWasExecuted(arquivo.getName())) {
			return;
		}

		try {
			StringBuilder sql = new StringBuilder();
			Scanner leitor = new Scanner(new FileInputStream(arquivo));

			while (leitor.hasNext()) {
				sql.append(leitor.nextLine());
				sql.append("\n");
			}

			PreparedStatement stmt = connection.prepareStatement(sql.toString());
			stmt.execute();

			connection.commit();
			insertArquivoSQL(arquivo.getName());
			
			connection.commit();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	private void insertArquivoSQL(String nomeArquivo) {
		String sql = "INSERT INTO ARQUIVOS_IMPLANTACAO_BD(nome_arquivo) VALUES(?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeArquivo);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean arquivoWasExecuted(String nomeArquivo) {
		String sql = "SELECT COUNT(id) > 0 AS executado FROM ARQUIVOS_IMPLANTACAO_BD WHERE nome_arquivo = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nomeArquivo);

			ResultSet resultado = stmt.executeQuery();

			if (resultado.next()) {
				return resultado.getBoolean("executado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	

}
