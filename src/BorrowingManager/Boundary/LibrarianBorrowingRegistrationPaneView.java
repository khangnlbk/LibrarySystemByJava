/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BorrowingManager.Boundary;

import BorrowingManager.Controller.BorrowingAndReturningController;
import BorrowingManager.Entity.BorrowRegistration;
import BorrowingManager.Entity.RegistrationDetail;
import Helper.Helper;
import Helper.ViewAbstract;
import static UserManager.Boundary.LibrarianView.mainLayout;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 *
 * @author anonymous588
 */
public class LibrarianBorrowingRegistrationPaneView extends ViewAbstract implements Initializable {
    @FXML private TableView<BorrowRegistration> registrationTable;
    @FXML private TableColumn registrationidCol;
    @FXML private TableColumn cardidCol;
    @FXML private TableColumn confirmCol;

    @FXML private Label user_id;
    @FXML private Label registration_id;
    @FXML private Label card_id;
    @FXML private Label expired_date;
    @FXML private Label deposit;
    @FXML private TableColumn detailBorrowDateCol;
    @FXML private TableColumn detailExpectedReturnDateCol;
    @FXML private TableColumn detailReturnedCol;
    @FXML private TableColumn detailCopyIdCol;
    @FXML private TableColumn detailLentDateCol;
    @FXML private TextField searchRegistration;
    @FXML private Button confirmButton;
    @FXML private Button searchRequestButton;
    @FXML private TableView<RegistrationDetail> detailTable;
    @FXML private Button check;
    private BorrowingAndReturningController controller=new BorrowingAndReturningController();

