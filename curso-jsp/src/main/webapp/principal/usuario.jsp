<%@page import="br.com.jdevtreinamentos.cursojsp.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragmentos/head.jsp"></jsp:include>

<body>

	<jsp:include page="fragmentos/preloader.jsp"></jsp:include>

	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="fragmentos/header.jsp"></jsp:include>


			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="fragmentos/menu-lateral.jsp"></jsp:include>

					<div class="pcoded-content">

						<jsp:include page="fragmentos/header-content.jsp"></jsp:include>

						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">

												<div class="card">
													<div class="card-header">
														<h5>Cadastro de usuário</h5>
													</div>
													<div class="card-block">
														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuario"
															method="post" id="formUsuario">
															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="number" name="id" class="form-control"
																	readonly="readonly" value="${sessionScope.modelo.id}"
																	id="id"><span class="form-bar"></span><label
																	class="float-label">Id</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" class="form-control"
																	required="required" value="${sessionScope.modelo.nome}">
																<span class="form-bar"></span> <label
																	class="float-label">Nome</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" class="form-control"
																	required="required" autocomplete="off"
																	value="${sessionScope.modelo.email}"> <span
																	class="form-bar"></span> <label class="float-label">Email
																	(exa@gmail.com)</label>
															</div>

															<div class="form-group form-default form-static-label">
																<select class="form-control" name="perfil">
																	<option hidden>--Selecione--</option>
																	
																	<option value="ADMIN" <% 
																		Usuario usuario = (Usuario)request.getSession().getAttribute("modelo");
																		if(usuario != null && usuario.getPerfil().equals("ADMIN")){ out.print("selected=\"selected\"");}%>>Admin</option>
																	<option value="SECRETARIA" <% 
																		if(usuario != null && usuario.getPerfil().equals("SECRETARIA")){ out.print("selected=\"selected\"");}%>>Secretária</option>
																	<option value="AUXILIAR" <% 
																		if(usuario != null && usuario.getPerfil().equals("AUXILIAR")){ out.print("selected=\"selected\"");}%> >Auxiliar</option>
																</select>
																<span class="form-bar"></span> <label
																	class="float-label">Perfil</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="login" class="form-control"
																	required="required"
																	value="${sessionScope.modelo.login}"> <span
																	class="form-bar"></span> <label class="float-label">Login</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" class="form-control"
																	required="required" autocomplete="off"><span
																	class="form-bar"></span> <label class="float-label">Password</label>
															</div>

															<button
																class="btn btn-primary btn-round waves-effect waves-light"
																type="button" onclick="limparCampos()">
																<font style="vertical-align: inherit;"><font
																	style="vertical-align: inherit;">Novo</font></font>
															</button>
															<button
																class="btn btn-success btn-round waves-effect waves-light"
																type="submit">
																<font style="vertical-align: inherit;"><font
																	style="vertical-align: inherit;">Salvar</font></font>
															</button>
															<button
																class="btn btn-danger btn-round waves-effect waves-light"
																type="button" onclick="excluir()">
																<font style="vertical-align: inherit;"><font
																	style="vertical-align: inherit;">Excluir</font></font>
															</button>
															<button
																class="btn btn-inverse btn-round waves-effect waves-light"
																type="button" data-toggle="modal"
																data-target="#modalPesquisa">
																<font style="vertical-align: inherit;"><font
																	style="vertical-align: inherit;">Pesquisar</font></font>
															</button>
														</form>
													</div>
												</div>

												<span id="msg">${sessionScope.msg}</span>

												<table class="table" id="tabelaListUsuarios">
													<thead>
														<tr>
															<th scope="col">Id</th>
															<th scope="col">Nome</th>
															<th scope="col">Ação</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${sessionScope.listUsuarios}" var="u">
															<tr>
																<td><c:out value="${u.id}"></c:out></td>
																<td><c:out value="${u.nome}"></c:out></td>
																<td><button type="button"
																		class="btn btn-info btn-round"
																		onclick="editar(${u.id})">Acessar</button></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>

										</div>
									</div>

								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="fragmentos/scripts.jsp"></jsp:include>

	<div class="modal fade" tabindex="-1" role="dialog" id="modalPesquisa">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Pesquisar</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<div class="input-group mb-3">
						<input class="form-control mr-sm-2" type="search"
							placeholder="Search" aria-label="Search" id="valorBusca">
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
							onclick="pesquisar()">Pesquisar</button>
					</div>
					<div style="height: 20em; overflow: scroll;">
						<table class="table" id="tabelaUsuarios">
							<thead>
								<tr>
									<th scope="col">Id</th>
									<th scope="col">Nome</th>
									<th scope="col">Ação</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<span id="totalRegistros"></span>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		function limparCampos() {
			var elementos = document.getElementById("formUsuario").elements;

			for (i = 0; i < elementos.length; i++) {
				elementos[i].value = '';
			}
		}

		function pesquisar() {
			var valor = document.getElementById("valorBusca").value;
			var formulario = document.getElementById("formUsuario");

			if (valor != null && valor.trim() != '') {
				$
						.ajax(
								{
									method : "get",
									url : formulario.action,
									data : "valorPesquisa=" + valor
											+ "&acao=pesquisar",
									success : function(response) {
										var json = JSON.parse(response);

										$("#tabelaUsuarios > tbody > tr")
												.remove();

										for (i = 0; i < json.length; i++) {
											$('#tabelaUsuarios > tbody')
													.append(
															'<tr> <td>'
																	+ json[i].id
																	+ '</td> <td>'
																	+ json[i].nome
																	+ '</td><td><button type="button" class="btn btn-info btn-round" onclick="editar('
																	+ json[i].id
																	+ ')">Acessar</button></td></tr>')
										}

										document
												.getElementById("totalRegistros").textContent = "Total de registros: "
												+ json.length;
									}
								}).fail(function(xhr, status, errorThrown) {
							alert("Erro ao pesquisar: " + xhr.reponseText);
						});
			}
		}

		function editar(id) {
			var formulario = document.getElementById("formUsuario");
			window.location.href = formulario.action + "?acao=editar&id=" + id;
		}

		function excluir() {
			var id = document.getElementById("id").value;
			var formulario = document.getElementById("formUsuario");

			if (id != "") {
				if (confirm("Deseja realmente excluir o usuário? ")) {
					var action = formulario.action;
					var msgContent = document.getElementById("msg");

					$.ajax({
						method : "get",
						url : action,
						data : "id=" + id + "&acao=delete",
						success : function(response) {
							limparCampos();
							msgContent.textContent = response;
						}
					}).fail(function(xhr, status, errorThrown) {
						msgContent.textContent = xhr.responseText;
					});
				}

			}
		}
	</script>
</body>

</html>