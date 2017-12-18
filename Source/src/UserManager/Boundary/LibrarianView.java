package UserManager.Boundary;


import Helper.ViewFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import UserManager.Controller.BorrowerController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



/**
 * Class này là Controller của giao diện Librarian. Bao gồm 1 menu và 1 bảng hiện thị tất cả các đầu sách.
 * @author aanonymous588
 *
 */
public class LibrarianView extends Application implements Initializable{
	
    /**
     *
     */
    public static void reload() {
		mainLayout.requestLayout();
	}
	
    /**
     *
     */
    public static Stage primaryStage;

    /**
     *
     */
    public static BorderPane mainLayout;
    @FXML public MenuBar bar;
	
        @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            ObservableList<Menu> menus= bar.getMenus();
            menus.forEach((menu)->{
                System.out.print(menu.getText());
                if(menu.getItems().size()==0){
                    System.out.print(menu.getItems().size()==0);
                    menu.setOnAction( new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String menu_name=((Menu)event.getSource()).getId();
                            draw(menu_name);
                        }
                    });
                }
                else {
                    System.out.print("have");
                    ObservableList<MenuItem> list=menu.getItems();
                    list.forEach((item)-> {
                        System.out.print(item.getId());
                        item.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.print("ngu hoc");
                                String menu_name=((MenuItem)event.getSource()).getId();
                                draw(menu_name);
                            }
                        });

                    });
                    
                }
                
            });
            
	}
	
	
	/**
	 * Hiện menu
	 * @throws IOException
	 */
        
        public void draw(String name){
            ViewFactory factory=new ViewFactory();
            factory.getView(name, this.mainLayout).draw();
        }
	public void showLibrarianView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("LibrarianView.fxml"));
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
                draw("search");
	}
	
	
	/**
	 * Hiện màn hình duyệt các đăng ký mượn sách.
	 * @throws Exception
	 */
    /**
     *
     * @throws Exception
     */
    public void logout() throws Exception {
		BorrowerController.getInstance().user = null;
		LoginView lc = new LoginView();
		lc.start(primaryStage);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		LibrarianView.primaryStage = primaryStage;
		LibrarianView.primaryStage.setTitle("Librarian");
		showLibrarianView();
	}
	
}
