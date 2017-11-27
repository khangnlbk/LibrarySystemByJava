/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author khangnlbk
 */
public class CopyInstance {
    private final StringProperty book_id = new SimpleStringProperty();
	public StringProperty book_id_property() {
        return book_id;
    }
    public final String getBook_id() {
        return book_id_property().get();
    }
    public final void setBook_id(String id) {
    	book_id_property().set(id);
    }
    
    private final StringProperty copy_id = new SimpleStringProperty();
	public StringProperty copy_id_property() {
        return copy_id;
    }
    public final String getCopy_id() {
        return copy_id_property().get();
    }
    public final void setCopy_id(String id) {
    	copy_id_property().set(id);
    }
    
    private final StringProperty copy_number = new SimpleStringProperty();
	public StringProperty copy_number_property() {
        return copy_number;
    }
    public final String getCopy_number() {
        return copy_number_property().get();
    }
    public final void setCopy_number(String id) {
    	copy_number_property().set(id);
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
    
    private final StringProperty price = new SimpleStringProperty();
	public StringProperty price_property() {
        return price;
    }
    public final String getPrice() {
        return price_property().get();
    }
    public final void setPrice(String id) {
    	price_property().set(id);
    }
    
    private final StringProperty copy_condition = new SimpleStringProperty();
	public StringProperty copy_condition_property() {
        return copy_condition;
    }
    public final String getCopy_condition() {
        return copy_condition_property().get();
    }
    public final void setCopy_condition(String id) {
    	copy_condition_property().set(id);
    }
    
    private final StringProperty copy_type = new SimpleStringProperty();
	public StringProperty copy_type_property() {
        return copy_type;
    }
    public final String getCopy_type() {
        return copy_type_property().get();
    }
    public final void setCopy_type(String id) {
    	copy_type_property().set(id);
    }
    
    public CopyInstance(String copy_id, String book_id, String copy_number, String status, String price, String copy_condition, String copy_type) {
    	this.setCopy_id(copy_id);
    	this.setBook_id(book_id);
    	this.setCopy_number(copy_number);
    	this.setStatus(status);
    	this.setPrice(price);
    	this.setCopy_condition(copy_condition);
    	this.setCopy_type(copy_type);
    }
}
