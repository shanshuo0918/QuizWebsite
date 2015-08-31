<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>No Such User</title>
</head>
<body>
	<h1><%= request.getParameter("errorMsg") %></h1>
	<a href="homepage.jsp">Go Back to Home Page</a>
</body>
</html>