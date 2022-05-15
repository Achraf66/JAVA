/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Vol;
import java.sql.Statement;
import java.util.List;
import edu.esprit.utils.MyConnection;
import java.io.InputStream;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author Achraf
 */
public class VolCRUD 
{
    /*
    public void ajouterVol()
    {
        try {
            String requete ="INSERT INTO vol (dateDepart,HeureDepart,destination,place)"
                    + "VALUES (str_to_date('02/02/1930','%m/%d/%Y'),'16h','tunis','74')";
            
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Vol Ajouté avec succes !!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
      */ 
    public void ajouterVol2(Vol v)
    {
        
        try {
            
        Date date1 = new Date();
        String dateDepart = new SimpleDateFormat("dd-MM-yyyy").format(date1);
        String datearrive = new SimpleDateFormat("dd-MM-yyyy").format(date1);
            
            
            String requete2 ="INSERT INTO vol (datedepart,datearrive,destination,nbrplace,heuredepart,heurearrive,airlinee,nom)"
                            +"VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
       
        
        
        
        pst.setString(1,v.getDatedepart());
        pst.setString(2, v.getDatearrive());
        pst.setString(3, v.getDestination());
        pst.setInt(4, v.getNbrplace());
        pst.setString(5, v.getHeuredepart());
        pst.setString(6, v.getHeurearrive());
        pst.setString(7, v.getNom());
        pst.executeUpdate();
       /* System.out.println("Vol Ajouté avec succes !!");*/
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
    public List<Vol> afficherVol() 
    {
        List<Vol> mylist = new ArrayList<>();
        try {
            
           
            String requete3 = "SELECT * FROM vol";
            
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
           Vol v = new Vol();
           v.setId(rs.getInt(1));
           v.setDatedepart(rs.getString("datedepart"));
            v.setDatearrive(rs.getString("datearrive"));
           v.setDestination(rs.getString("destination"));
           v.setNbrplace(rs.getInt(5));
           v.setHeuredepart(rs.getString("heuredepart"));
           v.setHeurearrive(rs.getString("heurearrive"));
           v.setNom("nom");
           mylist.add(v);
           }
      
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
             return mylist;
    
    }
    public void modifiervol(Vol v, int c)
    {
        
        try {
            
            String sql = "UPDATE vol SET `datedepart`=?,`datearrive`=?,`destination`=?,`nbrplace`=?,`heuredepart`=?,`heurearrive`=?,`nom`=? WHERE id=" + c;
            PreparedStatement ste = new MyConnection().getCnx().prepareStatement(sql);
           
            ste.setString(1, v.getDatedepart());
            ste.setString(2, v.getDatearrive());
            ste.setString(3, v.getDestination());
            ste.setInt(4, v.getNbrplace());
            ste.setString(5, v.getHeuredepart());
            ste.setString(6, v.getHeurearrive());
            ste.setString(7, v.getNom());
            ste.executeUpdate();
        
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
  
    }
   
    public void supprimervol(int c) {

        
        try {
            String req5 = "DELETE FROM `vol` WHERE `vol`.`id` = ?";
            PreparedStatement ste = new MyConnection().getCnx().prepareStatement(req5);
            ste.setInt(1,c);
            ste.executeUpdate();
           /* System.out.println("Le vol est supprimé");*/
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

      

    }  
 
    
    
    
} 

