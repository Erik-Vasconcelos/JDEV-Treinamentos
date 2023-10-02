package com.javaweb.email;

import java.util.Properties;

import jakarta.mail.Address;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private String email = "javaenterpriseoracle@gmail.com";
	private String senha = "jcdsfvhptdmvxywr";
	 
	
	@org.junit.Test
	public void testEmail() {
		try {
			Properties propriedadeConexao = new Properties();
			propriedadeConexao.put("mail.smtp.auth", "true"); //Autorização
			propriedadeConexao.put("mail.smtp.starttls", "true"); //Autenticação
			propriedadeConexao.put("mail.smtp.host", "smtp.gmail.com"); // Servidor de email
			propriedadeConexao.put("mail.smtp.port", "465"); //Porta do servidor
			propriedadeConexao.put("mail.smtp.socketFactory.port", "465"); //Porta a ser conectada pelo socket
			propriedadeConexao.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //Classe socket de conexão ao SMTP
			
			Session session = Session.getInstance(propriedadeConexao, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email, senha);
				}
			
			});
			
			Address[] toUsers = InternetAddress.parse("erik.v@escolar.ifrn.edu.br, erikvasconcelos2018@gmail.com, javaenterpriseoracle@gmail.com");
			
			Message mensagem = new MimeMessage(session);
			mensagem.setFrom(new InternetAddress(email, "Java EE - Oracle"));
			mensagem.setRecipients(Message.RecipientType.TO, toUsers);
			mensagem.setSubject("Envio de email com Java");
//			mensagem.setText("Olá, esse é o meu primeiro envio de email usando programação!!!!\nErik Vasconcelos 01/09/2023");
			mensagem.setText("Olá, esse é o meu terceiro envio de email usando programação!!!!\nErik Vasconcelos 01/09/2023");
			
			Transport.send(mensagem);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
