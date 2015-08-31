package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBConnection;

/**
 * Servlet implementation class DelAnnServlet
 */
@WebServlet("/DelAnnServlet")
public class DelAnnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] sel = request.getParameterValues("aBox");
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			
			if(sel != null){
				for(String ann: sel){
					stmt.executeUpdate("delete from "+database.TableNames.ANNOUNCE_TB+" where date = \""+ann+"\"");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		rd.forward(request,response);
	}

}
