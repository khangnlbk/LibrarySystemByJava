/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManager.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author khangnlbk
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }
    public static Stage primaryStage;
	public static BorderPane mainLayout;
	private Connection conn = DBConnect.getConnection();
	private PreparedStatement ps;
	
	@FXML private TextField username;
	@FXML private TextField password;
	
	public boolean validation() {
		if(username.getText().isEmpty()) {
			AlertPane.alertError("Empty Username Field!");
			return false;
		}
		if(password.getText().isEmpty()) {
			AlertPane.alertError("Empty Password Field!");
			return false;
		}
		return true;
	}
	
	
	public void showLoginPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UserManager/Boundary/Login.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	/**
	 * Kiểm tra username và password nhập vào. Nếu đúng sẽ gán lại thông tin của User vào biến BorrowerController.borrower
	 * @throws Exception
	 */
	public void checkLogin() throws Exception {
		if(validation()) {
			String query = "select * from user where username=? and password=? ";
			ps = conn.prepareStatement(query);
			ps.setString(1, username.getText());
			ps.setString(2, password.getText());
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
				if(role == 0) {
					//show admin pane
				}
				if(role == 1) {
					LibrarianViewController librarianView = new LibrarianViewController();				
					librarianView.start(primaryStage);
					AlertPane.notification("Login Successfully!");
				}
				if(role == 2) {
					BorrowerViewController borrowerView = new BorrowerViewController();
					borrowerView.start(primaryStage);
					AlertPane.notification("Login Successfully!");
				}
			}
			else {
				AlertPane.alertError("Wrong Username of Password");
			}
		}
	}	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Librarian");
		showLoginPane();
	}    
    
}
