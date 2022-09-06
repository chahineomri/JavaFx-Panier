/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class StatiqueView {

    private Parent view; 
    public StatiqueView(User user) throws MalformedURLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Stat.fxml"));
        StatController statController = new StatController(user);
        loader.setController(statController); 
        Parent root = loader.load();

        this.view = root;
    }

    public Parent getView() {
        return this.view;
    }

}
