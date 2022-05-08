/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Mariem.Gui;

import Mariem.Entity.Element;
import Mariem.Entity.Promotion;
import Mariem.Utils.My_Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class OfferItemController implements Initializable {

    @FXML
    private VBox vb0;
    @FXML
    private ImageView img;
    @FXML
    private VBox vb;
    @FXML
    private Label titre;
    @FXML
    private Label prix;
    @FXML
    private HBox Hbox1;
    @FXML
    private ImageView img1;
    @FXML
    private VBox vb1;
    @FXML
    private Label titre1;
    @FXML
    private Label prix1;
    
    Element element,elt;
    private Connection connection = My_Connexion.getInstance().getCnx();
    Image image2;
    private Statement ste;
    private ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hbox1(MouseEvent event) {
    }
    
    
      public  void setData(Element element1,Element elt2) {
            

      //   List <Promotion>pom = new ArrayList<>();
               List <Element>pom = new ArrayList<>();

                this.element = element1;
                this.elt=elt2;

        try {   
            //String req = " SELECT id,pourcentage  FROM `promotion` WHERE id IN (SELECT promotion_id FROM element) ";
            String req = " SELECT id  FROM `element` WHERE promotion_id = promotion_id ";

            ste = connection.createStatement();
            rs = ste.executeQuery(req);
            System.out.println(rs.next());
             while (rs.next()) {
//                 Promotion p =new Promotion();
//                 p.setId(rs.getInt("id"));
//                 p.setPourcentage(rs.getInt("pourcentage"));
//                 pom.add(p);
//             }
             
           //  for(int i=0 ;i<pom.size();i++){

                titre.setText(element.getNom());
                titre1.setText(elt.getNom());
                
                //if(pom.get(i).getId()==element.getPromotion_id()){
                if(rs.getInt("id")==element.getId()){

               // float n_prix = element.getPrix()-((element.getPrix()*pom.get(i).getPourcentage()))/100;
                float n_prix = element.getPrix();

                prix.setText(n_prix +"DT");
                Image image = new Image("http://127.0.0.1:8000/uploads/images/"+element.getImage());
                 img.setImage(image);
                  
                
                }
                 if(rs.getInt("id")==elt.getId()){
                //float n_prix = elt.getPrix()-((elt.getPrix()*pom.get(i).getPourcentage()))/100;
                float n_prix = elt.getPrix();
                prix1.setText(n_prix +"DT");
                titre1.setText(elt.getNom());

                    System.out.println(n_prix);
                 image2 = new Image("http://127.0.0.1:8000/uploads/images/"+elt.getImage());
                 img1.setImage(image2);

                
                }


            
             }
                 
           //  }
        } catch (SQLException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }

         }
    
}
