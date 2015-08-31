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
<%@ page import="java.sql.*, quiz.*, java.text.*, database.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/favicon.ico">

<title>
<%
String quizName = "Test Quiz";
System.out.println("haliluya" + request.getParameter("ID"));


session = request.getSession();
String current_user = (String) session.getAttribute("username");
//System.out.println(current_user);
//ID is sent from quiz_summary page
String QUIZID = request.getParameter("ID");//ID
//System.out.println("Pei-Chen : ID : " + QUIZID);

//QUIZID = "Q1"; //FOR TESTING ONLY

FriendListModel friends = new FriendListModel(current_user); //current_user;
ArrayList<String> friendList = friends.GetFriendList();
int friend_num = friendList.size();	



Connection con = DBConnection.getConnection();
Statement stmt = con.createStatement();
if(request.getParameter("ID")!=null) {
	String query = new String("SELECT quizName FROM quiz_summary WHERE quizID = \""+request.getParameter("ID") + "\"");
	ResultSet rs = stmt.executeQuery(query);
	if(rs.next()) {
		quizName = rs.getString("quizName");
	}
}

if(request.getParameter("ID")==null) QUIZID = "Q1";

%>quizName</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">

<link href="./css/style.css" rel="stylesheet">
</head>
<body>
<%
MyQuiz myquiz = new MyQuiz(quizName);
System.out.println("line61");
if (request.getParameter("ID") != null) {
	myquiz = new MyQuiz(request.getParameter("ID"), 1); 
	System.out.println("line64");
}
%>

<%
	//session = request.getSession();
	//String current_user = (String) session.getAttribute("username");
	//System.out.println(current_user);
	//ID is sent from quiz_summary page
	//String QUIZID = request.getParameter("ID");//ID
	//System.out.println("Pei-Chen : ID : " + QUIZID);
	
	//QUIZID = "Q1"; //FOR TESTING ONLY
	
	//FriendListModel friends = new FriendListModel(current_user); //current_user;
	//ArrayList<String> friendList = friends.GetFriendList();
	//int friend_num = friendList.size();	
%>

<div class="site-wrapper">
      <a href="./homepage.jsp"><div class="site-logo"></div></a>
	  <div class="lpanel">
		<div class = "nav-menu">
		  <% String guest="guest";
		  if(!guest.equals(session.getAttribute("username"))){
        	  out.println("<li>");
        	  out.println("<a href=\"Review.jsp?ID=" +myquiz.getId() + "\">");
        	  out.println("<span class=\"nav-icon\" id=\"profile\">X</span>");
        	  out.println("<span class=\"nav-title\">See reviews</span>");
        	  out.println("</a>");
        	  out.println("</li>");
          }%>
          <%if(!guest.equals(session.getAttribute("username"))){
        	  out.println("<li>");
        	  out.println("<a href=\"StartQuizServlet?ID=" +myquiz.getId() + "\">");
        	  out.println("<span class=\"nav-icon\" id=\"message\">X</span>");
        	  out.println("<span class=\"nav-title\">Start</span>");
        	  out.println("</a>");
        	  out.println("</li>");
          }%>
          <%if(!myquiz.isPractice()||!guest.equals(session.getAttribute("username"))){
        	  out.println("<li>");
        	  out.println("<a href=\"PracticeMode.jsp?ID=" +myquiz.getId() + "\">");
        	  out.println("<span class=\"nav-icon\" id=\"quiz\">P</span>");
        	  out.println("<span class=\"nav-title\">Practice</span>");
        	  out.println("</a>");
        	  out.println("</li>");
          }%>
            <%if(!guest.equals(session.getAttribute("username"))){
        	  out.println("<li id=\"challenge-b-button\">");
        	  out.println("<span class=\"nav-icon\" id=\"setting\">F</span>");
        	  out.println("<span class=\"nav-title\">Challenge</span>");
        	  out.println("</li>");
            }
          %>
          <%if(myquiz.getCreator().equals(session.getAttribute("username"))){
        	  out.println("<li>");
        	  out.println("<a href=\"EditQuiz.jsp?ID=" +myquiz.getId() + "\">");
        	  out.println("<span class=\"nav-icon\" id=\"achieve\">Z</span>");
        	  out.println("<span class=\"nav-title\">Edit</span>");
        	  out.println("</a>");
        	  out.println("</li>");
          }
          %>
      </div>
      </div>

	  <div class = "cpanel">
		<div class = "popular-quiz cpanel-item cpanel-item-quiz">
		<h2> High Score </h2>
		<ol>
			
