<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
    <link rel="icon" href="/favicon.ico">

    <title>Create Quiz</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <link href="./css/style.css" rel="stylesheet">
</head>
<body>
<div class="site-wrapper">
      <a href="./homepage.jsp"><div class="site-logo"></div></a>
	  <div class="quiz-background">
<form action="NewQuestionServlet" method="post">
<h3> You are now Creating a new Quiz! Please Following the steps!</h3>
<h4> Step 1: Input the quiz information! </h4>
Name:<input type="text" name="quizName" required><br>
Description:<br><textArea name="quizDescription" row="10" cols="60" ></textarea><br>
Tag:<input type="text" name="tag"><br>
Category:<select name="category" >
  <option value ="Others">Others</option>
  <option value ="Math">Math</option>
  <option value ="Science">Science</option>
  <option value="History">History</option>
  <option value="Phisics">Physics</option>
  <option value ="Chemistry">Chemistry</option>
  <option value="Biology">Biology</option>
  <option value="Art">Art</option>
</select><br>
<input type="checkbox" name="isPracticeMode" value="isPracticeMode"> Allow Practice Mode
<input type="checkbox" name="isRandomOrder" value="isRandomOrder"> Random Order<br>
<input type="checkbox" name="isSinglePage" value="isSinglePage"> All Question in Single Page
<input type="checkbox" name="isCorrection" value="isCorrection"> Immediate Correction<br>
<input type="submit" value="Add Question">
</form>
<hr>
</div>
</div>


</body>
</html>