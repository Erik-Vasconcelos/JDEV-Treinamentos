package com.javaweb.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.activation.DataHandler;
import jakarta.mail.Address;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;

/**
 * Objeto para o envio de email
 * 
 * @author Erik Vasconcelos
 * @since 01/09/2023
 */

public class SendEmail {

	private static final String EMAIL_REMETENTE = "javaenterpriseoracle@gmail.com";
	private static final String SENHA_REMETENTE = "jcds fvhp tdmv xywr";

	public void enviar(Email email, TipoEmail tipoEmail) {

		try {
			Session sessao = getSession();

			Address[] enderecosDestinatarios = InternetAddress.parse(email.getEmailsDestinatariosString());

			Message mensagem = new MimeMessage(sessao);
			mensagem.setFrom(new InternetAddress(EMAIL_REMETENTE, email.getNomeRemetente()));
			mensagem.setRecipients(Message.RecipientType.TO, enderecosDestinatarios);
			mensagem.setSubject(email.getAssuto());

			if (tipoEmail.equals(TipoEmail.FORMATADO_HTML)) {
				mensagem.setContent(email.getCorpo(), "text/html; charset=utf-8");
			} else {
				mensagem.setText(email.getCorpo());
			}

			Transport.send(mensagem);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public void enviarComAnexo(Email email, TipoEmail tipoEmail) {
		try {
			Session sessao = getSession();
			
			Address[] enderecos = InternetAddress.parse(email.getEmailsDestinatariosString());

			Message message = new MimeMessage(sessao);
			message.setFrom(new InternetAddress(EMAIL_REMETENTE, email.getNomeRemetente()));
			message.addRecipients(RecipientType.TO, enderecos);
			message.setSubject(email.getAssuto());

			/* Parte 1 do email: corpo */
			MimeBodyPart corpo = new MimeBodyPart();

			if (tipoEmail.equals(TipoEmail.FORMATADO_HTML)) {
				corpo.setContent(email.getCorpo(), "text/html; charset=utf-8");
			} else {
				corpo.setText(email.getCorpo());
			}

			List<FileInputStream> anexos = new ArrayList<FileInputStream>();
			anexos.add(geradorPdf());
			anexos.add(geradorPdf());
			anexos.add(geradorPdf());
			anexos.add(geradorPdf());

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpo);

			/* Parte 2 do email: anexos */
			int index = 1;
			for (FileInputStream file : anexos) {
				MimeBodyPart anexo = new MimeBodyPart();

				anexo.setDataHandler(new DataHandler(new ByteArrayDataSource(file, "application/pdf")));
				String nomeAnexo = String.format("anexo%d.pdf", index);
				anexo.setFileName(nomeAnexo);
				multipart.addBodyPart(anexo);

				index++;
			}

			/* Conteúdo da mensagem */
			message.setContent(multipart);

			Transport.send(message);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Session getSession() {
		Properties propConexao = new Properties();
		propConexao.put("mail.smtp.auth", "true");
		propConexao.put("mail.smtp.starttls", "true");
		propConexao.put("mail.smtp.ssl.trust", "*");
		propConexao.put("mail.smtp.host", "smtp.gmail.com");
		propConexao.put("mail.smtp.port", "465");
		propConexao.put("mail.smtp.socketFactory.port", "465");
		propConexao.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session sessao = Session.getInstance(propConexao, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_REMETENTE, SENHA_REMETENTE);
			}
		});
		
		return sessao;
	}

	private FileInputStream geradorPdf() {
		Document manipulador = new Document();
		File arquivo = new File("anexo.pdf");

		try {
			PdfWriter escritor = PdfWriter.getInstance(manipulador, new FileOutputStream(arquivo));

			manipulador.open();
			manipulador.add(new Paragraph("Texto do documento em anexo do email enviado com Java"));
			manipulador.close();

			return new FileInputStream(arquivo);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;

	}
}
