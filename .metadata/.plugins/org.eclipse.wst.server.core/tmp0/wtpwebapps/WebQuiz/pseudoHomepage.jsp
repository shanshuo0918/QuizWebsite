<%@ page import="java.math.BigDecimal" %>
<%@ page import="Model.ChallengeModel" %>
<%@ page import="Model.FriendRequestModel" %>
<%@ page import="Model.FriendListModel" %>
<%@ page import="Model.MessageModel" %>
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
<title>Homepage_Challenge</title>
</head>
<body>
<%
	session = request.getSession();
	String current_user = (String) session.getAttribute("username");
	
	//Testing only
	//current_user = "PeiChen";
	
	ChallengeModel Current_userChallengeList = new ChallengeModel(current_user);
	
	ArrayList<ChallengeModel> Challenging = Current_userChallengeList.GetChallengeList(current_user, 1);
	ArrayList<ChallengeModel> Challenged = Current_userChallengeList.GetChallengeList(current_user, 2);
	int challengeList_num = Challenging.size();
	int challengeNum = Challenged.size();
	
	
	//For adding friend!!!
	FriendRequestModel Current_user_request= new FriendRequestModel(current_user);
	ArrayList<String> Current_user_request_list = Current_user_request.GetRequestList(current_user);
	int request_num = Current_user_request_list.size();
	
	FriendListModel friends = new FriendListModel(current_user); //current_user;
	ArrayList<String> friendList = friends.GetFriendList();
	int friend_num = friendList.size();	
	
	//session = request.getSession();
	//String touser = (String) session.getAttribute("username");
	
	MessageModel InboxInfo = new MessageModel(current_user); //touser
	ArrayList<MessageModel> InboxList = InboxInfo.GetInboxInfo(current_user);
	int total_ = InboxList.size();
	int unreadNum = 0;
	for(MessageModel iter : InboxList){
		if(!iter.read) unreadNum++;
	}
	
%>
		This should be on quiz_page:
		<a href="Challenge_Friend.jsp"> <input type="button" value="Challenge Friends"></a> <br/>
		
		
		On HomePage: <br/>
		<a href="Mail.jsp"> <input type="button" value="Message"></a> (<%= unreadNum %>) <br/>
		
		<a href="Challenged.jsp"> <input type="button" value="Challenge!!"></a> (<%= challengeNum %>) <br/>
		
		<a href="FriendRequestList.jsp"> <input type="button" value="Request List"></a> (<%= request_num %>) <br/>
		
		<a href="FriendPage.jsp"> <input type="button" value="Friend List"></a> (<%= friend_num %>) <br/>
		
		
</body>
</html>


