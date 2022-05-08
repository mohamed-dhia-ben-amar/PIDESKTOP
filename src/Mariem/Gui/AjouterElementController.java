/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Mariem.Gui;

import Mariem.Entity.Element;
import Mariem.Services.ElementService;
import Mariem.Utils.My_Connexion;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AjouterElementController implements Initializable {

    @FXML
    private Pane idajouter;
    @FXML
    private Button Home;
    @FXML
    private Button front;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField nomElt;
    @FXML
    private TextField elt_Desc;
    @FXML
    private TextField elt_Ref;
    @FXML
    private TextField eltPrix;
    @FXML
    private TextField eltQte;
    @FXML
    private TextField eltType;
    @FXML
    private RadioButton etat_Dispo;
    @FXML
    private RadioButton Etat2;
    @FXML
    private TextField path_img;
    
   
    @FXML
    private AnchorPane addd;
    @FXML
    private ComboBox liste_promo;
    @FXML
    private Button choisir;
    @FXML
    private ImageView image;
    private FileChooser filechooser;
    private File file;
    private final Desktop desktop = Desktop.getDesktop();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                Connection connection = My_Connexion.getInstance().getCnx();
		PreparedStatement state =connection.prepareStatement("SELECT * FROM promotion");
                ResultSet x=state.executeQuery();
                while(x.next())
                { 
                    System.out.println(LocalDate.now().isAfter( LocalDate.parse(String.valueOf(x.getString("date_fin")))));
                 if(!LocalDate.now().isAfter( LocalDate.parse(String.valueOf(x.getString("date_fin"))))){
                   
                 liste_promo.getItems().add((x.getString("id")+" - "+ x.getString("pourcentage")+" % "));}
                  
                }
                
               
        } 
        catch (Exception e) {
                e.printStackTrace();
	}
        // TODO
    }    

      @FXML
    private void afficherfront(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
    }

    @FXML
    private void ajouterElement(ActionEvent event) throws IOException {
           System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("AjouterElement.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
    }

    @FXML
    private void modifierElement(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("ModifierElement.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
    }

    @FXML
    private void supprimerElement(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("SupprimerElement.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
    }
    @FXML
    private void AjouterElt(ActionEvent event) throws IOException {

        String nomE = nomElt.getText();
        String desc =elt_Desc.getText();
        String ref = elt_Ref.getText() ;
        float prix =Float.parseFloat(eltPrix.getText());
        int qte= Integer.parseInt(eltQte.getText()) ;
        String type =eltType.getText();
        String etat =""; 
        
        
         String image2 =path_img.getText();
         if (etat_Dispo.isSelected()){
              etat = etat_Dispo.getText();
             
         }
         else if (Etat2.isSelected())
         {
              etat = Etat2.getText();
         }
         
        if(!liste_promo.getSelectionModel().isSelected(-1)){
        int id_c = Integer.parseInt( liste_promo.getSelectionModel().getSelectedItem().toString().split(" ")[0]);
        
        
        System.out.println(etat);
        Element e =new Element(qte, type, ref, nomE, desc, etat, image2, prix, id_c); 
        ElementService B = new ElementService(); 
        B.ajouter(e);
        System.out.println("bien ajouter");
        
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionElement.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
        
        } 
        else{
        
        Element e =new Element(qte, type, ref, nomE, desc, etat, image2, prix, 0); 
        ElementService B = new ElementService(); 
        B.ajouter(e);
        System.out.println("bien ajouter");
        
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionElement.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
            
            
            
        }
     
        
    }

    @FXML
    private void GoHome(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
        
    }

    @FXML
    private void cmb_comm_changed(ActionEvent event) {
        
          int id_c = Integer.parseInt( liste_promo.getSelectionModel().getSelectedItem().toString().split(" ")[0]);
   
    }

    @FXML
    private void imgB(ActionEvent event) {
        
       Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("selectionner une image !"); 
         filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        choisir.setOnAction(e -> {
          file = filechooser.showOpenDialog(primaryStage);
       if (file != null) {
                //String s = file.getAbsolutePath(); 
                String F = file.toURI().toString();
                path_img.setText(F);
                path_img.setText(F.replace("file:/C:/xampp/htdocs/integration/public/uploads/images/",""));
                
                //     image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
                //     img1.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });
        
        
    }
    
}
