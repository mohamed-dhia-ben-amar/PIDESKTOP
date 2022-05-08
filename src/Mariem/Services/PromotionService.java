/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mariem.Services;

import Mariem.Entity.Promotion;
import Mariem.Utils.My_Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class PromotionService implements IService<Promotion> {
        
    private Connection connection;
    private Statement ste;
    private PreparedStatement pste; 
     private ResultSet rs;
    
    public PromotionService() {
            
         connection = My_Connexion.getInstance().getCnx() ; 
}

    @Override
    public void ajouter(Promotion p) {
        try{
            String req = "INSERT INTO promotion (date_fin , pourcentage , Date_debut) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);


            System.out.println("Insertion promotion ...");
            
            if((p.getPourcentage()>75.0)||(p.getPourcentage()<5.0)){
                
            System.out.println("oooooooooooooooooooooooooooooooooo2222222222");
                JOptionPane.showMessageDialog(null, "Pourcentage Hors intervalle... ");
            
            
                System.out.println("Pourcentage Hors intervalle...");
            }
            else{
                      System.out.println("oooooooooooooooooooooooooooooooooo" + p.getPourcentage() );

             ps.setString(1,p.getDate_fin());
             ps.setFloat(2,p.getPourcentage());
             ps.setString(3,p.getDate_deb());
             
                ps.executeUpdate();
                
            JOptionPane.showMessageDialog(null, "Promo Ajouté");
            
            System.out.println("Une ligne insérée dans la table Promotion...");
                
                
                
                
         }
//                
       }
        catch(SQLException e){
              }
    }
    @Override
    public List<Promotion> afficher() {
    List<Promotion> Promotions = new ArrayList<>();
           
           String req = "SELECT * from promotion";
       
       try {

           ste = connection.createStatement();
           ResultSet rs = ste.executeQuery(req);
           while(rs.next()){
               Promotion p = new Promotion();
               p.setId(rs.getInt("id"));
               p.setDate_deb(rs.getString("Date_debut"));
               p.setDate_fin(rs.getString("date_fin"));
               p.setPourcentage(rs.getFloat("pourcentage"));
              
               Promotions.add(p);
               
           }
       } catch (SQLException ex) {
           Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
       }
       return Promotions;    }

    @Override
    public void modifier(Promotion p) {
        try{
            String req = "UPDATE promotion SET date_fin = ?, pourcentage = ?, Date_debut = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            System.out.println("Modification...");
              if((p.getPourcentage()>75.0)||(p.getPourcentage()<5.0)){
                
            System.out.println("oooooooooooooooooooooooooooooooooo2222222222");
                JOptionPane.showMessageDialog(null, "Pourcentage Hors intervalle... ");
            
            
                System.out.println("Pourcentage Hors intervalle...");
            }
            else{
             ps.setString(1,  p.getDate_fin());
            ps.setFloat(2,p.getPourcentage());
           ps.setString(3,  p.getDate_deb());
          // java.sql.Date.valueOf(p.getDate_deb());
          
            ps.setInt(4,p.getId());
            ps.executeUpdate();
            System.out.println("Une ligne modifiée dans la table Promotion");
            JOptionPane.showMessageDialog(null, "Promo a été modifier ");
        }
          
       }
       catch(SQLException e){
           System.out.println(e.getMessage());
       }    

 
           }

    @Override
    public void supprimer(Promotion t) {
   try {
        String req = "DELETE FROM promotion WHERE id=? " ;
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,t.getId());
         ps.executeUpdate() ; 
        JOptionPane.showMessageDialog(null, "Promotion supprimé ");
        } 
   catch (SQLException ex) {
            
            Logger.getLogger(ElementService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }   
}
    

   
   
    
    
    
    
    

