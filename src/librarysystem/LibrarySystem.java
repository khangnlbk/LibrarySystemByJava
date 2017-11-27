/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author khangnlbk
 */
public class LibrarySystem extends Application {
    private static Stage primaryStage;
    private static BorderPane mainLayout;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Library System by group 5");
		LoginController lc = new LoginController();
		
		try {
			lc.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static class LoginController {

        public LoginController() {
        }
    }
    
}
