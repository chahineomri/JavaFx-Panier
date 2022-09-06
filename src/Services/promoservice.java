/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Interfaces.Ipromo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import Models.promo;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Models.offre;
import Utils.maConnexion;

/**
 *
 * @author LENOVO
 */
public class promoservice implements Ipromo {
    Connection cnx = maConnexion.getInstance().getCnx();
     @Override
        public void ajouterpromo(promo p ) {
        String request = "INSERT INTO `promo`(`id_promo`,`nom_promo`,`type_promo`,`date_d`,`date_f`,`id_resto`,`text_promo`) VALUES ('"+p.getId_promo()+"','"+p.getNom_promo()+"','"+p.getType_promo()+"','"+p.getDate_d()+"','"+p.getDate_f()+"','"+p.getId_resto()+"','"+p.getText_promo()+"')";
       try{
        Statement st = cnx.createStatement();
            st.executeUpdate(request);
                System.out.println("promo ajoutee avec succes");
                 } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    public List<promo> afficherpromo() {
        List<promo> promos = new ArrayList<>();
        
        String query = "SELECT * FROM promo";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                promos.add(new promo(rs.getInt("id_promo"), rs.getString("nom_promo"),rs.getInt("type_promo") ,rs.getString("date_d"),rs.getString("date_f"),rs.getInt("id_resto"),rs.getString("text_promo")));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return promos;
    }
     public boolean modifierpromo(promo p){
                
                
 //String text_offre = "ireie"; 
 //String date_offre = "2015-03-31"; 
// String type_offre = "rjej"; 
// int id_offre = 52;
 

    
        try {
            
            
            String sql = "UPDATE promo SET nom_promo=?, type_promo=?, date_d=?, date_f=?, id_resto=?, text_promo=? WHERE id_promo=?";

            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1,p.getNom_promo());
            st.setInt(2,p.getType_promo());
            st.setString(3,p.getDate_d());
            st.setString(4,p.getDate_f());
            st.setInt(5,p.getId_resto());
            st.setString(6,p.getText_promo());
            
            st.setInt(7,p.getId_promo());
            int rowsUpdated = st.executeUpdate();
            
            if (rowsUpdated > 0) {
            
                System.out.println("promo est modifiee");
            }
                   }
        catch (SQLException ex) {
                ex.printStackTrace();
 
        } return true;
            }
             public void supprimerpromo(int id_promo){
                     //int id_offre = 245; 


  try {
            String sql = "Delete FROM promo WHERE id_promo=?";

            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, id_promo);

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("promo supprimee");
            }       }
        catch (SQLException ex) {
                ex.printStackTrace();
 
        }
            }

}
