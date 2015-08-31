package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.SearchUser;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String searchKey = request.getParameter("searchKey");
		String searchTypeStr = request.getParameter("searchType");
		String[] exactMatchStr = request.getParameterValues("exactMatch");
		int searchType = 0; 
		if(searchTypeStr.equals("Friend")){
			searchType = 1;
		}else if(searchTypeStr.equals("Quiz")){
			searchType = -1;
		}
		boolean exactMatch = (exactMatchStr==null)? false : true;
		
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
		
		out.println("</div>");
		
		// search result
		out.println("<div class = \"cpanel-user\">");
		out.println("<h2>"+searchTypeStr+" Search Result</h2>");
		if(searchType == 1){ //friend
			out.println("<table style=\"width:80%\">");
			out.println("<tr>");
			out.println("<th>User Name (ID)</th>");
			out.println("<th>Recent Activity</th>");
			out.println("</tr>");
			ArrayList<SearchUser> userList = SearchUser.searchUser(searchKey,username,exactMatch);
			for(SearchUser user: userList){
				out.println("<tr>");
				out.println("<td align=\"center\"><a href=\"MyPageServlet?id="+user.username+"\">"+user.displayname+" ("+user.username+")</a></td>");
				if(user.quizID.length() > 0)
					out.println("<td align=\"center\"><a href=\"QuizSummaryPage.jsp?ID="+user.quizID+"\">"+user.recentActivity+"</a></td>");
				else
					out.println("<td align=\"center\"><i>no info</i></td>");
				out.println("</tr>\n");
			}
			out.println("</table>");
		}else if(searchType == -1){ // quiz
			out.println("<table style=\"width:80%\">");
			out.println("<tr>");
			out.println("<th>Quiz</th>");
			out.println("<th>Rating</th>");
			out.println("<th>Taken Number</th>");
			out.println("</tr>");
			ArrayList<UserQuizCreate> quizList = UserQuizCreate.searchQuiz(searchKey,exactMatch);
			for(UserQuizCreate quiz: quizList){
				out.println("<tr>");
				out.println("<td align=\"center\"><a href=\"QuizSummaryPage.jsp?ID="+quiz.quizID+"\">"+quiz.quizName+"</a>"+"</td>");
				out.println("<td align=\"center\">"+quiz.rating+"</td>");
				out.println("<td align=\"center\">"+quiz.numTaken+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		}else{
			;
		}
		out.println("</div>");
		
		
		out.println("</div>");
		out.println("</div>");
		
		out.println("</body>");
		out.println("</html>");
	}

}
