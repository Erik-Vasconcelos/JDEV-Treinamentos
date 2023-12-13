<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">

<jsp:include page="fragmentos/head.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
															method="get" id="formRelatorio">

															<input type="hidden" name="acao" id="acao"
																value="relatorio">

															<div class="form-group form-default form-static-label">
																<input type="text" id="dataInicial" name="dataInicial"
																	class="form-control"
																	value="${sessionScope.dataInicial}"> <span
																	class="form-bar"></span><label class="float-label">Data
																	Inicial</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" id="dataFinal" name="dataFinal"
																	class="form-control"
																	value="${sessionScope.dataFinal}"> <span
																	class="form-bar"></span><label class="float-label">Data
																	Final</label>
															</div>

															<input
																class="btn btn-primary btn-round waves-effect waves-light"
																type="submit" value="Ver relatório">

															<button
																class="btn btn-primary btn-round waves-effect waves-light"
																onclick="downloadRelatorio();">Imprimir
																relatório</button>
																
																<button
																class="btn btn-primary btn-round waves-effect waves-light"
																onclick="downloadRelatorioXsl();">Relatório em Excel</button>

														</form>

														<table class="table" id="tabelaListUsuarios">
															<thead>
																<tr>
																	<th scope="col">Id</th>
																	<th scope="col">Nome</th>
																	<th scope="col">Telefones</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${sessionScope.relatorio}" var="r">
																	<tr>
																		<td><c:out value="${r.id}"></c:out></td>
																		<td><c:out value="${r.nome}"></c:out></td>
																		<td><c:forEach items="${r.telefones}" var="t">
																				<span><c:out value="${t.numero} "></c:out></span>
																			</c:forEach></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- Page-body end -->
							</div>
							<div id="styleSelector"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="fragmentos/scripts.jsp"></jsp:include>

	<script type="text/javascript">
		function downloadRelatorio() {
			document.getElementById("acao").value = "downloadRelatorio";
			$("formRelatorio").submit();

		}
		
		function downloadRelatorioXsl() {
			document.getElementById("acao").value = "downloadRelatorioXsl";
			$("formRelatorio").submit();

		}

		$("#dataInicial, #dataFinal").datepicker(
				{
					dateFormat : 'dd/mm/yy',
					dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta',
							'Quinta', 'Sexta', 'Sábado' ],
					dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D' ],
					dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex',
							'Sáb', 'Dom' ],
					monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril',
							'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro',
							'Outubro', 'Novembro', 'Dezembro' ],
					monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai',
							'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
					nextText : 'Próximo',
					prevText : 'Anterior'
				});
	</script>
</body>

</html>
