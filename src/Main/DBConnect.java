package Main;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.ResultSetMetaData;


/**
 * Chứa các thông tin cần thiết về cơ sở dữ liệu, các biến truy xuất (Connection).
 * @author anonymous588
 *
 */
public class DBConnect {

    private static Connection conn;
    private static String url = "jdbc:mysql://localhost:3306/library";
    private static String user = "root";
    private static String pass = "";

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
    
    /*public static void main(String[] args) throws ClassNotFoundException, SQLException {*/
//    	Connection conn = DBConnect.getConnection();
//    	String insert_book = "INSERT INTO book (name, author, publisher, isbn) VALUES (?,?,?,?)";
//    	String insert_user = "insert into user (role, username, password, full_name, email, age, address, gender, student_id, deposit) VALUE (?,?,?,?,?,?,?,?,?,?)";
//    	String insert_borrowcard = "insert into borrowcard (card_id,user_id, expired_date, deposit) value (?,?,?,?)";
//    	String insert_borrowregistration = "insert into borrowregistration (card_id, status) value (?,?)";
//    	String insert_borrowingitem = "insert into borrowingregistrationitem (copy_id, registration_id, borrow_date, expected_return_date, returned) value (?,?,?,?,?)";
//    	String insert_copy = "insert into copy (copy_id, book_id, status, price, condition, copy_type) value (?,?,?,?,?,?)";
//    	String search = "Select * from borrowregistration";
//    	
    	
//    	PreparedStatement ps;
//    	ps = conn.prepareStatement("select user_id, borrowcard.card_id, registration_id, expired_date, deposit, copy_id, borrow_date, expected_return_date, returned from borrowingregistrationitem join borrowcard where borrowingregistrationitem.card_id = 1");
    	
    	//book
//    	ps.setString(1, "hannibal");
//    	ps.setString(2, "thomas harris");
//    	ps.setString(3, "Nha Nam");
//    	ps.setString(4, "123");
    	
    	//user
//    	ps.setString(1, "admin");
//    	ps.setString(2, "lee");
//    	ps.setString(3, "1");
//    	ps.setString(4, "leekien");
//    	ps.setString(5, "chickenfreeze0802@gmail.com");
//    	ps.setString(6, "21");
//    	ps.setString(7, "earth");
//    	ps.setString(8, "1");
//    	ps.setString(9, "123");
//    	ps.setString(10, "50000");
    	
    	//borrowcard
//    	ps.setString(1, "1");
//    	ps.setString(2, "1");
//    	ps.setString(3, "9999-12-31 23:59:59");
//    	ps.setString(4, "100000");
    	
    	//borrowregistration
//    	ps.setString(1, "1");
//    	ps.setString(2, "notconfirm");
    	
    	//copy
//    	ps.setString(1, "1");
//    	ps.setString(2, "2");
//    	ps.setString(3, "available");
//    	ps.setString(4, "30000");
//    	ps.setString(5, "good");
//    	ps.setString(6, "what");
    	
    	//borrowingitem
    	
    	
    	//insert
//    	int rs = ps.executeUpdate();
//    	System.out.println(rs);
    	
    	
    	
    	//search
//    	ResultSet rs = ps.executeQuery();
//    	java.sql.ResultSetMetaData rsmd = rs.getMetaData();
//    	int columnsNumber = rsmd.getColumnCount();
//    	while (rs.next()) {
//    	    for (int i = 1; i <= columnsNumber; i++) {
//    	        if (i > 1) System.out.print(",  ");
//    	        String columnValue = rs.getString(i);
//    	        System.out.print(columnValue + " " + rsmd.getColumnName(i));
//    	    }
//    	    System.out.println("");
//    	}
    //}
    
}
