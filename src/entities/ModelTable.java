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
public class ModelTable {
    private String date, description, etat, method_remb, target;

    public ModelTable(String date, String description, String etat, String method_remb, String target) {
        this.date = date;
        this.description = description;
        this.etat = etat;
        this.method_remb = method_remb;
        this.target = target;
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
    
    
}
