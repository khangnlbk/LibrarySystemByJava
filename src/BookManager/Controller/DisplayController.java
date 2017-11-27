/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookManager.Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import TableProperty.BookInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author khangnlbk
 */
public class DisplayController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    } 
    @FXML
	public Label lblTitle, lblPublisher, lblDesc, lblAuthor, lblCategory, lblStatus, lblContent;

	private BookManagerController _bc = BookManagerController.getInstance();
	private ObservableList<String> _classifications = FXCollections.observableArrayList();

	public void initialize(URL arg0, ResourceBundle arg1) {
		_classifications.clear();
		try {
			int bookID = 0; //chưa biết làm
			ResultSet result = _bc.GetBookByBookID(bookID);

			lblTitle.setText(result.getString(result.findColumn("title")));
			lblAuthor.setText(result.getString(result.findColumn("author")));
			lblCategory.setText(result.getString(result.findColumn("categoryName")));
			lblPublisher.setText(result.getString(result.findColumn("publisher")));
			lblContent.setText(result.getString(result.findColumn("content")));
			lblDesc.setText(result.getString(result.findColumn("description")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
    
}
