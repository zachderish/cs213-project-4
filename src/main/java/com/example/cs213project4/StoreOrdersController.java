package com.example.cs213project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StoreOrdersController  {

    private MainController mainController;

    @FXML
    private ListView orderList;
    @FXML
    private TextField totalText;
    @FXML
    ChoiceBox storeOrderChoiceBox;

  //  int[] currentOrderNumbers;

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

    public void setMainController(MainController controller) {
        mainController = controller;
    }

/*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StoreOrders orders = mainController.getStoreOrders();
       // currentOrderNumbers = new int[orders.getStoreOrders().size()];
         int[] currentOrderNumbers = orders.getOrderNumbers();
        storeOrderChoiceBox.getItems().addAll(currentOrderNumbers);
    }

 */
}
