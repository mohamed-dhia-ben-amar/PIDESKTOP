/*1
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.test;

import aces.entities.Article;
import aces.entities.Commentaire;
import aces.services.CrudArticles;
import aces.services.CrudCommentaires;
import aces.utiles.My_Connexion;

/**
 *
 * @author msi
 */
public class MainClass {
    
    
      public static void main(String[] args) {
    
        My_Connexion mc = new My_Connexion () ; 
        CrudArticles A = new CrudArticles();
      
        A.afficherArticle();
            System.out.println(A.afficherArticle());
           CrudCommentaires C = new CrudCommentaires();
            C.getAll(); 
            System.out.println(C.getAll());
            
           Commentaire C7 = new Commentaire(3,"TESTER");
            System.out.println(C7.toString());
         Article A2 = new Article("Product TEST 11 ","MSI2","",java.time.LocalDate.now().toString());
           A.ajouterarticle(A2);
            
            A.ModifierArticle(new Article(170,"GAMES"));
            
            
            
            //C.update(new Commentaire(72,1,"I LOVEDD ITT"));
           
           java.time.LocalDate.now();
          
           // A.SupprimerArticle(166);
            //System.out.println(A.afficherArticle());
           
            //System.out.println(A.afficherArticle());
  
       // A.SupprimerArticle(171);
            C.delete(156);
         
          
            
     
        


         
      
      }
}
