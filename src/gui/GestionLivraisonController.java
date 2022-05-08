/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Livraison;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

import services.ServiceLivraison;
import services.ServiceLivreur;
import java.sql.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author bouss
 */
public class GestionLivraisonController implements Initializable {

    ServiceLivraison sl = new ServiceLivraison();
    @FXML
    private Button retour;

    @FXML
    private TableView<Livraison> tblLiv;

    @FXML
    private TableColumn<Livraison, String> tbMethod;

    @FXML
    private TableColumn<Livraison, Integer> tbCinLiv;

    @FXML
    private TableColumn<Livraison, Integer> tbIdClient;

    @FXML
    private TableColumn<Livraison, Integer> tbIdProd;

    @FXML
    private TableColumn<Livraison, String> tbAdresse;

    @FXML
    private TableColumn<Livraison, String> tbEtat;

    private TextField tfMethod;

    private ComboBox<Integer> tfCinLivreur;

    private TextField tfIdClient;

    @FXML
    private Button DelP;

    @FXML
    private Button EdP;

    @FXML
    private Label error;

    private TextField tfIdProd;

    private TextField tfAdresse;

    @FXML
    private TextField tfEtat;

    ObservableList<Livraison> list;

    ServiceLivraison servLiv;

    File selectedImage;

    private int selectedLivId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList(sl.recuperer());
        System.out.println("nabil");

        tbMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        tbCinLiv.setCellValueFactory(new PropertyValueFactory<>("cinLivreur"));
        tbIdClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        tbIdProd.setCellValueFactory(new PropertyValueFactory<>("idProd"));
        tbAdresse.setCellValueFactory(new PropertyValueFactory<>("adresseclient"));
        tbEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        ServiceLivreur s = new ServiceLivreur();

        tblLiv.setItems(list);

        tblLiv.setRowFactory(tv -> {

            TableRow<Livraison> row = new TableRow<>();

            row.setOnMouseClicked(event -> {

                if (!row.isEmpty()) {
                    final Livraison selectedItem = tblLiv.getSelectionModel().getSelectedItem();

                    tfEtat.setText(selectedItem.getEtat());

                    //combCat.setValue(selectedItem.getCategory());
                    selectedLivId = selectedItem.getId();
                }
            });

            return row;
        });

        servLiv = new ServiceLivraison();

        ObservableList<Integer> cin = FXCollections
                .observableArrayList(
                        s.recuperer().stream().map(c -> c.getCin()).collect(Collectors.toList())
                );

        System.out.println(cin);
        System.out.println("test");

        System.out.println("test");
    }

    @FXML
    private void returnb(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GestLivreur.fxml"));
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void DelP(ActionEvent event) {
        final Livraison selectedItem = tblLiv.getSelectionModel().getSelectedItem();
        Livraison liv = sl.GetById(selectedItem.getId());
        sl.supprimer(liv.getId());

        list.remove(selectedItem);
        tblLiv.setItems(FXCollections.observableArrayList(sl.recuperer()));
        tblLiv.refresh();
    }

    @FXML
    private void EdP(ActionEvent event) {
        //final int cin = servLiv.getIdByCategoryName(combCat.getValue());

        // FIXME: change the id user from 1 to the current logged in user.
        final Livraison selectedItem = tblLiv.getSelectionModel().getSelectedItem();
        Livraison liv = sl.GetById(selectedItem.getId());

        liv.setEtat(tfEtat.getText());

        sl.modifier(liv);
        tblLiv.setItems(FXCollections.observableArrayList(sl.recuperer()));
        tblLiv.refresh();

    }

}
