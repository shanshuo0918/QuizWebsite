package quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class GetScoreServ
 */
@WebServlet("/GetScoreServ")
public class GetScoreServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetScoreServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		// get quizName questionIndex currentScore from session
		String quizName = (String) session.getAttribute("quizName");
		Integer questionIndex = (Integer) session.getAttribute("questionIndex");

		MyQuiz quiz = new MyQuiz(quizName);
		List<Question> questionList = quiz.getQuestionList();

		Question lastQuestion = questionList.get(questionIndex - 1);
		String answer = lastQuestion.getUserAnswer(request);
		int score = lastQuestion.getScore(answer);
		int maxScore = lastQuestion.getMaxScore();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("Score: " + score + "/" + maxScore);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
