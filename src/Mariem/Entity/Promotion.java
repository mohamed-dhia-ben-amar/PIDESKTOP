/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mariem.Entity;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Promotion {

    public int getId() {
        return id;
    }

    public String getDate_deb() {
        return date_deb;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public Promotion(String date_deb, String date_fin, float pourcentage) {
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.pourcentage = pourcentage;
    }

    public Promotion(int id, String date_deb, String date_fin, float pourcentage) {
        this.id = id;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.pourcentage = pourcentage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }


    
    
    private int id ;
    private String  date_deb , date_fin ;
    private float pourcentage  ; 

    public Promotion(int id, String date_deb, String date_fin) {
        this.id = id;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }

    public Promotion(int id, String date_deb, float pourcentage) {
        this.id = id;
        this.date_deb = date_deb;
        this.pourcentage = pourcentage;
    }

    public Promotion() {
    }
    
    
         @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", pourcentage=" + pourcentage + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final Promotion other = (Promotion) obj;
        return this.id == other.id;
    }

 


    
}
