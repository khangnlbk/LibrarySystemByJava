/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import BookManager.Boundary.AddNewBookView;
import BookManager.Boundary.AddNewCopyView;
import BookManager.Boundary.BrowsingCatalogView;
import BookManager.Boundary.SearchView;
import BorrowingManager.Boundary.BorrowerRegistrationPaneView;
import BorrowingManager.Boundary.HistoryView;
import BorrowingManager.Boundary.LibrarianBorrowingRegistrationPaneView;
import CardManager.Boundary.ActivateCardView;
import CardManager.Boundary.AddNewCardView;
import CardManager.Boundary.CardManagementView;
import java.awt.BorderLayout;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author anonymous588
 */
public class ViewFactory {
    public ViewAbstract getView(String name, BorderPane layout){
        switch(name) {
            case "add_book": 
                return new AddNewBookView(layout);
            case "add_copy":
                return new AddNewCopyView(layout);
            case "search":
                return new SearchView(layout);
            case "browsing_catalog":
                return new BrowsingCatalogView(layout);
            case "borrow_register":
                return new BorrowerRegistrationPaneView(layout);
            case "history":
                return new HistoryView(layout);
            case "borrow_manage":
                return new LibrarianBorrowingRegistrationPaneView(layout);
            case "activate": 
                return new ActivateCardView(layout);
            case "add_card": 
                return new AddNewCardView(layout);
            case "card_manage": 
                return new CardManagementView(layout);
            default:
                return null;
        }
    }
}
