/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Models.coupouns;
import Interfaces.interfacecoupouns;
import Utils.maConnexion;

/**
 *
 * @author LENOVO
 */
public class coupounsservice implements interfacecoupouns
        {
     Connection cnx = maConnexion.getInstance().getCnx();
     public void ajoutercoupouns(coupouns c  ) {
        String request = "INSERT INTO `coupouns`(`id_coupouns`, `text_coupouns`, `code_coupouns`) VALUES ('"+c.getId_coupouns()+"','"+c.getText_coupouns()+"','"+c.getCode_coupouns()+"')";
       try{
        Statement st = cnx.createStatement();
        st.executeUpdate(request);
                System.out.println("coupouns ajoutee avec succes");
                 } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
      public List<coupouns> affichercoupouns() {
        List<coupouns> coupoun = new ArrayList<>();
        
        String query = "SELECT * FROM coupouns";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                coupoun.add(new coupouns(rs.getInt("id_coupouns"), rs.getString("text_coupouns"), rs.getString("code_coupouns")));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return coupoun;
    }
         public boolean modifiercoupouns(coupouns c){
                
                
 //String text_coupouns = "texttee"; 
 //String code_coupouns = "code23432";  
 //int id_coupouns = 23;
 

    
        try {
            
            
            String sql = "UPDATE coupouns SET text_coupouns=?, code_coupouns=? WHERE id_coupouns=?";

            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1,c.getCode_coupouns());
            st.setString(2,c.getText_coupouns());
            st.setInt(3,c.getId_coupouns());
            int rowsUpdated = st.executeUpdate();
            
            if (rowsUpdated > 0) {
            
                System.out.println("coupouns est modifiee");
            }
                   }
        catch (SQLException ex) {
                ex.printStackTrace();
 
        }
        return true;
            }
            
    public void supprimercoupouns(int id_coupouns){
                     //int id_coupouns = 2; 


  try {
            String sql = "Delete FROM coupouns WHERE id_coupouns=?";

            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, id_coupouns);

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("coupouns supprimee");
            }       }
        catch (SQLException ex) {
                ex.printStackTrace();
 
        }
            }

}
