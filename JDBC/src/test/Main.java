/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.sponsor;
import entities.tournoi;
import services.ServiceSponsor;

import services.tournoiCrud;
import utils.My_Connexion;

/**
 *
 * @author amal
 */
public class Main {
    
    public static void main(String[] args) {
        
    My_Connexion mc = new My_Connexion () ; 
    
    tournoiCrud t =new tournoiCrud();
    
    t.afficherTournoi();
        System.out.println( t.afficherTournoi());
        
//        tournoi t3 = new tournoi( 7,"amal","2022-03-15","2023-03-15","2",10.2f, 100,"aycha.png");
//    
//    t.ajouterTournoi(t3);
    
    
     java.time.LocalDate.now();
     
 
    // t.Modifiertournoi(new tournoi(1,"TESTER"));
     
 //  t.Supprimertournoi(3);
    
    
    ServiceSponsor s = new ServiceSponsor();
    s.getAll();
    System.out.println(s.getAll());
    
//    sponsor s2 = new sponsor (1,"test","logo");    
//    s.add(s2);
//    s.update(new sponsor("TESTER",1,3));
    
    s.delete(3);
    
    
    
    
    
    
    
    
   //tournoiCrud t = new tournoiCrud();
   //t.affichertournoi();
    //System.out.println(t.affichertournoi());
    //t.Modifiertournoi(t);

           // tournoi t= new tournoi(0, name, date_debut, date_fin, description, 0, 0, 0, image);
       // sponsorCrud s = new sponsorCrud();
        //s.affichersponsor();
        //System.out.println(s.affichersponsor());
        
      
         //java.time.LocalDate.now();
        //tournoiCrud tr = new tournoiCrud();
        //tournoi t = new tournoi("amal", "hbdzsuy", "hjdvfufg", "bdfvu", 0, 0, 0, "jhfbu");
        //tr.ajouterTournoi(t);
       
    }
    
}
