package BorrowingManager.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author anonymous588
 */
public class RegistrationDetail {
    
    private final IntegerProperty copy_id = new SimpleIntegerProperty();
    private final IntegerProperty registration_id = new SimpleIntegerProperty();
    private final StringProperty borrowDate = new SimpleStringProperty();
    private final StringProperty lentDate = new SimpleStringProperty();
    private final StringProperty expectedReturnDate = new SimpleStringProperty();
    private final StringProperty returned = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
	
    /**
     *
     * @return
     */
    public IntegerProperty copy_id_property() {
        return copy_id;
    }

    /**
     *
     * @return
     */
    public final int getCopy_id() {
        return copy_id_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setCopy_id(int id) {
    	copy_id_property().set(id);
    }
    
    /**
     *
     * @return
     */
    public StringProperty title_property() {
        return title;
    }
    
    /**
     *
     * @return
     */
    public final String getTitle() {
        return title_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setTitle(String id) {
    	title_property().set(id);
    }
    
    /**
     *
     * @return
     */
    public StringProperty borrowDate_property() {
        return borrowDate;
    }

    /**
     *
     * @return
     */
    public final String getBorrowDate() {
        return borrowDate_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setBorrowDate(String id) {
    	borrowDate_property().set(id);
    }
	
    /**
     *
     * @return
     */
    public StringProperty lentDate_property() {
        return lentDate;
    }

    /**
     *
     * @return
     */
    public final String getLentDate() {
        return lentDate_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setLentDate(String id) {
    	lentDate_property().set(id);
    }
	
    /**
     *
     * @return
     */
    public StringProperty expectedReturnDate_property() {
        return expectedReturnDate;
    }

    /**
     *
     * @return
     */
    public final String getExpectedReturnDate() {
        return expectedReturnDate_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setExpectedReturnDate(String id) {
    	expectedReturnDate_property().set(id);
    }
    
    /**
     *
     * @return
     */
    public StringProperty returned_property() {
        return returned;
    }

    /**
     *
     * @return
     */
    public final String getReturned() {
        return returned_property().get();
    }

    /**
     *
     * @param id
     */
    
    public final void setReturned(String id) {
    	returned_property().set(id);
    }
    
    public IntegerProperty registration_id_property() {
        return registration_id;
    }

    /**
     *
     * @return
     */
    public final int getRegistration_id() {
        return registration_id_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setRegistration_id(int id) {
    	registration_id_property().set(id);
    }
    
    /**
     *
     * @param a1
     * @param a2
     * @param a3
     * @param a4
     * @param a5
     * @param a6
     */
    public RegistrationDetail(String a1, String a2, String a3, String a4, String a5,String a6) {
    	setCopy_id(Integer.parseInt(a1));
    	setBorrowDate(a2);
    	setLentDate(a3);
    	setExpectedReturnDate(a4);
    	setReturned(a5);
        setTitle(a6);
    }
    
    /**
     *
     * @param a1
     * @param a2
     * @param a3
     * @param a4
     * @param a5
     */
    public RegistrationDetail(String a1, String a2, String a3, String a4, String a5) {
    	setCopy_id(Integer.parseInt(a1));
    	setBorrowDate(a2);
    	setLentDate(a3);
    	setExpectedReturnDate(a4);
    	setReturned(a5);
    }

    /**
     *
     * @param result
     * @throws SQLException
     */
    public RegistrationDetail(ResultSet result) throws SQLException {
    	setCopy_id(result.getInt("copy_id"));
    	setBorrowDate(result.getString("borrow_date"));
    	setLentDate(result.getString("lent_date"));
    	setExpectedReturnDate(result.getString("expected_return_date"));
    	setReturned(result.getString("returned"));
        setTitle(result.getString("title"));
        setRegistration_id(result.getInt("registration_id"));
    }
}
