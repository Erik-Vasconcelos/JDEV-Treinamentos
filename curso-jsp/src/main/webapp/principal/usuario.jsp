<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8" %>
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
														<form class="form-material" action="<%=request.getContextPath()%>/ServletUsuario" method="post">
															<div class="form-group form-default">
																<input type="number" name="id"
																	class="form-control" readonly="readonly"> <span
																	class="form-bar"></span> <label
																	class="float-label">Id</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="nome"
																	class="form-control" required="required"> <span
																	class="form-bar"></span> <label
																	class="float-label">Nome</label>
															</div>
															<div class="form-group form-default">
																<input type="email" name="email"
																	class="form-control" required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label
																	class="float-label">Email
																	(exa@gmail.com)</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="login"
																	class="form-control" required="required"> <span
																	class="form-bar"></span> <label
																	class="float-label">Login</label>
															</div>
															<div class="form-group form-default">
																<input type="password" name="senha"
																	class="form-control" required="required" autocomplete="off"><span
																	class="form-bar"></span> <label
																	class="float-label">Password</label>
															</div>
															
															<button class="btn btn-primary btn-round waves-effect waves-light" type="submit"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Salvar</font></font></button>
														</form>
													</div>
												</div>

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
	</body>

	</html>