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
import javafx.scene.control.Label;
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
public class DeletecommentController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button menuu;
    @FXML
    private TableView<Commentaire> tableview;
    @FXML
    private TableColumn<Commentaire, Integer> idcomment;
    @FXML
    private TableColumn<Commentaire, Integer> idarticle;
    @FXML
    private TableColumn<Commentaire, String> idarticle1;
  
    @FXML
    private TextField id_comment;
    @FXML
    private TextField idnew;
    @FXML
    private TextField newcontent;
    @FXML
    private Button effacer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<Commentaire> commentaire = FXCollections.observableArrayList();
          
          
          
          
                CrudCommentaires cc = new CrudCommentaires(); 
                commentaire.addAll(cc.getAll()); 
                tableview.setItems(commentaire);
                
      
              
               // idarticle.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
                idcomment.setCellValueFactory(new PropertyValueFactory<Commentaire,Integer>("id"));
                idarticle.setCellValueFactory(new PropertyValueFactory<Commentaire,Integer>("article_id"));
                idarticle1.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("contenu"));
    }    

    @FXML
    private void returnback(ActionEvent event) throws IOException {
           System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Commentback.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }

   

   

    @FXML
    private void effacercomments(ActionEvent event) throws IOException {
        
       int idcomments = Integer.parseInt(id_comment.getText()); 
        int idarticles2 = Integer.parseInt(idnew.getText()); 
        String titrecomment = newcontent.getText() ; 
        
        
        
       CrudCommentaires C = new CrudCommentaires();
       
       if (titrecomment.isEmpty()){
             JOptionPane.showMessageDialog(null, "Vous avez oublié de remplir vos champs");
       }
      
        else {
       
       
       C.delete(idcomments);
         System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Commentback.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    

       }
    }
    
    
}
