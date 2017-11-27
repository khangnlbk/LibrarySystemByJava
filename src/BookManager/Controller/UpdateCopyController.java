/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookManager.Controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import Main.AlertPane;
import UserManager.Controller.LibrarianViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 * Controller của boundary Update Copy. Hiển thị các thông tin ban đầu của Copy đó và xác nhận sự thay đổi trong các trường dữ liệu của Copy.
 * @author khangnlbk
 */
public class UpdateCopyController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
    @FXML private TextField copy_condition;
	@FXML private Label copy_number;
	@FXML private TextField price;
	@FXML private ComboBox copy_type;
	@FXML private ComboBox status;
	
	private BookManagerController bc = BookManagerController.getInstance();
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<String> typeOption = FXCollections.observableArrayList(
				"reference",
				"borrowable"
		);
		copy_type.setItems(typeOption);
		
		ObservableList<String> statusOption = FXCollections.observableArrayList(
				"available",
				"referenced",
				"borrowed",
				"lent"
		);
		status.setItems(statusOption);
		
		if(bc.getUpdate_copy() != null) {
			copy_number.setText("Copy Number: "+bc.getUpdate_copy().getCopy_number());
			price.setText(bc.getUpdate_copy().getPrice());
			copy_condition.setText(bc.getUpdate_copy().getCopy_condition());
			int i = typeOption.indexOf(bc.getUpdate_copy().getCopy_type().toLowerCase());
			copy_type.getSelectionModel().select(i);
			i = statusOption.indexOf(bc.getUpdate_copy().getStatus().toLowerCase());
			status.getSelectionModel().select(i);
		}
	}
	
	public boolean validation() {
		if(copy_type.getSelectionModel().isEmpty()) {
			AlertPane.alertError("Empty Classification!");
			return false;
		}
		if(price.getText().isEmpty()) {
			AlertPane.alertError("Empty Price!");
			return false;
		} else {
			if(!isNumeric(price.getText())) {
				AlertPane.alertError("Price must be a number!");
				return false;
			}
		}
		if(copy_condition.getText().isEmpty()) {
			AlertPane.alertError("Empty Copy Condition!");
			return false;
		}
		if(status.getSelectionModel().isEmpty()) {
			AlertPane.alertError("Empty Status!");
			return false;
		}
		
		return true;
	}
	
	public static boolean isNumeric(String str) {
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}
	
	/**
	 * Gọi mediator, lưu dữ liệu.
	 * @throws IOException
	 */
	public void update() throws IOException {
		if(validation()) {
			bc.updateCopy(status.getSelectionModel().getSelectedItem().toString(), price.getText(), copy_condition.getText(), copy_type.getSelectionModel().getSelectedItem().toString());
		}
		bc.setUpdate_copy(null);
		LibrarianViewController lb = new LibrarianViewController();
		lb.showLibrarianView();
	}
    
}
