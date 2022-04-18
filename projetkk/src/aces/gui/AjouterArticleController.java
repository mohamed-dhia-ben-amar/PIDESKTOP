/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.gui;

import aces.entities.Article;
import aces.services.CrudArticles;
import java.awt.Desktop;
import java.io.File;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author aycha
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private Pane idajouter;
    @FXML
    private Button back;
    @FXML
    private TextField titre;
    @FXML
    private TextField contenu;
    @FXML
    private Button ajouter;
    @FXML
    private TextField image;
    @FXML
    private TextField date;
    @FXML
    private Button choice;
       private FileChooser filechooser;
    private File file;
    private final Desktop desktop = Desktop.getDesktop();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
    }    

    @FXML
    private void ajouterarticle(ActionEvent event) throws IOException {
         String titre2 = titre.getText() ;
        String contenu2 =contenu.getText();
        
         String image2 =image.getText();
         String date =java.time.LocalDate.now().toString();
        
   
        Article A = new Article(titre2,contenu2,image2,date); 
        CrudArticles B = new CrudArticles(); 
        
        if (titre2.isEmpty()||contenu2.isEmpty()||image2.isEmpty()){   //controles de saisie non vide 
            
             JOptionPane.showMessageDialog(null, "Vous avez oublié de remplir vos champs");
            
            
        }
        
        else {
        
        
          int response = JOptionPane.showConfirmDialog(null, "do you want to create an article ?", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) ; 
        
        
           
        
         if (response == JOptionPane.YES_OPTION){
        B.ajouterarticle(A);
       
        
        
        System.out.println("bien ajouter");
        
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionBlog.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
         }
         else {
                   
        Parent modifierCours = FXMLLoader.load(getClass().getResource("AjouterArticle.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
         }
    }
    }

    @FXML
    private void returnback(ActionEvent event) throws IOException {
           System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionBlog.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }

    @FXML
    private void choiceimage(ActionEvent event) {
      
        
       Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("selectionner une image !"); 
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        choice.setOnAction(e -> {
            file = filechooser.showOpenDialog(primaryStage);
       if (file != null) {
                //String s = file.getAbsolutePath(); 
                String F = file.toURI().toString();
                image.setText(F);
                image.setText(F.replace("file:/C:/xampp/htdocs/integration%20(2)/integration/public/aycha/uploads/images/",""));
                
                //     image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
                //     img1.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });
        
        
        
        
        
    }

}
