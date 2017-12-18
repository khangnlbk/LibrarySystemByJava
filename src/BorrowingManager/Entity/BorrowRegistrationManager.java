/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BorrowingManager.Entity;

import Helper.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anonymous588
 */
public class BorrowRegistrationManager {
    
    private Connection conn = DBConnect.getConnection();
    private PreparedStatement ps;

    public BorrowRegistrationManager(){};
        
    /**
     * Get unreturn book by user id
     * @param user_id
     * @return the number of unreturn book
     */
    public int getUnreturnBook(int user_id){
        String query = "Select COUNT(borrowingregistrationitem.copy_id) from borrowingregistrationitem join borrowcard on borrowcard.card_id=borrowingregistrationitem.card_id "
            + "where borrowcard.user_id=? "
            + "and borrowingregistrationitem.returned= 'no' GROUP BY borrowingregistrationitem.item_id";
        int unReturn=0;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,user_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                unReturn = rs.getInt("COUNT(copy_id)");

            }
        } catch (SQLException ex) {
        }
        return unReturn;   
    }
        
    /**
     * check outdate book of user
     * @param user_id
     * @return true if user has outdate book, false in the other case
     */
    public boolean haveOutdateBook(int user_id){
        String query = "Select * from borrowingregistrationitem join borrowcard on borrowcard.card_id=borrowingregistrationitem.card_id "
            + "where borrowcard.user_id=?"
            + " and ( borrowingregistrationitem.returned='no' and borrowingregistrationitem.expected_return_date < CURRENT_TIMESTAMP)";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,user_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowRegistrationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }
        
    /**
     * Save registration
     * @param card_id
     * @param copy_id
     * @param book_id
     * @return true if save successfully
     * @throws SQLException
     */
    public boolean saveRegistration(int card_id) throws SQLException{
        String insert_registration = "insert into borrowregistration (card_id) value (?)";
            // status == 1: librarian confirmed, status == 0: new
        ps = conn.prepareStatement(insert_registration);
        ps.setInt(1,card_id);
        if(ps.executeUpdate()== 0) {
                return false;
        }
        return true;
    }
    public int getRegistrationId(int card_id) throws SQLException{
         String getRegistrationID = "select * from borrowregistration where card_id=? and status=?";
         int registration_id;
        ps = conn.prepareStatement(getRegistrationID);
        ps.setInt(1,card_id);
        ps.setString(2, "borrowed");
        ResultSet rs = ps.executeQuery();
        rs.next();
        registration_id = rs.getInt(rs.findColumn("registration_id"));
        return registration_id;
    }
