/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Interfaces.IPanier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import Models.Panier;
import Models.PanierEntry;
import Models.Product;
import Models.User;
import Utils.DbUtils;
import Utils.maConnexion;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author fomri
 */
public class PanierService implements IPanier {

    Connection conn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private static PanierService INSTANCE;

    public static PanierService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PanierService();
        }
        return INSTANCE;
    }

    @Override
    public void addProduct(String productName, User user) {
        PanierEntry productEntry = Panier.getInstance().getEntries().get(productName.toUpperCase());
        if (productEntry != null) {
            productEntry.increaseQuantity();
            updateEntety(productEntry);
        } else {
            try {
                Product product = Product.valueOf(productName);
                PanierEntry entry = new PanierEntry(product, 1);
                Panier.getInstance().getEntries().put(productName.toUpperCase(), entry);
                conn = DbUtils.getInstance().getConnection();
                //conn = maConnexion.getInstance().getCnx();
                String query = "INSERT INTO commande (id_produit, id_user, Quantity) VALUES(" + product.getId() + "," + String.valueOf(user.getIdUser()) + ",1)";
                java.sql.Statement statement = conn.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void removeProduct(String prodcutName) {
        PanierEntry panierEntry = Panier.getInstance().getEntries().get(prodcutName.toUpperCase());
        if (panierEntry.getQuantity() >= 1) {
            panierEntry.decreaseQuantity();
            updateEntety(panierEntry);
        }
        if (panierEntry.getQuantity() == 0) {
            deleteEntety(panierEntry);
            Panier.getInstance().getEntries().remove(prodcutName);
        }
    }

    @Override
    public int getQuantity(String productName) {
        PanierEntry entry = Panier.getInstance().getEntries().get(productName.toUpperCase());
        if (entry != null) {
            return entry.getQuantity();
        }
        return 0;
    }

    @Override
    public float calculTotale() {
        float totale = 0;
        for (PanierEntry entry : Panier.getInstance().getEntries().values()) {
            float entryCost = entry.getProduct().getPrice() * entry.getQuantity();
            totale += entryCost;
        }
        return totale;
    }

    public float calculTotaleByCategory(String category) {

        float totale = 0;
        for (PanierEntry entry : Panier.getInstance().getEntries().values()) {
            if (category.equalsIgnoreCase(entry.getProduct().getCategory())) {
                float entryCost = entry.getProduct().getPrice() * entry.getQuantity();
                totale += entryCost;
            } 
        }
        return totale;
    }

    //get entry by user (entry is the commande who was passed to panier (en cour)
    @Override
    public List<PanierEntry> getEntries(User user) {
        String productId = "";
        int quantity;
        Product product = null;

        try {
            conn = DbUtils.getInstance().getConnection();
            //conn = maConnexion.getInstance().getCnx();
            preparedStatement = conn.prepareStatement("SELECT * from commande c,panier p WHERE c.id_user=p.id_user and c.id_user= ? and etat='En_cour' ");
            preparedStatement.setString(1, String.valueOf(user.getIdUser()));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productId = resultSet.getString("id_produit");
                quantity = Integer.valueOf(resultSet.getString("Quantity"));

                LocalDate localDate = LocalDate.parse(resultSet.getString("datePanier"));
                ZoneId defaultZoneId = ZoneId.systemDefault();
                Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

                PreparedStatement ps = conn.prepareStatement("SELECT * from produit WHERE id = ?");
                ps.setString(1, productId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    product = Product.valueOf(rs.getString("name"));
                    PanierEntry entry = new PanierEntry(product, quantity,date);
                    Panier.getInstance().getEntries().put(rs.getString("name"), entry);
                }
            }
            return new ArrayList<>(Panier.getInstance().getEntries().values());

        } catch (SQLException ex) {
            Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public void updateEntety(PanierEntry entry) {
        try {
            conn = DbUtils.getInstance().getConnection();
            //conn = maConnexion.getInstance().getCnx();
            String query = "UPDATE commande set Quantity = " + entry.getQuantity() + " WHERE id_produit=" + String.valueOf(entry.getProduct().getId());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteEntety(PanierEntry entry) {
        try {
            conn = DbUtils.getInstance().getConnection();
            //conn = maConnexion.getInstance().getCnx();
            String query = "DELETE FROM commande WHERE id_produit = " + entry.getProduct().getId();
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void passerCommande(User user, float totale, java.sql.Date sqlDate) {

        try {
            conn = DbUtils.getInstance().getConnection();
            //conn = maConnexion.getInstance().getCnx();
            String query = "INSERT INTO panier (total, datePanier,etat,id_user ) VALUES(" + String.valueOf(totale) + ",'" + sqlDate + "','En_cour'," + String.valueOf(user.getIdUser()) + ")";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Get All entries for admin to make sur he sees all commandes
    @Override
    public Map<String, List<PanierEntry>> getAllEntries() {
        String productId = "";
        String UserId = "";
        User user = null;
        int quantity;
        Map<String, List<PanierEntry>> allEntries = new HashMap<String, List<PanierEntry>>();
        Product product = null;

        try {
            conn = DbUtils.getInstance().getConnection();
            //conn = maConnexion.getInstance().getCnx();
            preparedStatement = conn.prepareStatement("SELECT * from commande c ,panier p WHERE c.id_user=p.id_user and etat='En_cour' ");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productId = resultSet.getString("id_produit");
                UserId = resultSet.getString("id_user");
                quantity = Integer.valueOf(resultSet.getString("Quantity"));

                PreparedStatement ps = conn.prepareStatement("SELECT * from produit WHERE id = ?");
                ps.setString(1, productId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    product = Product.valueOf(rs.getString("name"));
                    PanierEntry entry = new PanierEntry(product, quantity);
                    Panier.getInstance().getEntries().put(rs.getString("name"), entry);
                }

                PreparedStatement ps1 = conn.prepareStatement("SELECT * from user WHERE id = ?");
                ps1.setString(1, UserId);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    user = new User(Integer.valueOf(rs1.getString("id")), rs1.getString("name"), "", rs1.getString("email"), "", rs1.getString("password"), rs1.getString("role"), 0);
                }
                if (user != null) {
                    allEntries.put(user.getNameUser(), new ArrayList<>(Panier.getInstance().getEntries().values()));
                }

            }
            return allEntries;

        } catch (SQLException ex) {
            Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allEntries;
    }

    @Override
    public float calculTotaleByEntries(List<PanierEntry> panierEntries) {
        float totale = 0;
        for (PanierEntry entry : panierEntries) {
            float entryCost = entry.getProduct().getPrice() * entry.getQuantity();
            totale += entryCost;
        }
        return totale;
    }

    public User getUserByName(String name) throws SQLException {
        String sql = "SELECT * FROM user WHERE name='" + name + "'";
        Statement statement = conn.prepareStatement(sql);
        //statement.executeUpdate(sql);
        ResultSet rs = statement.executeQuery(sql);
        User u = new User();
        while (rs.next()) {
            u.setIdUser(rs.getInt("id"));
            u.setNameUser(rs.getString("name"));
            u.setEmailUser(rs.getString("email"));
            u.setUserRole(rs.getString("role"));
        }
        return u;
    }

    @Override
    public void validerCommande(User user) {
        try {

            conn = DbUtils.getInstance().getConnection();
            //conn = maConnexion.getInstance().getCnx();
            String query = "UPDATE panier set etat = '" + DbUtils.VLIDATED_STATUS + "'  WHERE id_user=" + String.valueOf(user.getIdUser());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Map<String, PanierEntry> getCommandeEntries(User user) {
        String productId = "";
        int quantity;
        Product product = null;
        HashMap<String, PanierEntry> entries = new HashMap<>();

        try {
            conn = DbUtils.getInstance().getConnection();
            //conn = maConnexion.getInstance().getCnx();
            preparedStatement = conn.prepareStatement("SELECT * from commande WHERE id_user= ? ");
            preparedStatement.setString(1, String.valueOf(user.getIdUser()));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productId = resultSet.getString("id_produit");
                quantity = Integer.valueOf(resultSet.getString("Quantity"));
                PreparedStatement ps = conn.prepareStatement("SELECT * from produit WHERE id = ?");
                ps.setString(1, productId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    product = Product.valueOf(rs.getString("name"));
                    PanierEntry entry = new PanierEntry(product, quantity);
                    entries.put(rs.getString("name"), entry);
                }
            }
            return entries;

        } catch (SQLException ex) {
            Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entries;
    }
    
    
    public List<PanierEntry> getCommandedEntries(User user) {
        String productId = "";
        int quantity;
        Product product = null;

        try {
            conn = DbUtils.getInstance().getConnection();
            //conn = maConnexion.getInstance().getCnx();
            preparedStatement = conn.prepareStatement("SELECT * from commande c,panier p WHERE c.id_user=p.id_user and c.id_user= ?");
            preparedStatement.setString(1, String.valueOf(user.getIdUser()));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productId = resultSet.getString("id_produit");
                quantity = Integer.valueOf(resultSet.getString("Quantity"));
                PreparedStatement ps = conn.prepareStatement("SELECT * from produit WHERE id = ?");
                ps.setString(1, productId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    product = Product.valueOf(rs.getString("name"));
                    PanierEntry entry = new PanierEntry(product, quantity);
                    Panier.getInstance().getEntries().put(rs.getString("name"), entry);
                }
            }
            return new ArrayList<>(Panier.getInstance().getEntries().values());

        } catch (SQLException ex) {
            Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
    public List<Panier> getPanierbyUser(User user){
         

        ArrayList<Panier> userCart= new ArrayList<>();

        try {
            conn = DbUtils.getInstance().getConnection();
            //conn = maConnexion.getInstance().getCnx();
            preparedStatement = conn.prepareStatement("SELECT * from panier p WHERE  p.id_user= ? ");
            preparedStatement.setString(1, String.valueOf(user.getIdUser()));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                float totale = Float.valueOf(resultSet.getString("total"));
                String etat = resultSet.getString("etat");
                
                LocalDate localDate = LocalDate.parse(resultSet.getString("datePanier"));
                ZoneId defaultZoneId = ZoneId.systemDefault();
                Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
                Panier panier = new Panier();
                panier.setDateCreation(date);
                panier.setEtat(etat);
                panier.setTotale(totale);
                userCart.add(panier);
                  
            }
            return userCart;

        } catch (SQLException ex) {
            Logger.getLogger(Panier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

}
