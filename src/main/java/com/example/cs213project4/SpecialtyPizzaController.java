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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Specialty Pizza GUI Controller class.
 * @author Kenrick Eagar, Zachary Derish
 */
public class SpecialtyPizzaController implements Initializable {
    @FXML
    private ComboBox<String> specialtyComboBox;
    @FXML
    private RadioButton specialtySmall, specialtyMedium, specialtyLarge;
    @FXML
    private Button specialtyAddBtn;
    @FXML
    private CheckBox specialtyExtraSauce, specialtyExtraCheese;
    @FXML
    private TextField specialtyPrice, specialtySauce;
    @FXML
    private ListView<Topping> specialtyToppings;
    @FXML
    private ImageView pizzaImage;

    private Pizza pizza;
    private MainController mainController;

    /**
     * Initialize the Specialty Pizza Menu and ComboBox.
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        specialtyComboBox.getItems().addAll(items);

        specialtyComboBox.setOnAction(this::setPizza);
    }

    /**
     * Convert a double pizza price to a String rounded to two decimal places.
     * @param price double pizza price
     * @return String format of price
     */
    private String formatDouble(double price) {
        DecimalFormat format = new DecimalFormat("#.##");
        price = Double.parseDouble(format.format(price));
        return String.valueOf(price);
    }

    /**
     * Set Specialty Pizza Menu on ComboBox action.
     * @param event ActionEvent
     */
    @FXML
    void setPizza(ActionEvent event) {
        String pizzaType = specialtyComboBox.getValue();
        pizza = PizzaMaker.createPizza(pizzaType);

        specialtySmall.setDisable(false);
        specialtyMedium.setDisable(false);
        specialtyLarge.setDisable(false);
        specialtyExtraSauce.setDisable(false);
        specialtyExtraCheese.setDisable(false);
        specialtyAddBtn.setDisable(false);

        ActionEvent event1 = new ActionEvent();
        setSize(event1);
        setExtras(event1);

        setPrice();
        setSauce();
        setToppings();
        setImage(pizzaType);
    }

    /**
     *Set the image based off given pizza type
     * @param pizzaType, string describing pizza type
     */
    @FXML
    private void setImage(String pizzaType) {

        String relativePath = "src/main/resources/com/example/cs213project4/";
        if (pizzaType.equals("Deluxe")) {
            Image image = new Image("file:" +  relativePath + "deluxe.jpg");
            pizzaImage.setImage(image);
        }
        if (pizzaType.equals("Meatzza")) {
            Image image = new Image("file:" +  relativePath + "meatzza.jpg");
            pizzaImage.setImage(image);
        }
        if (pizzaType.equals("Pepperoni")) {
            Image image = new Image("file:" +  relativePath + "pepperoni.jpg");
            pizzaImage.setImage(image);
        }
        if (pizzaType.equals("Seafood")) {
            Image image = new Image("file:" +  relativePath + "seafood.jpg");
            pizzaImage.setImage(image);
        }
        if (pizzaType.equals("Supreme")) {
            Image image = new Image("file:" +  relativePath + "supreme.jpg");
            pizzaImage.setImage(image);
        }
    }


    /**
     * Set ListView to the toppings of the chosen pizza.
     */
    @FXML
    private void setToppings() {
        specialtyToppings.setItems(FXCollections.observableArrayList(pizza.toppings));
    }

    /**
     * Set Sauce Text Area to the sauce of the chosen pizza.
     */
    @FXML
    private void setSauce() {
        if (pizza.sauce == Sauce.TOMATO) {
            specialtySauce.setText("Tomato");
        }
        else {
            specialtySauce.setText("Alfredo");
        }
    }

    /**
     * Set Price Text Area to the price of the chosen pizza and its attributes.
     */
    @FXML
    private void setPrice() {
        String price = formatDouble(pizza.price());
        specialtyPrice.setText(price);
    }

    /**
     * Set pizza size attribute to the size chosen.
     * @param event ActionEvent
     */
    @FXML
    void setSize(ActionEvent event) {
        if (specialtySmall.isSelected()) {
            pizza.size = Size.SMALL;
        }
        if (specialtyMedium.isSelected()) {
            pizza.size = Size.MEDIUM;
        }
        if (specialtyLarge.isSelected()) {
            pizza.size = Size.LARGE;
        }
        setPrice();
    }

    /**
     * Set pizza extraCheese and extraSauce attributes based on selections.
     * @param event ActionEvent
     */
    @FXML
    void setExtras(ActionEvent event) {
        if (specialtyExtraCheese.isSelected()) {
            pizza.extraCheese = true;
        }
        if (!specialtyExtraCheese.isSelected()) {
            pizza.extraCheese = false;
        }
        if (specialtyExtraSauce.isSelected()) {
            pizza.extraSauce = true;
        }
        if (!specialtyExtraSauce.isSelected()) {
            pizza.extraSauce = false;
        }
        setPrice();
    }

    /**
     * Button handler for back button.
     * @param event ActionEvent
     */
    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        Scene mainMenuScene = new Scene(mainMenuRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("RU Pizza <Main Menu>");

        stage.setScene(mainMenuScene);
        stage.show();
    }

    /**
     * Button handler for add order, adds pizza to order array list.
     * @param event ActionEvent
     */
    @FXML
    void onAddOrderClick(ActionEvent event) {
        StoreOrders orders = mainController.getStoreOrders();
        int currentOrderNumber = orders.getAvailable_OrderNumber();

        Order currentOrder = orders.find(currentOrderNumber);

        currentOrder.addPizza(pizza);
        ActionEvent fakeEvent = new ActionEvent();
        setPizza(fakeEvent);
        ArrayList<String> pizzas = currentOrder.getPizzas();

        pizzaAddedPopup();
    }

    /**
     * Get the reference to the MainController object.
     * @param controller MainController
     */
    public void setMainController(MainController controller) {
        mainController = controller;
    }

    /**
     * Create popup for successful pizza added.
     */
    void pizzaAddedPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Added");
        alert.setContentText("Pizza added successfully to your order!");
        alert.showAndWait();
    }

}
