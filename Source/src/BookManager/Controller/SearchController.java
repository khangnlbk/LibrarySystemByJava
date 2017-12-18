/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookManager.Controller;

/**
 *
 * @author anonymous588
 */

import BookManager.Boundary.BookDisplayView;
import BookManager.Entity.BookManager;
import java.sql.SQLException;
import BorrowingManager.Controller.BorrowerRegistration;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchController {
    
    private BookManager books= new BookManager();
    private BorrowerRegistration registration = BorrowerRegistration.getInstance();
    
    /**
     * Lay list sach tim thoe catalog va gia tri the
     * @return list sach tim thoe catalog va gia tri the
     * @param catalog: gia tri truong can phan loai
     * @param value: gia trị the nhap sau khi phan loai
     * @throws java.sql.SQLException
     */ 
    public ResultSet getBookinCatalog(String catalog, String value) throws SQLException{
        return books.getBookByCatalog(catalog,value);
    }
    
    /**
     * Tra ve list cac truong co the thuc hien browsing
     * @return searchOption chua ten cac truong co the browsing
     */
    public ObservableList<String> GetListCatalog(){
        ObservableList<String> searchOption = FXCollections.observableArrayList(
                "Author",
                "Publisher"
        );
        return searchOption;
    }
    
    /**
     * Tra ve list cac item sau khi thuc hien browsing catalog
     * @param args
     * @throws SQLException
     * @return List cac gia tri co the truy cap sau khi catalog theo truong
     * 
     */
    public ObservableList<String>getListItems(String args) throws SQLException {
        ArrayList<String> list=books.GetBookCatalog(args);
        ObservableList<String> Option = FXCollections.observableArrayList();
        Option.addAll(list);
        return Option;
    }
    
    /**
     * Lay ra day cac Book chua arg0 trong truong type
     * @param arg0 keyword
     * @param type id,isbn, title,author, publisher. book_number
     * @return day book duoi dang ResultSet
     * @throws SQLException
     */
    public ResultSet searchBook(String arg0, String type) throws SQLException {
            if(type.equals("book_id")) {
                    System.out.println("search by id");
                    return books.searchBookById(arg0);
            }
            if(type.equals("isbn")) {
                    System.out.println("search by isbn");
                    return books.searchBookByISBN(arg0);
            }
            if(type.equals("title")) {
                    System.out.println("search by title");
                    return books.searchBookByTitle(arg0);
            }
            if(type.equals("author")) {
                    System.out.println("search by author");
                    return books.searchBookByAuthor(arg0);
            }
            if(type.equals("publisher")) {
                    System.out.println("search by publisher");
                    return books.searchBookByPublisher(arg0);
            }
            if(type.equals("book_number")) {
                    System.out.println("search by book_number");
                    return books.searchBookByBookNumber(arg0);
            }

            return null;
    }
        
    
}
