/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

 
import static Controllers.LoginController.codem;
import static Controllers.LoginController.isValidEmailAddress;
import Interfaces.IUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import Services.SendingMail;
import Services.UserService;

/**
 * FXML Controller class
 *
 * @author sarawahada
 */
public class SendMailController implements Initializable {

    @FXML
    private Label mailLabel;
    @FXML
    private TextField EmailUser;
    
    static String mail;
    
    
    
    public boolean UserExist (String email) throws SQLException  {
       
            IUser Iu = new UserService();
            if (Iu.getIdbyMail(EmailUser.getText())!=0){
                mail=EmailUser.getText();
                return true;
    }
            return false;
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        @FXML
    private void SendMail(ActionEvent event) throws MessagingException, IOException, SQLException {
        IUser Iu = new UserService();
        Random r = new Random ();
        codem =r.nextInt(9999-1000+1);
        System.out.println(codem);
        if(isValidEmailAddress(EmailUser.getText())&&UserExist(EmailUser.getText())==true)
                {
        SendingMail.send(EmailUser.getText(),codem);
        FXMLLoader loader = new FXMLLoader();
        mailLabel.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../Views/ResetPassword.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
                }
        else if(isValidEmailAddress(EmailUser.getText())==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("Email address not valid");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("Not a user go ahead and create an account");
            alert.showAndWait();
           
        }
       
    }
        @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
                mailLabel.getScene().getWindow().hide();  
                Stage prStage =new Stage(); 
                loader.setLocation(getClass().getResource("../Views/Login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                prStage.setResizable(false);
                prStage.show();
    }
    
}
