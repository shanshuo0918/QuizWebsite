package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBConnection;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String username = request.getParameter("username");
			ResultSet rs = stmt.executeQuery("select username from "+database.TableNames.USERINFO_TB+
											 " where username = \""+username+"\"");
			if(rs.next()){
				session.setAttribute("signupErrMsg", "Your proposed username already exists. Please choose another username!");
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
				rd.forward(request,response);
			}else{
				Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
				stmt.executeUpdate("insert into "+database.TableNames.USERINFO_TB+" values (\""+
									username+"\",\""+
									Admin.wordToHash(request.getParameter("password"))+"\",\""+
									currentTimestamp+"\",\""+
									request.getParameter("displayname")+"\",\""+
									request.getParameter("privacy_quiz_take")+"\",\""+
									request.getParameter("privacy_quiz_create")+"\",\""+
									request.getParameter("privacy_quiz_score")+"\", \""+
									request.getParameter("privacy_achieve")+"\", "+
									"\"\", "+"\"0\")");
				RequestDispatcher rd = request.getRequestDispatcher("signup-success.jsp");
				rd.forward(request,response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
