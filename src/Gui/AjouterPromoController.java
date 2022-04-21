/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Gui;

import Entity.Element;
import Entity.Promotion;
import Services.ElementService;
import Services.PromotionService;
import Utils.My_Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AjouterPromoController implements Initializable {

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
    private TextField Proucentage_E;
    @FXML
    private ComboBox liste_ELt;
    @FXML
    private DatePicker Debut_D;
    @FXML
    private DatePicker Fin_D;
    @FXML
    private Button ajouterP;
    @FXML
    private TextField txt_email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                Connection connection = My_Connexion.getInstance().getCnx();
		PreparedStatement state =connection.prepareStatement("SELECT * FROM element");
                ResultSet x=state.executeQuery();
                while(x.next())
                {   
                  liste_ELt.getItems().add((x.getString("id")+" - "+ x.getString("nom")));
                  
                }
                
               
        } 
        catch (Exception e) {
                e.printStackTrace();
	}
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
    private void AjouterElt(ActionEvent event) throws IOException, MessagingException {
            
            
        float pourcen = Float.parseFloat(Proucentage_E.getText());
        String date_debut=(Debut_D.getValue().toString());
        String date_f=(Fin_D.getValue().toString());
      
       
        if(!liste_ELt.getSelectionModel().isSelected(-1)){
         int id_c = Integer.parseInt( liste_ELt.getSelectionModel().getSelectedItem().toString().split(" ")[0]);
       
        if (Debut_D.getValue().isBefore(Fin_D.getValue())&&((Debut_D.getValue().isAfter(LocalDate.now()))||(Fin_D.getValue().isAfter(LocalDate.now())))){ 
         Promotion p = new Promotion(date_debut, date_f, pourcen);
         
         PromotionService ps = new PromotionService();
         ps.ajouter(p);
         int p2 =  ps.afficher().get(ps.afficher().size()-1).getId();
         System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa ---  " +  p2);
         ElementService A = new ElementService();
         int i =0;
         List L = A.afficher();

         while (i!=L.size()){
            
              Element e1 =(Element) L.get(i);
             if(e1.getId()==(id_c)){
                 
                 Element e = new Element(id_c,e1.getQuantite(),p2,e1.getType(),e1.getRef(),e1.getNom(),e1.getDescription(),e1.getEtat(),e1.getImage(),e1.getPrix());
                 A.modifier(e);
                break;
             }
             else i++;
         }
         System.out.println("bien ajouter...");
         
          String to = txt_email.getText(); 
         String myaccountEmail="iheb.ayari@esprit.tn" ; 
         String password ="211JMT3630" ; 
          Properties properties = new Properties();    
         properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable", "true");
         properties.put("mail.smtp.host", "smtp.gmail.com");
         properties.put("mail.smtp.port", "587");
       
         Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
         
         
     protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myaccountEmail, password);
           
        }
     
       });
          
            Message message = new MimeMessage(session) ;
                       
            message.setFrom(new InternetAddress(myaccountEmail));
           
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to) );
            
            message.setSubject("Prommooo Prommmoo !!!!!");
            
            //message.setText("Votre cours est ajoutée avec succ  ");
            
            String htmlCode = "<h2> Nouvelle promotion a été ajouter</h2>" ; 
            message.setContent(htmlCode,"text/html");
            
            Transport.send(message);
            
               System.out.println("succes");
               
         
           
         

        
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
        }else{
                       
        if (Debut_D.getValue().isBefore(Fin_D.getValue())&&((Debut_D.getValue().isAfter(LocalDate.now()))&&(Fin_D.getValue().isAfter(LocalDate.now())))){
         
        
         Promotion p = new Promotion(date_debut, date_f, pourcen);
         PromotionService ps = new PromotionService();
         ps.ajouter(p);
         
           String to = txt_email.getText(); 
         String myaccountEmail="iheb.ayari@esprit.tn" ; 
         String password ="211JMT3630" ; 
          Properties properties = new Properties();    
         properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable", "true");
         properties.put("mail.smtp.host", "smtp.gmail.com");
         properties.put("mail.smtp.port", "587");
       
         Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
         
         
     protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myaccountEmail, password);
           
        }
     
       });
          
            Message message = new MimeMessage(session) ;
                       
            message.setFrom(new InternetAddress(myaccountEmail));
           
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to) );
            
            message.setSubject("concernant votre Cours");
            
            //message.setText("Votre cours est ajoutée avec succ  ");
            
            String htmlCode = "<h2> Nouvelle promotion a été ajouter </h2>" ; 
            message.setContent(htmlCode,"text/html");
            
            Transport.send(message);
            
               System.out.println("succes");
               
         
           
         

         
         System.out.println("bien ajouter...");
        
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

    @FXML
    private void cmb_comm_changed(ActionEvent event) {
    }
    
}
