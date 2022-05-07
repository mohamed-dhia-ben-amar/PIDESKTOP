package com.theacesrep.services;

import com.theacesrep.entities.Sponsor;
import com.theacesrep.utils.DatabaseConnection;
import com.theacesrep.utils.RelationObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SponsorService {

    private static SponsorService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public SponsorService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static SponsorService getInstance() {
        if (instance == null) {
            instance = new SponsorService();
        }
        return instance;
    }

    public List<Sponsor> getAll() {
        List<Sponsor> listSponsor = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `sponsor` AS x RIGHT JOIN `tournoi` AS y ON x.tournoi_id = y.id WHERE x.tournoi_id = y.id");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listSponsor.add(new Sponsor(
                        resultSet.getInt("id"),
                        new RelationObject(resultSet.getInt("tournoi_id"), resultSet.getString("y.name")),
                        resultSet.getString("name"),
                        resultSet.getString("logo")
                        
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) sponsor : " + exception.getMessage());
        }
        return listSponsor;
    }
    
    public List<RelationObject> getAllTournois() {
        List<RelationObject> listTournois = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `tournoi`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listTournois.add(new RelationObject(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) tournois : " + exception.getMessage());
        }
        return listTournois;
    }
    
    public boolean checkExist(Sponsor sponsor) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `sponsor` WHERE `tournoi_id` = ? AND `name` = ? AND `logo` = ?");

            preparedStatement.setInt(1, sponsor.getTournoiId().getId());
            preparedStatement.setString(2, sponsor.getName());
            preparedStatement.setString(3, sponsor.getLogo());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException exception) {
            System.out.println("Error (getAll) sdp : " + exception.getMessage());
        }
        return false;
    }
    
    public boolean add(Sponsor sponsor) {
        
        if (checkExist(sponsor)) {
            return false;
        }
    
        String request = "INSERT INTO `sponsor`(`tournoi_id`, `name`, `logo`) VALUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);
            
            preparedStatement.setInt(1, sponsor.getTournoiId().getId());
            preparedStatement.setString(2, sponsor.getName());
            preparedStatement.setString(3, sponsor.getLogo());
            
            preparedStatement.executeUpdate();
            System.out.println("Sponsor added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) sponsor : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Sponsor sponsor) {
        
        if (checkExist(sponsor)) {
            return false;
        }

        String request = "UPDATE `sponsor` SET `tournoi_id` = ?, `name` = ?, `logo` = ? WHERE `id`=" + sponsor.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, sponsor.getTournoiId().getId());
            preparedStatement.setString(2, sponsor.getName());
            preparedStatement.setString(3, sponsor.getLogo());
            
            preparedStatement.executeUpdate();
            System.out.println("Sponsor edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) sponsor : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `sponsor` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Sponsor deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) sponsor : " + exception.getMessage());
        }
        return false;
    }
}
