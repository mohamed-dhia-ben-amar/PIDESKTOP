/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hp
 */
public class Livraison {
    private int id;
    private String method;
    private int cinLivreur ;
    private int idClient ;
    private int idProd;
    private String adresseclient;
    private String etat ;

    public Livraison() {
    }

    public Livraison(String etat) {
        this.etat = etat;
    }
    

    public Livraison(String method, int cinLivreur, int idClient, int idProd, String adresseclient, String etat) {
        this.method = method;
        this.cinLivreur = cinLivreur;
        this.idClient = idClient;
        this.idProd = idProd;
        this.adresseclient = adresseclient;
        this.etat = etat;
    }

    public Livraison(int id, String method, int cinLivreur, int idClient, int idProd, String adresseclient, String etat) {
        this.id = id;
        this.method = method;
        this.cinLivreur = cinLivreur;
        this.idClient = idClient;
        this.idProd = idProd;
        this.adresseclient = adresseclient;
        this.etat = etat;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getCinLivreur() {
        return cinLivreur;
    }

    public void setCinLivreur(int cinLivreur) {
        this.cinLivreur = cinLivreur;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getAdresseclient() {
        return adresseclient;
    }

    public void setAdresseclient(String adresseclient) {
        this.adresseclient = adresseclient;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", method=" + method + ", cinLivreur=" + cinLivreur + ", idClient=" + idClient + ", idProd=" + idProd + ", adresseclient=" + adresseclient + ", etat=" + etat + '}';
    }

   
    
    
}
