/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.gui;

import aces.entities.Article;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author aycha
 */


  
public class ItemController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Label Titre_P;
    @FXML
    private ImageView ImgView;
    
    Article article;
     public static Article article2;
    @FXML
    private Button viewmore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    
    public  void setData(Article article) {
        this.article = article;
        Titre_P.setText(article.getTitre());
       
       
        //Image image = new Image(getClass().getResourceAsStream(article.getImagearticle()));
         Image image = new Image("http://127.0.0.1:8000/aycha/uploads/images/"+article.getImagearticle());
         
        ImgView.setImage(image);
    }

    @FXML
    private void Afficher_comments(ActionEvent event) throws IOException {
        this.article2=this.article;
           System.out.println("You clicked me!");
           
        Parent modifierCours = FXMLLoader.load(getClass().getResource("CommentFront.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }
    
}
