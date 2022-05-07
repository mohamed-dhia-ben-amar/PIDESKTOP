/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aces.gui.*;
import aces.entities.Article;
import static aces.gui.ItemController.cid;
import aces.services.CrudArticles;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aycha
 */
public class GestionFrontController implements Initializable {

    private ImageView frontimage;

    @FXML
    private AnchorPane affichertexte;
    private Text text;
    @FXML
    private ScrollPane Scrollpane;
    @FXML
    private GridPane gridpane;
    @FXML
    private Button home;
    @FXML
    private ImageView homeid;
    @FXML
    private TextField rechercher;
      Article article;
     public static Article article2;

    /**
     * Initializes the controller class.
     */
    private   ObservableList<ObservableList>  data = FXCollections.observableArrayList();
    @FXML
    private Button tele;
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        
       
        
         CrudArticles cc = new CrudArticles(); 
         List <Article> mylist =  new ArrayList<>();
         
        mylist=cc.afficherArticle();
        
        
        
         
        int column = 0;
        int row = 1;
        try {
           for (int i=0;i<mylist.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(mylist.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridpane.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                gridpane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridpane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
              
              
              
              
          }
                    
          
     

    @FXML
    private void Homereturn(ActionEvent event) throws IOException {
        
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
    }

    @FXML
    private void searchtest(ActionEvent event) {
         
    }

    @FXML
    private void search(KeyEvent event) {
         System.out.println("test");
         gridpane.getChildren().clear();
         
          CrudArticles cc = new CrudArticles(); 
         List <Article> mylist =  new ArrayList<>();
         
        mylist=cc.afficherArticlesearch(rechercher.getText());
        
        
        
         
        int column = 0;
        int row = 1;
        try {
           for (int i=0;i<mylist.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(mylist.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridpane.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                gridpane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridpane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void telecharger(ActionEvent event) {
  
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez vous exporter la liste des articles en un fichier PDF ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                pdf pdff = new pdf();
                try {
                    pdff.liste_Article();
                } catch (DocumentException ex) {
                    System.out.println("Controller.GestionGuideController.DownloadPDF()" + ex.getMessage());
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Controller.GestionGuideController.DownloadPDF()" + ex.getMessage());
            }
        
        
        
        }
    
        
    }
     
    }   
    

