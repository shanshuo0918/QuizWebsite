package quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StartQuizServlet
 */
@WebServlet("/StartQuizServlet")
public class StartQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		long startTime = System.currentTimeMillis();
		session.setAttribute("startTime", startTime);
		
		String quizID = request.getParameter("ID");
		System.out.println(quizID);
		MyQuiz myquiz = new MyQuiz(quizID, 1);
		List<String> userAnswer = new ArrayList<String>();
		List<String> correctAnswer = new ArrayList<String>();
		List<String> userScoreList = new ArrayList<String>();
		session.setAttribute("userAnswer", userAnswer);
		session.setAttribute("correctAnswer", correctAnswer);
		session.setAttribute("userScoreList", userScoreList);
		session.setAttribute("userScore", 0);
		
		if (myquiz.isSinglePage()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("SinglePageQuiz.jsp?ID=" + quizID);
			dispatcher.forward(request, response);
		} else {
			List<Question> questionList = myquiz.getQuestionList();
			if (myquiz.isRandomOrder()) {
				Collections.shuffle(questionList);
			}
			session.setAttribute("currentIndex", 0);
			session.setAttribute("questionList", questionList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("MultiPageQuiz.jsp?ID=" + quizID);
			dispatcher.forward(request, response);
		}
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
