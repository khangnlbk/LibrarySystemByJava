/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManager.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Main.AlertPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * Class này là Controller của giao diện ActivateCard (Javafx)
 * @author khangnlbk
 */
public class ActivateCardController {
    @FXML private TextField activateCode;

	BorrowerController bc = BorrowerController.getInstance();
	
	
	/**
	 * Kiểm tra input.
	 * @return
	 */
	public boolean validation() {
		if(activateCode.getText().isEmpty()) {
			AlertPane.alertError("Empty Code");
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
			String card_id = bc.checkBorrorCard(activateCode.getText()); 
			if(card_id != null) {
				AlertPane.notification("Success!");
				bc.activateCard(bc.getInstance().user.getUser_id(), card_id);
				BorrowerViewController bvc = new BorrowerViewController();
				bvc.showBorrowerView();
			} else {
				AlertPane.alertError("Wrong Code!");
				return;
			}
		}
	}
}
