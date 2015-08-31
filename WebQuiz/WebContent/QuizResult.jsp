<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, quiz.*, java.text.*, java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/favicon.ico">

<title>Your Quiz Result</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">

<link href="./css/style.css" rel="stylesheet">
</head>
<body>
<% 
String quizID = (String)request.getAttribute("ID");
String username = (String)session.getAttribute("username");
MyQuiz myquiz = new MyQuiz(quizID,1); 
String quizName = myquiz.getQuizName();
MyQuiz.QuizEvent event = myquiz.getUserLatestEvent(quizName, username);
%>

    <div class="site-wrapper">
      <a href="./homepage.jsp"><div class="site-logo"></div></a>
	 
	  
		<div class = "tpanel tpanel-quiz">
		
		<ul class = "quiz-main">
			<h2> <%=myquiz.getQuizName() %> </h2>
			<h1> <%=event.getScore() %>/<%=myquiz.getTotalScore() %> </h1>
			<div class = "low-graph bar-graph">
			<div class="inner-left-cap" id = "user-left-cap"></div>
			<div class="inner-left-bar" id = "user-left"></div>
			<div class="inner-right-bar" id = "user-right"></div>
			<div class="inner-right-cap" id = "user-right-cap"></div>
			
		</div>
		</ul>
			
			
			
			<ul class = "graph-table">
		
		<li>
		<span>Low: <%=myquiz.getLowestScore() %>/<%=myquiz.getTotalScore() %></span>
		 <div class = "low-graph bar-graph">
			<div class="inner-left-cap" id = "low-left-cap"></div>
			<div class="inner-left-bar" id = "low-left"></div>
			<div class="inner-right-bar" id = "low-right"></div>
			<div class="inner-right-cap" id = "low-right-cap"></div>
			
		</div>
		</li>
		<li>
<%
NumberFormat nf = NumberFormat.getInstance();
nf.setMaximumFractionDigits(0);
nf.setMinimumFractionDigits(0);
%>
		<span>Avg: <%=nf.format(myquiz.getAvgScore())%>/<%=myquiz.getTotalScore() %></span>
		 <div class = "low-graph bar-graph">
			<div class="inner-left-cap" id = "avg-left-cap"></div>
			<div class="inner-left-bar" id = "avg-left"></div>
			<div class="inner-right-bar" id = "avg-right"></div>
			<div class="inner-right-cap" id = "avg-right-cap"></div>
		</div>
		</li>
		<li>
		<span>High: <%=myquiz.getBestScore() %>/<%=myquiz.getTotalScore() %></span>
		 <div class = "low-graph bar-graph">
			<div class="inner-left-cap" id = "high-left-cap"></div>
			<div class="inner-left-bar" id = "high-left"></div>
			<div class="inner-right-bar" id = "high-right"></div>
			<div class="inner-right-cap" id = "high-right-cap"></div>
		</div>
		</li>
		</ul>
		</div>

	  <div class = "cpanel4">
		<div class = "q_result cpanel-item cpanel-item-quiz">
		<h2> Your Answers</h2>
		<h3>Hover for correct answers</h3>
		<ol>
		<%
		List<String> correctAnswer = (List<String>)session.getAttribute("correctAnswer");
		List<String> userAnswer = (List<String>)session.getAttribute("userAnswer"); 
		int size = correctAnswer.size();
		for (int i = 0; i < size; i++) {
			out.println("<li>");
			out.println("<a href=\"#\" class = \"quiz-tag\">");
			out.println(userAnswer.get(i));
			out.println("</a>");
			out.println("<ul>");
			out.println(correctAnswer.get(i));
			out.println("</ul>");
		}
		
		%>
		</ol>
		</div>
		<div class = "q_result cpanel-item cpanel-item-quiz">
		<h2> Your Performance </h2>
		<div class="lpanel lpanel-quiz">
		<div class = "nav-menu-quiz">
		<ul>
          <li id = "T1b">
              <span class="nav-icon-quiz" id="message">D</span>
              <span class="nav-title-quiz">By Date</span>
          </li>
          <li id = "T2b">
              <span class="nav-icon-quiz" id="quiz">a</span>
              <span class="nav-title-quiz">By Score</span>
          </li>
          <li id = "T3b">
             <span class="nav-icon-quiz" id="setting">j</span>
              <span class="nav-title-quiz">By Time</span>
          </li>
        </ul>
      </div>
	  </div>
		
		
		<div id = "T1" class = "Tol">
		<p>By Date</p>
		<ol>
		<%
		DateFormat format = new SimpleDateFormat("MM-dd-yy H:mm");
