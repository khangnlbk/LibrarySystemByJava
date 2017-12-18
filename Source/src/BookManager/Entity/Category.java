package BookManager.Entity;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author anonymous588
 */
public class Category {
	
    private final IntegerProperty category_id = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public IntegerProperty category_id_property() {
        return category_id;
    }

    /**
     *
     * @return
     */
    public final int getCategory_id() {
        return category_id_property().get();
    }

    /**
     *
     * @param id
     */
    public final void setCategory_id(int id) {
    	category_id_property().set(id);
    }
    
    private final StringProperty name = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty name_property() {
        return name;
    }

    /**
     *
     * @return
     */
    public final String getName() {
        return name_property().get();
    }

    /**
     *
     * @param name
     */
    public final void setName(String name) {
    	name_property().set(name);
    }
    
    private final StringProperty encode = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty encode_property() {
        return encode;
    }

    /**
     *
     * @return
     */
    public final String getEncode() {
        return encode_property().get();
    }

    /**
     *
     * @param encode
     */
    public final void setEncode(String encode) {
    	encode_property().set(encode);
    }
    
    private final BooleanProperty checked = new SimpleBooleanProperty();

    /**
     *
     * @return
     */
    public BooleanProperty checked_property() {
        return checked;
    }

    /**
     *
     * @return
     */
    public final boolean isChecked() {
        return checked_property().get();
    }

    /**
     *
     * @param checked
     */
    public final void setChecked(boolean checked) {
    	checked_property().set(checked);
    }
    
    /**
     *
     * @param category_id
     * @param name
     * @param encode
     * @param checked
     */
    public Category(int category_id, String name, String encode, boolean checked) {
    	this.setCategory_id(category_id);
    	this.setName(name);
    	this.setEncode(encode);
    	this.setChecked(checked);
    }

    /**
     *
     * @param categoryID
     * @param name
     */
    public Category(int categoryID, String name) {
        this.setCategory_id(categoryID);
        this.setName(name);
    }
}
