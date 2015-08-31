package quiz;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Helper;

/**
 * Servlet implementation class EditQuizInfoServlet
 */
@WebServlet("/EditQuizInfoServlet")
public class EditQuizInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditQuizInfoServlet() {
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
		String quizID = request.getParameter("quizID");
		MyQuiz myquiz = new MyQuiz(quizID, 1);
		
		String quizDescription = request.getParameter("quizDescription");
		List<String>tag = Helper.parseTags(request.getParameter("tag"));
		String category = request.getParameter("category");
		
		Boolean isPracticeMode = false;
		if (request.getParameter("isPracticeMode") != null) {
			isPracticeMode = true;
		}
		Boolean isRandomOrder =  false;
		if (request.getParameter("isRandomOrder") != null) {
			isRandomOrder = true;
		}
		Boolean isSinglePage = false;
		if (request.getParameter("isSinglePage") != null) {
			isSinglePage = true;
		}
		Boolean isCorrection = false;
		if (request.getParameter("isCorrection") != null) {
			isCorrection = true;
		}
		System.out.println(isCorrection);
		myquiz.edit(quizDescription, tag, isPracticeMode, isRandomOrder, isSinglePage, isCorrection, myquiz.getQuestionList(), category);
		myquiz.updateDatabase();
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditQuestionSummary.jsp?quizID" + myquiz.getId());
		dispatcher.forward(request, response);
	}

}
