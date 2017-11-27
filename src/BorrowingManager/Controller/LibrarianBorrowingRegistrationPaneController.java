/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BorrowingManager.Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import TableProperty.BorrowRegistration;
import TableProperty.RegistrationDetail;
import UserManager.Controller.LibrarianViewController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khangnlbk
 */
public class LibrarianBorrowingRegistrationPaneController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
    @FXML private TableView<BorrowRegistration> registration;
	@FXML private BorderPane itemDetail;
	@FXML private TableColumn registrationidCol;
	@FXML private TableColumn cardidCol;
	@FXML private TableColumn confirmCol;
	
	@FXML private Label user_id;
	@FXML private Label registration_id;
	@FXML private Label card_id;
	@FXML private Label expired_date;
	@FXML private Label deposit;
	@FXML private TableColumn detailCardidCol;
	@FXML private TableColumn detailBorrowDateCol;
	@FXML private TableColumn detailExpectedReturnDateCol;
	@FXML private TableColumn detailReturnedCol;
	@FXML private TableColumn detailCopyIdCol;
	@FXML private TableColumn detailLentDateCol;
	@FXML private Button searchRequestButton;
	@FXML private TextField searchRegistration;
	@FXML private Button confirmButton;
	@FXML private TableView<RegistrationDetail> detailTable;
	
	public static Stage primaryStage;
	public static BorderPane mainLayout;
	public static DataFormat dataFormat = new DataFormat("mydata");
	
	private static LibrarianBorrowingRegistrationPaneController instance = null;
	
	
	private static String getRegistrations = "select * from borrowregistration";
	
	
//	private BorrowingRegistrationPaneController() {}
	
	public static LibrarianBorrowingRegistrationPaneController getInstance() {
		if(instance == null) {
			instance = new LibrarianBorrowingRegistrationPaneController();
		}
		return instance;
	}
	
	// Khoi tao cac bien truoc khi load
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		registrationidCol.setCellValueFactory(new PropertyValueFactory<BorrowRegistration, String>("registration_id"));
		cardidCol.setCellValueFactory(new PropertyValueFactory<BorrowRegistration, String>("card_id"));
		confirmCol.setCellValueFactory(new PropertyValueFactory<BorrowRegistration, String>("status"));
		
		detailCopyIdCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("copy_id"));
		detailBorrowDateCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("borrowDate"));
		detailLentDateCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("lentDate"));
		detailExpectedReturnDateCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, Integer>("expectedReturnDate"));
		detailReturnedCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("returned"));
		
		
		//Tao listener cho event chon registration de hien thi detail cua registration do.
		final ObservableList<BorrowRegistration> selected = registration.getSelectionModel().getSelectedItems();
		selected.addListener(new ListChangeListener<BorrowRegistration>() {

			public void onChanged(Change c) {
				// TODO Auto-generated method stub
				try {
					getSelectedInformation();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
			
		
		// lay thong tin registration
		try {
			getBorrowRegistrations();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//search theo user_id, card_id, hoac registration_id
	/**
	 * Tìm Registration theo user_id, card_id, hoặc registration_id, sau đó hiển thị kết quả ra màn hình
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void searchRegistration() throws ClassNotFoundException, SQLException {
		if(searchRegistration.getText() != null) {
			ResultSet rs = BorrowingAndReturningController.getInstance().searchRegistration(searchRegistration.getText());
			showRegistration(rs);
		}
	}
	
	
	/**
	 * Hiển thị toàn bộ registration.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void getBorrowRegistrations() throws ClassNotFoundException, SQLException {
		ResultSet rs = BorrowingAndReturningController.getInstance().getRegistrations();
		showRegistration(rs);
	}

	/**
	 * Lấy thông tin của registration đã chọn.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void getSelectedInformation() throws ClassNotFoundException, SQLException {
		BorrowRegistration reg = registration.getSelectionModel().getSelectedItem();
		String card_id = reg.getCard_id();
		if(reg.getStatus() == "Confirmed") {
			confirmButton.setDisable(true);
		} else {
			confirmButton.setDisable(false);
		}
		ResultSet rs = BorrowingAndReturningController.getInstance().getRegistrationDetail(card_id);
		showItemDetail(rs);
	}
	
	public void showItemDetail(ResultSet rs) throws SQLException {
		detailTable.getItems().clear();
		ObservableList<RegistrationDetail> rows = FXCollections.observableArrayList();
		while(rs.next()) {
			user_id.setText("User ID: " + rs.getString(1)); //user_id
			card_id.setText("Card ID: " + rs.getString(2)); //card_id
			registration_id.setText("Registration ID: " + rs.getString(3)); //registration id
			expired_date.setText("Expired Date: " + rs.getString(4)); //expired date
			deposit.setText("Deposit: " + rs.getString(5)); //deposit
			String returned = rs.getInt(10) == 0 ? "no" : "yes";
			RegistrationDetail tmp = new RegistrationDetail(rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9), returned);
			rows.add(tmp);
		}
		detailTable.setItems(rows);
	}
	
	/**
	 * Hiển thị thông tin của registration đã chọn.
	 * @param rs
	 * @throws SQLException
	 */
	public void showRegistration(ResultSet rs) throws SQLException {
		registration.getItems().clear();
		ObservableList<BorrowRegistration> rows = FXCollections.observableArrayList();
		while(rs.next()){
            //Iterate Row
			String[] arguments = new String[3];
            for(int i=0 ; i < rs.getMetaData().getColumnCount(); i++){
                arguments[i] = rs.getString(i+1);
            }
            System.out.println(arguments[0] +"," + arguments[1] +","+ arguments[2]);
            String confirmed = (Integer.parseInt(arguments[2]) == 0 ? "Not Confirmed" : "Confirmed");
            BorrowRegistration tmp = new BorrowRegistration(arguments[0], arguments[1], confirmed);
            rows.add(tmp);
        }
		registration.setItems(rows);
	}
	
	/**
	 * Xác nhận đã confirm Registration. Gọi đến Mediator của BorrowingManager để thực hiện việc truy xuất cơ sở dữ liệu.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void confirmRegistration() throws ClassNotFoundException, SQLException {
		BorrowRegistration reg = registration.getSelectionModel().getSelectedItem();
		String registration_id = reg.getRegistration_id();
		BorrowingAndReturningController.getInstance().confirmRegistration(registration_id);
	}
	
}
