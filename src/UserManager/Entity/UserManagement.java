/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManager.Entity;

import Helper.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author anonymous588
 */
public class UserManagement {
        public Connection conn = DBConnect.getConnection();
    public PreparedStatement ps;
    
    public ResultSet findUser(String username, String password) throws SQLException {
        String query = "select * from user where username=? and password=? ";
        ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
	ResultSet rs = ps.executeQuery();
        return rs;
    }
    
    public boolean add(String role, String username, String password, String full_name, String email, String address) throws SQLException {
    String insert_user = "insert into user (role, username, password, full_name, email, address) VALUE (?,?,?,?,?,?)";
			ps = conn.prepareStatement(insert_user);
			ps.setString(1, role); //role = 2: User
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, full_name);
			ps.setString(5, email);
			ps.setString(6, address);
                        return ps.execute();
                        
    }
}
