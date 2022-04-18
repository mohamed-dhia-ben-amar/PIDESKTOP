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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.tournoiCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class BacktournoiController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Label labelt;
    @FXML
    private Label labelss;
    @FXML
    private Button afficher;
    @FXML
    private TableView<tournoi> tableview;
    @FXML
    private TableColumn<tournoi, Integer> id;
    @FXML
    private TableColumn<tournoi, String> date_debut;
    @FXML
    private TableColumn<tournoi, String> date_fin;
    @FXML
    private TableColumn<tournoi,Float> prix;
    @FXML
    private TableColumn<tournoi, Integer> participant;
    @FXML
    private TableColumn<tournoi, String> nom;
    @FXML
    private TableColumn<tournoi, String> description;
   

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
    private void ajoutertournoiback(ActionEvent event)  throws IOException {
        
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Ajouttournoiback.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }

    @FXML
    private void modifiertournoiback(ActionEvent event) throws IOException {
        
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Modifiertournoisback.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }

    @FXML
    private void supprimertournoiback(ActionEvent event) throws IOException {
        
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Supprimertournoiback.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }

    @FXML
    private void affichersponsorback(ActionEvent event) throws IOException {
        
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Sponsorback.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }
    
}
