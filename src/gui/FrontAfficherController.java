/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Table;
import desktopfxmlpidev.DesktopFXMLPiDev;
import entities.Reclamation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FrontAfficherController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Reclamation reclamation = null;

    ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();

    @FXML
    private Button add;
    @FXML
    private Button print;
    @FXML
    private Button display;
    @FXML
    private Button logout;
    @FXML
    private Button close;
    @FXML
    private TextField keywordTextField;
    @FXML
    private ListView<Reclamation> ListRecs;
    @FXML
    private Button mail;

    MouseEvent MouseEvent = null;
    @FXML
    private Button searchRec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Refresh(MouseEvent);
    }

    

    @FXML
    private void AddRec(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/AjouterReclamation.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AfficherReclamationController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Table TableauPrincipale() {

        Table table = new Table(5);
        //table.setWidthPercentage(100);
        //------------------------On créer l'objet cellule--------------------

        Cell cell = null;
        //Description
        table.addCell("ID");

        //TVA
        table.addCell("Description");

        //Quantity
        table.addCell("Method");

        //P.U
        table.addCell("Target");

        //Total
        table.addCell("State");

        //CONTENU DU TABLEAU
        for (int i = 0; i <= ListRecs.getItems().size() - 1; i++) {
            table.addCell((Integer.toString(ListRecs.getItems().get(i).getIdRec())));
            table.addCell(ListRecs.getItems().get(i).getDescription());
            table.addCell(ListRecs.getItems().get(i).getMethod_remb());
            table.addCell(ListRecs.getItems().get(i).getTarget());
            table.addCell(ListRecs.getItems().get(i).getEtat());
        }
        return table;
    } //Corps principale de l'appli

    @FXML
    private void Print(MouseEvent event) throws FileNotFoundException {
        String file
                = "C:/Users/MSI/Desktop/blank.pdf";

        // Step-1 Creating a PdfDocument object
        PdfDocument pdfDoc
                = new PdfDocument(new PdfWriter(file));

        // Step-2 Creating a Document object
        Document doc = new Document(pdfDoc);

        // Step-3 Creating a table
        Table table = TableauPrincipale();

        // Step-6 Adding Table to document
        doc.add((IBlockElement) table);

        // Step-7 Closing the document
        doc.close();
        System.out.println("Table created successfully..");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Print PDF");
        alert.setHeaderText("Results:");
        alert.setContentText("PDF Printed successfully!");
        alert.showAndWait();

    }

    @FXML
    private void Refresh(MouseEvent event) {

        ReclamationService ic = new ReclamationService();

        ListRecs.getItems().clear();
        try {
            for (int i = 0; i < ic.recuperer().size(); i++) {
                ListRecs.getItems().add(ic.recuperer().get(i));
            }

        } catch (Exception e) {
        }
    }

    @FXML
    private void LogOut(MouseEvent event) throws IOException {
        DesktopFXMLPiDev m = new DesktopFXMLPiDev();
        root = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void closeWindow(MouseEvent event
    ) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void mail(MouseEvent event) throws IOException {
        DesktopFXMLPiDev m = new DesktopFXMLPiDev();
        root = FXMLLoader.load(getClass().getResource("../gui/SendMail.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void SearchRec(MouseEvent event) {
        ReclamationService ic = new ReclamationService();

        ListRecs.getItems().clear();
        try {
            for (int i = 0; i < ic.recuperer().size(); i++) {
                ListRecs.getItems().add(ic.search(keywordTextField.getText()).get(i));
            }

        } catch (Exception e) {
        }
    }

}
