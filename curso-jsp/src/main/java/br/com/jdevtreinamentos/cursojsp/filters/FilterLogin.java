package br.com.jdevtreinamentos.cursojsp.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*" })
public class FilterLogin extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public FilterLogin() {
	}

	/* Finaliza os processos quando o servidor para */
	public void destroy() {
	}

	/*
	 * Intercepta todas as requisiçõe e respotas do projeto Autenticação
	 * redirecionamento 'A porta de entrada do sistema' Tudo passa por aqui
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requestHttp = (HttpServletRequest) request;
		HttpSession session = requestHttp.getSession();

		String usuario = (String) session.getAttribute("usuario"); 
		String urlAutenticar = requestHttp.getServletPath();

		if ((usuario == null || usuario.isBlank()) && !urlAutenticar.equals("/principal/ServletLogin")) {

			RequestDispatcher redirecionador = requestHttp.getRequestDispatcher("/index.jsp?url=" + urlAutenticar);
			request.setAttribute("msg", "Realize o login!");
			redirecionador.forward(request, response);
			
			return;
		} else {
			chain.doFilter(request, response);
		}

	}

	/*
	 * Inicia os processos do quando o servidor sobe o projeto ex.: Conexão com o
	 * banco
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
