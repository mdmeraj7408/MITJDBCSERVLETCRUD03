<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management Application</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue;">


			<div>
				<a href="http://www.xadmin.net" class="navbar-brand">User
					Management Application</a>

			</div>
			<ul class="navbar-nav">
				&nbsp;<li><a href="<%=request.getContextPath()%>/list" class="nav-link" style="color:white;font-weight: lighter;">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user!=null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user==null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
				
					<h2>
						<c:if test="${user!=null}">
             Edit User
	</c:if>
						<c:if test="${user==null}">
             Add New User
	</c:if>

					</h2>
				</caption>
				<c:if test="${user!=null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}'/>" />
				</c:if>

				<fieldset class="form-group">
					<label>User Name</label><input type="text"
						value="<c:out value='${user.name}'/>" class="form-control"
						 name="name">
				</fieldset>
				
				<fieldset class="form-group">
					<label>User Email</label><input type="text"
						value="<c:out value='${user.email}'/>" class="form-control"
						 name="email">
				</fieldset>
				
				<fieldset class="form-group">
					<label>User Country</label><input type="text"
						value="<c:out value='${user.country}'/>" class="form-control"
						 name="country">
				</fieldset>
				<button type="submit" class="btn btn-succes">Save</button>
				</form>
			</div>
		</div>

	</div>
      
</body>
</html>