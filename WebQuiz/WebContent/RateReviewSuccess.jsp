<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/favicon.ico">

<title>Thank you</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">

<link href="./css/style.css" rel="stylesheet">
</head>
<body>
<div class="site-wrapper">
      <a href="./homepage.jsp"><div class="site-logo"></div></a>
<div class = "tpanel" align="center" margin-top=50px>
<%
String username = (String)session.getAttribute("username");
%>
<h4>Thank you for your rating and review, <%=username %></h4>
<h4><%= new java.util.Date() %></h4>
<h4><a href="homepage.jsp">Home page</a></h4>
</div>
</div>
      <!-- Bootstrap core JavaScript
      ================================================== -->
      <!-- Placed at the end of the document so the pages load faster  -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
      <script>
	  
	  
	  $(document).ready(function(){
		
			
		}
      </script>
  </body>
</html>
