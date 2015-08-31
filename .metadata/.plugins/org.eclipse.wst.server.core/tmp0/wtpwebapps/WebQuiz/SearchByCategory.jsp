<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="quiz.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <link rel="icon" href="/favicon.ico">

    <title>Search By Category</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <link href="./css/style.css" rel="stylesheet">
</head>
<body>
<div class="site-wrapper">
      <a href="./homepage.jsp"><div class="site-logo"></div></a>
	  <div class="quiz-background">
<h4> Please Select Quiz Category and Press Search!</h4>
<form action="SearchByCategoryServlet" method="post">
Category: <select name="category" >
<%
	List<String> categories = QuizHelper.categories;
	for (int i = 0; i < categories.size(); i ++) {
		out.println("<option value =\"" + categories.get(i) + "\">" + categories.get(i) + "</option>");
	}
%>
</select>
<input type="submit" value="Search">
</form>
<hr>

<%
	if (request.getParameter("category") != null) {
		
		List<MyQuiz> quizList = QuizHelper.searchQuizByCategory(request.getParameter("category"));
		out.println("<table style=\"width:100%\">");
		out.println("<tr>");
		out.println("<th>Quiz Name</th>");
		out.println("<th>Description</th>");
		out.println("<th>Quiz Rating</th>");
		out.println("<th>Quiz Average Score</th>");
		out.println("<th>Quiz Taken Times</th>");
		out.println("</tr>");
		
		for(int i = 0; i < quizList.size(); i++) {
			out.println("<tr>");
			out.println("<td><a href=\"QuizSummaryPage.jsp?ID=" + quizList.get(i).getId() + "\">"+ quizList.get(i).getQuizName() + "</a></td>");
			out.println("<td>" + quizList.get(i).getQuizDescription() + "</td>");
			out.println("<td>" + quizList.get(i).getAvgRating() + "</td>");
			out.println("<td>" + quizList.get(i).getAvgScore() + "/" + quizList.get(i).getTotalScore() + "</td>");
			out.println("<td>" + quizList.get(i).getTakenTimes() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}
%>
</div>
</div>
</body>
</html>