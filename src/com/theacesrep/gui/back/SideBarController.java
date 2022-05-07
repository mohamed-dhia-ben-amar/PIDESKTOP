package com.theacesrep.gui.back;

import com.theacesrep.MainApp;
import com.theacesrep.utils.Animations;
import com.theacesrep.utils.Constants;
import desktopfxmlpidev.DesktopFXMLPiDev;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SideBarController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private final Color COLOR_GRAY = new Color(0.9, 0.9, 0.9, 1);
    private final Color COLOR_PRIMARY = Color.web("#053F6E");
    private final Color COLOR_DARK = new Color(0.9, 0.9, 0.9, 1);
    private Button[] liens;

    @FXML
    private Button btnSponsors;
    @FXML
    private Button btnTournois;
    @FXML
    private AnchorPane mainComponent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        liens = new Button[]{
            btnTournois,
            btnSponsors,};

        mainComponent.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));

        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            lien.setBackground(new Background(new BackgroundFill(COLOR_PRIMARY, CornerRadii.EMPTY, Insets.EMPTY)));
            Animations.animateButton(lien, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
        }
        btnSponsors.setTextFill(Color.WHITE);
        btnTournois.setTextFill(Color.WHITE);

    }

    @FXML
    private void afficherSponsors(ActionEvent event) {
        goToLink(Constants.FXML_BACK_DISPLAY_ALL_SPONSOR);

        btnSponsors.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnSponsors, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    @FXML
    private void afficherTournois(ActionEvent event) {
        goToLink(Constants.FXML_BACK_DISPLAY_ALL_TOURNOI);

        btnTournois.setTextFill(COLOR_PRIMARY);
        Animations.animateButton(btnTournois, COLOR_GRAY, Color.WHITE, COLOR_PRIMARY, 0, false);
    }

    private void goToLink(String link) {
        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            Animations.animateButton(lien, COLOR_GRAY, COLOR_DARK, COLOR_PRIMARY, 0, false);
        }
        MainWindowController.getInstance().loadInterface(link);
    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        DesktopFXMLPiDev m = new DesktopFXMLPiDev();
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
