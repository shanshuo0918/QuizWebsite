package quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CompleteQuizServlet
 */
@WebServlet("/CompleteQuizServlet")
public class CompleteQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteQuizServlet() {
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
		HttpSession session = request.getSession();
		String username = "GodShan";
		if (session.getAttribute("username") != null) {
			username = (String)session.getAttribute("username"); 
		}
		
		MyQuiz quiz = new MyQuiz(request.getParameter("quizID"), 1);
		List<Question> questionList = quiz.getQuestionList();
		
		List<String> userAnswers = new ArrayList<String>();
		List<String> correctAnswer = new ArrayList<String>();
		
		
		
		int total = 0;
		for (int i = 0; i < questionList.size(); i++) {
			String userAnswer = questionList.get(i).getUserAnswer(request);
			userAnswers.add(userAnswer);
			correctAnswer.add(questionList.get(i).getAnswer());
			total += questionList.get(i).getScore(userAnswer);
		}
		
		session.setAttribute("userAnswer", userAnswers);
		session.setAttribute("correctAnswer", correctAnswer);
		
		
		long timeSpend = 10000;
		if (session.getAttribute("startTime") != null) {
			long startTime = (long)session.getAttribute("startTime");
			long endTime = System.currentTimeMillis();
			timeSpend = endTime - startTime;
		}
		quiz.saveQuizEvent(quiz.getId(), username, timeSpend, total);
		request.setAttribute("ID", quiz.getId());
		RequestDispatcher dispatcher = request.getRequestDispatcher("QuizResult.jsp");
		dispatcher.forward(request, response);
	}

}
