/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * , open the template in the editor.
 */
package services;

import entities.Livraison;
import entities.Livreur;
import util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author hp
 */
public class ServiceLivreur implements Iservice1<Livreur> {

    Connection connection;

    public ServiceLivreur() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Livreur t) {
        try {
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);

            String req1 = "insert into livreur(cin,name,prenom,rating,date_naissance,image,zone,tel) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, t.getCin());
            ps.setString(2, t.getName());
            ps.setString(3, t.getPrenom());
            ps.setFloat(4, t.getRating());
            ps.setDate(5, t.getDate_naissance());
            ps.setString(6, t.getImage());
            ps.setString(7, t.getZone());
            ps.setString(8, t.getTel());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Livreur t) {
        try {
            String req = "UPDATE  livreur SET   cin = ? , name = ? , prenom = ? , rating = ? , date_naissance = ? , image = ? , zone = ? , tel = ?  where cin = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, t.getCin());
            ps.setString(2, t.getName());
            ps.setString(3, t.getPrenom());
            ps.setFloat(4, t.getRating());
            ps.setDate(5, t.getDate_naissance());
            ps.setString(6, t.getImage());
            ps.setString(7, t.getZone());
            ps.setString(8, t.getTel());
            ps.setInt(9, t.getCin());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int cin) {
        try {
            String req = "delete from livreur where cin = ?";
            PreparedStatement st = connection.prepareStatement(req);
            st.setInt(1, cin);

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Livreur> recuperer() {
        List<Livreur> list = new ArrayList<>();
        try {
            String req = "select * from livreur";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Livreur l = new Livreur();
                l.setCin(rs.getInt("cin"));
                l.setName(rs.getString("name"));
                l.setPrenom(rs.getString(3));
                l.setRating(rs.getFloat("rating"));
                l.setDate_naissance(rs.getDate("date_naissance"));
                l.setImage(rs.getString("image"));
                l.setZone(rs.getString("zone"));
                l.setTel(rs.getString("tel"));
                list.add(l);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public Livreur GetByCin(int cin) {
        return recuperer().stream().filter(e -> e.getCin() == cin).findFirst().get();
    }

    public void updateLivreur(int cin, float l) {
        try {
            String req = "UPDATE  livreur SET  rating = ? where cin = ?";
            PreparedStatement ps = connection.prepareStatement(req);

            ps.setFloat(1, l);

            ps.setInt(2, cin);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
