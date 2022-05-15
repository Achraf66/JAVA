/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Hotel;
import edu.esprit.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mootaz
 */
public class HotelCRUD 
{

    public void reserverHotel2(Hotel h)
    {
        
        try {
            
        Date date1 = new Date();
        String dateReservation = new SimpleDateFormat("dd-MM-yyyy").format(date1);
        
        
        Date date2 = new Date();
        String checkin = new SimpleDateFormat("dd-MM-yyyy").format(date2);
        
            
         Date date3 = new Date();
        String checkout = new SimpleDateFormat("dd-MM-yyyy").format(date3);
        
        
            String requete2 ="INSERT INTO hotel (dateReservation,checkin,checkout,nomhotel,endroit)"
                            +"VALUES(?,?,?,?,?)";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
       
        
        
        
        pst.setString(1, h.getDateReservation());
        pst.setString(2, h.getCheckin());
        pst.setString(3, h.getCheckout());
        pst.setString(4, h.getNomhotel());
        pst.setString(5, h.getEndroit());
        pst.executeUpdate();
        System.out.println("hotel reserver avec succes !!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
    public List<Hotel> afficherHotel() 
    {
        List<Hotel> mylist = new ArrayList<>();
        try {
            
           
            String requete3 = "SELECT * FROM hotel";
            
            Statement st = null;
                        st = new MyConnection().getCnx().createStatement();  
            ResultSet rs = null;
                 rs = st.executeQuery(requete3);
   while(rs.next()){
           Hotel h = new Hotel();
           h.setCode(rs.getInt(1));
           h.setDateReservation(rs.getString("DateReservation"));
           h.setNomhotel(rs.getString("nomhotel"));
           h.setCheckin(rs.getString("checkin"));
           h.setCheckout(rs.getString("checkout"));
           h.setEndroit(rs.getString("endroit"));
           mylist.add(h);
           }
      
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
             return mylist;
    
    }

   
    
  
    public void modifierReservation(Hotel h, int c)
    {
        
        try {
            String sql = "UPDATE hotel SET `DateReservation`=?,`nomhotel`=?,`checkin`=?,`checkout`=?,`endroit`=? WHERE code=" + c;
            PreparedStatement ste = new MyConnection().getCnx().prepareStatement(sql);
           
            ste.setString(1, h.getDateReservation());
            ste.setString(2, h.getNomhotel());
            ste.setString(3, h.getCheckin());
            ste.setString(4, h.getCheckout());
            ste.setString(5, h.getEndroit());

            ste.executeUpdate();
        
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
  
    }
   
   public void supprimerReservation(int c) {

        
        try {
            
            String req5 = "DELETE FROM `hotel` WHERE `hotel`.`code` = ?" ;
            PreparedStatement ste = new MyConnection().getCnx().prepareStatement(req5);
            ste.setInt(1,c);
            ste.executeUpdate();
            System.out.println("La reservation est supprimé");
        
        
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

      

    }  

}
   
 
    
    
    


