<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="quiz.*, java.util.*, javax.servlet.http.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%
	List<Question> questionList = new ArrayList<Question>();
	String quizID = request.getParameter("ID");
	//String quizID = "Q1";
	MyQuiz quiz = new MyQuiz(quizID, 1);
	//session.removeAttribute("practiceRecord");
	if (session.getAttribute("practiceRecord") == null) {
		System.out.println("here");
		questionList = quiz.getQuestionList();
	} else {
		System.out.println("here2");
		HashMap<Question, Integer> practiceRecord = (HashMap<Question, Integer>)session.getAttribute("practiceRecord");
		Set<Question> questionArray = practiceRecord.keySet();
		for (Question question : questionArray) {
			questionList.add(question);
		}	
	}
	if (quiz.isRandomOrder()) {
		Collections.shuffle(questionList);
	}
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
	  
<form action="CompletePracticeServlet" method="post">
<%
	for (int i = 0; i < questionList.size(); i++) {
		out.println(questionList.get(i).doQuizPage()); 
		out.println("<hr>");
	}
%>
<input name="ID" type="hidden" value="<%= quizID %>">
<input type = "submit" value = "Submit Answer">
</form>
<form action="ToSummaryPageServlet" method="post">
<input name="ID" type="hidden" value="<%= quizID %>">
<input type="submit" value="Finish Practiceing">
</form>
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