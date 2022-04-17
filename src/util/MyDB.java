/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class MyDB {
    private String url = "jdbc:mysql://localhost:3306/wlhfinal";
    private String username = "root";
    private String password = "";
    private Connection connection;
    static MyDB instance;

    private MyDB() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
