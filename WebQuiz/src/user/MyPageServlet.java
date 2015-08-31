package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.ChallengeModel;
import Model.FriendListModel;
import Model.FriendRequestModel;
import Model.MessageModel;
import util.Helper;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String visitor = (String) session.getAttribute("username");
		String pageowner = request.getParameter("id");
		String tab = request.getParameter("tab");
		if(tab == null){
			tab = "qt";
		}
		String shortInd = request.getParameter("ind");
		if(shortInd == null){
			shortInd = "da";
		}
		String orderStr = request.getParameter("order");
		int order = (orderStr == null)? 1: Integer.parseInt(orderStr);
		
		// get page owner information
		UserAccount ownerAcc = UserAccount.getUserInfo(pageowner);
		if(ownerAcc == null){
			RequestDispatcher rd = request.getRequestDispatcher("invalid-user.jsp");
			if(pageowner == null)
				request.setAttribute("errorMsg","User name is not specified.");
			else
				request.setAttribute("errorMsg","User: "+pageowner+" does not exist!");
			rd.forward(request,response);
		}
		
		int friendship = UserFriend.getFriendship(visitor,pageowner);
		
		
		// Mail, challenge, friend info
		FriendListModel friends = new FriendListModel(visitor); 
		ArrayList<String> friendList = friends.GetFriendList();
		int friend_num = friendList.size();	
		
		FriendRequestModel Current_user_request= new FriendRequestModel(visitor);
		ArrayList<String> Current_user_request_list = Current_user_request.GetRequestList(visitor);
		int request_num = Current_user_request_list.size();
		
		ChallengeModel Current_userChallengeList = new ChallengeModel(visitor);
		ArrayList<ChallengeModel> Challenging = Current_userChallengeList.GetChallengeList(visitor, 2);
		int challengeList_num = Challenging.size();
		ArrayList<Integer> bestscorelist = Current_userChallengeList.getBestScoreList(Challenging);
		
		MessageModel InboxInfo = new MessageModel(visitor); //touser
		ArrayList<MessageModel> InboxList = InboxInfo.GetInboxInfo(visitor);
		//int total_ = InboxList.size();
		int unreadNum = 0;
		for(MessageModel iter : InboxList){
			if(!iter.read) unreadNum++;
		}
		
		// Print html title
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\" />");
		out.println("<title>My Page</title>");
		out.println("<link href=\"./css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.println("<link href=\"./css/style.css\" rel=\"stylesheet\">");
		
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=\"site-wrapper\">");
		out.println("<a href=\"homepage.jsp\"><div class=\"site-logo\"></div></a>");
		
		//lpanel
		out.println("<div class=\"lpanel\"><div class = \"nav-menu\">");
		out.println("<li><a href=\"#\">");
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
		
		// old version
