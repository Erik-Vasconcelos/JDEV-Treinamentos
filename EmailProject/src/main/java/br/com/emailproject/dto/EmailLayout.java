package br.com.emailproject.dto;

import br.com.emailproject.model.Email;

public class EmailLayout {
	
	private final String QUEBRA_DE_LINHA_SIMPLES = "<br>";
	private final String QUEBRA_DE_LINHA_DUPLA = "<br><br>";
	
	public Email construirEmail(String destinatario, String assunto) {
		
		StringBuilder corpo = new StringBuilder();
		corpo
			.append("A/C Gerente")
			.append(QUEBRA_DE_LINHA_DUPLA);
		
		corpo
			.append("Corpo do email")
			.append(QUEBRA_DE_LINHA_DUPLA);
		
		gerarAssinatura(corpo);
		gerarRodape(corpo);
		
		return new Email(destinatario, assunto, corpo.toString());
	}

	
	private void gerarAssinatura(StringBuilder builder) {
		builder
			.append("Att.: ")
			.append(QUEBRA_DE_LINHA_SIMPLES)
			.append("Gerente")
			.append(QUEBRA_DE_LINHA_DUPLA);
	}
	
	
	private void gerarRodape(StringBuilder builder) {
		builder.append("E-mail automático. Favor não responser esse e-mail");
	}
	
}
