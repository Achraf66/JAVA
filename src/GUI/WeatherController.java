/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class WeatherController implements Initializable {

    @FXML
    private Text weatherText;
    @FXML
    private TextField cityInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



    private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";

    

    public String getWoeid() throws MalformedURLException {
        APIConnector apiConnectorCity = new APIConnector(cityAPI);

        JSONObject jsonData = (JSONObject) (apiConnectorCity.getJSONArray(cityInput.getText())).get(0);

        return jsonData.get("woeid").toString();
    }

    public JSONObject GetTodaysWeatherInformation(String woeid) throws MalformedURLException {
        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid + "/");

        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidated_weather");

        return  (JSONObject) weatherArray.get(0);
    }

    @FXML
    private void getweather(ActionEvent event) throws MalformedURLException{
        
        
        
        JSONObject todaysWeather = GetTodaysWeatherInformation(getWoeid());

        /*System.out.println(todaysWeather);*/

        weatherText.setText(
            "Min temperature: " + todaysWeather.get("min_temp") +
            "\nCurrent temperature: " + todaysWeather.get("the_temp") +
            "\nMax temperature: " + todaysWeather.get("max_temp")
                
        );
    }

    @FXML
    private void quitter(ActionEvent event) {
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
  
    window.close();
    }
}

        
        
   
        
        
        
        
        
        
    















    
    

