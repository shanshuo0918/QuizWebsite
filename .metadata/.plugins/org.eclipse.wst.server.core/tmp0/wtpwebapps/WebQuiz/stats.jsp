<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, quiz.*, java.text.*, database.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Statistics</title>
<style>
   #firstDiv {float: left;
              width: 350px;
            font-family: sans-serif;
            margin: 10px;
   }
   #secondDiv {float: left;
              width: 350px;
            font-family: sans-serif;
            margin: 10px;
   }
   </style>
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


<div id="firstDiv">
<h4>User Statistics</h4>
<% 
Connection con = DBConnection.getConnection();
try {
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as num FROM userinfo");
	if(rs.next()){
		String num = rs.getString("num");
		out.println("<p>Total number of users: " + num + "</p>");
	}
}catch (SQLException e) {
		e.printStackTrace();
	}

%>
<table style="width:80%">
<tr>
    <th>Username</th>
    <th>#Quiz Take</th> 
    <th>#Quiz Create</th>
  </tr>
<% 
try {
	Statement stmt = con.createStatement();
	Statement stmtone = con.createStatement();
	Statement stmttwo = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT username FROM userinfo ORDER BY username");
	while(rs.next()) {
		out.println("<tr>");
		String user = rs.getString("username");
		String take;
		String create;
		out.println("<td>"+ user + "</td>");
		ResultSet rsone = stmtone.executeQuery("SELECT COUNT(quizID) AS take FROM quiz_history WHERE username=\"" + user +"\"");
		if(rsone.next()) {
			out.println("<td>"+ rsone.getString("take") + "</td>");
		}else {
			out.println("<td>0</td>");
		}
		ResultSet rstwo = stmttwo.executeQuery("SELECT COUNT(*) AS a FROM quiz_summary WHERE creator=\"" + user +"\"");
		if(rstwo.next()) {
			out.println("<td>" + rstwo.getString("a") + "</td>");
		}else {
			out.println("<td>0</td>");
		}
		out.println("</tr>");
	}
}catch (SQLException e) {
		e.printStackTrace();
	}

%>
</table>
</div>

<div id="secondDiv">
   <h4>Quiz Statistics</h4>
   <% 
try {
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as numquiz FROM quiz_summary");
	if(rs.next()){
		String numquiz = rs.getString("numquiz");
		out.println("<p>Total number of quizzes: " + numquiz + "</p>");
	}
}catch (SQLException e) {
		e.printStackTrace();
	}

%>
   <table style="width:80%">
   <tr>
    <th>Quiz</th>
    <th>Times Taken</th> 
    <th>Rating</th>
    <th>Avg Score</th>
  </tr>
<% 
 try {
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM quiz_summary ORDER BY quizName");
	while(rs.next()){
		out.println("<tr>");
		String quiz = rs.getString("quizName");
		String times=rs.getString("numTaken");
		String rate=rs.getString("avgRating");
		String score=rs.getString("avgScore");
		out.println("<td>"+ quiz + "</td>");
		out.println("<td>"+ times + "</td>");
		out.println("<td>"+ rate + "</td>");
		out.println("<td>"+ score + "</td>");
		out.println("</tr>");
	}
}catch (SQLException e) {
		e.printStackTrace();
	}

%>
</table>
</div>

<br><br>
<a href="homepage.jsp"><font size=4>Home</font></a>

</body>
</html>