package BookManager.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BookManager.Book;
import BookManager.CategoryBook;
import BookManager.Copy;
import BookManager.Controller.SearchAdvanceController.SearchFilter;
import TableProperty.BookInstance;
import TableProperty.CopyInstance;

/**
 * Mediator của package BookManager. Truy xuất dữ liệu của Book, Copy, BookCategory, ...
 * @author anonymous588
 *
 */
public class BookManagerController {
	
	private static BookManagerController instance = null;
	private BookManagerController() {}
	public static BookManagerController getInstance() {
		if(instance == null) {
			instance = new BookManagerController();
		}
		return instance;
	}
	
	private Book b = new Book();
	private Copy c = new Copy();
	private CategoryBook cb = new CategoryBook();
	
	private BookInstance update_book = null;
	private CopyInstance update_copy = null;
	
	public CopyInstance getUpdate_copy() {
		return update_copy;
	}
	public void setUpdate_copy(CopyInstance update_copy) {
		this.update_copy = update_copy;
	}
	public BookInstance getUpdate_book() {
		return update_book;
	}
	public void setUpdate_book(BookInstance update_book) {
		this.update_book = update_book;
	}
	
	public void addBook(String title, String author, String publisher, String isbn, String bookNumber, String discription, String price) throws ClassNotFoundException, SQLException {
		b.addBook(title, author, publisher, isbn, bookNumber,discription, price);
	}
	
	public void addCategoryBook(String book_id, String category_id) throws SQLException {
		cb.addCategoryBook(book_id, category_id);
	}
	
	public ResultSet getCategories(String book_id) throws SQLException {
		return b.getCategoryByBookId(book_id);
	}
	
	public ResultSet getAllBook() throws SQLException {
		return b.getAllBook();
	}
	
	// get quantity of that category +1, type == 0: categoryID, type == 1: category Name, type == 2: category encode
	public int getNewBookSequence(String encode) {
		try {
			return b.getBookCountInCategory(encode) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//get quantity of book's copies.
	public int getCountOfCopy(String book_id) {
		try {
			return c.getCountOfCopy(book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ResultSet searchBook(String arg0, String type) throws SQLException {
		if(type.equals("book_id")) {
			System.out.println("search by id");
			return b.searchBookById(arg0);
		}
		if(type.equals("isbn")) {
			System.out.println("search by isbn");
			return b.searchBookByISBN(arg0);
		}
		if(type.equals("title")) {
			System.out.println("search by title");
			return b.searchBookByTitle(arg0);
		}
		if(type.equals("author")) {
			System.out.println("search by author");
			return b.searchBookByAuthor(arg0);
		}
		if(type.equals("publisher")) {
			System.out.println("search by publisher");
			return b.searchBookByPublisher(arg0);
		}
		if(type.equals("book_number")) {
			System.out.println("search by book_number");
			return b.searchBookByBookNumber(arg0);
		}
		
		return null;
	}
	
	public boolean isBorrowalbe(String book_id) {
		try {
			return c.isBorrowable(book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void addNewCopy(String book_id, String copy_number, String status, String price, String condition, String copy_type) {
		try {
			c.addCopy(book_id, copy_number, status, price, condition, copy_type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet SearchBookAdvance(SearchFilter filter) throws SQLException {
		return b.SearchAdvanceBook(filter);
	}

	public ResultSet GetBookByBookID(int bookID) throws Exception {
		// TODO Auto-generated method stub
		return b.GetBookByBookId(bookID);
	}
	
	public String getCopyID(String book_id) {
		try {
			return c.getCopyID(book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateCopyStatus(String copy_id, String status) {
		try {
			c.updateCopyStatus(copy_id, status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteBookCategory(String book_id) {
		try {
			cb.deleteBookCategory(book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateBook(String book_id, String title, String author, String publisher, String isbn, String bookNumber, String discription, String price) {
		try {
			b.upateBook(book_id, title, author, publisher, isbn, bookNumber, discription, price);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateCopyNumber(String book_id, String new_book_number) {
		try {
			c.updateCopyNumber(book_id, new_book_number);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setBrowseCopyPaneInfor(String book_id) {
		BrowseCopyPaneController.setBook_id(book_id);
	}
	
	
	
	public ResultSet getCopy(String book_id) {
		try {
			return c.getCopy(book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateCopy(String status, String price, String copy_condition, String copy_type) {
		try {
			c.updateCopy(update_copy.getCopy_id(), status, price, copy_condition, copy_type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
