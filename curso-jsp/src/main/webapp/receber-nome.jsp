<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teste de parametros</title>
</head>
<body>

	<%
		String nome = request.getParameter("nome");
		out.print("Nome: " + nome);
		out.print("<br><br>");
		
		String idade = request.getParameter("idade");
		out.print("Idade: " + idade);
	%>

</body>
</html>