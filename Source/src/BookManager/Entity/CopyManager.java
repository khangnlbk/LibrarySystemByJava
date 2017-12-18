package BookManager.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Helper.DBConnect;

/**
 * Enity của CopyManager. Truy xuất dữ liệu.
 * @author anonymous588
 *
 */
public class CopyManager {
	
    /**
     *
     */
    public CopyManager() {}
	
    /**
     *
     */
    public Connection conn = DBConnect.getConnection();

    /**
     *
     */
    public PreparedStatement ps;
	
    /**
     *
     * @param book_id
     * @return
     * @throws SQLException
     */
    public ResultSet getCopy(int book_id) throws SQLException {
		String query = "Select * from copy where book_id=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, book_id);
		return ps.executeQuery();
	}
	
    /**
     *
     * @param book_id
     * @return
     * @throws SQLException
     */
    public int getCountOfCopy(int book_id) throws SQLException {
		String query = "Select COUNT(book_id) from copy where book_id=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, book_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
            /**
     * create new copy with the info inside 
     * @throws SQLException 
     * @param book_id so thu tu cua sach
     * @param copy_number ma copy
     * @param status tinh trang ban copy (lent, borrowed, available....)
     * @param condition dieu kien hien tai cua sach
     * @param copy_type dang copy: reference hoac borrowable
     * @param price gia sach
     */
	public void addCopy(int book_id, String copy_number, String status, int price, String condition, String copy_type) throws SQLException {
		String query = "insert into copy (book_id, copy_number, status, price, copy_condition, copy_type) values (?,?,?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, book_id);
		ps.setString(2, copy_number);
		ps.setString(3, status);
		ps.setInt(4, price);
		ps.setString(5, condition);
		ps.setString(6, copy_type);
		ps.executeUpdate();
	}
            /**
     * Kiem tra xem co the muon dc quyen nay hay k
     * @throws SQLException 
     * @param book_id so thu tu sach

     * @return true neu ton tai ban copy borrowable
     */	
	public boolean isBorrowable(int book_id) throws SQLException {
		String query = "Select COUNT(book_id) from copy where book_id=? and status='available'";
		ps = conn.prepareStatement(query);
		ps.setInt(1, book_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		if(rs.getInt(1) > 0) return true;
		return false;
	}
	
    /**
     *
     * @param book_id
     * @return
     * @throws SQLException
     */
    public int getCopyID(int book_id) throws SQLException {
		String query = "Select * from copy where book_id=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, book_id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
                    return rs.getInt(rs.findColumn("copy_id"));
		}
		return -1;
	}
            /**
     * Lay gia tri ban copy co the muon dc
     * @throws SQLException 
     * @param book_id so thu tu cua sach
     * @return true neu ton tai 1 ban copy available va borrowable
     */
	public int getValidCopyID(int book_id) throws SQLException {
		String query = "Select * from copy where book_id=? and status='available'";
		ps = conn.prepareStatement(query);
		ps.setInt(1, book_id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getInt(rs.findColumn("copy_id"));
		}
		return -1;
	}
        
    /**
     *
     * @param copy_id
     * @param status
     * @throws SQLException
     */
    public void updateCopyStatus(int copy_id, String status) throws SQLException {
		System.out.println("updating status: copyID:" + "copy_id" +"," +"status: "+status);
		String query = "update copy set status=? where copy_id=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, status);
		ps.setInt(2, copy_id);
		ps.executeUpdate();
	}
	
    /**
     *
     * @param book_id
     * @param new_book_number
     * @throws SQLException
     */
    public void updateCopyNumber(int book_id, String new_book_number) throws SQLException {
		String query = "update copy set copy_number=? where book_id=? and copy_number NOT like ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(2, book_id);
		statement.setString(3, new_book_number + "%");
		int count = getCountOfCopy(book_id);
		for(int i = 1; i<= count; i++) {
			statement.setString(1, new_book_number + "-" + i);
			System.out.println(new_book_number + "-" + i);
			statement.executeUpdate();
		}
	}
	
    /**
     *
     * @param copy_id
     * @param status
     * @param price
     * @param copy_condition
     * @param copy_type
     * @throws SQLException
     */
    public void updateCopy(int copy_id, String status, int price, String copy_condition, String copy_type) throws SQLException {
		System.out.println("update copy_id: " + copy_id);
		String query = "update copy set status=?, price=?, copy_condition=?, copy_type=? where copy_id=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, status);
		ps.setInt(2, price);
		ps.setString(3, copy_condition);
		ps.setString(4, copy_type);
		ps.setInt(5, copy_id);
		ps.executeUpdate();
	}
}
