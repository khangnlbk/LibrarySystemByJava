package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Chứa các thông tin cần thiết về cơ sở dữ liệu, các biến truy xuất (Connection).
 * @author anonymous588
 *
 */
public class DBConnect {

    private static Connection conn;
    private static String url = "jdbc:mysql://localhost:3306/library_system?autoReconnect=true&useSSL=false";
    private static String user = "root";
    private static String pass = "123456789";

    /**
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url,user,pass);
            return conn;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
        return null;
    }
    
    /**
     *
     * @throws SQLException
     */
    public void closeDB() throws SQLException {
        if (conn != null)
            conn.close();
    }
    
}
