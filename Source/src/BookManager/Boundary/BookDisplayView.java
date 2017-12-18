package BookManager.Boundary;

import BookManager.Controller.BookManagerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BorrowingManager.Controller.BorrowerRegistration;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import BookManager.Entity.Book;
import static Helper.Helper.alertError;
import static Helper.Helper.notification;
import Helper.ViewAbstract;
import UserManager.Boundary.LibrarianView;
import UserManager.Controller.BorrowerController;
import UserManager.Boundary.BorrowerView;
import UserManager.Boundary.LibrarianView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.BorderPane;

/**
 * Controller cho Boundary DisplayBook. Điều khiển các hành vi trong màn hình hien thi sach
 *
 * @author anonymous588
 */
public class BookDisplayView implements Initializable {

    @FXML private TableView<Book> browseBookTable;
    @FXML private TableColumn bookidCol;
    @FXML private TableColumn authorCol;
    @FXML private TableColumn isbnCol;
    @FXML private TableColumn publisherCol;
    @FXML private TableColumn titleCol;
    @FXML private TableColumn categoryCol;
    @FXML private TableColumn discriptionCol;
    @FXML private TableColumn priceCol;

    private BookManagerController bc = new BookManagerController();
    private BorderPane parentPane;
    private BorrowerController currentUser = BorrowerController.getInstance();
    private BorrowerRegistration borrowerRegistration = BorrowerRegistration.getInstance();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        bookidCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("book_id"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        discriptionCol.setCellValueFactory(new PropertyValueFactory<Book, String>("discription"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("price"));
        browseBookTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                    try {
                        if (currentUser.user.getRole().equals("2")) {
                            addBookToRegistration();
                        } 
                        else if(currentUser.user.getRole().equals("1")) {
                            seeCopy();
                        }
                    } catch (SQLException | IOException e1) {
                    } 
                }
            }
        });
        try {
            showSetBook(bc.getAllBook());
        } catch (SQLException e) {
        }
    }

    /**
     * See all the copies 
     * @throws IOException 
     */
    public void seeCopy() throws IOException {
        Book b = browseBookTable.getSelectionModel().getSelectedItem();
          BrowseCopyPaneView view = new BrowseCopyPaneView(this.parentPane);
          view.setBook_id(b.getBook_id());
          view.draw();
    }

    /**
     * Add book to registration
     * @throws SQLException 
     */
    public void addBookToRegistration() throws SQLException {
        Book b = browseBookTable.getSelectionModel().getSelectedItem();
        int unreturn = 0;
        if (bc.isBorrowalbe(b.getBook_id())) {
            unreturn = currentUser.getUnReturnBook();
            if (borrowerRegistration.getRegistration().size() > (5 - unreturn)) {
                alertError("Out Of Max Number \n You are having " +unreturn+ " unreturned book");
                return;
            }
            for(int i = 0; i< borrowerRegistration.getRegistration().size(); i++) {
                if(borrowerRegistration.getRegistration().get(i).getBook_id() == b.getBook_id()) {
                        alertError("Duplicate Book");
                        return;
                }
            }
            borrowerRegistration.getRegistration().add(b);
            notification("Successs");
            currentUser.unReturnBook++;

        } else {
            alertError("Book Is Not Borrowable \n It can be the book is referenced or out of copy.");
        }
    }

    /**
     * Hiển thị một danh sách các đầu sách.
     * @param setBook Danh sách truyền vào.
     * @throws SQLException
     */
    public void showSetBook(ResultSet setBook) throws SQLException {
        browseBookTable.getItems().clear();
        ObservableList<Book> rows = FXCollections.observableArrayList();
        while (setBook.next()) {
            Book tmp = new Book(setBook);
            ResultSet categorySet = bc.getCategories(tmp.getBook_id());
            String category = "";
            while (categorySet.next()) {
                category += categorySet.getString(categorySet.findColumn("name")) + ", ";
            }
            tmp.setCategory(category);
            rows.add(tmp);
        }
        browseBookTable.setItems(rows);
    }
    
    public void setParentPane(BorderPane pane){
        this.parentPane=pane;
    }


}
