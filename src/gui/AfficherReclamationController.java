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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import desktopfxmlpidev.DesktopFXMLPiDev;
import entities.Client;
import entities.Reclamation;
import entities.Reparation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
//import javafx.scene.control.Cell;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AfficherReclamationController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button print;
    @FXML
    private Button add;
    @FXML
    private Button display;
    @FXML
    private Button logout;
    @FXML
    private Button close;
    @FXML
    private TableView<Reclamation> tableRec;

    @FXML
    private TableColumn<Reclamation, String> coldesc;
    @FXML
    private TableColumn<Reclamation, String> colstate;
    @FXML
    private TableColumn<Reclamation, String> coltarget;
    @FXML
    private TableColumn<Reclamation, String> colmethod;
    @FXML
    private TableColumn<Reclamation, String> colactions;
    @FXML
    private TableColumn<?, ?> coldate;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Reclamation reclamation = null;

    ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
    @FXML
    private TextField keywordTextField;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadDate();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        for (int i = 0; i <= tableRec.getItems().size() - 1; i++) {
            table.addCell((Integer.toString(tableRec.getItems().get(i).getIdRec())));
            table.addCell(tableRec.getItems().get(i).getDescription());
            table.addCell(tableRec.getItems().get(i).getMethod_remb());
            table.addCell(tableRec.getItems().get(i).getTarget());
            table.addCell(tableRec.getItems().get(i).getEtat());
        }

        return table;
    } //Corps principale de l'appli

    private PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
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

    @FXML
    private void Refresh() {
        try {
            ReclamationList.clear();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wlhfinal", "root", "");

            query = "SELECT * FROM `reclamation`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ReclamationList.add(new Reclamation(resultSet.getInt("idRec"), new Client(), new Reparation(), resultSet.getString("date"), resultSet.getString("description"), resultSet.getString("etat"), resultSet.getString("method_remb"), resultSet.getString("target")));
                tableRec.setItems(ReclamationList);

            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Display Complaints");
            alert.setHeaderText("Results:");
            alert.setContentText("Complaints displayed successfully!");
            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void loadDate() throws SQLException {

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wlhfinal", "root", "");

        colstate.setCellValueFactory(new PropertyValueFactory<>("etat"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colactions.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colmethod.setCellValueFactory(new PropertyValueFactory<>("method_remb"));
        coltarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        Refresh();

        //add cell of button edit 
        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctory = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView treatIcon = new FontAwesomeIconView(FontAwesomeIcon.ARCHIVE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        treatIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#black;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                reclamation = tableRec.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `reclamation` WHERE idRec  =" + reclamation.getIdRec();
                                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wlhfinal", "root", "");
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                Refresh();

                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherReclamationController.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        treatIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                reclamation = tableRec.getSelectionModel().getSelectedItem();
                                query = "update `reclamation` set etat='Treated' WHERE idRec  =" + reclamation.getIdRec();
                                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wlhfinal", "root", "");
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                Refresh();

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Treat Complaint");
                                alert.setHeaderText("Results:");
                                alert.setContentText("Complaint treated successfully!");
                                alert.showAndWait();

                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherReclamationController.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            reclamation = tableRec.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("../gui/AjouterReclamation.fxml"));
                            try {
                                loader.load();

                            } catch (IOException ex) {
                                Logger.getLogger(AfficherReclamationController.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }

                            AjouterReclamationController ajouterReclamationController = loader.getController();
                            ajouterReclamationController.reclamationid = reclamation.getIdRec();
                            ajouterReclamationController.setUpdate(true);
                            ajouterReclamationController.setTextField(reclamation.getDescription());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon, treatIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        colactions.setCellFactory(cellFoctory);
        tableRec.setItems(ReclamationList);

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Reclamation> filteredData = new FilteredList<>(ReclamationList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reclamation -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (reclamation.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (reclamation.getTarget().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (reclamation.getMethod_remb().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableRec.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableRec.setItems(sortedData);

    }

    @FXML
    private void loadStat(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/BarChartRecs.fxml"));
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

}
