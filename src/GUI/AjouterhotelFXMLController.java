/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Hotel;
import edu.esprit.services.HotelCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mootaz
 */
public class AjouterhotelFXMLController implements Initializable {

    @FXML
    private TextField tfendroit;
    @FXML
    private TextField tfnomhotel;
    @FXML
    private DatePicker dpcheckin;
    @FXML
    private DatePicker dpcheckout;
    @FXML
    private DatePicker dpdatereservation;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterhotel(ActionEvent event) {
      
        if((valdiatefield()) && validatedate())
     
     
     {  
        HotelCRUD hcd = new HotelCRUD();
        Hotel h = new Hotel();
        String dd = dpcheckin.getValue().toString();
        String da = dpcheckout.getValue().toString();  
        String dr = dpdatereservation.getValue().toString();  
        h.setEndroit(tfendroit.getText());
        h.setNomhotel(tfnomhotel.getText());
        h.setCheckin(dd);
        h.setCheckout(da);
        h.setDateReservation(dr);
        hcd.reserverHotel2(h);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout");
            alert.setHeaderText(null);
            alert.setContentText("hotel ajouté avec succés");
            alert.showAndWait();
        
        
    }
    }
    

            private boolean valdiatefield()
    {
    if (     (dpdatereservation.getValue()==null) &&
            (dpcheckin.getValue()==null) &&
              (dpcheckout.getValue() == null) && 
             (tfendroit.getText().isEmpty())&&
              (tfnomhotel.getText().isEmpty()
             ))
       
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Tous les Champs  sont vides");
        alert.showAndWait();
     return false;
    
    }
    
    if (dpdatereservation.getValue()==null)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Verifier la date de reservation !!");
        alert.showAndWait();
        return false;
    }
    
        if (dpcheckin.getValue()==null)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Verifier checkin !!");
        alert.showAndWait();
        return false;
    }
         if (dpcheckout.getValue()==null)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Verifier checkout !!");
        alert.showAndWait();
        return false;
    }
    
         if (tfendroit.getText().isEmpty())
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir un ednroit !");
        alert.showAndWait();
        return false;
    }
           if (tfnomhotel.getText().isEmpty())
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez indiquez le nom hotel!!");
        alert.showAndWait();
        return false;
    }
    
              
        
    else 
    {
        return true ;
     }
    
    
    }
            
            private boolean validatedate()
            {
            if (dpcheckin.getValue().isAfter(dpcheckout.getValue()))
            {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Date");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir un checkin inferieur a checkout");
        alert.showAndWait();
       return false;         
 
            }
            else {return true;}

            }
            
            
    @FXML
    private void menuprincipale(ActionEvent event) {
        
        
     
     Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
  
    window.close();
   
    }

 
}    