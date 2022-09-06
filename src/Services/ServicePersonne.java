/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Interfaces.Ipersonne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Models.Personne;
import Utils.maConnexion;

/**
 *
 * @author Fayechi
 */
public class ServicePersonne implements Ipersonne{
    
    //var
    Connection cnx = maConnexion.getInstance().getCnx();


    @Override
    public void ajouterPersonne(Personne p) {
        
        String request = "INSERT INTO `personne`(`nom`, `prenom`, `age`, `cin`) VALUES ('"+p.getNom()+"','"+p.getPrenom()+"',"+p.getAge()+",'"+p.getCin()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Personne ajoutee avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public List<Personne> afficherPersonne() {
        List<Personne> personnes = new ArrayList<>();
        
        String query = "SELECT * FROM personne";
        
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                personnes.add(new Personne(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getInt("age"), rs.getString(5)));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return personnes;
    }

    @Override
    public void ajouterPersonne2(Personne p) {
        
        String Req = "INSERT INTO `personne`(`nom`, `prenom`, `age`, `cin`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(Req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setInt(3, p.getAge());
            ps.setString(4, p.getCin());
            ps.execute();
            System.out.println("2 : Personnes ajoutee avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
