package Main;

import UserManager.Boundary.LoginView;
import javafx.application.Application;
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
		LoginView lc = new LoginView();
		
		try {
			lc.start(primaryStage);
		} catch (Exception e) {
                    // TODO Auto-generated catch block

		}
	}
	
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
		launch(args);
	}
}
