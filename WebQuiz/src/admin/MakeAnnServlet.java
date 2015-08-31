package admin;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class DelAnnounceServlet
 */
@WebServlet("/MakeAnnServlet")
public class MakeAnnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String content = request.getParameter("content");
		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			stmt.executeUpdate("insert into "+database.TableNames.ANNOUNCE_TB+" values (\""+
								username+"\",\""+
								currentTimestamp+"\",\""+
								content+"\")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		rd.forward(request,response);
	}

}
