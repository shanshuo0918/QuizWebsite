/**
 * 
 */
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
public class ChallengeModel {
	public String user;
	public String requested_user;
	public String QUIZ_ID;
	
	//For getting number of challenge
	public ChallengeModel(String username){
		super();
		this.user = username;
		this.requested_user = "";
		this.QUIZ_ID = "";
	}
	
	//For updating database or deleting database
	public ChallengeModel(String username, String reciever, String quizid){
		super();
		this.user = username;
		this.requested_user = reciever;
		this.QUIZ_ID = quizid;
	}
	
	public boolean ontalbeornot(){
		String query = "SELECT * FROM challenge WHERE fromuser = \"" + this.user + "\" AND requested_user=\"" + this.requested_user + "\" AND quizID= \"" + this.QUIZ_ID + "\";";
		try{
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		if(result.next()) return true;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public void UpdateChallengeDB(){
		String query = "INSERT INTO challenge " + "VALUES(\"" + user + "\", \"" + requested_user + "\", \"" + QUIZ_ID + "\");";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteChallenge(){
		String query = "DELETE FROM challenge WHERE fromuser = \"" + this.user+ "\" AND requested_user = \"" + this.requested_user + "\" AND quizID = \"" + this.QUIZ_ID + "\";";
		//System.out.println(query);
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Integer> getBestScoreList(ArrayList<ChallengeModel> ch_list){
		ArrayList<Integer> best_score_list = new ArrayList<Integer>();
		try{
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
	        for (ChallengeModel eachchallenge : ch_list) {
	        	//String quizname = eachchallenge.GetquizName(eachchallenge.QUIZ_ID);
	            //out.print(eachchallenge.user);
	        	String user_name = eachchallenge.user;
	        	String quizid = eachchallenge.QUIZ_ID;
	        	String query = "SELECT * FROM quiz_history WHERE quizID = \"" + quizid + "\" AND username=\"" + user_name + "\";";
	        	ResultSet result = stmt.executeQuery(query);
	        	int score_max = 0;
	        	while(result.next()){
	        		if(result.getInt(5) > score_max){
	        			score_max = result.getInt(5);
	        		}
	        	}
	        	best_score_list.add(score_max);
	        }
		} catch(SQLException e){
			e.printStackTrace();
		}
		return best_score_list;
	}
	
	
	public ArrayList<ChallengeModel> GetChallengeList(String username, int method){
		ArrayList<ChallengeModel> ChallengeList = new ArrayList<ChallengeModel>();
		String query = "";
		if(method==1)
			query = "SELECT * FROM challenge WHERE fromuser = \"" + username + "\";";
		else if(method==2)
			query = "SELECT * FROM challenge WHERE requested_user = \"" + username + "\";";
		
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next()){
				ChallengeModel eachC = new ChallengeModel(username);
				eachC.user = result.getString(1);
				eachC.requested_user = result.getString(2);
				eachC.QUIZ_ID = result.getString(3);
				ChallengeList.add(eachC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ChallengeList;
	}

	public String GetquizName(String quizid){
		String query = "SELECT * FROM quiz_summary WHERE quizID = \"" + quizid + "\";";
		String QuizName = "";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			if(result.next()){
				QuizName = result.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return QuizName;
	}
}
