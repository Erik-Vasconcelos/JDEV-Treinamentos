package br.com.jdevtreinamentos.cursojsp.servlets;

import java.io.IOException;
import java.util.List;

import br.com.jdevtreinamentos.cursojsp.dao.DAOTelefone;
import br.com.jdevtreinamentos.cursojsp.dao.DAOUsuario;
import br.com.jdevtreinamentos.cursojsp.model.Telefone;
import br.com.jdevtreinamentos.cursojsp.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/ServletTelefone" })
public class ServletTelefone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOTelefone daoTelefone;
	private DAOUsuario daoUsuario;

	public ServletTelefone() {
		daoTelefone = new DAOTelefone();
		daoUsuario = new DAOUsuario();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("excluir")) {
			Long idTelefone = Long.parseLong(request.getParameter("id-telefone"));

			Telefone telefone = new Telefone();
			telefone.setId(idTelefone);

			daoTelefone.excluir(telefone);
			request.getSession().setAttribute("msg", "Telefone excluído com sucesso!");
		}
		
		String idString = request.getParameter("id-usuario");
		Long id = idString != null && !idString.isEmpty() && !idString.equals("null") ? Long.parseLong(idString) : null;

		Usuario usuario = new Usuario();
		usuario.setId(id);

		List<Telefone> telefones = daoTelefone.getTelefones(usuario);
		request.getSession().setAttribute("telefones", telefones);

		response.sendRedirect(request.getContextPath() + "/principal/telefone.jsp?id-usuario=" + id);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long currentUserId = ((Usuario) request.getSession().getAttribute("usuario")).getId();
		
		Long id = Long.parseLong(request.getParameter("id"));
		String numero = request.getParameter("numero");
		
		Usuario usuario = daoUsuario.getUsuarioPorId(id, currentUserId).get();
		Telefone telefone = new Telefone(numero, usuario);
		
		if(!daoTelefone.telefoneUsuarioExists(telefone)) {
			daoTelefone.salvar(telefone);
			request.getSession().setAttribute("msg", "Telefone inserido com sucesso!");

		}else {
			request.getSession().setAttribute("msg", "O telefone já existe!");
		}

		response.sendRedirect(request.getContextPath() + "/ServletTelefone?id-usuario=" + id);
	}

}
