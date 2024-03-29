package quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import database.DBConnection;

public class QuizHelper {
	
	public static final List<String> categories;
	static {
		categories = new ArrayList<String>();
		categories.add("Others");
		categories.add("Math");
		categories.add("Science");
		categories.add("History");
		categories.add("Physics");
		categories.add("Chemistry");
		categories.add("Biology");
		categories.add("Art");
	}
	
	public static List<MyQuiz> searchQuizByCategory(String category) {
		List<MyQuiz> list = new ArrayList<MyQuiz>();
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT quizName FROM quiz_summary WHERE category = \"" + category + "\";";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String quizName = rs.getString("quizName");
				list.add(new MyQuiz(quizName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	public QuizHelper() {
		
	}
	
	public Quiz getQuiz(String quizName) {
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT quizName FROM quiz_summary"
							+ " WHERE quizName = \"" + quizName + "\"");
			if(rs.isBeforeFirst())
				return new MyQuiz(quizName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<MyQuiz> getAllQuiz() {
		List<MyQuiz> list = new ArrayList<MyQuiz>();
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT quizName FROM quiz_summary");
			while (rs.next()) {
				String quizName = rs.getString("quizName");
				list.add(new MyQuiz(quizName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Quiz> getPopularQuiz(int num) {
		List<Quiz> list = new ArrayList<Quiz>();
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT quizName FROM quiz_summary ORDER BY rating DESC " 
			+ "LIMIT 0," + num);
			while (rs.next()) {
				String quizName = rs.getString("quizName");
				list.add(new MyQuiz(quizName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Quiz> getRecentCreatedQuiz(int num) {
		List<Quiz> list = new ArrayList<Quiz>();
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT quizName FROM quiz_summary"
							+ " ORDER BY createTime DESC " + "LIMIT 0,"
							+ num);
			while (rs.next()) {
				String quizName = rs.getString("quizName");
				list.add(new MyQuiz(quizName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//get a specific user's recently taken quizzes, with a num limit
	public List<Quiz> getUserRecentTakenQuiz(String username, int num) {
		List<Quiz> list = new ArrayList<Quiz>();
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT quizID FROM quiz_history WHERE username =\"" + username + 
							"\" ORDER BY submitTime DESC " + "LIMIT 0," + num);
			while (rs.next()) {
				String quizID = rs.getString("quizID");
				list.add(new MyQuiz(quizID, 1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//get a specific user's recently created quizzes, with a num limit
		public List<Quiz> getUserRecentCreatedQuiz(String username, int num) {
			List<Quiz> list = new ArrayList<Quiz>();
			Connection con = DBConnection.getConnection();
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT quizName FROM quiz_summary WHERE creator =\"" + username + 
								"\" ORDER BY createTime DESC " + "LIMIT 0," + num);
				while (rs.next()) {
					String quizName = rs.getString("quizName");
					list.add(new MyQuiz(quizName));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
}
