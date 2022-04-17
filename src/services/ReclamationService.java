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
import java.sql.JDBCType;
import static java.sql.JDBCType.INTEGER;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyDB;

/**
 *
 * @author MSI
 */
public class ReclamationService implements iserviceReclamation<Reclamation, Reparation, Client> {

    Connection connection;

    public ReclamationService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public int ajouter(Reclamation r, Reparation rep, Client cl) {
        try {
            //Add Complaint
            String req1 = "insert into Reclamation(description,date,etat,"
                    + "method_remb,target,idClient,idRep) "
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, r.getDescription());
            ps.setString(2, r.getDate());
            ps.setString(3, r.getEtat());
            ps.setString(4, r.getMethod_remb());
            ps.setString(5, r.getTarget());
            ps.setObject(6, cl.getIdClient());
            ps.setObject(7, rep.getIdRep());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public void modifier(Reclamation r) {
        try {
            String req1 = "update Reclamation"
                    + " set description = ?, "
                    + "date = ?, "
                    + "etat = ?, "
                    + "method_remb = ?, "
                    + "target = ?, "
                    + "idClient = ?, "
                    + "idRep = ? "
                    + "where idRec = ?";
            PreparedStatement ps4 = connection.prepareStatement(req1);
            ps4.setString(1, r.getDescription());
            ps4.setString(2, r.getDate());
            ps4.setString(3, r.getEtat());
            ps4.setString(4, r.getMethod_remb());
            ps4.setString(5, r.getTarget());
            ps4.setObject(6, r.getIdClient());
            ps4.setObject(7, r.getIdRep());
            ps4.setInt(8, r.getIdRec());
            ps4.executeUpdate();

            if (r.getMethod_remb() != "Reparation") {
                String req = "delete from Reparation where idRep = " + r.getIdRep().getIdRep() + "";
                Statement st = connection.createStatement();
                st.executeUpdate(req);
                Reparation rep = new Reparation();
                r.setIdRep(rep);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int idRec) {
        try {
            String req1 = "delete from Reclamation"
                    + " where idRec = ?";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, idRec);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Reclamation> recuperer() {
        List<Reclamation> list = new ArrayList<>();
        try {

            String req = "select * from Reclamation";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setIdRec(rs.getInt("idRec"));
                r.setDescription(rs.getString("description"));
                r.setDate(rs.getString("date"));
                r.setEtat(rs.getString("etat"));
                r.setMethod_remb(rs.getString("method_remb"));
                r.setTarget(rs.getString("target"));

                String req2 = "select * from Client where idClient = " + rs.getInt("idClient") + "";
                PreparedStatement st2 = connection.prepareStatement(req2);
                //st2.setInt(1, rs.getInt("idClient"));
                ResultSet rs2 = st2.executeQuery(req2);

                Client c = new Client();
                while (rs2.next()) {
                    c.setIdClient(rs2.getInt("idClient"));
                    c.setName(rs2.getString("name"));
                    c.setPrenom(rs2.getString("prenom"));
                    c.setAge(rs2.getInt("age"));
                    c.setAdresse(rs2.getString("adresse"));
                    c.setMail(rs2.getString("mail"));
                    c.setPhone(rs2.getInt("phone"));
                    c.setLogin(rs2.getString("login"));
                    c.setMdp(rs2.getString("mdp"));
                }
                r.setIdClient(c);

                String req3 = "select * from Reparation where idRep = " + rs.getInt("idRep") + "";
                PreparedStatement st3 = connection.prepareStatement(req3);
                //st3.setInt(1, rs.getInt("idRep"));
                ResultSet rs3 = st3.executeQuery(req3);

                Reparation rep = new Reparation();
                while (rs3.next()) {
                    rep.setIdRep(rs3.getInt("idRep"));
                    rep.setDelai(rs3.getString("delai"));
                }
                r.setIdRep(rep);
                list.add(r);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<Reclamation> search(String text) {

        List<Reclamation> list = new ArrayList<>();
        try {

            String req = "select * from Reclamation WHERE description LIKE '" + text + "' OR target LIKE '" + text + "' OR method_remb LIKE '" + text + "' OR etat LIKE '" + text + "';";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setIdRec(rs.getInt("idRec"));
                r.setDescription(rs.getString("description"));
                r.setDate(rs.getString("date"));
                r.setEtat(rs.getString("etat"));
                r.setMethod_remb(rs.getString("method_remb"));
                r.setTarget(rs.getString("target"));

                String req2 = "select * from Client where idClient = " + rs.getInt("idClient") + "";
                PreparedStatement st2 = connection.prepareStatement(req2);
                //st2.setInt(1, rs.getInt("idClient"));
                ResultSet rs2 = st2.executeQuery(req2);

                Client c = new Client();
                while (rs2.next()) {
                    c.setIdClient(rs2.getInt("idClient"));
                    c.setName(rs2.getString("name"));
                    c.setPrenom(rs2.getString("prenom"));
                    c.setAge(rs2.getInt("age"));
                    c.setAdresse(rs2.getString("adresse"));
                    c.setMail(rs2.getString("mail"));
                    c.setPhone(rs2.getInt("phone"));
                    c.setLogin(rs2.getString("login"));
                    c.setMdp(rs2.getString("mdp"));
                }
                r.setIdClient(c);

                String req3 = "select * from Reparation where idRep = " + rs.getInt("idRep") + "";
                PreparedStatement st3 = connection.prepareStatement(req3);
                //st3.setInt(1, rs.getInt("idRep"));
                ResultSet rs3 = st3.executeQuery(req3);

                Reparation rep = new Reparation();
                while (rs3.next()) {
                    rep.setIdRep(rs3.getInt("idRep"));
                    rep.setDelai(rs3.getString("delai"));
                }
                r.setIdRep(rep);
                list.add(r);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }

    public int SelectPending() throws SQLException {

        String req = "SELECT COUNT(*) FROM reclamation where etat like 'Pending'";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        rs.next();
        int count = rs.getInt(1);

        return count;
    }

    public int SelectTreated() throws SQLException {

        String req = "SELECT COUNT(*) FROM reclamation where etat like 'Treated'";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);

        rs.next();
        int count = rs.getInt(1);

        return count;
    }

}
