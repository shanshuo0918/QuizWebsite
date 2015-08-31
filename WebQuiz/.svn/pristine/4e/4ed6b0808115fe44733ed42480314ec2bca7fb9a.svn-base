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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Challenged_page</title>
</head>
<body>
<%
	session = request.getSession();
	String current_user = (String) session.getAttribute("username");
	
	//Test only
	//current_user = "PeiChen";
	
	ChallengeModel Current_userChallengeList = new ChallengeModel("current_user");
	
	ArrayList<ChallengeModel> Challenging = Current_userChallengeList.GetChallengeList(current_user, 2);
	ArrayList<Integer> bestscorelist = Current_userChallengeList.getBestScoreList(Challenging); 
	
	int challengeList_num = Challenging.size();
	
	
%>



    <ul>
        <%
        	if(challengeList_num!=0){
        		int i=0;
	            for (ChallengeModel eachchallenge : Challenging) {
	            	String quizname = eachchallenge.GetquizName(eachchallenge.QUIZ_ID);
	                out.print(eachchallenge.user);

	                out.print(" <a href=" + 
		                	"ChallengingServlet?from=" +
		                	eachchallenge.user + "&to_=" + eachchallenge.requested_user + "&ID=" + eachchallenge.QUIZ_ID + ">" + 
		                	"<b>" + quizname + 
		                	"</b>" + "</a> ");
	                out.print(" Best Score: " + bestscorelist.get(i));
	                out.print("<br/>");
	             	i++;   
	            }  
        	}
        %>
    </ul>
    Total: <%= challengeList_num %>

	<a href="pseudoHomepage.jsp"><input type="button" value="Go Back"/></a>
</body>
</html>