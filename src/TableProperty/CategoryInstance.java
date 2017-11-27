/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableProperty;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author khangnlbk
 */
public class CategoryInstance {
    private final StringProperty category_id = new SimpleStringProperty();
	public StringProperty category_id_property() {
        return category_id;
    }
    public final String getCategory_id() {
        return category_id_property().get();
    }
    public final void setCategory_id(String id) {
    	category_id_property().set(id);
    }
    
	private final StringProperty name = new SimpleStringProperty();
	public StringProperty name_property() {
        return name;
    }
    public final String getName() {
        return name_property().get();
    }
    public final void setName(String id) {
    	name_property().set(id);
    }
    
	private final StringProperty encode = new SimpleStringProperty();
	public StringProperty encode_property() {
        return encode;
    }
    public final String getEncode() {
        return encode_property().get();
    }
    public final void setEncode(String id) {
    	encode_property().set(id);
    }
    
    private final BooleanProperty checked = new SimpleBooleanProperty();
	public BooleanProperty checked_property() {
        return checked;
    }
    public final boolean isChecked() {
        return checked_property().get();
    }
    public final void setChecked(boolean id) {
    	checked_property().set(id);
    }
    
    public CategoryInstance(String category_id, String name, String encode, boolean checked) {
    	this.setCategory_id(category_id);
    	this.setName(name);
    	this.setEncode(encode);
    	this.setChecked(checked);
    }
    public CategoryInstance(String categoryID, String name) {
		this.setCategory_id(categoryID);
		this.setName(name);
	}
}
