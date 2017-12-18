// LoginController quản lý đăng nhập người dùng.
// LoginController lấy thông tin trực tiếp từ database.
// Role:  1 = librarian, 2 = borrower

package UserManager.Boundary;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Helper.Helper;
import Helper.DBConnect;
import static UserManager.Boundary.LoginView.primaryStage;

import UserManager.Controller.BorrowerController;
import UserManager.Controller.UserController;
import UserManager.Entity.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * LoginController quản lý đăng nhập người dùng, kiểm tra đối chiếu username và password với DB
 * 
 * @author anonymous588
 *
 */
public class RegisterView extends Application{

    /**
     *
     */
    public static Stage primaryStage;

    /**
     *
     */
    public static BorderPane mainLayout;

	private Connection conn = DBConnect.getConnection();
	private PreparedStatement ps;
        private UserController controller= new UserController();
	
	@FXML private TextField usernameTF;
	@FXML private TextField passwordTF;
	@FXML private TextField fullnameTF;
	@FXML private TextField emailTF;
	@FXML private TextField addressTF;

	
    /**
     *
     * @return
     */
    public boolean validation() {
		if(usernameTF.getText().isEmpty()) {
			Helper.alertError("Empty Username Field!");
			return false;
		}
		if(passwordTF.getText().isEmpty()) {
			Helper.alertError("Empty Password Field!");
			return false;
		}
		if(fullnameTF.getText().isEmpty()) {
			Helper.alertError("Empty Full Name Field!");
			return false;
		}
		if(emailTF.getText().isEmpty()) {
			Helper.alertError("Empty Email Field!");
			return false;
		}
		if(addressTF.getText().isEmpty()) {
			Helper.alertError("Empty Address Field!");
			return false;
		}
		
		return true;
	}
	
    /**
     *
     * @throws IOException
     */
    public void showRegisterPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UserManager/Boundary/Register.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
    /**
     *
     * @throws Exception
     */
    public void submitRegister() throws Exception {
		if(validation()) {
                    controller.register("2", usernameTF.getText(), passwordTF.getText(), fullnameTF.getText(), emailTF.getText(), addressTF.getText());
                    User currentUser=new User(controller.getUser(usernameTF.getText(), passwordTF.getText()));
                    controller.Login(currentUser).start(primaryStage);
                    Helper.notification("Login Successfully!");
			}
			else {
				Helper.alertError("Wrong Username of Password");
			}
	}	
        
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		RegisterView.primaryStage = primaryStage;
		RegisterView.primaryStage.setTitle("Sign up");
		showRegisterPane();
	}
        
        
}
