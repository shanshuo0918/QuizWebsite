package quiz;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Helper;

/**
 * Servlet implementation class CreateQuizServlet
 */
@WebServlet("/CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizServlet() {
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
		
		
		String quizName = "";
		String creator = "";
		String quizDescription = "";
		List<String> tag = new ArrayList<String>();
		boolean practiceMode = false;
		boolean isRandomOrder = false;
		boolean isSinglePage = false; 
		boolean isCorrection = false;
		List<Question> questionList =  new ArrayList<Question>();
		Timestamp createTime = (Timestamp) new java.util.Date();
		String category = "";
		int totalScore = 0;
		
		quizName = request.getParameter("quziName");
		
		creator = request.getParameter("creator");
		quizDescription = request.getParameter("quizDescription");
		//tag = request.getParameter("tag");
		
		practiceMode = Boolean.parseBoolean(request.getParameter("practiceMode"));
		isRandomOrder = Boolean.parseBoolean(request.getParameter("isRandomOrder"));
		isSinglePage = Boolean.parseBoolean(request.getParameter("isSinglePage"));
		isCorrection = Boolean.parseBoolean(request.getParameter("isCorrection"));
		//request.getParameter("questionList");
		category = request.getParameter("category");
		request.getParameter("totalScore");
		
		HttpSession session = request.getSession();
		
		session.setAttribute("creator", creator);
		session.setAttribute("quizeDescription", quizDescription);
		session.setAttribute("tag", tag);
		session.setAttribute("practiceMode", practiceMode);
		session.setAttribute("isRandomOrder", isRandomOrder);
		session.setAttribute("isSinglePage", isSinglePage);
		session.setAttribute("isCorrection", isCorrection);
		session.setAttribute("questionList", questionList);
		session.setAttribute("category", category);
		session.setAttribute("totalScore", totalScore);
		
		
		
		MyQuiz quiz = new MyQuiz(quizName, creator, quizDescription, tag, practiceMode, isRandomOrder, isSinglePage, isCorrection, questionList, createTime, category, totalScore);
		quiz.save();
		// TODO Auto-generated method stub
	}

}
