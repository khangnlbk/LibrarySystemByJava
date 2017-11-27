package BookManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Main.DBConnect;


/**
 * Enity của CategoryBook, là bảng trung gian giữa category và book. Truy xuất dữ liệu.
 * @author anonymous588
 *
 */
public class CategoryBook {
	Connection conn = DBConnect.getConnection();
	PreparedStatement ps;
	
	public CategoryBook() {	}
	
	public void addCategoryBook(String book_id, String category_id) throws SQLException {
		String query = "insert into categorybook (book_id, category_id) value (?,?)";
		ps = conn.prepareStatement(query);
		ps.setString(1, book_id);
		ps.setString(2, category_id);
		System.out.println(ps.executeUpdate());
	}
	
	public void deleteBookCategory(String book_id) throws SQLException {
		String query = "delete from categorybook where book_id=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, book_id);
		ps.executeUpdate();
	}
}
