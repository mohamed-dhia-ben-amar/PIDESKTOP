/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aces.gui.*;
import aces.entities.Article;
import aces.services.CrudArticles;
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
public class DeleteArticleController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button back;
    @FXML
    private TableColumn<Article, Integer> idarticle;
    @FXML
    private TableColumn<Article, String> titre;
    @FXML
    private TableView<Article> tableview;
    @FXML
    private TextField id;
    @FXML
    private Button supprimer;
    @FXML
    private TextField autretitre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<Article> article = FXCollections.observableArrayList();
          
          
          
          
                CrudArticles cc = new CrudArticles(); 
                article.addAll(cc.afficherArticle()) ; 
                tableview.setItems(article);
                
      
              
               // idarticle.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
                titre.setCellValueFactory(new PropertyValueFactory<Article,String>("titre"));
                idarticle.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
              
                
     }

    @FXML
    private void Supprimer_article(ActionEvent event) throws IOException {
        
        
        int idarticles = Integer.parseInt(id.getText()); 
        String titrearticle = autretitre.getText() ; 
        
        
        
       CrudArticles A = new CrudArticles(); 
       
       
        int response = JOptionPane.showConfirmDialog(null, "do you want to delete your article ?", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) ; 
        if (response == JOptionPane.YES_OPTION){
        A.SupprimerArticle(idarticles);
         System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionBlog.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
        }
        else {
             Parent modifierCours = FXMLLoader.load(getClass().getResource("DeleteArticle.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
        }
    }

    @FXML
    private void Click(MouseEvent event) {
                Object selectedItem = tableview.getSelectionModel().getSelectedItem();
         CrudArticles A = new CrudArticles();   
         
         int i =0;
         List article = A.afficherArticle();
    
        
 
        
   while (i!=article.size()){
     
        
          
         if(article.get(i).toString().equals(selectedItem.toString())) {
            
             
             System.out.println(selectedItem.toString());          
             System.out.println(article.get(i).toString());            
             System.out.println(selectedItem.getClass());


              Article B = (Article) article.get(i);
              id.setText(String.valueOf(B.getId()));
              autretitre.setText(String.valueOf(B.getTitre())); 
            break;
             
            } else {
             
             i++;

            }
          }
    }

    @FXML
    private void backfront(ActionEvent event) throws IOException {
           System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionBlog.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }
    
    }    
    

