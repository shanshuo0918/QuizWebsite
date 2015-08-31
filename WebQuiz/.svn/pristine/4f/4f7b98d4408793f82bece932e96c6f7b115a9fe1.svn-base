package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DBConnection;
import quiz.Question;
import quiz.Quiz;

public class Helper {
	public static String generateTags(List<String> tags) {
		StringBuilder s = new StringBuilder();
		for (String t : tags) {
			s.append("#" + t);
		}
		return s.toString();
	}

	public static List<String> parseTags(String tagString) {
		String[] splits = tagString.split("#");
		List<String> list = new ArrayList<String>(splits.length);
		for (int i = 0; i < splits.length; i++)
			if (!splits[i].equals(""))
				list.add(splits[i]);
		return list;
	}
	
	public static String generateQuestionType(List<Question> questionList) {
		StringBuilder s = new StringBuilder();
		for (Question t : questionList) {
			s.append("," + t.getType());
		}
		return s.toString().substring(1);
	}
	
	public static List<String> parseQuestionType(String questionType) {
		String[] splits = questionType.split(",");
		List<String> list = new ArrayList<String>(splits.length);
		for (int i = 0; i < splits.length; i++)
			if (!splits[i].equals(""))
				list.add(splits[i]);
		return list;
	}
	
	public static String generateQuestionId(List<Question> questionList) {
		StringBuilder s = new StringBuilder();
		for (Question t : questionList) {
			s.append("," + t.getId());
		}
		return s.toString().substring(1);
	}
	
	public static List<String> parseQuestionId(String questionId) {
		String[] splits = questionId.split(",");
		List<String> list = new ArrayList<String>(splits.length);
		for (int i = 0; i < splits.length; i++)
			if (!splits[i].equals(""))
				list.add(splits[i]);
		return list;
	}
	
	public static String getMD5ForTime() {
		String timeString = "" + new Date().getTime();
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(timeString.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void addAchievement(String username, String achievement) {
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			String prevAchievement;
			ResultSet rs = stmt.executeQuery("SELECT * FROM userinfo WHERE username =\"" + username + "\"");
			rs.next();
			if(rs.getString("achievement") != null){
				prevAchievement = rs.getString("achievement");
				prevAchievement = prevAchievement + "#" + achievement;
				String update = "UPDATE userinfo SET achievement = \"" + prevAchievement + 
						"\" WHERE username =\"" + username + "\"";
				stmt.executeUpdate(update);
			}else{
				prevAchievement = "#" + achievement;
				String update = "UPDATE userinfo SET achievement = \"" + prevAchievement + 
						"\" WHERE username =\"" + username + "\"";
				stmt.executeUpdate(update);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> parseAchievements(String achievementString) {
		String[] splits = achievementString.split("#");
		List<String> list = new ArrayList<String>(splits.length);
		for (int i = 0; i < splits.length; i++)
			if (!splits[i].equals(""))
				list.add(splits[i]);
		return list;
	}
	
	public static boolean existQuizName(String newName) {
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM quiz_summary WHERE quizName =\"" + newName + "\"");
			if (rs.isBeforeFirst()) {
				return true;
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * 
	 * @param timeUsed milisecond
	 * @return
	 */
	public static String formattedTime(int timeUsed){
		int t = timeUsed/1000;
		int sec = t % 60;
		int min = (t/60) % 60;
		int hr = (t/3600) % 60;
		String s = new String();
		if(hr > 0)
			s = s+hr+"h:";
		if(min > 0 || hr > 0)
			s = s+min+"m:";
		s = s+sec+"s";
		return s;
	}

}
