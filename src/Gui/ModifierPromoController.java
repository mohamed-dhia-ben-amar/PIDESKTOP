/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entity.Element;
import Entity.Promotion;
import Services.ElementService;
import Services.PromotionService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ModifierPromoController implements Initializable {

    @FXML
    private Pane color;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button Home;
    @FXML
    private TableView<Promotion> table;
    @FXML
    private TableColumn<Promotion, Integer> id_P;
    @FXML
    private TableColumn<Promotion, String> date_debut;
    @FXML
    private TableColumn<Promotion, Float> Pourcentage;
    @FXML
    private TableColumn<Promotion, String> Date_Fin;
    @FXML
    private TextField Proucentage_E;
    private DatePicker Debut_D;
    private DatePicker Fin_D;
    @FXML
    private Button ajouterP;
    @FXML
    private TextField Proucentage_E1;
    @FXML
    private TextField Proucentage_E11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Promotion> promo = FXCollections.observableArrayList();
          
          
          
          
                PromotionService cc = new PromotionService(); 
                promo.addAll(cc.afficher()) ;
                
                table.setItems(promo);
                
      
              
                id_P.setCellValueFactory(new PropertyValueFactory<Promotion,Integer>("id"));
                date_debut.setCellValueFactory(new PropertyValueFactory<Promotion, String> ("date_deb"));
                Pourcentage.setCellValueFactory(new PropertyValueFactory<Promotion, Float> ("pourcentage"));
                Date_Fin.setCellValueFactory(new PropertyValueFactory<Promotion, String> ("date_fin"));

        // TODO
    }    

      @FXML
    private void ajouterPromo(ActionEvent event) throws IOException {
         System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("AjouterPromo.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
    }

    @FXML
    private void modifierPomo(ActionEvent event) throws IOException {
         System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("ModifierPromo.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show(); 
    }

    @FXML
    private void supprimerPromo(ActionEvent event) throws IOException {
    System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("SupprimerPromo.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show();     }

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
         PromotionService A = new PromotionService(); 
         
         int i =0;
         List Promo = A.afficher();
        
   while (i!=Promo.size()){
         if(Promo.get(i).toString().equals(selectedItem.toString())) {
            
             
             System.out.println(selectedItem.toString());          
             System.out.println(Promo.get(i).toString());            
             System.out.println(selectedItem.getClass());


              Promotion B = (Promotion) Promo.get(i);
              id_P.setText(String.valueOf(B.getId()));
               
              Debut_D.setValue((LocalDate.parse(String.valueOf(B.getDate_deb())))); 
              
             Fin_D.setValue((LocalDate.parse(String.valueOf(B.getDate_fin())))); 
              Proucentage_E.setText(String.valueOf(B.getPourcentage())) ; 
              
            break;
             
            } else {
             
             i++;

            }
          }
        
    }

    @FXML
    private void AjouterElt(ActionEvent event) throws IOException {
        
        
         int idElemnt = Integer.parseInt(id_P.getText()); 
         float pourcen = Float.parseFloat(Proucentage_E.getText());
         String date_debut=(Debut_D.getValue().toString());
         String date_f=(Fin_D.getValue().toString());
         System.out.println(Debut_D.getValue().toString());
         
         if((Debut_D.getValue().isBefore(Fin_D.getValue()))&&((Debut_D.getValue().isAfter(LocalDate.now()))&&(Fin_D.getValue().isAfter(LocalDate.now())))){
            
         Promotion p = new Promotion(idElemnt,date_debut, date_f, pourcen);
         PromotionService ps = new PromotionService();
         ps.modifier(p);
         System.out.println("bien modifier...");
        
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionPromo.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show(); 
            
        }
        else {
            
             JOptionPane.showMessageDialog(null, "Date Hors intervalle... ");
             System.out.println("Date Hors intervalle...");
        }
        
        
        
        
    }

        
        
        
    }
    

