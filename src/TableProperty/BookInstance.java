package TableProperty;


/**
 * Class có nhiệm vụ chính là lưu dữ liệu của một đầu sách để lưu vào bảng.
 * @author anonymous588
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookInstance {
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
    
    private final StringProperty title = new SimpleStringProperty();
	public StringProperty title_property() {
        return title;
    }
    public final String getTitle() {
        return title_property().get();
    }
    public final void setTitle(String id) {
    	title_property().set(id);
    }
    
    private final StringProperty author = new SimpleStringProperty();
	public StringProperty author_property() {
        return author;
    }
    public final String getAuthor() {
        return author_property().get();
    }
    public final void setAuthor(String id) {
    	author_property().set(id);
    }
    
    private final StringProperty publisher = new SimpleStringProperty();
	public StringProperty publisher_property() {
        return publisher;
    }
    public final String getPublisher() {
        return publisher_property().get();
    }
    public final void setPublisher(String id) {
    	publisher_property().set(id);
    }
    
    private final StringProperty category = new SimpleStringProperty();
	public StringProperty category_property() {
        return category;
    }
    public final String getCategory() {
        return category_property().get();
    }
    public final void setCategory(String id) {
    	category_property().set(id);
    }
    
    private final StringProperty isbn = new SimpleStringProperty();
	public StringProperty isbn_property() {
        return isbn;
    }
    public final String getIsbn() {
        return isbn_property().get();
    }
    public final void setIsbn(String id) {
    	isbn_property().set(id);
    }
    
    private final StringProperty discription = new SimpleStringProperty();
	public StringProperty discription_property() {
        return discription;
    }
    public final String getDiscription() {
        return discription_property().get();
    }
    public final void setDiscription(String id) {
    	discription_property().set(id);
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
    
    private final StringProperty categoryName = new SimpleStringProperty();
  	public StringProperty categoryName_property() {
          return categoryName;
      }
      public final String getCategoryName() {
          return categoryName_property().get();
      }
      public final void setCategoryName(String id) {
    	  categoryName_property().set(id);
      }
    
    public BookInstance(String book_id, String title, String author, String publisher, String category, String isbn, String discription, String price) {
    	this.setBook_id(book_id);
    	this.setTitle(title);
    	this.setAuthor(author);
    	this.setCategory(category);
    	this.setIsbn(isbn);
    	this.setPublisher(publisher);
    	this.setDiscription(discription);
    	this.setPrice(price);
    }
    
    public BookInstance(String title, String author, String categoryName, String publisher) {
		this.setTitle(title);
    	this.setAuthor(author);
    	this.setCategoryName(categoryName);
    	this.setPublisher(publisher);
	}
}
