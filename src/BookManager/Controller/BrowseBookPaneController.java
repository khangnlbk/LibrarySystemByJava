package BookManager.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BorrowingManager.Controller.BorrowerRegistration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import TableProperty.BookInstance;
import TableProperty.UserInstance;
import UserManager.Controller.BorrowerController;
import UserManager.Controller.LibrarianViewController;
import UserManager.Controller.LoginController;

/**
 * Controller cho Boundary BrowseBook. Điều khiển các hành vi trong màn hình duyệt sách tùy theo Role của User.
 * @author anonymous588
 */
public class BrowseBookPaneController implements Initializable{
	@FXML private TableView<BookInstance> browseBookTable;
	@FXML private TableColumn bookidCol;
	@FXML private TableColumn authorCol;
	@FXML private TableColumn isbnCol;
	@FXML private TableColumn publisherCol;
	@FXML private TableColumn titleCol;
	@FXML private TableColumn categoryCol;
	@FXML private TableColumn discriptionCol;
	@FXML private TableColumn priceCol;
	@FXML private Button searchButton;
	@FXML private TextField searchTextField;
	@FXML private ComboBox optionBox;
	
	BookManagerController bc = BookManagerController.getInstance();
	private BorrowerRegistration registration = BorrowerRegistration.getInstance();
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		bookidCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("book_id"));
		authorCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("author"));
		titleCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("title"));
		isbnCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("isbn"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("category"));
		publisherCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("publisher"));
		discriptionCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("discription"));
		priceCol.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("price"));
		//browseBookTable.getItems().clear();
		
		//init context menus
		final ContextMenu librarianContextMenu = new ContextMenu();
		MenuItem updateBook = new MenuItem("Update Book");
		MenuItem seeCopy = new MenuItem("See Copies");
		librarianContextMenu.getItems().addAll(updateBook, seeCopy);
		updateBook.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent event) {
		    	try {
					updateBook();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		seeCopy.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent event) {
		    	try {
					seeCopy();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		//
		
		//click on row book table handle
		browseBookTable.setOnMousePressed(new EventHandler<MouseEvent>() {
			   public void handle(MouseEvent e) {
			      if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
//			         System.out.println(browseBookTable.getSelectionModel().getSelectedItem());
			    	  //check everything X((
			    	  try {
			    		  if(BorrowerController.getInstance().user.getRole().equals("2")) {
			    			  addBookToRegistration();
			    		  } else {
			    			  if(BorrowerController.getInstance().user.getRole().equals("1")) {
			    				  updateBook();
			    			  }
			    		  }	  
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			      }
			      
			      if(e.isSecondaryButtonDown()) {
			    	  if(BorrowerController.getInstance().user.getRole().equals("1")) {
			    		  librarianContextMenu.show(browseBookTable, e.getScreenX(), e.getScreenY());
	    			  }
			      }
			   }
			});
		//
		
		ObservableList<String> searchOption = FXCollections.observableArrayList(
				"Title",
				"Author",
				"Publisher",
				"ISBN"
		);		
		optionBox.setItems(searchOption);
		optionBox.getSelectionModel().select(0);
		
		try {
			showAllBook();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateBook() throws IOException {
		BookInstance b = browseBookTable.getSelectionModel().getSelectedItem();
		bc.setUpdate_book(b);
		LibrarianViewController lc = new LibrarianViewController();
		lc.showAddNewBook();
	}
	
	public void seeCopy() throws IOException {
		BookInstance b = browseBookTable.getSelectionModel().getSelectedItem();	
		bc.setBrowseCopyPaneInfor(b.getBook_id());
		LibrarianViewController lc = new LibrarianViewController();
		lc.showBrowseCopy();
	}
	
	public void showAllBook() throws SQLException {
		ResultSet rs = bc.getAllBook();
		showSetBook(rs);
	}
	
	public void searchBook() throws SQLException { 
		if(searchTextField.getText().isEmpty()) {
			alertError("Empty Search Field");
			return;
		}
		ResultSet rs = bc.searchBook(searchTextField.getText(), optionBox.getSelectionModel().getSelectedItem().toString().toLowerCase());
		showSetBook(rs);
	}
	
	public void addBookToRegistration() throws SQLException {
		UserInstance user = BorrowerController.getInstance().user;
		if(Integer.parseInt(user.getRole()) == 2) {
			BookInstance b = browseBookTable.getSelectionModel().getSelectedItem();
			if(bc.isBorrowalbe(b.getBook_id())) {
				if(registration.addRegistration(b)) {
					notification("Successs");
					BorrowerController.getInstance().unReturnBook ++;
				}
//				printRegistration();
			} else {
				alertError("Book Is Not Borrowable \n It can be the book is referenced or out of copy.");
			}	
		}
	}
	
	public void printRegistration() {
		ObservableList<BookInstance> reg = registration.getRegistration();
		for(int i = 0; i< reg.size(); i++) {
			System.out.print(reg.get(i).getBook_id() + ",");
		}
		System.out.println("");
	}
	
	public void alertError(String mess) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText("Error");
		alert.setContentText(mess);
		alert.showAndWait();
	}
	
	public void notification(String mess) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notification");
		alert.setHeaderText(mess);
		alert.setContentText("Added to Registration");
		alert.showAndWait();
	}
	
	/**
	 * Hiển thị một danh sách các đầu sách.
	 * @param setBook Danh sách truyền vào.
	 * @throws SQLException
	 */
	public void showSetBook(ResultSet setBook) throws SQLException {
		browseBookTable.getItems().clear();
		ObservableList<BookInstance> rows = FXCollections.observableArrayList();
		while(setBook.next()) {
			String book_id = setBook.getString(setBook.findColumn("book_id"));
			String title = setBook.getString(setBook.findColumn("title"));
			String author = setBook.getString(setBook.findColumn("author"));
			String publisher = setBook.getString(setBook.findColumn("publisher"));
			String isbn = setBook.getString(setBook.findColumn("isbn"));
			String price = setBook.getString(setBook.findColumn("price"));
			
			ResultSet categorySet = bc.getCategories(book_id);
			String category = "";
			while(categorySet.next()) {
				category += categorySet.getString(categorySet.findColumn("name")) + " ";
			}
			String discription = setBook.getString(setBook.findColumn("discription"));
			BookInstance tmp = new BookInstance(book_id, title, author, publisher, category, isbn, discription, price);
//			System.out.println(book_id +"," + title+","+ author+","+ publisher+","+ category+","+ isbn);
			rows.add(tmp);
		}
		browseBookTable.setItems(rows);
	}
	
}

