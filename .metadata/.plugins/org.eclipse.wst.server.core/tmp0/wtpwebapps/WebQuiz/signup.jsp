<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String signupErrMsg = (String) session.getAttribute("signupErrMsg");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <link rel="icon" href="/favicon.ico">

    <title>Quiz</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
	<link href="./css/style.css" rel="stylesheet">
    
  </head>
<body>
<div class="site-wrapper">
      <a href="homepage.jsp"><div class="site-logo"></div></a>
<div class = "easy-b">
	<h1>Create New Account</h1>
	<form action="SignupServlet" method="post">
		<ul>
			<li>Username: <input type="text" name="username" />  
			<%
			if(signupErrMsg != null){
				out.print(signupErrMsg);
				session.removeAttribute("signupErrMsg");
			}
			%>
			</li>
			<li>Password: <input type="password" name="password" /> </li>
			<li>Name to be displayed: <input type="text" name="displayname" /> </li>
		</ul>
		<h2>Privacy Setting:</h2>
		<ul>
			<li>  Who can see my quiz taking history: 
				<input type="radio" name="privacy_quiz_take" value="0" checked/> Myself
				<input type="radio" name="privacy_quiz_take" value="1"/> My friends
				<input type="radio" name="privacy_quiz_take" value="3"/> Everyone 
			</li>
			<li>  Who can see my quiz creating history: 
				<input type="radio" name="privacy_quiz_create" value="0" checked/> Myself
				<input type="radio" name="privacy_quiz_create" value="1"/> My friends
				<input type="radio" name="privacy_quiz_create" value="3"/> Everyone 
			</li>
			<li>  Who can see my quiz scores: 
				<input type="radio" name="privacy_quiz_score" value="0" checked/> Myself
				<input type="radio" name="privacy_quiz_score" value="1"/> My friends
				<input type="radio" name="privacy_quiz_score" value="3"/> Everyone 
			</li>
			<li>  Who can see my achievements: 
				<input type="radio" name="privacy_achieve" value="0" checked/> Myself
				<input type="radio" name="privacy_achieve" value="1"/> My friends
				<input type="radio" name="privacy_achieve" value="3"/> Everyone 
			</li>
		</ul>
		<input type="submit" value="Sign Up"/>
	</form>
	</div>
	</div>
</body>
</html>