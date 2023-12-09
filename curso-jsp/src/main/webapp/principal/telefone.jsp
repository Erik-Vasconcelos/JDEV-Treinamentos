<%@page import="br.com.jdevtreinamentos.cursojsp.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">

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
															<h5>Cadastro de telefone</h5>
														</div>
														<div class="card-block">
															<form class="form-material"
																action="<%=request.getContextPath()%>/ServletTelefone"
																method="post" id="formUsuario"
																>
																<input type="hidden" name="acao" id="acao">

																<div class="form-group form-default form-static-label">
																	<input type="text" id="id" name="id" class="form-control"
																		readonly="readonly" value="${sessionScope.modelo.id}"
																		id="id"><span class="form-bar"></span><label
																		class="float-label">Id usuário</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" class="form-control"
																		readonly="readonly" value="${sessionScope.modelo.nome}">
																	<span class="form-bar"></span><label class="float-label">Nome</label>
																</div>
																<div class="form-group form-default form-static-label">
																<input type="text" id="numero" name="numero" class="form-control"
																	required="required">
																<span
																		class="form-bar"></span> <label class="float-label">Número
																		</label>
																</div>
																
																<button
																	class="btn btn-success btn-round waves-effect waves-light"
																	type="submit">
																	<font style="vertical-align: inherit;"> <font
																			style="vertical-align: inherit;"> Salvar</font>
																	</font>
																</button>
															</form>
														</div>
													</div>

													<span id="msg">${sessionScope.msg}</span>

													<table class="table" id="tabelaListUsuarios">
														<thead>
															<tr>
																<th scope="col">Id</th>
																<th scope="col">Numero</th>
																<th scope="col">Ação</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${sessionScope.telefones}" var="t">
																<tr>
																	<td><c:out value="${t.id}"></c:out></td>
																	<td><c:out value="${t.numero}"></c:out></td>
																	<td><button type="button"
																			class="btn btn-info btn-round"
																			onclick="excluir(${t.id})">Excluir</button></td>
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
							
							<nav aria-label="Navegação de página exemplo">
							  <ul class="pagination justify-content-center" id="pagination">
							    
							  </ul>
							</nav>
							
						</div>
					</div>
					<div class="modal-footer">
						<span id="totalRegistros"></span>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			
					function excluir(id) {
						var formulario = document.getElementById("formUsuario");
						var idUsuario = document.getElementById("id").value;

						if (id != "") {
							if (confirm("Deseja realmente excluir o usuário? ")) {
								var action = formulario.action;
								var msgContent = document.getElementById("msg");

								window.location.href = formulario.action + "?acao=excluir&id-usuario=" + idUsuario + "&id-telefone=" + id;

							}

						}
					}


				</script>
	</body>

</html>