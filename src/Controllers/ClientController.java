/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Interfaces.IClient;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Services.ClientService;

/**
 * FXML Controller class
 *
 * @author sarawahada
 */
public class ClientController implements Initializable {

    @FXML
    private Label Email;

    @FXML
    private Label LastName;

    @FXML
    private Label Name;
    
    @FXML
    private Label Name1;

    @FXML
    private Label Status;

    @FXML
    private Button Update;

    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            IClient Ic = new ClientService();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
            Parent root = loader.load();
            LoginController Lc = loader.getController();
            String mail = Lc.email;
            String pwd = Lc.password;
            Name.setText(Ic.getNamebyId(Ic.getIdbyMail(mail)));
            Name1.setText(Ic.getNamebyId(Ic.getIdbyMail(mail)));
            LastName.setText(Ic.getLastNamebyId(Ic.getIdbyMail(mail)));
            Email.setText(Ic.getMailbyId(Ic.getIdbyMail(mail)));
            Status.setText(Ic.getStatusbyId(Ic.getIdbyMail(mail)));
            File file = new File(Ic.getProfilePicbyId(Ic.getIdbyMail(mail)));
            Image image = new Image(file.toString());
            System.out.println(file.toURI().toString());
            img.setImage(image);
        } catch (IOException | SQLException ex) {
        }
        } 
     @FXML
    private void initialize(MouseEvent event)  {
    }

    @FXML
    private void logoutt(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Name.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../Views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void UpdateProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Name.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../Views/UpdateProfileClient.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }
}

