<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <link rel="icon" href="/favicon.ico">

    <title>Quiz</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
	<link href="./css/style.css" rel="stylesheet">
    
  </head>
<body>
<div class="site-wrapper">
      <a href="homepage.jsp"><div class="site-logo"></div></a>
<div class = "easy-b">
<%
	String non_user = request.getParameter("Non_user");
%>
	User: <%= non_user %> doesn't exist. <br/>
	<a href="Mail.jsp"><input type="button" value="Go Back to Mail Box"/></a> 
</div>
</div>
</body>
</html>