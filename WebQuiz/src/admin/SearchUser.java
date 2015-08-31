package admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

import user.UserAccount;
import user.UserFriend;
import user.UserQuizCreate;
import user.UserQuizTake;
import database.DBConnection;
import database.TableNames;

public class SearchUser {
	public String username;
	public String displayname;
	public String recentActivity;
	public String date;
	public String quizID;
	
	public SearchUser(String username, String displayname){
		this.username = username;
		this.displayname = displayname;
		this.recentActivity = "";
		this.date = "";
		this.quizID = "";
	}
	
	
	/**
	 * Return a list contains user's most recent act (including take and create),
	 * whose username match the search key
	 * @param key
	 * @param visitor
	 * @param exactMatch
	 * @return
	 */
	public static ArrayList<SearchUser> searchUser(String key, String visitor, boolean exactMatch){
		ArrayList<SearchUser> userList = new ArrayList<SearchUser>();
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String searchCrit = (exactMatch)? " = \""+key+"\"" : " like \"%"+key+"%\"";
			String selectedColumn = "username, displayname";
			ResultSet rs = stmt.executeQuery("select "+selectedColumn+
											 " from "+TableNames.USERINFO_TB+
											 " where username"+searchCrit+
											 " or displayname"+searchCrit+
											 " order by username"
											);
			rs.beforeFirst();
			while(rs.next()){
				UserAccount userinfo = UserAccount.getUserInfo(rs.getString(1));
				ArrayList<SearchUser> recentAct = singleUserRecentAct(userinfo.username, visitor, 1);
				if(recentAct.size() > 0){
					userList.add(recentAct.get(0));
				}else{
					SearchUser newUser = new SearchUser(userinfo.username,userinfo.displayname);
					userList.add(newUser);
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	
	
	public static ArrayList<SearchUser> singleUserRecentAct(String user, String visitor, int num){
		ArrayList<SearchUser> actList = new ArrayList<SearchUser>();
		UserAccount userinfo = UserAccount.getUserInfo(user);
		int friendship = UserFriend.getFriendship(visitor,user);
		boolean canSeeScore = (friendship > userinfo.privacy_quiz_score)? false:true;
		ArrayList<UserQuizTake> quizTake = new ArrayList<UserQuizTake>();
		ArrayList<UserQuizCreate> quizCreate = new ArrayList<UserQuizCreate>();
		
		if(friendship <= userinfo.privacy_quiz_take)  // can access
			quizTake = UserQuizTake.quizTakeHist(user, "da", 1, num);
		for(int i = 0; i < num && i < quizTake.size(); i++){
			SearchUser act = new SearchUser(userinfo.username,userinfo.displayname);
			act.recentActivity = quizTakeString(quizTake.get(i),canSeeScore);
			act.quizID = quizTake.get(i).quizID;
			act.date = quizTake.get(i).date;
			actList.add(act);
		}
		
		if(friendship <= userinfo.privacy_quiz_create)  // can access
			quizCreate = UserQuizCreate.quizCreateHist(user, "da", 1, num);
		for(int i = 0; i < num && i < quizCreate.size(); i++){
			SearchUser act = new SearchUser(userinfo.username,userinfo.displayname);
			act.recentActivity = quizCreateString(quizCreate.get(i));
			act.quizID = quizCreate.get(i).quizID;
			act.date = quizCreate.get(i).date;
			actList.add(act);
		}
		
		if(actList.size() > 0)
			Collections.sort(actList, new SortSearchUser());
		
		return actList;
	}
	
	private static String quizCreateString(UserQuizCreate quiz){
//		String s = new String("created "+quiz.quizName+" on "+quiz.date.split(" ")[0]);
		String s = new String("created "+quiz.quizName+" on "+quiz.date);
		return s;
	}
	
	private static String quizTakeString(UserQuizTake quiz, boolean canSeeScore){
//		String s = new String("took "+quiz.quizName+" on "+quiz.date.split(" ")[0]);
		String s = new String("took "+quiz.quizName+" on "+quiz.date);
		if(canSeeScore)
			s = s + ", score = "+quiz.score;
		return s;
	}
	
	public static class SortSearchUser implements Comparator<SearchUser>{
		public int compare(SearchUser a, SearchUser b) {
			// trick: form the neg/0/pos by subtraction
			if(a.date.equals(b.date))
				return 0;
			Timestamp t1 = Timestamp.valueOf(a.date);
			Timestamp t2 = Timestamp.valueOf(b.date);
			if(t1.after(t2))
				return -1;
			else
				return 1;
		}
	}
}
