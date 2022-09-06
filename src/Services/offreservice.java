/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.interfaceoffer;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.List;
import Models.offre; 
import java.sql.DriverManager;
import Utils.maConnexion;

import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class offreservice implements interfaceoffer 
         {
    Connection cnx = maConnexion.getInstance().getCnx();

    @Override
    public void ajouteroffre(offre o ) {
        String request = "INSERT INTO `offre`(`id_offre`, `text_offre`, `date_offre`, `type_offre`) VALUES ('"+o.getId_offre()+"','"+o.getText_offre()+"','"+o.getDate_offre()+"','"+o.getType_offre()+"')";
       try{
        Statement st = cnx.createStatement();
        st.executeUpdate(request);
                System.out.println("offre ajoutee avec succes");
                 } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void ajouteroffre2(offre o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<offre> afficheroffre() {
        List<offre> offres = new ArrayList<>();
        
        String query = "SELECT * FROM offre";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                offres.add(new offre(rs.getInt("id_offre"), rs.getString("text_offre"), rs.getString("date_offre"),rs.getString("type_offre")));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return offres;
    }
    
             public boolean modifieroffre(offre o){
                
                
 //String text_offre = "ireie"; 
 //String date_offre = "2015-03-31"; 
// String type_offre = "rjej"; 
// int id_offre = 52;
 

    
        try {
            
            
            String sql = "UPDATE offre SET text_offre=?, date_offre=?, type_offre=? WHERE id_offre=?";

            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1,o.getText_offre());
            st.setString(2,o.getDate_offre());
          
            st.setString(3,o.getType_offre());
            st.setInt(4,o.getId_offre());
            int rowsUpdated = st.executeUpdate();
            
            if (rowsUpdated > 0) {
            
                System.out.println("l'offre est modifiee");
            }
                   }
        catch (SQLException ex) {
                ex.printStackTrace();
 
        } return true;
            }
                   public void supprimeroffre(int id_offre){
                     //int id_offre = 245; 


  try {
            String sql = "Delete FROM offre WHERE id_offre=?";

            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, id_offre);

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("offre supprimee");
            }       }
        catch (SQLException ex) {
                ex.printStackTrace();
 
        }
            }

    }
    

