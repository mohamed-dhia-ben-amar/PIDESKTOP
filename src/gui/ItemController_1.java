/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Livreur;
import services.ServiceLivreur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Test.MyListener;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ItemController_1 implements Initializable {

    private Label priceLable;
    @FXML
    private ImageView img;
    private Livreur livreur;
    private MyListener myListener;
    @FXML
    private AnchorPane parent;
    @FXML
    private Label zone;
    @FXML
    private Label prenom;
    @FXML
    private Rating rate;
    ServiceLivreur livS = new ServiceLivreur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rate.setPartialRating(true);
        //
        System.out.println(livreur);
    }

    @FXML
    private void click(MouseEvent event) {
    }

    public void setData(Livreur livreur, MyListener myListener) {

        String path = "/img/" + livreur.getImage();
        Image image = new Image(getClass().getResourceAsStream(path));
        this.myListener = myListener;
        zone.setText(livreur.getZone());
        prenom.setText(livreur.getPrenom());
        img.setImage(image);
        this.livreur = livreur;
        rate.setRating(livreur.getRating());
        //Image image = new Image(getClass().getResourceAsStream(plat.getImg()));
        //img.setImage(image);

    }

    @FXML
    private void details(MouseEvent event) {
        myListener.onClickListener(livreur);
    }

    @FXML
    private void addR(MouseEvent event) {

        livS.updateLivreur(livreur.getCin(), (float) rate.getRating());
    }

    public float sr() {
        return (float) rate.getRating();
    }
}
