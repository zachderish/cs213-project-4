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
import java.lang.reflect.Array;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Store Orders GUI Controller class.
 * @author Kenrick Eagar, Zachary Derish
 */
public class StoreOrdersController implements Initializable {

    @FXML
    private ListView<String> orderList;
    @FXML
    private TextField totalText;
    @FXML
    private ChoiceBox<Integer> soBox;
    @FXML
    private Button cancelButton, exportButton;

    private MainController mainController = new MainController().getReference();
    private StoreOrders orders;

    private ArrayList<Integer> currentOrderNumbers;

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
     * set mainController to current MainController
     * @param controller, the main controller we want to save
     */
    public void setMainController(MainController controller) {
        mainController = controller;
    }

    /**
     * Pushes alert stating no pizza orders have been placed
     */
    void noPizzaAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Pizza");
        alert.setContentText("No Pizza Orders Placed");
        alert.showAndWait();
    }

    /**
     * Initialize all list views
     * @param url, default url
     * @param resourceBundle, default resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orders = mainController.getStoreOrders();
        if (orders.numberOfOrders() == 0) {
            noPizzaAlert();
            return;
        }
        currentOrderNumbers = orders.getOrderNumbers();
        soBox.getItems().addAll(currentOrderNumbers);

        soBox.setOnAction(this::displayPizzas);

    }

    /**
     * Method to calculate and show current price of order
     */
    void setPrice() {
        int orderNum = soBox.getValue();
        double taxes = 0.06625;
        double subtotal = orders.find(orderNum).getOrder_Subtotal();
        double total = (subtotal * taxes) + subtotal;
        String totalString = new DecimalFormat("#,##0.00").format(total);
        totalText.setText(totalString);
    }

    /**
     * Method to update the list of order numbers in choice box
     * @param event, the event triggering the method
     * @param orderNumber, the order number we want to update
     */
    @FXML
    void updateChoiceBox(ActionEvent event, int orderNumber) {
        soBox.getItems().removeAll(currentOrderNumbers);
        currentOrderNumbers = mainController.getStoreOrders().getOrderNumbers();
        soBox.getItems().addAll(currentOrderNumbers);

    }

    /**
     * Method to display pizzas in an order
     * @param event, the event triggering our method
     */
    @FXML
    void displayPizzas(ActionEvent event) {
        if (soBox.getValue() == null || orders.numberOfOrders() == 0) {
            //noPizzaAlert();
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

    /**
     * Displays Message if user trys to cancel orders with no pizzas
     * @param event, event triggering our method
     */
    @FXML
    void emptyCancelAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Error");
        alert.setContentText("Cannot Cancel, No Pizzas In Order");
        alert.showAndWait();
    }

    /**
     * Displays message if user trys to cancel without selecting order
     * @param event
     */
    @FXML
    void nullAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Error");
        alert.setContentText("Cannot Cancel, No Pizzas Selected");
        alert.showAndWait();
    }

    /**
     * Displays message when cancel order is successful
     * @param event, the event triggering our method
     */
    @FXML
    void cancelSuccessAlert(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Successful");
        alert.setContentText("Order Successfully Cancelled");
        alert.showAndWait();
    }
    /**
     * Displays message when action is performed on our not officially placed
     * @param event, the event triggering our method
     */
    @FXML
    void notPlacedAlert(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Error");
        alert.setContentText("Order has not been officially placed");
        alert.showAndWait();
    }

    /**
     * Method to cancel an order in GUI interface
     * @param event, the event triggering our method
     */
    @FXML
    void cancelOrder(ActionEvent event) {
        if (soBox.getValue() == null) {
            nullAlert(event);
            return;
        }
        ArrayList<Integer> ordersPlaced = mainController.getReference().getOrdersPlaced();
        if(!contains(ordersPlaced, soBox.getValue())){
            notPlacedAlert(event);
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
        removeOrderPlaced(currentNumb);
        updateChoiceBox(event, currentNumb);
        cancelSuccessAlert(event);
        displayPizzas(event);


    }

    /**
     * return boolean if integer is in a given integer ArrayList
     * @param list, a given integer arraylist
     * @param orderNumber, the integer we want to search for
     * @return true if order is in list, false otherwise
     */
    private boolean contains(ArrayList<Integer> list, int orderNumber){
        for(int i =0; i<list.size(); i++){
            if(list.get(i) == orderNumber){
                return true;
            }
        }
        return false;
}

    /**
     * Remove ordernumber from the ordersPlaced list
     * @param orderNumber, the list of orderNumbers officially placed
     */
    private void removeOrderPlaced(int orderNumber){
    ArrayList<Integer> ordersPlaced = mainController.getReference().getOrdersPlaced();
    for(int i =0; i<ordersPlaced.size(); i++){
        if(ordersPlaced.get(i) == orderNumber){
            ordersPlaced.remove(i);
            return;
        }
    }
}


    /*
    //For testing will delete later
private void printPlacedCurrent(ArrayList<Integer> placed, ArrayList<Integer> current){
        System.out.println("Beginning Placed List");
    for (Integer value : placed) {
        System.out.println(value);
    }
    System.out.println("End Placed List");
    System.out.println("Beginning Current List");
    for (Integer integer : current) {
        System.out.println(integer);
    }
    System.out.println("End Current List");
}
*/

    /**
     * Method to check if all existing orders with pizzas have been officially placed
     * @param orders, a storeOrder object
     * @return true if all orders have been officially placed, false otherwise
     */
    private boolean allOrdersPlaced(StoreOrders orders){
        orders = mainController.getReference().getStoreOrders();
     ArrayList<Integer> ordersPlaced = mainController.getReference().getOrdersPlaced();
     currentOrderNumbers = mainController.getReference().getStoreOrders().getOrderNumbers();

     int index = currentOrderNumbers.get(currentOrderNumbers.size()-1);
     if(orders.find(index).getPizzas().isEmpty()){
         return true;
     }
    return false;

}

    /**
     * Displays message if export was unsuccessful
     * @param event, the event triggering our method
     */
    @FXML
    void exportErrorAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Error");
        alert.setContentText("Could Not Export");
        alert.showAndWait();
    }
    /**
     * Displays message if export was unsuccessful
     * @param event, the event triggering our method
     */
    @FXML
    void exportSuccessAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Successful");
        alert.setContentText("Successfully Exported Store Orders");
        alert.showAndWait();
    }

    /**
     * Displays message if order with pizzas have not been officially placed
     * @param event, the event triggering our method
     */
    @FXML
    void orderNotPlacedExportAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export Error");
        alert.setContentText("Last Order Has Not Been Officially Placed");
        alert.showAndWait();
    }

    /**
     * Method to export store orders to file
     * @param event, the event triggering our method
     */
    @FXML
    void exportToFile(ActionEvent event){

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        if(!allOrdersPlaced(orders)){
            orderNotPlacedExportAlert(event); //check if we have a order that has pizzas but not been officially "placed"
            return;
        }

        boolean expSuccess = orders.export(stage);

        if(!expSuccess){
            exportErrorAlert(event);
            return;
        }
        exportSuccessAlert(event);

    }

}