/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.Panier;
import Models.Product;
import Models.User;
import Services.PanierService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author fomri
 */
public class ProductsController implements Initializable {

    @FXML
    private GridPane productGridPane;

     private User user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            productGridPane.getChildren().clear();
            VBox productView1 = productView(Product.BURGER);
            productGridPane.add(productView1, 0, 0);
            VBox productView2 = productView(Product.SUSHI);
            productGridPane.add(productView2, 1, 0);
            VBox productView3 = productView(Product.TACOS);
            productGridPane.add(productView3, 2, 0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private VBox productView(Product product) throws FileNotFoundException {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        FileInputStream input = new FileInputStream("C:\\Users\\fomri\\Documents\\Chahine\\Final\\CRUDPI2\\MyApp\\src\\Assets\\" + product.getImageFile());

        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Label productName = new Label(product.name());
        Label price = new Label(product.getPrice() + "£");

        Button addBtn = new Button("Ajouter dans Panier");
        addBtn.setUserData(product.name());
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                  //Ajouter le produit to panier
                  Node sourceComponent  = (Node)actionEvent.getSource();
                  String productName = (String)sourceComponent.getUserData();
                  PanierService.getInstance().addProduct(productName,getUser());
                  
            }
        });
        layout.getChildren().addAll(imageView, productName, price, addBtn);
        return layout;

    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
