package quiz;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaveQuestionServlet
 */
@WebServlet("/SaveQuestionServlet")
public class SaveQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveQuestionServlet() {
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
		
		String questionType = request.getParameter("questionType");
		Question newQuestion = QuestionHelper.createQuestion(questionType, request);
		newQuestion.addQuestion();
		HttpSession session = request.getSession();
		List<Question> questionList = (List<Question>)session.getAttribute("questionList");
		questionList.add(newQuestion);
		session.setAttribute("questionList", questionList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("SelectQuestionType.jsp");
		dispatcher.forward(request, response);
		// TODO Auto-generated method stub
	}

}
