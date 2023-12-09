<%@page import="br.com.jdevtreinamentos.cursojsp.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
																method="post" id="formUsuario"
																enctype="multipart/form-data">
																<input type="hidden" name="acao" id="acao">

																<div class="form-group form-default form-static-label">
																	<input type="number" name="id" class="form-control"
																		readonly="readonly" value="${sessionScope.modelo.id}"
																		id="id"><span class="form-bar"></span><label
																		class="float-label">Id</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="text" name="nome" class="form-control"
																		required="required" value="${sessionScope.modelo.nome}">
																	<span class="form-bar"></span><label class="float-label">Nome</label>
																</div>
																<div class="form-group form-default form-static-label">
																	<input type="email" name="email" class="form-control"
																		required="required" autocomplete="off"
																		value="${sessionScope.modelo.email}"> <span
																		class="form-bar"></span> <label class="float-label">Email
																		(exa@gmail.com)</label>
																</div>

																<div class="form-group form-default">
																	<label class>Imagem usuário</label>

																	<c:choose>
																		<c:when
																			test="${sessionScope.modelo.imagem != null && !sessionScope.modelo.imagem.equals('')}">
																			<a
																				href="../ServletUsuario?acao=downloadImagem&id=${sessionScope.modelo.id}">
																				<img src="${sessionScope.modelo.imagem}"
																					class="d-block" alt="Imagem usuário"
																					id="previewImagem"
																					style="width: 128px; height: 128px; border-radius: 50%; object-fit: cover;">
																			</a>
																		</c:when>
																		<c:otherwise>
																			<img src="assets\images\avatar-1.jpg" class="d-block"
																				alt="Imagem usuário" id="previewImagem"
																				style="width: 128px; height: 128px; border-radius: 50%; object-fit: cover;">
																		</c:otherwise>
																	</c:choose>

																	<input id="inputImagemBanner" type="file"
																		name="imagemFile" accept="image/*">
																</div>

																<div class="form-group form-default">
																	<label class>Sexo</label>
																	<div class="form-check">
																		<input class="form-check-input" type="radio"
																			name="sexo" value="MASCULINO" id="radioMasculino"
																			<%Usuario usuario=(Usuario)
																			request.getSession().getAttribute("modelo");
																			if (usuario !=null && usuario.getSexo().equals("MASCULINO"))
																			{
																			out.print("checked");
																			}%>
																		style="margin-left: 0"> <label
																			class="form-check-label" for="radioMasculino">
																			Masculino</label>
																	</div>
																	<div class="form-check">
																		<input class="form-check-input" type="radio"
																			name="sexo" value="FEMININO" id="radioFeminino"
																			style="margin-left: 0"
																			<%if (usuario !=null && usuario.getSexo().equals("FEMININO"))
																			{
																			out.print("checked");
																			}%>>
																		<label class="form-check-label" for="radioFeminino">
																			Feminino</label>
																	</div>
																</div>
																<div class="form-group form-default form-static-label">
																	<select class="form-control" name="perfil">
																		<option hidden>--Selecione--</option>

																		<option value="ADMIN"
																			<%if (usuario !=null && usuario.getPerfil().equals("ADMIN"))
																			{
																			out.print("selected=\"selected\"");
																			}%>>Admin</option>
																		<option value="SECRETARIA"
																			<%if (usuario !=null &&
																			usuario.getPerfil().equals("SECRETARIA")) {
																			out.print("selected=\"selected\"");
																			}%>>Secretária</option>
																		<option value="AUXILIAR"
																			<%if (usuario !=null &&
																			usuario.getPerfil().equals("AUXILIAR")) {
																			out.print("selected=\"selected\"");
																			}%>>Auxiliar</option>
																	</select> <span class="form-bar"></span> <label
																		class="float-label">Perfil</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input onblur="pesquisarCep()" type="text" name="cep" id="cep"
																		class="form-control"
																		required="required"
																		value="${sessionScope.modelo.cep}"> <span
																		class="form-bar"></span> <label class="float-label">Cep</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input type="text" name="logradouro" id="logradouro" class="form-control"
																		required="required"
																		value="${sessionScope.modelo.logradouro}"> <span
																		class="form-bar"></span> <label class="float-label">Logradouro</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input type="text" name="bairro" id="bairro" class="form-control"
																		required="required"
																		value="${sessionScope.modelo.bairro}"> <span
																		class="form-bar"></span> <label class="float-label">Bairro</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input type="text" name="cidade" id="cidade" class="form-control"
																		required="required"
																		value="${sessionScope.modelo.cidade}"> <span
																		class="form-bar"></span> <label class="float-label">Cidade</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input type="text" name="estado" id="estado" class="form-control"
																		required="required"
																		value="${sessionScope.modelo.estado}"> <span
																		class="form-bar"></span> <label class="float-label">Estado</label>
																</div>

																<div class="form-group form-default form-static-label">
																	<input type="text" id="numero" name="numero" class="form-control"
																		required="required"
																		value="${sessionScope.modelo.numero}"> <span
																		class="form-bar"></span> <label class="float-label">Número</label>
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
																	<font style="vertical-align: inherit;"> <font
																			style="vertical-align: inherit;"> Novo</font>
																	</font>
																</button>
																<button
																	class="btn btn-success btn-round waves-effect waves-light"
																	type="submit">
																	<font style="vertical-align: inherit;"> <font
																			style="vertical-align: inherit;"> Salvar</font>
																	</font>
																</button>
																
																
																<c:if test="${sessionScope.modelo.id != null}">
																	<a href="../ServletTelefone?id-usuario=${sessionScope.modelo.id}"
																		class="btn btn-inverse btn-round waves-effect waves-light">
																		<font style="vertical-align: inherit;"> <font
																				style="vertical-align: inherit;">Telefones</font>
																		</font>
																	</a>
																</c:if>
																<button
																	class="btn btn-danger btn-round waves-effect waves-light"
																	type="button" onclick="excluir()">
																	<font style="vertical-align: inherit;"> <font
																			style="vertical-align: inherit;"> Excluir</font>
																	</font>
																</button>
																<button
																	class="btn btn-inverse btn-round waves-effect waves-light"
																	type="button" data-toggle="modal"
																	data-target="#modalPesquisa">
																	<font style="vertical-align: inherit;"> <font
																			style="vertical-align: inherit;"> Pesquisar</font>
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
												
												<nav>
													<ul class="pagination justify-content-center">

														<%
															int totalPaginas = (int) request.getSession().getAttribute("totalPaginas");
														
															for(int i = 0; i < totalPaginas; i++){
																
																String url = request.getContextPath() + "/ServletUsuario?acao=pagination&offset=" + (i*5);
																out.println("<li class=\"page-item\"><a class=\"page-link\" href=\"" + url + "\"> " + (i + 1) + "</a></li>");
															}
														
														%>

													</ul>
												  </nav>

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

			$('#cep, #numero').keypress(function(event){
				return /\d/.test(String.fromCharCode(event.keyCode));
			});

			function pesquisarCep(){
				var cep = document.getElementById("cep").value;

				$.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

					if (!("erro" in dados)) {
                        //Atualiza os campos com os valores da consulta.
						$("#cep").val(dados.cep);
                        $("#logradouro").val(dados.logradouro);
						$("#cidade").val(dados.localidade);
						$("#bairro").val(dados.bairro);
						$("#estado").val(dados.uf);
                    } 
				});

			}
					function limparCampos() {
						var elementos = document.getElementById("formUsuario").elements;

						for (i = 0; i < elementos.length; i++) {
							elementos[i].value = '';
						}
					}

					function pesquisar(offset) {
						var valor = document.getElementById("valorBusca").value;
						var formulario = document.getElementById("formUsuario");

						if(offset == null){
							offset = 0;
						}
						
						if (valor != null && valor.trim() != '') {
							$
								.ajax(
									{
										method: "get",
										url: formulario.action,
										data: "valorPesquisa=" + valor
											+ "&acao=pesquisar&offset=" + offset,
										success: function (response, textStatus, xhr) {
											var json = JSON.parse(response);

											$("#tabelaUsuarios > tbody > tr")
												.remove();

											$("#pagination > li")
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


											var totalPaginas = xhr.getResponseHeader("totalPaginas");
											
											if(totalPaginas > 0){
												for(var i = 0; i < totalPaginas; i++){
													$("#pagination").append('<li class="page-item"><a class="page-link" onclick="pesquisar(' + (i*5) + ')">' + (i + 1) +'</a></li>')
												}
											}

											document
												.getElementById("totalRegistros").textContent = "Total de registros: "
												+  xhr.getResponseHeader("totalRegistros");;
										}
									}).fail(function (xhr, status, errorThrown) {
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
									method: "get",
									url: action,
									data: "id=" + id + "&acao=delete",
									success: function (response) {
										limparCampos();
										msgContent.textContent = response;
									}
								}).fail(function (xhr, status, errorThrown) {
									msgContent.textContent = xhr.responseText;
								});
							}

						}
					}


					// Add the following code if you want the name of the file appear on select
					$("#imagemFile").on("change", function() {
						var file = document.getElementById("imagemFile");
						var previewImagem = document.getElementById("previewImagem");

						if(file.files.length <= 0){
							return;
						}

						reader = new FileReader();

						reader.onload = () => {
							previewImagem.src = reader.result;
						}

						reader.readAsDataURL(file.files[0]);
					});
				</script>
	</body>

</html>