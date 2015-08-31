<%@ page import="java.math.BigDecimal" %>
<%@ page import="Model.MessageModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.text.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>

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
		//String ID = (String)request.getAttribute("emailID_forcontenjsp");
		String Contentofemail = (String) request.getAttribute("CONTENT");
		String towho = (String) request.getAttribute("Sender");
		
		out.print("From: " + towho + "<br/> <br/>");
		
		out.print(Contentofemail);
		
		//MessageModel InboxInfo = new MessageModel("Amy"); //touser
		//ArrayList<MessageModel> InboxList = InboxInfo.GetInboxInfo("Amy");
		//int total = InboxList.size();
	%>
	<p>
	<a href="Mail.jsp"><input type="button" value="Go Back"/></a>
	</p>
	</div>
	</div>
</body>
</html>