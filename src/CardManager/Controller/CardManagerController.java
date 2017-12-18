/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardManager.Controller;

import CardManager.Entity.CardManager;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author anonymous588
 */
public class CardManagerController {
    
    private CardManager cardManager = new CardManager();
    
    /**
     * activate Card
     * @param card_id
     * @param activate_code
     * @param user_id
     * @return true if activate successfully
     */
    public boolean activateCard(int card_id, String activate_code, int user_id) {
        return cardManager.activateCard(card_id, activate_code, user_id);
    }
    
    /**
     * Lay gia tri của ma so card tiep theo co the tao dc
     * @return gia tri của ma so card tiep theo co the tao dc
     */
    public int getLastId() {
        return cardManager.getNextId();
    }
    
    /**
     * Tao 1 card ms
     * @param card_id ma so card moi
     * @param activate_code ma so kich hoat
     * @param date ngay het han
     * @param deposit khoan tien dat coc
     */    
    public void issueNewCard(int card_id, String activate_code, Date date, int deposit) {
        cardManager.issueNewCard(0, activate_code, date, 0);
    }
    
    /**
     * Get all card list
     * @return card list
     */
    public ResultSet getAllCard() {
        return cardManager.getAllCard();
    }
    
    /**
     * get card by card id
     * @param card_id
     * @return card
     */
    public ResultSet getCardById(int card_id) {
        return cardManager.getCard(card_id);
    }
}
