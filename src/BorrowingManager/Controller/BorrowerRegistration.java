/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BorrowingManager.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import BookManager.Controller.BookManagerController;
import librarysystem.DBConnect;
import TableProperty.BookInstance;
import UserManager.Controller.BorrowerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Enity của bảng borrowregistration và borrowingregistrationitem, lưu dữ liệu và truy xuất
 * @author khangnlbk
 */
public class BorrowerRegistration {
    private static BorrowerRegistration instance = null;
	private BorrowerRegistration() {}
	public static BorrowerRegistration getInstance() {
		if(instance == null) {
			instance = new BorrowerRegistration();
		}
		return instance;
	}
	//
	
	private Connection conn = DBConnect.getConnection();
	PreparedStatement ps;
	
	ObservableList<BookInstance> registration = FXCollections.observableArrayList();
	
	public boolean addRegistration(BookInstance b) {
		int unreturned = 0;
		try {
			unreturned = BorrowerController.getInstance().getUnReturnBook();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(registration.size() > 5-unreturned) {
			alertError("Out Of Max Number \n You are having " +unreturned+ " unreturned book");
			return false;
		}
		for(int i = 0; i< registration.size(); i++) {
			if(registration.get(i).getBook_id().equals(b.getBook_id())) {
				alertError("Duplicate Book");
				return false;
			}
		}
		registration.add(b);
		return true;
	}
	
	public void clearRegistration() {
		registration.clear();
	}
	
	public ObservableList<BookInstance> getRegistration() {
		return registration;
	}
	
	public void setRegistration(ObservableList<BookInstance> registration) {
		this.registration = registration;
	}
	
	public void alertError(String mess) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error");
		alert.setContentText(mess);
		alert.showAndWait();
	}
	
	public void saveRegistration() throws SQLException {
		//
		if(registration.size() == 0) {
			System.out.println("no book in registration!");
			return;
		}
		String insert_registration = "insert into borrowregistration (card_id, status) value (?, ?)";
		// status == 1: librarian confirmed, status == 0: new
		String insert_items = "insert into borrowingregistrationitem (copy_id, card_id, registration_id, borrow_date, returned)"
				+ "value (?,?,?,?,?)";
		String getRegistrationID = "select * from borrowregistration where card_id=? and status=?";
		
		ps = conn.prepareStatement(insert_registration);
		ps.setString(1, BorrowerController.getInstance().getCard_id());
		ps.setString(2, "0");
		int result = ps.executeUpdate();
		if(result == 0) {
			alertError("SQL Error!");
		}
		
		// update copy status -> get copy_id, change copy status // status: available, borrowed, lent
		BookManagerController bc = BookManagerController.getInstance();
		
		// get registration_id (after insert to borrowregistration)
		String registration_id;
		ps = conn.prepareStatement(getRegistrationID);
		ps.setString(1, BorrowerController.getInstance().getCard_id());
		ps.setString(2, "0");
		ResultSet rs = ps.executeQuery();
		rs.next();
		registration_id = rs.getString(rs.findColumn("registration_id"));
		
		// get borrow_date (today)
		String borrow_date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		
		// get card_id
		String card_id = BorrowerController.getInstance().getCard_id();
		
		// update registration item 
		ps = conn.prepareStatement(insert_items);
		for(int i = 0; i < registration.size(); i++) {
			String book_id = registration.get(i).getBook_id();
			String copy_id = bc.getCopyID(book_id);
			bc.updateCopyStatus(copy_id, "borrowed");
			ps.setString(1, copy_id);
			ps.setString(2, card_id);
			ps.setString(3, registration_id);
			ps.setString(4, borrow_date);
			ps.setString(5, "0");
			ps.executeUpdate();
		}	
	}
}
