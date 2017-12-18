/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BorrowingManager.Boundary;

import BookManager.Entity.Book;
import BorrowingManager.Controller.BorrowerRegistration;
import Helper.Helper;
import Helper.ViewAbstract;
import UserManager.Boundary.BorrowerView;
import static UserManager.Boundary.LibrarianView.mainLayout;
import UserManager.Controller.BorrowerController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author anonymous588
 */
public class BorrowerRegistrationPaneView extends ViewAbstract implements Initializable {
    
    @FXML private TableView<Book> booksTable;
    @FXML private TableColumn bookidCol;
    @FXML private TableColumn authorCol;
    @FXML private TableColumn isbnCol;
    @FXML private TableColumn publisherCol;
    @FXML private TableColumn titleCol;
    @FXML private TableColumn categoryCol;
    @FXML private TableColumn discriptionCol;
    @FXML private Button confirmButton;

    private BorrowerController currentUser= BorrowerController.getInstance();
    private BorrowerRegistration borrowerRegistration = BorrowerRegistration.getInstance();

    public BorrowerRegistrationPaneView(BorderPane mainLayout) {
        super(mainLayout);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
            // TODO Auto-generated method stub
            bookidCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("book_id"));
            authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
            titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
            isbnCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
            categoryCol.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
            publisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
            discriptionCol.setCellValueFactory(new PropertyValueFactory<Book, String>("discription"));
            showRegistrationBooks();
            confirmButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        sendRegistration();
                    } catch (IOException ex) {
                        Logger.getLogger(BorrowerRegistrationPaneView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(BorrowerRegistrationPaneView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
    }
	
    /**
     * show all registration books
     */
    public void showRegistrationBooks() {
        booksTable.getItems().clear();
        ObservableList<Book> books = borrowerRegistration.getRegistration();
        booksTable.setItems(books);
    }

    /**
     * send registering request
     * @throws IOException
     */
    public void sendRegistration() throws IOException, SQLException {
        String mess= borrowerRegistration.checkRequest();
        if(!mess.equals("success"))
            Helper.alertError(mess);
        else {
            ViewAbstract view=borrowerRegistration.saveRegistration();
            if(view!=null) {
                view.setPane(this.mainLayout);
                view.draw();
            }
            else {
                Helper.alertError("Please retry again later\n");
            }
        }

    }

    @Override
    public void draw() {
 		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("BorrowerRegistrationPane.fxml"));
                loader.setController(this);
		BorderPane librarianItem = new BorderPane();
        try {
            librarianItem.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(BorrowerRegistrationPaneView.class.getName()).log(Level.SEVERE, null, ex);
        }
		mainLayout.setCenter(librarianItem);
    }

}
