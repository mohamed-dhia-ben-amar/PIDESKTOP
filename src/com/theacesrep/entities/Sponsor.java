package com.theacesrep.entities;


import com.theacesrep.utils.RelationObject;

public class Sponsor {
    
    private int id;
    private RelationObject tournoiId;
    private String name;
    private String logo;
    
    public Sponsor(int id, RelationObject tournoiId, String name, String logo) {
        this.id = id;
        this.tournoiId = tournoiId;
        this.name = name;
        this.logo = logo;
    }

    public Sponsor(RelationObject tournoiId, String name, String logo) {
        this.tournoiId = tournoiId;
        this.name = name;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public RelationObject getTournoiId() {
        return tournoiId;
    }

    public void setTournoiId(RelationObject tournoiId) {
        this.tournoiId = tournoiId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    

    
}