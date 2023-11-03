package br.com.jdevtreinamentos.cursojsp.model;

/**
 * @author Erik Vasconcelos
 * @since 13/10/2023
 */

public class Usuario {

	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private String perfil;
	private boolean admin;

	public Usuario() {
	}
	
	public Usuario(String nome, String email, String login, String senha) {
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(Long id, String nome, String email, String login, String senha, String perfil) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	public Usuario(Long id, String nome, String email, String login, String senha, String perfil, boolean admin) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public boolean isNovo() {
		return this.id == null || this.id.equals(0L);
	}

}
