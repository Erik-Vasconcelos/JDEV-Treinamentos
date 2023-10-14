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

	<form action="ServletLogin" method="post">

		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">

		<table>
			<tr>
				<td>Login:</td>
				<td><input type="text" name="login"></td>
			</tr>

			<tr>
				<td>Senha:</td>
				<td><input type="password" name="senha"></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Enviar"></td>
			</tr>
		</table>
	</form>

	<h4>${msg}</h4>

</body>
</html>