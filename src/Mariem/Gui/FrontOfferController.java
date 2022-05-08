/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Mariem.Gui;

import Mariem.Entity.Element;
import Mariem.Services.ElementService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class FrontOfferController implements Initializable {

    @FXML
    private ScrollPane Scrollpane;
    @FXML
    private GridPane gridpane;
    @FXML
    private Pane color;
    @FXML
    private Button offer;
    @FXML
    private Button Home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
          ElementService cc = new ElementService(); 
          List <Element> mylist =  new ArrayList<>();
         
          mylist=cc.afficher();
        
        System.out.println(mylist);
        
         
        int column = 0;
        int row = 1;
        int i =0;
        
        int max =mylist.size();
        int j =i+1;
        System.out.println("zzzzzzzzzzzzzzzzzzSSaaaaaaaaaaa " + max +" ------ " );

        try {
           while (i<mylist.size()-1){
               while(j!=max){
               System.out.println("zzzzzzzzzzzzzzzzzzSSaaaaaaaaaaa " + i +" ------ " + j);

               if((mylist.get(i).getPromotion_id() ==mylist.get(j).getPromotion_id())&&(mylist.get(i).getPromotion_id()!=0)&&(mylist.get(j).getPromotion_id()!=0)){
                   System.out.println("zzzzzzzmmmmmmmmmmmmmmmaaaaaaaaaaa " + i +" ------ " + j);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OfferItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                OfferItemController itemController = fxmlLoader.getController();
                itemController.setData(mylist.get(i),mylist.get(j));
                  System.out.println("aaaaaaaaaaaaaaaaaaaaaa" + i +" ------ " + j);
                
                if (column == 2) {
                    column = 0;
                  
                    
                    row++;
                }

                gridpane.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                gridpane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridpane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
               
                
                i++;
                j=i+1;
              break;
              
              
               }else {
                   System.out.println("aaaaoooooooooooooooooooooooooooooooo");
                    if (j==max-1){
                                           System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwoooooooooooooooooooooooooooooooo");

                        i++;
                        j=i+1;
                    }
                    else
                          j++;
                   
               }
            
               }
               
              }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        // TODO
    }    
    

    @FXML
    private void goOffer(ActionEvent event) throws IOException {
                System.out.println("You clicked me!");
        Parent AfficherElt = FXMLLoader.load(getClass().getResource("Gestionoffer.fxml"));
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
    
}
