package quiz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBConnection;
import util.Helper;

/**
 * Servlet implementation class FinishCreateQuizServlet
 */
@WebServlet("/FinishCreateQuizServlet")
public class FinishCreateQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishCreateQuizServlet() {
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
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("quizName"));
		String quizName = (String)session.getAttribute("quizName");
		String creator = (String)session.getAttribute("creator");
		String quizDescription = (String)session.getAttribute("quizDescription");
		List<String>tag = (List<String>)session.getAttribute("tag");
		String category = (String)session.getAttribute("category");
		
		Boolean practiceMode = (Boolean)session.getAttribute("isPracticeMode");
		Boolean isRandomOrder = (Boolean)session.getAttribute("isRandomOrder");
		Boolean isSinglePage = (Boolean)session.getAttribute("isSinglePage");
		Boolean isCorrection = (Boolean)session.getAttribute("isCorrection");
		List<Question> questionList = (List<Question>)session.getAttribute("questionList");
		
		Timestamp createTime = new Timestamp(new java.util.Date().getTime());
		int totalScore = 0;
		for (int i = 0; i < questionList.size(); i ++) {
			
			totalScore += questionList.get(i).getMaxScore();
		}
		MyQuiz newQuiz = new MyQuiz(quizName, creator, quizDescription, tag, practiceMode, isRandomOrder,
				isSinglePage, isCorrection, questionList, createTime, category, totalScore);
		newQuiz.save();
		for (int i = 0; i < questionList.size(); i ++) {
			questionList.get(i).updateQuizID(newQuiz.getId());
		}
		
		// Add achievement
		int quizCreated = 0;
		String query = "SELECT COUNT(*) FROM quiz_summary WHERE creator = \"" + creator + "\";";
		Connection con = DBConnection.getConnection();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			quizCreated = rs.getInt("COUNT(*)");
			System.out.println("count" + quizCreated);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (quizCreated == 1) {
			Helper.addAchievement(creator, "Amateur Author");
		}
		
		if (quizCreated == 5) {
			Helper.addAchievement(creator, "Prolific Author");
		}
		
		if (quizCreated == 10) {
			Helper.addAchievement(creator, "Prodigious Author");
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("QuizSummaryPage.jsp?ID=" + newQuiz.getId());
		dispatcher.forward(request, response);
	}

}
