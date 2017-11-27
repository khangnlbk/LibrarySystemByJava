package BookManager;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BookManager.Controller.SearchAdvanceController.SearchFilter;
import Main.DBConnect;


/**
 * Enity Của Book. Truy xuất dữ liệu.
 * @author anonymous588
 *
 */
public class Book {
	private String getAllBook = "select * from book";
	private String getBookByID = "select * from book where book_id=?";
	private String getBookByTitle = "select * from book where title LIKE ?";
	private String getBookByAuthor = "select * from book where author LIKE ?";
	private String getBookByPublisher = "select * from book where publisher LIKE ?";
	private String getBookByISBN = "select * from book where isbn=?";
	private String getBookByBookNumber = "select * from book where book_number=?";
	private String getCategoriesByBookID = "select * from categorybook join category where category.category_id=categorybook.category_id and book_id=?";
	private String addBook = "INSERT INTO book (title, author, publisher, isbn, book_number, discription, price) VALUES (?,?,?,?,?,?,?)";
	private String getCountOfBookInCategory = "Select COUNT(book_id) from book where book_number LIKE ? ";
	private String updateBookById = "update book set title=?, author=?, publisher=?, isbn=?, book_number=?, discription=?, price=? where book_id=?";
	
	public Book() {}
	
	public Connection conn = DBConnect.getConnection();
	public PreparedStatement ps;
	
	
	public ResultSet getAllBook() throws SQLException {
		ps = conn.prepareStatement(getAllBook);
		ResultSet rs_categories = ps.executeQuery();
		return rs_categories;	
	}
	
	//Tim categories theo book_id
	public ResultSet getCategoryByBookId(String book_id) throws SQLException {
		ps = conn.prepareStatement(getCategoriesByBookID);
		ps.setString(1, book_id);
		ResultSet rs_categories = ps.executeQuery();
		return rs_categories;	
	}

	
	public void addBook( String title, String author, String publisher, String isbn, String bookNumber, String discription, String price) throws ClassNotFoundException, SQLException {
    	ps = conn.prepareStatement(addBook);
    	ps.setString(1, title);
    	ps.setString(2, author);
    	ps.setString(3, publisher);
    	ps.setString(4, isbn);
    	ps.setString(5, bookNumber);
    	ps.setString(6, discription);
    	ps.setString(7, price);
    	int rs = ps.executeUpdate();
    	System.out.println("added" + rs + "rows");
	}
	
	public void upateBook(String book_id,String title, String author, String publisher, String isbn, String bookNumber, String discription, String price) throws ClassNotFoundException, SQLException {
    	ps = conn.prepareStatement(updateBookById);
    	ps.setString(1, title);
    	ps.setString(2, author);
    	ps.setString(3, publisher);
    	ps.setString(4, isbn);
    	ps.setString(5, bookNumber);
    	ps.setString(6, discription);
    	ps.setString(7, price);
    	ps.setString(8, book_id);
    	int rs = ps.executeUpdate();
    	System.out.println("updated" + rs + "rows");
	}
	
	public ResultSet searchBookById(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByID);
		ps.setString(1, arg0);
		return ps.executeQuery();
	}
	
	public ResultSet searchBookByISBN(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByISBN);
		ps.setString(1, arg0);
		return ps.executeQuery();
	}
	
	public ResultSet searchBookByTitle(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByTitle);
		ps.setString(1, "%" + arg0 + "%");
		return ps.executeQuery();
	}
	
	public ResultSet searchBookByPublisher(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByPublisher);
		ps.setString(1, "%" + arg0 + "%");
		return ps.executeQuery();
	}
	
	public ResultSet searchBookByAuthor(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByAuthor);
		ps.setString(1, "%" + arg0 + "%");
		return ps.executeQuery();
	}
	
	public ResultSet searchBookByBookNumber(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByBookNumber);
		ps.setString(1, arg0);
		return ps.executeQuery();
	}

	public int getBookCountInCategory(String encode) throws SQLException {	
		encode += "%";
		ps = conn.prepareStatement(getCountOfBookInCategory);
		ps.setString(1, encode);
		ResultSet count = ps.executeQuery();
		count.next();
		int i = count.getInt(1);
		return i;
	}

	public ResultSet SearchAdvanceBook(SearchFilter filter) throws SQLException {
		String sql = "SELECT book.book_number, book.title, book.author, book.publisher, book.isbn, category.name as categoryName"
				+ " FROM book" + " LEFT JOIN categorybook on categorybook.book_id = book.book_id"
				+ " LEFT JOIN category on category.category_id = categorybook.category_id"
				+ " where ('?' is null or book.title like '?') and ('?' is null or book.author like '?') and ('?' is null or category.name = '?')";

		ps = conn.prepareStatement(sql);
		ps.setString(1, filter.Title);
		ps.setString(2, "%" + filter.Title + "%");
		ps.setString(3, filter.Author);
		ps.setString(4, "%" + filter.Author + "%");
		ps.setString(5, filter.PublishCompany);
		ps.setString(6, "%" + filter.PublishCompany + "%");
		ps.setString(7, filter.CategoryName);
		ps.setString(8, "%" + filter.CategoryName + "%");

		return ps.executeQuery();
	}

	public ResultSet GetBookByBookId(int bookID) throws Exception {
		String sql = "Select book.*, category.name as categoryName "
				+ " FROM book  LEFT JOIN categorybook on categorybook.book_id = book.book_id"
				+ " LEFT JOIN category on category.category_id = categorybook.category_id" + " where book.bookID = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, Integer.toString(bookID));
		return ps.executeQuery();
	}


	
	
//	public static void main(String[] args) {
//		try {
//			System.out.println(Book.getInstance().getBookCountInCategory("2"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
