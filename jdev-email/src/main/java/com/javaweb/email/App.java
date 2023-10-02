package com.javaweb.email;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Set<String> destinatarios = new LinkedHashSet<String>(Arrays.asList("erikvasconcelos2018@gmail.com",
				"erik.v@escolar.ifrn.edu.br", "javaenterpriseoracle@gmail.com"));

		StringBuilder corpo = new StringBuilder();
		corpo.append("<h2>Olá, esse é o meu primeiro e-mail formatado com html e enviado com programação</h2><br>");
		corpo.append("<p><b>Este é o corpo da mensagem formatada com html</b></p><br><br>");
		corpo.append(
				"<a target='_blank' href='https://projetojavaweb.com/certificado-aluno/login' style='padding: 10px 15px; border-radius: 20px; text-aling: center; text-decoration: none; background-color: #7C23F0; color: #FFF;' >Acessar área de aprendizado</a><br><br>");
		corpo.append("<i>Email criado em: 01/09/2023<i>");

		Email email = new Email("Java Enterprise Edition",  "Envio de Email com Java", corpo.toString(), destinatarios);

		SendEmail entregador = new SendEmail();
		entregador.enviarComAnexo(email, TipoEmail.FORMATADO_HTML);
		
	}
}
