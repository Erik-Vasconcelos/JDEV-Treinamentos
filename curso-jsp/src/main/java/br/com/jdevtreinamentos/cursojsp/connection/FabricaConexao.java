package br.com.jdevtreinamentos.cursojsp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

	private static final String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static final String user = "postgres";
	private static final String password = "postgre2023";
	private static Connection connection = null;

	static {
		conectar();
	}

	public FabricaConexao() {
		conectar();
	}

	public static Connection getConnection() {
		return connection;
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
