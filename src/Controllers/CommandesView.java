/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author fomri
 */
public class CommandesView {
     private Parent view;
     
     public CommandesView(User user) throws MalformedURLException, IOException { 
         
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Commandes.fxml")); 
        CommandesController commandesController = new CommandesController(user);
        loader.setController(commandesController); 
        Parent root  = loader.load(); 
         
        this.view = root;
    }
     
     
      public Parent getView() {
        return this.view;
    }

    
}
