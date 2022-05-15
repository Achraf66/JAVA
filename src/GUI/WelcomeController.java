/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class WelcomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hotel(ActionEvent event) /*throws IOException*/  {
        
        
       try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLHotelmenu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root1));
            stage.show();
       }
       catch(IOException e)
       {
           e.printStackTrace();
       }
        
        
    }

    @FXML
    private void vol(ActionEvent event) 
    {
              
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLmenu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

      

    

    @FXML
    private void quitter(ActionEvent event) {
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
  
    window.close();
    }
}
