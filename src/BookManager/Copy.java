package BookManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Main.DBConnect;

/**
 * Enity của Copy. Truy xuất dữ liệu.
 * @author anonymous588
 *
 */
public class Copy {
	
	public Copy() {}
	
	public Connection conn = DBConnect.getConnection();
	public PreparedStatement ps;
	
	public ResultSet getCopy(String book_id) throws SQLException {
		String query = "Select * from copy where book_id=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, book_id);
		return ps.executeQuery();
	}
	
	public int getCountOfCopy(String book_id) throws SQLException {
		String query = "Select COUNT(book_id) from copy where book_id=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, book_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String count = rs.getString(1);
		return Integer.parseInt(count);
	}
	
	public void addCopy(String book_id, String copy_number, String status, String price, String condition, String copy_type) throws SQLException {
		String query = "insert into copy (book_id, copy_number, status, price, copy_condition, copy_type) values (?,?,?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setString(1, book_id);
		ps.setString(2, copy_number);
		ps.setString(3, status);
		ps.setString(4, price);
		ps.setString(5, condition);
		ps.setString(6, copy_type);
		ps.executeUpdate();
	}
	
	public boolean isBorrowable(String book_id) throws SQLException {
		String query = "Select COUNT(book_id) from copy where book_id=? and status='available' and copy_type='borrowable'";
		ps = conn.prepareStatement(query);
		ps.setString(1, book_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String count = rs.getString(1);
		if(Integer.parseInt(count) > 0) return true;
		return false;
	}
	
	public String getCopyID(String book_id) throws SQLException {
		String query = "Select * from copy where book_id=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, book_id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getString(rs.findColumn("copy_id"));
		}
		return null;
	}
	
	public void updateCopyStatus(String copy_id, String status) throws SQLException {
		System.out.println("updating status: copyID:" + "copy_id" +"," +"status: "+status);
		String query = "update copy set status=? where copy_id=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, status);
		ps.setString(2, copy_id);
		int result = ps.executeUpdate();
	}
	
	public void updateCopyNumber(String book_id, String new_book_number) throws SQLException {
		String query = "update copy set copy_number=? where book_id=? and copy_number NOT like ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(2, book_id);
		statement.setString(3, new_book_number + "%");
		int count = getCountOfCopy(book_id);
		for(int i = 1; i<= count; i++) {
			statement.setString(1, new_book_number + "-" + i);
			System.out.println(new_book_number + "-" + i);
			statement.executeUpdate();
		}
	}
	
	public void updateCopy(String copy_id, String status, String price, String copy_condition, String copy_type) throws SQLException {
		System.out.println("update copy_id: " + copy_id);
		String query = "update copy set status=?, price=?, copy_condition=?, copy_type=? where copy_id=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, status);
		ps.setString(2, price);
		ps.setString(3, copy_condition);
		ps.setString(4, copy_type);
		ps.setString(5, copy_id);
		ps.executeUpdate();
	}
}
