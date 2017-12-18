package BorrowingManager.Entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author anonymous588
 */
public class BorrowRegistration {
    
    private final IntegerProperty registration_id = new SimpleIntegerProperty();
    private final IntegerProperty card_id = new SimpleIntegerProperty();
    private final StringProperty status = new SimpleStringProperty();


    public IntegerProperty registration_id_property() {
        return registration_id;
    }

    public final int getRegistration_id() {
        return registration_id_property().get();
    }

    public final void setRegistration_id(int id) {
    	registration_id_property().set(id);
    }
    

    public IntegerProperty card_id_property() {
        return card_id;
    }

    public final int getCard_id() {
        return card_id_property().get();
    }

    public final void setCard_id(int id) {
    	card_id_property().set(id);
    }
    
    public StringProperty status_property() {
        return status;
    }

    public final String getStatus() {
        return status_property().get();
    }

    public final void setStatus(String status) {
    	status_property().set(status);
    }

    public BorrowRegistration(int registration_id, int card_id, String status) {
    	this.setRegistration_id(registration_id);
    	this.setCard_id(card_id);
    	this.setStatus(status);
    }
}
