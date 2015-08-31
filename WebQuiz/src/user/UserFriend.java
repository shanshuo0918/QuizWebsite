package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import admin.SearchUser;
import database.DBConnection;
import database.TableNames;

public class UserFriend {
	
	public static ArrayList<String> getFriendList(String username){
		ArrayList<String> friendList = new ArrayList<String>();
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String selectedColumn = "friendList";
			ResultSet rs = stmt.executeQuery("select "+selectedColumn+
											 " from "+TableNames.FRIEND_LIST_TB+
											 " where User = \""+username+"\""
											);
			rs.beforeFirst();
			if(rs.next()){
				String friendListStr =rs.getString(1);
				String[] splits = friendListStr.split(",");
				for(String user: splits){
					friendList.add(user);
				}
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return friendList;
	}
	
	
	public static ArrayList<SearchUser> friendRecentAct(String username, int num){
		ArrayList<SearchUser> recentActs = new ArrayList<SearchUser>();
		ArrayList<String> friendList = getFriendList(username);
		for(String friend: friendList){
			ArrayList<SearchUser> acts = SearchUser.singleUserRecentAct(friend,username,num);
			if(acts.size() > 0 && acts.get(0).quizID.length() > 0){
				recentActs.addAll(acts);
			}
		}
		Collections.sort(recentActs, new SearchUser.SortSearchUser());
		
		if(recentActs.size() <= num){
			return recentActs;
		}else{
			ArrayList<SearchUser> newRecentActs = new ArrayList<SearchUser>();
			for(int i = 0; i < num ;i++ ){
				newRecentActs.add(recentActs.get(i));
			}
			return newRecentActs;
		}
//		else
//			return (ArrayList<SearchUser>) recentActs.subList(0,num);
	}
	
	
	public static boolean getIsFriend(String user1, String user2){
		ArrayList<String> friendList = getFriendList(user1);
		for(String user: friendList){
			if(user2.equals(user))
				return true;
		}
		return false;
	}
	
	public static boolean hasSentRequest(String fromuser, String touser){
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String selectedColumn = "*";
			ResultSet rs = stmt.executeQuery("select "+selectedColumn+
											 " from "+TableNames.FRIEND_REQ_TB+
											 " where fromuser = \""+fromuser+"\""+
											 " and touser = \""+touser+"\""
											);
			rs.beforeFirst();
			if(rs.next()){
				return true;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static int getFriendship(String visitor, String pageowner){
		int friendship;
		if(visitor.equals(pageowner))
			friendship = 0; // same user, visit own page
		else if(UserFriend.getIsFriend(visitor,pageowner))
			friendship = 1; // visitor is owner's friend
		else if(!visitor.equals("guest"))
			friendship = 2; // visitor is not owner's friend but is a user
		else
			friendship = 3; // visitor is a guest
		return friendship;
	}
}
