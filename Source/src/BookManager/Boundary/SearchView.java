package BookManager.Boundary;

import BookManager.Boundary.BookDisplayView;
import BookManager.Controller.BookManagerController;
import BookManager.Controller.SearchController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BorrowingManager.Controller.BorrowerRegistration;
import static Helper.Helper.alertError;
import Helper.ViewAbstract;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * Controller cho Boundary BrowseBook. Điều khiển các hành vi trong màn hình
 * duyệt sách tùy theo Role của User.
 *
 * @author anonymous588
 */
public class SearchView extends ViewAbstract implements Initializable {

    @FXML private TextField searchTextField;
    @FXML private ComboBox optionBox;
    @FXML private BookDisplayView displayController;

    private SearchController bc = new SearchController();
    private BookManagerController books = new BookManagerController();
    private BorrowerRegistration registration = BorrowerRegistration.getInstance();
    @FXML
    private Button searchButton;

    public SearchView(BorderPane mainLayout) {
        super(mainLayout);
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        //click on row book table handle
        displayController.setParentPane(this.mainLayout);
        ObservableList<String> searchOption = FXCollections.observableArrayList(
                "Title",
                "Author",
                "Publisher",
                "ISBN"
        );
        optionBox.setItems(searchOption);
        optionBox.getSelectionModel().select(0);

        try {
            showSetBook(books.getAllBook());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        searchButton.setOnMouseClicked((event) -> {
            try {
                searchBook();
            } catch (SQLException ex) {
                Logger.getLogger(SearchView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     *
     * @throws SQLException
     */
    public void searchBook() throws SQLException {
        if (searchTextField.getText().isEmpty()) {
            alertError("Empty Search Field");
            return;
        }
        ResultSet rs = bc.searchBook(searchTextField.getText(), optionBox.getSelectionModel().getSelectedItem().toString().toLowerCase());
        showSetBook(rs);
    }

    /**
     * Hiển thị một danh sách các đầu sách.
     *
     * @param setBook Danh sách truyền vào.
     * @throws SQLException
     */
    public void showSetBook(ResultSet setBook) throws SQLException {
        displayController.showSetBook(setBook);
    }

    @Override
    public void draw() {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Search.fxml"));
            loader.setController(this);
            
            BorderPane browseBook = new BorderPane();
        try {
            browseBook.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(SearchView.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            mainLayout.setCenter(browseBook);
    }

}
