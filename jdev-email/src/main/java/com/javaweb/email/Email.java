package com.javaweb.email;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Objeto modelo para um email
 *  
 * @author Erik Vasconcelos
 * @since 01/09/2023
 */
public class Email {
	
	private String nomeRemetente;
	private String assunto;
	private String corpo;

	private Set<String> destinatarios = new LinkedHashSet<String>();

	public Email(String nomeRemetente, String assuto, String corpo, Set<String> destinatarios) {
		this.nomeRemetente = nomeRemetente;
		this.assunto = assuto;
		this.corpo = corpo;
		this.destinatarios = destinatarios;
	}

	public String getNomeRemetente() {
		return nomeRemetente;
	}

	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}

	public String getAssuto() {
		return assunto;
	}

	public void setAssuto(String assuto) {
		this.assunto = assuto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public Set<String> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(Set<String> destinatarios) {
		this.destinatarios = destinatarios;
	}
	
	public String getEmailsDestinatariosString() {
		String listaEmails = String.join(", ", this.destinatarios);
		return listaEmails;
	}

}
