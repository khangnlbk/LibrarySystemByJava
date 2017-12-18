package BookManager.Boundary;

import BookManager.Controller.BookManagerController;
import BookManager.Controller.SearchController;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BorrowingManager.Entity.BorrowRegistration;
import BookManager.Entity.Category;
import static Helper.Helper.alertError;
import static Helper.Helper.isNumeric;
import Helper.ViewAbstract;
import UserManager.Boundary.LibrarianView;
import static UserManager.Boundary.LibrarianView.mainLayout;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;


/**
 * Controller cho Boundary AddNewBook. 
 * @author anonymous588
 *
 */
public class AddNewBookView extends ViewAbstract implements Initializable  {
    @FXML private TextField author, price, isbn, publisher, booktitle;
    @FXML private Label bookNumber;
    @FXML private TableColumn checkboxCol, categoryCol;
    @FXML private TableView<Category> categoryTable;
    @FXML private ComboBox classificationBox;
    @FXML private TextArea discriptionBox;

    private BookManagerController bc = new BookManagerController();
    private SearchController search = new SearchController();
    private ObservableList<String> classifications = FXCollections.observableArrayList();

    public AddNewBookView(BorderPane mainLayout) {
        super(mainLayout);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        classifications.clear();

        checkboxCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, CheckBox>, ObservableValue<CheckBox>>() {
            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<Category, CheckBox> arg0) {
                final Category category = arg0.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().setValue(category.isChecked());

                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> ov,
                            Boolean old_val, Boolean new_val) {

                        if(new_val == true) {
                                updateClassification(category.getName(), category.getEncode(), 0);
                        }
                        if(new_val == false) {
                                updateClassification(category.getName(), category.getEncode(), 1);
                        }
                        category.setChecked(new_val);

                    }
                });
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });

        categoryCol.setCellValueFactory(new PropertyValueFactory<BorrowRegistration, String>("name"));

            // init classification combobox
            ObservableList<Category> rows = FXCollections.observableArrayList();
            try {
                ResultSet rs = bc.getAllCatalog();
                while(rs.next()) {
                    int category_id = rs.getInt(rs.findColumn("category_id"));
                    String name = rs.getString(rs.findColumn("name"));
                    String encode = rs.getString(rs.findColumn("encode"));
                    boolean checked = false;
                    Category tmp = new Category(category_id, name, encode, checked);
                    rows.add(tmp);
                }
                categoryTable.setItems(rows);
            } catch (SQLException e) {
            }

    }


    // action-listener for gen Book Number + change classification's item 
    /**
     * Update các lựa chọn trong Classification dựa vào các category đã chọn.
     * @param name name of category
     * @param encode encode of category
     * @param type  type = 0 : update, type =1: delete
     */
    public void updateClassification(String name, String encode, int type) {
        if(!classifications.contains(name + "/" + encode) && type == 0) {
            classifications.add(name + "/" + encode);
        }
        if(classifications.contains(name + "/" + encode) && type == 1) {
            classifications.remove(name + "/" + encode);
        }
        classificationBox.setItems(classifications);
    }

    // generate book number by selected classification
    /**
     * Tạo BookNumber cho Book dựa vào Classification đã chọn và số thứ tự trong DB.
     */
    public void genBookNumber() {
        String selectedItem = (String)classificationBox.getSelectionModel().getSelectedItem();
        String[] tmp = selectedItem.split("/");
        int sequence = bc.getNewBookSequence(tmp[1]);
        bookNumber.setText(tmp[1] + String.format("%04d", sequence));
    }


    /**
     * Add new a book to database
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public void addBook() throws ClassNotFoundException, SQLException, IOException {
        if(validation()) {
            // add new
            ArrayList<Integer> categories=new ArrayList<>();
            ObservableList<Category> rows = FXCollections.observableArrayList();
            rows = categoryTable.getItems();
            for(int i = 0; i < rows.size(); i ++) {
                if(rows.get(i).isChecked()) {
                    categories.add(rows.get(i).getCategory_id());
                }
            }

        ViewAbstract view =bc.addBook(booktitle.getText(), author.getText(), publisher.getText(), isbn.getText(), bookNumber.getText(), discriptionBox.getText(), Integer.parseInt(price.getText()), categories);
        view.setPane(this.mainLayout);
        view.draw();

        }
    }

    /**
     * Cancel process of adding or updating book
     * @throws IOException 
     */
    public void cancelAdd() throws IOException {
        LibrarianView lb = new LibrarianView();
        lb.showLibrarianView();
    }

    /**
     * Validate the fields of add new book form
     * @return true if all of field is correct, false in the other case
     */
    public boolean validation() {
        if(classificationBox.getSelectionModel().isEmpty()) {
                alertError("Empty Classification!");
                return false;
        }
        if(booktitle.getText().isEmpty()) {
                alertError("Empty Book Title!");
                return false;
        }
        if(author.getText().isEmpty()) {
                alertError("Empty Author!");
                return false;
        }
        if(isbn.getText().isEmpty()) {
                alertError("Empty ISBN!");
                return false;
        }
        if(price.getText().isEmpty()) {
                alertError("Empty Price!");
                return false;
        } else {
                if(!isNumeric(price.getText())) {
                        alertError("Price must be a number!");
                        return false;
                }
        }
        if(publisher.getText().isEmpty()) {
                alertError("Empty Publisher!");
                return false;
        }
        if(discriptionBox.getText().isEmpty()) {
                discriptionBox.setText("");
        }
        return true;
    }

    @Override
    public void draw() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddNewBook.fxml"));
        loader.setController(this);
        BorderPane newPane = new BorderPane();
        try {
            newPane.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(AddNewBookView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.mainLayout.setCenter(newPane);
    }

}