<%
for(MyQuiz.QuizEvent q: myquiz.getHighScoreEvents(5)) {
	out.println("<li>");
	out.println("<a href=\"#\" class = \"quiz-tag\">");
	out.println(q.getUserName() + "</a>");
	out.println("<ul>");
	out.println("<li>Score: " + q.getScore() + "</li>");
	out.println("<li>Time: " + q.getTimeUsedFormat() + "</li>");
	out.println("</ul>");
	out.println("</li>");
}
%>				
		</ol>
		</div>
		<div class = "recent-quiz cpanel-item cpanel-item-quiz">
		<h2> High Score Yesterday </h2>
		<ol>
<%
for(MyQuiz.QuizEvent q: myquiz.getHighScoreEventsLastDay(5)) {
	out.println("<li>");
	out.println("<a href=\"#\" class = \"quiz-tag\">");
	out.println(q.getUserName() + "</a>");
	out.println("<ul>");
	out.println("<li>Score: " + q.getScore() + "</li>");
	out.println("<li>Time: " + q.getTimeUsedFormat() + "</li>");
	out.println("</ul>");
	out.println("</li>");
}
%>	
		</ol>
		</div>
		
		<div class = "b-summary cpanel-item cpanel-item-quiz">
		<h2> Recent Score </h2>
		<ol>
<%
for(MyQuiz.QuizEvent q: myquiz.getRecentTakenEvents(5)) {
	out.println("<li>");
	out.println("<a href=\"#\" class = \"quiz-tag\">");
	out.println(q.getUserName() + "</a>");
	out.println("<ul>");
	out.println("<li>Score: " + q.getScore() + "</li>");
	out.println("<li>Time: " + q.getTimeUsedFormat() + "</li>");
	out.println("</ul>");
	out.println("</li>");
}
%>	
		</ol>
		</div>
	<div id = "challenge-bubble">
	<form action="ChallengingServlet" method="post">
    <ul>
        <%
        	if(friend_num!=0){
	            for (String eachfriend : friendList) {
	                
	                String combine = current_user + "," + eachfriend + "," + QUIZID;
	                //System.out.println(combine);
	                out.print(" <input type=" + "\"radio\"" + " name=" + "\"Info\" " + "value=" + combine + " />");
	                out.print("  "+eachfriend);
	                out.print("<br/>");
	            }
	            out.print(" <input type=submit value=Challenge!! class=\"login-submit\"> <br/>");
        	}
        %>
    </ul>
    Total Friend Number: <%= friend_num %>
</form>
	</div>
	  </div>

	  <div class = "tpanel tpanel-quiz">
	  <ul class = "quiz-main">
			<li class="quiz-name">
			<%=myquiz.getQuizName() %>
			</li>
			<li>
			<%=myquiz.getCategory() %>
<%
out.println(", ");
for (String tag: myquiz.getTag()) {
	out.println("#" + tag);
}
%>
			</li>
<%
NumberFormat nf = NumberFormat.getInstance();
nf.setMaximumFractionDigits(0);
nf.setMinimumFractionDigits(0);
%>
			<li>
			Rating: <%=nf.format(myquiz.getAvgRating()) %>/5<span class="star">
			<%
			int rate = (int)Math.ceil(myquiz.getAvgRating());
			if (rate != -1){
				while(rate > 0){
					out.println("J");
					rate = rate-1;
				}
				
			}
			%>
			</span>
			</li>
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
		
		<p class = "description">
			<%=myquiz.getQuizDescription() %>
		</p>
		
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
			var lowp = low/total*100;
			var avgp = avg/total*100;
			var highp = high/total*100;
			
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
			$("#challenge-b-button").click(function() {
	  			$("#challenge-bubble").toggle();
	  			$(".cpanel-item").toggle();
	  		});
	});
	
      </script>
  </body>
</html>

