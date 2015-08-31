package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.*;

public class UserQuizTake {
	public String quizName;
	public String quizID;
	public String date;
	public int score;
	public int timeUsed;
	
	public UserQuizTake(String quizID, String quizName, String date, String score, String timeUsed){
		this.quizName = quizName;
		this.quizID = quizID;
		this.date = date;
		this.score = Integer.parseInt(score);
		this.timeUsed = Integer.parseInt(timeUsed);
	}
	
	public static ArrayList<UserQuizTake> quizTakeHist(String username, String index, int order, int num){
		ArrayList<UserQuizTake> histList = new ArrayList<UserQuizTake>();
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String orderFlag = (order == -1)? "":" desc";
			String limitFlag = (num == -1)? "":(" limit "+num);
			String selectedColumn = "quizID, quizName, submitTime, score, timeUsed";
			ResultSet rs = stmt.executeQuery("select "+selectedColumn+
											 " from "+TableNames.QUIZ_HIST_TB+
											 " left join "+TableNames.QUIZ_SUM_TB+
											 " using (quizID) where username = \""+username+"\""+
											 " order by "+toIndex(index)+orderFlag+limitFlag
											);
			rs.beforeFirst();
			while(rs.next()){
				UserQuizTake hist = new UserQuizTake(rs.getString(1),
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
	
	private static String toIndex(String idFlag){
		if(idFlag.equals("da"))
			return "submitTime";
		else if(idFlag.equals("qn"))
			return "quizName";
		else if(idFlag.equals("sc"))
			return "score";
		else if(idFlag.equals("tu"))
			return "timeUsed";
		else
			return "";
	}
}
