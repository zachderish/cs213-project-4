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

public class CurrentOrderController {
    @FXML
    private TextField orderNumber;
    @FXML
    private ListView<String> orderTextArea;
    @FXML
    private TextField subtotal, tax, total;

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
        setPrices();
    }

    /**
     * Set orderNumber text field to the current order number.
     */
    @FXML
    void setOrderNumber() {
        String currentOrderNum = String.valueOf(mainController.getStoreOrders().getAvailable_OrderNumber());
        orderNumber.setText(currentOrderNum);
    }

    /**
     * Set orderTextArea to the current pizzas ordered.
     */
    @FXML
    void setPizzas() {
        int currentOrderNum = mainController.getStoreOrders().getAvailable_OrderNumber();
        StoreOrders orders = mainController.getStoreOrders();//.getStoreOrders();
        Order currentOrder = orders.find(currentOrderNum);
        ArrayList<String> pizzas = currentOrder.getPizzas();

        ObservableList<String> pizzaString = FXCollections.observableArrayList(pizzas);
        orderTextArea.setItems(pizzaString);
        System.out.println(pizzaString);
    }

    /**
     * Set subtotal, tax and total text fields to their current values for the order.
     */
    @FXML
    void setPrices() {
        int currentOrderNum = mainController.getStoreOrders().getAvailable_OrderNumber();
        StoreOrders orders = mainController.getStoreOrders();
        Order currentOrder = orders.find(currentOrderNum);

        double subtotalDouble = currentOrder.getOrder_Subtotal();
        String subtotalString = new DecimalFormat("#,##0.00").format(subtotalDouble);
        subtotal.setText(String.valueOf(subtotalString));
        double taxDouble = subtotalDouble * 0.06625;
        String taxString = new DecimalFormat("#,##0.00").format(taxDouble);
        tax.setText(taxString);
        double totalDouble = subtotalDouble + taxDouble;
        String totalString = new DecimalFormat("#,##0.00").format(totalDouble);
        total.setText(String.valueOf(totalString));
    }

    /**
     * Button handler for Remove Pizza button.
     * @param event ActionEvent
     */
    @FXML
    protected void onRemovePizzaClick(ActionEvent event) {
        SelectionModel<String> selected = orderTextArea.getSelectionModel();
        int selectedIndex = selected.getSelectedIndex();

        StoreOrders orders = mainController.getStoreOrders();
        Order currentOrder = orders.find(mainController.getStoreOrders().getAvailable_OrderNumber());
        currentOrder.removePizza(selectedIndex);
        setPizzas();
        setPrices();
    }

    /**
     * Button handler for Place Order button.
     * @param event ActionEvent
     */
    @FXML
    protected void onPlaceOrderClick(ActionEvent event) {
        int currIndex = mainController.getStoreOrders().getAvailable_OrderNumber();
        StoreOrders orders = mainController.getStoreOrders();

        ArrayList<String> pizzaList = orders.find(currIndex).getPizzas();
        if (pizzaList.isEmpty()) {
            emptyPizzaPopup();
            return;
        }

        orders.addOrder(orders.find(currIndex));
        mainController.getOrdersPlaced().add(currIndex); //purpose since all orders whether it was placed or not shows in storeOrders

        setPizzas();
        setPrices();
        setOrderNumber();
        orderSuccessPopup();

    }

    /**
     * Create popup for empty order.
     */
    void emptyPizzaPopup() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Order Warning");
        alert.setContentText("Cannot place an order without any pizzas!");
        alert.showAndWait();
    }

    /**
     * Create popup for successful order.
     */
    void orderSuccessPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Success");
        alert.setContentText("Your order was successfully placed!");
        alert.showAndWait();
    }

}
