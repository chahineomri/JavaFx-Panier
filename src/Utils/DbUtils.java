/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Models.User;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author fomri
 */
public class DbUtils {

    private Connection conn;
    private static DbUtils INSTANCE;
    public final static String VLIDATED_STATUS = "Valid";

    public static DbUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DbUtils();
        }
        return INSTANCE;
    }

    public DbUtils() {
    }
    public Connection getConnection(){
        try {
               Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/chahine", "root", "");
            return conn;    
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }
        return conn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    

}
