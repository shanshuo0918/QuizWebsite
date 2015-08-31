package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.*;

public class UserQuizCreate {
	public String quizID;
	public String quizName;
	public String date;
	public int numTaken;
	public float rating;
	
	public UserQuizCreate(String quizID, String quizName, String date, String numTaken, String rating){
		this.quizID = quizID;
		this.quizName = quizName;
		this.date = date;
		this.numTaken = Integer.parseInt(numTaken);
		this.rating = Float.parseFloat(rating);
	}
	
	/**
	 * return the first num items of user's quiz create history
	 * sorted by shortInd. No privacy checking 
	 * @param username
	 * @param shortInd
	 * @param order
	 * @param num
	 * @return
	 */
	public static ArrayList<UserQuizCreate> quizCreateHist(String username, String shortInd, int order, int num){
		ArrayList<UserQuizCreate> histList = new ArrayList<UserQuizCreate>();
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String orderFlag = (order == -1)? "":" desc";
			String limitFlag = (num == -1)? "":(" limit "+num);
			String selectedColumn = "quizID, quizName, createTime, numTaken, avgRating";
			ResultSet rs = stmt.executeQuery("select "+selectedColumn+
											 " from "+TableNames.QUIZ_SUM_TB+
											 " where creator = \""+username+"\""+
											 " order by "+toIndex(shortInd)+orderFlag+limitFlag
											);
			rs.beforeFirst();
			while(rs.next()){
				UserQuizCreate hist = new UserQuizCreate(rs.getString(1),
													 rs.getString(2),
													 rs.getString(3),
													 rs.getString(4),
													 rs.getString(5)
													);
				histList.add(hist);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return histList;
	}
	
	
	private static String toIndex(String shortInd){
		if(shortInd.equals("da"))
			return "createTime";
		else if(shortInd.equals("qn"))
			return "quizName";
		else if(shortInd.equals("nt"))
			return "numTaken";
		else if(shortInd.equals("ra"))
			return "avgRating";
		else
			return "";
	}
	
	
	
	public static ArrayList<UserQuizCreate> searchQuiz(String key, boolean exactMatch){
		ArrayList<UserQuizCreate> quizList = new ArrayList<UserQuizCreate>();
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String searchCrit = (exactMatch)? " = \""+key+"\"" : " like \"%"+key+"%\"";
			String selectedColumn = "quizID, quizName, createTime, numTaken, avgRating";
			ResultSet rs = stmt.executeQuery("select "+selectedColumn+
											 " from "+TableNames.QUIZ_SUM_TB+
											 " where quizName"+searchCrit+
											 " or tag"+searchCrit+
											 " order by quizName"
											);
			rs.beforeFirst();
			while(rs.next()){
				UserQuizCreate quiz = new UserQuizCreate(rs.getString(1),
													 rs.getString(2),
													 rs.getString(3),
													 rs.getString(4),
													 rs.getString(5)
													);
				quizList.add(quiz);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quizList;
	}
}
