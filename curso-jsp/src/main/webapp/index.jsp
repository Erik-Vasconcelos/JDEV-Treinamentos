<%@page import="java.security.SecureRandom"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP</title>
</head>
<body>
	<h1>Minha primeira página JSP</h1>

	<%
		int cont = 0;
	
		int red = 255;
		int green = 255;
		int blue = 255;
		String background = "";
		
		SecureRandom gerador = new SecureRandom();
		
		out.println("Usando código Java na página JSP:");
		out.println("<br><br>");
		while(cont < 10){
			red = gerador.nextInt(0, 255);
			green = gerador.nextInt(0, 255);
			blue = gerador.nextInt(0, 255);
			
			background = String.format("background-color: rgb(%d, %d, %d);", red, green, blue);
			
			out.println(String.format("<div style='%s'>Cor: %s</div>", background, background));
			
			cont ++;
		}
		
		out.println("<br><br>");
	%>
	
	<form action="receber-nome.jsp" method="post">
		<input type="text" name="nome">
		<input type="text" name="idade">
		<input type="submit" value="Enviar">
	</form>

</body>
</html>