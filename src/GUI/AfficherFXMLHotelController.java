/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUII;

import edu.esprit.entities.Hotel;
import edu.esprit.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mootaz
 */
public class AfficherFXMLHotelController implements Initializable {

    private final Connection con = MyConnection.getInstance().getCnx();

    @FXML
    private TableView<Hotel> table;
    @FXML
    private TableColumn<Hotel,String> tbendroit;
    @FXML
    private TableColumn<Hotel,String> tbnomhotel;
    @FXML
    private TableColumn<Hotel,String> tbdatereservation;
    @FXML
    private TableColumn<Hotel,String> tbcheckin;
    @FXML
    private TableColumn<Hotel,String> tbcheckout;
    @FXML
    private PieChart hoteldistribution;
    
    ObservableList <Hotel> Hotellist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
            public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ObservableList<PieChart.Data>data = FXCollections.observableArrayList();
            
            
            
            String query = "SELECT * FROM hotel";
            PreparedStatement ste = new MyConnection().getCnx().prepareStatement(query);
            ResultSet rs = ste.executeQuery(query);
            
            
            while (rs.next()){
                
                Hotellist.add(new  Hotel(rs.getInt("code"),rs.getString("dateReservation"), rs.getString("checkin"), rs.getString("endroit"),rs.getString("nomhotel"), rs.getString("checkout")));
                data.add(new PieChart.Data(rs.getString("endroit"),rs.getInt("code")));
                
                
                tbendroit.setCellValueFactory(new PropertyValueFactory<Hotel,String>("endroit"));
                tbnomhotel.setCellValueFactory(new PropertyValueFactory<Hotel,String>("nomhotel"));
                tbcheckin.setCellValueFactory(new PropertyValueFactory<Hotel,String>("checkin"));
                tbcheckout.setCellValueFactory(new PropertyValueFactory<Hotel,String>("checkout"));
                tbdatereservation.setCellValueFactory(new PropertyValueFactory<Hotel,String>("dateReservation"));
                
                
                
                table.setItems(Hotellist);
                
                
                hoteldistribution.setData(data);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFXMLHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
            @FXML
            private void menuprincipal(ActionEvent event)
            {
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.close();
                
                
            }   
     
}
