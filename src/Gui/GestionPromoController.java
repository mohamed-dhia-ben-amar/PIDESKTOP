/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entity.Element;
import Entity.Promotion;
import Services.ElementService;
import Services.PromotionService;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class GestionPromoController implements Initializable {

    @FXML
    private Pane color;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button Home;
    @FXML
    private TableView<Promotion> table;
    @FXML
    private TableColumn<Promotion, String> date_debut;
    @FXML
    private TableColumn<Promotion, Float> Pourcentage;
    @FXML
    private TableColumn<Promotion, String>  Date_Fin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Promotion> promo = FXCollections.observableArrayList();
          
          
          
          
                PromotionService cc = new PromotionService(); 
                promo.addAll(cc.afficher()) ;
                
                table.setItems(promo);
                
      
              
               // idarticle.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
                date_debut.setCellValueFactory(new PropertyValueFactory<Promotion, String> ("date_deb"));
                Pourcentage.setCellValueFactory(new PropertyValueFactory<Promotion, Float> ("pourcentage"));
                Date_Fin.setCellValueFactory(new PropertyValueFactory<Promotion, String> ("date_fin"));

                // TODO
    }    

    @FXML
    private void ajouterPromo(ActionEvent event) throws IOException {
         System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("AjouterPromo.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
    }

    @FXML
    private void modifierPomo(ActionEvent event) throws IOException {
         System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("ModifierPromo.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
    }

    @FXML
    private void supprimerPromo(ActionEvent event) throws IOException {
    System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("SupprimerPromo.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show();     }

    @FXML
    private void GoHome(ActionEvent event) throws IOException {
         System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
    }
    
}
