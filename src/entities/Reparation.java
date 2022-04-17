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
public class Reparation implements Serializable{
    private int idRep;
    private String delai;

    public Reparation() {
    }

    public Reparation(int idRep, String delai) {
        this.idRep = idRep;
        this.delai = delai;
    }

    public Reparation(String delai) {
        this.delai = delai;
    }

    public int getIdRep() {
        return idRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }

    public String getDelai() {
        return delai;
    }

    public void setDelai(String delai) {
        this.delai = delai;
    }

    @Override
    public String toString() {
        return "\n********************" +
                "\nReparation :"  
                + " \nID Du Reparation : " + idRep
                + " . \nDelai Du Reparation : " + delai;
    }
    
    
}
