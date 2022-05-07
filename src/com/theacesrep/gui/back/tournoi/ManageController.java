package com.theacesrep.gui.back.tournoi;

import com.theacesrep.MainApp;
import com.theacesrep.entities.Tournoi;
import com.theacesrep.gui.back.MainWindowController;
import com.theacesrep.services.TournoiService;
import com.theacesrep.utils.AlertUtils;
import com.theacesrep.utils.Constants;
import com.theacesrep.utils.RelationObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    public ComboBox<RelationObject> clientCB;
    @FXML
    public TextField nameTF;
    @FXML
    public DatePicker dateDebutDP;
    @FXML
    public DatePicker dateFinDP;
    @FXML
    public TextField descriptionTF;
    @FXML
    public TextField prixTF;
    @FXML
    public TextField nbparticipantTF;
    @FXML
    public ImageView imageIV;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Tournoi currentTournoi;
    Path selectedImagePath;
    boolean imageEdited;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for (RelationObject client : TournoiService.getInstance().getAllClients()) {
            clientCB.getItems().add(client);
        }
        
        currentTournoi = ShowAllController.currentTournoi;

        if (currentTournoi != null) {
            topText.setText("Modifier tournoi");
            btnAjout.setText("Modifier");
            
            try {
                clientCB.setValue(currentTournoi.getClientId());
                nameTF.setText(currentTournoi.getName());
                dateDebutDP.setValue(currentTournoi.getDateDebut());
                dateFinDP.setValue(currentTournoi.getDateFin());
                descriptionTF.setText(currentTournoi.getDescription());
                prixTF.setText(String.valueOf(currentTournoi.getPrix()));
                nbparticipantTF.setText(String.valueOf(currentTournoi.getNbparticipant()));
                selectedImagePath = FileSystems.getDefault().getPath(currentTournoi.getImage());
                if (selectedImagePath.toFile().exists()) {
                    imageIV.setImage(new Image(selectedImagePath.toUri().toString()));
                }
                
            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter tournoi");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {
            
            String imagePath;
            if (imageEdited) {
                imagePath = currentTournoi.getImage();
            } else {
                createImageFile();
                imagePath = selectedImagePath.toString();
            }
            
            Tournoi tournoi = new Tournoi(
                clientCB.getValue(),
                nameTF.getText(),
                dateDebutDP.getValue(),
                dateFinDP.getValue(),
                descriptionTF.getText(),
                Float.parseFloat(prixTF.getText()),
                Integer.parseInt(nbparticipantTF.getText()),
                imagePath
            );

            if (currentTournoi == null) {
                if (TournoiService.getInstance().add(tournoi)) {
                    AlertUtils.makeSuccessNotification("Tournoi ajouté avec succés");
                    MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_TOURNOI);
                } else {
                    AlertUtils.makeError("tournoi existe deja");
                }
            } else {
                tournoi.setId(currentTournoi.getId());
                if (TournoiService.getInstance().edit(tournoi)) {
                    AlertUtils.makeSuccessNotification("Tournoi modifié avec succés");
                    ShowAllController.currentTournoi = null;
                    MainWindowController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_TOURNOI);
                } else {
                    AlertUtils.makeError("tournoi existe deja");
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
        
        
        if (clientCB.getValue() == null) {
            AlertUtils.makeInformation("Choisir client");
            return false;
        }
        
        
        if (nameTF.getText().isEmpty()) {
            AlertUtils.makeInformation("name ne doit pas etre vide");
            return false;
        }
        
        
        if (dateDebutDP.getValue() == null){
            AlertUtils.makeInformation("Choisir une date pour dateDebut");
            return false;
        }
        
        
        if (dateFinDP.getValue() == null){
            AlertUtils.makeInformation("Choisir une date pour dateFin");
            return false;
        }
        
        
        if (descriptionTF.getText().isEmpty()) {
            AlertUtils.makeInformation("description ne doit pas etre vide");
            return false;
        }
        
        
        if (prixTF.getText().isEmpty()) {
            AlertUtils.makeInformation("prix ne doit pas etre vide");
            return false;
        }
        
        try {
            Float.parseFloat(prixTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("prix doit etre un réel");
            return false;
        }
        if (nbparticipantTF.getText().isEmpty()) {
            AlertUtils.makeInformation("nbparticipant ne doit pas etre vide");
            return false;
        }
        
        try {
            Integer.parseInt(nbparticipantTF.getText());
        } catch (NumberFormatException ignored) {
            AlertUtils.makeInformation("nbparticipant doit etre un nombre");
            return false;
        }
        
        if (selectedImagePath == null) {
            AlertUtils.makeInformation("Veuillez choisir une image");
            return false;
        }
        
        
        return true;
    }
}