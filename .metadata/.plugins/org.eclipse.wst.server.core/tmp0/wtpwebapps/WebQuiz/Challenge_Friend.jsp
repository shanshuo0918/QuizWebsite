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
<title>Challenge Friend</title>
</head>
<body>
<%
	session = request.getSession();
	String current_user = (String) session.getAttribute("username");
	
	//Test only
	current_user = "PeiChen";
	//System.out.println(current_user);
	//ID is sent from quiz_summary page
	String QUIZID = request.getParameter("ID");//ID
	//System.out.println(QUIZID);
	
	QUIZID = "Q1"; //FOR TESTING ONLY
	
	FriendListModel friends = new FriendListModel(current_user); //current_user;
	ArrayList<String> friendList = friends.GetFriendList();
	int friend_num = friendList.size();	
%>


<form action="ChallengingServlet" method="post">
    <ul>
        <%
        	if(friend_num!=0){
	            for (String eachfriend : friendList) {
	                out.print(eachfriend);
	                String combine = current_user + "," + eachfriend + "," + QUIZID;
	                //System.out.println(combine);
	                out.print(" <input type=" + "\"radio\"" + " name=" + "\"Info\" " + "value=" + combine + " />");
	                out.print("<br/>");
	            }
	            out.print(" <input type=submit value=Challenge!!> <br/>");
        	}
        %>
    </ul>
    Total: <%= friend_num %>
</form>
	<a href="pseudoHomepage.jsp"><input type="button" value="Go Back"/></a>
	<% 
    out.print(" <a href=" + "QuizSummaryPage.jsp?ID=" + QUIZID + ">" + "<b>" + "Go Back To Quiz Summary" + "</b>" + "</a> ");	               
    out.print("<br/>");
	%>
</body>
</html>