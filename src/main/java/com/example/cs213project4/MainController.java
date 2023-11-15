package com.example.cs213project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController {

    /**
     * Button handler for Specialty Pizzas.
     * @param event ActionEvent
     */
    @FXML
    protected void onSpecialtyPizzaClick(ActionEvent event) throws IOException {
        Parent specialtyPizzaRoot = FXMLLoader.load(getClass().getResource("specialty-pizza.fxml"));
        Scene specialtyPizzaScene = new Scene(specialtyPizzaRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Specialty Pizzas");

        stage.setScene(specialtyPizzaScene);
        stage.show();
    }

    /**
     * Button handler for Build Your Own Pizza.
     * @param event ActionEvent
     */
    @FXML
    protected void onBuildYourOwnClick(ActionEvent event) throws IOException {
        Parent buildYourOwnRoot = FXMLLoader.load(getClass().getResource("build-your-own.fxml"));
        Scene buildYourOwnScene = new Scene(buildYourOwnRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Build Your Own Pizza");

        stage.setScene(buildYourOwnScene);
        stage.show();
    }

    /**
     * Button handler for Current Order.
     * @param event ActionEvent
     */
    @FXML
    protected void onCurrentOrderClick(ActionEvent event) throws IOException {
        Parent currentOrderRoot = FXMLLoader.load(getClass().getResource("current-order.fxml"));
        Scene currentOrderScene = new Scene(currentOrderRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Current Order");

        stage.setScene(currentOrderScene);
        stage.show();
    }

    /**
     * Button handler for Store Orders.
     * @param event ActionEvent
     */
    @FXML
    protected void onStoreOrdersClick(ActionEvent event) throws IOException {
        Parent storeOrdersRoot = FXMLLoader.load(getClass().getResource("store-orders.fxml"));
        Scene storeOrdersScene = new Scene(storeOrdersRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Store Orders");

        stage.setScene(storeOrdersScene);
        stage.show();
    }
}