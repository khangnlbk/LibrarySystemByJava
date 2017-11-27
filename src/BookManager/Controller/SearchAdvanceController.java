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
import java.util.ResourceBundle;

import BookManager.Category;
import TableProperty.BookInstance;
import TableProperty.CategoryInstance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author khangnlbk
 */
public class SearchAdvanceController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
    @FXML
	private TextField txtBookName, txtAuthor, txtPublishCom;
	@SuppressWarnings("rawtypes")
	@FXML
	public ComboBox ccbCategory;
	@SuppressWarnings("rawtypes")
	@FXML
	public TableColumn tbclTitle, tbclAuthor, tbclCategory, tbclPublisher;
	@FXML
	public TableView<BookInstance> tbvLstBook;
	@FXML
	public DatePicker dtpPublistStartDTG, dtpComposedStartDTG, dtpPublishEndDTG, dtpComposedEndDTG;
	@FXML
	public Button btnSearch;

	private BookManagerController _bc = BookManagerController.getInstance();
	private ObservableList<String> _classifications = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	public void initialize(URL arg0, ResourceBundle arg1) {
		_classifications.clear();
		try {
			ResultSet rs = Category.getInstance().getAllCategory();
			ObservableList<CategoryInstance> rows = FXCollections.observableArrayList();
			while (rs.next()) {
				String category_id = rs.getString(rs.findColumn("category_id"));
				String name = rs.getString(rs.findColumn("name"));

				CategoryInstance cate = new CategoryInstance(category_id, name);
				rows.add(cate);
			}
			ccbCategory.getItems().addAll(rows);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public class SearchFilter {
		public String Title;
		public String Author;
		public String PublishCompany;
		public int CategoryID;
		public String CategoryName;
	}

	@SuppressWarnings("unchecked")
	public void btnSearch_Click() throws ClassNotFoundException, SQLException, IOException {
		SearchFilter filter = new SearchFilter();
		filter.Title = txtBookName.getText();
		filter.Author = txtAuthor.getText();
		filter.PublishCompany = txtPublishCom.getText();
//		filter.CategoryID = (int) ccbCategory.getSelectionModel().getSelectedItem();
		filter.CategoryName = (String) ccbCategory.getSelectionModel().getSelectedItem();

		ResultSet result = _bc.SearchBookAdvance(filter);
		tbclTitle.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("title"));
		tbclAuthor.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("author"));
		tbclPublisher.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("publisher"));
		tbclCategory.setCellValueFactory(new PropertyValueFactory<BookInstance, String>("categoryName"));
//		
//		TableColumn display = new TableColumn<>("display");
//		display.setCellValueFactory(new PropertyValueFactory<>("display"));
//		Callback<TableColumn<B, T>, R>
//		
		ObservableList<BookInstance> row = FXCollections.observableArrayList();
		while (result.next()) {
			String title = result.getString(result.findColumn("title"));
			String author = result.getString(result.findColumn("author"));
			String categoryName = result.getString(result.findColumn("categoryName"));
			String publiser = result.getString(result.findColumn("publisher"));

			BookInstance item = new BookInstance(title, author, categoryName, publiser);
			row.add(item);
		}
		tbvLstBook.setItems(row);
	}
    
}
