/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.PanierEntry;
import Models.User;
import Services.PanierService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author fomri
 */
public class StatController implements Initializable {

    @FXML
    private VBox StatPane;

    private User user;
    
    private Label label;

    public StatController(User user) {
        this.user = user;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<PanierEntry> entries = PanierService.getInstance().getCommandedEntries(getUser());
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        for (PanierEntry panierEntry : entries) {
            pieData.add(new PieChart.Data(panierEntry.getProduct().getCategory(), PanierService.getInstance().calculTotaleByCategory(panierEntry.getProduct().getCategory())));
        }
        // TODO 
        PieChart pieChart = new PieChart(pieData);
        pieChart.setStartAngle(180.0);
        Group root = new Group(pieChart);
        
        label = new Label();
        label.setFont(Font.font("SanSerif",FontWeight.BOLD,15));
        pieChart.getData()
                .stream()
                .forEach(data->{
                    data.getNode().addEventHandler(MouseEvent.ANY, e->{
                        label.setText(data.getName()+ " Consomations: "+data.getPieValue());
                    });
                });
        
        StatPane.getChildren().addAll(root,label);

    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
