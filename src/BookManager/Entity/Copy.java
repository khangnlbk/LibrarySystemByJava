package BookManager.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author anonymous588
 */
public class Copy {
	private final IntegerProperty book_id = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public IntegerProperty book_id_property() {
        return book_id;
    }

    /**
     *
     * @return
     */
    public final int getBook_id() {
        return book_id_property().get();
    }

    /**
     *
     * @param book_id
     */
    public final void setBook_id(int book_id) {
    	book_id_property().set(book_id);
    }
    
    private final IntegerProperty copy_id = new SimpleIntegerProperty();

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
     * @param copy_id
     */
    public final void setCopy_id(int copy_id) {
    	copy_id_property().set(copy_id);
    }
    
    private final StringProperty copy_number = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty copy_number_property() {
        return copy_number;
    }

    /**
     *
     * @return
     */
    public final String getCopy_number() {
        return copy_number_property().get();
    }

    /**
     *
     * @param copy_number
     */
    public final void setCopy_number(String copy_number) {
    	copy_number_property().set(copy_number);
    }
    
    private final StringProperty status = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty status_property() {
        return status;
    }

    /**
     *
     * @return
     */
    public final String getStatus() {
        return status_property().get();
    }

    /**
     *
     * @param status
     */
    public final void setStatus(String status) {
    	status_property().set(status);
    }
    
    private final IntegerProperty price = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public IntegerProperty price_property() {
        return price;
    }

    /**
     *
     * @return
     */
    public final int getPrice() {
        return price_property().get();
    }

    /**
     *
     * @param price
     */
    public final void setPrice(int price) {
    	price_property().set(price);
    }
    
    private final StringProperty copy_condition = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty copy_condition_property() {
        return copy_condition;
    }

    /**
     *
     * @return
     */
    public final String getCopy_condition() {
        return copy_condition_property().get();
    }

    /**
     *
     * @param condition
     */
    public final void setCopy_condition(String condition) {
    	copy_condition_property().set(condition);
    }
    
    private final StringProperty copy_type = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty copy_type_property() {
        return copy_type;
    }

    /**
     *
     * @return
     */
    public final String getCopy_type() {
        return copy_type_property().get();
    }

    /**
     *
     * @param copy_type
     */
    public final void setCopy_type(String copy_type) {
    	copy_type_property().set(copy_type);
    }
    
    /**
     *
     * @param copy_id
     * @param book_id
     * @param copy_number
     * @param status
     * @param price
     * @param copy_condition
     * @param copy_type
     */
    public Copy(int copy_id, int book_id, String copy_number, String status, int price, String copy_condition, String copy_type) {
    	this.setCopy_id(copy_id);
    	this.setBook_id(book_id);
    	this.setCopy_number(copy_number);
    	this.setStatus(status);
    	this.setPrice(price);
    	this.setCopy_condition(copy_condition);
    	this.setCopy_type(copy_type);
    }
    
    /**
     *
     * @param rs
     * @throws SQLException
     */
    public Copy(ResultSet rs) throws SQLException {
        this.setCopy_id(rs.getInt("copy_id"));
    	this.setBook_id(rs.getInt("book_id"));
    	this.setCopy_number(rs.getString("copy_number"));
    	this.setStatus(rs.getString("status"));
    	this.setPrice(rs.getInt("price"));
    	this.setCopy_condition(rs.getString("copy_condition"));
    	this.setCopy_type(rs.getString("copy_type"));
    }
}
