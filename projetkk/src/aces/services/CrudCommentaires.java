/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.services;

import aces.entities.Commentaire;
import aces.utiles.My_Connexion;
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
 * @author aycha
 */
public class CrudCommentaires {
    
    
        public List<Commentaire> getAll() {
           String req = "select * from Commentaire";
            List<Commentaire> list=new ArrayList<>();
            try {
                 Statement ste = new My_Connexion().getCnx().createStatement() ;

              ResultSet rs = ste.executeQuery(req) ;
               while(rs.next()){
                  list.add(new Commentaire(rs.getInt("id"),rs.getInt("article_id"), rs.getString("contenu")));
               }
            } catch (SQLException ex) {
                Logger.getLogger(CrudCommentaires.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;  }
        
        
         public List<Commentaire> affichercommentaires(Integer article_id) {
            String req = "select * from Commentaire where article_id="+article_id+"";
            List<Commentaire> list=new ArrayList<>();
            try {
                 Statement ste = new My_Connexion().getCnx().createStatement() ;

              ResultSet rs = ste.executeQuery(req) ;
               while(rs.next()){
                  list.add(new Commentaire(rs.getInt("id"),rs.getInt("article_id"), rs.getString("contenu")));
               }
            } catch (SQLException ex) {
                Logger.getLogger(CrudCommentaires.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;  }
    

        
         public void add(Commentaire t) {


            String req = "insert into Commentaire (contenu,article_id) values ('" + t.getContenu() + "','" + t.getArticle_id() + "')" ;

          {

            try {
                Statement ste = new My_Connexion().getCnx().createStatement() ;

                ste.executeUpdate(req);

                System.out.println("------Commentaire bien ajouter-------");
            } catch (SQLException ex) {
                Logger.getLogger(CrudCommentaires.class.getName()).log(Level.SEVERE, null, ex);
              
                
             }
            }
         
              
                 JOptionPane.showMessageDialog(null, "votre commentaire est ajouté ");  
            
        }
         
         
      
        public void update(Commentaire t) {
            
           {
               
        try {
            String req3 = "UPDATE commentaire SET contenu= ?, article_id= ? where id=?" ;
            PreparedStatement pst = new My_Connexion().getCnx().prepareStatement(req3) ;
         
            pst.setString(1, t.getContenu());
            pst.setInt(2, t.getArticle_id());       
            pst.setInt(3, t.getId());

            
           
            pst.executeUpdate() ;
             System.out.println("votre commentaire est modifié ");
             
                JOptionPane.showMessageDialog(null, "votre commentaire est modifié ");  
        
        
        } catch (SQLException ex) {
            Logger.getLogger(CrudCommentaires.class.getName()).log(Level.SEVERE, null, ex);

        }
           }
           
            
        }

        public void delete(int id) {
          {
             try {
            String req3 = "DELETE FROM Commentaire WHERE id=? " ;
            
           PreparedStatement pst2= new My_Connexion().getCnx().prepareStatement(req3) ;
            pst2.setInt(1,id);
           pst2.executeUpdate() ; 
            System.out.print("Votre commentaire est suprrimé ");
              JOptionPane.showMessageDialog(null, "votre commentaire est supprimé ");  
           
                        
        } catch (SQLException ex) {
          Logger.getLogger(CrudCommentaires.class.getName()).log(Level.SEVERE, null, ex);
         
        }}
        
           
            
                  
            
        }

   
}
