<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management Application</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<!-- Bootstrap Font Icon CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-md navbar-dark "
			style="background-color: blue;">


			<div>
				<a href="http://www.xadmin.net" class="navbar-brand">User
					Management Application</a>

			</div>
			<ul class="navbar-nav">
				<li><a style="color: white;"
					href="<%=request.getContextPath()%>/list" class="nav-link"><i class="bi bi-person-lines-fill"></i></a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of User</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath() %>/new" class="btn btn-success">Add
					New User</a>

			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Email</th>
						<th>Country</th>
						<th>Action</th>
					</tr>
				</thead>
				<body>
					<c:forEach var="user" items="${listUser}">
						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.country}" /></td>
							<td><a href="edit?id=<c:out value='${user.id}'/>"><i class="bi bi-pencil-square" style="color:green;size: bold;"></i></a>
								&nbsp;&nbsp; &nbsp;&nbsp; <a
								href="delete?id=<c:out value='${user.id}'/>"><i class="bi bi-trash"style="color:red;"></i></a></td>
						</tr>

					</c:forEach>
				</body>
			</table>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>