/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author MSI
 */
public interface iserviceReclamation<Rec,Rep,Client> {
    public int ajouter(Rec r, Rep rep, Client cl);

    public void modifier(Rec r);

    public void supprimer(int idRec);

    public List<Rec> recuperer();
}
