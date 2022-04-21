/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entity.Element;
import Services.ElementService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ModifierElementController implements Initializable {

    @FXML
    private Pane color;
    @FXML
    private Button front;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
 @FXML
    private TableView<Element> table;
    
    @FXML
    private TableColumn<Element, String> elt_nom;
    @FXML
    private TableColumn<Element, String> elt_Ref;
    @FXML
    private TableColumn<Element, String> elt_Desc;
    @FXML
    private TableColumn<Element, String> elt_Type;
    @FXML
    private TableColumn<Element, Integer> elt_qte;
    @FXML
    private TableColumn<Element, Float> elt_prix;
    @FXML
    private TableColumn<Element, String> image_elt;
    @FXML
    private TableColumn<Element, Integer> elt_Promo;
    @FXML
    private TableColumn<Element, String> elt_etat;
    @FXML
    private TextField nomElt;
    @FXML
    private TextField elt_Desc1;
    @FXML
    private TextField elt_Ref1;
    @FXML
    private TextField eltPrix;
    @FXML
    private TextField eltQte;
    @FXML
    private TextField eltType;
    @FXML
    private TextField path_img;
    @FXML
    private TextField etatE;
    @FXML
    private TextField eltPromo;
    @FXML
    private Button modifierElt;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private Button Home;
    @FXML
    private Button choisir;
        private FileChooser filechooser;
    private File file;
    private final Desktop desktop = Desktop.getDesktop();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Element> element = FXCollections.observableArrayList();
          
          
          
          
                ElementService cc = new ElementService(); 
                element.addAll(cc.afficher()) ;
                
                table.setItems(element);
                
      
              
               // idarticle.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
                elt_nom.setCellValueFactory(new PropertyValueFactory<Element, String> ("nom"));
                elt_Ref.setCellValueFactory(new PropertyValueFactory<Element, String> ("ref"));
                elt_Desc.setCellValueFactory(new PropertyValueFactory<Element, String> ("description"));
                elt_Type.setCellValueFactory(new PropertyValueFactory<Element, String> ("type"));
                elt_qte.setCellValueFactory(new PropertyValueFactory<Element, Integer> ("quantite"));
                elt_etat.setCellValueFactory(new PropertyValueFactory<Element, String> ("etat"));
                elt_prix.setCellValueFactory(new PropertyValueFactory<Element, Float> ("prix"));
                image_elt.setCellValueFactory(new PropertyValueFactory<Element, String> ("image"));
                elt_Promo.setCellValueFactory(new PropertyValueFactory<Element, Integer>("promotion_id"));
        
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
    private void GoHome(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
        
    }

    @FXML
    private void Click(MouseEvent event) {
         Object selectedItem = table.getSelectionModel().getSelectedItem();
         ElementService A = new ElementService(); 
         
         int i =0;
         List Elements = A.afficher();
        
 
        
   while (i!=Elements.size()){
     
        
          
         if(Elements.get(i).toString().equals(selectedItem.toString())) {
            
             
             System.out.println(selectedItem.toString());          
             System.out.println(Elements.get(i).toString());            
             System.out.println(selectedItem.getClass());


              Element B = (Element) Elements.get(i);
              id.setText(String.valueOf(B.getId()));
               
              etatE.setText(String.valueOf(B.getEtat())) ; 
              nomElt.setText(String.valueOf(B.getNom())) ; 
              elt_Desc1.setText(String.valueOf(B.getDescription())) ; 
              elt_Ref1.setText(String.valueOf(B.getRef())) ; 
              eltPrix.setText(String.valueOf(B.getPrix())) ; 
             eltQte.setText(String.valueOf(B.getQuantite()));
             eltType.setText(String.valueOf(B.getType()));
             eltPromo.setText(String.valueOf(B.getPromotion_id())) ;       
             path_img.setText(String.valueOf(B.getImage())) ;  
            break;
             
            } else {
             
             i++;

            }
          }
    }

    @FXML
    private void modifierElt(ActionEvent event) throws IOException {
        int idElemnt = Integer.parseInt(id.getText()); 
        String etat = etatE.getText() ; 
         String nomE = nomElt.getText() ; 
        String desc = elt_Desc1.getText() ; 
        String ref = elt_Ref1.getText() ; 
         float prix = Float.parseFloat(eltPrix.getText()) ; 
         int qte = Integer.parseInt(eltQte.getText()) ;  
         String type = eltType.getText() ;
         int promo = Integer.parseInt(eltPromo.getText()) ;       
         String img = path_img.getText() ;

         
       ElementService A = new ElementService(); 
       Element e = new Element(idElemnt,qte,promo,type,ref,nomE,desc,etat,img,prix);
        A.modifier(e);
        
        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionElement.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
        
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
