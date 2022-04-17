/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import util.MyDB;

/**
 *
 * @author MSI
 */
public class ClientService implements iservice<Client> {

    Connection connection;

    public ClientService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public int ajouter(Client cl) {
        String req0 = "INSERT INTO client "
                + "(name, prenom, age, adresse, mail, phone, login, mdp, image) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            //Add Client
            PreparedStatement ps0 = connection.prepareStatement(req0,
                    Statement.RETURN_GENERATED_KEYS);
            ps0.setString(1, cl.getName());
            ps0.setString(2, cl.getPrenom());
            ps0.setInt(3, cl.getAge());
            ps0.setString(4, cl.getAdresse());
            ps0.setString(5, cl.getMail());
            ps0.setInt(6, cl.getPhone());
            ps0.setString(7, cl.getLogin());
            ps0.setString(8, cl.getMdp());
            ps0.setString(9, cl.getImage());
            ps0.executeUpdate();
            ResultSet rs = ps0.getGeneratedKeys();
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
        return 0;
    }

    @Override
    public void modifier(Client t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int idRec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Client> recuperer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
