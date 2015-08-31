<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, quiz.*, java.text.*, database.*, util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
MyQuiz myquiz = new MyQuiz(request.getParameter("ID"), 1);
//MyQuiz myquiz = new MyQuiz("Q1",1);
%>
<head>
	<meta charset="utf-8">
    <link rel="icon" href="/favicon.ico">

    <title>Edit QUiz</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <link href="./css/style.css" rel="stylesheet">
</head>
<body>
<div class="site-wrapper">
      <a href="./homepage.jsp"><div class="site-logo"></div></a>
	  <div class="quiz-background">
<h3>You are going to editing this quiz!</h3>
<h4>Please review the following quiz information! You can modify any of them if you want!</h4>
<form action="EditQuizInfoServlet" method="post">
Name: <%=myquiz.getQuizName() %><br>
Description: <br><textArea name="quizDescription" rows="10" cols="60" ><%= myquiz.getQuizDescription() %></textarea><br>
Tag:<input type="text" name="tag" value="<%= Helper.generateTags(myquiz.getTag()) %>"><br>
Category:<select name="category" >
  <option value ="<%=myquiz.getCategory()%>"><%=myquiz.getCategory()%></option>
  <option value ="Others">Others</option>
  <option value ="Math">Math</option>
  <option value ="Science">Science</option>
  <option value="History">History</option>
  <option value="Phisics">Physics</option>
  <option value ="Chemistry">Chemistry</option>
  <option value="Biology">Biology</option>
  <option value="Art">Art</option>
</select><br>

<%
	if (myquiz.isPractice()) {
		out.println("<input type=\"checkbox\" name=\"isPracticeMode\" value=\"isPracticeMode\" checked> Allow Practice Mode");
	} else {
		out.println("<input type=\"checkbox\" name=\"isPracticeMode\" value=\"isPracticeMode\"> Allow Practice Mode");
	}

	if (myquiz.isRandomOrder()) {
		out.println("<input type=\"checkbox\" name=\"isRandomOrder\" value=\"isRandomOrder\"checked> Random Order<br>");
	} else {
		out.println("<input type=\"checkbox\" name=\"isRandomOrder\" value=\"isRandomOrder\"> Random Order<br>");
	}
	
	if (myquiz.isSinglePage()) {
		out.println("<input type=\"checkbox\" name=\"isSinglePage\" value=\"isSinglePage\" checked> All Question in Single Page");
	} else {
		out.println("<input type=\"checkbox\" name=\"isSinglePage\" value=\"isSinglePage\"> All Question in Single Page");
	}
	
	if (myquiz.isCorrection()) {
		out.println("<input type=\"checkbox\" name=\"isCorrection\" value=\"isCorrection\" checked>Immediate Correction<br>");
	} else {
		out.println("<input type=\"checkbox\" name=\"isCorrection\" value=\"isCorrection\">Immediate Correction<br>");
	}
%>
<input type="hidden" name="quizID" value=<%=myquiz.getId()%> >
<input type="submit" value="Edit Question">
</form>
</div>
</div>
</body>
</html>