package br.com.jdevtreinamentos.cursojsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdevtreinamentos.cursojsp.connection.FabricaConexao;
import br.com.jdevtreinamentos.cursojsp.model.Telefone;
import br.com.jdevtreinamentos.cursojsp.model.Usuario;

public class DAOTelefone {
	
	private Connection connection;

	public DAOTelefone(){
		connection = FabricaConexao.getConnection();
	}
	
	public void salvar(Telefone telefone) {
		try {
			
			if(telefone.isNovo()) {
				String sql  = "INSERT INTO telefones(numero, usuario_id) VALUES (?, ?)";
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, telefone.getNumero());
				stmt.setLong(2, telefone.getUsuario().getId());
				
				stmt.execute();
			}else {
				String sql  = "UPDATE telefones SET numero = ? WHERE id = ?";
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, telefone.getNumero());
				stmt.setLong(2, telefone.getUsuario().getId());
				
				stmt.execute();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Telefone> getTelefones(Usuario usuario){
		try {
			List<Telefone> telefones = new ArrayList<>();
			
			String sql  = "SELECT * FROM telefones WHERE usuario_id = ?";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				Telefone telefone = new Telefone();
				
				telefone.setId(result.getLong("id"));
				telefone.setNumero(result.getString("numero"));
				
				usuario.setId(result.getLong("usuario_id"));
				telefone.setUsuario(usuario);

				telefones.add(telefone);
			}
			return telefones;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void excluir(Telefone telefone) {
		try {
			String sql = "DELETE FROM telefones WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, telefone.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean telefoneUsuarioExists(Telefone telefone) {
		try {
			String sql = "SELECT COUNT(*) > 0 AS quantidade FROM telefones WHERE numero = ? and usuario_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, telefone.getNumero());
			stmt.setLong(2, telefone.getUsuario().getId());
			
			ResultSet result = stmt.executeQuery();
			result.next();
			
			boolean telefoneExiste = result.getBoolean("quantidade");

			return telefoneExiste;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
	}

}
