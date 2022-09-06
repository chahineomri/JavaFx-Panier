/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; 
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane; 
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author fomri
 */
public class CommandeController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private BorderPane body;
    @FXML
    private VBox sideArea;
    @FXML
    private HBox sideControls;
    @FXML
    private Label closeButton;
    @FXML
    private VBox sideNav;
    @FXML
    private Region navHome;
    @FXML
    private Region navCart;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private BorderPane contentPane;
    @FXML
    private VBox componentBox;
    @FXML
    private Pane handPaneMac;

    private User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeApp(MouseEvent event) {
        NewFXMain.getWindow().close();
    }

    @FXML
    private void showHomeView(MouseEvent event) throws IOException {
        contentPane.setCenter(new ProductsView(user).getView());
    }

    @FXML
    private void showCartView(MouseEvent event) throws IOException {
        contentPane.setCenter(new PanierView(user).getView());
    }

     void showCommandes(ActionEvent event) {
        

    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void showUserStat(MouseEvent event) throws IOException {
         contentPane.setCenter(new StatiqueView(user).getView());
    }

    @FXML
    private void showCommandeList(MouseEvent event) throws IOException {
        contentPane.setCenter(new CommandesView(user).getView());
    }

}
