package br.com.jdevtreinamentos.cursojsp.servlets;

import java.io.IOException;

import br.com.jdevtreinamentos.cursojsp.dao.DAOUsuario;
import br.com.jdevtreinamentos.cursojsp.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/login" })
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DAOUsuario daoLogin;

	public ServletLogin() {
		daoLogin = new DAOUsuario();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equals("logout")) {
			request.getSession().invalidate(); //Invalida/cancela a sessão do usuário
		}
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String url = request.getParameter("url");

			if (login != null && !login.isBlank() && senha != null && !senha.isBlank()) {
				Usuario modelLogin = new Usuario();
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);

				boolean loginIsValid = daoLogin.autenticar(modelLogin);

				if (loginIsValid) {
					request.getSession().setAttribute("usuario", modelLogin.getLogin());

					if (url == null || url.equals("null") || url.isBlank()) {
						url = "/principal/inicio.jsp";
					}

					response.sendRedirect(request.getContextPath() + url);
					request.getSession().removeAttribute("msg");
				} else {
					request.getSession().setAttribute("msg", "Informe o login e senha corretamente!");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			} else {

				request.getSession().setAttribute("msg", "Informe o login e senha corretamente!");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/erro.jsp");
		}

	}

}
