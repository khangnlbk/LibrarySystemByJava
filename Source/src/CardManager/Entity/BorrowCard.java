package CardManager.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Enity của BorrowCard. Truy xuất dữ liệu.
 * @author anonymous588
 *
 */
public class BorrowCard {

    private final IntegerProperty user_id = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public IntegerProperty user_id_property() {
        return user_id;
    }

    /**
     *
     * @return
     */
    public final int getUser_id() {
        return user_id_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setUser_id(int id) {
    	user_id_property().set(id);
    }
    
    private final IntegerProperty card_id = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public IntegerProperty card_id_property() {
        return card_id;
    }

    /**
     *
     * @return
     */
    public final int getCard_id() {
        return card_id_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setCard_id(int id) {
    	card_id_property().set(id);
    }
    
    private final StringProperty expired_date = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty expired_date_property() {
        return expired_date;
    }

    /**
     *
     * @return
     */
    public final String getExpiredDate() {
        return expired_date_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setExpiredDate(String id) {
    	expired_date_property().set(id);
    }
    
    private final StringProperty activate_code = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty activate_code_property() {
        return activate_code;
    }

    /**
     *
     * @return
     */
    public final String getActivateCode() {
        return activate_code_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setActivateCode(String id) {
    	activate_code_property().set(id);
    }
	
    private final IntegerProperty deposit = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public IntegerProperty deposit_property() {
        return deposit;
    }

    /**
     *
     * @return
     */
    public final int getDeposit() {
        return deposit_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setDeposit(int id) {
    	deposit_property().set(id);
    }
    
    /**
     *
     * @param result
     */
    public BorrowCard(ResultSet result) {
        try {
            setCard_id(result.getInt("card_id"));
            setUser_id(result.getInt("user_id"));
            setDeposit(result.getInt("deposit"));
            setActivateCode(result.getString("activate_code"));
            System.out.print(getActivateCode());
            setExpiredDate(result.getString("expired_date"));
            System.out.print(getExpiredDate());
        } catch (SQLException ex) {
        }

    }
}
