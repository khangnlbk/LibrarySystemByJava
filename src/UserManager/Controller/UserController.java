/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManager.Controller;

import Helper.DBConnect;
import UserManager.Boundary.BorrowerView;
import UserManager.Boundary.LibrarianView;
import UserManager.Entity.User;
import UserManager.Entity.UserManagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;


/**
 *
 * @author anonymous588
 */
public class UserController {
    UserManagement manager =new UserManagement();
    
    public boolean checkUser(String username, String password) {
        try {
            ResultSet rs=manager.findUser(username, password);
            if(rs.next())
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ResultSet getUser(String username, String pass) {
        try {
            ResultSet rs=manager.findUser(username, pass);
            rs.next();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean register(String role, String username, String password, String full_name, String email, String address) throws SQLException{
        return manager.add(role, username, password, full_name, email, address);
    }

    public Application Login(User user) {
        BorrowerController currentUser = BorrowerController.getInstance();
        currentUser.setUser(user);
        if(currentUser.getRole().equals("1"))
            return new LibrarianView();
        else if(currentUser.getRole().equals("2"))
            return new BorrowerView();
        else return null;
    }

}
