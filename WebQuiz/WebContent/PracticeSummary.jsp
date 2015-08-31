<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="quiz.*, java.util.*, javax.servlet.http.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%
	String quizID = request.getParameter("ID");
%>
<head>
<meta charset="utf-8">
    <link rel="icon" href="/favicon.ico">

    <title>Quiz Practice</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <link href="./css/style.css" rel="stylesheet">
</head>
<body>
<div class="site-wrapper">
      <a href="./homepage.jsp"><div class="site-logo"></div></a>
	  <div class="quiz-background">
<%
	out.println("<h4> Your answer and correct answer here: </h4>");
	out.println("<table style=\"width:100%\">");
	out.println("<tr>");
	out.println("<th>Question Index</th>");
	out.println("<th>Your Answer</th>");
	out.println("<th>Correct Answer</th>");
	out.println("</tr>");
	
	List<String> userAnswer = (List<String>)session.getAttribute("userAnswer");
	List<String> correctAnswer = (List<String>)session.getAttribute("correctAnswer");
	for (int i = 1; i <= userAnswer.size(); i++) {
			out.println("<tr>");
			out.println("<td>" + i + "</td>");
			out.println("<td>" + userAnswer.get(i-1) + "</td>");
			out.println("<td>" + correctAnswer.get(i-1) + "</td>");
			out.println("</tr>");
	}	
	out.println("</table>");
%>
<%
	HashMap<Question, Integer> practiceRecord = new HashMap<Question, Integer>();
	practiceRecord = (HashMap<Question, Integer>)session.getAttribute("practiceRecord");
	
	if (practiceRecord.size() == 0) {
		out.println("You've complete the practice mode!");
		out.println("<form action=\"ToSummaryPageServlet\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"ID\" value=\"" + quizID + "\">");
		out.println("<input type=\"submit\" value=\"Finish Practiceing\">");
		out.println("</form>");
	} else {
		out.println("Continue Practicing!");
		out.println("<form action=\"PracticeMode.jsp?ID=" + quizID + "\">");
		out.println("<input type=\"hidden\" name=\"ID\" value=\"" + quizID + "\">");
		out.println("<input type=\"submit\" value=\"Continue Practicing\">");
		out.println("</form>");
		out.println("Stop Practicing!");
		out.println("<form action=\"ToSummaryPageServlet\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"ID\" value=\"" + quizID + "\">");
		out.println("<input type=\"submit\" value=\"Finish Practiceing\">");
		out.println("</form>");
	}
%>
</div>
</div>

</body>
</html>