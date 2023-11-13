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
import java.util.ResourceBundle;

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

    private Pizza pizza;

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
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        Scene mainMenuScene = new Scene(mainMenuRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("RU Pizza <Main Menu>");

        stage.setScene(mainMenuScene);
        stage.show();
    }
}
