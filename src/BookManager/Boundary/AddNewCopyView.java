package BookManager.Boundary;

import BookManager.Controller.BookManagerController;
import BookManager.Controller.SearchController;
import static Helper.Helper.alertError;
import static Helper.Helper.isNumeric;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * Controller cho Boundary AddNewCopy. 
 * @author anonymous588
 *
 */
public class AddNewCopyView extends ViewAbstract implements Initializable {
	
    @FXML private ComboBox typeOfCopy;
    @FXML private TextField bookNumber;
    @FXML private TextField quantity;
    @FXML private TextField price;
    @FXML private TextField condition;

    private String _newBookNumber;

    private BookManagerController bc = new BookManagerController();
    private SearchController search = new SearchController();

    public AddNewCopyView(BorderPane mainLayout) {
        super(mainLayout);
    }

    public AddNewCopyView() {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
            // TODO Auto-generated method stub
            if(_newBookNumber == null) {
                    bookNumber.clear();
            } else {
                    bookNumber.setText(_newBookNumber);
            }

            ObservableList<String> copy_type = 
                        FXCollections.observableArrayList(
                            "Reference",
                            "Available"
                        );
            typeOfCopy.getItems().addAll(copy_type);
            typeOfCopy.getSelectionModel().select(0);

    } 
    
    /**
     * Lay gia tri book number cho copy
     * @return 
     */
    public String get_bookNumber() {
        return _newBookNumber;
    }
    /**
     * Set gia tri cho Booknumber
     * @param newBookNumber
     */
    public void set_bookNumber(String newBookNumber) {
        this._newBookNumber = newBookNumber;
    }

    /**
     * Validate the fields of add new copy form
     * @return true if all fields is correct, false in the other case
     */
    public boolean validation() {
        if(typeOfCopy.getSelectionModel().isEmpty()) {
                alertError("Empty Type Of Copy");
                return false;
        }

        if(bookNumber.getText().isEmpty()) {
                alertError("Empty Book Number");
                return false;
        }

        if(price.getText().isEmpty()) {
                alertError("Empty Price");
                return false;
        } else {
                if(!isNumeric(price.getText())) {
                        alertError("Price must be a number");
                        return false;
                }
        }

        if(quantity.getText().isEmpty()) {
                alertError("Empty Number Of New Copy");
                return false;
        } else {
                if(!isNumeric(quantity.getText())) {
                        alertError("Quantity must be a number");
                        return false;
                }
        }

        return true;
    }

    /**
     * Xác nhận thêm.
     * @throws SQLException
     * @throws IOException
     */
    public void submit() throws SQLException, IOException {
        if(validation()) {
            ResultSet rs = search.searchBook(bookNumber.getText(), "book_number");
            if(rs.next()) {
                int book_id = rs.getInt(rs.findColumn("book_id"));
                int copyCount = bc.getCountOfCopy(book_id) + 1;
                String copyNumber;
                int quant = Integer.parseInt(quantity.getText());
                for(int i = 1; i<= quant; i++) {
                        int sequence = copyCount +i;
                        copyNumber = bookNumber.getText() + "-" + sequence;
                        bc.addNewCopy(book_id, copyNumber, typeOfCopy.selectionModelProperty().toString().toLowerCase(), Integer.parseInt(price.getText()), condition.getText(), typeOfCopy.getSelectionModel().getSelectedItem().toString().toLowerCase());
                }

            } else {
                alertError("Found Nothing with that Book Number.");
            }

            set_bookNumber(null);
            LibrarianView lc = new LibrarianView();
            lc.showLibrarianView();
        }	
}

    @Override
    public void draw() {
        FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("AddNewCopy.fxml"));
        loader.setController(this);
	BorderPane addnewPane = new BorderPane();	
        try {
            addnewPane.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(AddNewCopyView.class.getName()).log(Level.SEVERE, null, ex);
        }
	mainLayout.setCenter(addnewPane);
    }

}
