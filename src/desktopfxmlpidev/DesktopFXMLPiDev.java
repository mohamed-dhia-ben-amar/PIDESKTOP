/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopfxmlpidev;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MSI
 */
public class DesktopFXMLPiDev extends Application {

    private static Stage stg;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));
        Scene scene2 = new Scene(root2);
        primaryStage.setTitle("The Aces");
        primaryStage.setScene(scene2);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
