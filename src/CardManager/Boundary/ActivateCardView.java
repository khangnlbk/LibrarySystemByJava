/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardManager.Boundary;

import CardManager.Controller.CardManagerController;
import Helper.Helper;
import Helper.ViewAbstract;
import UserManager.Boundary.BorrowerView;
import UserManager.Controller.BorrowerController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author anonymous588
 */
public class ActivateCardView extends ViewAbstract implements Initializable{
    
    @FXML private TextField activateCode;
    @FXML private TextField cardId;
    @FXML private Button check;

    private BorrowerController bc = BorrowerController.getInstance();
    private CardManagerController cardManagerController = new CardManagerController();

    public ActivateCardView(BorderPane mainLayout) {
        super(mainLayout);
    }

    /**
     * Kiểm tra input.
     * @return
     */
    public boolean validation() {
        if(activateCode.getText().isEmpty()|| cardId.getText().isEmpty()) {
            Helper.alertError("Empty Code");
            return false;
        }
        if(!Helper.isNumeric(cardId.getText())) {
            Helper.alertError("Card id have to be a number");
            return false;
        }
        return true;
    }
	
    /**
     * Kiểm tra xem code người dùng nhập vào có đúng không.
     * Nếu đúng thì lưu user_name vào dữ liệu của card đã kích hoạt.
     * @throws IOException
     */
    public void checkCode() throws IOException {
        if(validation()) {
            boolean check= cardManagerController.activateCard(Integer.parseInt(cardId.getText()),
                activateCode.getText(), bc.getUserId());
            if(check){
               BorrowerView bvc = new BorrowerView();
               bvc.showBorrowerView();
               Helper.notification("Success!");
            } else Helper.alertError("Wrong code or id");
        }
    }

    @Override
    public void draw() {
                    FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ActivateCard.fxml"));
            loader.setController(this);
            BorderPane browseBook = new BorderPane();
        try {
            browseBook.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(ActivateCardView.class.getName()).log(Level.SEVERE, null, ex);
        }
            mainLayout.setCenter(browseBook);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    checkCode();
                } catch (IOException ex) {
                    Logger.getLogger(ActivateCardView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
