package BookManager.Controller;

import BookManager.Boundary.AddNewCopyView;
import BookManager.Boundary.BrowseCopyPaneView;
import java.sql.ResultSet;
import java.sql.SQLException;

import BookManager.Entity.BookManager;
import BookManager.Entity.Category;
import BookManager.Entity.CategoryBook;
import BookManager.Entity.CategoryManager;
import BookManager.Entity.CopyManager;
import Helper.ViewAbstract;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 * Mediator của package BookManager. Truy xuất dữ liệu của BookManager, CopyManager, BookCategory, ...
 * @author anonymous588
 *
 */
public class BookManagerController {
	
//	private static BookManagerController instance = null;
	public BookManagerController() {}

    /**
     *
     * @return
     */
//    public static BookManagerController getInstance() {
//		if(instance == null) {
//			instance = new BookManagerController();
//		}
//		return instance;
//	}
	
	private BookManager b = new BookManager();
	private CopyManager c = new CopyManager();
        private CategoryManager cat = new CategoryManager();
	private CategoryBook cb = new CategoryBook();
	
        /**
         * Add book to database (table Book)
         * @param title
         * @param author
         * @param publisher
         * @param isbn
         * @param bookNumber
         * @param discription
         * @param price
         * @throws ClassNotFoundException
         * @throws SQLException 
         */
	public ViewAbstract addBook(String title, String author, String publisher, String isbn, String bookNumber, String discription, int price,ArrayList<Integer> categories) throws ClassNotFoundException, SQLException {
            b.addBook(title, author, publisher, isbn, bookNumber,discription, price);               
            ResultSet rs = b.searchBookByBookNumber(bookNumber);
            rs.next();
            for(int v: categories){
                addCategoryBook(rs.getInt(rs.findColumn("book_id")),v);
            }
            AddNewCopyView view= new AddNewCopyView();
            view.set_bookNumber(bookNumber);
            return view;
            
	}
	
        /**
         * Add book and category to database (table CategoryBook)
         * @param book_id
         * @param category_id
         * @throws SQLException 
         */
	public void addCategoryBook(int book_id, int category_id) throws SQLException {
		cb.addCategoryBook(book_id, category_id);
	}
	
        /**
         * Get all category by book id
         * @param book_id
         * @return ResultSet category list
         * @throws SQLException 
         */
	public ResultSet getCategories(int book_id) throws SQLException {
		return b.getCategoryByBookId(book_id);
	}
	
	
	// get quantity of that category +1, type == 0: categoryID, type == 1: category Name, type == 2: category encode
        /**
         * Get quantity of that category, then plus 1 to take new book sequence
         * @param encode
         * @return new book sequence
         */
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

    /**
     *
     * @param book_id
     * @return
     */
	public int getCountOfCopy(int book_id) {
		try {
			return c.getCountOfCopy(book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	
    /**
     *
     * @param book_id
     * @return
     */
    public boolean isBorrowalbe(int book_id) {
		try {
			return c.isBorrowable(book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
    /**
     *
     * @param book_id
     * @param copy_number
     * @param status
     * @param price
     * @param condition
     * @param copy_type
     */
    public void addNewCopy(int book_id, String copy_number, String status, int price, String condition, String copy_type) {
		try {
			c.addCopy(book_id, copy_number, status, price, condition, copy_type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    /**
     *
     * @param bookID
     * @return
     * @throws Exception
     */
    public ResultSet GetBookByBookID(int bookID) throws Exception {
		// TODO Auto-generated method stub
		return b.GetBookByBookId(bookID);
	}
	
    /**
     *
     * @param book_id
     * @return
     */
    public int getCopyID(int book_id) {
		try {
			return c.getCopyID(book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

    /**
     *
     * @param copy_id
     * @param status
     */
    public void updateCopyStatus(int copy_id, String status) {
		try {
			c.updateCopyStatus(copy_id, status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    /**
     *
     * @param book_id
     */
    public void deleteBookCategory(int book_id) {
		try {
			cb.deleteBookCategory(book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    /**
     *
     * @param book_id
     * @param new_book_number
     */
    public void updateCopyNumber(int book_id, String new_book_number) {
		try {
			c.updateCopyNumber(book_id, new_book_number);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//    /**
//     *
//     * @param book_id
//     */
//    public void setBrowseCopyPaneInfor(int book_id) {
//		BrowseCopyPaneView.setBook_id(book_id);
//	}
	
    /**
     *
     * @param book_id
     * @return
     */
    public ResultSet getCopy(int book_id) {
		try {
			return c.getCopy(book_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    
            /**
         * Get all book
         * @return ResultSet book list
         * @throws SQLException 
         */
	public ResultSet getAllBook() throws SQLException {
		return b.getAllBook();
	}
	    
        public ResultSet getAllCatalog() throws SQLException {
            return cat.getAllCategory();
        }
}
