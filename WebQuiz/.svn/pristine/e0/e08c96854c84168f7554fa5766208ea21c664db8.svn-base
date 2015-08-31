package admin;

import java.sql.*;
import java.util.ArrayList;
import database.DBConnection;

public class Announce {
	public String creator;
	public String datetime;
	public String content;
	
	public static ArrayList<Announce> getRecentAnnounce(int num){
		ArrayList<Announce> announce_list = new ArrayList<Announce>();
		Connection con = DBConnection.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+database.TableNames.ANNOUNCE_TB+" ORDER BY date DESC");
			int count = 0;
			rs.beforeFirst();
			while (rs.next() && (num == -1 || count < num)) {
				count++;
				Announce announce = new Announce();
				announce.creator = rs.getString(1);
				announce.datetime = rs.getString(2);
				announce.content = rs.getString(3);
				announce_list.add(announce);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return announce_list;
	}
}
