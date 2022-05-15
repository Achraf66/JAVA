/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author mootaz
 */
public class FXMLHotelmenuController implements Initializable {

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
    private void ajouter(ActionEvent event) {
        
                  
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjouterhotelFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLHotelmenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
    }


    @FXML
    private void afficher(ActionEvent event) {
        
          try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficherFXMLHotel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLHotelmenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("EditFXMLHotel.fxml"));
            Parent root1 = (Parent) fxmlLoader1.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLHotelmenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }

    @FXML
    private void Quitter(ActionEvent event) {
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
  
    window.close();}}
    
    