//        ps=conn.prepareStatement(updateCopy);
//        ps.setInt(1, copy_id);
//        if(ps.executeUpdate()==0)
//            return false;
        // done update to borrowregistration
        
    public boolean addItemtoBorrow(int copy_id, int card_id, int registration_id) throws SQLException{
                String insert_items = "insert into borrowingregistrationitem (copy_id, card_id, registration_id, borrow_date, returned)"
                            + "value (?,?,?,?,?)";
                String borrow_date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                ps = conn.prepareStatement(insert_items);
                ps.setInt(1, copy_id);
                ps.setInt(2, card_id);
                ps.setInt(3, registration_id);
                ps.setString(4, borrow_date);
                ps.setString(5, "no");
                int x= ps.executeUpdate();
        if(x==0){
            return false;
        }
        return true;
    }
        
    /**
     * Get history of borrowing
     * @param user_id
     * @return history list
     * @throws SQLException
     */
    public ResultSet getHistory(int user_id) throws SQLException {
        String query= "select * from borrowingregistrationitem join borrowcard on borrowingregistrationitem.card_id=borrowcard.card_id join copy on borrowingregistrationitem.copy_id= copy.copy_id join book on copy.book_id=book.book_id where user_id=? ";
        ps=conn.prepareStatement(query);
        ps.setInt(1,user_id);
        return ps.executeQuery();
    }
    
    public ResultSet getAll() throws SQLException{
        String query="select * from borrowregistration";
         ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery();
         return rs;
    }
    
    public ResultSet getDetail(int regist) throws SQLException{
        String getRegistrationDetail = "select * from borrowingregistrationitem join borrowcard on borrowingregistrationitem.card_id=borrowcard.card_id join copy on borrowingregistrationitem.copy_id= copy.copy_id join book on copy.book_id=book.book_id where borrowingregistrationitem.registration_id=?";
        ps=conn.prepareStatement(getRegistrationDetail);
        ps.setInt(1, regist);
            ResultSet rs = ps.executeQuery();
            return rs;
    }
    
    public ResultSet getRegisteredCopy(int id) throws SQLException{
        String getRegistrationDetail = "select * from borrowingregistrationitem join borrowcard where registration_id=?";
        ps=conn.prepareStatement(getRegistrationDetail);
        ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs;
        
    }
    
    public boolean updateRegistration(int id) throws SQLException{
         String confirmRegistration_UpdateBorrowRegistration = "UPDATE borrowregistration SET status = 'lent' WHERE borrowregistration.registration_id = ?";
          ps = conn.prepareStatement(confirmRegistration_UpdateBorrowRegistration);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if(result==0)
                return false;
            else return true;
    }
    public boolean updateRegistrationtToReturn(int id) throws SQLException{
         String confirmRegistration_UpdateBorrowRegistration = "UPDATE borrowregistration SET status = 'returned' WHERE borrowregistration.registration_id = ?";
          ps = conn.prepareStatement(confirmRegistration_UpdateBorrowRegistration);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if(result==0)
                return false;
            else return true;
    }    
    public void updateCopytoLent(int id) throws SQLException{
        String query = "Update copy SET status='lent'where copy_id=?";
        ps=conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();        
    }
    
        
    public void updateCopytoBorrowed(int id) throws SQLException{
        String query = "Update copy SET status='borrowed'where copy_id=?";
        ps=conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();        
    }
        
    public void updateCopytoAvail(int id) throws SQLException{
        String query = "Update copy SET status='available'where copy_id=?";
        ps=conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();        
    }
    
    public  boolean updateToLend(int id) throws SQLException{
         String confirmRegistration_UpdateBorrowRegistrationItems = "update borrowingregistrationitem set lent_date= ?, expected_return_date= ? where registration_id= ? ";      
                    Calendar today = Calendar.getInstance();
                    today.add(Calendar.DAY_OF_MONTH, 15);
                    String lent_date = new SimpleDateFormat("yyyy-MM-dd").format(today.getTime());
                    String expectedReturnDate = new SimpleDateFormat("yyyy-MM-dd").format(today.getTime());
                    ps = conn.prepareStatement(confirmRegistration_UpdateBorrowRegistrationItems);
                    ps.setString(1, lent_date);
                    ps.setString(2, expectedReturnDate);
                    ps.setInt(3, id);
                    int result2 = ps.executeUpdate();
                    if(result2 == 0) {
                            return false;
                    } else return true;
    }
    
    public boolean updateToReturn(int copy_id,int regist_id) throws SQLException{
             String confirmRegistration_UpdateBorrowRegistrationItems = "update borrowingregistrationitem set returned='yes' where copy_id=? and registration_id=?";
             ps=conn.prepareStatement(confirmRegistration_UpdateBorrowRegistrationItems);
             ps.setInt(1, copy_id);
      ps.setInt(2,regist_id);
                    
                    int result2 = ps.executeUpdate();
                    if(result2 == 0) {
                            return false;
                    } else return true;
    }
    
    public int numberToReturn(int copy_id,int regist_id) throws SQLException{
        String confirmRegistration_UpdateBorrowRegistrationItems = "select COUNT(item_id)from borrowingregistrationitem where copy_id=? and registration_id=? and returned='no' Group by item_id";
             ps=conn.prepareStatement(confirmRegistration_UpdateBorrowRegistrationItems);
             ps.setInt(1, copy_id);
             ps.setInt(2,regist_id);
             ResultSet rs=ps.executeQuery();
             if(rs.next())
             return rs.getInt("COUNT(item_id)");
             else return 0;
    
    }

}
