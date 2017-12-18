// 

// LoginView 
// Role: 0 = admin, 1 = librarian, 2 = borrower

package UserManager.Boundary;

import java.io.IOException;
import Helper.Helper;

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
 * LoginView quản lý đăng nhập người dùng, kiểm tra đối chiếu username và password với DB
 * 
 * @author anonymous588
 *
 */
public class LoginView extends Application{

    /**
     *
     */
    public static Stage primaryStage;

    /**
     *
     */
        public static BorderPane mainLayout;
        private UserController controller = new UserController();
	
	@FXML private TextField username;
	@FXML private TextField password;
	
    /**
     *
     * @return
     */
    public boolean validation() {
		if(username.getText().isEmpty()) {
			Helper.alertError("Empty Username Field!");
			return false;
		}
		if(password.getText().isEmpty()) {
			Helper.alertError("Empty Password Field!");
			return false;
		}
		return true;
	}
	
    /**
     *
     * @throws IOException
     */
    public void showLoginPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UserManager/Boundary/Login.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
    /**
     *
     * @throws Exception
     */
    public void openRegister() throws Exception {
            RegisterView regCon = new RegisterView();
            regCon.start(primaryStage);
        }
	
	/**
	 * Kiểm tra username và password nhập vào. Nếu đúng sẽ gán lại thông tin của User vào biến BorrowerController.borrower
	 * @throws Exception
	 */
	public void checkLogin() throws Exception {
            String user_name= username.getText();
            String pass = password.getText();
		if(validation()) {
			if(controller.checkUser(user_name, pass)) {
                                User currentUser=new User(controller.getUser(user_name, pass));
                                controller.Login(currentUser).start(primaryStage);
				Helper.notification("Login Successfully!");
			}
			else {
				Helper.alertError("Wrong Username of Password");
			}
		}
	}	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		LoginView.primaryStage = primaryStage;
		LoginView.primaryStage.setTitle("Log in");
		showLoginPane();
	}
}
