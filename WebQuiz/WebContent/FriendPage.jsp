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
<title>FriendPage</title>
</head>
<body>
<%
	session = request.getSession();
	String current_user = (String) session.getAttribute("username");
	FriendListModel friends = new FriendListModel(current_user); //current_user;
	ArrayList<String> friendList = friends.GetFriendList();
	int friend_num = friendList.size();	
%>

	<ul>
        <%
        	if(friend_num!=0){
	            for (String eachfriend : friendList) {
	            	//Need to modify the href!!!!!!!!!!!!
	                out.print(" <a href=" + 
	               	"MyPageServlet?id=" +
	               	eachfriend + ">" + 
	               	"<b>" + eachfriend + 
	               	"</b>" + "</a> ");	               
	                out.print("<br/>");
	            }
        	}
        %>
    </ul>
    Total: <%= friend_num %>
	<a href="pseudoHomepage.jsp"><input type="button" value="Go Back"/></a>
</body>
</html>