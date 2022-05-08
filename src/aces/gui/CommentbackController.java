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
import desktopfxmlpidev.DesktopFXMLPiDev;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aycha
 */
public class CommentbackController implements Initializable {
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane pane;
    @FXML
    private TableView<Commentaire> tableview;
    @FXML
    private TableColumn<Commentaire, Integer> id;
    @FXML
    private TableColumn<Commentaire, Integer> idarticle;
    @FXML
    private TableColumn<Commentaire, String> content1;
    @FXML
    private Button delete;
    @FXML
    private Button returnmenu;
    @FXML
    private Button logout;

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
                id.setCellValueFactory(new PropertyValueFactory<Commentaire,Integer>("id"));
                idarticle.setCellValueFactory(new PropertyValueFactory<Commentaire,Integer>("article_id"));
                content1.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("contenu"));
                
    }    

    @FXML
    private void deletecomments(ActionEvent event)throws IOException {
           System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Deletecomment.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }

    @FXML
    private void returnmenu(ActionEvent event) throws IOException {
           System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionBlog.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }

    @FXML
    private void LogOut(MouseEvent event) throws IOException {
        DesktopFXMLPiDev m = new DesktopFXMLPiDev();
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
}
