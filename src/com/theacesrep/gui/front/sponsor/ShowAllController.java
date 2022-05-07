package com.theacesrep.gui.front.sponsor;

import com.theacesrep.entities.Sponsor;
import com.theacesrep.services.SponsorService;
import com.theacesrep.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    public static Sponsor currentSponsor;

    @FXML
    public Text topText;
    
    public VBox mainVBox;
    

    List<Sponsor> listSponsor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listSponsor = SponsorService.getInstance().getAll();
        
        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();
        
        Collections.reverse(listSponsor);

        if (!listSponsor.isEmpty()) {
            for (Sponsor sponsor : listSponsor) {
                
                mainVBox.getChildren().add(makeSponsorModel(sponsor));
                
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeSponsorModel(
            Sponsor sponsor
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_SPONSOR)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#tournoiIdText")).setText("Tournoi : " + sponsor.getTournoiId());
            ((Text) innerContainer.lookup("#nameText")).setText("Name : " + sponsor.getName());
            
            Path selectedImagePath = FileSystems.getDefault().getPath(sponsor.getLogo());
            if (selectedImagePath.toFile().exists()) {
                ((ImageView) innerContainer.lookup("#imageIV")).setImage(new Image(selectedImagePath.toUri().toString()));
            }
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    
    
    private void specialAction(Sponsor sponsor) {
        
    }
}
