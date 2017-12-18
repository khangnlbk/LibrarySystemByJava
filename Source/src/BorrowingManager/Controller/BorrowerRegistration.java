package BorrowingManager.Controller;

import BorrowingManager.Entity.BorrowRegistrationManager;
import java.sql.SQLException;

import BookManager.Entity.CopyManager;
import BookManager.Entity.Book;
import BorrowingManager.Boundary.HistoryView;
import Helper.ViewAbstract;
import UserManager.Controller.BorrowerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Controller của bảng borrowregistration và borrowingregistrationitem, lưu dữ liệu và truy xuất
 * @author anonymous588
 *
 */
public class BorrowerRegistration {

    private static BorrowerRegistration instance = null;
    private BorrowerRegistration() {}
    private BorrowerController currentUser = BorrowerController.getInstance();

    public static BorrowerRegistration getInstance() {
        if(instance == null) {
                instance = new BorrowerRegistration();
        }
        return instance;
    }
	
    private ObservableList<Book> registration = FXCollections.observableArrayList();
    private BorrowRegistrationManager borrowRegistrationManager = new BorrowRegistrationManager();

    /**
     * Lay toan bo yeu cau
     * @return List sach yeu cau duoi dang observableList
     */
    public ObservableList<Book> getRegistration() {
            return registration;
    }
	
    public void setRegistration(ObservableList<Book> registration) {
            this.registration = registration;
    }
    public void clearRegistration() {
            this.registration.clear();
    }

    /**
     * luu cac yeu cau muon sach
     * @throws  SQLException
     * @return true neu save dc
     */
    public ViewAbstract saveRegistration() throws SQLException {
        
        // update copy status -> get copy_id, change copy status // status: available, borrowed, lent
        int card_id=currentUser.getCard_id();
        borrowRegistrationManager.saveRegistration(card_id);
        int regist=borrowRegistrationManager.getRegistrationId(card_id);
        CopyManager copy_manager=new CopyManager();
        for(int i = 0; i < registration.size(); i++) {
                int book_id = registration.get(i).getBook_id();
                int copy_id = copy_manager.getValidCopyID(book_id);
                 borrowRegistrationManager.updateCopytoBorrowed(copy_id);
                 boolean check= borrowRegistrationManager.addItemtoBorrow(copy_id, card_id, regist);
                if(check==false)
                    return null;
        }
        getRegistration().clear();
        return new HistoryView();
    }
    
    public String checkRequest() throws SQLException{
        if(currentUser.checkUnConfirmRegistration())
            return "You have 1 registration waiting to be confirmed";
         else if(currentUser.getCard_id() == -1)
            return "You must have aleast 1 activated Borrow Card";
        if(currentUser.getUnReturnBook() + getRegistration().size() > 5)
            return "You have" + currentUser.getUnReturnBook() + " unreturned book." + "\n" + "You can only borrow 5 books at a time. Please return some book before borrow.";
        if(currentUser.haveOutdatedBook())
            return "Please return all your outdated book first";
        if(getRegistration().isEmpty())
            return "Please true some book first";
        else return "success";
    }
    
}
