package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBConnection;

/**
 * Servlet implementation class ModifyAccountServlet
 */
@WebServlet("/ModifyAccountServlet")
public class ModifyAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String username = (String) session.getAttribute("username");
			int count = stmt.executeUpdate("update "+database.TableNames.USERINFO_TB+
					" set displayname=\""+request.getParameter("displayname")+
					"\", privacy_quiz_take=\""+request.getParameter("privacy_quiz_take")+
					"\", privacy_quiz_create=\""+request.getParameter("privacy_quiz_create")+
					"\", privacy_quiz_score=\""+request.getParameter("privacy_quiz_score")+
					"\", privacy_achieve=\""+request.getParameter("privacy_achieve")+
					"\" where username=\""+username+"\"");
			if(count > 0)
				out.println("Update Succeeds!");
			else
				out.println("Update Fails...");
		} catch (SQLException e) {
			out.println("Update Fails...");
			e.printStackTrace();
		}
	}

}
