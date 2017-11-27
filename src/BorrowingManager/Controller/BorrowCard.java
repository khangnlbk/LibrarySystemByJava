package BorrowingManager.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Main.DBConnect;



/**
 * Enity của BorrowCard. Truy xuất dữ liệu.
 * @author anonymous588
 *
 */
public class BorrowCard {
	private Connection conn = DBConnect.getConnection();
	private PreparedStatement ps;
	
	private String getBorrowCard = "select * from borrowcard where user_id=?";
	
	public ResultSet getBorrowCard(String user_id) throws SQLException {
		ps = conn.prepareStatement(getBorrowCard);
		ps.setString(1, user_id);
		return ps.executeQuery();				
	}
	
	public String checkCode(String code) throws SQLException {
		String check = "select card_id from borrowcard where activate_code=?";
		ps = conn.prepareStatement(check);
		ps.setString(1, code);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getString(rs.findColumn("card_id"));
		}
		return null;
	}
	
	public void activateCard(String user_id, String card_id) throws SQLException {
		String query = "update borrowcard set user_id=?, expired_date=? where card_id=?";
		
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DAY_OF_MONTH, 60);
		String expired_date = new SimpleDateFormat("yyyy-MM-dd").format(today.getTime());
		
		ps = conn.prepareStatement(query);
		ps.setString(1, user_id);
		ps.setString(2, expired_date);
		ps.setString(3, card_id);
		ps.executeUpdate();
		
	}
	
}
