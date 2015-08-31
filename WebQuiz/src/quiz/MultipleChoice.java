package quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import database.DBConnection;

public class MultipleChoice extends Question {
	protected final String choices;
	
	public MultipleChoice(String questionType, String questionID) {
		super(questionType, questionID);
		String query = "SELECT * FROM " + questionTypeTable + " WHERE questionID = \"" + questionID + "\"";
		String tempChoices = "";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			tempChoices = rs.getString(7);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.choices = tempChoices;
	}
	
	public MultipleChoice(String quizID, int timeLimit,
			String question, String answer, int score, String choices) {
		super("MC", quizID, timeLimit, question, answer, score);
		this.choices = choices;
	}
	
	@Override
	public String getUserAnswer(HttpServletRequest request) {
		String[] userAnswer = request.getParameterValues("answer_"+ questionID);
		String result = "";
		if (userAnswer == null) {
			return "N.A.";
		}
		result += userAnswer[0];
		return result;
	}
	
	@Override
	public void addQuestion() {
		String query = "INSERT INTO " + questionTypeTable + " VALUES(\"" + questionID + "\", \"" + quizID +
				"\", \"" + timeLimit + "\", \"" + question + "\", \"" + answer + "\", \"" + score + 
				"\", \"" + choices + "\");";
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
		ArrayList<String> choiceList = getChoiceList();
		page.append("This is a Multiple-Choice question, please select one option!");
		page.append("<h4>Question: " + question + "</h4>");
		//page.append("<form>");
		for (int i = 0; i < choiceList.size(); i ++) {
			page.append("<input type=\"radio\" name=\"answer_" + questionID + "\" value=\"" + choiceList.get(i) + "\"/> " + choiceList.get(i) + " <br>");
		}
		//page.append("</form>");
		return page.toString();
	}
	
	@Override
	public void updateQuestion(HttpServletRequest request) {
		String question = request.getParameter("questionDescription");
		String answer = request.getParameter(request.getParameter("options"));
		String options = QuestionHelper.generateOptions(request);
		int timeLimit = 0;
		if (request.getParameter("time") != "") {
			timeLimit = Integer.parseInt(request.getParameter("time"));
		}
		String query = "Update " + questionTypeTable + " SET question = \"" + question + "\", answer = \"" + answer +
				"\", timeLimit = \"" + timeLimit + "\", choices = \"" + options +  "\" WHERE questionID = \"" + questionID + "\";";
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
		page.append("<h3>You are editing a Multiple-Choice question!</h3>\n");
		page.append("Question (Please type your question in the textAera below): <br>");
		page.append("<textarea name=\"questionDescription\" rows=\"10\" cols=\"60\" required>" + question + "</textarea><br>");
		page.append("Answer (Please input any possible answer in the blanks, and select the right answer, you answer should have 5 or less options): <br>");
		
		ArrayList<String> choiceList = getChoiceList();
		for (int i = 1; i <= choiceList.size(); i++) {
			if (answer.equalsIgnoreCase(choiceList.get(i-1))) {
				page.append("<input type=\"radio\" name=\"options\" value=\"option" + i +"\" checked><input type=\"text\" name=\"option" + i +"\" value=\"" + choiceList.get(i-1) +"\"/><br>");
			} else {
				page.append("<input type=\"radio\" name=\"options\" value=\"option" + i +"\"><input type=\"text\" name=\"option" + i +"\" value=\"" + choiceList.get(i-1) +"\"/><br>");	
			}
		}
		for (int i = choiceList.size()+1 ; i <= 5; i++) {
			page.append("<input type=\"radio\" name=\"options\" value=\"option" + i +"\"><input type=\"text\" name=\"option" + i +"\"/><br>");
		}
		page.append("Time Limit (If you wish to create a limit time question, please provide the time limit): "
				+ "<input type=\"text\" name=\"time\" value =\"" + timeLimit + "\"><br>");
		return page.toString();
	}
	
	// Not tested
	protected ArrayList<String> getChoiceList() {
		ArrayList<String> choiceList = new ArrayList<String>();
		int start = 0;
		int end = 0;
		while (end < choices.length() - 1) {
			if (choices.charAt(end) == '|') {
				System.out.println(start);
				System.out.println(end);
				choiceList.add(choices.substring(start, end));
				start = end + 1;
			}
			end ++;
		}
		choiceList.add(choices.substring(start));
		return choiceList;
	}		
}