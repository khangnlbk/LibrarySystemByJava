/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardManager.Boundary;

import CardManager.Controller.CardManagerController;
import CardManager.Entity.BorrowCard;
import Helper.ViewAbstract;
import UserManager.Boundary.LibrarianView;
import static UserManager.Boundary.LibrarianView.mainLayout;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author anonymous588
 */
public class CardManagementView extends ViewAbstract implements Initializable  {
    
    @FXML private TextField searchTextField;
    @FXML private TableView<BorrowCard> browseBookTable;
    @FXML private TableColumn<BorrowCard, Integer> cardidCol;
    @FXML private TableColumn<BorrowCard, Integer> userIdCol;
    @FXML private TableColumn<BorrowCard, String> expiredCol;
    @FXML private TableColumn<BorrowCard, Integer> deposit;
    @FXML private TableColumn<BorrowCard, String> codeCol;
    @FXML private Button searchButton;
    @FXML private Button addButton1;

    private CardManagerController cardManagerController = new CardManagerController();

    public CardManagementView(BorderPane mainLayout) {
        super(mainLayout);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cardidCol.setCellValueFactory(new PropertyValueFactory<BorrowCard, Integer>("card_id"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<BorrowCard, Integer>("user_id"));
        expiredCol.setCellValueFactory(new PropertyValueFactory<BorrowCard, String>("ExpiredDate"));
        deposit.setCellValueFactory(new PropertyValueFactory<BorrowCard, Integer>("deposit"));
        codeCol.setCellValueFactory(new PropertyValueFactory<BorrowCard, String>("ActivateCode"));
        try {
            setTable(cardManagerController.getAllCard());
        } catch (SQLException ex) {
        }
        searchButton.setOnAction((event) -> {
            try {
                search();
            } catch (SQLException ex) {
                Logger.getLogger(CardManagementView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        addButton1.setOnAction((event) -> {
            try {
                showAddCard();
            } catch (IOException ex) {
                Logger.getLogger(CardManagementView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }   
      
    private void setTable(ResultSet sets) throws SQLException{
         browseBookTable.getItems().clear();
        ObservableList<BorrowCard> rows = FXCollections.observableArrayList();
        if(sets!=null){
        while (sets.next()) {
            BorrowCard card=new BorrowCard(sets);
            rows.add(card);
        }
        }
        browseBookTable.setItems(rows);
        
    }
    
    public void search() throws SQLException{
        int card_id = Integer.parseInt(searchTextField.getText());
        setTable(cardManagerController.getCardById(card_id));
    
    }
    
    public void showAddCard() throws IOException { 
        AddNewCardView view= new AddNewCardView(this.mainLayout);
        view.draw();
    }

    @Override
    public void draw() {
                FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CardManagementView.fxml"));
		BorderPane librarianItem = new BorderPane();
                loader.setController(this);
        try {
            librarianItem.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(CardManagementView.class.getName()).log(Level.SEVERE, null, ex);
        }
		mainLayout.setCenter(librarianItem);
    }
}
