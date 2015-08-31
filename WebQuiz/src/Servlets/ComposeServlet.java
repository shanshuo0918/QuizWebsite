package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;

import database.DBConnection;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.lang.Boolean;

import Model.MessageModel;
import Model.FriendListModel;

/**
 * Servlet implementation class ComposeServlet
 */
@WebServlet("/ComposeServlet")
public class ComposeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComposeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	private boolean Isuserornot(String UserName){
		String query = "SELECT * FROM userinfo WHERE username = \"" + UserName + "\";";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			if(result.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
		HttpSession session = request.getSession();
		String fromuser = (String) session.getAttribute("username");
		String touser = request.getParameter("touser");
		String from_where = request.getParameter("from_mailbox_userpage");
		
		System.out.println(touser);
		System.out.println(from_where);
		
		String title = request.getParameter("Subject");
		String content = request.getParameter("Content");
		boolean read = false;
		Timestamp createTime = new Timestamp(new java.util.Date().getTime());
		
		//Check if touser is in the userinfo table or not
		if(Isuserornot(touser)){
			MessageModel email = new MessageModel(fromuser, touser, title, content, read, createTime);
			email.UpdateMessageDB();
			if(from_where.equals("mailbox")){
				RequestDispatcher rd = request.getRequestDispatcher("Mail.jsp");
				rd.forward(request,response);
			}
			else if(from_where.equals("userpage")){
				String desti = "MyPageServlet?id=" + touser;

				response.sendRedirect(desti);
			}
		}
		else if(!Isuserornot(touser)){
			RequestDispatcher rd = request.getRequestDispatcher("NonUser.jsp?Non_user=" + touser);
			rd.forward(request,response);
		}
	}
}
