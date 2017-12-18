package BookManager.Entity;


/**
 * Class có nhiệm vụ chính là lưu dữ liệu của một đầu sách để lưu vào bảng.
 * @author anonymous588
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author anonymous588
 */
public class Book {
	
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
    
    private final StringProperty title = new SimpleStringProperty();

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
     * @param title
     */
    public final void setTitle(String title) {
    	title_property().set(title);
    }
    
    private final StringProperty author = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty author_property() {
        return author;
    }

    /**
     *
     * @return
     */
    public final String getAuthor() {
        return author_property().get();
    }

    /**
     *
     * @param author
     */
    public final void setAuthor(String author) {
    	author_property().set(author);
    }
    
    private final StringProperty publisher = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty publisher_property() {
        return publisher;
    }

    /**
     *
     * @return
     */
    public final String getPublisher() {
        return publisher_property().get();
    }

    /**
     *
     * @param publisher
     */
    public final void setPublisher(String publisher) {
    	publisher_property().set(publisher);
    }
    
    private final StringProperty category = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty category_property() {
        return category;
    }

    /**
     *
     * @return
     */
    public final String getCategory() {
        return category_property().get();
    }

    /**
     *
     * @param category
     */
    public final void setCategory(String category) {
    	category_property().set(category);
    }
    
    private final StringProperty isbn = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty isbn_property() {
        return isbn;
    }

    /**
     *
     * @return
     */
    public final String getIsbn() {
        return isbn_property().get();
    }

    /**
     *
     * @param isbn
     */
    public final void setIsbn(String isbn) {
    	isbn_property().set(isbn);
    }
    
    private final StringProperty discription = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty discription_property() {
        return discription;
    }

    /**
     *
     * @return
     */
    public final String getDiscription() {
        return discription_property().get();
    }

    /**
     *
     * @param discription
     */
    public final void setDiscription(String discription) {
    	discription_property().set(discription);
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
    
    private final StringProperty categoryName = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public StringProperty categoryName_property() {
        return categoryName;
    }

    /**
     *
     * @return
     */
    public final String getCategoryName() {
        return categoryName_property().get();
    }

    /**
     *
     * @param name
     */
    public final void setCategoryName(String name) {
    	categoryName_property().set(name);
    }
    
    /**
     *
     * @param book_id
     * @param title
     * @param author
     * @param publisher
     * @param category
     * @param isbn
     * @param discription
     * @param price
     */
    public Book(int book_id, String title, String author, String publisher, String category, String isbn, String discription, int price) {
    	this.setBook_id(book_id);
    	this.setTitle(title);
    	this.setAuthor(author);
    	this.setCategory(category);
    	this.setIsbn(isbn);
    	this.setPublisher(publisher);
    	this.setDiscription(discription);
    	this.setPrice(price);
    }
    
    /**
     *
     * @param title
     * @param author
     * @param categoryName
     * @param publisher
     */
    public Book(String title, String author, String categoryName, String publisher) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setCategoryName(categoryName);
        this.setPublisher(publisher);
    }
    
    /**
     *
     * @param rs
     * @throws SQLException
     */
    public Book(ResultSet rs) throws SQLException {
        this.setBook_id(rs.getInt(rs.findColumn("book_id")));
    	this.setTitle(rs.getString(rs.findColumn("title")));
    	this.setAuthor(rs.getString(rs.findColumn("author")));
    	this.setIsbn(rs.getString(rs.findColumn("isbn")));
    	this.setPublisher(rs.getString(rs.findColumn("publisher")));
    	this.setDiscription(rs.getString(rs.findColumn("discription")));
    	this.setPrice(rs.getInt(rs.findColumn("price")));
    }
}
