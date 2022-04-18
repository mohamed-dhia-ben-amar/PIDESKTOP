/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.gui;

import aces.entities.Article;
import aces.entities.Commentaire;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author aycha
 */
public class ItemcommentController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private ImageView image;
    @FXML
    private Label Titre;
    @FXML
    private Label contenu;
    @FXML
    private Label comments;
     Article article;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxa;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     public  void setData(Article article,List<Commentaire>lc) {
       
       
        //Image image = new Image(getClass().getResourceAsStream(article.getImagearticle()));
         Image image2 = new Image("http://127.0.0.1:8000/aycha/uploads/images/"+article.getImagearticle());
         
        image.setImage(image2);
        
        
        this.article = article;
        Titre.setText(article.getTitre());
        contenu.setText(article.getContenu());
       
         for (int i=0;i<lc.size();i++){
             comments.setText(comments.getText()+"\n"+lc.get(i).getContenu());
             
         
        
    }
     }

    
    
    
}
