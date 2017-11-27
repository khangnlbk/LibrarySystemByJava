/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import librarysystem.DBConnect;
/**
 *
 * @author khangnlbk
 */
public class Category {
    private static Category instance = null;
	
	private Category() {}
	
	public static Category getInstance() {
		if(instance == null) {
			instance = new Category();
		}
		return instance;
	}
	
	private Connection conn = DBConnect.getConnection();
	private PreparedStatement ps;
	
	public ResultSet getCategoryById(String category_id) throws SQLException {
		String query = "select * from category where category_id = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, category_id);
		return ps.executeQuery();
	}
	
	public ResultSet getCategoryByName(String name) throws SQLException {
		String query = "select * from category where name = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, name);
		return ps.executeQuery();
	}
	
	public ResultSet getCategoryByEncode(String encode) throws SQLException {
		String query = "select * from category where encode = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, encode);
		return ps.executeQuery();
	}
	
	public ResultSet getAllCategory() throws SQLException {
		String query = "select * from category";
		ps = conn.prepareStatement(query);
		return ps.executeQuery();
	}
}
