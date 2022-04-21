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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class GestionofferController implements Initializable {

    @FXML
    private Label theaces;
    @FXML
    private ComboBox liste_ELt;
    @FXML
    private ComboBox liste_ELt1;
    @FXML
    private ComboBox liste_promo;
    @FXML
    private Label theaces1;
    @FXML
    private Button ajouterP;
    @FXML
    private TextField txt_email;
    @FXML
    private Button offer;

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
                              
                 try {
                Connection connection = My_Connexion.getInstance().getCnx();
		PreparedStatement state =connection.prepareStatement("SELECT * FROM element");
                ResultSet x=state.executeQuery();
                while(x.next())
                {   
                  liste_ELt1.getItems().add((x.getString("id")+" - "+ x.getString("nom")));
                  
                }
                
               
        } 
        catch (Exception e) {
                e.printStackTrace();
	}
                 
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
    private void cmb_comm_changed(ActionEvent event) {
    }

    @FXML
    private void AjouterElt(ActionEvent event) throws IOException, MessagingException {
        
        
        if((!liste_promo.getSelectionModel().isSelected(-1))&&(!liste_ELt.getSelectionModel().isSelected(-1))&&(!liste_ELt1.getSelectionModel().isSelected(-1))&&(!txt_email.getText().equals(""))){
        int id_p = Integer.parseInt( liste_promo.getSelectionModel().getSelectedItem().toString().split(" ")[0]);
        int id_elt1 = Integer.parseInt( liste_ELt.getSelectionModel().getSelectedItem().toString().split(" ")[0]);
        int id_elt2 = Integer.parseInt( liste_ELt1.getSelectionModel().getSelectedItem().toString().split(" ")[0]);
        
        PromotionService ps = new PromotionService();
        
         ElementService A = new ElementService();
         int i =0;
         List L = A.afficher();

         while (i!=L.size()){
            
             Element e1 =(Element) L.get(i);
             if(e1.getId()==(id_elt1)){
                 
                 Element e = new Element(id_elt1,e1.getQuantite(),id_p,e1.getType(),e1.getRef(),e1.getNom(),e1.getDescription(),e1.getEtat(),e1.getImage(),e1.getPrix());
                 A.modifier(e);
                 i++;
             }else if(e1.getId()==(id_elt2)){
                 
                 Element elt = new Element(id_elt2,e1.getQuantite(),id_p,e1.getType(),e1.getRef(),e1.getNom(),e1.getDescription(),e1.getEtat(),e1.getImage(),e1.getPrix());
                 A.modifier(elt);
                 i++;
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
            
            String htmlCode = "<h2> Nouvelle offre a été ajouter</h2>" ; 
            message.setContent(htmlCode,"text/html");
            
            Transport.send(message);
            
               System.out.println("succes");
               
         
           
         

        
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();    
        }
        else {
            
             JOptionPane.showMessageDialog(null, "Verifier Les champs... ");
             System.out.println("Verifier Les champs... ");
        }

        
        
        
   
        
    }

    @FXML
    private void goOffer(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("FrontOffer.fxml"));
        Scene AfficherElt_scene = new Scene(AfficherElt);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(AfficherElt_scene);
        app_stage.show();
        
    }
    
}