//		out.println("<li><a href=\"friends.jsp\" class = \"friend\">");
//		out.println("<span class=\"nav-icon\" id=\"friend\">F</span><span class=\"nav-title\">Friends</span>");
//		out.println("</a></li>");
//		out.println("<li><a href=\"friends.jsp\" class = \"friend\">");
//		out.println("<span class=\"nav-icon\" id=\"challenge\">m</span><span class=\"nav-title\">Challenged</span>");
//		out.println("</a></li>");
//		out.println("</div></div>");
		
		
		
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
                String combine = visitor + "," + eachrequest;
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
		
		
		
		
		//right lpanel
		out.println("<div class=\"lpanel lpanel-right\"><div class = \"nav-menu\">");
		if(friendship == 1){ // already friend
			out.println("<li id = \"user-send\">");
			out.println("<span class=\"nav-icon\" id=\"profile\">s</span><span class=\"nav-title\">Send</span>");
			out.println("</li>");
			out.println("<li id = \"user-unfriend\">");
			out.println("<span class=\"nav-icon\" id=\"quiz\">l</span><span class=\"nav-title\">Unfriend</span>");
			out.println("</li>");
		}else if(friendship == 2){  // is legit user but not friend
			if(UserFriend.hasSentRequest(visitor,pageowner)){  //#sync method with peichen
				out.println("<li id = \"user-wait\">");
				out.println("<span class=\"nav-icon\" id=\"setting\">s</span><span class=\"nav-title\">Wait Response</span>");
				out.println("</li>");
			}else if(UserFriend.hasSentRequest(pageowner,visitor)){
				out.println("<li id = \"user-accept\">");
				out.println("<span class=\"nav-icon\" id=\"message\">Q</span><span class=\"nav-title\">Accept</span>");
				out.println("</li>");
				out.println("<li id = \"user-reject\">");
				out.println("<span class=\"nav-icon\" id=\"friend\">S</span><span class=\"nav-title\">Reject</span>");
				out.println("</li>");
			}else{
				out.println("<li id = \"user-add\">");
				out.println("<span class=\"nav-icon\" id=\"challenge\">T</span><span class=\"nav-title\">Add Friend</span>");
				out.println("</li>");
			}
		}	
		out.println("</div></div>");
		
		
		// Add friend or send message button 
		if(friendship == 1){ // already friend
			out.println("<form action=\"Compose.jsp\" method=\"get\">");  //#go to peichen's servlet
			out.println("<input type=\"submit\" value=\"Send Message\" id = \"user-send-b\" class = \"hide-b\">");
			out.println("<input type=\"hidden\" name=\"receiver\" value=\""+pageowner+"\">");
			out.println("</form>");
			out.println("<form action=\"AddFriendServlet\" method=\"post\">");  //#go to peichen's servlet
			out.println("<input type=\"hidden\" name=\"receiver\" value=\""+pageowner+"\">");  //#sync name with peichen 
			out.println("<input type=\"hidden\" name=\"add_delete\" value=\"Unfriend\">");
			out.println("<input type=\"submit\" value=\"Un-Friend\" id = \"user-unfriend-b\" class = \"hide-b\">");
			out.println("</form>");
		}else if(friendship == 2){  // is legit user but not friend
			if(UserFriend.hasSentRequest(visitor,pageowner)){  //#sync method with peichen
				out.println("<input type=\"submit\" value=\"Wait For Response\" disabled class = \"hide-b\">");
			}else if(UserFriend.hasSentRequest(pageowner,visitor)){
				out.println("<form action=\"FriendListUpdateServlet\" method=\"post\">");  //#go to peichen's servlet
				out.println("<input type=\"submit\" value=\"Accept\" id = \"user-accept-b\" class = \"hide-b\">");
				out.println("<input type=\"hidden\" name=\"Request_Info\" value=\""+pageowner+","+visitor+"\">");
				out.println("<input type=\"hidden\" name=\"ok\" value=\"accept\">");  //#sync name with peichen 
				out.println("</form>");
				out.println("<form action=\"FriendListUpdateServlet\" method=\"post\">");  //#go to peichen's servlet
				out.println("<input type=\"hidden\" name=\"Request_Info\" value=\""+pageowner+","+visitor+"\">");
				out.println("<input type=\"hidden\" name=\"ok\" value=\"ignore\">");  //#sync name with peichen 
				out.println("<input type=\"submit\" value=\"Reject\" id = \"user-reject-b\" class = \"hide-b\">");
				out.println("</form>");
			}else{
				out.println("<form action=\"AddFriendServlet\" method=\"post\">");  //#go to peichen's servlet
				out.println("<input type=\"hidden\" name=\"receiver\" value=\""+pageowner+"\">");  //#sync name with peichen 
				out.println("<input type=\"hidden\" name=\"add_delete\" value=\"Add\">");
				out.println("<input type=\"submit\" value=\"Add Friend\" id = \"user-add-b\" class = \"hide-b\">");
				out.println("</form>");
			}
		}
		out.println("<br>");
		
		
		//Display Tabs
		out.println("<div class=\"lpanel lpanel-user\">");
		out.println("<div class = \"nav-menu-user\"><ul>");
		out.println("<li><a href=\"MyPageServlet?id="+pageowner+"&tab=qt&ind=da&order=1\">");
		out.println("<span class=\"nav-icon-user\" id=\"message\">a</span><span class=\"nav-title-user\">Taken Quiz</span>");
		out.println("</a></li>");
		out.println("<li><a href=\"MyPageServlet?id="+pageowner+"&tab=qc&ind=da&order=1\">");
		out.println("<span class=\"nav-icon-user\" id=\"quiz\">Z</span><span class=\"nav-title-user\">Created<br>Quiz</span>");
		out.println("</a></li>");
		out.println("<li><a href=\"MyPageServlet?id="+pageowner+"&tab=ac\">");
		out.println("<span class=\"nav-icon-user\" id=\"setting\">Q</span><span class=\"nav-title-user\">Achievement</span>");
		out.println("</a></li>");
		out.println("<li><a href=\"MyPageServlet?id="+pageowner+"&tab=as\">");
		out.println("<span class=\"nav-icon-user\" id=\"setting\">j</span><span class=\"nav-title-user\">Setting</span>");
		out.println("</a></li>");
		out.println("</ul></div>");
		out.println("</div>");
		
		
		//cpanel
		out.println("<div class = \"cpanel-user cpanel-thin\">");
		// Display name
		out.println("<h1 id=\"display\">"+ownerAcc.displayname+" Personal Page</h1>");
		//Display content
		if(tab.equals("qt")){
			out.println(getQuizTakeHistHTML(pageowner,friendship,
					ownerAcc.privacy_quiz_take,ownerAcc.privacy_quiz_score,shortInd,order));
		}else if(tab.equals("qc")){
			out.println(getQuizCreateHistHTML(pageowner,friendship,
					ownerAcc.privacy_quiz_create,shortInd,order));
		}else if(tab.equals("ac")){
			out.println(getAchievementsHTML(pageowner,friendship,
					ownerAcc.privacy_achieve));
		}else if(tab.equals("as")){ 
			out.println(getAccountSettingHTML(ownerAcc,friendship));
		}else{
			//error handling
		}
		out.println("</div>");
		
		//Search Bar
		String username = (String) session.getAttribute("username");
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
		// admin button
		if(ownerAcc.isAdmin)
			out.println("<a href=\"admin.jsp\" class = \"adminBtn\">Admin</a>");
		//
		out.println("<form action=\"SearchServlet\" method=\"post\" class =\"search-form\">");
		out.println("<input type=\"text\" name=\"searchKey\" class = \"search\" placeholder = \"Search\"/>");
		out.println("<input type=\"submit\" value=\"H\" class = \"search-button\">");
		out.println("<br>");
		out.println("<input type=\"radio\" name=\"searchType\" value=\"Friend\" checked> <p>Friend</p class = \"search-op\">");
		out.println("<input type=\"radio\" name=\"searchType\" value=\"Quiz\"> <p class = \"search-op\">Quiz</p>");
		out.println("<input type=\"checkbox\" name=\"exactMatch\" value=\"1\"/> <p class = \"search-op\">Exact Match</p>");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		
		
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js\"></script>");
		out.println("<script>");
		out.println("$(document).ready(function(){");
		
		out.println("$(\"#user-send\").click(function(){");
		out.println("$(\"#user-send-b\").click();");
		out.println("});");
		
		out.println("$(\"#user-unfriend\").click(function(){");
		out.println("$(\"#user-unfriend-b\").click();");
		out.println("});");
		
		out.println("$(\"#user-accept\").click(function(){");
		out.println("$(\"#user-accept-b\").click();");
		out.println("});");
		
		out.println("$(\"#user-reject\").click(function(){");
		out.println("$(\"#user-reject-b\").click();");
		out.println("});");
		
		out.println("$(\"#user-add\").click(function(){");
		out.println("$(\"#user-add-b\").click();");
		out.println("});");
		
		out.println("$(\"#lp-friend\").click(function(){");
		out.println("$(\"#lp-friend-b\").toggle();");
		out.println("$(\"#lp-challenged-b\").hide();");
		out.println("});");
		
		out.println("$(\"#lp-challenged\").click(function(){");
		out.println("$(\"#lp-challenged-b\").toggle();");
		out.println("$(\"#lp-friend-b\").hide();");
		out.println("});");
	
		out.println("});");

		out.println("</script>");
		
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * return the HTML code for the table showing the user's quiz taking history
	 * @param owner
	 * @param friendship
	 * @param privacy_quiz_take
	 * @param privacy_quiz_score
	 * @param shortInd
	 * @param order
	 * @return
	 */
	private String getQuizTakeHistHTML(String owner,
										 int 	friendship,
										 int 	privacy_quiz_take,
										 int 	privacy_quiz_score, 
										 String shortInd, 
										 Integer order)
	{
		StringBuilder sb = new StringBuilder();
		if(friendship > privacy_quiz_take){ // do not display
			sb.append("<font color=\"red\"><i>User do not want to share this information</i></font>\n");
		}else{
			sb.append("<table style=\"width:80%\">\n");
			sb.append("<tr>\n");
			sb.append("<th><a href=\"MyPageServlet?id="+owner+"&tab=qt&ind=qn&order="+getOrder(shortInd,"qn",order).toString()+"\"><font color=\"red\">Quiz Name</font></a></th>\n");
			if(friendship > privacy_quiz_score)
				sb.append("<th><font color=\"red\">Score</font></th>\n");
			else
				sb.append("<th><a href=\"MyPageServlet?id="+owner+"&tab=qt&ind=sc&order="+getOrder(shortInd,"sc",order).toString()+"\"><font color=\"red\">Score</font></a></th>\n");
			sb.append("<th><a href=\"MyPageServlet?id="+owner+"&tab=qt&ind=tu&order="+getOrder(shortInd,"tu",order).toString()+"\"><font color=\"red\">Time Used</font></a></th>\n");
			sb.append("<th><a href=\"MyPageServlet?id="+owner+"&tab=qt&ind=da&order="+getOrder(shortInd,"da",order).toString()+"\"><font color=\"red\">Date</font></a></th>\n");
			sb.append("</tr>\n");
			ArrayList<UserQuizTake> histList = UserQuizTake.quizTakeHist(owner,shortInd,order,-1);
			String score;
			for(UserQuizTake hist: histList){
				sb.append("<tr>\n");
				sb.append("<td align=\"center\"><a href=\"QuizSummaryPage.jsp?ID="+hist.quizID+"\">"+hist.quizName+"</a>"+"</td>\n");
				if(friendship > privacy_quiz_score)
					score = new String("hidden");
				else
					score = Integer.toString(hist.score);
				sb.append("<td align=\"center\">"+score+"</td>\n");
				sb.append("<td align=\"center\">"+Helper.formattedTime(hist.timeUsed)+"</td>\n");
				sb.append("<td align=\"center\">"+hist.date+"</td>\n");
				sb.append("</tr>\n");
			}
			sb.append("</table>\n");
		}
		return sb.toString();
	}
	
	
	/**
	 * return the HTML code for the table showing the user's quiz creating history
	 * @param owner
	 * @param friendship
	 * @param privacy_quiz_create
	 * @param shortInd
	 * @param order
	 * @return
	 */
	private String getQuizCreateHistHTML(String	owner,
			 						   	 int 	friendship,
			 						   	 int 	privacy_quiz_create, 
			 						   	 String shortInd, 
			 						   	 Integer order){
		StringBuilder sb = new StringBuilder();
		if(friendship > privacy_quiz_create){ // do not display
			sb.append("<font color=\"red\"><i>User do not want to share this information</i></font>\n");
		}else{
			sb.append("<table style=\"width:80%\">\n");
			sb.append("<tr>\n");
			sb.append("<th><a href=\"MyPageServlet?id="+owner+"&tab=qc&ind=qn&order="+getOrder(shortInd,"qn",order).toString()+"\"><font color=\"red\">Quiz Name</font></a></th>\n");
			sb.append("<th><a href=\"MyPageServlet?id="+owner+"&tab=qc&ind=nt&order="+getOrder(shortInd,"nt",order).toString()+"\"><font color=\"red\">Taken Number</font></a></th>\n");
			sb.append("<th><a href=\"MyPageServlet?id="+owner+"&tab=qc&ind=ra&order="+getOrder(shortInd,"ra",order).toString()+"\"><font color=\"red\">Rating</font></a></th>\n");
			sb.append("<th><a href=\"MyPageServlet?id="+owner+"&tab=qc&ind=da&order="+getOrder(shortInd,"da",order).toString()+"\"><font color=\"red\">Created Date</font></a></th>\n");
			sb.append("</tr>\n");
			ArrayList<UserQuizCreate> histList = UserQuizCreate.quizCreateHist(owner,shortInd,order,-1);
			for(UserQuizCreate hist: histList){
				sb.append("<tr>\n");
				sb.append("<td align=\"center\"><a href=\"QuizSummaryPage.jsp?ID="+hist.quizID+"\">"+hist.quizName+"</a>"+"</td>\n");
				sb.append("<td align=\"center\">"+hist.numTaken+"</td>\n");
				sb.append("<td align=\"center\">"+hist.rating+"</td>\n");
				sb.append("<td align=\"center\">"+hist.date+"</td>\n");
				sb.append("</tr>\n");
			}
			sb.append("</table>\n");
		}
		return sb.toString();
	}
	
	
	private String getAccountSettingHTML(UserAccount ownerAcc, int friendship){
		StringBuilder sb = new StringBuilder();
		if(friendship == 0){
			sb.append("<form action=\"ModifyAccountServlet\" method=\"post\" id=\"updateAcc\">\n");
			sb.append("<p>Your Username: <input type=\"text\" value=\""+ownerAcc.username+"\" disabled/></p>\n");
			sb.append("<p>Display Name: <input type=\"text\" name=\"displayname\" value=\""+ownerAcc.displayname+"\" id=\"dName\"/></p>\n");
			sb.append("<p>Who can see my quiz taking history:\n");
			sb.append("<input type=\"radio\" name=\"privacy_quiz_take\" value=\"0\""+isChecked(ownerAcc.privacy_quiz_take,0)+"/> Myself\n");
			sb.append("<input type=\"radio\" name=\"privacy_quiz_take\" value=\"1\""+isChecked(ownerAcc.privacy_quiz_take,1)+"/> My friends\n");
			sb.append("<input type=\"radio\" name=\"privacy_quiz_take\" value=\"3\""+isChecked(ownerAcc.privacy_quiz_take,3)+"/> Everyone\n");
			sb.append("</p>\n");
			sb.append("<p>Who can see my quiz creating history:\n");
			sb.append("<input type=\"radio\" name=\"privacy_quiz_create\" value=\"0\""+isChecked(ownerAcc.privacy_quiz_create,0)+"/> Myself\n");
			sb.append("<input type=\"radio\" name=\"privacy_quiz_create\" value=\"1\""+isChecked(ownerAcc.privacy_quiz_create,1)+"/> My friends\n");
			sb.append("<input type=\"radio\" name=\"privacy_quiz_create\" value=\"3\""+isChecked(ownerAcc.privacy_quiz_create,3)+"/> Everyone\n");
			sb.append("</p>\n");
			sb.append("<p>Who can see my quiz scores:\n");
			sb.append("<input type=\"radio\" name=\"privacy_quiz_score\" value=\"0\""+isChecked(ownerAcc.privacy_quiz_score,0)+"/> Myself\n");
			sb.append("<input type=\"radio\" name=\"privacy_quiz_score\" value=\"1\""+isChecked(ownerAcc.privacy_quiz_score,1)+"/> My friends\n");
			sb.append("<input type=\"radio\" name=\"privacy_quiz_score\" value=\"3\""+isChecked(ownerAcc.privacy_quiz_score,3)+"/> Everyone\n");
			sb.append("</p>\n");
			sb.append("<p>Who can see my achievements:\n");
			sb.append("<input type=\"radio\" name=\"privacy_achieve\" value=\"0\""+isChecked(ownerAcc.privacy_achieve,0)+"/> Myself\n");
			sb.append("<input type=\"radio\" name=\"privacy_achieve\" value=\"1\""+isChecked(ownerAcc.privacy_achieve,1)+"/> My friends\n");
			sb.append("<input type=\"radio\" name=\"privacy_achieve\" value=\"3\""+isChecked(ownerAcc.privacy_achieve,3)+"/> Everyone\n");
			sb.append("</p>\n");
			sb.append("<p><input type=\"submit\" value=\"Update\"/></p>\n");
			sb.append("</form>\n");
			
			// Javascript section
			sb.append("<script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js\"></script>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("var form = $('#updateAcc');\n");
			sb.append("form.submit(function (){\n");
			sb.append("$.ajax({\n");
			sb.append("type: form.attr('method'),\n");
			sb.append("url: form.attr('action'),\n");
			sb.append("data: form.serialize(),\n");
			sb.append("async: false,\n");
			sb.append("success: function(msg){\n");
			sb.append("var displayName = document.getElementById(\"dName\").value;\n"); //bug
			sb.append("$('#display').html(displayName+\" Personal Page\");\n");
			sb.append("alert(msg);\n");
			sb.append("}\n");
			sb.append("});\n");
			sb.append("return false;\n");
			sb.append("});\n");
			sb.append("</script>\n");
			// End of Javascript section
		}else{
			sb.append("<font color=\"red\" size=\"5\">You are not the owner of this user page!</font>");
//			sb.append("<a href=\"homepage.jsp\">Go Back to Home Page</a>");
		}
		return sb.toString();
	}
	
	
	private String getAchievementsHTML(String owner,int friendship, int privacy_achieve){
		StringBuilder sb = new StringBuilder();
		if(friendship > privacy_achieve){
			sb.append("<font color=\"red\"><i>User do not want to share this information</i></font>\n");
		}else{
			Set<String> achieveNot = new HashSet<String>(Achievement.achievementList.keySet());
			Set<String> achieveGot = Achievement.getAchievement(owner);
			if(achieveGot == null){
				sb = new StringBuilder("<font color=\"red\">User not exists!</font>\n");
				return sb.toString();
			}
			achieveNot.removeAll(achieveGot);
			
			// acquired achievements
			sb.append("<table align=\"left\" style=\"width:50%\">\n");
			sb.append("<tr>\n");
			sb.append("<th>Acquired Achievements</th>\n");
			sb.append("<th>Description</th>\n");
			sb.append("</tr>\n");
			int imgHei = 60, imgWid = 60;
			for(String title: achieveGot){
				Achievement ach = Achievement.achievementList.get(title);
				sb.append("<tr>\n");
				sb.append("<td align=\"left\"><img src=\""+ach.img+"\" alt=\""+title+"\" height=\""+
						imgHei+"\" width=\""+imgWid+"\"/>   "+title+"</td>\n");
				sb.append("<td>"+ach.desc+"</td>\n");
				sb.append("</tr>\n");
			}
			sb.append("</table>\n");
			
			// not yet acquired achievements
			sb.append("<table align=\"right\" style=\"width:50%\">\n");
			sb.append("<tr>\n");
			sb.append("<th>Achievements Not Yet Acquired</th>\n");
			sb.append("<th>Description</th>\n");
			sb.append("</tr>\n");
			for(String title: achieveNot){
				Achievement ach = Achievement.achievementList.get(title);
				sb.append("<tr>\n");
				sb.append("<td align=\"left\"><img src=\""+ach.img+"\" alt=\""+title+"\" height=\""+
						imgHei+"\" width=\""+imgWid+"\"/>   "+title+"</td>\n");
				sb.append("<td>"+ach.desc+"</td>\n");
				sb.append("</tr>\n");
			}
			sb.append("</table>\n");
		}
		return sb.toString();
	}
	
	
	
	/**
	 * Compare the current order index and the index to be selected.
	 * If same, flip the order; else, set order = 1
	 * @param currentshortInd
	 * @param shortInd
	 * @param order
	 * @return 
	 */
	private Integer getOrder(String currentshortInd, String shortInd, Integer order){
		return currentshortInd.equals(shortInd)? -order: 1;
	}
	
	/**
	 * return if checked if current privacy == radio option
	 * @param cur
	 * @param toCheck
	 * @return
	 */
	private String isChecked(int cur, int toCheck){
		if(cur == toCheck)
			return " checked";
		else
			return "";
	}
}
