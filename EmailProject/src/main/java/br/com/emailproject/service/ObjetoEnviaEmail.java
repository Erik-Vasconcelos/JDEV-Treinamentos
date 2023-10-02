package br.com.emailproject.service;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.emailproject.model.Email;
import br.com.emailproject.util.LogUtil;

/**
 * Classe de objeto de envio de email. Implementa o padrão de projeto Singleton
 * 
 * @author Erik Vasconcelos
 * @since 02/10/2023
 * 
 */
public class ObjetoEnviaEmail extends Thread {

	private static ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail();
	private static ConcurrentLinkedQueue<Email> fila = new ConcurrentLinkedQueue<>();

	private ObjetoEnviaEmail() {
	}

	public static ObjetoEnviaEmail getInstacia() {
		return enviaEmail;
	}

	public static void startThread() {
		if (!enviaEmail.isAlive()) {
			enviaEmail.start();
		}
	}
	
	public static void stopThread() {
		if(enviaEmail.isAlive()) {
			enviaEmail.interrupt();
		}
	}

	public static void add(Email email) {
		fila.add(email);
	}

	@Override
	public void run() {
		while (true) {
			synchronized (fila) {

				Iterator<Email> iterator = fila.iterator();
				while (iterator.hasNext()) {

					Email enviar = iterator.next();
					sendEmail(enviar);
					LogUtil.getLog(Transport.class).info("Email enviado!");;

					iterator.remove();

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						LogUtil.getLog(e.getClass()).error("Erro ao processar tempo de liberação");
					}

				}

			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LogUtil.getLog(e.getClass()).error("Erro ao processar tempo de liberação");
			}
		}

	}

	private void sendEmail(Email email) {

		try {
			Properties prop = new Properties();
			prop.put("mail.smtp.starttls", "true");
			prop.put("mail.smtp.host", System.getProperty("email-project.mail.smtp.host"));
			prop.put("mail.smtp.port", System.getProperty("email-project.mail.smtp.port"));

			Session session = Session.getInstance(prop);

			Message message = new MimeMessage(session);
			message.setSubject(email.getAssunto());
			message.setFrom(new InternetAddress(System.getProperty("email-project.mail.from"), "Java Enterprise"));

			if (email.getDestinatario().contains(";")) {
				Address[] destinatarios = InternetAddress.parse(email.getDestinatario().replaceAll(";", ","));
				message.setRecipients(Message.RecipientType.TO, destinatarios);
			} else {
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getDestinatario()));
			}

			message.setContent(email.getCorpo(), "text/html; charset=utf-8");
			Transport.send(message);

		} catch (MessagingException e) {
			LogUtil.getLog(e.getClass()).error("Erro ao enviar email: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			LogUtil.getLog(e.getClass()).error("Erro ao enviar email: " + e.getMessage());
		}

	}

}
