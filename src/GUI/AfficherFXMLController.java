/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import edu.esprit.entities.Vol;
import edu.esprit.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AfficherFXMLController implements Initializable {

    private Connection con = MyConnection.getInstance().getCnx();
    
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
    private PieChart voldistribution;

   

    
    
    /**
     * Initializes the controller class.
     */
    @Override
            public void initialize(URL url, ResourceBundle rb) {
       ObservableList<PieChart.Data>data = FXCollections.observableArrayList();
                
                try {
          
            String query = "SELECT * FROM vol";
            PreparedStatement ste = new MyConnection().getCnx().prepareStatement(query);
            ResultSet rs = ste.executeQuery(query);
            
            
            while (rs.next()){
                try {

                    Vollist.add(new  Vol(rs.getInt("id"),rs.getString("datedepart"), rs.getString("datearrive"), rs.getString("destination"),rs.getInt("nbrplace"), rs.getString("heuredepart"),rs.getString("heurearrive"),rs.getString("nom")));
                    data.add(new PieChart.Data(rs.getString("nom"),rs.getInt("nbrplace")));

           
            tbdestination.setCellValueFactory(new PropertyValueFactory<Vol,String>("destination"));
            tbplace.setCellValueFactory(new PropertyValueFactory<Vol,String>("nbrplace"));
            tbdd.setCellValueFactory(new PropertyValueFactory<Vol,String>("datedepart"));
            tbhd.setCellValueFactory(new PropertyValueFactory<Vol,String>("heuredepart"));
            tbda.setCellValueFactory(new PropertyValueFactory<Vol,String>("datearrive"));
            tbha.setCellValueFactory(new PropertyValueFactory<Vol,String>("heurearrive"));
            tbnomvol.setCellValueFactory(new PropertyValueFactory<Vol,String>("nom"));
           
                    table.setItems(Vollist);
                    
           
                    
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        voldistribution.setData(data);
        
        
            }

    /*@FXML
    private void ontableitemselect(MouseEvent event) {
    }*/

    @FXML
       private void menuprincipal(ActionEvent event)
    {
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
  
    window.close();    
    }

    @FXML
    private void print(ActionEvent event) {
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/3a8","root","");
           
       JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Achraf\\Documents\\NetBeansProjects\\final\\src\\GUI\\pdf\\report1.jrxml");
        String query = "SELECT\n" +
"     vol.`datedepart` AS vol_datedepart,\n" +
"     vol.`datearrive` AS vol_datearrive,\n" +
"     vol.`destination` AS vol_destination,\n" +
"     vol.`nbrplace` AS vol_place,\n" +
"     vol.`heuredepart` AS vol_HeureDepart,\n" +
"     vol.`heurearrive` AS vol_Heurearrive,\n" +
"     vol.`id` AS vol_code\n" +
"FROM\n" +
"     `vol` vol";
      
        
        JRDesignQuery updateQuery = new JRDesignQuery();
        
        updateQuery.setText(query);
        
        jdesign.setQuery(updateQuery);
        
        JasperReport jreport = JasperCompileManager.compileReport(jdesign);
        JasperPrint jprint = JasperFillManager.fillReport(jreport,null,con);
        JasperViewer.viewReport(jprint);
        
        
        
        
        } catch (ClassNotFoundException | SQLException | JRException ex) {
            Logger.getLogger(AfficherFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
        
        
        
        
        
       
        
        
        
    }
     
}
