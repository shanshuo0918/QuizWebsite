package quiz;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import database.DBConnection;

public class FillInBlank extends Question {
	public FillInBlank(String questionType, String questionID) {
		super(questionType, questionID);
	}
	
	public FillInBlank(String quizID, int timeLimit,
			String question, String answer, int score) {
		super("FIB", quizID, timeLimit, question, answer, score);
	}
	
	@Override
	public String doQuizPage() {
		StringBuilder page = new StringBuilder();
		page.append("This is a Fill-In-Blank question, please input your answer in the text box!");
		page.append("<h4>Question: " + getFirstPartQuestion() + "<input type=\"text\" name=\"answer_" + questionID + "\"/>" 
				+ getSecondPartQuestion() + "</h4>");		
		return page.toString();
	}
	
	@Override
	public void updateQuestion(HttpServletRequest request) {
		String question1 = request.getParameter("question1");
		String question2 = request.getParameter("question2");
		String question = question1 + "|" + question2;
		String answer = QuestionHelper.answerFormatter(request.getParameter("answer"));
		int timeLimit = 0;
		if (request.getParameter("time") != "") {
			timeLimit = Integer.parseInt(request.getParameter("time"));
		}
		String query = "Update " + questionTypeTable + " SET question = \"" + question + "\", answer = \"" + answer +
				"\", timeLimit = \"" + timeLimit + "\" WHERE questionID = \"" + questionID + "\";";
		System.out.println(query);
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String editQuestionPage() {
		StringBuilder page = new StringBuilder();
		page.append("<h3>You are editing a Fill-In-Blank question!</h3>\n");
		page.append("Question (Please seperate your question in two textAera below): <br>");
		page.append("<textarea name=\"question1\" rows=\"5\" cols=\"60\" required>" + getFirstPartQuestion() + "</textarea><br>");
		page.append("<textarea name=\"question2\" rows=\"5\" cols=\"60\" required>" + getSecondPartQuestion() + "</textarea><br>");
		page.append("Answer (If you have mutiple possible answers, please put them in different lines): <br>");
		page.append("<textarea name=\"answer\" rows=\"5\" cols=\"60\" required>" +  answerInLines() + "</textarea><br>");
		page.append("Time Limit (If you wish to create a limit time question, please provide the time limit): "
				+ "<input type=\"text\" name=\"time\" value =\"" + timeLimit + "\"><br>");
		return page.toString();
	}
	
	private String getFirstPartQuestion() {
		for (int i = 0; i < question.length(); i ++) {
			if (question.charAt(i) == '|') {
				return question.substring(0, i);
			}
		}
		return "";
	}
	
	private String getSecondPartQuestion() {
		for (int i = 0; i < question.length(); i ++) {
			if (question.charAt(i) == '|') {
				return question.substring(i + 1);
			}
		}
		return "";
	}
	
}