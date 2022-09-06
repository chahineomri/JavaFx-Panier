/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.User;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author fomri
 */
public class PanierView {

    private Parent view;

    public PanierView(User user) throws MalformedURLException, IOException { 
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Panier.fxml")); 
        PanierController panierController = new PanierController(user);
        loader.setController(panierController); 
        Parent root  = loader.load(); 
         
        this.view = root;
    }

    public Parent getView() {
        return this.view;
    }

}
