package UserManager.Controller;

import BorrowingManager.Entity.BorrowRegistrationManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Helper.DBConnect;
import CardManager.Entity.CardManager;
import UserManager.Entity.User;

/**
 * Class này chứa toàn bộ thông tin của user, có nhiệm vụ truy xuất dữ liệu liên quan đến user.
 * @author anonymous588
 *
 */
public class BorrowerController {

    private static BorrowerController instance = null;
    private BorrowerController() {}

    public static BorrowerController getInstance() {
        if(instance == null) {
                instance = new BorrowerController();
        }
        return instance;
    }
	
    private BorrowRegistrationManager borrowRegistrationManager=new BorrowRegistrationManager();
    private Connection conn = DBConnect.getConnection();
    private PreparedStatement ps;
    public User user;
    private CardManager cardManager = new CardManager();
    public int unReturnBook = -1;
    public int card_id = -1;
	
	
    /**
     * Lấy card_id của người dùng.
     * @return Nếu tìm thấy sẽ trả về Card_id, không sẽ trả về null.
     * @throws SQLException
     */
    public int getCard_id() throws SQLException {
            if(card_id == -1) {
                    if(user!=null) {
                            card_id=cardManager.getCardId(getUserId());
                    }
            }
            return card_id;
    }
        
    /**
     *
     * @return
     */
    public int getUserId() {
            return user.getUser_id();
        }
	
	
	/**
	 * Kiểm tra xem cơ sở dữ liệu có tồn tại activate code này không.
	 * @param code
	 * @return Card Id nếu tìm thấy. Không sẽ trả về -1.
	 */
//	public int checkBorrorCard(String code) {
//		try {
//                    return card.checkCode(code);
//		} catch (SQLException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//		}
//		return -1;
//	}
	
	
	
    /**
     * Lấy thông tin thẻ của User.
     * @param user_id
     * @return ResultSet chứa thông tin thẻ của User.
     */
    public ResultSet getCard(int user_id) {
            try {
                    return cardManager.getBorrowCard(user_id);
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            return null;
    }

    /**
     * Lấy số lượng sách chưa được trả của borrower.
     * @return 
     * @throws SQLException
     */
    public int getUnReturnBook() throws SQLException {
            if(unReturnBook != -1) return unReturnBook;
            if(user!=null) {
                
                return borrowRegistrationManager.getUnreturnBook(1);
            }
            return 0;
    }

    /**
     * Kiểm tra xem borrower có đăng ký mượn sách nào chưa được confirm không, nếu có không cho mượn tiếp.
     * @return true neu co. false neu khong
     * @throws SQLException
     */
    public boolean checkUnConfirmRegistration() throws SQLException {
            String registration_id;
            getCard_id();
            ps = conn.prepareStatement("select * from borrowregistration where card_id=? and status= 'borrowed' ");
            ps.setInt(1, card_id);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                    registration_id = result.getString(result.findColumn("registration_id"));
                    if(!registration_id.isEmpty()) return true;
            }
            return false;
    }
    
    /**
     * Kiem tra co quyen nao chua tra va qua han khong
     * @return true neu co, false neu k
     */
    public boolean haveOutdatedBook(){
        if(user==null)
            return true;
        else {
            return borrowRegistrationManager.haveOutdateBook(user.getUser_id());
        }
    }


       public void setUser(User user){
           this.user=user;
       }
       
       public String getRole() {
           return this.user.getRole();
       }

}
