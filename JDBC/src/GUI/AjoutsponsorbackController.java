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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ServiceSponsor;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjoutsponsorbackController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button back;
    @FXML
    private TextField id;
    @FXML
    private TextField tournoi_id;
    @FXML
    private TextField name;
    @FXML
    private TextField logo;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
//    @FXML
//    private void handleAjouterButtonAction(ActionEvent event)
//    {
//    
//    }
    
    
    @FXML
    private void ajoutersponsorback(ActionEvent event) throws IOException {
        if (tournoi_id.getText().length()==0)
    {
        tournoi_id.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        new animatefx.animation.Shake(tournoi_id).play();
   
    }
    else
        tournoi_id.setStyle(null);
    
       if (name.getText().length()==0)
    {
       name.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        new animatefx.animation.Shake(name).play();
   
    }
    else
        logo.setStyle(null);
          if (logo.getText().length()==0)
    {
        logo.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        new animatefx.animation.Shake(logo).play();
   
    }
    else
       logo.setStyle(null);
          
        
         int tournoi =Integer.parseInt(tournoi_id.getText());           
      
         String nom = name.getText() ;
         String log =logo.getText();
        
        
        
        sponsor s = new sponsor(tournoi,nom,log);
   
       
        ServiceSponsor s1 = new ServiceSponsor(); 
         s1.add(s);
        System.out.println("bien ajouter");
        
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Sponsorback.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
        
        
        
        
        
        
        
    }
    
     
    
    
    
}
