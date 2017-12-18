package BookManager.Entity;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.DBConnect;
import java.sql.Statement;


/**
 * Enity Của BookManager. Truy xuất dữ liệu.
 * @author anonymous588
 *
 */
public class BookManager {
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
	
    /**
     *
     */
    public BookManager() {}
	
    /**
     *
     */
    public Connection conn = DBConnect.getConnection();

    /**
     *
     */
    public PreparedStatement ps;
            /**
     * get All book 
     * @throws SQLException 
     * @return list of book under ResultSet type
     */	
	
	public ResultSet getAllBook() throws SQLException {
		ps = conn.prepareStatement(getAllBook);
		ResultSet rs_categories = ps.executeQuery();
		return rs_categories;	
	}
	
	//Tim categories theo book_id

    /**
     *
     * @param book_id
     * @return
     * @throws SQLException
     */
	public ResultSet getCategoryByBookId(int book_id) throws SQLException {
		ps = conn.prepareStatement(getCategoriesByBookID);
		ps.setInt(1, book_id);
		ResultSet rs_categories = ps.executeQuery();
		return rs_categories;	
	}
        
            /**
     * create new book with the info inside 
     * @throws java.lang.ClassNotFoundException
     * @throws SQLException 
     * @param title ten sach
     * @param author ten tac gia
     * @param publisher ten nha xuat ban
     * @param isbn ma so isbn cua sach
     * @param bookNumber ma so sach
     * @param discription mo ta noi dung sach
     * @param price gia sach
     */
	
	public void addBook( String title, String author, String publisher, String isbn, String bookNumber, String discription, int price) throws ClassNotFoundException, SQLException {
            ps = conn.prepareStatement(addBook);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setString(4, isbn);
            ps.setString(5, bookNumber);
            ps.setString(6, discription);
            ps.setInt(7, price);
            int rs = ps.executeUpdate();
            System.out.println("added" + rs + "rows");
	}
	
    /**
     *
     * @param book_id
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
    public void updateBook(int book_id,String title, String author, String publisher, String isbn, String bookNumber, String discription, int price) throws ClassNotFoundException, SQLException {
            ps = conn.prepareStatement(updateBookById);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setString(4, isbn);
            ps.setString(5, bookNumber);
            ps.setString(6, discription);
            ps.setInt(7, price);
            ps.setInt(8, book_id);
            int rs = ps.executeUpdate();
            System.out.println("updated" + rs + "rows");
	}
	
    /**
     *
     * @param arg0
     * @return
     * @throws SQLException
     */
    public ResultSet searchBookById(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByID);
		ps.setString(1, arg0);
		return ps.executeQuery();
	}
	
    /**
     *
     * @param arg0
     * @return
     * @throws SQLException
     */
    public ResultSet searchBookByISBN(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByISBN);
		ps.setString(1, arg0);
		return ps.executeQuery();
	}
	
    /**
     *
     * @param arg0
     * @return
     * @throws SQLException
     */
    public ResultSet searchBookByTitle(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByTitle);
		ps.setString(1, "%" + arg0 + "%");
		return ps.executeQuery();
	}
	
    /**
     *
     * @param arg0
     * @return
     * @throws SQLException
     */
    public ResultSet searchBookByPublisher(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByPublisher);
		ps.setString(1, "%" + arg0 + "%");
		return ps.executeQuery();
	}
	
    /**
     *
     * @param arg0
     * @return
     * @throws SQLException
     */
    public ResultSet searchBookByAuthor(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByAuthor);
		ps.setString(1, "%" + arg0 + "%");
		return ps.executeQuery();
	}
	
    /**
     *
     * @param arg0
     * @return
     * @throws SQLException
     */
    public ResultSet searchBookByBookNumber(String arg0) throws SQLException {
		ps = conn.prepareStatement(getBookByBookNumber);
		ps.setString(1, arg0);
		return ps.executeQuery();
	}

    /**
     *
     * @param encode
     * @return
     * @throws SQLException
     */
    public int getBookCountInCategory(String encode) throws SQLException {	
		encode += "%";
		ps = conn.prepareStatement(getCountOfBookInCategory);
		ps.setString(1, encode);
		ResultSet count = ps.executeQuery();
		count.next();
		return count.getInt(1);
	}
        
            /**
     * get book by search with book_id 
     * @throws SQLException 
     * @param bookID gia tri book_id
     * @return book co gia tri book_id duoi dang resultSet
     */
	public ResultSet GetBookByBookId(int bookID) throws Exception {
		String sql = "Select book.*, category.name as categoryName "
				+ " FROM book  LEFT JOIN categorybook on categorybook.book_id = book.book_id"
				+ " LEFT JOIN category on category.category_id = categorybook.category_id" + " where book.bookID = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, bookID);
		return ps.executeQuery();
	}
        
            /**
     * get All catalog that have  in args 
     * @throws SQLException 
     * @param args ten truong can sort theo
     * @return list cac gia tri khi loc theo args
     */
        
        public ArrayList<String> GetBookCatalog(String args) throws SQLException{
            args.toLowerCase();
            ArrayList<String> catalog=new ArrayList();
            String sql = "select book."+args+" from book group by "+args;
            System.out.print(sql);
            Statement st=conn.createStatement();
            ResultSet set=st.executeQuery(sql);
            while(set.next()){
            catalog.add(set.getString(args));
            }
            return catalog;
        }
            /**
     * get All book that have args in catalog 
     * @throws SQLException 
     * @param  catalog muc can tìm
     * @param args gia tri can tim
     * @return list of book in ResultSet type
     */
        
        public ResultSet getBookByCatalog(String catalog, String args) throws SQLException {
            catalog.toLowerCase();
            String sql = "Select book.* from book where book."+catalog+" = ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, args);
            return ps.executeQuery();
        }


	
	
//	public static void main(String[] args) {
//		try {
//			System.out.println(BookManager.getInstance().getBookCountInCategory("2"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
