/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.sponsor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.My_Connexion;

/**
 *
 * @author MSI
 */
public class ServiceSponsor {
    
     public List<sponsor> getAll() {
           String req = "select * from sponsor";
            List<sponsor> list=new ArrayList<>();
            try {
                 Statement ste = new My_Connexion().getCnx().createStatement() ;

              ResultSet rs = ste.executeQuery(req) ;
               while(rs.next()){
                  list.add(new sponsor(rs.getInt("id"),rs.getInt("tournoi_id"), rs.getString("name"),rs.getString("logo")));
               }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;  }
     
     
      public void add(sponsor s) {


            String req = "insert into sponsor (name,tournoi_id,logo) values ('" + s.getName()+ "','" + s.getTournoi_id()+ "','" + s.getLogo()+ "')" ;

          {

            try {
                Statement ste = new My_Connexion().getCnx().createStatement() ;

                ste.executeUpdate(req);

                System.out.println("------Sponsor bien ajouter-------");
            } catch (SQLException ex) {
                Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
              
                
             }
            }
         
              
                 JOptionPane.showMessageDialog(null, "votre commentaire est ajouté ");  
            
        }
     
     
     
     
        public void update(sponsor s) {
            
           {
               
        try {
            String req3 = "UPDATE sponsor SET name= ?, tournoi_id= ? where id=?" ;
            PreparedStatement pst = new My_Connexion().getCnx().prepareStatement(req3) ;
         
            pst.setString(1, s.getName());
            pst.setInt(2, s.getTournoi_id());       
            pst.setInt(3, s.getId());

            
           
            pst.executeUpdate() ;
             System.out.println("votre sponsor est modifié ");
             
                JOptionPane.showMessageDialog(null, "votre sponsor est modifié ");  
        
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);

        }
           }
           
            
        }
     
       public void delete(int id) {
          {
             try {
            String req3 = "DELETE FROM sponsor WHERE id=? " ;
            
           PreparedStatement pst2= new My_Connexion().getCnx().prepareStatement(req3) ;
            pst2.setInt(1,id);
           pst2.executeUpdate() ; 
            System.out.print("Votre sponsor  est suprrimé ");
              JOptionPane.showMessageDialog(null, "votre sponsor est supprimé ");  
           
                        
        } catch (SQLException ex) {
          Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
         
        }}
        
           
            
                  
            
        }

     
     
    
}
