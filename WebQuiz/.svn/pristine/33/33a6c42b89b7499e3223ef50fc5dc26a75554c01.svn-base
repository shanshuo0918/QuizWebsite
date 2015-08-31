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
 * Servlet implementation class AccManageServlet
 */
@WebServlet("/AccManageServlet")
public class AccManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] sel = request.getParameterValues("aBox");
		String actType = request.getParameter("btn");
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			if(actType.equals("delete")){
				if(sel != null){
					for(String user: sel){
						stmt.executeUpdate("delete from "+database.TableNames.USERINFO_TB+" where username = \""+user+"\"");
					}
				}
			}else{
				String accChangeTo = request.getParameter("changeTo");
				if(sel != null){
					for(String user: sel){
						stmt.executeUpdate("update "+database.TableNames.USERINFO_TB+" set isAdmin = \""+accChangeTo+"\""+" where username = \""+user+"\"");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("admin-account.jsp");
		rd.forward(request,response);
		
	}

}
