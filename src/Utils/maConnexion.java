
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class maConnexion {
   
    //DB
    final static String URL = "jdbc:mysql://localhost/EFood";
    final static String USERNAME = "root";
    final static String PWD = "";
    
    //var
    //Singleton : 1
    static maConnexion instance = null;
    private Connection cnx;

    //Constructeur
    //Singleton : 2 (private)
    private maConnexion() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("Connexion successful");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //Getters 
    public Connection getCnx() {
        return cnx;
    }
    //Singleton : 3
    public static maConnexion getInstance() {
        if (instance == null) {
            instance = new maConnexion();
        }
        return instance;
    }
    
    
    
    
    
}