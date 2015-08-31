package Model;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.lang.Boolean;
import java.text.*;

import javax.servlet.http.HttpServletRequest;

import database.DBConnection;
import database.MyDBInfo;

/**
 * @author peichenwu
 *
 */

public class MessageModel {
	public String fromuser;
	public String touser;
	public Timestamp sent_time;
	public String title;
	public boolean read;
	public String content;
	public String emailID_;
	
	private static String stripNonDigits(String input){
	    final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = 0; i < input.length(); i++){
	        final char c = input.charAt(i);
	        if(c > 47 && c < 58) sb.append(c);
	    }
	    return sb.toString();
	}
	
	//Save the composed message to DB
	public MessageModel(String fromuser, String touser, String title, String content, boolean read, Timestamp sent_time) {
		super();
		SimpleDateFormat df = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss.SSS");
		String s = df.format(sent_time);
		this.fromuser = fromuser;
		this.touser = touser;
		this.sent_time = sent_time;
		this.title = title;
		this.read = read;
		this.content = content;
		s = stripNonDigits(s);
		this.emailID_ = s;
	}
	
	//For getting InboxInfo
	public MessageModel(String username){
		super();
		this.fromuser = username;
		this.touser = "";
		this.sent_time = new Timestamp(new java.util.Date().getTime());
		this.title = "";
		this.read = false;
		this.content = "";
		this.emailID_ = "";
	}
	
	//For change read status
	public MessageModel(String ID, String from, String to){
		super();
		this.touser = to;
		this.fromuser = from;
		this.sent_time = new Timestamp(new java.util.Date().getTime());
		this.title = "";
		this.content = "";
		this.read = true;
		this.emailID_ = ID;
	}
	
	public void UpdateMessageDB(){
		String query1 = "INSERT INTO message_inbox " + "VALUES(\"" + fromuser + "\", \"" + touser + "\", \"" + title + "\", \"" + content + "\", " + read + ", \"" + sent_time + "\", \"" + emailID_ + "\");";
		String query2 = "INSERT INTO message_sentbox " + "VALUES(\"" + fromuser + "\", \"" + touser + "\", \"" + title + "\", \"" + content + "\", " + read + ", \"" + sent_time + "\", \"" + emailID_ + "\");";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MessageModel> GetInboxInfo(String username){
		ArrayList<MessageModel> InboxList = new ArrayList<MessageModel>();
		
		String query = "SELECT * FROM message_inbox WHERE touser = \"" + username + "\";";
		
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next()){
				MessageModel eachmail = new MessageModel(username);
				eachmail.fromuser = result.getString(1);
				eachmail.touser = result.getString(2);
				eachmail.title = result.getString(3);
				eachmail.content = result.getString(4);
				eachmail.read = result.getBoolean(5);
				eachmail.sent_time = result.getTimestamp(6);
				eachmail.emailID_ = result.getString(7);
				InboxList.add(eachmail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return InboxList;
	}
	
	public ArrayList<MessageModel> GetSentboxInfo(String username){
		ArrayList<MessageModel> boxList = new ArrayList<MessageModel>();
		
		String query = "SELECT * FROM message_sentbox WHERE fromuser = \"" + username + "\";";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next()){
				MessageModel eachmail = new MessageModel(username);
				eachmail.fromuser = result.getString(1);
				eachmail.touser = result.getString(2);
				eachmail.title = result.getString(3);
				eachmail.content = result.getString(4);
				eachmail.read = result.getBoolean(5);
				eachmail.sent_time = result.getTimestamp(6);
				eachmail.emailID_ = result.getString(7);
				boxList.add(eachmail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boxList;
	}
	
	public void Read_Ornot(){
		String query = "UPDATE message_inbox SET read_ornot = " + this.read + " WHERE touser = \"" + this.touser + "\" AND emailID = \"" + this.emailID_ + "\" AND fromuser = \"" + this.fromuser + "\";";
		
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Read_Ornot_sentbox(){
		String query = "UPDATE message_sentbox SET read_ornot = " + this.read + " WHERE touser = \"" + this.touser + "\" AND emailID = \"" + this.emailID_ + "\" AND fromuser = \"" + this.fromuser + "\";";
		
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String GetContent_inbox(){
		String query = "SELECT * FROM message_inbox WHERE touser = \"" + this.touser + "\" AND emailID = \"" + this.emailID_ + "\" AND fromuser = \"" + this.fromuser + "\";";
		//System.out.println(query);
		String Content_ = "";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			if(result.next()){
				Content_ = result.getString(4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Content_;
	}
	
	public String GetContent_sentbox(){
		String query = "SELECT * FROM message_sentbox WHERE touser = \"" + this.touser + "\" AND emailID = \"" + this.emailID_ + "\" AND fromuser = \"" + this.fromuser + "\";";
		//System.out.println(query);
		String Content_ = "";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			if(result.next()){
				Content_ = result.getString(4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Content_;
	}
	/*
	public String GetSender(){
		String query = "SELECT * FROM message WHERE touser = \"" + this.touser + "\" AND emailID = \"" + this.emailID_ + "\" AND fromuser = \"" + this.fromuser + "\";";
		//System.out.println(query);
		String from_ = "";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			if(result.next()){
				from_ = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return from_;
	}
	*/
	public void DeleteMail_inbox(){
		String query = "DELETE FROM message_inbox WHERE touser = \"" + this.touser + "\" AND emailID = \"" + this.emailID_ + "\" AND fromuser = \"" + this.fromuser + "\";";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteMail_sentbox(){
		String query = "DELETE FROM message_sentbox WHERE touser = \"" + this.touser + "\" AND emailID = \"" + this.emailID_ + "\" AND fromuser = \"" + this.fromuser + "\";";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM quiz_history WHERE quizID = \"" + quizID + "\"" +
			" and username = \"" + username + "\"" + " and submitTime = \"" + submitTime + "\"");
			rs.next();
			timeUsed = rs.getLong("timeUsed");
			score = rs.getInt("score");
			rs = stmt.executeQuery("SELECT * FROM quiz_rating WHERE quizID = \"" + quizID + "\" and username = \"" + 
			username + "\"");
			rs.next();
			review = rs.getString("review");
			rating = rs.getInt("rating");
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	*/
		/*
		questionTypeTable = getTable(message);
		String query = "SELECT * FROM " + questionTypeTable + " WHERE questionID = \"" + questionID + "\"";
		String tempQuizID = "";
		String tempQuestion = "";
		String tempAnswer = "";
		int tempTimeLimit = 0;
		int tempScore = 0;
		System.out.println(query);
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			rs.next();
			tempQuizID =rs.getString(2);
			tempQuestion = rs.getString(4);
			tempAnswer = rs.getString(5);
			tempTimeLimit = Integer.parseInt(rs.getString(3));
			tempScore = Integer.parseInt(rs.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.quizID = tempQuizID;
		this.question = tempQuestion;
		this.answer = tempAnswer;
		this.timeLimit = tempTimeLimit;
		this.score = tempScore;
		
		String query = "INSERT INTO " + questionTypeTable + " VALUES(\"" + questionID + "\", \"" + quizID +
				"\", \"" + timeLimit + "\", \"" + question + "\", \"" + answer + "\", \"" +  "\", \"" + score + "\");" ;
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} */
}