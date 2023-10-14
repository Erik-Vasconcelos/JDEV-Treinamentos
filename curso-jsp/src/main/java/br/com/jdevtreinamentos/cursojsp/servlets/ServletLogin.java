package br.com.jdevtreinamentos.cursojsp.servlets;

import java.io.IOException;

import br.com.jdevtreinamentos.cursojsp.model.Login;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/ServletLogin", "/principal/ServletLogin"})
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ServletLogin() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse  response)
			throws ServletException, IOException {
		
		ServletRequest red = request;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		if(login != null && !login.isBlank() && senha != null && !senha.isBlank()) {
			Login modelLogin = new Login(login, senha);
			
			if(modelLogin.getLogin().equals("admin") && modelLogin.getSenha().equals("admin")) {
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				if(url == null || url.equals("null") || url.isBlank()) {
					url = "/principal/inicio.jsp";
				}
				
				
//				response.sendRedirect(request.getContextPath() + url);
				RequestDispatcher redirecionador = request.getRequestDispatcher(url);
				redirecionador.forward(request, response);
			}else {
				RequestDispatcher redirecionador = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente!");
				redirecionador.forward(request, response);
			}
		}else {
			RequestDispatcher redirecionador = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente!");
			redirecionador.forward(request, response);
		}
		
	}

}
