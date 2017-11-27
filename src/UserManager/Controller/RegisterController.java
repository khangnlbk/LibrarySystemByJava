// LoginController quản lý đăng nhập người dùng.
// LoginController lấy thông tin trực tiếp từ database.
// Role:  1 = librarian, 2 = borrower

package UserManager.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Main.AlertPane;
import Main.DBConnect;
import TableProperty.UserInstance;
import static UserManager.Controller.LoginController.mainLayout;
import static UserManager.Controller.LoginController.primaryStage;
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
public class RegisterController extends Application{

	public static Stage primaryStage;
	public static BorderPane mainLayout;
	private Connection conn = DBConnect.getConnection();
	private PreparedStatement ps;
	
	@FXML private TextField usernameTF;
	@FXML private TextField passwordTF;
	@FXML private TextField fullnameTF;
	@FXML private TextField emailTF;
	@FXML private TextField addressTF;
	
	public boolean validation() {
		if(usernameTF.getText().isEmpty()) {
			AlertPane.alertError("Empty Username Field!");
			return false;
		}
		if(passwordTF.getText().isEmpty()) {
			AlertPane.alertError("Empty Password Field!");
			return false;
		}
		if(fullnameTF.getText().isEmpty()) {
			AlertPane.alertError("Empty Full Name Field!");
			return false;
		}
		if(emailTF.getText().isEmpty()) {
			AlertPane.alertError("Empty Email Field!");
			return false;
		}
		if(addressTF.getText().isEmpty()) {
			AlertPane.alertError("Empty Address Field!");
			return false;
		}
		
		return true;
	}
	
	public void showRegisterPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UserManager/Boundary/Register.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void submitRegister() throws Exception {
		if(validation()) {
			String insert_user = "insert into user (role, username, password, full_name, email, age, address, gender, student_id, deposit) VALUE (?,?,?,?,?,?,?,?,?,?)";
			String query = "select * from user where username=? and password=? ";
			ps = conn.prepareStatement(query);
			ps.setString(1, "student");
			ps.setString(2, usernameTF.getText());
			ps.setString(2, passwordTF.getText());
			ps.setString(2, emailTF.getText());
			ps.setString(2, usernameTF.getText());
			ps.setString(2, usernameTF.getText());
			ps.setString(2, usernameTF.getText());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				BorrowerController.getInstance().user = new UserInstance(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11)
						);
				int role = Integer.parseInt(rs.getString(rs.findColumn("role")));
				if(role == 1) {
					LibrarianViewController librarianView = new LibrarianViewController();				
					librarianView.start(primaryStage);
				}
				if(role == 2) {
					BorrowerViewController borrowerView = new BorrowerViewController();
					borrowerView.start(primaryStage);
				}
				AlertPane.notification("Login Successfully!");
			}
			else {
				AlertPane.alertError("Wrong Username of Password");
			}
		}
	}	

        public void showLoginPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UserManager/Boundary/Login.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
        
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Librarian");
		showLoginPane();
	}
        
        
}
