package TableProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author anonymous588
 */
public class BorrowRegistration {
	private final StringProperty registration_id = new SimpleStringProperty();
	public StringProperty registration_id_property() {
        return registration_id;
    }
    public final String getRegistration_id() {
        return registration_id_property().get();
    }
    public final void setRegistration_id(String id) {
    	registration_id_property().set(id);
    }
    
    private final StringProperty card_id = new SimpleStringProperty();
	public StringProperty card_id_property() {
        return card_id;
    }
    public final String getCard_id() {
        return card_id_property().get();
    }
    public final void setCard_id(String id) {
    	card_id_property().set(id);
    }
    
    private final StringProperty status = new SimpleStringProperty();
	public StringProperty status_property() {
        return status;
    }
    public final String getStatus() {
        return status_property().get();
    }
    public final void setStatus(String id) {
    	status_property().set(id);
    }
    
    public BorrowRegistration(String registration_id, String card_id, String status) {
    	this.setRegistration_id(registration_id);
    	this.setCard_id(card_id);
    	this.setStatus(status);
    }
}
