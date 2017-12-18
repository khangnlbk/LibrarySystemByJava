/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardManager.Entity;

import Helper.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * Truy cap du lieu tim kiem thong tin lien quan den card
 * @author anonymous588
 */

public class CardManager {

    private Connection conn = DBConnect.getConnection();
    private PreparedStatement ps;
 
    public CardManager() {}
        
    /**
     * activate 1 card
     * @param card_id ma so card
     * @param activate_code ma kich hoat
     * @param user_id ma ng kich hoat
     * @return true co the kich hoat dc the
     */
    public boolean activateCard(int card_id,String activate_code, int user_id){
        String query = "update borrowcard set user_id=? where card_id=?";
        String search_query = "select * from borrowcard where card_id=?";
        try {
            ps=conn.prepareStatement(search_query);
            ps.setInt(1, card_id);
            ResultSet set=ps.executeQuery();
            if(!set.next())
                return false;
            if(activate_code.compareTo(set.getString("activate_code"))!=0)
                return false;
        } catch (SQLException ex) {
        }
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, user_id);
            ps.setInt(2, card_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return true;
    }
        
    /**
     * Lay card id co nghia cua user
     * @param user_id ma so ng dung
     * @return card_id hoac -1 trong truong hop khong tim thay
     */
    public int getCardId(int user_id) {
        int card_id=-1;
        String query = "select * from borrowcard where user_id=?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,user_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                card_id = rs.getInt(rs.findColumn("card_id"));
            } 
        } catch (SQLException ex) {
        }

       return card_id;
    }

    /**
     * get borrowed card by user id
     * @param user_id
     * @return
     * @throws SQLException
     */
    public ResultSet getBorrowCard(int user_id) throws SQLException {
        String getBorrowCard = "select * from borrowcard where user_id=?";
        ps = conn.prepareStatement(getBorrowCard);
        ps.setInt(1, user_id);
        return ps.executeQuery();				
    }
    
    /**
     * Lay tat ca cac card
     * @return list card duoi dang ResultSet
     */        
    public ResultSet getAllCard(){
        String query = "select * from borrowcard";
        try {
            ps=conn.prepareStatement(query);
            ResultSet sets=ps.executeQuery();
            return sets;
        } catch (SQLException ex) {
        }
        return null;
    }

    /**
     * Get card with card id
     * @param card_id
     * @return
     */
    public ResultSet getCard(int card_id){
        String query = "select * from borrowcard where card_id= ?";
        try {
            ps=conn.prepareStatement(query);
            ps.setInt(1,card_id);
            ResultSet sets=ps.executeQuery();
        return sets;
        } catch (SQLException ex) {
        }

        return null;        
    }

    /**
     * Lay gia tri của ma so card tiep theo co the tao dc
     * @return gia tri của ma so card tiep theo co the tao dc
     */
    public int getNextId(){
     String query = "select * from borrowcard";
     int i=1;
        try {
            ps=conn.prepareStatement(query);
            ResultSet sets=ps.executeQuery();
            sets.afterLast();
            sets.previous();
            i=sets.getInt("card_id")+1;
        } catch (SQLException ex) {
        }
        return i;        
    }
        
    /**
     * Tao 1 card ms
     * @param card_id ma so card moi
     * @param activate ma so kich hoat
     * @param date ngay het han
     * @param deposit khoan tien dat coc
     */        
    public void issueNewCard(int card_id,String activate, Date date, int deposit){
        try {
            String query = "Insert into borrowcard(card_id,expired_date,deposit,activate_code) value (?,?,?,?)";
            ps=conn.prepareStatement(query);
            ps.setInt(1, card_id);
            ps.setString(4,activate);
            ps.setInt(3, deposit);
            ps.setDate(2,  date);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

}
