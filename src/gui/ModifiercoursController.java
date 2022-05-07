/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aces.gui.*;
import aces.entities.Article;
import aces.services.CrudArticles;
import aces.services.CrudCommentaires;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author aycha
 */
public class ModifiercoursController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button back;
    @FXML
    private TableView<Article> tablearticle;
    @FXML
    private TableColumn<Article, Integer> id;
    @FXML
    private TableColumn<Article, String> Titre;
    @FXML
    private TextField idmodifier;
    @FXML
    private TextField titremodifier;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)   {
            ObservableList<Article> article = FXCollections.observableArrayList();
          
          
          
          
                CrudArticles cc = new CrudArticles(); 
                article.addAll(cc.afficherArticle()) ; 
                tablearticle.setItems(article);
                
      
              
               // idarticle.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
                Titre.setCellValueFactory(new PropertyValueFactory<Article,String>("titre"));
                id.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
              
                
     }

    @FXML
    private void modifierarticle(ActionEvent event) throws IOException {
        
       
        String titre = titremodifier.getText() ; 
        int idautre = Integer.parseInt(idmodifier.getText()); 
        CrudArticles A = new CrudArticles(); 
        
        if (titre.isEmpty()){
            
              JOptionPane.showMessageDialog(null, "Vous avez oublié de mentionner le titre ");
            
        }
        else {
        
        
        
        
        
        A.ModifierArticle(new Article(idautre,titre));
         System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionBlog.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
        }
    }

    @FXML
    private void backmodifier(ActionEvent event) throws IOException {
           System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionBlog.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }

  
}

    

