<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <link rel="icon" href="/favicon.ico">

    <title>Select Question Type</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <link href="./css/style.css" rel="stylesheet">
</head>
<body>
<div class="site-wrapper">
      <a href="./homepage.jsp"><div class="site-logo"></div></a>
	  <div class="quiz-background">
<h4> Step 2: Please select the type of question you wish to add!</h4>

Add New Question:<br>
<form action="SelectQuestionTypeServlet" method="post">
Question Type:
<select name="questionType" >
  <option value ="QR">Question Response</option>
  <option value ="FIB">Fill In Blank</option>
  <option value="MC">Multiple Choice</option>
  <option value="PR">Picture Response</option>
  <option value ="MA">Multiple Answer</option>
  <option value="MCMA">Multiple Choice Multiple Answer</option>
  <option value="MATCH">Matching</option>
</select>
<input type="submit" value="Add New Question">
</form>
<hr>
<h4>Here are question(s) you already create!</h4>
<table style="width:100%">
  <tr>
    <th>Question Index</th>
    <th>Question Type</th>
    <th>Question</th>
  </tr>
<%
	if (session.getAttribute("questionList") != null) {
		List<Question> questionList = (List<Question>)session.getAttribute("questionList");
		for (int i = 1; i <= questionList.size(); i++) {
			out.println("<tr>");
			out.println("<td>" + i + "</td>");
			out.println("<td>" + questionList.get(i-1).getType() + "</td>");
			out.println("<td>" + questionList.get(i-1).getQuestion() + "</td>");
			out.println("</tr>");
		}	
	}
%>
</table>
<hr>
If you have finished adding question, please click here to finish! <br>
<form action="FinishCreateQuizServlet" method="post">
<input type="submit" value="I'm done!">
</form>
</div>
</div>
</body>
</html>