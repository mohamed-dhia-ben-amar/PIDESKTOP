/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aces.entities;

/**
 *
 * @author aycha
 */
public class Commentaire {

  
    
     private int id;
      private int article_id;
     private String contenu;

    public Commentaire(int id, int article_id, String contenu) {
        this.id = id;
        this.article_id = article_id;
        this.contenu = contenu;
    }

    public Commentaire(int article_id, String contenu) {
        this.article_id = article_id;
        this.contenu = contenu;
    }

   

    public int getArticle_id() {
        return article_id;
    }

    public int getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "article_id=" + article_id  + ", contenu=" + contenu + '}';
    }

   

}
