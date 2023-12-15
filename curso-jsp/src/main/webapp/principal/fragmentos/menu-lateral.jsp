<%@page import="br.com.jdevtreinamentos.cursojsp.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set scope="session" var="perfil"
	value="${sessionScope.usuario.perfil}"></c:set>
<nav class="pcoded-navbar">
	<div class="sidebar_toggle">
		<a href="#"><i class="icon-close icons"></i></a>
	</div>
	<div class="pcoded-inner-navbar main-menu">
		<div class="">
			<div class="main-menu-header">
				<c:choose>
					<c:when test="${sessionScope.usuario.imagem != null && !sessionScope.usuario.imagem.equals('')}">
						<img class="img-80 img-radius" src="${sessionScope.usuario.imagem}"
						alt="User-Profile-Image">
					</c:when>
					
					<c:otherwise>
						<img class="img-80 img-radius" src="assets/images/avatar-4.jpg"
						alt="User-Profile-Image">
					</c:otherwise>
				</c:choose>
				
				<div class="user-details">
					<span id="more-details"><%=((Usuario) session.getAttribute("usuario")).getLogin()%><i
						class="fa fa-caret-down"></i></span>
				</div>
			</div>

			<div class="main-menu-content">
				<ul>
					<li class="more-details"><a href="user-profile.html"><i
							class="ti-user"></i>View Profile</a> <a href="#!"><i
							class="ti-settings"></i>Settings</a> <a href="../login?acao=logout"><i
							class="ti-layout-sidebar-left"></i>Logout</a></li>
				</ul>
			</div>
		</div>
		<div class="p-15 p-b-0">
			<form class="form-material">
				<div class="form-group form-primary">
					<input type="text" name="footer-email" class="form-control"
						required=""> <span class="form-bar"></span> <label
						class="float-label"><i class="fa fa-search m-r-10"></i>Search
						Friend</label>
				</div>
			</form>
		</div>
		<div class="pcoded-navigation-label"
			data-i18n="nav.category.navigation">Layout</div>
		<ul class="pcoded-item pcoded-left-item">
			<li class="active"><a href="inicio.jsp"
				class="waves-effect waves-dark"> <span class="pcoded-micon"><i
						class="ti-home"></i><b>D</b></span> <span class="pcoded-mtext"
					data-i18n="nav.dash.main">Inicio</span> <span
					class="pcoded-mcaret"></span>
			</a></li>
			<li class="pcoded-hasmenu"><a href="javascript:void(0)"
				class="waves-effect waves-dark"> <span class="pcoded-micon"><i
						class="ti-layout-grid2-alt"></i></span> <span class="pcoded-mtext"
					data-i18n="nav.basic-components.main">Cadastros</span> <span
					class="pcoded-mcaret"></span>
			</a>
				<ul class="pcoded-submenu">
				
					<c:if test="${perfil == 'ADMIN'}">
						<li class=" "><a href="../ServletUsuario"
							class="waves-effect waves-dark"> <span class="pcoded-micon"><i
									class="ti-angle-right"></i></span> <span class="pcoded-mtext"
								data-i18n="nav.basic-components.alert">Usuário</span> <span
								class="pcoded-mcaret"></span>
						</a></li>
					</c:if>
					
					
					
				</ul></li>
		</ul>
		<div class="pcoded-navigation-label" data-i18n="nav.category.forms">Relatórios
			</div>
		<ul class="pcoded-item pcoded-left-item">
			<li><a href="relatorio-usuario.jsp"
				class="waves-effect waves-dark"> <span class="pcoded-micon"><i
						class="ti-layers"></i><b>FC</b></span> <span class="pcoded-mtext"
					data-i18n="nav.form-components.main">Usuário</span> <span
					class="pcoded-mcaret"></span>
			</a></li>
			
			<li><a href="grafico-user.jsp"
				class="waves-effect waves-dark"> <span class="pcoded-micon"><i
						class="ti-layers"></i><b>FC</b></span> <span class="pcoded-mtext"
					data-i18n="nav.form-components.main">Gráfico de média salarial</span> <span
					class="pcoded-mcaret"></span>
			</a></li>
		</ul>

	</div>
</nav>