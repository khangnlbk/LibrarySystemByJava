package Helper;

import java.text.NumberFormat;
import java.text.ParsePosition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Bảng thông báo.
 * @author anonymous588
 *
 */
public class Helper {

    /**
     *
     * @param mess
     */
    public static void alertError(String mess) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Error");
        alert.setContentText(mess);
        alert.showAndWait();
    }

    /**
     *
     * @param mess
     */
    public static void notification(String mess) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(mess);
        alert.setContentText("");
        alert.showAndWait();
    }
        
        /**
     * Check a string's content if it is a number
     * @param str string that want to check
     * @return true if it is a number, false in the other case
     */
    public static boolean isNumeric(String str)
    {
      NumberFormat formatter = NumberFormat.getInstance();
      ParsePosition pos = new ParsePosition(0);
      formatter.parse(str, pos);
      return str.length() == pos.getIndex();
    }
}
