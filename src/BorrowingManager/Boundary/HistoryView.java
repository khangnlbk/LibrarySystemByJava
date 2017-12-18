/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BorrowingManager.Boundary;

import BorrowingManager.Entity.BorrowRegistrationManager;
import BorrowingManager.Entity.RegistrationDetail;
import Helper.ViewAbstract;
import UserManager.Controller.BorrowerController;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author anonymous588
 */
public class HistoryView extends ViewAbstract implements Initializable {
    @FXML
    private TableView<RegistrationDetail> registTable;
    @FXML
    private TableColumn copyidCol;
    @FXML 
    private TableColumn titleCol;
    @FXML
    private TableColumn borrowdateCol;
    @FXML
    private TableColumn lentdateCol;
    @FXML
    private TableColumn returndateCol;
    @FXML
    private TableColumn statusCol;

    public HistoryView(BorderPane mainLayout) {
        super(mainLayout);
    }

    public HistoryView() {
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        copyidCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("copy_id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("title"));
        borrowdateCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("borrowDate"));
        lentdateCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("lentDate"));
        returndateCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("expectedReturnDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("returned"));
        try {
            setTable();
        } catch (SQLException ex) {
        }
        
    }  
    
    private void setTable() throws SQLException{
        registTable.getItems().clear();
         ObservableList<RegistrationDetail> rows = FXCollections.observableArrayList();
        BorrowRegistrationManager manager=new BorrowRegistrationManager();
        ResultSet set=manager.getHistory(BorrowerController.getInstance().getUserId());
        while(set.next()) {
            RegistrationDetail detail= new RegistrationDetail(set);
            rows.add(detail);
        }
        
        registTable.setItems(rows);
        
    }

    @Override
    public void draw() {
                FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("History.fxml"));
        loader.setController(this);
        BorderPane browseBook = new BorderPane();
        try {
            browseBook.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(HistoryView.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainLayout.setCenter(browseBook);
    }
}
