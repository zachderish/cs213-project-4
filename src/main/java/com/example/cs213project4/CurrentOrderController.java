package com.example.cs213project4;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CurrentOrderController {
    @FXML
    private TextField orderNumber;
    @FXML
    private ListView<String> orderTextArea;

    private MainController mainController;

    /**
     * Button handler for back button.
     * @param event ActionEvent
     */
    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        Scene mainMenuScene = new Scene(mainMenuRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("RU Pizza <Main Menu>");

        stage.setScene(mainMenuScene);
        stage.show();
    }

    /**
     * Get the reference to the MainController object.
     * @param controller MainController
     */
    public void setMainController(MainController controller) {
        mainController = controller;
        setOrderNumber();
        setPizzas();
    }

    @FXML
    void setOrderNumber() {
        String currentOrderNum = String.valueOf(mainController.getStoreOrders().getAvailable_OrderNumber());
        orderNumber.setText(currentOrderNum);
    }

    @FXML
    void setPizzas() {
        int currentOrderNum = mainController.getStoreOrders().getAvailable_OrderNumber();

    }
}
