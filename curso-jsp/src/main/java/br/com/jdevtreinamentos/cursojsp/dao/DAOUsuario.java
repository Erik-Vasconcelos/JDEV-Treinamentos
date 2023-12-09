package br.com.jdevtreinamentos.cursojsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.jdevtreinamentos.cursojsp.connection.FabricaConexao;
import br.com.jdevtreinamentos.cursojsp.model.Usuario;

//record UsuarioRecord(String nome) {};//Java record - disponivel a partir do Java 14

public class DAOUsuario {

	private Connection connection;
	private final int REGISTROS_POR_PAGINA = 5;

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

	public Optional<Usuario> getUsuarioPorId(Long id, Long currentUserId) {
		Optional<Usuario> optional = Optional.empty(); // Inicia um optinal vazio
		String sql = "SELECT * FROM usuarios WHERE id = ? AND admin IS FALSE AND current_user_id = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.setLong(2, currentUserId);

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(result.getLong("id"));
				usuario.setNome(result.getString("nome"));
				usuario.setEmail(result.getString("email"));
				usuario.setDataNascimento(result.getDate("data_nascimento"));
				usuario.setSalario(result.getDouble("salario"));
				usuario.setLogin(result.getString("login"));
				usuario.setSenha(result.getString("senha"));
				usuario.setPerfil(result.getString("perfil"));
				usuario.setAdmin(result.getBoolean("admin"));
				usuario.setSexo(result.getString("sexo"));
				usuario.setImagem(result.getString("imagem"));
				usuario.setExtensaoImagem(result.getString("extensaoImagem"));

				usuario.setCep(result.getString("cep"));
				usuario.setLogradouro(result.getString("logradouro"));
				usuario.setBairro(result.getString("bairro"));
				usuario.setCidade(result.getString("cidade"));
				usuario.setEstado(result.getString("estado"));
				usuario.setNumero(result.getInt("numero"));

				optional = Optional.ofNullable(usuario); // Retorna um valor pedido pelo método. Pode ser nulo ou não
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return optional;
	}

	public Optional<Usuario> getUsuarioPorLoginAuth(String login) {
		Optional<Usuario> optional = Optional.empty(); // Inicia um optinal vazio
		String sql = "SELECT * FROM usuarios WHERE login = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, login);

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(result.getLong("id"));
				usuario.setNome(result.getString("nome"));
				usuario.setEmail(result.getString("email"));
				usuario.setLogin(result.getString("login"));
				usuario.setDataNascimento(result.getDate("data_nascimento"));
				usuario.setSalario(result.getDouble("salario"));
				usuario.setSenha(result.getString("senha"));
				usuario.setPerfil(result.getString("perfil"));
				usuario.setAdmin(result.getBoolean("admin"));
				usuario.setSexo(result.getString("sexo"));
				usuario.setImagem(result.getString("imagem"));
				usuario.setExtensaoImagem(result.getString("extensaoImagem"));

				optional = Optional.ofNullable(usuario); // Retorna um valor pedido pelo método. Pode ser nulo ou não
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return optional;
	}

