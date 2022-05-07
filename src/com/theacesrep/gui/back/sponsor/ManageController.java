package com.theacesrep.gui.back.sponsor;

import com.theacesrep.MainApp;
import com.theacesrep.entities.Sponsor;
import com.theacesrep.gui.back.MainWindowController;
import com.theacesrep.services.SponsorService;
import com.theacesrep.utils.AlertUtils;
import com.theacesrep.utils.Constants;
import com.theacesrep.utils.RelationObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

public class ManageController implements Initializable {

    @FXML
    public ComboBox<RelationObject> tournoiCB;
    @FXML
    public TextField nameTF;
    @FXML
    public ImageView imageIV;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Sponsor currentSponsor;
    Path selectedImagePath;
    boolean imageEdited;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for (RelationObject tournoi : SponsorService.getInstance().getAllTournois()) {
            tournoiCB.getItems().add(tournoi);
        }
        
        currentSponsor = ShowAllController.currentSponsor;

        if (currentSponsor != null) {
            topText.setText("Modifier sponsor");
            btnAjout.setText("Modifier");
            
            try {
                tournoiCB.setValue(currentSponsor.getTournoiId());
                nameTF.setText(currentSponsor.getName());
                selectedImagePath = FileSystems.getDefault().getPath(currentSponsor.getLogo());
                if (selectedImagePath.toFile().exists()) {
                    imageIV.setImage(new Image(selectedImagePath.toUri().toString()));
                }
                
            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter sponsor");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {
            
            String imagePath;
            if (imageEdited) {
                imagePath = currentSponsor.getLogo();
            } else {
                createImageFile();
                imagePath = selectedImagePath.toString();
            }
            
            Sponsor sponsor = new Sponsor(
                tournoiCB.getValue(),
                nameTF.getText(),
                imagePath
            );

            if (currentSponsor == null) {
                if (SponsorService.getInstance().add(sponsor)) {
                    AlertUtils.makeSuccessNotification("Sponsor ajouté avec succés");
                    MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_SPONSOR);
                } else {
                    AlertUtils.makeError("sponsor existe deja");
                }
            } else {
                sponsor.setId(currentSponsor.getId());
                if (SponsorService.getInstance().edit(sponsor)) {
                    AlertUtils.makeSuccessNotification("Sponsor modifié avec succés");
                    ShowAllController.currentSponsor = null;
                    MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_SPONSOR);
                } else {
                    AlertUtils.makeError("sponsor existe deja");
                }
            }
            
            if (selectedImagePath != null) {
                createImageFile();
            }
        }
    }

    @FXML
    public void chooseImage(ActionEvent actionEvent) {

        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(MainApp.mainStage);
        if (file != null) {
            selectedImagePath = Paths.get(file.getPath());
            imageIV.setImage(new Image(file.toURI().toString()));
        }
    }

    public void createImageFile() {
        try {
            Path newPath = FileSystems.getDefault().getPath("src/com/theacesrep/images/uploads/" + selectedImagePath.getFileName());
            Files.copy(selectedImagePath, newPath, StandardCopyOption.REPLACE_EXISTING);
            selectedImagePath = newPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean controleDeSaisie() {
        
        
        if (tournoiCB.getValue() == null) {
            AlertUtils.makeInformation("Choisir tournoi");
            return false;
        }
        
        
        if (nameTF.getText().isEmpty()) {
            AlertUtils.makeInformation("name ne doit pas etre vide");
            return false;
        }
        
        
        if (selectedImagePath == null) {
            AlertUtils.makeInformation("Veuillez choisir une image");
            return false;
        }
        
        
        return true;
    }
}