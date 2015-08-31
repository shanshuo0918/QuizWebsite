<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, quiz.*, java.text.*, database.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	List<MyQuiz> allQuiz = QuizHelper.getAllQuiz();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="left" style="width:100%" border=1>
		<tr>
			<th><a href="admin.jsp"><font size=4 color=red>Announcement</font></a></th>
			<th><a href="admin-account.jsp"><font size=4 color=red>Account Manager</font></a></th>
			<th><a href="QuizManagement.jsp"><font size=4 color=red>Quiz Manager</font></a></th>
			<th><a href="stats.jsp"><font size=4 color=red>Statistics</font></a></th>
		</tr>
	</table>
	<br><br>

<form action="ModifyQuizServlet" method="post">
<%

	out.println("<h4> You can delete or clear history of any quiz here: </h4>");
	out.println("<table style=\"width:100%\" align=\"center\" >");
	out.println("<tr>");
	out.println("<th width= \"60%\">Question Name</th>");
	out.println("<th width= \"20%\">Delete Quiz</th>");
	out.println("<th width= \"20%\">Clear History</th>");
	out.println("</tr>");
	
	
	for (int i = 0; i < allQuiz.size(); i++) {
			out.println("<tr>");
			out.println("<td>" + allQuiz.get(i).getQuizName() + "</td>");
			out.println("<td><input type=\"checkbox\" name=\"delete\" value=\"" + allQuiz.get(i).getId() +"\"></td>");
			out.println("<td><input type=\"checkbox\" name=\"clear\" value=\"" + allQuiz.get(i).getId() +"\"></td>");
			out.println("</tr>");
	}
	
	out.println("<tr>");
	out.println("<td> </td>");
	out.println("<td><input type=\"submit\" name=\"button\" value=\"Delete Quiz\"></td>");
	out.println("<td><input type=\"submit\" name=\"button\" value=\"Clear History\"></td>");
	out.println("</tr>");
	
	out.println("</table>");
%>

</form>

<br><br>
<a href="homepage.jsp"><font size=4>Home</font></a>

</body>
</html>