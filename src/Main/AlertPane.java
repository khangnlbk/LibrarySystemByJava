package Main;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * Bảng thông báo.
 * @author anonymous588
 *
 */
public class AlertPane {

	public static void alertError(String mess) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText("Error");
		alert.setContentText(mess);
		alert.showAndWait();
	}
	
	public static void notification(String mess) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notification");
		alert.setHeaderText(mess);
		alert.setContentText("");
		alert.showAndWait();
	}
}
