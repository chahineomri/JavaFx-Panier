/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Panier;
import Models.PanierEntry;
import Models.Product;
import Models.User;
import Services.PanierService;

import java.io.IOException;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author fomri
 */
public class CommandesController implements Initializable {

    private User user;

    @FXML
    private TextField searchInput;
    @FXML
    private TableView<Panier> commandesTable;
    @FXML
    private TableColumn<Panier, Date> DatePanier;
    @FXML
    private TableColumn<Panier, String> etatPanier;
    @FXML
    private TableColumn<Panier, Float> TotalPanier;

    private final ObservableList<Panier> dataList = FXCollections.observableArrayList();

    public CommandesController(User user) {
        this.user = user;
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        List<Panier> entries = PanierService.getInstance().getPanierbyUser(getUser());

        DatePanier.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        etatPanier.setCellValueFactory(new PropertyValueFactory<>("etat"));
        TotalPanier.setCellValueFactory(new PropertyValueFactory<>("totale"));

        for (Panier panier : entries) {
            dataList.add(panier);
        }

        FilteredList<Panier> filteredData = new FilteredList<>(dataList, b -> true);
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(panier -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (panier.getDateCreation().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (panier.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(panier.getTotale()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Panier> sortedPanier = new SortedList<>(filteredData);
        sortedPanier.comparatorProperty().bind(commandesTable.comparatorProperty());
        commandesTable.setItems(sortedPanier);

        getSelectedCommand();

    }

    private void getSelectedCommand() {
        commandesTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/SingleCommandeInfo.fxml"));
                Parent parent = null;
                ObservableList<Panier> panier = commandesTable.getSelectionModel().getSelectedItems();
                // parent = fxmlLoader.load();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/SingleCommandeInfo.fxml"));
                SingleCommandeController singleCommandeController = new SingleCommandeController(getUser(),panier);
                singleCommandeController.setUser(getUser());
                loader.setController(singleCommandeController);
                try {
                    parent = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                SingleCommandeController dialogController = loader.<SingleCommandeController>getController();


                Scene scene = new Scene(parent, 300, 200);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            }
        });
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
