<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, quiz.*, database.*, java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/favicon.ico">

<title><% 
MyQuiz myquiz = new MyQuiz(request.getParameter("ID"), 1);
%>
<%=myquiz.getQuizName() %>
</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">

<link href="./css/style.css" rel="stylesheet">

</head>
<body>
<div class="site-wrapper">
<a href="./homepage.jsp"><div class="site-logo"></div></a>
<div class="quiz-background">
<h2>Reviews</h2>
<%
Connection con = DBConnection.getConnection();
Statement stmt = con.createStatement();
String query = new String("SELECT * FROM quiz_rating WHERE quizID = \""+request.getParameter("ID") + "\"");
ResultSet rs = stmt.executeQuery(query);
out.println("<a href=\"QuizSummaryPage.jsp?ID=" +request.getParameter("ID") + "\">Back</a>");
out.println("<ul>");
while(rs.next()) {
	out.println("<li>");
	out.println("<p> User: " + rs.getString("username") + "</p>");
	out.println("<p>" + rs.getString("rating") + "/5 <span class=\"star\">");
	int rating = (int)Math.ceil(Double.parseDouble(rs.getString("rating")));
	if (rating != -1){
		while(rating > 0){
			out.println("J");
			rating = rating-1;
		}
		
	}
	out.println("</span></p>");
	out.println("<p> Review: " + rs.getString("review") + "</p>");
	out.println("</li>");
}
out.println("</ul>");
%>
</div>
</div>
<!-- Bootstrap core JavaScript
      ================================================== -->
      <!-- Placed at the end of the document so the pages load faster  -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
      <script>
	  
	  
	  $(document).ready(function(){
		
			
		}
      </script>
  </body>
</html>