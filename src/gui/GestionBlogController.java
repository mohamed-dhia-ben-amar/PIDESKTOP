/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aces.gui.*;
import aces.entities.Article;
import aces.services.CrudArticles;
import aces.utiles.My_Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author aycha
 */
public class GestionBlogController implements Initializable {

    @FXML
    private Pane color;
    @FXML
    private Button front;
    @FXML
    private AnchorPane tester;
    @FXML
    private TableView<Article> view;
    @FXML
    private TableColumn<Article, String> titre;
    @FXML
    private TableColumn<Article, String> contenu;
    @FXML
    private TableColumn<Article, String> date;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button comments;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Article> article = FXCollections.observableArrayList();

        CrudArticles cc = new CrudArticles();
        article.addAll(cc.afficherArticle());
        view.setItems(article);

        // idarticle.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<Article, String>("titre"));
        contenu.setCellValueFactory(new PropertyValueFactory<Article, String>("contenu"));
        date.setCellValueFactory(new PropertyValueFactory<Article, String>("dateCreation"));

    }

    @FXML
    private void afficherfront(ActionEvent event) throws IOException {

        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }

    @FXML
    private void supprimerarticle(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("DeleteArticle.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }

    @FXML
    private void modifierarticle(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Modifiercours.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }

    @FXML
    private void ajouterarticle(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("AjouterArticle.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }

    @FXML
    private void commentsback(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Commentback.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }

}

//     private final  ObservableList<ObservableList>  data = FXCollections.observableArrayList();
//
//        
//         public void initialize(URL url, ResourceBundle rb) {
//     
//        
//        int compteur = 1;
//        for (int i = 0; i < view.getItems().size(); i++) {
//            view.getItems().clear();
//        }
//        
//        
//        
//       
//       
//        try {
//             My_Connexion con = new My_Connexion()  ;
//             PreparedStatement state =con.getCnx().prepareStatement("SELECT * FROM Article");
//             ResultSet rs=state.executeQuery();
//         
//
//            /**
//             * ******************************
//             * TABLE COLUMN ADDED DYNAMICALLY *
//             *******************************
//             */
//            if (compteur == 1) {
//                compteur++;
//                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                    //We are using non property style for making dynamic table
//                    final int j = i;
//                    if (i != 3) {
//                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//                            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
//                                return new SimpleStringProperty(param.getValue().get(j).toString());
//                            }
//                        });
//
//                        view.getColumns().addAll(col);
//                        //System.out.println("Column ["+i+"] ");
//                    }
//                }
//            }
//
//            /**
//             * ****************************
//             * Data added to ObservableList *
//             *****************************
//             */
//            while (rs.next()) {
//                //Iterate Row
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                    //Iterate Column
//                    row.add(rs.getString(i));
//                }
//                // System.out.println("Row [1] added "+row );
//                data.add(row);
//
//         
//            }
//
//            //FINALLY ADDED TO TableView
//            view.setItems(data);
//            
//            
//                    
//        
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
//         }
