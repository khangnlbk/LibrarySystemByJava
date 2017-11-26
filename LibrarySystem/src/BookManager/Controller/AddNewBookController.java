/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookManager.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import BookManager.Category;
import TableProperty.BorrowRegistration;
import TableProperty.CategoryInstance;
import TableProperty.RegistrationDetail;
import UserManager.Controller.LibrarianViewController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author khangnlbk
 */
public class AddNewBookController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
    
    @FXML private TextField author, price, isbn, publisher, booktitle;
	@FXML private Button submitButton;
	@FXML private Label bookNumber;
	@FXML private Label window_label;
	@FXML private TableColumn checkboxCol, categoryCol;
	@FXML private TableView<CategoryInstance> categoryTable;
	@FXML private ComboBox classificationBox;
	@FXML private TextArea discriptionBox;
		
	private BookManagerController bc = BookManagerController.getInstance();
	private ObservableList<String> classifications = FXCollections.observableArrayList();
	
	private int mode; // 0: add, 1: update
	
	@SuppressWarnings("unchecked")
	public void initialize(URL arg0, ResourceBundle arg1) {
		classifications.clear();
		
		// stack-overflow's code for checkboxCol - thanks god it worked
        checkboxCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CategoryInstance, CheckBox>, ObservableValue<CheckBox>>() {
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<CategoryInstance, CheckBox> arg0) {
            	final CategoryInstance category = arg0.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().setValue(category.isChecked());

                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov,
                            Boolean old_val, Boolean new_val) {
                    	
                    	if(new_val == true) {
                    		updateClassification(category.getName(), category.getEncode(), 0);
                    	}
                    	if(new_val == false) {
                    		updateClassification(category.getName(), category.getEncode(), 1);
                    	}
                        category.setChecked(new_val);
                        
                    }
                });
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        ///
        
        categoryCol.setCellValueFactory(new PropertyValueFactory<BorrowRegistration, String>("name"));
		
        // init classification combobox
		ObservableList<CategoryInstance> rows = FXCollections.observableArrayList();
		try {
			ResultSet rs = Category.getInstance().getAllCategory();
			while(rs.next()) {
				String category_id = rs.getString(rs.findColumn("category_id"));
				String name = rs.getString(rs.findColumn("name"));
				String encode = rs.getString(rs.findColumn("encode"));
				boolean checked = false;
//				System.out.println(category_id + ", " + name + ", " + encode + ", " + checked);
				CategoryInstance tmp = new CategoryInstance(category_id, name, encode, checked);
				rows.add(tmp);
			}
			categoryTable.setItems(rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		
		//fill text-field in case of update
		if(bc.getUpdate_book() != null) {
			booktitle.setText(bc.getUpdate_book().getTitle());
			author.setText(bc.getUpdate_book().getAuthor());
			isbn.setText(bc.getUpdate_book().getIsbn());
			price.setText(bc.getUpdate_book().getPrice());
			publisher.setText(bc.getUpdate_book().getPublisher());
			discriptionBox.setText(bc.getUpdate_book().getDiscription());
			window_label.setText("Update Book");
			mode = 1;
		} else {
			mode = 0;
		}
		//
	}
	
	
	// action-listener for gen Book Number + change classification's item /
	/**
	 * Update các lựa chọn trong Classification dựa vào các category đã chọn.
	 * @param name 
	 * @param encode
	 * @param type  type = 0 : update, type =1: delete
	 */
	public void updateClassification(String name, String encode, int type) {
		if(!classifications.contains(name + "/" + encode) && type == 0) {
			classifications.add(name + "/" + encode);
		}
		if(classifications.contains(name + "/" + encode) && type == 1) {
			classifications.remove(name + "/" + encode);
		}
		classificationBox.setItems(classifications);
	}
	
	//generate book number by selected classification
	/**
	 * Tạo BookNumber cho Book dựa vào Classification đã chọn và số thứ tự trong DB.
	 */
	public void genBookNumber() {
		String selectedItem = (String)classificationBox.getSelectionModel().getSelectedItem();
		String[] tmp = selectedItem.split("/");
		int sequence = bc.getInstance().getNewBookSequence(tmp[1]);
		bookNumber.setText(tmp[1] + String.format("%04d", sequence));
	}
	
	
	/**
	 * Thêm sách vào cơ sở dữ liệu, hoặc sửa sách.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void addBook() throws ClassNotFoundException, SQLException, IOException {
		if(validation()) {
			if(mode == 0) {
				// add new
				bc.addBook(booktitle.getText(), author.getText(), publisher.getText(), isbn.getText(), bookNumber.getText(), discriptionBox.getText(), price.getText());
				
				
				ResultSet rs = bc.searchBook(bookNumber.getText(), "book_number");
				rs.next();
				
				ObservableList<CategoryInstance> rows = FXCollections.observableArrayList();
				rows = categoryTable.getItems();
				for(int i = 0; i < rows.size(); i ++) {
					if(rows.get(i).isChecked()) {
						bc.addCategoryBook(rs.getString(rs.findColumn("book_id")), rows.get(i).getCategory_id());
					}
				}
				
				AddNewCopyController.set_bookNumber(bookNumber.getText());
				LibrarianViewController lb = new LibrarianViewController();
				lb.showLibrarianView();
				lb.showAddNewCopy();
				
			} else {
				//update ->
				//->delete all old categories
				bc.deleteBookCategory(bc.getUpdate_book().getBook_id());
				//->update book's information
				bc.updateBook(bc.getUpdate_book().getBook_id(), booktitle.getText(), author.getText(), publisher.getText(), isbn.getText(), bookNumber.getText(), discriptionBox.getText(), price.getText());
				System.out.println(bc.getUpdate_book().getBook_id()+","+ booktitle.getText()+","+ author.getText()+","+ publisher.getText()+","+ isbn.getText()+","+ bookNumber.getText()+","+ discriptionBox.getText()+","+ price.getText());
				//->add new set of categories
				ObservableList<CategoryInstance> rows = FXCollections.observableArrayList();
				rows = categoryTable.getItems();
				for(int i = 0; i < rows.size(); i ++) {
					if(rows.get(i).isChecked()) {
						bc.addCategoryBook(bc.getUpdate_book().getBook_id(), rows.get(i).getCategory_id());
					}
				}
				
				//update copy_number
				bc.updateCopyNumber(bc.getUpdate_book().getBook_id(), bookNumber.getText());
				//
				
				AddNewCopyController.set_bookNumber(bookNumber.getText());
				bc.setUpdate_book(null);
				LibrarianViewController lb = new LibrarianViewController();
				lb.showLibrarianView();
				
			}
		}
	}
	
	public boolean validation() {
		if(classificationBox.getSelectionModel().isEmpty()) {
			alertError("Empty Classification!");
			return false;
		}
		if(booktitle.getText().isEmpty()) {
			alertError("Empty Book Title!");
			return false;
		}
		if(author.getText().isEmpty()) {
			alertError("Empty Author!");
			return false;
		}
		if(isbn.getText().isEmpty()) {
			alertError("Empty ISBN!");
			return false;
		}
		if(price.getText().isEmpty()) {
			alertError("Empty Price!");
			return false;
		} else {
			if(!isNumeric(price.getText())) {
				alertError("Price must be a number!");
				return false;
			}
		}
		if(publisher.getText().isEmpty()) {
			alertError("Empty Publisher!");
			return false;
		}
		if(discriptionBox.getText().isEmpty()) {
			discriptionBox.setText("");
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
}
