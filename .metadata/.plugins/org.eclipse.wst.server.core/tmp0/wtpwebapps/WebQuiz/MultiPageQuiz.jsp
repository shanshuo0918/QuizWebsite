<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="quiz.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%
	String quizID = request.getParameter("ID");
	MyQuiz quiz = new MyQuiz(quizID, 1);
	List<Question> questionList = (List<Question>)session.getAttribute("questionList");
	int currentIndex = (Integer)session.getAttribute("currentIndex");
	Question question = questionList.get(currentIndex);
%>
<head>
<meta charset="utf-8">
    <link rel="icon" href="/favicon.ico">

    <title>Quiz Now</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <link href="./css/style.css" rel="stylesheet">
</head>
<body>
	<div class="site-wrapper">
      <a href="./homepage.jsp"><div class="site-logo"></div></a>
	  <div class="quiz-background">
<%
	if (question.getTimeLimit() != 0) {
		out.println("<form name=\"counter\" id = \"timer\">This is a timed question! <br> <input type=\"text\" id=\"time\" name=\"d2\">seconds</form>");
	}
%>

<form action="MultiPageQuizServlet" method="post">
<%
	out.println(question.doQuizPage());

	if (currentIndex == questionList.size() - 1) {
		out.println("Almost there! This is your last question!");
	}
	
%>
<input name="quizID" type="hidden" value="<%= quiz.getId() %>">
<input type = "submit" value = "Submit Answer" id="submit-test">
</form>
<hr>
<%
 	if (quiz.isCorrection()) {
 		out.println("<h4> Your Score here: </h4>");
 		out.println("<table style=\"width:100%\">");
 		out.println("<tr>");
 		out.println("<th>Question Index</th>");
 		out.println("<th>Your Answer</th>");
 		out.println("<th>Correct Answer</th>");
 		out.println("<th>Your Score</th>");
 		out.println("</tr>");
 		
 		List<String> userAnswer = (List<String>)session.getAttribute("userAnswer");
		List<String> correctAnswer = (List<String>)session.getAttribute("correctAnswer");
		List<String> userScoreList = (List<String>)session.getAttribute("userScoreList");
		
		for (int i = 1; i <= userAnswer.size(); i++) {
 				out.println("<tr>");
 				out.println("<td>" + i + "</td>");
 				out.println("<td>" + userAnswer.get(i-1) + "</td>");
 				out.println("<td>" + correctAnswer.get(i-1) + "</td>");
 				out.println("<td>" + userScoreList.get(i-1) + "</td>");
 				out.println("</tr>");
 			}	
 		out.println("</table>");
 	}
%>

</div>
</div>

<!-- Bootstrap core JavaScript
      ================================================== -->
      <!-- Placed at the end of the document so the pages load faster  -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
      <script>
	  $(function() {
		var height = $(".match-left").height();
		var canvas = $("#myCanvas");
		canvas.height(height);
		var c = canvas[0];
		var ctx= c.getContext("2d");
		ctx.canvas.width = 500;
		ctx.canvas.height = height;
			
		var leftSelected = "", rightSelected = "";
		
		var drawLine = function() {			
			var leftN = leftSelected[1], rightN = rightSelected[1];
			var leftHeight = 25 + 67 * (leftN - 1);
			var rightHeight = 25 + 67 * (rightN - 1);
			
			ctx.beginPath();
			ctx.moveTo(0,leftHeight);
			ctx.lineTo(500,rightHeight);
			ctx.stroke();
			console.log(leftHeight, rightHeight);
			
			var leftInv = document.getElementById(leftSelected+'inv');
			var rightInv = document.getElementById(rightSelected+'inv');
			
			leftInv.value = rightSelected;
			rightInv.value = leftSelected;
			
			console.log(leftInv);
			leftSelected = '';
			rightSelected = '';
		}
		
		$(".left-op").click(function(e) {
			var item = $(e.target);
			if(document.getElementById(item.attr('id')+'inv').value!=""){
				alert("already selected");
				return;
			}
			leftSelected = item.attr('id');
			if (leftSelected != '' && rightSelected != '') {
				drawLine();
			}
		});
		
		$(".right-op").click(function(e) {
			var item = $(e.target);
			if(document.getElementById(item.attr('id')+'inv').value!=""){
				alert("already selected");
				return;
			}
			rightSelected = item.attr('id');
			if (leftSelected != '' && rightSelected != '') {
				drawLine();
			}
		});
		$("#clear-but").click(function() {
			ctx.clearRect(0,0,500,height);
			var allInv = document.getElementsByClassName("inv");
			for (i=0;i<allInv.length;i++){
				allInv[i].value = "";
			}
		})
		
	  });
	
	
	  var milisec=0;
	  var seconds= <%=question.getTimeLimit() %>; 
	  var boo = false;
	  document.counter.d2.value='<%=question.getTimeLimit() %>' ;

	function display(){ 
	  if (milisec<=0){ 
	     milisec=9; 
	     seconds-=1;
	  } 
	  if (seconds<=-1){ 
	     milisec=0;
	     seconds+=1 ;
	 	if(boo==false){
	 		$("#submit-test").click();
	 		boo = true;
	 	}
	  } 
	  if (seconds==4){
			document.getElementById("time").style.color = "red";
	}
	     if(boo==false){
	     milisec-=1 
	     }
	     document.counter.d2.value=seconds+"."+milisec ;
	     setTimeout("display()",100);
	 } 
	 display();
	 
	  /*$(document).ready(function(){
		
		
		var elem = document.getElementById("test-text");
		
		
		var id = "#L1";
		var position = getPosition(id);
		
		var myC = document.getElementById("myCanvas");
		myC.height = getPosition("#L1").mY-getPosition("#L2").mY;
		myC.width = getPosition("#R1").mX-getPosition("#L1").mX;
		myC.position = "absolute";
		console.log(myC);
		myC.left = getPosition("#L1").mX + "px";
		myC.top = getPosition("#L1").mY + "px";
		
		alert("The image is located at: " + myC.height +"  "+ myC.width +"  "+ myC.left +"  "+ myC.top);
		
		$(".l1").click(function(){
				$(".l2").toggle();
			});
			var ctx=myC.getContext("2d");
				ctx.beginPath();
				ctx.moveTo(0,0);
				ctx.lineTo(100,100);
				ctx.stroke();
	});
	
	function getPosition(id) {
			var element = document.getElementById(id.substring(1));
			var xPosition = 0;
			var yPosition = 0;
			var xWidth = 0;
			var yHeight = 0;
			var matchX = 0;
			var matchY = 0;
  
			
			while(element) {
				xPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);
				yPosition += (element.offsetTop - element.scrollTop + element.clientTop);
				xWidth = $(id).width();
				yHeight = $(id).height();
				element = element.offsetParent;
			}
			
			matchX = xPosition;
			matchY = yPosition+yHeight/2;
			
			if(id.substring(1,2)=="L"){
				matchX = xPosition+xWidth;
			}
			
			return { x: xPosition, y: yPosition, width:xWidth, height:yHeight, mX: matchX, mY: matchY};
			
		}*/
      </script>
      
      

</body>
</html>