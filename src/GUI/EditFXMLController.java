/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import edu.esprit.entities.Vol;
import edu.esprit.services.VolCRUD;
import edu.esprit.utils.MyConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class EditFXMLController implements Initializable {


    
    @FXML
    private TableView<Vol> table;
    @FXML
    private TableColumn<Vol,String> tbdestination;
    @FXML
    private TableColumn<Vol,String> tbplace;
    @FXML
    private TableColumn<Vol, String> tbdd;
    @FXML
    private TableColumn<Vol, String> tbhd;
    @FXML
    private TableColumn<Vol, String> tbda;
    @FXML
    private TableColumn<Vol, String> tbha;
    @FXML
    private TableColumn<Vol, String> tbnomvol;

    ObservableList <Vol> Vollist = FXCollections.observableArrayList();
    @FXML
    private DatePicker dpdatedepart;
    @FXML
    private DatePicker dpdatearrive;
    @FXML
    private TextField tfplace;
    @FXML
    private TextField tfdestination;
    @FXML
    private ChoiceBox<String> slHeureDepart;
    @FXML
    private ChoiceBox<String> slHeurearrive;
    @FXML
     private ImageView airline;
    @FXML
    private TextField tfsearch;
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

             
             try {
          
            String query = "SELECT * FROM vol";
            PreparedStatement ste = new MyConnection().getCnx().prepareStatement(query);
            ResultSet rs = ste.executeQuery(query);
       
            while (rs.next()){
                try {
                    Vollist.add(new  Vol(rs.getInt("id"),rs.getString("datedepart"), rs.getString("datearrive"), rs.getString("destination"),rs.getInt("nbrplace"), rs.getString("heuredepart"),rs.getString("heurearrive"),rs.getString("nom")));
                    if(rs.getBlob("airlinee")!=null)
                    {InputStream is = rs.getBinaryStream("airlinee");
                    OutputStream os = new FileOutputStream(new File("photo.jpg"));
                    
                    byte[] content = new byte[1024];
                    int size =0;
                    while ((size = is.read(content))!=-1)
                    {
                    
                    os.write(content,0,size);
                    
                    }
                    os.close();
                    is.close();
                    
        
                    Image image = new Image("file:photo.jpg");
                    airline.setImage(image);
                    }
                    else {airline.setImage(null);}    
                    
                    
                    
            tbdestination.setCellValueFactory(new PropertyValueFactory<Vol,String>("destination"));
            tbplace.setCellValueFactory(new PropertyValueFactory<Vol,String>("nbrplace"));
            tbdd.setCellValueFactory(new PropertyValueFactory<Vol,String>("datedepart"));
            tbhd.setCellValueFactory(new PropertyValueFactory<Vol,String>("heuredepart"));
            tbda.setCellValueFactory(new PropertyValueFactory<Vol,String>("datearrive"));
            tbha.setCellValueFactory(new PropertyValueFactory<Vol,String>("heurearrive"));
            tbnomvol.setCellValueFactory(new PropertyValueFactory<Vol,String>("nom"));                                                                                  
           
                    table.setItems(Vollist);
       
                
               
           FilteredList<Vol> filteredData = new FilteredList<>(Vollist, b -> true);
		
		
		tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(table-> {
                          
                          
                                
                                
                                if (newValue == null || newValue.isEmpty()) {
                                    return true;
                                }
                                
                                String lowerCaseFilter = newValue.toLowerCase();
                                if (table.getDestination().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                    return true; }
            
                                
                                
                                if (table.getDatedepart().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                return true;
                                }
                               
                                
                                 if (String.valueOf(table.getNbrplace()).indexOf(lowerCaseFilter)!=-1)
                                return true;
                                
                                if (table.getDatearrive().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                return true;
                                } 
                                
                                if (table.getHeuredepart().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                return true;
                                }    
                                
                                if (table.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                return true;
                                }    
         
                                
                               else  if (table.getHeurearrive().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                return true;
                                }      
                                
                                else
                                { return false;}                   
			});
		});
		
                SortedList<Vol> sortedData = new SortedList<>(filteredData);
		
                sortedData.comparatorProperty().bind(table.comparatorProperty());		
		
                table.setItems(sortedData);                
               
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EditFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EditFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
         
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    
        
        
             
     
    @FXML
    
     
    private void ontableitemselect(MouseEvent event) {
       
     Vol v = table.getSelectionModel().getSelectedItem();   
        if (v != null)
            
        {
    
               
            LocalDate localDate1;
            localDate1 = LocalDate.parse(v.getDatedepart().toString());
            dpdatedepart.setValue(localDate1);
            LocalDate localDate2;
            localDate2 = LocalDate.parse(v.getDatearrive().toString());
            dpdatearrive.setValue(localDate2);
            tfplace.setText(String.valueOf(v.getNbrplace()));
           
            tfdestination.setText(v.getDestination());
            
            slHeureDepart.setValue(v.getHeuredepart());
            
            slHeurearrive.setValue(v.getHeurearrive()); 
           
            tfnomvol.setText(v.getNom());
            
            String query = "SELECT `airlinee` FROM vol WHERE id="+v.getId();
            PreparedStatement ste;
            try {
   
             ste = new MyConnection().getCnx().prepareStatement(query);
             ResultSet rs = ste.executeQuery(query);
            
                while (rs.next()){
 
                 InputStream is = rs.getBinaryStream("airlinee");
                 OutputStream os = new FileOutputStream(new File("photo.jpg"));
                if(!(is.equals(null))){
                     byte[] content = new byte[1024];
                     int size =0;
                     while ((size = is.read(content))!=-1)
                     {
                         
                         os.write(content,0,size);
                         
                     }
                     os.close();
                     is.close();
                 
             Image image = new Image("file:photo.jpg");
             airline.setImage(image);       
                }
                else {is=null;}
                 }
             } catch (SQLException ex) {
             Logger.getLogger(EditFXMLController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(EditFXMLController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(EditFXMLController.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
             
      
         
        }  
    
    

             @FXML
             private void onupdatevol(ActionEvent event)
             {
                 
                 VolCRUD vcd = new VolCRUD();
                 Vol v2 = table.getSelectionModel().getSelectedItem();
          
                 if (v2 == null)
                 {
                     
                     
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("Erreur");
                     alert.setHeaderText(null);
                     alert.setContentText("Choisir un vol pour modifier ");
                     alert.showAndWait();
                     
                     
                 }
                 
                 else{
                     
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                     alert.setTitle("Confirmation dialog");
                     alert.setHeaderText(null);
                     alert.setContentText("vous etes sur d'appliquer les modification?");
                     Optional <ButtonType> action = alert.showAndWait();
                     
                     
                     
                     if (action.get() == ButtonType.OK){
                         
                         
                         Vol v3 = new Vol(dpdatedepart.getValue().toString(), dpdatearrive.getValue().toString(), tfdestination.getText(), Integer.valueOf(tfplace.getText()), slHeureDepart.getValue()+slHeureDepart1.getValue()+"00", slHeurearrive.getValue()+slHeurearrive1.getValue()+"00",tfnomvol.getText());
                         vcd.modifiervol(v3,v2.getId());
                         
                         try {
                             Parent AfficherParent = FXMLLoader.load(getClass().getResource("EditFXML.fxml"));
                             Scene AfficherScene = new Scene(AfficherParent);
                             
                             
                             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                             
                             window.setScene(AfficherScene);
                             window.show();
                             window.setTitle("Modifier Vol");
                         } catch (IOException ex) {
                             Logger.getLogger(AjoutervolFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         
                         Image img = new Image("/GUI/image/tick.png");
                         Notifications notificationBuilder = Notifications.create()
                                 .title("5 Star Tours")
                                 .text("Vol Modifié avec succés")
                                 .graphic(new ImageView (img))
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
                         notificationBuilder.show();
                         
                         
                         
                         
                         
                         
                     }
                 }
                 
             }
             
             @FXML
             private void ondeletevol(ActionEvent event) {
             
             VolCRUD vcd = new VolCRUD();
             Vol v2 = table.getSelectionModel().getSelectedItem();
             
             
             if (v2 == null)
             {
                 
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                 alert.setTitle("Erreur");
                 alert.setHeaderText(null);
                 alert.setContentText("Choisir un vol pour supprimer ");
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
                     
                     
                     vcd.supprimervol(v2.getId());
                     Notifications notificationBuilder = Notifications.create()
                             .title("5 Star Tours")
                             .text("Vol Supprimé avec succés")
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
                     notificationBuilder.showInformation();
                     
                     try {
                         Parent AfficherParent = FXMLLoader.load(getClass().getResource("EditFXML.fxml"));
                         Scene AfficherScene = new Scene(AfficherParent);
                         
                         
                         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                         
                         window.setScene(AfficherScene);
                         window.show();
                         window.setTitle("Modifier Vol");
                     } catch (IOException ex) {
                         Logger.getLogger(AjoutervolFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
