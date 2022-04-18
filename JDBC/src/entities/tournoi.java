/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;
/**
 *
 * @author MSI
 */
public class tournoi {
    private int id;
    private int client_id;
    private String name;
  private String date_debut;
     private String date_fin;
      private String description;
      private float prix;
   
      private int nbparticipant;
      private String image;

    public tournoi(int id, String name) {
        this.id = id;
        this.name = name;
    }

      
      
      
      
    public tournoi(int client_id, String name, String date_debut, String date_fin, String description, float prix, int nbparticipant, String image) {
        this.client_id = client_id;
        this.name = name;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.prix = prix;
        this.nbparticipant = nbparticipant;
        this.image = image;
    }

   

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
      
      
      
      
      
      

    public tournoi(int id, String name, String date_debut, String date_fin, String description, int prix, int sponsors, int nbparticipant, String image) {
        this.id = id;
        this.name = name;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.prix = prix;
        this.nbparticipant = nbparticipant;
        this.image = image;
    }

    public tournoi(String name, String date_debut, String date_fin, String description, int prix, int nbparticipant, String image) {
        this.name = name;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.prix = prix;
      
        this.nbparticipant = nbparticipant;
        this.image = image;
    }

    public tournoi() {
    }

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

 
    

    public int getnbparticipant() {
        return nbparticipant;
    }

    public void setnbparticipant(int nbparticipant) {
        this.nbparticipant = nbparticipant;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "tournoi{" + "id=" + id + ", client_id=" + client_id + ", name=" + name + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", description=" + description + ", prix=" + prix + ", nbparticipant=" + nbparticipant + ", image=" + image + '}';
    }

    

   
    @Override
    public int hashCode() {
        int hash = 5;
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
        final tournoi other = (tournoi) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
       
        
        if (this.nbparticipant != other.nbparticipant) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
      
}
