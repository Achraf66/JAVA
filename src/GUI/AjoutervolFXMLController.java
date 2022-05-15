/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.lowagie.text.Jpeg;
import edu.esprit.entities.Vol;
import edu.esprit.services.VolCRUD;
import edu.esprit.utils.MyConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author Achraf
 */

public class AjoutervolFXMLController implements Initializable {


    @FXML
    private TextField tfdestination;
    @FXML
    private TextField tfplace;
    @FXML
    private DatePicker dpdatedepart;
    @FXML
    private DatePicker dpdatearrive;
    @FXML
    private ChoiceBox<String> slHeureDepart;
    @FXML
    private ChoiceBox<String> slHeurearrive;
    @FXML
    private TextField path;
    private FileChooser filechooser;
    
    private File file;
    
    private Image image1;
    
    @FXML
    private ImageView imageview1;
    
    private FileInputStream fis;
    @FXML
    private TextField tfnomvol;
    @FXML
    private ChoiceBox<String> slHeureDepart1;
    @FXML
    private ChoiceBox<String> slHeurearrive1;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      slHeureDepart.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");  
      slHeurearrive.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");  
      slHeureDepart1.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");  
      slHeurearrive1.getItems().addAll("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");  


      path.setPromptText("Path of selected file");
      path.setEditable(false);
      
      filechooser = new FileChooser();
      filechooser.getExtensionFilters().addAll(
              new FileChooser.ExtensionFilter("All files","*.*"),
              new FileChooser.ExtensionFilter("Text files","*.txt"),
              new FileChooser.ExtensionFilter("Image files","*.png","*.jpg","*.gif")
              
      );
      
      
      

        }


    @FXML
    private void ajoutervol(ActionEvent event) {
      
      
     
        if((valdiatefield()) && validatedate())
     
     
     {  
            try {
                VolCRUD vcd = new VolCRUD();
                Vol v = new Vol();
                String dd = dpdatedepart.getValue().toString();
                String da = dpdatearrive.getValue().toString();
                int a = Integer.parseInt(tfplace.getText());
                v.setDestination(tfdestination.getText());
                
                
                
                
                
                v.setNbrplace(a);
                v.setDatedepart(dd);
                v.setDatearrive(da);
                v.setHeuredepart(slHeureDepart.getValue()+slHeureDepart1.getValue()+"00");
                v.setHeurearrive(slHeurearrive.getValue()+slHeurearrive1.getValue()+"00");
                v.setNom(tfnomvol.getText());
                fis = new FileInputStream(file);
                
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
                pst.setBinaryStream(7,(InputStream)fis,(int)file.length());
                pst.setString(8,v.getNom());
                
                pst.executeUpdate();
                
                /*     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout");
                alert.setHeaderText(null);
                alert.setContentText("Vol ajouté avec succés");
                alert.showAndWait();*/
                
                
                Notifications notificationBuilder = Notifications.create()
                        .title("5 Star Tours")
                        .text("Vol ajouté avec succés")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler <ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event)
                            {
                                System.out.println("Notification");
                            }
                            
                        });
                notificationBuilder.darkStyle();
                notificationBuilder.showConfirm();
            } catch (SQLException ex) {
                Logger.getLogger(AjoutervolFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjoutervolFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       
       
        
        
    }
    }
    

            private boolean valdiatefield()
    {
    if (     (dpdatedepart.getValue()==null) &&
              (dpdatearrive.getValue() == null) && 
             (tfdestination.getText().isEmpty())&&
              (tfplace.getText().isEmpty()) &&
            (slHeureDepart.getValue() == null)&&
            (slHeurearrive.getValue() == null)&&
            (tfnomvol.getText().isEmpty())
        )
       
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Tous les Champs  sont vides");
        alert.showAndWait();
     return false;
    
    }
    
    if (dpdatedepart.getValue()==null)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Verifier la date de depart !!");
        alert.showAndWait();
        return false;
    }
    
        if (dpdatearrive.getValue()==null)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Verifier la date de d'Arrivée !!");
        alert.showAndWait();
        return false;
    }
    
         if (tfdestination.getText().isEmpty())
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir la destination !");
        alert.showAndWait();
        return false;
    }
           if (tfplace.getText().isEmpty())
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez indiquez le nombre de place!!");
        alert.showAndWait();
        return false;
    }
    
               if (slHeureDepart.getValue() == null)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir une heure de depart!!");
        alert.showAndWait();
        return false;
    }
                  if (slHeurearrive.getValue() == null)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir une Heure d'arrivée!!");
        alert.showAndWait();
        return false;
    }
                  
                  
                  if (tfnomvol.getText().isEmpty())
                      
                      
                  {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez indiquez le nom du vol");
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
            if (dpdatedepart.getValue().isAfter(dpdatearrive.getValue()))
            {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Date");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir une date de depart inferieur a la date d'arrivée");
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

    @FXML
    private void browse(ActionEvent event)
    {
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       file = filechooser.showOpenDialog(window);
       
     if(file !=null)
     {
     path.setText(file.getAbsolutePath());
     image1 = new Image(file.toURI().toString(),100,150,true,true);
     imageview1.setImage(image1);
     
     }

 
    }
}

    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

