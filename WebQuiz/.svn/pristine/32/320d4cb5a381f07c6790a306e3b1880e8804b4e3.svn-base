<%@ page import="admin.*" %>
<%@ page import="user.*" %>
<%@ page import="java.util.*" %>

<% 
ArrayList<UserAccount> allUserInfo = UserAccount.searchUserInfo("", false);
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset=UTF-8 />
	<title>Admin</title>
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
		
	
	<form method=post action="AccManageServlet">
	<table align="center" style="width:70%">
		<caption>Account Manager</caption>
		<tr>
		<th>Username</th>
		<th>Authority</th>
		<th>Select</th>
		</tr>
		<% 
		for(UserAccount user: allUserInfo){
			out.println("<tr>");
			out.println("<td align=center>"+user.username+"</td>");
			out.println("<td align=center>"+(user.isAdmin?"admin":"normal")+"</td>");
			out.println("<td align=center><input type=checkbox name=aBox value=\""+user.username+"\"></td>");
			out.println("</tr>");
		}
		%>
		<tr>
		<td></td>
		<td align=center>
			<input type="submit" name="btn" value="delete"/>
		</td>
		<td></td>
		<td></td>
		<td align=center>
			<select name="changeTo">
				 <option value="1">admin</option>
				 <option value="0">normal</option>
			</select>
			<input type="submit" name="btn" value="change"/>
		</td>
		<td></td>
		</tr>
	</table>
	</form>
	
	<br><br>
	<a href="homepage.jsp"><font size=4>Home</font></a>
</body>
</html>