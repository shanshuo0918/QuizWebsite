package Model;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.lang.Boolean;
import java.text.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import database.DBConnection;
import database.MyDBInfo;

public class FriendRequestModel {
	public String fromuser_;
	public String touser_;
	
	public FriendRequestModel(String username, String to_) {
		super();
		this.fromuser_ = username;
		this.touser_ = to_;
	}
	
	//for getting requestlist
	public FriendRequestModel(String current_user){
		super();
		this.fromuser_ = current_user;
		this.touser_ = "";
	}
	
	public void DeleteFriendRequest(){
		String query = "DELETE FROM friend_request WHERE fromuser=\"" + this.fromuser_ + "\" AND touser=\"" + this.touser_ + "\";";
		String query_2 = "DELETE FROM friend_request WHERE fromuser=\"" + this.touser_ + "\" AND touser=\"" + this.fromuser_ + "\";";
		
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.executeUpdate(query_2);
			//System.out.println("PeiChen: " + query);
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	private ArrayList<String> getfriendlist(String username){
		ArrayList<String> friendlist = new ArrayList<String>();
		String query = "SELECT * FROM friend_list WHERE User = \"" + username + "\";";
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			if(result.next()){
				String friendnamelist = result.getString(2);
				int friendNUM = result.getInt(3);
				int index = 0;
				for(int j = 0; j < friendNUM; j++){
					final StringBuilder sb = new StringBuilder(friendnamelist.length());
				    for(int i = index; i < friendnamelist.length(); i++){
				        final char c = friendnamelist.charAt(i);
				        if(c == ','){
				        	index = i+1; break;
				        }
				        else sb.append(c);
				    }
				    String name = sb.toString();
		        	friendlist.add(name);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendlist;
	}
	public void DeleteFriend(String User_, String deleted){
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ArrayList<String> List = getfriendlist(User_);
			int num = List.size();
			if(num==1){
				String query = "DELETE FROM friend_list WHERE User = \"" + User_ + "\";";
				stmt.executeUpdate(query);
			} else{
				int i = 0;
				boolean check_friend = false;
				for(String s : List){
					if(deleted.equals(s)){
						List.remove(i);
						check_friend = true;
				        break;
					}
					i++;
				}
				if(check_friend==false) return;
				String new_string = "";
				for(String s : List){
					new_string = new_string + s + ",";
				}
				String query_1 = "UPDATE friend_list SET friendList=\"" + new_string + "\" WHERE User=\"" + User_ + "\";"; 
				String query_2 = "UPDATE friend_list SET number=" + (num-1) + " WHERE User=\"" + User_ + "\";";
				stmt.executeUpdate(query_1);
				stmt.executeUpdate(query_2);
			}
			
			ArrayList<String> List2 = getfriendlist(this.touser_);
			int num2 = List2.size();
			if(num2==1){
				String query = "DELETE FROM friend_list WHERE User = \"" + this.touser_ + "\";";
				stmt.executeUpdate(query);
			} else{
				int i2 = 0;
				boolean check_friend2 = false;
				for(String s : List){
					if(this.fromuser_.equals(s)){
						List.remove(i2);
						check_friend2 = true;
				        break;
					}
					i2++;
				}
				if(check_friend2==false) return;
				String new_string2 = "";
				for(String s : List){
					new_string2 = new_string2 + s + ",";
				}
				String query_1_ = "UPDATE friend_list SET friendList=\"" + new_string2 + "\" WHERE User=\"" + this.touser_ + "\";"; 
				String query_2_ = "UPDATE friend_list SET number=" + (num2-1) + " WHERE User=\"" + this.touser_ + "\";";
				stmt.executeUpdate(query_1_);
				stmt.executeUpdate(query_2_);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void AddFriendRequestDB(){
		String query = "SELECT * FROM friend_request WHERE fromuser = \"" + this.fromuser_ + "\" AND " + "touser = \"" + this.touser_ +"\";";
		//System.out.println(query);
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			if(result.next()) return;
			else{
				String query_ = "INSERT INTO friend_request " + "VALUES(\"" + fromuser_ + "\", \"" + touser_ + "\");";
				//System.out.println(query_);
				stmt.executeUpdate(query_);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> GetRequestList(String username){
		ArrayList<String> friendREQUESTlist = new ArrayList<String>();
		String query = "SELECT * FROM friend_request WHERE touser = \"" + username + "\";";
		//System.out.println("PeiChen: " + query);
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next()){
				String friendname = result.getString(1);
				friendREQUESTlist.add(friendname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendREQUESTlist;
	}
}
