/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author hp
 */
public class ServiceLivraison implements Iservice1<Livraison> {

    Connection connection;

    public ServiceLivraison() {

        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Livraison t) {

        try {
            String req = "insert into livraison(method,cinLivreur,idClient,idProd,adresseclient,etat) values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, t.getMethod());
            ps.setInt(2, t.getCinLivreur());
            ps.setInt(3, t.getIdClient());
            ps.setInt(4, t.getIdProd());
            ps.setString(5, t.getAdresseclient());
            ps.setString(6, t.getEtat());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Livraison t) {

        try {
            String req = "UPDATE livraison set etat = ? WHERE id= ?";
            PreparedStatement ps = connection.prepareStatement(req);

            ps.setString(1, t.getEtat());
            ps.setInt(2, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from livraison WHERE id= ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Livraison> recuperer() {
        List<Livraison> list = new ArrayList<>();
        try {
            String req = "select * from livraison";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Livraison l = new Livraison();
                l.setId(rs.getInt(1));
                l.setMethod(rs.getString(2));
                l.setCinLivreur(rs.getInt(3));
                l.setIdClient(rs.getInt(4));
                l.setIdProd(rs.getInt(5));
                l.setAdresseclient(rs.getString(6));
                l.setEtat(rs.getString(7));
                list.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Livraison GetById(int id) {
        return recuperer().stream().filter(e -> e.getId() == id).findFirst().get();
    }

    public List<Integer> getCinL() {
        List<Livreur> l = new ArrayList();
        List<Integer> cin = new ArrayList<>();
        ServiceLivreur sl = new ServiceLivreur();
        l = sl.recuperer();
        for (int i = 0; i < l.size(); i++) {
            cin.add(l.get(i).getCin());
        }
        return cin;
    }

}
