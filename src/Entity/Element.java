/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author PC
 */
public class Element {

    private int id, quantite,promotion_id;
    private String type, ref, nom, description, etat;
    private String image;
    private float prix;

    public Element(int id, int quantite, int promotion_id, String type, String ref, String nom, String description, String etat, String image, float prix) {
        this.id = id;
        this.quantite = quantite;
        this.promotion_id = promotion_id;
        this.type = type;
        this.ref = ref;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.image = image;
        this.prix = prix;
    }
    
    


    public Element() {
    }

    public Element(int id, int quantite, String type, String ref, String nom, String description, String etat, String image, float prix) {
        this.id = id;
        this.quantite = quantite;
        this.type = type;
        this.ref = ref;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.image = image;
        this.prix = prix;
    }

    public Element(int qte, String type, String ref, String nomE, String desc, String etat, String image2, float prix, int id_c) {
        this.quantite = qte;
        this.type = type;
        this.ref = ref;
        this.nom = nomE;
        this.description = desc;
        this.etat = etat;
        this.image = image2;
        this.prix = prix;   
        this.promotion_id=id_c;
        }

    public Element(int id_c, int id_p) {
        
    this.id=id_c;
    this.promotion_id=id_p;
    }



    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getType() {
        return type;
    }

    public String getRef() {
        return ref;
    }
        public int getPromotion_id() {
        return promotion_id;
    }

    public void setPromotion_id(int promotion_id) {
        this.promotion_id = promotion_id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

    public String getImage() {
        return image;
    }

    public float getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Element{" + "id=" + id + ", quantite=" + quantite + ", promotion_id=" + promotion_id + ", type=" + type + ", ref=" + ref + ", nom=" + nom + ", description=" + description + ", etat=" + etat + ", image=" + image + ", prix=" + prix + '}';
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
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
        final Element other = (Element) obj;
        return this.id == other.id;
    }
       
 
    
}
