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

import librarysystem.AlertPane;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Class này là controller của giao diện borrower.
 * @author khangnlbk
 */
public class BorrowerViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//    
    @FXML private Button logoutButton;
	
	public static Stage primaryStage;
	public static BorderPane mainLayout;
	
	private BorrowerController bc = BorrowerController.getInstance();

	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}
	
	
	/**
	 * Hàm này hiển thị của sổ giao diện chính của Borrower, bao gồm 1 menu và một bảng chọn có tất cả các sách trong cơ sở dữ liệu.
	 * @throws IOException
	 */
	public void showBorrowerView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UserManager/Boundary/BorrowerView.fxml"));
		mainLayout = loader.load();
		showBrowseBookPane();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Hiển thị bảng chọn sách.
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
	 * Hiển thị bảng các sách đã chọn.
	 * @throws IOException
	 */
	public void showRegistrationPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/BorrowingManager/Boundary/BorrowerRegistrationPane.fxml"));
		BorderPane browseBook = new BorderPane();
		browseBook.setCenter((Node) loader.load());
		mainLayout.setCenter(browseBook);
	}
	
	
	/**
	 * Hiển thị trang kích hoạt Borrow Card.
	 * @throws IOException
	 */
	public void showActivateCard() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UserManager/Boundary/ActivateCard.fxml"));
		BorderPane browseBook = new BorderPane();
		browseBook.setCenter((Node) loader.load());
		mainLayout.setCenter(browseBook);
	}
	
	
	/**
	 * Hiển thị thông tin BorrowCard của người dùng (Nếu có)
	 */
	public void showCardInformation() {
		ResultSet card = bc.getCard(bc.user.getUser_id());
		if(card!=null) {
			try {
				if(card.next()) {
					String mess = "Card Id: " + card.getString(card.findColumn("card_id")) + "\n" 
							+ "Expired Date: " + card.getString(card.findColumn("expired_date")) + "\n"
							+ "Deposit: " + card.getString(card.findColumn("deposit")) + "\n";
					AlertPane.notification(mess);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Logout, hiện lại trang đăng nhập.
	 * @throws Exception
	 */
	public void logout() throws Exception {
		BorrowerController.getInstance().user = null;
		LoginController lc = new LoginController();
		lc.start(primaryStage);
	}
	

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Borrower");
//		UserInstance user = LoginController.getUserInformation();
//		System.out.println(user.getUsername() + "," + user.getPassword());
		showBorrowerView();
	}
}
