/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entity.Element;
import Utils.My_Connexion;
import static Utils.My_Connexion.getInstance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class ElementService implements IService<Element> {
    
    private Connection connection;
    
    private Statement ste;
    private PreparedStatement pste;
    private ResultSet rs;
    
    public ElementService() {
        
         connection = My_Connexion.getInstance().getCnx() ;  
    }

    @Override
    public void ajouter(Element e) {
       
        
      try {
          if((e.getPrix()>0)&&(e.getQuantite()>0)){
             if((e.getPromotion_id()==0)) {
            String req = "INSERT INTO `element` (  type , ref ,  nom , prix , description , image , etat , quantite  ) VALUES (?,?,?,?,?,?,?,?)";
            pste = connection.prepareStatement(req);

            pste.setString(1, e.getType());
            pste.setString(2, e.getRef());
            pste.setString(3, e.getNom());
            pste.setFloat(4, e.getPrix());
            pste.setString(5, e.getDescription());
            pste.setString(6, e.getImage());
            pste.setString(7, e.getEtat());
            pste.setInt(8, e.getQuantite());           
                  
          } 
            else {
             String req = "INSERT INTO `element` (  type , ref ,  nom , prix , description , image , etat , quantite , promotion_id  ) VALUES (?,?,?,?,?,?,?,?,?)";
            pste = connection.prepareStatement(req);

            pste.setString(1, e.getType());
            pste.setString(2, e.getRef());
            pste.setString(3, e.getNom());
            pste.setFloat(4, e.getPrix());
            pste.setString(5, e.getDescription());
            pste.setString(6, e.getImage());
            pste.setString(7, e.getEtat());
            pste.setInt(8, e.getQuantite());
            pste.setInt(9, e.getPromotion_id());
                
          }

            pste.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Element Ajoutée");
            
            System.out.println("Une ligne insérée dans la table Element...");

            System.out.println("Element Ajoutée ");
          }
          
          else{
              
              JOptionPane.showMessageDialog(null, "Champs invalide");
            
                System.out.println("champ invalide...");

              
          }
        } catch (SQLException ex) {
            Logger.getLogger(ElementService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public List<Element> afficher() {
  
        List<Element> Elements = new ArrayList<>();

        try {
            String req = " SELECT * from element ";
            
            ste = connection.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                Element e = new Element();
                e.setId(rs.getInt("id"));
                e.setRef(rs.getString("ref"));
                e.setType(rs.getString("type"));
                e.setNom(rs.getString("nom"));
                e.setPrix(rs.getFloat("prix"));
                e.setDescription(rs.getString("description"));
                e.setImage(rs.getString("image"));
                e.setEtat(rs.getString("etat"));
                e.setQuantite(rs.getInt("quantite"));
                e.setPromotion_id(rs.getInt("promotion_id"));

                Elements.add(e);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return Elements;
    }  
    



    @Override
    public void modifier(Element t) {
    try{
        if((t.getPrix()>0)&&(t.getQuantite()>0)){
             if((t.getPromotion_id()!=0)) {
            String req = "UPDATE element SET type = ?, ref = ?, nom = ?, prix = ?, description = ?, image = ?, etat = ?, quantite = ?, promotion_id = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            System.out.println("Modification...");
            ps.setString(1,t.getType());
            ps.setString(2,t.getRef());
            ps.setString(3,t.getNom());

            ps.setFloat(4,t.getPrix());
            ps.setString(5,t.getDescription());
            ps.setString(6,t.getImage());
            ps.setString(7,t.getEtat());
            ps.setInt(8,t.getQuantite());
            ps.setInt(9,t.getPromotion_id());
            
            

                        
            ps.setInt(10,t.getId());
            ps.executeUpdate();
            System.out.println("Une ligne modifiée dans la table Element");
            JOptionPane.showMessageDialog(null, "Element modifié ");
       }else{
            
                 String req = "UPDATE element SET type = ?, ref = ?, nom = ?, prix = ?, description = ?, image = ?, etat = ?, quantite = ? = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            System.out.println("Modification...");
            ps.setString(1,t.getType());
            ps.setString(2,t.getRef());
            ps.setString(3,t.getNom());

            ps.setFloat(4,t.getPrix());
            ps.setString(5,t.getDescription());
            ps.setString(6,t.getImage());
            ps.setString(7,t.getEtat());
            ps.setInt(8,t.getQuantite());             
            ps.setInt(9,t.getId());
            ps.executeUpdate();
            System.out.println("Une ligne modifiée dans la table Element");
            JOptionPane.showMessageDialog(null, "Element modifié ");

                 
                 
                 
             }
        }else{
             JOptionPane.showMessageDialog(null, "Champs invalide");
            
                System.out.println("champ invalide...");


            
        }
    }
       catch(SQLException e){
           System.out.println(e.getMessage());
       }    }

    
    @Override
    public void supprimer(Element t) {
   try {
        String req = "DELETE FROM element WHERE id=? " ;
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,t.getId());
         ps.executeUpdate() ; 
        JOptionPane.showMessageDialog(null, "Element supprimé ");
        } 
   catch (SQLException ex) {
            
            Logger.getLogger(ElementService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
}
