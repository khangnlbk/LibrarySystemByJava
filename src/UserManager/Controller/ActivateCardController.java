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
 * @author anonymous588
 *
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


//c8d8144b245b092e910c9ff0686a2def leekien
//f798532af0b7ff84bf0766f07d17f257 thuyninh
//e10e2e1ded77e1aca515aa4daadf0d56 dxmanh