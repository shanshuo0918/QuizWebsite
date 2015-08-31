<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>peseudoUserPage</title>
</head>
<body>
	<form action="AddFriendServlet" method="post">
	<%
		String requested_user = request.getParameter("receiver");
		
	
		//Testing only
		//requested_user = "PeiChen";
		
		out.print("<input type=hidden" + " name=\"" + "receiver" + "\" value=" + requested_user + "> <br/>");
	%>
		<p>
	    	<input type="submit" name= add_delete value="Add"/> 
	    	<input type="submit" name= add_delete value="Unfriend"/>
	    </p>
	</form>
</body>
</html>