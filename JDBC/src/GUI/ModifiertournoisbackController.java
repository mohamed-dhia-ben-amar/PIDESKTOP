/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.tournoi;
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
import services.tournoiCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifiertournoisbackController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private TableView<tournoi> tableview;
    @FXML
    private TableColumn<tournoi, Integer> id;
    @FXML
    private TableColumn<tournoi, String> nom;
    @FXML
    private TableColumn<tournoi, String> date_debut;
    @FXML
    private TableColumn<tournoi, String> date_fin;
    @FXML
    private TableColumn<tournoi, String> description;
    @FXML
    private TableColumn<tournoi, Integer> participant;
    @FXML
    private TableColumn<tournoi, Float> prix;
    @FXML
    private Button modifier;
    @FXML
    private TextField idtextfield;
    @FXML
    private TextField nametext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<tournoi> tournois = FXCollections.observableArrayList();
          
          
          
          
                tournoiCrud t = new tournoiCrud(); 
                tournois.addAll(t.afficherTournoi()) ; 
                tableview.setItems(tournois);
                
      
              
               // idarticle.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
                nom.setCellValueFactory(new PropertyValueFactory<tournoi,String>("name"));
                date_debut.setCellValueFactory(new PropertyValueFactory<tournoi,String>("date_debut"));
                date_fin.setCellValueFactory(new PropertyValueFactory<tournoi,String>("date_fin"));
                  description.setCellValueFactory(new PropertyValueFactory<tournoi,String>("Description"));
                  prix.setCellValueFactory(new PropertyValueFactory<tournoi,Float>("prix"));
                    participant.setCellValueFactory(new PropertyValueFactory<tournoi,Integer>("nbparticipant"));
    }    

    @FXML
    private void confirmermodifier(ActionEvent event) throws IOException {
        
       
        String nom = nametext.getText() ; 
        int idautre = Integer.parseInt(idtextfield.getText()); 
        tournoiCrud t = new tournoiCrud(); 
        t.Modifiertournoi(new tournoi(idautre,nom));
     
         System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Backtournoi.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
        
    }
    
}
