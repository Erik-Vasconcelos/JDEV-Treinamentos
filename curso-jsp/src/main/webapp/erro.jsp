<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Erro</title>
</head>
<body>
	<h1>Ocorreu algum erro inesperado, entre em contato com a equipe de suporte!</h1>
	<span><%= request.getSession().getAttribute("msg") == "null" ? "" : request.getSession().getAttribute("msg")%></span>
</body>
</html>