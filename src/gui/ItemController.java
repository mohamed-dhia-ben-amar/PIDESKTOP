/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aces.gui.*;
import aces.entities.Article;
import com.itextpdf.text.DocumentException;
import static com.itextpdf.text.pdf.BidiOrder.PDF;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aycha
 */
public class ItemController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Label Titre_P;
    @FXML
    private ImageView ImgView;

    public static int cid;
    Article article;
    public static Article article2;
    @FXML
    private Button viewmore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    public void setData(Article article) {
        this.article = article;
        Titre_P.setText(article.getTitre());

        //Image image = new Image(getClass().getResourceAsStream(article.getImagearticle()));
        Image image = new Image("http://127.0.0.1:8000/aycha/uploads/images/" + article.getImagearticle());

        ImgView.setImage(image);
    }

    @FXML
    private void Afficher_comments(ActionEvent event) throws IOException {
        this.article2 = this.article;
        cid = article.getId();

        System.out.println("You clicked me!" + cid);

        Parent modifierCours = FXMLLoader.load(getClass().getResource("CommentFront.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }

    private void telecharger(ActionEvent event) {
        cid = article.getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez vous exporter la liste des Reservation en un fichier PDF ?");

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
