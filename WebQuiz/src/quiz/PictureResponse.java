package quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import database.DBConnection;

public class PictureResponse extends Question {
	protected final String imageURL;
	
	public PictureResponse(String questionType, String questionID) {
		super(questionType, questionID);
		
		String query = "SELECT * FROM " + questionTypeTable + " WHERE questionID = \"" + questionID + "\"";
		String tempImageURL = "";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			tempImageURL = rs.getString(7);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.imageURL = tempImageURL;
	}
	
	public PictureResponse(String quizID, int timeLimit,
			String question, String answer, int score, String imageURL) {
		super("PR", quizID, timeLimit, question, answer, score);
		this.imageURL = imageURL;
	}
	
	@Override
	public void addQuestion() {
		String query = "INSERT INTO " + questionTypeTable + " VALUES(\"" + questionID + "\", \"" + quizID +
				"\", \"" + timeLimit + "\", \"" + question + "\", \"" + answer + "\", \"" + score + 
				"\", \"" + imageURL + "\");";
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
	public String doQuizPage() {
		StringBuilder page = new StringBuilder();
		page.append("This is a Picture-Response question, please input your answer in "
				+ "the text box after carefully reading the question and looking at the pictrue!");
		page.append("<h4>Question: " + question + "</h4>");
		page.append("<img src=\"" + imageURL + "\" /> <br>");
		page.append("Your Answer: <input type=\"text\" name=\"answer_" + questionID + "\"/><br>");
		return page.toString();
	}
	
	@Override
	public void updateQuestion(HttpServletRequest request) {
		String question = request.getParameter("questionDescription");
		String answer = QuestionHelper.answerFormatter(request.getParameter("answer"));
		String imageURL = request.getParameter("image");
		int timeLimit = 0;
		if (request.getParameter("time") != "") {
			timeLimit = Integer.parseInt(request.getParameter("time"));
		}
		String query = "Update " + questionTypeTable + " SET question = \"" + question + "\", answer = \"" + answer +
				"\", timeLimit = \"" + timeLimit + "\", imageURL = \"" + imageURL + "\" WHERE questionID = \"" + questionID + "\";";
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
		page.append("<h3>You are editing a Picture-Response question!</h3>\n");
		page.append("Question (Please type your question in the textAera below): <br>");
		page.append("<textarea name=\"questionDescription\" rows=\"10\" cols=\"60\" required>" + question + "</textarea><br>");
		page.append("Your image URL (Please paste your image URL here): <br>");
		page.append("<textarea name=\"image\" rows=\"5\" cols=\"60\" required>" + imageURL + "</textarea><br>");
		page.append("Answer (If you have mutiple possible answers, please put them in different line): <br>");
		page.append("<textarea name=\"answer\" rows=\"5\" cols=\"60\" required>" + answerInLines() + "</textarea><br>");
		page.append("Time Limit (If you wish to create a limit time question, please provide the time limit): "
				+ "<input type=\"text\" name=\"time\" value =\"" + timeLimit + "\"><br>");
		return page.toString();
	}
	
}