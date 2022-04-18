/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.tournoi;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utils.My_Connexion;

/**
 *
 * @author MSI
 */
public class tournoiCrud {
    
     public List<tournoi> afficherTournoi (){
        
            List <tournoi> mylist =  new ArrayList<>() ;
        try {
             String req3 = "SELECT * FROM tournoi " ;
            Statement st = new My_Connexion().getCnx().createStatement()  ; 
            ResultSet rs = st.executeQuery(req3) ;
            while (rs.next()){
                tournoi t = new tournoi () ; 
                t.setId(rs.getInt(1));
                t.setClient_id(2);
                 t.setName(rs.getString("Name"));
                t.setDate_debut(rs.getString("Date_debut"));
                t.setDate_fin(rs.getString("Date_fin"));
                t.setDescription(rs.getString("Description")); 
                t.setPrix(rs.getFloat("Prix"));
                 t.setnbparticipant(rs.getInt("nbparticipant"));
                t.setImage(rs.getString("Image"));
                mylist.add(t) ; 
                  
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mylist ; 
     }
    
    
    
    
    

    public void ajouterTournoi( tournoi t)
    {
       
       try { 
            String req2 = " INSERT INTO tournoi (name,date_debut,date_fin,description,prix,nbparticipant,image) VALUES ('" + t.getName()+ "','" + t.getDate_debut()+"','" + t.getDate_fin()+ t.getDescription()+ "','" + t.getPrix()+"','" + t.getnbparticipant()+t.getImage()+"')";
            PreparedStatement pst = new My_Connexion().getCnx().prepareStatement(req2) ;
            pst.executeUpdate() ; 
         //    PreparedStatement pst1 = new My_Connexion().getCnx().prepareStatement(req3) ;
           // pst1.setString(1,A.getContenu());
            //pst1.executeUpdate() ; 
        
            System.out.println("Article added successfuly");
            JOptionPane.showMessageDialog(null, "Article added");
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
         JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    
         
      public void Modifiertournoi (tournoi t)
    {
        try {
            String req3 = "UPDATE tournoi SET name= ? where id= ?" ;
            PreparedStatement pst = new My_Connexion().getCnx().prepareStatement(req3) ;
         
            pst.setString(1,t.getName());
             pst.setInt(2,t.getId());

           
           
            pst.executeUpdate() ;
             System.out.println("Your article has been modified ");
         JOptionPane.showMessageDialog(null, "Article modified ");
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             JOptionPane.showMessageDialog(null, ex);
        }
    }
    
  
    public void Supprimertournoi (int id)
    {
        
       try {
           String req3 = "DELETE FROM tournoi where id= ?" ;
            PreparedStatement pst2 = new My_Connexion().getCnx().prepareStatement(req3) ;
                   pst2.setInt(1,id);
                   pst2.executeUpdate();
                   System.out.println("votre tournoi a été effacé");
                   JOptionPane.showMessageDialog(null,"tournoi supprimé");
                   
                   
                   
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
           JOptionPane.showMessageDialog(null,"tournoi a un commentaire");
                   
       } 
        
        
        
        
        
    }    
    
    
    
    
    
}
