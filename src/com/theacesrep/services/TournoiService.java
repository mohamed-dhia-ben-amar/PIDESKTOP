package com.theacesrep.services;

import com.theacesrep.entities.Tournoi;
import com.theacesrep.utils.DatabaseConnection;
import com.theacesrep.utils.RelationObject;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TournoiService {

    private static TournoiService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public TournoiService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static TournoiService getInstance() {
        if (instance == null) {
            instance = new TournoiService();
        }
        return instance;
    }

    public List<Tournoi> getAll() {
        List<Tournoi> listTournoi = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `tournoi` AS x RIGHT JOIN `client` AS y ON x.client_id = y.idClient WHERE x.client_id = y.idClient");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listTournoi.add(new Tournoi(
                        resultSet.getInt("id"),
                        new RelationObject(resultSet.getInt("client_id"), resultSet.getString("y.name")),
                        resultSet.getString("name"),
                        LocalDate.parse(String.valueOf(resultSet.getDate("date_debut"))),
                        LocalDate.parse(String.valueOf(resultSet.getDate("date_fin"))),
                        resultSet.getString("description"),
                        resultSet.getFloat("prix"),
                        resultSet.getInt("nbparticipant"),
                        resultSet.getString("image")
                        
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) tournoi : " + exception.getMessage());
        }
        return listTournoi;
    }
    
    public List<RelationObject> getAllClients() {
        List<RelationObject> listClients = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `client`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listClients.add(new RelationObject(resultSet.getInt("idClient"), resultSet.getString("name")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) clients : " + exception.getMessage());
        }
        return listClients;
    }
    
    public boolean checkExist(Tournoi tournoi) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `tournoi` WHERE `client_id` = ? AND `name` = ? AND `date_debut` = ? AND `date_fin` = ? AND `description` = ? AND `prix` = ? AND `nbparticipant` = ? AND `image` = ?");

            preparedStatement.setInt(1, tournoi.getClientId().getId());
            preparedStatement.setString(2, tournoi.getName());
            preparedStatement.setDate(3, Date.valueOf(tournoi.getDateDebut()));
            preparedStatement.setDate(4, Date.valueOf(tournoi.getDateFin()));
            preparedStatement.setString(5, tournoi.getDescription());
            preparedStatement.setFloat(6, tournoi.getPrix());
            preparedStatement.setInt(7, tournoi.getNbparticipant());
            preparedStatement.setString(8, tournoi.getImage());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException exception) {
            System.out.println("Error (getAll) sdp : " + exception.getMessage());
        }
        return false;
    }
    
    public boolean add(Tournoi tournoi) {
        
        if (checkExist(tournoi)) {
            return false;
        }
    
        String request = "INSERT INTO `tournoi`(`client_id`, `name`, `date_debut`, `date_fin`, `description`, `prix`, `nbparticipant`, `image`) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);
            
            preparedStatement.setInt(1, tournoi.getClientId().getId());
            preparedStatement.setString(2, tournoi.getName());
            preparedStatement.setDate(3, Date.valueOf(tournoi.getDateDebut()));
            preparedStatement.setDate(4, Date.valueOf(tournoi.getDateFin()));
            preparedStatement.setString(5, tournoi.getDescription());
            preparedStatement.setFloat(6, tournoi.getPrix());
            preparedStatement.setInt(7, tournoi.getNbparticipant());
            preparedStatement.setString(8, tournoi.getImage());
            
            preparedStatement.executeUpdate();
            System.out.println("Tournoi added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) tournoi : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Tournoi tournoi) {
        
        if (checkExist(tournoi)) {
            return false;
        }

        String request = "UPDATE `tournoi` SET `client_id` = ?, `name` = ?, `date_debut` = ?, `date_fin` = ?, `description` = ?, `prix` = ?, `nbparticipant` = ?, `image` = ? WHERE `id`=" + tournoi.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, tournoi.getClientId().getId());
            preparedStatement.setString(2, tournoi.getName());
            preparedStatement.setDate(3, Date.valueOf(tournoi.getDateDebut()));
            preparedStatement.setDate(4, Date.valueOf(tournoi.getDateFin()));
            preparedStatement.setString(5, tournoi.getDescription());
            preparedStatement.setFloat(6, tournoi.getPrix());
            preparedStatement.setInt(7, tournoi.getNbparticipant());
            preparedStatement.setString(8, tournoi.getImage());
            
            preparedStatement.executeUpdate();
            System.out.println("Tournoi edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) tournoi : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `tournoi` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Tournoi deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) tournoi : " + exception.getMessage());
        }
        return false;
    }
}
