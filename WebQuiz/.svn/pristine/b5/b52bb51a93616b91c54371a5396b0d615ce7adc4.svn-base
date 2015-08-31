package admin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

import database.DBConnection;

public class Admin {
	public Admin(){
		
	}
	
	public static class HomepageQuiz{
		public String quizID;
		public String quizName;
		public float rating;
		public String createTime;
	}
	
	public static class QuizHist{
		public String quizID;
		public String quizName;
		public String username;
		public String submitTime;
		public int timeUsed;
		public int score;
	}
	
	/**
	 * 
	 * @param username creator of quiz
	 * @param num how many quizzes to display
	 * @param type 0: recent quiz; 1: popular quiz; 2: quiz created by username
	 * @return
	 */
	public static ArrayList<HomepageQuiz> getHomepageQuiz(String username, int num, int type){
		ArrayList<HomepageQuiz> quizList = new ArrayList<HomepageQuiz>();
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			String queryPrefix = "SELECT quizID, quizName, avgRating, createTime FROM ";
			switch(type){
			case 0:	rs = stmt.executeQuery(queryPrefix+database.TableNames.QUIZ_SUM_TB+
						" ORDER BY createTime desc");
					break;
			case 1:	rs = stmt.executeQuery(queryPrefix+database.TableNames.QUIZ_SUM_TB+
						" ORDER BY avgRating desc");
					break;
			case 2: String s = new String(queryPrefix+database.TableNames.QUIZ_SUM_TB
					+" where creator = \""+username+"\" order by createTime desc");
					rs = stmt.executeQuery(s);
					break;
			default:
				; // deal with error inputs
			}

			int count = 0;
			rs.beforeFirst();
			while (rs.next() && (count < num)) {
				count++;
				HomepageQuiz quiz = new HomepageQuiz();
				quiz.quizID = rs.getString(1);
				quiz.quizName = rs.getString(2);
				quiz.rating = Float.parseFloat(rs.getString(3));
				quiz.createTime = rs.getString(4);
				quizList.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizList;
	}
	
	
	public static ArrayList<QuizHist> getQuizTaking(String username, int num){
		ArrayList<QuizHist> quizList = new ArrayList<QuizHist>();
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from "+database.TableNames.QUIZ_HIST_TB+
											" where username = \""+username+"\" order by submitTime desc");
			int count = 0;
			rs.beforeFirst();
			while (rs.next() && (count < num)) {
				count++;
				QuizHist quiz = new QuizHist();
				quiz.quizID = rs.getString(1);
				quiz.username = rs.getString(2);
				quiz.submitTime = rs.getString(3);
				quiz.timeUsed = Integer.parseInt(rs.getString(4));
				quiz.score = Integer.parseInt(rs.getString(5));
				
				ResultSet rs2 = stmt.executeQuery("select quizName from "+database.TableNames.QUIZ_SUM_TB+
												 " where quizID = \""+quiz.quizID+"\"");
				rs2.beforeFirst();
				if(rs2.next())
					quiz.quizName = rs2.getString(1);
				else{
					; // should deal with errors
				}
				
				quizList.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizList;
	}
	
	
	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	private static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/**
	 * return hashcode of s in byte format
	 * @param s
	 * @return
	 */
	public static byte[] wordToHashByte(String s){
		MessageDigest digest = null;
		byte[] bytes = null;
		try {
			digest = MessageDigest.getInstance("SHA");
			digest.reset();
			digest.update(s.getBytes());
			bytes = digest.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
	
	
	public static String wordToHash(String s){
		return hexToString(wordToHashByte(s));
	}
}
