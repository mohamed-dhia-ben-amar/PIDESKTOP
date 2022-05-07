

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.services;

import aces.entities.Article;
import aces.utiles.My_Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author aycha
 */
public class CrudArticles {
     public List<Article> afficherArticle (){
        
            List <Article> mylist =  new ArrayList<>() ;
        try {
             String req3 = "SELECT * FROM Article " ;
            Statement st = new My_Connexion().getCnx().createStatement()  ; 
            ResultSet rs = st.executeQuery(req3) ;
            while (rs.next()){
                Article A = new Article () ; 
                A.setId(rs.getInt(1));
                A.setTitre(rs.getString("titre"));
                A.setContenu(rs.getString("contenu"));
                A.setDateCreation(rs.getString("datecreation"));   
                A.setImagearticle(rs.getString("imagearticle"));
                mylist.add(A) ; 
                  
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mylist ; 
     }
     
     
      public List<Article> afficherArticlesearch (String value){
        
            List <Article> mylist =  new ArrayList<>() ;
        try {
             String req3 = "SELECT * FROM Article where titre LIKE '%"+value+"%'" ;
            Statement st = new My_Connexion().getCnx().createStatement()  ; 
            ResultSet rs = st.executeQuery(req3) ;
            while (rs.next()){
                Article A = new Article () ; 
                A.setId(rs.getInt(1));
                A.setTitre(rs.getString("titre"));
                A.setContenu(rs.getString("contenu"));
                A.setDateCreation(rs.getString("datecreation"));   
                A.setImagearticle(rs.getString("imagearticle"));
                mylist.add(A) ; 
                  
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mylist ; 
     }
     
     
     
     
     
     
     
     
     
     
     
     
      public List<Article> afficherArticles(Integer id) {
         List <Article> mylist =  new ArrayList<>() ;
        try {
             String req3 = "SELECT * FROM Article where id= "+id+"" ;
            Statement st = new My_Connexion().getCnx().createStatement()  ; 
            ResultSet rs = st.executeQuery(req3) ;
            while (rs.next()){
                Article A = new Article () ; 
                A.setId(rs.getInt(1));
                A.setTitre(rs.getString("titre"));
                A.setContenu(rs.getString("contenu"));  
                A.setImagearticle(rs.getString("imagearticle"));
                mylist.add(A) ; 
                  
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mylist ; 
    }
      
       public List<Article> affichersArticles(Integer id) {
         List <Article> mylist =  new ArrayList<>() ;
        try {
             String req3 = "SELECT * FROM Article where id= "+id+"" ;
            Statement st = new My_Connexion().getCnx().createStatement()  ; 
            ResultSet rs = st.executeQuery(req3) ;
            while (rs.next()){
                Article A = new Article () ; 
                A.setId(rs.getInt(1));
                A.setTitre(rs.getString("titre"));
                A.setContenu(rs.getString("contenu"));  
               
                mylist.add(A) ; 
                  
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mylist ; 
    }
     
     
     
     
     
     
     
        
         public void ajouterarticle(Article A){
        
        String req2 = "INSERT INTO Article (titre,contenu,imagearticle,datecreation) VALUES ('" + A.getTitre() + "','" + A.getContenu() +"','" + A.getImagearticle()+"','" + A.getDateCreation()+ "')"; 

        
        try { 
            PreparedStatement pst = new My_Connexion().getCnx().prepareStatement(req2) ;
            pst.executeUpdate() ; 
         //    PreparedStatement pst1 = new My_Connexion().getCnx().prepareStatement(req3) ;
           // pst1.setString(1,A.getContenu());
            //pst1.executeUpdate() ; 
        
            System.out.println("Article added successfuly");
            JOptionPane.showMessageDialog(null, "Article added successfully");
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
         JOptionPane.showMessageDialog(null, ex);
        }
         }
         
         
      public void ModifierArticle (Article A)
    {
        try {
            String req3 = "UPDATE Article SET titre= ? where id= ?" ;
            PreparedStatement pst = new My_Connexion().getCnx().prepareStatement(req3) ;
         
            pst.setString(1,A.getTitre());
             pst.setInt(2,A.getId());

           
           
            pst.executeUpdate() ;
             System.out.println("Your article has been modified ");
         JOptionPane.showMessageDialog(null, "Article modified successfully ");
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             JOptionPane.showMessageDialog(null, ex);
        }
    }
          public void SupprimerArticle (int id ){
       
      try {
            String req3 = "DELETE FROM Article WHERE id=? " ;
            
           PreparedStatement pst2= new My_Connexion().getCnx().prepareStatement(req3) ;
            pst2.setInt(1,id);
           pst2.executeUpdate() ; 
            System.out.print("Votre article a est suprrimé ");
            JOptionPane.showMessageDialog(null, "article supprimé ");
                        
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
          // JOptionPane.showMessageDialog(null, ex);
         JOptionPane.showMessageDialog(null, "cet article obtient un commentaire  ");
        }
        
        
    
    
}

   

   
}
