/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardManager.Boundary;

import CardManager.Controller.CardManagerController;
import static Helper.Helper.alertError;
import Helper.ViewAbstract;
import UserManager.Boundary.LibrarianView;
import static UserManager.Boundary.LibrarianView.mainLayout;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author anonymous588
 */
public class AddNewCardView extends ViewAbstract implements Initializable {
    
    private CardManagerController cardManagerController = new CardManagerController();
    @FXML private TextField cardId;
    @FXML private TextField activate;
    @FXML private DatePicker date = new DatePicker(LocalDate.now());
    @FXML private TextField deposit;
    @FXML private Button submitButton;

    public AddNewCardView(BorderPane mainLayout) {
        super(mainLayout);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cardId.setText(Integer.toString(cardManagerController.getLastId()));
        submitButton.setOnAction((event) -> {
            try {
                submit();
            } catch (IOException ex) {
                Logger.getLogger(AddNewCardView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    public void submit() throws IOException {
        if(activate.getText().isEmpty()||date.getValue()==null|| deposit.getText().isEmpty())
            alertError("Empty Book Number");
        else {
            Date date1= Date.valueOf(date.getValue());
            cardManagerController.issueNewCard(Integer.parseInt(cardId.getText()),
                activate.getText(),date1,Integer.parseInt(deposit.getText()));
//            LibrarianView lb= new LibrarianView();
//            lb.showCardManagement();
        }
            
    }

    @Override
    public void draw() {
        		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AddNewCardView.fxml"));
                loader.setController(this);
		BorderPane librarianItem = new BorderPane();
        try {
            librarianItem.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(AddNewCardView.class.getName()).log(Level.SEVERE, null, ex);
        }
		mainLayout.setCenter(librarianItem);
    }
}
