/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fomri
 */
public class Panier {

    private static Panier INSTANCE; 
    public static Panier getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Panier();
        }
        return INSTANCE;
    }
    private Map<String, PanierEntry> entries;
    private Date dateCreation;
    private String etat;
    private float totale;
    public Panier() {
        this.entries = new HashMap<>();
    }

    public Map<String, PanierEntry> getEntries() {
        return entries;
    }

    public void setEntries(Map<String, PanierEntry> entries) {
        this.entries = entries;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }
}
