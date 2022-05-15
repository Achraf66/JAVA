/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Hotel;
import edu.esprit.services.HotelCRUD;
import edu.esprit.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mootaz
 */
public class EditFXMLHotelController implements Initializable {


    
    ObservableList <Hotel> Hotellist = FXCollections.observableArrayList();
    @FXML
    private DatePicker dpcheckout;
    @FXML
    private DatePicker dpcheckin;
    @FXML
    private TextField tfsearch;
    @FXML
    private TextField tfnomhotel;
    @FXML
    private TableView<Hotel> table;
    @FXML
    private TableColumn<Hotel,String> tbendroit;
    @FXML
    private TableColumn<Hotel,String> tbnomhotel;
    @FXML
    private TableColumn<Hotel, String> tbdatereservation;
    @FXML
    private TableColumn<Hotel,String> tbcheckin;
    @FXML
    private TableColumn<Hotel,String> tbcheckout;
    @FXML
    private DatePicker dpdatereservation;
    @FXML
    private TextField tfendroit;
    

    /**
     * Initializes the controller class.
     * @param 
     * @param 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      

try {
          
            String query = "SELECT * FROM hotel";
            PreparedStatement ste = new MyConnection().getCnx().prepareStatement(query);
            ResultSet rs = ste.executeQuery(query);
            
            
            while (rs.next()){
                try {
                    Hotellist.add(new Hotel (rs.getInt("code"),rs.getString("dateReservation"),rs.getString("checkin"),rs.getString("checkout"), rs.getString("nomhotel"),rs.getString("endroit")));
                    
                    
            tbdatereservation.setCellValueFactory(new PropertyValueFactory<Hotel,String>("dateReservation"))  ; 
            tbcheckin.setCellValueFactory(new PropertyValueFactory<Hotel,String>("checkin"));
            tbcheckout.setCellValueFactory(new PropertyValueFactory<Hotel,String>("checkout"));
            tbnomhotel.setCellValueFactory(new PropertyValueFactory<Hotel,String>("nomhotel"));
            tbendroit.setCellValueFactory(new PropertyValueFactory<Hotel,String>("endroit"));
            
            
            
            table.setItems(Hotellist);
       
                
               
           FilteredList<Hotel> filteredData = new FilteredList<>(Hotellist, b -> true);
		
		
		tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(table-> {
                          
                          
                                
                                
                                if (newValue == null || newValue.isEmpty()) {
                                    return true;
                                }
                                
                                String lowerCaseFilter = newValue.toLowerCase();
                                if (String.valueOf(table.getEndroit()).indexOf(lowerCaseFilter) != -1 ) {
                                    return true; }
            
                                if (table.getDateReservation().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                return true;
                                }
                               
                                
                                 if (String.valueOf(table.getNomhotel()).indexOf(lowerCaseFilter)!=-1)
                                return true;
                                
                                if (table.getCheckin().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                return true;
                                } 
                                
                               else if (table.getCheckout().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                return true;
                                }    
                                
                                 
                                
                                else
                                { return false;} // Does not match.
                         
                           
			});
		});
		
		
		SortedList<Hotel> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		
		table.setItems(sortedData);     
                
                    
      
                
                
                } catch (SQLException ex) {
                    Logger.getLogger(EditFXMLHotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
         
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditFXMLHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        }
    
        
        
             
     
    private void ontableitemselect(MouseEvent event) {
       
     Hotel h = table.getSelectionModel().getSelectedItem();   
        if (h != null)
            
        {
        
         LocalDate localDate1;
         localDate1 = LocalDate.parse(h.getDateReservation().toString());
         dpdatereservation.setValue(localDate1);

         LocalDate localDate2;
         localDate2 = LocalDate.parse(h.getCheckin());
         dpcheckin.setValue(localDate2);
         
         
        LocalDate localDate3;
         localDate3 = LocalDate.parse(h.getCheckout());
         dpcheckout.setValue(localDate3);
         
          tfnomhotel.setText(String.valueOf(h.getNomhotel()));
       
         tfendroit.setText((String) h.getEndroit());
 
        

        }
        
 
    }

    @FXML
    private void onupdatehotel(ActionEvent event) 
    {
        
        HotelCRUD hcd = new HotelCRUD();
        Hotel h2 = (Hotel) table.getSelectionModel().getSelectedItem();
        
                  if (h2 == null)
        {
        
 
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Choisir un hotel pour modifier ");
            alert.showAndWait();

        
        }   
          
                  else{
                      
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation dialog");
            alert.setHeaderText(null);
            alert.setContentText("vous etes sur d'appliquer les modification?");
            Optional <ButtonType> action = alert.showAndWait();
          
            
            
            if (action.get() == ButtonType.OK){              
                      
                      
             
                try {
                    Hotel h3 = new Hotel(dpdatereservation.getValue().toString(), dpcheckin.getValue().toString(),dpcheckout.getValue().toString(), tfendroit.getText(),tfnomhotel.getText());
                    hcd.modifierReservation(h3, h2.getCode());
                    
                    
                    
                    Parent AfficherParent = FXMLLoader.load(getClass().getResource("EditFXMLHotel.fxml"));
                    Scene AfficherScene = new Scene(AfficherParent);
                    
                    
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    
                    window.setScene(AfficherScene);
                    window.show();
                    window.setTitle("Modifier Hotel");
                    
                    
                    
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Done ");
                    alert2.setHeaderText("Updates bien ajouté  ");
                    alert2.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(EditFXMLHotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
                  }
                  } 
            

    @FXML
    private void ondeletehotel(ActionEvent event) {
  
        HotelCRUD hcd = new HotelCRUD();
        Hotel h2 = (Hotel) table.getSelectionModel().getSelectedItem();

        
        if (h2 == null)
        {
  
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Choisir un hotel pour supprimer ");
            alert.showAndWait();
        

        }
        else
        {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation dialog");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous effecitivement supprimer? ");
            Optional <ButtonType> action = alert.showAndWait();
          
            
            
            if (action.get() == ButtonType.OK)
            
            {
            
                
               try {
                   /* try {*/
                   hcd.supprimerReservation(h2.getCode());
                   Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                   alert1.setTitle("Done ");
                   alert1.setHeaderText("hotel supprimer Avec Succés");
                   alert1.showAndWait();
                   
                   
                   Parent AfficherParent = FXMLLoader.load(getClass().getResource("EditFXMLHotel.fxml"));
                   Scene AfficherScene = new Scene(AfficherParent);
                   
                   
                   Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                   
                   window.setScene(AfficherScene);
                   window.show();
                   window.setTitle("Modifier hotel");
               } catch (IOException ex) {
                   Logger.getLogger(EditFXMLHotelController.class.getName()).log(Level.SEVERE, null, ex);
               }
             
      
        }
    }
    }

    @FXML
    private void menupricipal(ActionEvent event)
    {
  
    
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.close();
   
    }
    
}