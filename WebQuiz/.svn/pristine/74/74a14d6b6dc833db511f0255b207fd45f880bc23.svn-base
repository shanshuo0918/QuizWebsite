package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DBConnection;
import database.TableNames;

public class UserAccount {
	public String username;
	public String createtime;
	public String displayname;
	public int privacy_quiz_take;
	public int privacy_quiz_create;
	public int privacy_quiz_score;
	public int privacy_achieve;
	public String achievement;
	public boolean isAdmin; 
	
	public UserAccount(){
		this.username = "NA";
		this.createtime = "NA";
		this.displayname = "NA";
		this.privacy_quiz_take = 0;
		this.privacy_quiz_create = 0;
		this.privacy_quiz_score = 0;
		this.privacy_achieve = 0;
		this.achievement = new String();
		this.isAdmin = false;
	}
	
	public UserAccount(String username,
					   String createtime,
					   String displayname,
					   int privacy_quiz_take,
					   int privacy_quiz_create,
					   int privacy_quiz_score,
					   int privacy_achieve,
					   String achievement,
					   String isAdmin){
		this.username = username;
		this.createtime = createtime;
		this.displayname = displayname;
		this.privacy_quiz_take = privacy_quiz_take;
		this.privacy_quiz_create = privacy_quiz_create;
		this.privacy_quiz_score = privacy_quiz_score;
		this.privacy_achieve = privacy_achieve;
		this.achievement = achievement;
		this.isAdmin = isAdmin.equals("1")? true:false;
	}
	
	public static String getDisplayName(String username){
		String displayName = null;
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select displayname from "+database.TableNames.USERINFO_TB+
											 " where username = \""+username+"\"");
			rs.beforeFirst();
			if(rs.next())
				displayName = rs.getString(1);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return displayName;
	}
	
	
	public static UserAccount getUserInfo(String username){
		UserAccount acc = null;
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from "+database.TableNames.USERINFO_TB+
											 " where username = \""+username+"\"");
			rs.beforeFirst();
			if(rs.next()){
				acc = new UserAccount(rs.getString(1),
									  rs.getString(3),
									  rs.getString(4),
									  Integer.parseInt(rs.getString(5)),
									  Integer.parseInt(rs.getString(6)),
									  Integer.parseInt(rs.getString(7)),
									  Integer.parseInt(rs.getString(8)),
									  rs.getString(9),
									  rs.getString(10)
									 );
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acc;
	}
	
	public static ArrayList<UserAccount> searchUserInfo(String key, boolean exactMatch){
		ArrayList<UserAccount> userList = new ArrayList<UserAccount>();
		try {
			Connection con = DBConnection.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			String searchCrit = (exactMatch)? " = \""+key+"\"" : " like \"%"+key+"%\"";
			String selectedColumn = "*";
			ResultSet rs = stmt.executeQuery("select "+selectedColumn+
											 " from "+TableNames.USERINFO_TB+
											 " where username"+searchCrit+
											 " or displayname"+searchCrit+
											 " order by username"
											);
			rs.beforeFirst();
			while(rs.next()){
				UserAccount acc = new UserAccount(rs.getString(1),
												  rs.getString(3),
												  rs.getString(4),
												  Integer.parseInt(rs.getString(5)),
												  Integer.parseInt(rs.getString(6)),
												  Integer.parseInt(rs.getString(7)),
												  Integer.parseInt(rs.getString(8)),
												  rs.getString(9),
												  rs.getString(10)
												 );
				userList.add(acc);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	
}
