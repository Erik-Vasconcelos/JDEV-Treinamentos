package br.com.jdevtreinamentos.cursojsp.servlets;

import java.io.IOException;

import br.com.jdevtreinamentos.cursojsp.dao.DAOUsuario;
import br.com.jdevtreinamentos.cursojsp.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletUsuario
 */
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOUsuario daoUsuario = new DAOUsuario();

	public ServletUsuario() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = (String) request.getParameter("acao");
		if (acao != null && acao.equals("delete")) {
			String id = (String) request.getParameter("id");

			boolean isExcluido = daoUsuario.remover(Long.parseLong(id));

			if (isExcluido) {
				request.getSession().setAttribute("msg", "Usuário excluido com sucesso");
				request.getSession().removeAttribute("modelo");
			} else {
				request.getSession().setAttribute("msg", "Não foi possível excluir o usuário #" + id);
			}
		}

		response.sendRedirect("principal/usuario.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idString = request.getParameter("id");
		Long id = idString != null && !idString.isEmpty() && !idString.equals("null") ? Long.parseLong(idString) : null;

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		Usuario usuario = new Usuario(id, nome, email, login, senha);

		try {

			if (usuario.isNovo()) {
				request.getSession().setAttribute("msg", "Usuário inserido com sucesso!");
			} else {
				request.getSession().setAttribute("msg", "Usuário #" + usuario.getId() + " atualizado com sucesso!");
			}
			
			usuario = daoUsuario.salvar(usuario);
			usuario.setSenha("");

		} catch (Exception e) {
			request.getSession().setAttribute("msg", "ERRO: " + e.getMessage());
		}

		request.getSession().setAttribute("modelo", usuario);

		doGet(request, response);
	}

}