    public LibrarianBorrowingRegistrationPaneView(BorderPane mainLayout) {
        super(mainLayout);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        registrationidCol.setCellValueFactory(new PropertyValueFactory<BorrowRegistration, String>("registration_id"));
        cardidCol.setCellValueFactory(new PropertyValueFactory<BorrowRegistration, String>("card_id"));
        confirmCol.setCellValueFactory(new PropertyValueFactory<BorrowRegistration, String>("status"));

        detailCopyIdCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("copy_id"));
        detailBorrowDateCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("borrowDate"));
        detailLentDateCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("lentDate"));
        detailExpectedReturnDateCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, Integer>("expectedReturnDate"));
        detailReturnedCol.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String>("returned"));
        searchRequestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    searchRegistration();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LibrarianBorrowingRegistrationPaneView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LibrarianBorrowingRegistrationPaneView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    confirmRegistration();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LibrarianBorrowingRegistrationPaneView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LibrarianBorrowingRegistrationPaneView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        registrationTable.getSelectionModel().select(0);

        //Tao listener cho event chon registration de hien thi detail cua registration do.
        ObservableList<BorrowRegistration> selected = registrationTable.getSelectionModel().getSelectedItems();
        selected.addListener(new ListChangeListener<BorrowRegistration>() {
                @Override
                public void onChanged(ListChangeListener.Change c) {
                        try {
                            System.out.print("\nbug\n");
                                getSelectedInformation();
                        } catch (ClassNotFoundException | SQLException e) {
                        }
                }

        });

        // lay thong tin registration
        try {
                getBorrowRegistrations();
        } catch (ClassNotFoundException | SQLException e) {
        }
        
        check.setOnAction((event) -> {
            try {
                checkCopy();
            } catch (SQLException ex) {
                Logger.getLogger(LibrarianBorrowingRegistrationPaneView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

	
    //search theo user_id, card_id, hoac registration_id
    /**
     * Tìm Registration theo user_id sau đó hiển thị kết quả ra màn hình
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void searchRegistration() throws ClassNotFoundException, SQLException {
        if(searchRegistration.getText() != null) {
            if (Helper.isNumeric(searchRegistration.getText())) {
                ResultSet rs = controller.searchRegistration(Integer.parseInt(searchRegistration.getText()));
                showRegistration(rs);
            }
            else Helper.alertError("Keyword have to be a number");
        }
    }


    /**
     * Hiển thị toàn bộ registration.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getBorrowRegistrations() throws ClassNotFoundException, SQLException {
            ResultSet rs = controller.getRegistrations();
            showRegistration(rs);
    }

    /**
     * Lấy thông tin của registration đã chọn.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getSelectedInformation() throws ClassNotFoundException, SQLException {
        System.out.print("getSelection");
            BorrowRegistration reg = registrationTable.getSelectionModel().getSelectedItem();
            int cardID = reg.getCard_id();
            int regist=reg.getRegistration_id();
            if(reg.getStatus().equals("lent")) {
                    confirmButton.setDisable(true);
            } else {
                    confirmButton.setDisable(false);
            }
            ResultSet rs = controller.getRegistrationDetail(regist);
            showItemDetail(rs);
    }
	
    /**
     *
     * @param rs
     * @throws SQLException
     */
    public void showItemDetail(ResultSet rs) throws SQLException {
            detailTable.getItems().clear();
            rs.next();
            user_id.setText("User ID: " + rs.getString("user_id")); //user_id
                    card_id.setText("Card ID: " + rs.getString("card_id")); //card_id
                    registration_id.setText("Registration ID: " + rs.getString("registration_id")); //registration id
                    expired_date.setText("Expired Date: " + rs.getString("expired_date")); //expired date
                    deposit.setText("Deposit: " + rs.getString("deposit")); //deposit
                    rs.beforeFirst();
            ObservableList<RegistrationDetail> rows = FXCollections.observableArrayList();
            while(rs.next()) {              
                System.out.print("\ntable only\n");
                System.out.print("\n"+rs.getString("title")+"\n");
                    RegistrationDetail tmp = new RegistrationDetail(rs);
                    rows.add(tmp);
            }
            detailTable.setItems(rows);
    }
	
    /**
     * Hiển thị thông tin của registration đã chọn.
     * @param rs
     * @throws SQLException
     */
    public void showRegistration(ResultSet rs) throws SQLException {
        registrationTable.getItems().clear();
        ObservableList<BorrowRegistration> rows = FXCollections.observableArrayList();
        while(rs.next()){
        //Iterate Row
            String[] arguments = new String[3];
            for(int i=0 ; i < rs.getMetaData().getColumnCount(); i++){
                arguments[i] = rs.getString(i+1);
            }

            BorrowRegistration tmp = new BorrowRegistration(Integer.parseInt(arguments[0]),
                Integer.parseInt(arguments[1]), arguments[2]);
            rows.add(tmp);
        }
        registrationTable.setItems(rows);
    }

    /**
     * Xác nhận đã confirm Registration. Gọi đến Mediator của BorrowingManager để thực hiện việc truy xuất cơ sở dữ liệu.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void confirmRegistration() throws ClassNotFoundException, SQLException {
            BorrowRegistration reg = registrationTable.getSelectionModel().getSelectedItem();
            int id=registrationTable.getSelectionModel().getSelectedIndex();
            int registrationId = reg.getRegistration_id();
            controller.confirmRegistration(registrationId);
            //reg.setStatus("lent");
            
            draw();
            registrationTable.getSelectionModel().select(id);
            
    }
    
    public void checkCopy() throws SQLException{
        RegistrationDetail detail = detailTable.getSelectionModel().getSelectedItem();
        controller.confirmItem(detail.getCopy_id(),detail.getRegistration_id());
        //detail.setReturned("yes");
        int id=registrationTable.getSelectionModel().getSelectedIndex();
        int other_id=detailTable.getSelectionModel().getSelectedIndex();
        draw();
        registrationTable.getSelectionModel().select(id);
        detailTable.getSelectionModel().select(other_id);
        
        
    }
    


    @Override
    public void draw() {
        		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("LibrarianBorrowingRegistrationPane.fxml"));
                loader.setController(this);
		BorderPane librarianItem = new BorderPane();
        try {
            librarianItem.setCenter((Node) loader.load());
        } catch (IOException ex) {
            Logger.getLogger(LibrarianBorrowingRegistrationPaneView.class.getName()).log(Level.SEVERE, null, ex);
        }
		mainLayout.setCenter(librarianItem);
    }
}
