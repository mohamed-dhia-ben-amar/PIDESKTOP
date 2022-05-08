/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author hp
 */
public class Livreur {
      private int cin ;
    private String name;
    private String prenom;
    private float rating;
    private Date date_naissance;
    private String zone;
    private String image;
    private String tel;

    public Livreur(int cin, String name, String prenom, Date date_naissance, String zone, String image, String tel) {
        this.cin = cin;
        this.name = name;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.zone = zone;
        this.image = image;
        this.tel = tel;
    }

    public Livreur(int cin, String name, String prenom, float rating, Date date_naissance, String zone, String image, String tel) {
        this.cin = cin;
        this.name = name;
        this.prenom = prenom;
        this.rating = rating;
        this.date_naissance = date_naissance;
        this.zone = zone;
        this.image = image;
        this.tel = tel;
    }

    public Livreur() {
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Livreur{" + "cin=" + cin + ", name=" + name + ", prenom=" + prenom + ", rating=" + rating + ", date_naissance=" + date_naissance + ", zone=" + zone + ", image=" + image + ", tel=" + tel + '}';
    }
    
}
