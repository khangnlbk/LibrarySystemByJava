package Main;

import java.io.IOException;
import java.util.Calendar;

import BorrowingManager.Controller.LibrarianBorrowingRegistrationPaneController;
import UserManager.Controller.BorrowerViewController;
import UserManager.Controller.LibrarianViewController;
import UserManager.Controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * Để khởi động chương trình.
 * @author anonymous588
 *
 */
public class Main extends Application {

	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("Library System");
		LoginController lc = new LoginController();
		
		try {
			lc.start(primaryStage);
		} catch (Exception e) {
                    // TODO Auto-generated catch block

		}
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
}
