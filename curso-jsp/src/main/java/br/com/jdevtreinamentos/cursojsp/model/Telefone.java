package br.com.jdevtreinamentos.cursojsp.model;

import java.io.Serializable;


public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numero;
	private Usuario usuario;

	public Telefone() {
	}

	public Telefone(String numero, Usuario usuario) {
		this.numero = numero;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isNovo() {
		return id == null || id.equals(0L);
	}

}
