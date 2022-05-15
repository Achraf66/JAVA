/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class FXMLmenuController implements Initializable {

    @FXML
    private Text textdollar;
    @FXML
    private TextField money;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
                  
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutervolFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLmenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  
    }


    @FXML
    private void afficher(ActionEvent event) {
        
          try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficherFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLmenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        
              try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditFXML.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLmenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @FXML
    private void Quitter(ActionEvent event) {
             Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
  
    window.close();
    }

    @FXML
    private void weather(ActionEvent event) {
        
        
                      try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Weather.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLmenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }

    @FXML
    private void refresh(ActionEvent event) throws MalformedURLException, IOException 
    {
        
        
        String url_str = "https://v6.exchangerate-api.com/v6/487eacc421acbfb57e92cfbf/latest/USD";


URL url = new URL(url_str);
HttpURLConnection request = (HttpURLConnection) url.openConnection();
request.connect();


JsonParser jp = new JsonParser();
JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
JsonObject jsonobj = root.getAsJsonObject();



  
        JsonObject rates = jsonobj.get("conversion_rates").getAsJsonObject();
        System.out.println(rates);

        
       
          textdollar.setText(
            "Dollar: "+rates.get("USD")+"\n"+"EURO: "+ rates.get("EUR")+"\n"+"Egyptian pound: " + rates.get("EGP")+"\n"+"Dinar tunisian: "+rates.get("TND")+"\n");
             
            
                
               
        
    }

   
}
