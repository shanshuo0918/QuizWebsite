<%@ page import="java.math.BigDecimal" %>
<%@ page import="Model.ChallengeModel" %>
<%@ page import="Model.FriendListModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.text.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Compose</title>
</head>
<body>
	<form action="ComposeServlet" method="post">
	<%
		String to_u = request.getParameter("receiver");
	
		//System.out.println(to_u);
		
		if(to_u==null){ 
			out.print("To: <input type=text name=touser>");
			out.print("<input type=hidden name=from_mailbox_userpage value=mailbox>");
		}
		else{
			out.print("To: <input type=hidden name=touser value=" + to_u + ">");
			out.print(to_u);
			out.print("<input type=hidden name=from_mailbox_userpage value=userpage>");
		}
		//To: <input type="text" name="touser"/> <br/>
	%>
		<br/>
	    Subject: <input type="text" name="Subject"/> <br/>
	    <p>
			<textarea rows="10" cols="50" name="Content"></textarea>
		</p>
		<p>
	    	<input type="submit" value="send"/>
	    	<a href="Mail.jsp"><input type="button" value="Go Back"/></a>
	    </p>
	</form>
</body>
</html>

