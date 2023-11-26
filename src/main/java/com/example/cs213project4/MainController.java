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

/**
 * Define Main Controller for GUI.
 * @author Kenrick Eagar, Zachary Derish
 */
public class MainController {
    private static StoreOrders orders = new StoreOrders();
    private static ArrayList<Integer> ordersPlaced = new ArrayList<>();

    /**
     * Button handler for Specialty Pizzas.
     * @param event ActionEvent
     */
    @FXML
    protected void onSpecialtyPizzaClick(ActionEvent event) throws IOException {
        FXMLLoader pizzaLoader = new FXMLLoader(getClass().getResource("specialty-pizza.fxml"));

        Parent specialtyPizzaRoot = pizzaLoader.load();
        Scene specialtyPizzaScene = new Scene(specialtyPizzaRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Specialty Pizzas");

        stage.setScene(specialtyPizzaScene);
        stage.show();
        SpecialtyPizzaController specialtyPizzaController = pizzaLoader.getController();
        specialtyPizzaController.setMainController(this);
    }

    /**
     * Button handler for Build Your Own Pizza.
     * @param event ActionEvent
     */
    @FXML
    protected void onBuildYourOwnClick(ActionEvent event) throws IOException {

        FXMLLoader loadMe = new FXMLLoader(getClass().getResource("build-your-own.fxml"));

        Parent buildYourOwnRoot = loadMe.load();
        Scene buildYourOwnScene = new Scene(buildYourOwnRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Build Your Own Pizza");

        stage.setScene(buildYourOwnScene);
        stage.show();
        BuildYourOwnController byoController = loadMe.getController();
        byoController.setMainController(this);
    }

    /**
     * Button handler for Current Order.
     * @param event ActionEvent
     */
    @FXML
    protected void onCurrentOrderClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("current-order.fxml"));

        Parent currentOrderRoot = loader.load();
        Scene currentOrderScene = new Scene(currentOrderRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Current Orders");

        stage.setScene(currentOrderScene);
        stage.show();

        CurrentOrderController currentOrderController = loader.getController();
        currentOrderController.setMainController(this);
    }

    /**
     * Button handler for Store Orders.
     * @param event ActionEvent
     */
    @FXML
    protected void onStoreOrdersClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("store-orders.fxml"));

        Parent storeOrdersRoot = loader.load();
        Scene storeOrdersScene = new Scene(storeOrdersRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Store Orders");

        stage.setScene(storeOrdersScene);
        stage.show();

        StoreOrdersController storeOrdersController = loader.getController();
        storeOrdersController.setMainController(this);
    }

    /**
     * Get the current StoreOrders object.
     * @return StoreOrders
     */
    public StoreOrders getStoreOrders() {
        return orders;
    }

    /**
     * Get main controller reference
     * @return THIS main controller
     */
    public MainController getReference(){
        return this;
    }

    /**
     * Get current orders placed list
     * @return current orders placed list
     */
    public ArrayList<Integer> getOrdersPlaced(){ return ordersPlaced;}
}

