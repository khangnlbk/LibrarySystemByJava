/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author khangnlbk
 */
public class DBConnect {
    private static Connection conn;
    private static String url= "jdb:mysql://127.0.0.1:3306/library.kien";
    private static String user= "root";
    private static String pass= "";
    
    public static Connection connect() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(ClassNotFoundException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
        }catch(InstantiationException ie){
            System.err.println("Error: "+ie.getMessage());
        }catch(IllegalAccessException iae){
            System.err.println("Error: "+iae.getMessage());
        }

        conn = (Connection)DriverManager.getConnection(url,user,pass);
        return conn;
    }
    
    
    public static Connection getConnection(){
        try {
			if(conn !=null && !conn.isClosed())
			    return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        try {
			connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return conn;
    }
    
    public void closeDB() throws SQLException {
        if (conn != null)
        	conn.close();
    }
    
}
