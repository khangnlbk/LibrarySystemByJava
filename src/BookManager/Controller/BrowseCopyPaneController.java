package BookManager.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Main.AlertPane;
import TableProperty.BookInstance;
import TableProperty.CopyInstance;
import UserManager.Controller.BorrowerController;
import UserManager.Controller.LibrarianViewController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * Controller của Boundary BrowseCopyPane. Quản lý việc hiển thị các thông tin về copy của một đầu sách nhất định.
 * @author anonymous588
 *
 */
public class BrowseCopyPaneController implements Initializable{
	@FXML private TableColumn bookIdCol;
	@FXML private TableColumn copyConditionCol;
	@FXML private TableColumn copyIdCol;
	@FXML private TableColumn copyNumberCol;
	@FXML private TableColumn copyTypeCol;
	@FXML private TableColumn priceCol;
	@FXML private TableColumn statusCol;
	@FXML private TableView<CopyInstance> browseCopyTable;
	@FXML private TextField searchTextField;
	@FXML private ComboBox optionBox;
	
	
	private BookManagerController bc = BookManagerController.getInstance();
	public static String book_id = null;
	
	public static String getBook_id() {
		return book_id;
	}

	public static void setBook_id(String book_id) {
		BrowseCopyPaneController.book_id = book_id;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		bookIdCol.setCellValueFactory(new PropertyValueFactory<CopyInstance, String>("book_id"));
		copyIdCol.setCellValueFactory(new PropertyValueFactory<CopyInstance, String>("copy_id"));
		copyConditionCol.setCellValueFactory(new PropertyValueFactory<CopyInstance, String>("copy_condition"));
		copyNumberCol.setCellValueFactory(new PropertyValueFactory<CopyInstance, String>("copy_number"));
		copyTypeCol.setCellValueFactory(new PropertyValueFactory<CopyInstance, String>("copy_type"));
		priceCol.setCellValueFactory(new PropertyValueFactory<CopyInstance, String>("price"));
		statusCol.setCellValueFactory(new PropertyValueFactory<CopyInstance, String>("status"));
		browseCopyTable.setEditable(true);
		
		browseCopyTable.setOnMousePressed(new EventHandler<MouseEvent>() {
			   public void handle(MouseEvent e) {
			      if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
			    	try {
						updateCopy();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			      }
			   }
			});
	             
//        priceCol.setCellFactory(TextFieldTableCell.forTableColumn());
//       
//        ObservableList<String> statusOption = FXCollections.observableArrayList(
//				"Borrowed",
//				"Lent",
//				"Available"
//		);
//        Callback<TableColumn<CopyInstance, String>, TableCell<CopyInstance, String>> cbtc = 
//        		ComboBoxTableCell.forTableColumn(statusOption);
//        statusCol.setCellFactory(cbtc);
        
        
		ObservableList<String> searchOption = FXCollections.observableArrayList(
				"Title",
				"Author",
				"Publisher",
				"ISBN"
		);		
		optionBox.setItems(searchOption);
		optionBox.getSelectionModel().select(0);
		
		try {
			showSetCopy(getCopy());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getCopy() {
		if(!book_id.isEmpty()) {
			System.out.println();
			return bc.getCopy(book_id);
		}
		return null;
	}
	
	public ResultSet getCopy(String book_id) {
		if(!book_id.isEmpty()) {
			System.out.println();
			return bc.getCopy(book_id);
		}
		return null;
	}
	
	public void showSetCopy(ResultSet setCopy) throws SQLException {
		browseCopyTable.getItems().clear();
	
		ObservableList<CopyInstance> rows = FXCollections.observableArrayList();
		while(setCopy.next()) {
//			String copy_id, String book_id, String copy_number, String status, String price, String copy_condition, String copy_type
			String book_id = setCopy.getString(setCopy.findColumn("book_id"));
			String copy_id = setCopy.getString(setCopy.findColumn("copy_id"));
			String copy_number = setCopy.getString(setCopy.findColumn("copy_number"));
			String status = setCopy.getString(setCopy.findColumn("status"));
			String price = setCopy.getString(setCopy.findColumn("price"));
			String copy_condition = setCopy.getString(setCopy.findColumn("copy_condition"));
			String copy_type = setCopy.getString(setCopy.findColumn("copy_type"));
			
			CopyInstance tmp = new CopyInstance(copy_id,  book_id,  copy_number,  status,  price,  copy_condition,  copy_type);
//			System.out.println(book_id +"," + title+","+ author+","+ publisher+","+ category+","+ isbn);
			rows.add(tmp);
		}
		browseCopyTable.setItems(rows);
	}
	
	public void searchBook() throws SQLException { 
		if(searchTextField.getText().isEmpty()) {
			AlertPane.alertError("Empty Search Field");
			return;
		}
		ResultSet rs = bc.searchBook(searchTextField.getText(), optionBox.getSelectionModel().getSelectedItem().toString().toLowerCase());
		
		if(rs.next()) {
			showSetCopy(getCopy(rs.getString(rs.findColumn("book_id"))));
		}
	}
	
	/**
	 * Hiển thị màn hình update một copy nhất định.
	 * @throws IOException
	 */
	public void updateCopy() throws IOException {
		CopyInstance c = browseCopyTable.getSelectionModel().getSelectedItem();
		bc.setUpdate_copy(c);
		LibrarianViewController lc = new LibrarianViewController();
		lc.showUpdateCopy();
	}
	
	
}
