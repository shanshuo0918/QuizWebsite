package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import java.sql.Timestamp;


//import Model.ChallengeModel;
import Model.FriendListModel;
//import Model.MessageModel;
import Model.FriendRequestModel;

import java.sql.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AddFriendServlet
 */
@WebServlet("/AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requested_user = request.getParameter("receiver");
		HttpSession session = request.getSession();
		String current_user = (String)session.getAttribute("username");
		String unfriendornot = request.getParameter("add_delete");
		
		//Testing only
		//current_user = "Sharon";
		//current_user = "Ian";
		//current_user = "Mon";
		//current_user = "Jocelin";
		//current_user = "Jacob";
		
		//System.out.println(requested_user);
		//System.out.println(current_user);
		FriendRequestModel updateinfo = new FriendRequestModel(current_user, requested_user);
		
		if(unfriendornot.equals("Add")){
		
			//FriendRequestModel updateinfo = new FriendRequestModel(current_user, requested_user);
			updateinfo.AddFriendRequestDB();
		}
		else if(unfriendornot.equals("Unfriend")){
			updateinfo.DeleteFriend(current_user, requested_user);
			updateinfo.DeleteFriend(requested_user, current_user);
		}
		
		
		
		
		/***************************************************************************/
		
		//forward to MyPageServlet
		String desti = "MyPageServlet?id=" + requested_user;
		response.sendRedirect(desti);
		//RequestDispatcher rd = request.getRequestDispatcher("pseudoUserPage.jsp?id=" + requested_user);
        //rd.forward(request,response);
	}

}
