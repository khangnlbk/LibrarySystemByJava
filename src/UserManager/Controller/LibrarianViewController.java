/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManager.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BookManager.Controller.BookManagerController;
import BookManager.Controller.BrowseBookPaneController;
import BorrowingManager.Controller.LibrarianBorrowingRegistrationPaneController;
import librarysystem.LibrarySystem;
import TableProperty.UserInstance;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Class này là Controller của giao diện Librarian. Bao gồm 1 menu và 1 bảng hiện thị tất cả các đầu sách.
 * @author khangnlbk
 */
public class LibrarianViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
    public class LibrarianViewController extends Application implements Initializable{
	@FXML private TextField searchBook; 
	@FXML private Button logout;
	@FXML private ComboBox searchOption;
	@FXML private Button homeButton; 
	
	
	public static void reload() {
		mainLayout.requestLayout();
	}
	
	public static Stage primaryStage;
	public static BorderPane mainLayout;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * Hiện menu
	 * @throws IOException
	 */
	public void showLibrarianView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UserManager/Boundary/LibrarianView.fxml"));
		mainLayout = loader.load();
		showBrowseBookPane();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	/**
	 * Hiện bảng duyệt sách.
	 * @throws IOException
	 */
	public void showBrowseBookPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/BookManager/Boundary/BrowseBookPane.fxml"));
		BorderPane browseBook = new BorderPane();
		browseBook.setCenter((Node) loader.load());
		mainLayout.setCenter(browseBook);
	}
	
	
	/**
	 * Hiện màn hình duyệt các đăng ký mượn sách.
	 * @throws Exception
	 */
	public void showRegistrationPane() throws Exception {
		FXMLLoader loader = new FXMLLoader();
//		BorrowingRegistrationPaneController brpc = BorrowingRegistrationPaneController.getInstance();
//		brpc.getBorrowRegistrations();
		loader.setLocation(getClass().getResource("/BorrowingManager/Boundary/LibrarianBorrowingRegistrationPane.fxml"));
		BorderPane librarianItem = new BorderPane();
		librarianItem.setCenter((Node) loader.load());
		mainLayout.setCenter(librarianItem);
	}
	
	
	/**
	 * Hiện màn hình thêm sách hoặc sửa sách.
	 * @throws IOException
	 */
	public void showAddNewBook() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/BookManager/Boundary/AddNewBook.fxml"));
		BorderPane addnewPane = new BorderPane();
		addnewPane.setCenter((Node) loader.load());
		mainLayout.setCenter(addnewPane);
	}
	
	
	/**
	 * Hiện màn hình thêm Copy mới.
	 * @throws IOException
	 */
	public void showAddNewCopy() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/BookManager/Boundary/AddNewCopy.fxml"));
		BorderPane addnewPane = new BorderPane();
		addnewPane.setCenter((Node) loader.load());
		mainLayout.setCenter(addnewPane);
	}
	
	
	/**
	 * Hiện màn hình duyệt các Copy của một đầu sách nhất định.
	 * @throws IOException
	 */
	public void showBrowseCopy() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/BookManager/Boundary/BrowseCopyPane.fxml"));
		BorderPane addnewPane = new BorderPane();
		addnewPane.setCenter((Node) loader.load());
		mainLayout.setCenter(addnewPane);
	}
	
	
	/**
	 * Hiện màn hình update một copy.
	 * @throws IOException
	 */
	public void showUpdateCopy() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/BookManager/Boundary/UpdateCopy.fxml"));
		BorderPane addnewPane = new BorderPane();
		addnewPane.setCenter((Node) loader.load());
		mainLayout.setCenter(addnewPane);
	}
	
	public void SearchAdvance_Click() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/BookManager/Boundary/SearchAdvance.fxml"));
		BorderPane searchPan = new BorderPane();
		searchPan.setCenter((Node) loader.load());
		mainLayout.setCenter(searchPan);
	}
	
	

	
	public void logout() throws Exception {
		BorrowerController.getInstance().user = null;
		LoginController lc = new LoginController();
		lc.start(primaryStage);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Librarian");
//		UserInstance user = LoginController.getUserInformation();
//		System.out.println(user.getUsername() + "," + user.getPassword());
		showLibrarianView();
	}
}
