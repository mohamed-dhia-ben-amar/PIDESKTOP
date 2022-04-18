/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aycha
 */
public class HOMEController  {

    @FXML
    private AnchorPane voir;
    @FXML
    private Pane sidemenu;
    @FXML
    private Button element;
    @FXML
    private Button blog;
    @FXML
    private Button tournoi;
    @FXML
    private Button complaints1;
    @FXML
    private Button livraison;
    @FXML
    private ImageView imageoffer;
    @FXML
    private ImageView elementicone;
    @FXML
    private ImageView blogicone;
    @FXML
    private ImageView imagetournoi;
    @FXML
    private ImageView adminimage;
    @FXML
    private ImageView imagecomplaint;

    @FXML
    private void GestionBlog(ActionEvent event) throws IOException {
        
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionFront.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }

    @FXML
    private void Backblog(ActionEvent event) throws IOException {
        
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionBlog.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }
  
    
      
}
