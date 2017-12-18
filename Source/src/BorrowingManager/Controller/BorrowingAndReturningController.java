package BorrowingManager.Controller;

import BorrowingManager.Boundary.BorrowerRegistrationPaneView;
import BorrowingManager.Entity.BorrowRegistrationManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Quản lý quan hệ giữa các class enity và boundary.
 * @author anonymous588
 *
 */
public class BorrowingAndReturningController {
    private BorrowRegistrationManager manager = new BorrowRegistrationManager();
    public BorrowingAndReturningController() {}
		
    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ResultSet getRegistrations() throws ClassNotFoundException, SQLException {
            return manager.getAll();
    }
	
    /**
     *
     * @param regist
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ResultSet getRegistrationDetail(int regist) throws ClassNotFoundException, SQLException {
           return manager.getDetail(regist);
    }
	
    /**
     * search registration by user id
     * @param user_id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet searchRegistration(int user_id) throws SQLException, ClassNotFoundException {
            return manager.getHistory(user_id);
    }
	
    /**
     * Xác nhận Registration. Gán ngày giờ trả sách, đổi thông tin của Copy.
     * @param registration_id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean confirmRegistration(int registration_id) throws SQLException, ClassNotFoundException {
           if(manager.updateRegistration(registration_id)==false)
               return false;
           ResultSet sets=manager.getRegisteredCopy(registration_id);
           while(sets.next()){
               if(manager.updateToLend(sets.getInt("copy_id"))==false)
                   return false;
           };
           return true;
    }
    
    public boolean confirmItem(int copy_id, int regist_id) throws SQLException{
        if( manager.updateToReturn(copy_id, regist_id)==false)
        return false;
        manager.updateCopytoAvail(copy_id);
        int num=manager.numberToReturn(copy_id, regist_id);
        if(num==0){
            manager.updateRegistrationtToReturn(regist_id);
        }
        return true;
    }
	
}
