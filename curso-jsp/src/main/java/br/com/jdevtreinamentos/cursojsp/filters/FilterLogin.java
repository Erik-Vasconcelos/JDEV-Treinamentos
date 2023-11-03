package br.com.jdevtreinamentos.cursojsp.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.jdevtreinamentos.cursojsp.connection.FabricaConexao;
import br.com.jdevtreinamentos.cursojsp.model.Usuario;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" })
public class FilterLogin extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private Connection conexao = null;

	public FilterLogin() {
	}

	/* Finaliza os processos quando o servidor para */
	public void destroy() {
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Intercepta todas as requisiçõe e respotas do projeto Autenticação
	 * redirecionamento 'A porta de entrada do sistema' Tudo passa por aqui
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest requestHttp = (HttpServletRequest) request;
		HttpSession session = requestHttp.getSession();
		HttpServletResponse resposta = (HttpServletResponse) response;
		try {

			Usuario usuario = (Usuario)session.getAttribute("usuario");
			String loginUsuario = usuario != null ? usuario.getLogin() : null;
			String urlAutenticar = requestHttp.getServletPath();

			if ((loginUsuario == null || loginUsuario.isBlank()) && !urlAutenticar.equals("/index.jsp")) {
				
				requestHttp.getSession().setAttribute("url", requestHttp.getServletPath());
				requestHttp.getSession().setAttribute("msg", "Realize o login!");
				resposta.sendRedirect(requestHttp.getContextPath() + "/index.jsp");

				// return 'opcional'. passa pelo resto do código abaixo, como não tem nada que
				// interfere, ficou sem.
			} else {
				chain.doFilter(request, response);
			}
			conexao.commit(); // Realiza de fato as alterações feitas
		} catch (Exception e) {
			e.printStackTrace();

			requestHttp.getSession().setAttribute("msg", e.getMessage());
			resposta.sendRedirect(requestHttp.getContextPath() + "/erro.jsp");
			try {
				conexao.rollback(); // Desfaz as alterações feitas no banco
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	/*
	 * Inicia os processos do quando o servidor sobe o projeto ex.: Conexão com o
	 * banco
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		conexao = FabricaConexao.getConnection();
	}

}
