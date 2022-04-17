/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Client;
import entities.Reclamation;
import entities.Reparation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

/**
 *
 * @author MSI
 */
public class ReparationService implements iserviceReclamation<Reclamation, Reparation, Client> {

    Connection connection;

    public ReparationService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public int ajouter(Reclamation r, Reparation rep, Client cl) {
        String req2 = "INSERT INTO Reparation "
                + "(delai) "
                + "VALUES (?)";
        try {
            PreparedStatement ps2 = connection.prepareStatement(req2,
                    Statement.RETURN_GENERATED_KEYS);
            ps2.setString(1, rep.getDelai());
            ps2.executeUpdate();

            ResultSet rs = ps2.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                return generatedKey;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;

    }

    public void modifier(Reparation r) {
        try {
            String req1 = "update Reparation"
                    + " set delai = ?";
            PreparedStatement ps4 = connection.prepareStatement(req1);
            ps4.setString(1, r.getDelai());
            ps4.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int idRec) {
        try {
            String req1 = "delete from Reparation"
                    + " where idRep = ?";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, idRec);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Reparation> recuperer2() {
        List<Reparation> list = new ArrayList<>();
        try {
            String req3 = "select * from Reparation";
            PreparedStatement st3 = connection.prepareStatement(req3);
            //st3.setInt(1, rs.getInt("idRep"));
            ResultSet rs3 = st3.executeQuery(req3);

            Reparation rep = new Reparation();
            while (rs3.next()) {
                rep.setIdRep(rs3.getInt("idRep"));
                rep.setDelai(rs3.getString("delai"));
                list.add(rep);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
        
    }

    @Override
    public void modifier(Reclamation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> recuperer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
