/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.sponsor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ServiceSponsor;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifiersponsorbackController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private TableView<sponsor> tableview;
    @FXML
    private TableColumn<sponsor, Integer> id;
    @FXML
    private TableColumn<sponsor, Integer> tournoi_id;
    @FXML
    private TableColumn<sponsor, String> name;
    @FXML
    private TableColumn<sponsor, String> logo;
    @FXML
    private TextField idd;
    @FXML
    private Button modifier;
    @FXML
    private TextField iddtournoi;
    @FXML
    private TextField namess;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<sponsor> sponsors = FXCollections.observableArrayList();
          
          
          
                ServiceSponsor s = new ServiceSponsor();
             
                sponsors.addAll(s.getAll()) ; 
                tableview.setItems(sponsors);
                
      
              
               // idarticle.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
                id.setCellValueFactory(new PropertyValueFactory<sponsor,Integer>("id"));
                 tournoi_id.setCellValueFactory(new PropertyValueFactory<sponsor,Integer>("tournoi_id"));
                name.setCellValueFactory(new PropertyValueFactory<sponsor,String>("name"));
                  logo.setCellValueFactory(new PropertyValueFactory<sponsor,String>("logo"));
    }    

    @FXML
    private void confirmermodif(ActionEvent event) throws IOException {
        
       
        String names = namess.getText() ; 
        int idsponsor = Integer.parseInt(idd.getText()); 
         int idtournoi = Integer.parseInt(iddtournoi.getText()); 
         
         ServiceSponsor s = new ServiceSponsor();
         s.update(new sponsor(names,idtournoi,idsponsor));
      
         System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Sponsorback.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
        
    }
    
}
