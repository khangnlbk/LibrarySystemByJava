package BookManager.Boundary;

import BookManager.Controller.BookManagerController;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BookManager.Entity.Copy;
import Helper.ViewAbstract;
import static UserManager.Boundary.LibrarianView.mainLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 * Quản lý việc hiển thị các thông tin về copy của một đầu sách nhất định.
 * @author anonymous588
 *
 */
public class BrowseCopyPaneView extends ViewAbstract implements Initializable{
    
    @FXML private TableColumn bookIdCol;
    @FXML private TableColumn copyConditionCol;
    @FXML private TableColumn copyIdCol;
    @FXML private TableColumn copyNumberCol;
    @FXML private TableColumn copyTypeCol;
    @FXML private TableColumn priceCol;
    @FXML private TableColumn statusCol;
    @FXML private TableView<Copy> browseCopyTable;

    private BookManagerController bc =new  BookManagerController();
    private int book_id;
	
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public BrowseCopyPaneView(BorderPane mainLayout) {
        super(mainLayout);
    }
    
    public BrowseCopyPaneView(BorderPane mainLayout, int book_id) {
        super(mainLayout);
        this.book_id=book_id;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        bookIdCol.setCellValueFactory(new PropertyValueFactory<Copy, String>("book_id"));
        copyIdCol.setCellValueFactory(new PropertyValueFactory<Copy, String>("copy_id"));
        copyConditionCol.setCellValueFactory(new PropertyValueFactory<Copy, String>("copy_condition"));
        copyNumberCol.setCellValueFactory(new PropertyValueFactory<Copy, String>("copy_number"));
        copyTypeCol.setCellValueFactory(new PropertyValueFactory<Copy, String>("copy_type"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Copy, String>("price"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Copy, String>("status"));

        try {
            showSetCopy(bc.getCopy(book_id));
        } catch (SQLException e) {
        }

    }
	
    /**
     *
     * @param setCopy
     * @throws SQLException
     */
    public void showSetCopy(ResultSet setCopy) throws SQLException {
        browseCopyTable.getItems().clear();

        ObservableList<Copy> rows = FXCollections.observableArrayList();
        while(setCopy.next()) {
                Copy tmp = new Copy(setCopy);
                rows.add(tmp);
        }
        browseCopyTable.setItems(rows);
    }

    @Override
    public void draw() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BrowseCopyPane.fxml"));
        loader.setController(this);
	BorderPane librarianItem = new BorderPane();
        try {
            librarianItem.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(BrowseCopyPaneView.class.getName()).log(Level.SEVERE, null, ex);
        }
	mainLayout.setCenter(librarianItem);
    }
	
}