for(MyQuiz.QuizEvent e: myquiz.getUserRecentEvents(username,5)) {
	out.println("<li>" + format.format(e.getSubmitTime()) + ", " + e.getScore() + "/" + myquiz.getTotalScore() + ", " + e.getTimeUsedFormat() + "</li>");
}
%>
		</ol>
		</div>
		<div id = "T2" class = "Tol">
		<p>By Score</p>
		<ol>
<%
for(MyQuiz.QuizEvent e: myquiz.getUserRecentScoreEvents(username,5)) {
	out.println("<li>" + format.format(e.getSubmitTime()) + ", " + e.getScore() + "/" + myquiz.getTotalScore() + ", " + e.getTimeUsedFormat() + "</li>");
}
%>
		</ol>
		</div>
		<div  id = "T3" class = "Tol">
		<p>By Time</p>
		<ol>
<%
for(MyQuiz.QuizEvent e: myquiz.getUserRecentTimeEvents(username,5)) {
	out.println("<li>" + format.format(e.getSubmitTime()) + ", " + e.getScore() + "/" + myquiz.getTotalScore() + ", " + e.getTimeUsedFormat() + "</li>");
}
%>
		</ol>
		</div>
		</div>
		<div class = "q_result cpanel-item cpanel-item-quiz">
		<h2> Top Performance </h2>
		<ol>
		<%
for(MyQuiz.QuizEvent q: myquiz.getHighScoreEvents(5)) {
	out.println("<li>" + q.getUserName() + " Score: " + q.getScore() + " Time: " + q.getTimeUsedFormat() + "</li>");
}
%>
		</ol>
		</div>
		<div class = "q_result cpanel-item cpanel-item-quiz">
		<h2> Review </h2>
		<p>You can rate and review this quiz:</p>
		<form action="SaveRateReviewServ" method="post">
			<li>Rating<select name="rating">
			<option value = "No rating">No rating</option>
			<option value = "0">0</option>
			<option value = "1">1</option>
			<option value = "2">2</option>
			<option value = "3">3</option>
			<option value = "4">4</option>
			<option value = "5">5</option>
			</select>
			</li>
			<li>Review<input type="text" name="review" /></li>
			<%out.println("<input name = quizID type = \"hidden\" value = \"" + quizID + "\"/>"); %>
			<input type="submit" class = "login-submit" value="Submit"/>
		</form>
		</div>
		
		
	  </div>
	   
	  
	</div>

      <!-- Bootstrap core JavaScript
      ================================================== -->
      <!-- Placed at the end of the document so the pages load faster  -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
      <script>
	  
	  
      $(document).ready(function(){
    	  var total = <%=myquiz.getTotalScore()%>;
			var low = <%=myquiz.getLowestScore()%>;
			var avg = <%=nf.format(myquiz.getAvgScore())%>;
			var high = <%=myquiz.getBestScore()%>;
			var user = <%=event.getScore() %>;
			var lowp = low/total*100;
			var avgp = avg/total*100;
			var highp = high/total*100;
			var userp = user/total*100;
			
			document.getElementById("user-left").style.width = userp*0.98+"%";
			document.getElementById("user-right").style.width = (100-userp)*0.98+"%";
			if(user==0){
				document.getElementById("user-left-cap").style.background = "red";
			}
			if(user==total){
				document.getElementById("user-right-cap").style.background = "orange";
			}
			
			
			document.getElementById("low-left").style.width = lowp*0.98+"%";
			document.getElementById("low-right").style.width = (100-lowp)*0.98+"%";
			if(low==0){
				document.getElementById("low-left-cap").style.background = "red";
			}
			if(low==total){
				document.getElementById("low-right-cap").style.background = "orange";
			}
			
			document.getElementById("avg-left").style.width = avgp*0.98+"%";
			document.getElementById("avg-right").style.width = (100-avgp)*0.98+"%";
			if(avg==0){
				document.getElementById("avg-left-cap").style.background = "red";
			}
			if(avg==total){
				document.getElementById("avg-right-cap").style.background = "orange";
			}
			
			document.getElementById("high-left").style.width = highp*0.98+"%";
			document.getElementById("high-right").style.width = (100-highp)*0.98+"%";
			if(high==0){
				document.getElementById("high-left-cap").style.background = "red";
			}
			if(high==total){
				document.getElementById("high-right-cap").style.background = "orange";
			}
  		$("#T1b").click(function() {
  			$("#T1").show();
  			$("#T2").hide();
  			$("#T3").hide();
  		});
  		$("#T2b").click(function() {
  			$("#T2").show();
  			$("#T1").hide();
  			$("#T3").hide();
  		});
  		$("#T3b").click(function() {
  			$("#T3").show();
  			$("#T1").hide();
  			$("#T2").hide();
  		});		
  		})
      </script>
  </body>
</html>
