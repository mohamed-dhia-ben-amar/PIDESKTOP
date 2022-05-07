/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aces.gui.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    private Label comments;
    Article article;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxa;
    @FXML
    private Label title;
    @FXML
    private Label content;
    @FXML
    private Label title1;
    @FXML
    private TextField leave;
    @FXML
    private Button add;

    int id = 0;
    @FXML
    private Button s;
    @FXML
    private Button e;
    @FXML
    private TableView<Commentaire> table;
    @FXML
    private TableColumn<Commentaire, String> commentaires;

    int idcommentaires;
    @FXML
    private HBox test;
    @FXML
    private ImageView delete;
    @FXML
    private ImageView edit;
    @FXML
    private ImageView tessssttt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setData(Article article) {
        id = article.getId();
        ObservableList<Commentaire> commentaire = FXCollections.observableArrayList();

        CrudCommentaires CR = new CrudCommentaires();
        commentaire.addAll(CR.affichercommentaires(id));
        table.setItems(commentaire);

        // idarticle.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
        commentaires.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("contenu"));

        //Image image = new Image(getClass().getResourceAsStream(article.getImagearticle()));
        Image image2 = new Image("http://127.0.0.1:8000/aycha/uploads/images/" + article.getImagearticle());

        image.setImage(image2);

        this.article = article;
        Titre.setText(article.getTitre());
        contenu.setText(article.getContenu());

    }

    @FXML
    private void Clicked(MouseEvent event) {
//        Object selectedItem = table.getSelectionModel().getSelectedItem();
//        CrudCommentaires CR = new CrudCommentaires();
//
//        int i = 0;
//        List commentaire = CR.affichercommentaires(id);
//
//        while (i != commentaire.size()) {
//
//            if (commentaire.get(i).toString().equals(selectedItem.toString())) {
//
//                System.out.println(selectedItem.toString());
//                System.out.println(commentaire.get(i).toString());
//                System.out.println(selectedItem.getClass());
//
//                Commentaire C = (Commentaire) commentaire.get(i);
//                // id.setText(String.valueOf(C.getId()));
//                idcommentaires = C.getId();
//                leave.setText(String.valueOf(C.getContenu()));
//                break;
//
//            } else {
//
//                i++;
//
//            }
//        }

    }

    @FXML
    private void addpersonnel(MouseEvent event) throws IOException {
        String contenu2 = leave.getText();

        System.out.println(contenu2);

        String output = badWordsFilter.getCensoredText(contenu2);

        System.out.println(output);
        System.out.println("aaa");

        CrudCommentaires C = new CrudCommentaires();
        Commentaire CR = new Commentaire(id, output);

        C.add(CR);

        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("CommentFront.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }

    @FXML
    private void supprimer(MouseEvent event) throws IOException {
        //  int idarticles = Integer.parseInt(id.getText()); 
        String titrearticle = leave.getText();

        CrudCommentaires C = new CrudCommentaires();

        int response = JOptionPane.showConfirmDialog(null, "do you want to delete your comment ?", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            C.delete(idcommentaires);
            System.out.println("You clicked me!");
            Parent modifierCours = FXMLLoader.load(getClass().getResource("CommentFront.fxml"));
            Scene modifierCours_scene = new Scene(modifierCours);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(modifierCours_scene);
            app_stage.show();
        } else {
            Parent modifierCours = FXMLLoader.load(getClass().getResource("CommentFront.fxml"));
            Scene modifierCours_scene = new Scene(modifierCours);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(modifierCours_scene);
            app_stage.show();
        }
    }

    @FXML
    private void edit(MouseEvent event) throws IOException {
        String titrearticle = leave.getText();

        String output = badWordsFilter.getCensoredText(titrearticle);

        CrudCommentaires C = new CrudCommentaires();
        Commentaire CX = new Commentaire(idcommentaires, id, output);

        int response = JOptionPane.showConfirmDialog(null, "do you want to update your comment ?", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            C.update(CX);
            System.out.println("You clicked me!");
            Parent modifierCours = FXMLLoader.load(getClass().getResource("CommentFront.fxml"));
            Scene modifierCours_scene = new Scene(modifierCours);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(modifierCours_scene);
            app_stage.show();
        } else {
            Parent modifierCours = FXMLLoader.load(getClass().getResource("CommentFront.fxml"));
            Scene modifierCours_scene = new Scene(modifierCours);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(modifierCours_scene);
            app_stage.show();
        }
    }

}
