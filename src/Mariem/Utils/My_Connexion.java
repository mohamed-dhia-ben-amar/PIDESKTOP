/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mariem.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author msi
 */
public class My_Connexion {
    
    public String url="jdbc:mysql://localhost:3306/wlhfinal" ; 
    public String login="root" ; 
    public String pwd="" ; 
    Connection cnx ; 
    static My_Connexion instance;
    public My_Connexion() {
        
      try { 
            cnx = DriverManager.getConnection(url, login, pwd) ;
            System.out.println("connnexion etablie !! ");
        } catch (SQLException ex) {
           System.out.print(ex.getMessage()) ; 
        }
    }
    
    public static My_Connexion getInstance(){
        
        if (instance == null)
            instance = new My_Connexion();
            
            return instance;
        
    }

      public Connection getCnx() {
        
        return cnx ;         
    }
      
      
      
  
 
        
      
    }
    
    
    
    
    
    
    
    

