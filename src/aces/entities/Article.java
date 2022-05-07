/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.entities;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author aycha
 */
public class Article {
  private int id_article;
    private String titre;
    private String contenu;
     private String dateCreation;
     private String imagearticle;

    public Article(String titre, String contenu,String imagearticle) {
        this.titre = titre;
        this.contenu = contenu;
        this.imagearticle=imagearticle;
      
    }
     
     
     

    public Article( String titre, String contenu,int id_article) {
        
        this.titre = titre;
        this.contenu = contenu;
        this.id_article = id_article;
    }

    public Article(int id_article, String titre, String contenu, String dateCreation, String imagearticle) {
        this.id_article = id_article;
        this.titre = titre;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.imagearticle = imagearticle;
    }

    
   

    public Article(int id_article,String titre) {
    
         this.id_article = id_article;
        this.titre = titre;
       
    }

    public Article(int id_article, String titre, String dateCreation) {
        this.id_article = id_article;
        this.titre = titre;
        this.dateCreation = dateCreation;
       
    }

    public Article(String titre, String contenu,String imagearticle,String dateCreation) {
        this.titre = titre;
        this.contenu = contenu;
       
        this.imagearticle = imagearticle;
         this.dateCreation = dateCreation;
    }

   
    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getImagearticle() {
        return imagearticle;
    }

    public void setImagearticle(String imagearticle) {
        this.imagearticle = imagearticle;
    }
    
    

   
   
    


    
    
 
     public Article(String titre) {
    this.titre = titre;
      
              

    }

    public Article() {
       
       
    }


    public int getId() {
        return id_article;
    }

  
    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId(int id) {
        this.id_article = id;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "\nArticle {" + "\ntitre=" + titre + "\ncontenu=" + contenu + "\nid=" + id_article + "\ndateCreation=" + dateCreation + '}';
       
    }

    
    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (this.id_article != other.id_article) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        return true;
    }
    
    
    
}
