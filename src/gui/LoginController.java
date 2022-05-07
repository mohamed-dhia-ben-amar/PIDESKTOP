/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import desktopfxmlpidev.DesktopFXMLPiDev;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label error;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button LogInBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void checkLogin(ActionEvent event) throws IOException {
        DesktopFXMLPiDev m = new DesktopFXMLPiDev();
        if (username.getText().toString().equals("dhia") && password.getText().toString().equals("dhia")) {
            error.setText("Success!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LogIN");
            alert.setHeaderText("Results:");
            alert.setContentText("LogIN successfully!");
            alert.showAndWait();

            root = FXMLLoader.load(getClass().getResource("../gui/FrontAfficher.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (username.getText().toString().equals("dhia") && password.getText().toString().equals("back")) {
            error.setText("Success!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LogIN");
            alert.setHeaderText("Results:");
            alert.setContentText("LogIN successfully!");
            alert.showAndWait();

            root = FXMLLoader.load(getClass().getResource("../gui/AfficherReclamation.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (username.getText().toString().equals("aycha") && password.getText().toString().equals("aycha")) {
            error.setText("Success!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LogIN");
            alert.setHeaderText("Results:");
            alert.setContentText("LogIN successfully!");
            alert.showAndWait();

            root = FXMLLoader.load(getClass().getResource("../gui/HOME.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (username.getText().toString().equals("amal") && password.getText().toString().equals("amal")) {
            error.setText("Success!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LogIN");
            alert.setHeaderText("Results:");
            alert.setContentText("LogIN successfully!");
            alert.showAndWait();

            root = FXMLLoader.load(getClass().getResource("/com/theacesrep/gui/front/MainWindow.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if (username.getText().toString().equals("amal") && password.getText().toString().equals("back")) {
            error.setText("Success!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LogIN");
            alert.setHeaderText("Results:");
            alert.setContentText("LogIN successfully!");
            alert.showAndWait();

            root = FXMLLoader.load(getClass().getResource("/com/theacesrep/gui/back/MainWindow.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            error.setText("Please enter your data.");
        } else {
            error.setText("Wrong username or password!");
        }
        
    }

}
