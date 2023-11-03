package br.com.jdevtreinamentos.cursojsp.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jdevtreinamentos.cursojsp.dao.DAOUsuario;
import br.com.jdevtreinamentos.cursojsp.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet(urlPatterns = { "/ServletUsuario" })
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOUsuario daoUsuario = new DAOUsuario();

	public ServletUsuario() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Long currentUserId = ((Usuario)request.getSession().getAttribute("usuario")).getId();
		
		List<Usuario> listUsuarios = daoUsuario.getUsuarios(currentUserId);
		request.getSession().setAttribute("listUsuarios", listUsuarios);
		
		String acao = (String) request.getParameter("acao");
		if (acao != null && acao.equals("delete")) {
			String id = (String) request.getParameter("id");

			boolean isExcluido = daoUsuario.remover(Long.parseLong(id), currentUserId);

			if (isExcluido) {
				request.getSession().removeAttribute("modelo");
				response.getWriter().write("Usuário excluido com sucesso");
			} else {
				response.getWriter().write("Não foi possível excluir o usuário #" + id);
			}
		} else if (acao != null && acao.equals("pesquisar")) {
			String valorPesquisa = (String) request.getParameter("valorPesquisa");
			
			List<Usuario> usuarios = daoUsuario.pesquisarUsuarioPorNome(valorPesquisa);
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(usuarios); //Converte em json para retornar para a função AJAX
			
			response.getWriter().write(json);
		}else if(acao != null && acao.equals("editar")) {
			String idString = request.getParameter("id");
			Long id = Long.parseLong(idString);
			
			Optional<Usuario> optional = daoUsuario.getUsuarioPorId(id, currentUserId);
			
			if(optional.isPresent()) {
				request.getSession().setAttribute("modelo", optional.get());
				request.getSession().setAttribute("msg", "Usuário em edição");
				
			}else {
				request.getSession().setAttribute("msg", "Usuário não encontrado!");
			}
			
			response.sendRedirect("principal/usuario.jsp");
		}else {
			response.sendRedirect("principal/usuario.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idString = request.getParameter("id");
		Long id = idString != null && !idString.isEmpty() && !idString.equals("null") ? Long.parseLong(idString) : null;

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String perfil = request.getParameter("perfil");

		Usuario usuario = new Usuario(id, nome, email, login, senha, perfil);

		try {

			if (usuario.isNovo()) {
				request.getSession().setAttribute("msg", "Usuário inserido com sucesso!");
			} else {
				request.getSession().setAttribute("msg", "Usuário #" + usuario.getId() + " atualizado com sucesso!");
			}

			
			Long currentUserId = ((Usuario)request.getSession().getAttribute("usuario")).getId();
			usuario = daoUsuario.salvar(usuario, currentUserId);
			usuario.setSenha("");

		} catch (Exception e) {
			request.getSession().setAttribute("msg", "ERRO: " + e.getMessage());
		}

		request.getSession().setAttribute("modelo", usuario);

		doGet(request, response);
	}

}
