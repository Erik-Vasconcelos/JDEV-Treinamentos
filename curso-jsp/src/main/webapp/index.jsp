<%@page import="java.security.SecureRandom"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<title>JSP</title>
</head>
<style>
body{
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	background: linear-gradient(to right, rgb(35, 37, 38), rgb(65, 67, 69));;
}

.container-form{
	width: 50%;
	padding: 2em;
	box-sizing: content-box;
	color: #fff;
}
</style>

<body>
	<div class="container-form">
		<h1>Bem vindo ao sistema!</h1>
	
		<form action="login" method="post" class="g-3 needs-validation mt-3 mb-4 form"
			novalidate>
			<input type="hidden" value="<%=request.getParameter("url")%>"
				name="url">
			<div class="mb-3">
				<label for="inputLogin" class="form-label">Login</label> <input
					type="text" class="form-control" id="inputLogin" name="login"
					required>
				<div class="invalid-feedback">Por favor, informe o nome!</div>
			</div>
			<div class="mb-3">
				<label for="inputSenha" class="form-label">Senha</label> <input
					type="password" class="form-control" id="inputSenha" name="senha"
					required>
				<div class="invalid-feedback">Por favor, informe a senha!</div>
			</div>
			<button type="submit" class="btn btn-primary">Acessar</button>
		</form>
		
		<%
			String msg = (String) request.getSession().getAttribute("msg");
		
			if(msg != null && !msg.equals("null")){
				out.println("<div class='alert alert-warning' role='alert'>" + msg + "</div>");
			}
		%>
	</div>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
	(() => {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  const forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.from(forms).forEach(form => {
		    form.addEventListener('submit', event => {
		      if (!form.checkValidity()) {
		        event.preventDefault()
		        event.stopPropagation()
		      }

		      form.classList.add('was-validated')
		    }, false)
		  })
		})()
	</script>
</body>
</html>