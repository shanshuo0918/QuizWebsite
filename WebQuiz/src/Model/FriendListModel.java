package Model;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.lang.Boolean;
import java.text.*;

import javax.servlet.http.HttpServletRequest;

import database.DBConnection;
import database.MyDBInfo;

public class FriendListModel {
	public String User;
	public String FriendList;
	public int number;
	
	//For get friend list
	public FriendListModel(String username) {
		super();
		this.User = username;
		this.FriendList = "";
		this.number = 0;
	}
	
	
	public void AddFriend(String addedfriend){
		String query = "SELECT * FROM friend_list WHERE User = \"" + this.User + "\";";
		//System.out.println(query);
		
		try{
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			if(result.next()){
				String namelist = result.getString(2);
				int num = result.getInt(3);
				String adding = namelist + addedfriend + ",";
				String query_ = "UPDATE friend_list SET friendList=\"" + adding + "\" WHERE User=\"" + this.User + "\";";
				System.out.println(query_);
				String query_2 = "UPDATE friend_list SET number=" + (num+1) + " WHERE User=\"" + this.User + "\";";
				System.out.println(query_2);
				stmt.executeUpdate(query_);
				stmt.executeUpdate(query_2);
			}else{
				String query_3 = "INSERT INTO friend_list " + "VALUES(\"" + this.User + "\", \"" + addedfriend + ",\", " + 1 + ");";
				System.out.println(query_3);
				stmt.executeUpdate(query_3);
			}
			//String query_4 = "DELETE FROM friend_request WHERE fromuser = \"" + this.User + "\" AND touser = \"" + addedfriend + "\";";
			//stmt.executeUpdate(query_4);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> GetFriendList(){
		ArrayList<String> friendlist = new ArrayList<String>();
		String query = "SELECT * FROM friend_list WHERE User = \"" + this.User + "\";";
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
}
