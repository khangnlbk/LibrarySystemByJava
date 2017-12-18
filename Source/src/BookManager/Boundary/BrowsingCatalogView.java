/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookManager.Boundary;

/**
 * Controller cho man hinh browsing catalog
 * @author anonymous588
 */

import BookManager.Controller.BookManagerController;
import BookManager.Controller.SearchController;
import BookManager.Entity.BookManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BorrowingManager.Controller.BorrowerRegistration;
import Helper.ViewAbstract;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

/**
 *
 * @author anonymous588
 */
public class BrowsingCatalogView extends ViewAbstract implements Initializable {
    @FXML
    private Button searchButton;
    @FXML private ComboBox catalogBox;
    @FXML private Window bookdisplay;
    @FXML 
    private ComboBox optionBox;
    @FXML private BookDisplayView displayController;
    

    private SearchController search=new SearchController();
    private BorrowerRegistration registration = BorrowerRegistration.getInstance();

    public BrowsingCatalogView(BorderPane mainLayout) {
        super(mainLayout);
    }

    public void initialize(URL arg0, ResourceBundle arg1) {
        displayController.setParentPane(this.mainLayout);
        optionBox.setItems(search.GetListCatalog());
        optionBox.getSelectionModel().select(0);
            try {
             getCatalog();
              
            } catch (SQLException ex) {
                Logger.getLogger(BrowsingCatalogView.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
             showBookfromCatalog();
            } catch (SQLException ex) {
                Logger.getLogger(BrowsingCatalogView.class.getName()).log(Level.SEVERE, null, ex);
            }
        optionBox.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    getCatalog();
                } catch (SQLException ex) {
                    Logger.getLogger(BrowsingCatalogView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        catalogBox.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    showBookfromCatalog();
                } catch (SQLException ex) {
                    Logger.getLogger(BrowsingCatalogView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    @FXML
    private void getCatalog() throws SQLException{
    String args=(String) optionBox.getValue();
    ObservableList<String> Option= search.getListItems(args);
    this.catalogBox.setItems(Option);
    this.catalogBox.getSelectionModel().select(0);  
    }
    @FXML
    private void showBookfromCatalog() throws SQLException{
        displayController.showSetBook(search.getBookinCatalog((String)optionBox.getValue(), (String)catalogBox.getValue()));
    }

    @Override
    public void draw() {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BrowsingCatalog.fxml"));
            BorderPane browseBook = new BorderPane();
            loader.setController(this);
        try {
            browseBook.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(BrowsingCatalogView.class.getName()).log(Level.SEVERE, null, ex);
        }
            mainLayout.setCenter(browseBook);
    }
    
}

