/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import static Controllers.SendMailController.mail;
import Interfaces.IUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Models.User;
import Services.UserService;


public class SignUpClientController implements Initializable {
    @FXML
    private Button AddPictureClientButton;

    @FXML
    private Hyperlink BackToLoginButton;

    @FXML
    private TextField EmailSignUp;

    @FXML
    private TextField LastNameSignUp;

    @FXML
    private TextField NameUserSignUp;

    @FXML
    private PasswordField PasswordSignUp;
    
    @FXML
    private PasswordField PasswordConfirm;
    
    @FXML
    private ImageView imgClient;

    @FXML
    private Button SignUpClientButton;
    
     @FXML
    private Label SignUpLabel;
    
    File file;
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    public boolean UserExists (String email) throws SQLException  {
       
            IUser Iu = new UserService();
            if (Iu.getIdbyMail(EmailSignUp.getText())!=Iu.getId()){
                mail=EmailSignUp.getText();
                return true;
    }
            return false;
    }
  
    public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    @FXML
         void SignUpClient(ActionEvent event) throws IOException, SQLException {
        
        if(NameUserSignUp.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(" Name Field empty");
        alert.showAndWait();}
        else if (LastNameSignUp.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Last Name Field empty");
        alert.showAndWait();}
        else if (PasswordSignUp.getText().isEmpty())
        { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Password Field empty");
        alert.showAndWait();}
        else if(isValidEmailAddress(EmailSignUp.getText())==false)
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Email is not valid");
        alert.showAndWait();     
        }
        else if(isValidEmailAddress(EmailSignUp.getText())==false)
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Email is not valid");
        alert.showAndWait();     
        }
           else if(UserExists(EmailSignUp.getText())==false)
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("email already exists");
        alert.showAndWait();     
        }
          else if (!PasswordConfirm.getText().equals(PasswordSignUp.getText()))
        { 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Password confirmation does not match password");
        alert.showAndWait();
        }
        else
        { 
        User u = new User();
        u.setUserRole("client");
        u.setUserStatus(1);
        u.setNameUser(NameUserSignUp.getText());
        u.setLastNameUser(LastNameSignUp.getText());
        u.setPasswordUser(PasswordSignUp.getText());
        u.setEmailUser(EmailSignUp.getText());
        if (this.file==null){
        File file = new File("src/assets/avatar.png");
        u.setProfilePicUser(file.toURI().toString());
        UserService us = new UserService();
        us.AddUser(u,PasswordSignUp.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Account created successfully ");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader();
        NameUserSignUp.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../Views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
        }
        else
        {
        u.setProfilePicUser(file.toURI().toString());
        UserService us = new UserService();
        us.AddUser(u,PasswordSignUp.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Account created successfully ");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader();
        NameUserSignUp.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../Views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
        }
            }
         }

      @FXML
    void AddPictureClient(ActionEvent event) {
        FileChooser fileChooserr = new FileChooser();
        fileChooserr.setTitle("Select Image");
        //might need to be changed to c: in windows users
        fileChooserr.setInitialDirectory(new File("/"));
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooserr.getExtensionFilters().add(imageFilter);
        file = fileChooserr.showOpenDialog(imgClient.getScene().getWindow());
        Image image = new Image(file.toURI().toString());
        imgClient.setImage(image);

    }
        @FXML
    void BackToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        BackToLoginButton.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("../Views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    }


