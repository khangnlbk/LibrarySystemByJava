package TableProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author anonymous588
 */
public class RegistrationDetail {
	private final StringProperty copy_id = new SimpleStringProperty();
	private final StringProperty borrowDate = new SimpleStringProperty();
	private final StringProperty lentDate = new SimpleStringProperty();
	private final StringProperty expectedReturnDate = new SimpleStringProperty();
	private final StringProperty returned = new SimpleStringProperty();
	
	public StringProperty copy_id_property() {
        return copy_id;
    }
    public final String getCopy_id() {
        return copy_id_property().get();
    }
    public final void setCopy_id(String id) {
    	copy_id_property().set(id);
    }
    
    public StringProperty borrowDate_property() {
        return borrowDate;
    }
    public final String getBorrowDate() {
        return borrowDate_property().get();
    }
    public final void setBorrowDate(String id) {
    	borrowDate_property().set(id);
    }
	
    public StringProperty lentDate_property() {
        return lentDate;
    }
    public final String getLentDate() {
        return lentDate_property().get();
    }
    public final void setLentDate(String id) {
    	lentDate_property().set(id);
    }
	
    public StringProperty expectedReturnDate_property() {
        return expectedReturnDate;
    }
    public final String getExpectedReturnDate() {
        return expectedReturnDate_property().get();
    }
    public final void setExpectedReturnDate(String id) {
    	expectedReturnDate_property().set(id);
    }
    
    
    public StringProperty returned_property() {
        return returned;
    }
    public final String getReturned() {
        return returned_property().get();
    }
    public final void setReturned(String id) {
    	returned_property().set(id);
    }
    
    public RegistrationDetail(String a1, String a2, String a3, String a4, String a5) {
    	setCopy_id(a1);
    	setBorrowDate(a2);
    	setLentDate(a3);
    	setExpectedReturnDate(a4);
    	setReturned(a5);
    }
    
}
