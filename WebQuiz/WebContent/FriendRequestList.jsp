<%@ page import="java.math.BigDecimal" %>
<%@ page import="Model.FriendRequestModel" %>
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
<title>FriendRequestList</title>
</head>
<body>
<%
	session = request.getSession();
	String current_user = (String) session.getAttribute("username");
	
	//Testing only
	//current_user = "PeiChen";
	
	//System.out.println(current_user);
	
	//For adding friend!!!
	FriendRequestModel Current_user_request= new FriendRequestModel(current_user);
	ArrayList<String> Current_user_request_list = Current_user_request.GetRequestList(current_user);
	int request_num = Current_user_request_list.size();
%>


<form action="FriendListUpdateServlet" method="post">
    <ul>
        <%
        	if(request_num!=0){
	            for (String eachrequest : Current_user_request_list) {
	                out.print(eachrequest);
	                String combine = current_user + "," + eachrequest;
	                //System.out.println(combine);
	                out.print(" <input type=" + "\"radio\"" + " name=" + "\"Request_Info\" " + "value=" + combine + " />");
	                out.print("<br/>");
	            }
	            out.print(" <input type=submit name=ok value=accept>");
	            out.print(" <input type=submit name=ok value=ignore> <br/>");
        	}
        %>
    </ul>
    Total: <%= request_num %>
</form>
	<a href="homepage.jsp"><input type="button" value="Go Homepage"/></a>
</body>
</html>