package com.example.cs213project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StoreOrdersController implements Initializable {

    @FXML
    private ListView<String> orderList;
    @FXML
    private TextField totalText;
    @FXML
    private ChoiceBox<Integer> soBox;
    @FXML
    private Button cancelButton;

    private MainController mainController = new MainController().getReference();
    private StoreOrders orders;

    private ArrayList<Integer> currentOrderNumbers;

    /**
     * Button handler for back button.
     *
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


    void noPizzaAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Pizza");
        alert.setContentText("No Pizza Orders Placed");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orders = mainController.getStoreOrders();
        if (orders.numberOfOrders() == 0) {
            noPizzaAlert();
            return;
        }
        currentOrderNumbers = orders.getOrderNumbers();
        soBox.getItems().addAll(currentOrderNumbers);
        // int currentNum = currentOrderNumbers.get(0);
        // soBox.setValue(currentNum);
        //setPrice();

        soBox.setOnAction(this::displayPizzas);
        // orderList.getItems().addAll(pizzaString);


    }


    void setPrice() {
        int orderNum = soBox.getValue();
        double taxes = 0.06625;
        double subtotal = orders.find(orderNum).getOrder_Subtotal();
        double total = (subtotal * taxes) + subtotal;
        String totalString = new DecimalFormat("#,##0.00").format(total);
        totalText.setText(totalString);
    }

    @FXML
    void updateChoiceBox(ActionEvent event, int orderNumber) {
        soBox.getItems().removeAll(currentOrderNumbers);
        currentOrderNumbers = mainController.getStoreOrders().getOrderNumbers();
        soBox.getItems().addAll(currentOrderNumbers);

    }

    @FXML
    void displayPizzas(ActionEvent event) {
        if (soBox.getValue() == null || orders.numberOfOrders() == 0) {
            noPizzaAlert();
            totalText.setText("");
            orderList.setItems(null);
            return;
        }

        orders = new MainController().getReference().getStoreOrders();

        Order selectedOrder = orders.find(soBox.getValue());
        ArrayList<String> pizzas = selectedOrder.getPizzas();
        ObservableList<String> pizzaString = FXCollections.observableArrayList(pizzas);
        orderList.setItems(pizzaString);

        setPrice();
    }

    @FXML
    void emptyCancelAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Error");
        alert.setContentText("Cannot Cancel, No Pizzas In Order");
        alert.showAndWait();
    }

    @FXML
    void nullAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Error");
        alert.setContentText("Cannot Cancel, No Pizzas Selected");
        alert.showAndWait();
    }

    @FXML
    void cancelOrder(ActionEvent event) {
        if (soBox.getValue() == null) {
            nullAlert(event);
            return;
        }
        if (orders.numberOfOrders() == 0) {
            nullAlert(event);
            return;
        }
        orders = new MainController().getReference().getStoreOrders();
        int currentNumb = soBox.getValue();
        ArrayList<String> pizzaList = orders.find(currentNumb).getPizzas();
        if (pizzaList.isEmpty()) {
            emptyCancelAlert(event);
            return;
        }
        boolean removeMe = orders.removeOrder(soBox.getValue());
        updateChoiceBox(event, currentNumb);
        displayPizzas(event);


    }

}