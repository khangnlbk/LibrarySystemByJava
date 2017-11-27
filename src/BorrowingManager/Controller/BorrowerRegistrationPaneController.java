// hàm này là controller cho màn hình hiển thị registration hiện tại của borrower. (trigged bằng cách ấn vào nút registration trên giao diện của borrower)

package BorrowingManager.Controller;



import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BookManager.Controller.BookManagerController;
import Main.AlertPane;
import TableProperty.BookInstance;
import TableProperty.UserInstance;
import UserManager.Controller.BorrowerController;
import UserManager.Controller.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller của boudary BorrowerRegistrationPane, là màn hình hiển thị danh sách các sách người dùng đang yêu cầu mượn.
 * Xác nhận hoạt động đặt mượn bằng nút Confirm.
 * @author anonymous588
 *
 */
public class BorrowerRegistrationPaneController implements Initializable {
	@FXML private TableView<BookInstance> bookTable;
	@FXML private TableColumn bookidCol;
	@FXML private TableColumn authorCol;
	@FXML private TableColumn isbnCol;
	@FXML private TableColumn publisherCol;
	@FXML private TableColumn titleCol;
	@FXML private TableColumn categoryCol;
	@FXML private TableColumn discriptionCol;
	@FXML private Button confirmButton;
	
	BookManagerController bc = BookManagerController.getInstance();
	BorrowerRegistration registration = BorrowerRegistration.getInstance();
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		bookidCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("book_id"));
		authorCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("author"));
		titleCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("title"));
		isbnCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("isbn"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("category"));
		publisherCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("publisher"));
		discriptionCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("discription"));
		showRegistrationBooks();
	}
	
	
	public void showRegistrationBooks() {
		bookTable.getItems().clear();
		ObservableList<BookInstance> rows = registration.getRegistration();
//		for(int i = 0; i< rows.size(); i++) {
//			System.out.println(rows.get(i).getTitle());
//		}
		bookTable.setItems(rows);
	}
	
	/**
	 * Xác nhận Registration. 
	 */
	public void sendRegistration() {
		try {
			if(BorrowerController.getInstance().getUnReturnBook() + registration.getRegistration().size() > 5) {
				AlertPane.alertError("You have" + BorrowerController.getInstance().getUnReturnBook() + " unreturned book." + "\n"
						+ "You can only borrow 5 books at a time. Please return some book before borrow.");
				return;
			}
			if(BorrowerController.getInstance().getCard_id().isEmpty()) {
				AlertPane.alertError("You must have aleast 1 activated Borrow Card");
				return;
			}	
			if(BorrowerController.getInstance().checkUnConfirmRegistration()) {
				AlertPane.alertError("You have 1 registration waiting to be confirmed");
				return;
			}			
			BorrowerRegistration.getInstance().saveRegistration();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
