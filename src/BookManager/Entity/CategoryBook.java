package BookManager.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Helper.DBConnect;


/**
 * Enity của CategoryBook, là bảng trung gian giữa category và book. Truy xuất dữ liệu.
 * @author anonymous588
 *
 */
public class CategoryBook {
	Connection conn = DBConnect.getConnection();
	PreparedStatement ps;
	
    /**
     *
     */
    public CategoryBook() {	}
	
    /**
     *
     * @param book_id
     * @param category_id
     * @throws SQLException
     */
    public void addCategoryBook(int book_id, int category_id) throws SQLException {
		String query = "insert into categorybook (book_id, category_id) value (?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, book_id);
		ps.setInt(2, category_id);
		System.out.println(ps.executeUpdate());
	}
	
    /**
     *
     * @param book_id
     * @throws SQLException
     */
    public void deleteBookCategory(int book_id) throws SQLException {
		String query = "delete from categorybook where book_id=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, book_id);
		ps.executeUpdate();
	}
}