	public int getTotalPaginas(Long currentUserId) {
		try {
			String sql = "SELECT COUNT(id) AS quantidade FROM usuarios";
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();
			if (result.next()) {

				int totalRegistros = result.getInt("quantidade");

				int totalPaginas = totalRegistros / REGISTROS_POR_PAGINA;

				if (totalRegistros % REGISTROS_POR_PAGINA > 0) {
					totalPaginas++;
				}

				return totalPaginas;
			}

			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getTotalPaginasPesquisa(Long currentUserId, String parteNome) {
		try {
			String sql = "SELECT COUNT(id) AS quantidade FROM usuarios WHERE nome ILIKE ? AND admin IS FALSE";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "%" + parteNome + "%");

			ResultSet result = stmt.executeQuery();
			if (result.next()) {

				int totalRegistros = result.getInt("quantidade");

				int totalPaginas = totalRegistros / REGISTROS_POR_PAGINA;

				if (totalRegistros % REGISTROS_POR_PAGINA > 0) {
					totalPaginas++;
				}

				return totalPaginas;
			}

			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int getTotalRegistrosPesquisa(Long currentUserId, String parteNome) {
		try {
			String sql = "SELECT COUNT(id) AS quantidade FROM usuarios WHERE nome ILIKE ? AND admin IS FALSE";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "%" + parteNome + "%");

			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return result.getInt("quantidade");
			}
			
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	

	public List<Usuario> pesquisarUsuarioPorNome(String parteNome, int offset) {
		List<Usuario> usuarios = new ArrayList<>();

		try {
			String sql = "SELECT * FROM usuarios WHERE nome ILIKE ? AND admin IS FALSE OFFSET ? LIMIT ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "%" + parteNome + "%");
			stmt.setInt(2, offset);
			stmt.setInt(3, REGISTROS_POR_PAGINA);

			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getLong("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setPerfil(resultSet.getString("perfil"));
				usuario.setDataNascimento(resultSet.getDate("data_nascimento"));
				usuario.setSalario(resultSet.getDouble("salario"));

				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public List<Usuario> getUsuarios(Long currentUserId, int offset) {
		List<Usuario> usuarios = new ArrayList<>();

		try {
			String sql = "SELECT * FROM usuarios WHERE admin IS FALSE AND current_user_id = ? OFFSET ? LIMIT ? ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, currentUserId);
			stmt.setInt(2, offset);
			stmt.setInt(3, REGISTROS_POR_PAGINA);

			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getLong("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setPerfil(resultSet.getString("perfil"));
				usuario.setDataNascimento(resultSet.getDate("data_nascimento"));
				usuario.setSalario(resultSet.getDouble("salario"));

				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public boolean loginValido(String login) {
		String sql = "SELECT COUNT(id) FROM usuarios WHERE login = ?";
		int ocorrenciasLogin = 0;

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, login);

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				ocorrenciasLogin = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ocorrenciasLogin == 0;
	}

	public Usuario salvar(Usuario usuario, Long currentUserId) {
		try {
			String sql = "";
			PreparedStatement stmt = null;

			if (usuario.isNovo()) {
				if (!loginValido(usuario.getLogin())) {
					throw new IllegalArgumentException("O login '" + usuario.getLogin() + "' já existe no banco!");
				}
				sql = "INSERT INTO usuarios(nome, email, login, senha, perfil, sexo, cep, logradouro, bairro, cidade, estado, numero, data_nascimeneto, salario, current_user_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				setDadosStatement(stmt, usuario, currentUserId);
				stmt.execute();
				connection.commit();

				ResultSet result = stmt.getGeneratedKeys();

				Long id = null;
				if (result.next()) {
					id = result.getLong("id");
					usuario.setId(id);
				}

				if (usuario.getImagem() != null && !usuario.getImagem().trim().isEmpty()) {
					updateImagemUsuario(usuario);
				}

				return getUsuarioPorId(id, currentUserId).get();

			} else {
				if (getUsuarioPorId(usuario.getId(), currentUserId).isPresent()) {

					sql = "UPDATE usuarios SET nome = ?, email = ?, login = ?, senha = ?, perfil= ?, sexo = ?, cep = ?, logradouro = ?, bairro = ?, cidade = ?, estado = ?, numero = ?, data_nascimento = ?, salario = ? WHERE id = ?";
					stmt = connection.prepareStatement(sql);
					setDadosStatement(stmt, usuario, currentUserId);

					stmt.execute();
					connection.commit();

					if (usuario.getImagem() != null && !usuario.getImagem().trim().isEmpty()) {
						updateImagemUsuario(usuario);
					}

					return getUsuarioPorId(usuario.getId(), currentUserId).get();
				} else {
					throw new IllegalArgumentException(
							"O usuário de id: #" + usuario.getId() + " não existe no banco de dados!");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possivel inserir o usuário" + e);
		}
	}

	public void updateImagemUsuario(Usuario usuario) {
		try {
			String sql = "UPDATE usuarios SET imagem = ?, extensaoImagem = ? WHERE id= ?";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getImagem());
			stmt.setString(2, usuario.getExtensaoImagem());
			stmt.setLong(3, usuario.getId());

			stmt.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean remover(Long id, Long currentUserId) {
		Optional<Usuario> optional = getUsuarioPorId(id, currentUserId);
		int excluidos = 0;

		try {
			if (optional.isPresent()) {
				String sql = "DELETE FROM usuarios WHERE id = ? AND admin IS FALSE";

				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setLong(1, id);
				excluidos = stmt.executeUpdate();

				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return excluidos > 0;
	}

	private void setDadosStatement(PreparedStatement stmt, Usuario usuario, Long currentUserId) {
		try {
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getLogin());
			stmt.setString(4, usuario.getSenha());
			stmt.setString(5, usuario.getPerfil());
			stmt.setString(6, usuario.getSexo());
			stmt.setString(7, usuario.getCep());
			stmt.setString(8, usuario.getLogradouro());
			stmt.setString(9, usuario.getBairro());
			stmt.setString(10, usuario.getCidade());
			stmt.setString(11, usuario.getEstado());
			stmt.setInt(12, usuario.getNumero());
			stmt.setDate(13, usuario.getDataNascimento());
			stmt.setDouble(14, usuario.getSalario());

			if (usuario.isNovo()) {
				stmt.setLong(15, currentUserId);
			} else {
				stmt.setLong(15, usuario.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
