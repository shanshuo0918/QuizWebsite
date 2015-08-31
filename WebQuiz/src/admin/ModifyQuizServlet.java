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
 * Servlet implementation class ModifyQuizServlet
 */
@WebServlet("/ModifyQuizServlet")
public class ModifyQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyQuizServlet() {
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
		if (request.getParameter("button").equalsIgnoreCase("Delete Quiz")) {
			String[] quiz = request.getParameterValues("delete");
			try {
				Connection con = DBConnection.getConnection();
				Statement stmt;
				stmt = con.createStatement();
				
				if(quiz != null){
					for(String id: quiz){
						stmt.executeUpdate("DELETE FROM quiz_summary WHERE quizID = \"" + id +"\"");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.out.println("asdvasdvasdvasdvasdv" + request.getParameter("button"));
			String[] quiz = request.getParameterValues("clear");
			try {
				Connection con = DBConnection.getConnection();
				Statement stmt;
				stmt = con.createStatement();
				
				if(quiz != null){
					for(String id: quiz){
						stmt.executeUpdate("DELETE FROM quiz_history WHERE quizID = \"" + id +"\"");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("QuizManagement.jsp");
		rd.forward(request,response);
		
	}

}
