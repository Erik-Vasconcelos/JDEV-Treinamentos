package br.com.jdevtreinamentos.cursojsp.model;

/**
 * @author Erik Vasconcelos
 * @since 13/10/2023
 */

public class Login {

	private String login;
	private String senha;

	public Login(String login, String senha) {
		this.login = login;
		this.senha = senha;
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

}
