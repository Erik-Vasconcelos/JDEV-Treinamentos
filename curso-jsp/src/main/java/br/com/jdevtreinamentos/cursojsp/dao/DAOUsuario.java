package br.com.jdevtreinamentos.cursojsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.jdevtreinamentos.cursojsp.connection.FabricaConexao;
import br.com.jdevtreinamentos.cursojsp.model.Usuario;

public class DAOUsuario {

	private Connection connection;

	public DAOUsuario() {
		connection = FabricaConexao.getConnection();
	}

	public boolean autenticar(Usuario login) {
		try {
			String sql = "SELECT * FROM usuarios l WHERE l.login = ? and l.senha = ?";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, login.getLogin());
			stmt.setString(2, login.getSenha());
			
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
