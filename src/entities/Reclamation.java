/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author MSI
 */
public class Reclamation implements Serializable {

    private int idRec;
    private Client idClient;
    private Reparation idRep;
    private String date, description, etat, method_remb, target;

    public Reclamation(int idRec, Client idClient, Reparation idRep, String date, String description, String etat, String method_remb, String target) {
        this.idRec = idRec;
        this.idClient = idClient;
        this.idRep = idRep;
        this.date = date;
        this.description = description;
        this.etat = etat;
        this.method_remb = method_remb;
        this.target = target;
    }

    public Reclamation(Client idClient, Reparation idRep, String date, String description, String etat, String method_remb, String target) {
        this.idClient = idClient;
        this.idRep = idRep;
        this.date = date;
        this.description = description;
        this.etat = etat;
        this.method_remb = method_remb;
        this.target = target;
    }

    public Reclamation() {

    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Reparation getIdRep() {
        return idRep;
    }

    public void setIdRep(Reparation idRep) {
        this.idRep = idRep;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMethod_remb() {
        return method_remb;
    }

    public void setMethod_remb(String method_remb) {
        this.method_remb = method_remb;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "\nComplaint :"
                + " . \nDate Of Complaint : " + date
                + " . \nDescription Of The Complaint : " + description
                + " . \nState Of The Complaint : " + etat
                + " . \nMethod Of The Complaint : " + method_remb
                + " . \nTarget Of The Complaint : " + target;
    }

}
