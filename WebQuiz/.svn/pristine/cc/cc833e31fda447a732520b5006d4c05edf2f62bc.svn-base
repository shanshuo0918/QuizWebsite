package quiz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBConnection;

/**
 * Servlet implementation class SaveRateReviewServ
 */
@WebServlet("/SaveRateReviewServ")
public class SaveRateReviewServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveRateReviewServ() {
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
		// TODO Auto-generated method stub
		String rating = request.getParameter("rating");
		String review = request.getParameter("review");
		String temp = "No rating";
		if(rating.equals(temp) && review == "") {
			System.out.println("two no");
			RequestDispatcher dispatch = request.getRequestDispatcher("DidnotRateReview.jsp");
			dispatch.forward(request, response);
		} else {
			String quizID = request.getParameter("quizID");
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("username");
			MyQuiz myquiz = new MyQuiz(quizID,1);
			if(rating.equals(temp)) {
				System.out.println("rate no");
				myquiz.updateRatingReview(quizID, username, -1, review);
			}else {
				System.out.println("save success");
				int rate = Integer.parseInt(rating);
				System.out.println(rate);
				System.out.println("user:"+username);
				System.out.println("Review:" + review);
				myquiz.updateRatingReview(quizID, username, rate, review);
			}			
			RequestDispatcher dispatch = request.getRequestDispatcher("RateReviewSuccess.jsp");
			dispatch.forward(request, response);
		}
	}

}
