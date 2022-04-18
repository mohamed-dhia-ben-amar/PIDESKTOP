/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouttournoibackController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button back;
    @FXML
    private TextField name;
    @FXML
    private TextField date_debut;
    @FXML
    private TextField date_fin;
    @FXML
    private TextField description;
    @FXML
    private TextField client_id;
    @FXML
    private TextField prix;
    @FXML
    private TextField nbrparticipant;
    @FXML
    private TextField image;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
