package BookManager.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import UserManager.Controller.LibrarianViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Controller cho Boundary AddNewCopy. 
 * @author anonymous588
 *
 */
public class AddNewCopyController implements Initializable {
	
	@FXML private ComboBox typeOfCopy;
	@FXML private TextField bookNumber;
	@FXML private TextField quantity;
	@FXML private TextField price;
	@FXML private TextField condition;
	@FXML private Button submitButton;
	
	public static String _newBookNumber;

	
	private BookManagerController bc = BookManagerController.getInstance(); 

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(_newBookNumber == null) {
			bookNumber.clear();
		} else {
			bookNumber.setText(_newBookNumber);
		}
		
		ObservableList<String> copy_type = 
			    FXCollections.observableArrayList(
			        "Reference",
			        "Borrowable"
			    );
		typeOfCopy.getItems().addAll(copy_type);
		typeOfCopy.getSelectionModel().select(0);
		
	} 
	
	public static String get_bookNumber() {
		return _newBookNumber;
	}

	public static void set_bookNumber(String newBookNumber) {
		AddNewCopyController._newBookNumber = newBookNumber;
	}

	public boolean validation() {
		if(typeOfCopy.getSelectionModel().isEmpty()) {
			alertError("Empty Type Of Copy");
			return false;
		}
		
		if(bookNumber.getText().isEmpty()) {
			alertError("Empty Book Number");
			return false;
		}
		
		if(price.getText().isEmpty()) {
			alertError("Empty Price");
			return false;
		} else {
			if(!isNumeric(price.getText())) {
				alertError("Price must be a number");
				return false;
			}
		}
		
		if(quantity.getText().isEmpty()) {
			alertError("Empty Number Of New Copy");
			return false;
		} else {
			if(!isNumeric(quantity.getText())) {
				alertError("Quantity must be a number");
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isNumeric(String str)
	{
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}
	
	public void alertError(String mess) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText(mess);
		alert.setContentText("Please Fix The Error In Fields!");

		alert.showAndWait();
	}
	
	/**
	 * Xác nhận thêm.
	 * @throws SQLException
	 * @throws IOException
	 */
	public void submit() throws SQLException, IOException {
		if(validation()) {
			ResultSet rs = bc.getInstance().searchBook(bookNumber.getText(), "book_number");
			if(rs.next()) {
				String book_id = rs.getString(rs.findColumn("book_id"));
				int copyCount = bc.getCountOfCopy(book_id) + 1;
				String copyNumber;
				int quant = Integer.parseInt(quantity.getText());
				for(int i = 1; i<= quant; i++) {
					int sequence = copyCount +i;
					copyNumber = bookNumber.getText() + "-" + sequence;
					bc.addNewCopy(book_id, copyNumber, "available", price.getText(), condition.getText(), typeOfCopy.getSelectionModel().getSelectedItem().toString().toLowerCase());
					System.out.println(book_id +"," + copyNumber+"," + "available"+"," + price.getText()+"," + condition.getText()+"," + (String)typeOfCopy.getSelectionModel().getSelectedItem().toString().toLowerCase());
				}
				
//				System.out.println(copyNumber);
			} else {
				alertError("Found Nothing with that Book Number.");
			}
			
			set_bookNumber(null);
			LibrarianViewController lc = new LibrarianViewController();
			lc.showLibrarianView();
		}	
	}

}
