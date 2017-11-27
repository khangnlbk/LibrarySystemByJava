package UserManager.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BorrowingManager.Controller.BorrowCard;
import Main.DBConnect;
import TableProperty.UserInstance;

/**
 * Class này chứa toàn bộ thông tin của user, có nhiệm vụ truy xuất dữ liệu liên quan đến user.
 * @author anonymous588
 *
 */
public class BorrowerController {
	//singleton
	private static BorrowerController instance = null;
	private BorrowerController() {}
	public static BorrowerController getInstance() {
		if(instance == null) {
			instance = new BorrowerController();
		}
		return instance;
	}
	//
	private Connection conn = DBConnect.getConnection();
	private PreparedStatement ps;
	
	public UserInstance user;
	private BorrowCard card = new BorrowCard();
	
	public int unReturnBook = -1;
	public String card_id="";
	
	
	/**
	 * Lấy card_id của người dùng.
	 * @return Nếu tìm thấy sẽ trả về Card_id, không sẽ trả về null.
	 * @throws SQLException
	 */
	public String getCard_id() throws SQLException {
		if(card_id.isEmpty()) {
			if(user!=null) {
				String query = "select * from borrowcard where user_id=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, user.getUser_id());
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					card_id = rs.getString(rs.findColumn("card_id"));
				}
			}
		}
		return card_id;
	}
	
	
	/**
	 * Kiểm tra xem cơ sở dữ liệu có tồn tại activate code này không.
	 * @param code
	 * @return Card Id nếu tìm thấy. Không sẽ trả về null.
	 */
	public String checkBorrorCard(String code) {
		try {
			return card.checkCode(code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Kích hoạt thẻ.
	 * @param user_id
	 * @param card_id
	 */
	public void activateCard(String user_id, String card_id) {
		try {
			card.activateCard(user_id, card_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Lấy thông tin thẻ của User.
	 * @param user_id
	 * @return ResultSet chứa thông tin thẻ của User.
	 */
	public ResultSet getCard(String user_id) {
		try {
			return card.getBorrowCard(user_id);
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
			String query = "Select COUNT(copy_id) from borrowingregistrationitem join borrowcard where borrowcard.card_id=borrowingregistrationitem.card_id "
					+ "and borrowcard.user_id=?"
					+ "and borrowingregistrationitem.returned=0";
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getUser_id());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				unReturnBook = Integer.parseInt(rs.getString(1));
				return unReturnBook;
			}
		}
		return 0;
	}
	
	
	/**
	 * Kiểm tra xem borrower có đăng ký mượn sách nào chưa được confirm không, nếu có không cho mượn tiếp.
	 * @return
	 * @throws SQLException
	 */
	public boolean checkUnConfirmRegistration() throws SQLException {
		String registration_id;
		getCard_id();
		ps = conn.prepareStatement("select * from borrowregistration where card_id=? and status=0");
		ps.setString(1, card_id);
		ResultSet result = ps.executeQuery();
		if(result.next()) {
			System.out.println(result.getString(1)+ ","+ result.getString(2) +"," + result.getString(3));
			registration_id = result.getString(result.findColumn("registration_id"));
			if(!registration_id.isEmpty()) return true;
		}
		return false;
	}
	
	

}
