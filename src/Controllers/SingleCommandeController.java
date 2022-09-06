package Controllers;

import Models.Panier;
import Models.PanierEntry;
import Models.Product;
import Models.User;
import Services.PanierService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleCommandeController implements Initializable {
    @FXML
    private Label CreationDate;

    @FXML
    private AnchorPane ProdcutPane;

    @FXML
    private Label Totale;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<PanierEntry> entries = PanierService.getInstance().getEntries(getUser());


        for (PanierEntry entry : entries) {
            try {
                try {
                    HBox hbox = panierEntryView(entry);
                    Label prodcutName = new Label(entry.getProduct().name());
                    hbox.getChildren().add(prodcutName);
                    ProdcutPane.getChildren().add(hbox);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Separator separator = new Separator();
                separator.setOrientation(Orientation.HORIZONTAL);
                ProdcutPane.getChildren().add(separator);
                HBox totaleView = totalView(PanierService.getInstance().calculTotale(), getUser());
                ProdcutPane.getChildren().add(totaleView);


                // Create the Status Box
                    /*HBox jobStatusBox = new HBox(5, new Label("Job Status: "), jobStatus);
                    PanierPane.getChildren().add(jobStatusBox);*/
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    private HBox totalView(float totalePrice, User user) throws FileNotFoundException {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        Label totalLabel = new Label("Totale : ");
        totalLabel.setStyle("-fx-font-size:15pt;");
        Button passerCommandePanier = new Button("Passer Le Panier");
        passerCommandePanier.setStyle("-fx-padding:15px");

        //Action du button passerCommande
        passerCommandePanier.setOnAction((t) -> {
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            PanierService.getInstance().passerCommande(user, totalePrice, sqlDate);
            PanierService.getInstance().getEntries(user).clear();

        });

        FileInputStream input = new FileInputStream("C:\\Users\\fomri\\Documents\\Chahine\\Integration\\DevCorp\\src\\Assets\\pdf-file.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        Button exportPdf = new Button();
        exportPdf.setGraphic(imageView);
        exportPdf.setStyle("-fx-padding:20px");

        this.Totale = new Label(String.valueOf(totalePrice));
        layout.getChildren().addAll(totalLabel, this.Totale, passerCommandePanier, exportPdf);

        exportPdf.setOnAction((t) -> {
            print(ProdcutPane);
        });

        return layout;
    }


    public SingleCommandeController(User user, ObservableList<Panier> panier) {
        this.user = user;
        this.panier = panier;
    }

    ObservableList<Panier> panier;
    User user;

    public ObservableList<Panier> getPanier() {
        return panier;
    }

    public void setPanier(ObservableList<Panier> panier) {
        this.panier = panier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    private void print(Node node) {
        // Create a printer job for the default printer
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null) {

            // Print the node
            boolean printed = job.printPage(node);

            if (printed) {
                // End the printer job
                job.endJob();
            } else {
                // Write Error Message
                return;
            }
        } else {
            // Write Error Message
            return;
        }
    }

    private HBox panierEntryView(PanierEntry panierEntry) throws FileNotFoundException {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);

        FileInputStream input = new FileInputStream("C:\\Users\\fomri\\Documents\\Chahine\\Final\\CRUDPI2\\MyApp\\src\\Assets\\" + panierEntry.getProduct().getImageFile());
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        Label productName = new Label(panierEntry.getProduct().name());
        productName.setPrefWidth(100);
        productName.setStyle("-fx-font-size:15pt; -fx-padding:5px");

        Label productQuantity = new Label(String.valueOf(panierEntry.getQuantity()));
        productName.setStyle("-fx-padding:5px");

        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-padding:5px");

        plusButton.setUserData(panierEntry.getProduct().name());
        plusButton.setOnAction((t) -> {
            String name = (String) ((Node) t.getSource()).getUserData();
            PanierService.getInstance().addProduct(name, getUser());
            productQuantity.setText(String.valueOf(PanierService.getInstance().getQuantity(name)));
            this.Totale.setText(String.valueOf(PanierService.getInstance().calculTotale()));
        });

        Button moinsButton = new Button("-");
        moinsButton.setStyle("-fx-padding:5px");
        moinsButton.setUserData(panierEntry.getProduct().name());
        moinsButton.setOnAction((t) -> {
            String name = (String) ((Node) t.getSource()).getUserData();
            PanierService.getInstance().removeProduct(name);
            productQuantity.setText(String.valueOf(PanierService.getInstance().getQuantity(name)));
            this.Totale.setText(String.valueOf(PanierService.getInstance().calculTotale()));
        });

        Label labelPrice = new Label(String.valueOf("£ " + panierEntry.getProduct().getPrice()));
        labelPrice.setStyle("-fx-padding:5px");

        layout.getChildren().addAll(imageView, productName, plusButton, productQuantity, moinsButton, labelPrice);
        return layout;

    }

    private HBox totalViewForAdmin(float totalePrice, User user) {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        Label totalLabel = new Label("Totale : ");
        totalLabel.setStyle("-fx-font-size:15pt;");

        Button validerLePanier = new Button("Valider Le Panier");
        validerLePanier.setStyle("-fx-padding:15px");
        //Action du button passerCommande
        validerLePanier.setOnAction((t) -> {
            PanierService.getInstance().validerCommande(user);
        });
        this.Totale = new Label(String.valueOf(totalePrice));
        layout.getChildren().addAll(totalLabel, this.Totale, validerLePanier);
        return layout;
    }


}
