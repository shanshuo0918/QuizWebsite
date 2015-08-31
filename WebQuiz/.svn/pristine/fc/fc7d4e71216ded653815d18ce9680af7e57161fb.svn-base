package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;

import Model.ChallengeModel;
import Model.FriendListModel;
import Model.MessageModel;

import java.sql.*;

/**
 * Servlet implementation class ChallengingServlet
 */
@WebServlet("/ChallengingServlet")
public class ChallengingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String getuser(String input){
	    final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = 0; i < input.length(); i++){
	        final char c = input.charAt(i);
	        if(c == ',') break;
	        sb.append(c);
	    }
	    return sb.toString();
    }
    private String getrequesteduser(String input, String username){
	    final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = username.length()+1; i < input.length(); i++){
	        final char c = input.charAt(i);
	        if(c == ',') break;
	        sb.append(c);
	    }
	    return sb.toString();
    }
    private String getquizID(String input, String username){
    	String requesteduser = getrequesteduser(input, username);
	    final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = username.length()+requesteduser.length()+2; i < input.length(); i++){
	        final char c = input.charAt(i);
	        sb.append(c);
	    }
	    return sb.toString();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String FROM = request.getParameter("from");
		String TO_ = request.getParameter("to_");
		String QUIZID = request.getParameter("ID");
		
		System.out.println(FROM);
		System.out.println(TO_);
		System.out.println(QUIZID);
		
		ChallengeModel delete_challenge = new ChallengeModel(FROM, TO_, QUIZID);
		delete_challenge.DeleteChallenge();
		
		
		//System.out.println("Forward to quiz_summary page!");
		RequestDispatcher rd = request.getRequestDispatcher("QuizSummaryPage.jsp?ID=" + QUIZID);
        rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String combine = request.getParameter("Info");
		
		String user_name = getuser(combine);
		String reciever = getrequesteduser(combine, user_name);
		String QUIZID = getquizID(combine, user_name);
		

		
		ChallengeModel challenge = new ChallengeModel(user_name, reciever, QUIZID);
		
		
		if(!challenge.ontalbeornot()) challenge.UpdateChallengeDB();
		
		//forward to the homepage
		RequestDispatcher rd = request.getRequestDispatcher("QuizSummaryPage.jsp?ID=" + QUIZID);
        rd.forward(request,response);
	}

}
