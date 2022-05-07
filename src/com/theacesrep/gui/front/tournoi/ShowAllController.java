package com.theacesrep.gui.front.tournoi;

import com.theacesrep.entities.Tournoi;
import com.theacesrep.services.TournoiService;
import com.theacesrep.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShowAllController implements Initializable {
    
    public static Tournoi currentTournoi;

    @FXML
    public Text topText;
    
    public VBox mainVBox;
    @FXML
    public TextField searchTF;
    

    List<Tournoi> listTournoi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listTournoi = TournoiService.getInstance().getAll();

        displayData("");
    }

    void displayData(String searchText) {
        mainVBox.getChildren().clear();

        Collections.reverse(listTournoi);

        if (!listTournoi.isEmpty()) {
            for (Tournoi tournoi : listTournoi) {
                if (tournoi.getName().toLowerCase().startsWith(searchText.toLowerCase())) {
                    mainVBox.getChildren().add(makeTournoiModel(tournoi));
                }
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeTournoiModel(
            Tournoi tournoi
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_TOURNOI)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#clientIdText")).setText("Client : " + tournoi.getClientId());
            ((Text) innerContainer.lookup("#nameText")).setText("Name : " + tournoi.getName());
            ((Text) innerContainer.lookup("#dateDebutText")).setText("DateDebut : " + tournoi.getDateDebut());
            ((Text) innerContainer.lookup("#dateFinText")).setText("DateFin : " + tournoi.getDateFin());
            ((Text) innerContainer.lookup("#descriptionText")).setText("Description : " + tournoi.getDescription());
            ((Text) innerContainer.lookup("#prixText")).setText("Prix : " + tournoi.getPrix());
            ((Text) innerContainer.lookup("#nbparticipantText")).setText("Nbparticipant : " + tournoi.getNbparticipant());
            
            Path selectedImagePath = FileSystems.getDefault().getPath(tournoi.getImage());
            if (selectedImagePath.toFile().exists()) {
                ((ImageView) innerContainer.lookup("#imageIV")).setImage(new Image(selectedImagePath.toUri().toString()));
            }
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }


    @FXML
    private void search(KeyEvent event) {
        displayData(searchTF.getText());
    }

}
