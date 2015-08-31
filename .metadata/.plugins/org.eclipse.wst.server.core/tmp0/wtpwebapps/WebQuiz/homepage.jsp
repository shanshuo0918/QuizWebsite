<%@ page import="admin.*" %>
<%@ page import="admin.Admin.*" %>
<%@ page import="user.*" %>
<%@ page import="java.util.*" %>
<%@ page import="Model.MessageModel" %>
<%@ page import="Model.ChallengeModel" %>
<%@ page import="Model.FriendListModel" %>
<%@ page import="Model.FriendRequestModel" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
//revision log
//Chishuen:
//Ken: update css on 3/13 12:20am
%>
    
<%! 
public String quizHREF(String id, String name){
	return "<a href=\"QuizSummaryPage.jsp?ID=" + id + "\">" + name + "</a>";
}
%>

<%
int listNum = 5;
int imgHei = 40, imgWid = 40;
String username = (String) session.getAttribute("username");
String loginFailMsg = (String) session.getAttribute("loginFailMsg");
ArrayList<Announce> announceList = admin.Announce.getRecentAnnounce(listNum);
ArrayList<HomepageQuiz> recentQuiz = admin.Admin.getHomepageQuiz(username,listNum,0);
ArrayList<HomepageQuiz> popularQuiz = admin.Admin.getHomepageQuiz(username,listNum,1);

session = request.getSession();
String current_user = (String) session.getAttribute("username");
FriendListModel friends = new FriendListModel(current_user); //current_user;
ArrayList<String> friendList = friends.GetFriendList();
int friend_num = friendList.size();	

FriendRequestModel Current_user_request= new FriendRequestModel(current_user);
ArrayList<String> Current_user_request_list = Current_user_request.GetRequestList(current_user);
int request_num = Current_user_request_list.size();

ChallengeModel Current_userChallengeList = new ChallengeModel("current_user");

ArrayList<ChallengeModel> Challenging = Current_userChallengeList.GetChallengeList(current_user, 2);
ArrayList<Integer> bestscorelist = Current_userChallengeList.getBestScoreList(Challenging); 

int challengeList_num = Challenging.size();

MessageModel InboxInfo = new MessageModel(current_user); //touser
ArrayList<MessageModel> InboxList = InboxInfo.GetInboxInfo(current_user);
int total_ = InboxList.size();
int unreadNum = 0;
for(MessageModel iter : InboxList){
	if(!iter.read) unreadNum++;
}

String about = new String("Welcome to Quiztal! Here you can create and take a variety types of quizzes stuffed with interesting questions! Invite your friends to Quiztal and beat them! We really appreciate your support :)<br><br>by Pei Chen, Ken, Lingzhi, Chi-shuen, Shuo"); 
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <link rel="icon" href="/favicon.ico">

    <title>Quiz</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
	<link href="./css/style.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Tangerine">
  </head>
<style>
#aboutid{
        font-family: 'Tangerine', serif;
        font-size: 60px;
}
</style>
  
