/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.gui;

import aces.entities.Article;
import aces.entities.Commentaire;
import aces.services.CrudArticles;
import aces.services.CrudCommentaires;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aycha
 */
public class CommentFrontController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane imagearticle;
    @FXML
    private Button home;
    @FXML
    private ImageView immage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         CrudArticles cc = new CrudArticles(); 
         List <Article> mylist =  new ArrayList<>();
         
        mylist=cc.afficherArticles(ItemController.article2.getId());
        
        CrudCommentaires c = new CrudCommentaires();
        List<Commentaire> listc=new ArrayList<>();
       listc=c.affichercommentaires(ItemController.article2.getId());
       
       
   
         
        int column = 0;
        int row = 1;
        try {
           for (int i=0;i<mylist.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Itemcomment.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemcommentController itemController = fxmlLoader.getController();
                itemController.setData(mylist.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                imagearticle.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                 imagearticle.setMinWidth(Region.USE_COMPUTED_SIZE);
                 imagearticle.setPrefWidth(Region.USE_COMPUTED_SIZE);
                 imagearticle.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                 imagearticle.setMinHeight(Region.USE_COMPUTED_SIZE);
                 imagearticle.setPrefHeight(Region.USE_COMPUTED_SIZE);
                 imagearticle.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
              
              
              
              
          }

    @FXML
    private void home(ActionEvent event) throws IOException {
        
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionFront.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }
}
