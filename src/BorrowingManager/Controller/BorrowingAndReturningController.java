package BorrowingManager.Controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Main.DBConnect;
import TableProperty.BookInstance;
import javafx.collections.ObservableList;

/**
 * Mediator của package BorrowingManager. Quản lý quan hệ giữa các class enity và boundary.
 * @author anonymous588
 *
 */
public class BorrowingAndReturningController {
	private static BorrowingAndReturningController instance = null;
	private Connection conn;
	private BorrowingAndReturningController() throws ClassNotFoundException, SQLException {
		conn = DBConnect.getConnection();
	}
	
	public static BorrowingAndReturningController getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new BorrowingAndReturningController();
		} 
		return instance;
	}
	
	private String searchRegistration = "SELECT borrowregistration.registration_id, borrowcard.card_id, borrowregistration.status from borrowregistration join borrowcard WHERE borrowcard.card_id = ? OR borrowcard.user_id = ? OR borrowregistration.registration_id = ?";
	private String getRegistrationDetail = "select user_id, borrowcard.card_id, registration_id, expired_date, deposit, copy_id, borrow_date, lent_date, expected_return_date, returned from borrowingregistrationitem join borrowcard where borrowcard.card_id=borrowingregistrationitem.card_id and borrowingregistrationitem.card_id=?";
	private String getRegistrations = "select * from borrowregistration";
	private String confirmRegistration_UpdateBorrowRegistration = "UPDATE borrowregistration SET status = '1' WHERE borrowregistration.registration_id = ?";
	private String confirmRegistration_UpdateBorrowRegistrationItems = "update borrowingregistrationitem set lent_date=?, expected_return_date=? where registration_id=? ";
	
	
	public ResultSet getRegistrations() throws ClassNotFoundException, SQLException {
		PreparedStatement ps;
		ps = conn.prepareStatement(getRegistrations);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet getRegistrationDetail(String card_id) throws ClassNotFoundException, SQLException {
		PreparedStatement ps;
		ps = conn.prepareStatement(getRegistrationDetail);
		ps.setString(1, card_id);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet searchRegistration(String id) throws SQLException, ClassNotFoundException {
		PreparedStatement ps;
		ps = conn.prepareStatement(searchRegistration);
		ps.setString(1, id);
		ps.setString(2, id);
		ps.setString(3, id);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	/**
	 * Xác nhận Registration. Gán ngày giờ trả sách, đổi thông tin của Copy.
	 * @param registration_id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean confirmRegistration(String registration_id) throws SQLException, ClassNotFoundException {
		PreparedStatement ps;
		ps = conn.prepareStatement(confirmRegistration_UpdateBorrowRegistration);
		ps.setString(1, registration_id);
		int result1 = ps.executeUpdate();
		if(result1 == 0) {
			return false;
		} else {
			ps = conn.prepareStatement(confirmRegistration_UpdateBorrowRegistrationItems);
			ps.setString(3, registration_id);
			Calendar today = Calendar.getInstance();
			today.add(Calendar.DAY_OF_MONTH, 15);
			String lent_date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String expectedReturnDate = new SimpleDateFormat("yyyy-MM-dd").format(today.getTime());
			ps.setString(1, lent_date);
			ps.setString(2, expectedReturnDate);
			int result2 = ps.executeUpdate();
			if(result2 == 0) {
				return false;
			} else return true;
		}
	}
	
	public boolean sendRegistration(ObservableList<BookInstance> setBook) {
		return false;
	}
	
}
