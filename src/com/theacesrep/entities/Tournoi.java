package com.theacesrep.entities;



import com.theacesrep.utils.RelationObject;

import java.time.LocalDate;

public class Tournoi {
    
    private int id;
    private RelationObject clientId;
    private String name;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    private float prix;
    private int nbparticipant;
    private String image;
    
    public Tournoi(int id, RelationObject clientId, String name, LocalDate dateDebut, LocalDate dateFin, String description, float prix, int nbparticipant, String image) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.prix = prix;
        this.nbparticipant = nbparticipant;
        this.image = image;
    }

    public Tournoi(RelationObject clientId, String name, LocalDate dateDebut, LocalDate dateFin, String description, float prix, int nbparticipant, String image) {
        this.clientId = clientId;
        this.name = name;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.prix = prix;
        this.nbparticipant = nbparticipant;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public RelationObject getClientId() {
        return clientId;
    }

    public void setClientId(RelationObject clientId) {
        this.clientId = clientId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
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
    
    public int getNbparticipant() {
        return nbparticipant;
    }

    public void setNbparticipant(int nbparticipant) {
        this.nbparticipant = nbparticipant;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    
}