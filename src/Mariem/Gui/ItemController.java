/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;





  
public class ItemController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Label Titre_P;
    @FXML
    private ImageView ImgView;
    
    Element element;
     private Connection connection = My_Connexion.getInstance().getCnx();
    
    private Statement ste;
    private ResultSet rs;
    @FXML
    private Label PRix_l;
    @FXML
    private Label lbl_promo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    
    public  void setData(Element element) {
            

         List <Promotion>pom = new ArrayList<>();

        try {   
            String req = " SELECT id,pourcentage  FROM `promotion` WHERE id IN (SELECT promotion_id FROM element) ";

            ste = connection.createStatement();
            rs = ste.executeQuery(req);
            System.out.println(rs.next());
             while (rs.next()) {
                 Promotion p =new Promotion();
                 p.setId(rs.getInt("id"));
                 p.setPourcentage(rs.getInt("pourcentage"));
                pom.add(p);
             }
             
             for(int i=0 ;i<pom.size();i++){
                this.element = element;
                Titre_P.setText(element.getNom());
                
                if(pom.get(i).getId()==element.getPromotion_id()){
                float n_prix = element.getPrix()-((element.getPrix()*pom.get(i).getPourcentage()))/100;
                lbl_promo.setVisible(true);
                lbl_promo.setText(n_prix +"DT");
                PRix_l.setVisible(false);
                
                }else{
                  PRix_l.setText(String.valueOf(element.getPrix()));
                }

            Image image = new Image("http://127.0.0.1:8000/uploads/images/"+element.getImage());
            ImgView.setImage(image);
            ImgView.setFitHeight(182);
            ImgView.setFitWidth(218);
            
                 
                 
             }
        } catch (SQLException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }

         }
    
}
