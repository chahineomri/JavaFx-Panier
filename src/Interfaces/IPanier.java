/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Models.PanierEntry;
import Models.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fomri
 */
public interface IPanier {

    public void addProduct(String productName, User user);

    public void removeProduct(String prodcutName);

    public int getQuantity(String productName);

    public float calculTotale();

    public List<PanierEntry> getEntries(User user);

    public void updateEntety(PanierEntry entry);

    public void deleteEntety(PanierEntry entry);

    public void passerCommande(User user, float totale, java.sql.Date sqlDate);

    public Map<String, List<PanierEntry>> getAllEntries();

    public float calculTotaleByEntries(List<PanierEntry> panierEntries);

    public void validerCommande(User user);

    Map<String, PanierEntry> getCommandeEntries(User user);

    public float calculTotaleByCategory(String category);
}
