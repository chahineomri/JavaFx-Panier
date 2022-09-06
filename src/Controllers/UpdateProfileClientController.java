/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static Controllers.LoginController.isValidEmailAddress;
import Interfaces.IClient;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Models.User;
import Services.ClientService;

/**
 * FXML Controller class
 *
 * @author sarawahada
 */
public class UpdateProfileClientController implements Initializable {


    @FXML
    private Label rec;
    @FXML
    private TextField Name;
    @FXML
    private TextField Email;
    @FXML
    private TextField LastName;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField NewPassword;
    @FXML
    private PasswordField PasswordConfirm;
    @FXML
    private Button imgLoad;
    @FXML
    private ImageView img;
    @FXML
    private Button Cancel;
    @FXML
    private Button Deactivate;
    @FXML
    private Button Modify;
    
    File file;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            IClient Ic = new ClientService();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
            Parent root = loader.load();
            LoginController Lc = loader.getController();
            String mail = Lc.email;
            String pwd = Lc.password;
            //   System.out.println(mail);
            Name.setText(Ic.getNamebyId(Ic.getIdbyMail(mail)));
            LastName.setText(Ic.getLastNamebyId(Ic.getIdbyMail(mail)));
            Email.setText(Ic.getMailbyId(Ic.getIdbyMail(mail)));
            File file = new File(Ic.getProfilePicbyId(Ic.getIdbyMail(mail)));
            Image image = new Image(file.toString());
            System.out.println(file.toURI().toString());
            img.setImage(image);
            
        } catch (IOException | SQLException ex) {
        }
        }
    
    @FXML
    private void imgLoad(ActionEvent event) {
        FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select picture");
        fileChooserr.setInitialDirectory(new File("/"));
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        file = fileChooserr.showOpenDialog(img.getScene().getWindow());
        Image image = new Image(file.toURI().toString());
        img.setImage(image);
    }
    
    @FXML
    private void Modify(ActionEvent event) throws SQLException, IOException {

        User u = new User();
        u.setNameUser(Name.getText());
        u.setLastNameUser(LastName.getText());
        u.setEmailUser(Email.getText());
        if (this.file==null){
        File file = new File("src/assets/avatar.png");
        u.setProfilePicUser(file.toURI().toString());
        u.setPasswordUser(NewPassword.getText());
        u.setUserRole("client");
        u.setUserStatus(1);
        //u.setPasswordUser(Password.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
        Parent root = loader.load();
        LoginController Lc = loader.getController();
        String pwd = Lc.password;
        if(Password.getText().equals(pwd)){
        if(PasswordConfirm.getText().equals(NewPassword.getText())){
        if(isValidEmailAddress(Email.getText())==true)
        {
        IClient Ic = new ClientService();
        Ic.UpdateUser(u,Ic.getIdbyMail(Email.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Profile updated successfully");
        alert.showAndWait();
        
        }
        else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid email");
        alert.showAndWait();
        }
        }
        }
        else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Verify your password to update your profile");
        alert.showAndWait(); 
        }
        }
        else{
        u.setProfilePicUser(file.toURI().toString());
        u.setPasswordUser(NewPassword.getText());
        u.setUserRole("client");
        u.setUserStatus(1);
        //u.setPasswordUser(Password.getText());
        IClient Ic = new ClientService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
        Parent root = loader.load();
        LoginController Lc = loader.getController();
        String pwd = Lc.password;
        if(Password.getText().equals(pwd)){
        if(PasswordConfirm.getText().equals(NewPassword.getText())){
        if(isValidEmailAddress(Email.getText())==true )
        {
        Ic.UpdateUser(u,Ic.getIdbyMail(Email.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Profile updated successfully");
        alert.showAndWait();
        }
        else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid email");
        alert.showAndWait();
        }
        }
        }
        else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Verify your password to update your profile");
        alert.showAndWait();    
        }
        }
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Name.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../Views/Client.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    @FXML
    private void Deactivate(ActionEvent event) throws IOException, SQLException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Warning");
      alert.setHeaderText("Are you sure you want to delete your account permanently ?");
      alert.setContentText(null);
 
      // option != null.
      Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == null) {
        this.Deactivate.setText("No selection!");
      } else if (option.get() == ButtonType.OK) {
       IClient Ic = new ClientService();
        Ic.DeleteUser(Ic.getIdbyMail(Email.getText()));
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Success");
        alerte.setHeaderText(null);
        alerte.setContentText("Your account was permanently deleted");
        alerte.showAndWait();
        FXMLLoader loader = new FXMLLoader();
        Name.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../Views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
      } else if (option.get() == ButtonType.CANCEL) {
         
      } else {
         this.Deactivate.setText(".");
      } 
    }
}


