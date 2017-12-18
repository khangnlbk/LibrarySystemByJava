/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManager.Boundary;


import UserManager.Boundary.LoginView;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import Helper.Helper;
import Helper.ViewFactory;
import UserManager.Controller.BorrowerController;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author anonymous588
 */

public class BorrowerView extends Application implements Initializable{

    
    private static Stage primaryStage;
    private static BorderPane mainLayout;
    private BorrowerController bc = BorrowerController.getInstance();
    @FXML public MenuBar bar;
    /**
     * Hàm này hiển thị của sổ giao diện chính của Borrower, bao gồm 1 menu và một bảng chọn có tất cả các sách trong cơ sở dữ liệu.
     * @throws IOException
     */
     
	
        @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            ObservableList<Menu> menus= bar.getMenus();
            menus.forEach((menu)->{
                
                if(menu.getItems().size()==0){
                    
                    menu.setOnAction( new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if(((Menu)event.getSource()).getId()!=null){
                            String menu_name=((Menu)event.getSource()).getId();
                            draw(menu_name);
                            }
                        }
                    });
                }
                else {
                    
                    ObservableList<MenuItem> list=menu.getItems();
                    list.forEach((item)-> {
                        item.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                String menu_name=((MenuItem)event.getSource()).getId();
                                if(menu_name!=null){
                                
                                    if(menu_name.equals("card_jnfo")){
                                        System.out.print("card");
                                        showCardInformation();
                                    }
                                    else {
                                        draw(menu_name);
                                    }
                                }
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
            System.out.print(name);
            factory.getView(name, this.mainLayout).draw();
        }
    public void showBorrowerView() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BorrowerView.fxml"));
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
    @FXML
    public void showBrowseBookPane() throws IOException {
           draw("search");
    }

    /**
     *
     * @throws IOException
     */
  


    /**
     * Hiển thị thông tin BorrowCard của người dùng (Nếu có)
     */
    public void showCardInformation() {
        System.out.print("chan\n");
            ResultSet card = bc.getCard(bc.user.getUser_id());
            if(card!=null) {
                    try {
                            if(card.next()) {
                                    String mess = "Card Id: " + card.getString(card.findColumn("card_id")) + "\n" 
                                                    + "Expired Date: " + card.getString(card.findColumn("expired_date")) + "\n"
                                                    + "Deposit: " + card.getString(card.findColumn("deposit")) + "\n";
                                    Helper.notification(mess);
                            }
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            } else {
                Helper.alertError("You must activate a card to display this information");
            }
    }
    
    public void showRegistrationPane(){
        draw("borrow_register");
    }

	
    /**
     * Logout, hiện lại trang đăng nhập.
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
            BorrowerView.primaryStage = primaryStage;
            BorrowerView.primaryStage.setTitle("Borrower");
            showBorrowerView();
    }
}
