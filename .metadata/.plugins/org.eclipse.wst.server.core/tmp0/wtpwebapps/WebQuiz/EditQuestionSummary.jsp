<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*, quiz.*, java.text.*, database.*, util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
MyQuiz myquiz = new MyQuiz(request.getParameter("quizID"), 1);
//MyQuiz myquiz = new MyQuiz("Q1",1);
List<Question> questionList = myquiz.getQuestionList();
%>
<head>
<meta charset="utf-8">
    <link rel="icon" href="/favicon.ico">

    <title>Edit Questions</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <link href="./css/style.css" rel="stylesheet">

</head>
<body>
<div class="site-wrapper">
      <a href="./homepage.jsp"><div class="site-logo"></div></a>
	  <div class="quiz-background">
<h4>Step2: You can select ANY of the following question you want to edit!</h4>

<table style="width:100%">
  <tr>
    <th>Question Index</th>
    <th>Question Type</th>
    <th>Question</th>
    <th>Button</th>
  </tr>
<%
	for (int i = 1; i <= questionList.size(); i++) {
		out.println("<tr>");
		out.println("<td>" + i + "</td>");
		out.println("<td>" + questionList.get(i-1).getType() + "</td>");
		out.println("<td>" + questionList.get(i-1).getQuestion() + "</td>");
		out.println("<td>" + "<form action=\"EditSingleQuestionServlet\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"quizID\" value=\"" + myquiz.getId() + "\">");
		out.println("<input type=\"hidden\" name=\"questionType\" value=\"" + questionList.get(i-1).getType() + "\">");
		out.println("<input type=\"hidden\" name=\"questionID\" value=\"" + questionList.get(i-1).getId() + "\">");
		out.println("<input type=\"submit\" value=\"edit\"></form>" + "</td>");
		out.println("</tr>");
	}	
%>

</table>
<hr>
If you have finished editing question, please click here to finish! <br>
<form action="FinishEditQuizServlet" method="post">
<input type="hidden" name="quizID" value="<%=myquiz.getId() %>">
<input type="submit" value="I'm done!">
</form>
</div>

</div>
</body>
</html>