package BookManager.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.DBConnect;

/**
 * Enity của CategoryManager. Truy xuất dữ liệu.
 * @author anonymous588
 *
 */
public class CategoryManager {
	
	public CategoryManager() {}
	
//    /**
//     *
//     * @return
//     */
//    public static CategoryManager getInstance() {
//		if(instance == null) {
//			instance = new CategoryManager();
//		}
//		return instance;
//	}
	
	private Connection conn = DBConnect.getConnection();
	private PreparedStatement ps;
	
    /**
     *
     * @param category_id
     * @return
     * @throws SQLException
     */
    public ResultSet getCategoryById(int category_id) throws SQLException {
		String query = "select * from category where category_id = ?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, category_id);
		return ps.executeQuery();
	}
	
    /**
     *
     * @param name
     * @return
     * @throws SQLException
     */
    public ResultSet getCategoryByName(String name) throws SQLException {
		String query = "select * from category where name = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, name);
		return ps.executeQuery();
	}
	
    /**
     *
     * @param encode
     * @return
     * @throws SQLException
     */
    public ResultSet getCategoryByEncode(String encode) throws SQLException {
		String query = "select * from category where encode = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, encode);
		return ps.executeQuery();
	}
	
    /**
     *
     * @return
     * @throws SQLException
     */
    public ResultSet getAllCategory() throws SQLException {
		String query = "select * from category";
		ps = conn.prepareStatement(query);
		return ps.executeQuery();
	}
	
}