<body>
	<%
	// divide into two views to accommodate css
	if(username == null || username.equals("guest")){
		out.println("<div class=\"site-wrapper\"><a href=\"homepage.jsp\"><div class=\"site-logo\"></div></a>");
		out.println("<div class=\"about\" id=\"aboutid\"><p><b>"+about+"</b></p></div>");
		out.println("<div class=\"lpanel lpanel-login\">");
		out.println("<div class = \"nav-menu-login\"><ul>");
		out.println("<li><a href=\"#\" class = \"login-show\">");
		out.println("<span class=\"nav-icon-login\" id=\"message\">D</span><span class=\"nav-title-login\">Login</span>");
		out.println("</a></li>");
		out.println("<li><a href=\"signup.jsp\">");
		out.println("<span class=\"nav-icon-login\" id=\"quiz\">a</span><span class=\"nav-title-login\">Signup</span>");
		out.println("</a></li>");
		out.println("<li><a href=\"SearchByCategory.jsp\">");
		out.println("<span class=\"nav-icon-login\" id=\"setting\">j</span><span class=\"nav-title-login\">Quiz</span>");
		out.println("</a></li>");
		out.println("</ul></div></div>");
		
		out.println("<div class = \"login-bar\">");
		out.println("<p class=\"login-welcome\">Welcome Back</p><br>");
		out.println("<form action=\"LoginServlet\" method=\"post\">");
		out.println("<input type=\"text\" name = \"username\" value = \"\" placeholder = \"Username\" class = \"login user\"><br>");
		out.println("<input type=\"password\" name = \"password\" value = \"\" placeholder = \"Password\" class = \"login password\"><br>");
		out.println("<input type=\"submit\" value = \"Login\" class = \"login-submit\"><br>");
		if(loginFailMsg != null){
			out.print(loginFailMsg);
			out.print("<br>");
			session.removeAttribute("loginFailMsg");
			out.println("<input type = \"text\" id=\"failcheck\" value=\"fail\">");
		}
		out.println("</form></div>");
		
		
		// Announcement
		out.println("<div class = \"tpanel-login\">");
		out.println("<h2>Announcement</h2>");
		
		if(announceList == null || announceList.size() == 0)
			out.println("No Announcement.");
		else{
			out.println("<ol>");
			for(Announce a: announceList){
				out.println("<li>"+a.content+", "+a.datetime+"</li>");
			}
			out.println("</ol>");
		}
		out.println("</div>");
		
		// Cpanel
		out.println("<div class = \"cpanel-login\">");
		// Recent Quiz
		out.println("<div class = \"recent-quiz cpanel-item cpanel-item-login\">");
		out.println("<h2> Recent Quizzes </h2>");
		out.println("<ol>");
		if(recentQuiz == null || recentQuiz.size() == 0){
			out.println("<li>");
			out.println("No quizzes.");
			out.println("</li>");
		}
		else{
			
			for(HomepageQuiz quiz: recentQuiz){
				out.println("<li>"+quizHREF(quiz.quizID,quiz.quizName));
				out.println("<ul><li>"+quiz.createTime+"</li></ul>");
				out.println("</li>");
			}

		}
		out.println("</ol>");
		out.println("</div>");
		
		// Popular Quiz
		out.println("<div class = \"popular-quiz cpanel-item cpanel-item-login\">");
		out.println("<h2> Popular Quizzes </h2>");
		out.println("<ol>");
		if(popularQuiz == null || popularQuiz.size() == 0){
			out.println("<li>");
			out.println("No quizzes.");
			out.println("</li>");
		}
		else{
			
			for(HomepageQuiz quiz: popularQuiz){
				out.println("<li>"+quizHREF(quiz.quizID,quiz.quizName));
				out.println("<ul><li>"+quiz.createTime+"</li></ul>");
				out.println("</li>");
			}

		}
		out.println("</ol>");
		out.println("</div>");
		
		
		out.println("</div>");
		
	}else{
		
		out.println("<div class=\"site-wrapper\">");
		out.println("<a href=\"homepage.jsp\"><div class=\"site-logo\"></div></a>");
		out.println("<div class=\"lpanel\"><div class = \"nav-menu\">");
		out.println("<li><a href=\"MyPageServlet?id="+username+"\">");
		out.println("<span class=\"nav-icon\" id=\"profile\">E</span><span class=\"nav-title\">Profile</span>");
		out.println("</a></li>");
		out.println("<li><a href=\"SearchByCategory.jsp\">");
		out.println("<span class=\"nav-icon\" id=\"quiz\">a</span><span class=\"nav-title\">Quiz</span>");
		out.println("</a></li>");
		out.println("<li><a href=\"CreateQuiz.jsp\">");
		out.println("<span class=\"nav-icon\" id=\"setting\">Z</span><span class=\"nav-title\">Create</span>");
		out.println("</a></li>");
		out.println("<li><a href=\"Mail.jsp\" class = \"message\">");
		out.println("<span class=\"nav-icon\" id=\"message\">D</span><span class=\"nav-title\">Message</span>");
		out.println("<div class=\"nav-notif\">"+unreadNum+"</div>");
		out.println("</a></li>");
		out.println("<li id = \"lp-friend\">");
		out.println("<span class=\"nav-icon\" id=\"friend\">F</span><span class=\"nav-title\">Friends</span>");
		out.println("<div class=\"nav-notif\">"+ request_num+"</div>");
		out.println("</li>");
		out.println("<li id = \"lp-challenged\">");
		out.println("<span class=\"nav-icon\" id=\"challenge\">m</span><span class=\"nav-title\">Challenged</span>");
		out.println("<div class=\"nav-notif\">"+challengeList_num+"</div>");
		out.println("</li>");
		out.println("</ul></div></div>");
				
		// Cpanel
		out.println("<div class = \"cpanel\">");
		// Recent Quiz
		out.println("<div class = \"recent-quiz cpanel-item\">");
		out.println("<h2> Recent Quizzes </h2>");
		out.println("<ol>");
		if(recentQuiz == null || recentQuiz.size() == 0){
			out.println("<li>");
			out.println("No quizzes.");
			out.println("</li>");
		}
		else{
			
			for(HomepageQuiz quiz: recentQuiz){
				out.println("<li>"+quizHREF(quiz.quizID,quiz.quizName));
				out.println("<ul><li>"+quiz.createTime+"</li></ul>");
				out.println("</li>");
			}

		}
		out.println("</ol>");
		out.println("</div>");
        
		// Popular Quiz
		out.println("<div class = \"popular-quiz cpanel-item\">");
		out.println("<h2> Popular Quizzes </h2>");
		out.println("<ol>");
		if(popularQuiz == null || popularQuiz.size() == 0){
			out.println("<li>");
			out.println("No quizzes.");
			out.println("</li>");
		}
		else{
			
			for(HomepageQuiz quiz: popularQuiz){
				out.println("<li>"+quizHREF(quiz.quizID,quiz.quizName));
				out.println("<ul><li>"+quiz.createTime+"</li></ul>");
				out.println("</li>");
			}

		}
		out.println("</ol>");
		out.println("</div>");
		
		// Friends' recent activity
		ArrayList<SearchUser> recentAct = UserFriend.friendRecentAct(username, listNum);
		out.println("<div class = \"friend-act cpanel-item\">");
		out.println("<h2> Friends' Recent Activity </h2>");
		
		if(recentAct.size() > 0){
			out.println("<ol>");
			for(SearchUser act: recentAct){
				out.println("<li><a href=\"MyPageServlet?id="+act.username+"\">"+
							act.displayname+"</a> "+
							"<a href=\"QuizSummaryPage.jsp?ID="+act.quizID+"\">"+
							act.recentActivity+"</a></li>");
			}
			out.println("</ol>");
		}
		out.println("</div>");
		
		// User's recent activity
		ArrayList<QuizHist> quizTaking = admin.Admin.getQuizTaking(username,listNum);
		out.println("<div class = \"my-taken-q cpanel-item\">");
		out.println("<h2> My Recent Taken </h2>");
		out.println("<ol>");
		if(quizTaking == null || quizTaking.size() == 0){
			out.println("<li>");
			out.println("Have not taken any quiz!");
			out.println("</li>");
		}
		else{
			for(QuizHist quiz: quizTaking){
				out.println("<li>"+quizHREF(quiz.quizID,quiz.quizName));
				out.println("<ul>");
				out.println("<li>Date: "+quiz.submitTime+"</li>");
				out.println("<li>Score: "+quiz.score+"</li>");
				out.println("<li>Time Used: "+quiz.timeUsed+"</li>");
				out.println("</ul>");
			}
		}
		out.println("</ol>");
		out.println("</div>");
		
		ArrayList<HomepageQuiz> quizCreating = admin.Admin.getHomepageQuiz(username,listNum,2);
		out.println("<div class = \"my-crea-q cpanel-item\">");
		out.println("<h2> My Recent Created </h2>");
		out.println("<ol>");
		if(quizCreating == null || quizCreating.size() == 0){
			out.println("<li>");
			out.println("Have not created any quiz!");
			out.println("</li>");
		}
		else{
			for(HomepageQuiz quiz: quizCreating){
				out.println("<li>"+quizHREF(quiz.quizID,quiz.quizName));
				out.println("<ul>");
				out.println("<li>"+quiz.createTime+"</li>");
				out.println("</ul>");
			}
		}
		out.println("</ol>");
		out.println("</div>");
	
		// Achievement
		Set<String> achievements = Achievement.getAchievement(username);
		out.println("<div class = \"achieve cpanel-item\">");
		out.println("<h2> Achievement </h2>");
		if(!achievements.isEmpty()){
			out.println("<ul>");
			for(String key: achievements){
				Achievement ach = Achievement.achievementList.get(key);
				out.println("<li><img src=\""+ach.img+"\" alt=\""+key+"\" height=\""+
							imgHei+"\" width=\""+imgWid+"\">   "+key+"</li>");
			}
			out.println("</ul>");
		}else{
			out.println("<i> Do not have any achievement... yet! </i>");
		}
		out.println("</div>");
		out.println("</div>");

		// Announcement
		out.println("<div class = \"tpanel\">");
		out.println("<h2>Announcement</h2>");
		
		if(announceList == null || announceList.size() == 0)
			out.println("No Announcement.");
		else{
			out.println("<ol>");
			for(Announce a: announceList){
				out.println("<li>"+a.content+", "+a.datetime+"</li>");
			}
			out.println("</ol>");
		}
		out.println("</div>");
		
		
		//Search Bar
		String displayName = (String) session.getAttribute("displayname");
		if(displayName == null){
			displayName = UserAccount.getDisplayName(username);
			session.setAttribute("displayname", displayName);
		}
		
		out.println("<div class = \"search-bar\"><p>");
		out.println("Welcome </br>");
		out.println(displayName);
		out.println("</p>");
		out.println("<a href=\"LogoutServlet\" class = \"logout\">Log Out</a>");
		out.println("<form action=\"SearchServlet\" method=\"post\" class =\"search-form\">");
		out.println("<input type=\"text\" name=\"searchKey\" class = \"search\" placeholder = \"Search\"/>");
		out.println("<input type=\"submit\" value=\"H\" class = \"search-button\">");
		out.println("<br>");
		out.println("<input type=\"radio\" name=\"searchType\" value=\"Friend\" checked> <p>Friend</p class = \"search-op\">");
		out.println("<input type=\"radio\" name=\"searchType\" value=\"Quiz\"> <p class = \"search-op\">Quiz</p>");
		out.println("<input type=\"checkbox\" name=\"exactMatch\" value=\"1\"/> <p class = \"search-op\">Exact Match</p>");
		out.println("</form>");
		out.println("</div>");
		
		
		
		
		
		out.println("<div class = \"cpanel-user cpanel-long\" id = \"lp-friend-b\">");
		out.println("<div class = \"cpanel-long-item\">");
		out.println("Friend List: <br/>");
		out.println("<ul>");
		if(friend_num!=0){
		for (String eachfriend : friendList) {
	            	//Need to modify the href!!!!!!!!!!!!
	                out.print(" <a href=" + 
	               	"MyPageServlet?id=" +
	               	eachfriend + ">" + 
	               	"<b>" + eachfriend + 
	               	"</b>" + "</a> ");	               
	                out.print("<br/>");
			}
		}
		out.println("Total number of friends :" +friend_num);
		out.println("</div></ul>");
		
		
		
		out.println("<div class = \"cpanel-long-item\">");
		
		out.println("Friend Request List: <br/>");
		out.println("<form action=\"FriendListUpdateServlet\" method=\"post\">");
		out.println("<ul>");
		if(request_num!=0){
            for (String eachrequest : Current_user_request_list) {
                out.print(eachrequest);
                String combine = current_user + "," + eachrequest;
                //System.out.println(combine);
                out.print(" <input type=" + "\"radio\"" + " name=" + "\"Request_Info\" " + "value=" + combine + " />");
                out.print("<br/>");
            }
            out.print(" <input type=submit name=ok value=accept>");
            out.print(" <input type=submit name=ok value=ignore> <br/>");
    	}
		
		out.println("</ul>");
		out.println("Total Requested:"+request_num);
		out.println("</form></div>");
		out.println("</div>");
		
		
		
		out.println("<div class = \"cpanel-user cpanel-long\" id = \"lp-challenged-b\">");
		
		out.println("Challenged List: <br/>");
		out.println("<ul>");
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
		out.println("</ul>");
		out.println("Total Challenged: "+challengeList_num);
		out.println("");
		out.println("");
		out.println("");
		out.println("");
		out.println("");
		out.println("");
		
		
		out.println("</div>");  
		    
		
		
	}
	
	
	
	%>
	
	
	
	
	
	
	
	
	
	
	</div>

	
	
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
      <script>
	  $(document).ready(function(){
			$(".login-show").click(function(){
				$(".about").toggle();
				$(".login-bar").toggle();
			});
			$("#lp-friend").click(function(){
				$("#lp-friend-b").toggle();
				$("#lp-challenged-b").hide();
			});
			$("#lp-challenged").click(function(){
				$("#lp-challenged-b").toggle();
				$("#lp-friend-b").hide();
			});
			if(document.getElementById("failcheck").value=="fail"){
				$(".about").toggle();
				$(".login-bar").toggle();
			}
			
			
			
	});
      </script>
	
</body>
</html>