package br.com.jdevtreinamentos.cursojsp.servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import org.apache.tomcat.jakartaee.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jdevtreinamentos.cursojsp.dao.DAOUsuario;
import br.com.jdevtreinamentos.cursojsp.model.Usuario;
import br.com.jdevtreinamentos.cursojsp.util.JasperUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class ServletUsuario
 */
@MultipartConfig
@WebServlet(urlPatterns = { "/ServletUsuario" })
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOUsuario daoUsuario = new DAOUsuario();

	public ServletUsuario() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long currentUserId = ((Usuario) request.getSession().getAttribute("usuario")).getId();

		List<Usuario> listUsuarios = daoUsuario.getUsuarios(currentUserId, 0);
		request.getSession().setAttribute("listUsuarios", listUsuarios);
		request.getSession().setAttribute("totalPaginas", daoUsuario.getTotalPaginas(currentUserId));
		

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

			int offset = 0;
			
			if(request.getParameter("offset") != null) {
				offset = Integer.parseInt(request.getParameter("offset")); 
			}
			
			List<Usuario> usuarios = daoUsuario.pesquisarUsuarioPorNome(valorPesquisa, offset);

			int totalPaginas = daoUsuario.getTotalPaginasPesquisa(currentUserId, valorPesquisa); 
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(usuarios); // Converte em json para retornar para a função AJAX

			response.getWriter().write(json);
			response.setIntHeader("totalPaginas", totalPaginas);
			response.setIntHeader("totalRegistros", daoUsuario.getTotalRegistrosPesquisa(currentUserId, valorPesquisa));
			
		} else if (acao != null && acao.equals("editar")) {
			String idString = request.getParameter("id");
			Long id = Long.parseLong(idString);

			Optional<Usuario> optional = daoUsuario.getUsuarioPorId(id, currentUserId);

			if (optional.isPresent()) {
				request.getSession().setAttribute("modelo", optional.get());
				request.getSession().setAttribute("msg", "Usuário em edição");

			} else {
				request.getSession().setAttribute("msg", "Usuário não encontrado!");
			}

			response.sendRedirect("principal/usuario.jsp");
		} else if (acao != null && acao.equals("pagination")) {
			int offset = Integer.parseInt(request.getParameter("offset"));
			
			listUsuarios = daoUsuario.getUsuarios(currentUserId, offset);
			request.getSession().setAttribute("listUsuarios", listUsuarios);
			request.getSession().setAttribute("totalPaginas", daoUsuario.getTotalPaginas(currentUserId));
			
			response.sendRedirect("principal/usuario.jsp");
		} else if (acao != null && acao.equals("downloadImagem")) {
			Long id = Long.parseLong(request.getParameter("id"));
			
			Optional<Usuario> optional = daoUsuario.getUsuarioPorId(id, currentUserId);
			
			if(optional.isPresent()) {
				Usuario usuario = optional.get();
				
				response.setHeader("Content-Disposition", "attachment;filename=imagem." + usuario.getExtensaoImagem());
				String imagemBase64 = usuario.getImagem().split("\\,")[1]; 
				
				response.getOutputStream().write(new Base64().decode(imagemBase64));
				
			}
			
		} else if (acao != null && acao.equals("relatorio")) {
			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");
			
			if(dataInicial == null || dataInicial.equals("") || dataFinal == null || dataFinal.equals("")) {
				request.getSession().setAttribute("relatorio", daoUsuario.getRelatorio(currentUserId));
			}else {
				try {
					Date dateInicialDt = Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial)));
					Date dateFinalDt = Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal)));
					
					request.getSession().setAttribute("relatorio", daoUsuario.getRelatorioFiltroData(dateInicialDt, dateFinalDt, currentUserId));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			request.getSession().setAttribute("dataInicial", dataInicial);
			request.getSession().setAttribute("dataFinal", dataFinal);
			
			response.sendRedirect("principal/relatorio-usuario.jsp");
			
		} else if (acao != null && acao.equals("downloadRelatorio")) {
			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");
			
			
			byte[] relatorio = null;
			
			if(dataInicial == null || dataInicial.equals("") || dataFinal == null || dataFinal.equals("")) {
				relatorio = new JasperUtil().gerarRelatorioPdf(daoUsuario.getRelatorio(currentUserId), "relatorio-usuario", request.getServletContext());
			}else {
				try {
					Date dateInicialDt = Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial)));
					Date dateFinalDt = Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal)));
					
					relatorio = new JasperUtil().gerarRelatorioPdf(daoUsuario.getRelatorioFiltroData(dateInicialDt, dateFinalDt, currentUserId), "relatorio-usuario", request.getServletContext());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			response.setHeader("Content-Disposition", "attachment;filename=relatorio-usuario.pdf");
			response.getOutputStream().write(relatorio);
		}
		
		else {
			response.sendRedirect("principal/usuario.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String idString = request.getParameter("id");
			Long id = idString != null && !idString.isEmpty() && !idString.equals("null") ? Long.parseLong(idString) : null;

			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			int numero = Integer.parseInt(request.getParameter("numero"));
			
			Date dataNascimento = Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(request.getParameter("dataNascimento"))));
			String salarioString = request.getParameter("salario");
			
			String valor = salarioString.replaceAll("\\.", "").replaceAll("\\,", ".");
			
			Double salario = Double.parseDouble(salarioString.replaceAll("\\.", "").replaceAll("\\,", ".") );

			Usuario usuario = new Usuario(id, nome, email, login, senha, perfil, sexo);
			usuario.setSalario(salario);
			usuario.setCep(cep);
			usuario.setLogradouro(logradouro);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			usuario.setNumero(numero);
			usuario.setDataNascimento(dataNascimento);
			
			if (JakartaServletFileUpload.isMultipartContent(request)) {
				Part part = request.getPart("imagemFile");
				if (part.getSize() > 0) {
					byte[] imagemByte = IOUtils.toByteArray(part.getInputStream());
					String imagemBase64 = new Base64().encodeAsString(imagemByte);

					String extensao = part.getContentType().split("/")[1];
					imagemBase64 = "data:image/" + extensao + ";base64," + imagemBase64;

					usuario.setImagem(imagemBase64);
					usuario.setExtensaoImagem(extensao);
				}
			}

			try {
				if (usuario.isNovo()) {
					request.getSession().setAttribute("msg", "Usuário inserido com sucesso!");
				} else {
					request.getSession().setAttribute("msg", "Usuário #" + usuario.getId() + " atualizado com sucesso!");
				}

				Long currentUserId = ((Usuario) request.getSession().getAttribute("usuario")).getId();
				usuario = daoUsuario.salvar(usuario, currentUserId);
				usuario.setSenha("");

			} catch (Exception e) {
				request.getSession().setAttribute("msg", "ERRO: " + e.getMessage());
			}

			request.getSession().setAttribute("modelo", usuario);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
