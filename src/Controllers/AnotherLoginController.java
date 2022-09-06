/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.User;
import Utils.DbUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fomri
 */
public class AnotherLoginController implements Initializable {

    @FXML
    private TextField emailTextInput;
    @FXML
    private Button btnConnect;
    @FXML
    private PasswordField passwordInput;
    
    
    Connection conn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private Stage stage;
    private Scene scene;
    private Parent parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void seConnecter(ActionEvent event) {
        String email = emailTextInput.getText();
        String passwd = passwordInput.getText();
        User connectedUser = null;

        try {
            conn = DbUtils.getInstance().getConnection();
            preparedStatement = conn.prepareStatement("SELECT * from user WHERE email=? and password=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwd);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                connectedUser = new User(Integer.valueOf(resultSet.getString("id")), resultSet.getString("name"), "", resultSet.getString("email"), "", resultSet.getString("password"), resultSet.getString("role"), 0);
            }

            if (connectedUser != null) {
                switchToMainScence(event, connectedUser); 

            } else {
                JOptionPane.showMessageDialog(null, "Error Login! try egain");
                emailTextInput.setText("");
                passwordInput.setText("");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Error");
        }
    }
    
    public void switchToMainScence(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Commande.fxml"));
        parent = loader.load();

        CommandeController commandeController = loader.getController();
        commandeController.setUser(user);
        //Parent root = FXMLLoader.load(getClass().getResource("../Views/Commande.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    
}
