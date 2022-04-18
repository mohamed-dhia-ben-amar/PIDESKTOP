/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MSI
 */
public class sponsor {
    private int id;
    private int tournoi_id;
    private String name;
    private String logo;

   
    public int getTournoi_id() {
        return tournoi_id;
    }

    public void setTournoi_id(int tournoi_id) {
        this.tournoi_id = tournoi_id;
    }

    public sponsor(int id, int tournoi_id, String name, String logo) {
        this.id = id;
        this.tournoi_id = tournoi_id;
        this.name = name;
        this.logo = logo;
    }

   

    public sponsor(String name,int tournoi_id,int id) {
          this.name = name;
           this.tournoi_id = tournoi_id;
        this.id = id;
       
      
    }

    public sponsor(int tournoi_id, String name, String logo) {
        this.tournoi_id = tournoi_id;
        this.name = name;
        this.logo = logo;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

  

    public sponsor(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    public sponsor() {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "sponsor{" + "id=" + id + ", tournoi_id=" + tournoi_id + ", name=" + name + ", logo=" + logo + '}';
    }

   
     
}
